import java.util.Arrays;

/**
 * LeetCode 34. Find First and Last Position of Element in Sorted Array
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * PROBLEM
 *   - Input: an array nums sorted in non-decreasing order (duplicates allowed) and a target.
 *   - Return: [first index, last index] of target, or [-1, -1] if it isn't there.
 *   - Constraint: O(log n) time, so finding one match and walking outwards is not allowed.
 *
 * EXAMPLE
 *   Input: nums = [5,7,7,8,8,10], target = 8
 *   Output: [3,4]
 *
 * HOW TO THINK
 *   - Clue: "sorted" + "O(log n)" = binary search. "First and last" = two boundary searches.
 *   - Technique: plain binary search, but a match does not stop the loop. Save it in ans and
 *     keep going: left (hi = mid - 1) to find the first, right (lo = mid + 1) to find the last.
 *   - Rule that stays true: ans is the best match found so far, and any better match is
 *     still inside [lo, hi].
 *   - Decision: nums[mid] == target -> ans = mid, then move towards the side being searched.
 *               nums[mid] <  target -> lo = mid + 1.
 *               nums[mid] >  target -> hi = mid - 1.
 *   - Return: [first search's ans, last search's ans]; ans stays -1 if target never matched.
 *
 * STEPS
 *   1. findBound(first = true) and findBound(first = false), each with lo = 0, hi = n - 1, ans = -1.
 *   2. While lo <= hi: mid = lo + (hi - lo) / 2.
 *   3. Equal -> ans = mid; first ? hi = mid - 1 : lo = mid + 1.
 *      Smaller -> lo = mid + 1. Bigger -> hi = mid - 1.
 *   4. Return ans; the answer is [first bound, last bound].
 *
 * DRY RUN   nums = [5,7,7,8,8,10], target = 8   (lo and hi are values before the step's action)
 *
 *    search | lo | hi | mid | nums[mid] | compare | ans after | action
 *   --------+----+----+-----+-----------+---------+-----------+--------
 *    first  |  0 |  5 |  2  |     7     | 7 < 8   |    -1     | lo = 3
 *    first  |  3 |  5 |  4  |     8     | equal   |     4     | hi = 3
 *    first  |  3 |  3 |  3  |     8     | equal   |     3     | hi = 2, stop
 *    last   |  0 |  5 |  2  |     7     | 7 < 8   |    -1     | lo = 3
 *    last   |  3 |  5 |  4  |     8     | equal   |     4     | lo = 5
 *    last   |  5 |  5 |  5  |    10     | 10 > 8  |     4     | hi = 4, stop
 *
 *   Returns [3, 4].
 *
 * EDGE CASES
 *   [5,7,7,8,8,10], 6   -> [-1,-1]   target missing: ans never set
 *   [], 0               -> [-1,-1]   empty array: hi = -1, the loop never runs
 *   [1], 1              -> [0,0]     one element is both first and last
 *   [2,2,2,2], 2        -> [0,3]     every element matches
 *   [1,2,3], 3          -> [2,2]     target only at the right end
 *
 * COMMON MISTAKES
 *   - Returning on the first match: it may be in the middle of the run of equal values.
 *   - Finding one match then walking left and right: O(n) when the whole array is the target.
 *   - Moving the wrong way after a match: the first search must go left, the last must go right.
 *
 * COMPLEXITY
 *   O(log n) time (two binary searches), O(1) extra space.
 *
 * RELATED
 *   LeetCode 704 (BinarySearch, BinarySearch/): the same loop, but it stops at the first match.
 *   LeetCode 981 (TimeBasedKeyValueStore, HashMap/): the same "save ans and keep searching" trick
 *     to find the last time <= t.
 *   LeetCode 35 (Search Insert Position, not in repo yet): the first search, but it returns lo
 *     when there is no match.
 */
public class FirstAndLastPosition {

    public int[] searchRange(int[] nums, int target) {
        return new int[]{findBound(nums, target, true), findBound(nums, target, false)};
    }

    private int findBound(int[] nums, int target, boolean first) {
        int lo = 0;
        int hi = nums.length - 1;
        int ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                ans = mid;
                if (first) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        FirstAndLastPosition sol = new FirstAndLastPosition();

        System.out.println(Arrays.toString(sol.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8))); // Expected: [3, 4]
        System.out.println(Arrays.toString(sol.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6))); // Expected: [-1, -1]
        System.out.println(Arrays.toString(sol.searchRange(new int[]{}, 0)));                  // Expected: [-1, -1]
        System.out.println(Arrays.toString(sol.searchRange(new int[]{1}, 1)));                 // Expected: [0, 0]
        System.out.println(Arrays.toString(sol.searchRange(new int[]{2, 2, 2, 2}, 2)));        // Expected: [0, 3]
        System.out.println(Arrays.toString(sol.searchRange(new int[]{1, 2, 3}, 3)));           // Expected: [2, 2]
    }
}

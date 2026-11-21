/**
 * LeetCode 704. Binary Search
 * https://leetcode.com/problems/binary-search/
 *
 * PROBLEM
 *   - Input: an array nums sorted in ascending order (all values distinct) and a target.
 *   - Return: the index of target, or -1 if it isn't there.
 *   - Constraint: O(log n) time.
 *
 * EXAMPLE
 *   Input: nums = [-1,0,3,5,9,12], target = 9
 *   Output: 4
 *   Explanation: 9 exists in nums and its index is 4
 *
 * HOW TO THINK
 *   - Clue: "sorted" + "O(log n)" = binary search.
 *   - Technique: look at the middle value. Because the array is sorted, one comparison tells
 *     which half can still hold target, so the other half is thrown away.
 *   - Rule that stays true: if target is in nums, its index is inside [lo, hi].
 *   - Decision: nums[mid] == target -> return mid.
 *               nums[mid] <  target -> target is to the right: lo = mid + 1.
 *               nums[mid] >  target -> target is to the left:  hi = mid - 1.
 *   - Return: mid when found; -1 once lo > hi (the range is empty).
 *
 * STEPS
 *   1. lo = 0, hi = n - 1.
 *   2. While lo <= hi: mid = lo + (hi - lo) / 2.
 *   3. Equal -> return mid. Smaller -> lo = mid + 1. Bigger -> hi = mid - 1.
 *   4. Return -1.
 *
 * DRY RUN   nums = [-1,0,3,5,9,12], target = 5   (lo and hi are values before the step's action)
 *
 *    lo | hi | mid | nums[mid] | compare  | action
 *   ----+----+-----+-----------+----------+-----------
 *     0 |  5 |  2  |     3     | 3 < 5    | lo = 3
 *     3 |  5 |  4  |     9     | 9 > 5    | hi = 3
 *     3 |  3 |  3  |     5     | 5 == 5   | return 3
 *
 *   Returns 3.
 *
 * EDGE CASES
 *   [-1,0,3,5,9,12], 9   -> 4    official example
 *   [-1,0,3,5,9,12], 2   -> -1   lo and hi cross between 0 and 3
 *   [5], 5               -> 0    one element, found at the first mid
 *   [5], 7               -> -1   one element, not the target
 *   [1,2,3], 1           -> 0    target at the left end
 *   [1,2,3], 3           -> 2    target at the right end
 *
 * COMMON MISTAKES
 *   - Loop condition lo < hi: misses the last one-element range, e.g. [5], 5 returns -1.
 *   - Setting lo = mid or hi = mid: the range can stop shrinking and loop forever.
 *   - mid = (lo + hi) / 2: lo + hi can overflow for huge arrays; use lo + (hi - lo) / 2.
 *
 * COMPLEXITY
 *   O(log n) time (the range halves every step), O(1) extra space.
 *
 * RELATED
 *   LeetCode 34 (FirstAndLastPosition, BinarySearch/): on a match, keep searching left or right
 *     instead of returning.
 *   LeetCode 74 (Search2DMatrix, BinarySearch/): the same search on a matrix read as one long array.
 *   LeetCode 33 (SearchInRotatedDistinctArray, FAQ/): only one half is sorted, so check which.
 *   LeetCode 69 (SqrRtx, Math/): binary search on the answer instead of on an array.
 *   LeetCode 981 (TimeBasedKeyValueStore, HashMap/): finds the last time <= t instead of an exact match.
 */
public class BinarySearch {

    public int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch sol = new BinarySearch();
        int[] nums = {-1, 0, 3, 5, 9, 12};

        System.out.println(sol.search(nums, 5));              // Expected: 3
        System.out.println(sol.search(nums, 9));              // Expected: 4
        System.out.println(sol.search(nums, 2));              // Expected: -1
        System.out.println(sol.search(new int[]{5}, 5));      // Expected: 0
        System.out.println(sol.search(new int[]{5}, 7));      // Expected: -1
        System.out.println(sol.search(new int[]{1, 2, 3}, 1)); // Expected: 0
        System.out.println(sol.search(new int[]{1, 2, 3}, 3)); // Expected: 2
    }
}

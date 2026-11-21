/**
 * LeetCode 153. Find Minimum in Rotated Sorted Array
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 *
 * PROBLEM
 *   - Input: an ascending array of unique values, rotated 1 to n times.
 *     Example: [0,1,2,4,5,6,7] rotated 4 times -> [4,5,6,7,0,1,2]. Rotated n times = not rotated.
 *   - Return: the minimum value.
 *   - Constraint: O(log n) time.
 *
 * EXAMPLE
 *   Input: nums = [3,4,5,1,2]
 *   Output: 1
 *   Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 *
 * HOW TO THINK
 *   - Clue: "sorted" + "rotated" + "O(log n)" = binary search for the drop point.
 *   - Technique: the array is two ascending runs, and the minimum is the start of the second run.
 *     Compare mid with the right end nums[hi]:
 *       nums[mid] > nums[hi] -> mid is in the first (bigger) run, so the minimum is right of mid.
 *       nums[mid] < nums[hi] -> mid..hi is ascending, so the minimum is mid or left of it.
 *   - Rule that stays true: the minimum's index is inside [lo, hi].
 *   - Decision: nums[mid] > nums[hi] -> lo = mid + 1. Else -> hi = mid (keep mid, it may be the minimum).
 *   - Return: nums[lo] once lo == hi.
 *
 * STEPS
 *   1. lo = 0, hi = n - 1.
 *   2. While lo < hi: mid = lo + (hi - lo) / 2.
 *   3. nums[mid] > nums[hi] -> lo = mid + 1, else hi = mid.
 *   4. Return nums[lo].
 *
 * DRY RUN   nums = [4,5,6,7,0,1,2]   (lo and hi are values before the step's action)
 *
 *    lo | hi | mid | nums[mid] | nums[hi] | compare | action
 *   ----+----+-----+-----------+----------+---------+--------
 *     0 |  6 |  3  |     7     |    2     | 7 > 2   | lo = 4
 *     4 |  6 |  5  |     1     |    2     | 1 < 2   | hi = 5
 *     4 |  5 |  4  |     0     |    1     | 0 < 1   | hi = 4
 *     4 |  4 |  -  |     -     |    -     | lo == hi| stop
 *
 *   Returns nums[4] = 0.
 *
 * EDGE CASES
 *   [3,4,5,1,2]       -> 1    official example
 *   [11,13,15,17]     -> 11   not rotated: hi keeps moving left to 0
 *   [1]               -> 1    one element: the loop never runs
 *   [2,1]             -> 1    smallest rotation: lo moves past the 2
 *   [2,3,4,5,1]       -> 1    minimum at the last index
 *   [1,1,0,1]         -> 1 (wrong, the minimum is 0): duplicates make nums[mid] == nums[hi]
 *                        ambiguous; LeetCode 153 guarantees unique values (see LeetCode 154)
 *
 * COMMON MISTAKES
 *   - Comparing mid with nums[lo]: an unrotated array like [1,2,3,4,5] has nums[mid] > nums[lo]
 *     and sends the search right, away from the minimum. Compare with nums[hi].
 *   - hi = mid - 1: mid can be the minimum, e.g. [3,1,2] has mid = 1 (value 1), hi becomes 0,
 *     and it returns 3.
 *   - Loop condition lo <= hi with hi = mid: never ends once lo == hi.
 *
 * COMPLEXITY
 *   O(log n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 33 (SearchInRotatedDistinctArray, FAQ/): searches for a target in the same kind of
 *     array; checks which half is sorted instead of looking for the drop.
 *   LeetCode 81 (SearchInRotatedDuplicateArray, FAQ/): duplicates allowed, so an equal compare
 *     can only shrink the range by one.
 *   LeetCode 162 (FindPeakElement, BinarySearch/): the same lo < hi / hi = mid shape.
 */
public class MinInRotatedSortedArray {

    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return nums[lo];
    }

    public static void main(String[] args) {
        MinInRotatedSortedArray sol = new MinInRotatedSortedArray();

        System.out.println(sol.findMin(new int[]{4, 5, 6, 7, 0, 1, 2})); // Expected: 0
        System.out.println(sol.findMin(new int[]{3, 4, 5, 1, 2}));       // Expected: 1
        System.out.println(sol.findMin(new int[]{11, 13, 15, 17}));      // Expected: 11
        System.out.println(sol.findMin(new int[]{1}));                   // Expected: 1
        System.out.println(sol.findMin(new int[]{2, 1}));                // Expected: 1
        System.out.println(sol.findMin(new int[]{2, 3, 4, 5, 1}));       // Expected: 1
        System.out.println(sol.findMin(new int[]{1, 1, 0, 1}));          // Expected: 1 (wrong on purpose, duplicates)
    }
}

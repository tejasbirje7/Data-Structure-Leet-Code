/**
 * LeetCode 81. Search in Rotated Sorted Array II
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 *
 * PROBLEM
 *   - Input: a non-decreasing array rotated at an unknown pivot (duplicates allowed), and a target.
 *   - Return: true if target is in the array, otherwise false.
 *   - Goal: as few steps as possible; duplicates make O(log n) impossible in the worst case.
 *
 * EXAMPLE
 *   Input: nums = [2,5,6,0,0,1,2], target = 0
 *   Output: true
 *
 * HOW TO THINK
 *   - Clue: "sorted" + "rotated" = binary search on the sorted half, as in LeetCode 33.
 *   - Technique: compare nums[l] and nums[m] to find which half is sorted.
 *     Duplicates add one new case: nums[l] == nums[m] tells you nothing (e.g. [1,0,1,1,1]).
 *   - Rule that stays true: if target is in nums, one copy of it is inside [l, r].
 *   - Decision: nums[m] == target -> return true.
 *               nums[l] < nums[m] -> left half sorted:
 *                 nums[l] <= target < nums[m] -> r = m - 1, else l = m + 1.
 *               nums[l] > nums[m] -> right half sorted:
 *                 nums[m] < target <= nums[r] -> l = m + 1, else r = m - 1.
 *               nums[l] == nums[m] -> can't decide: l++ (safe, nums[l] equals nums[m], not target).
 *   - Return: true when found; false once l > r.
 *
 * STEPS
 *   1. l = 0, r = n - 1.
 *   2. While l <= r: m = l + (r - l) / 2; if nums[m] == target, return true.
 *   3. If nums[l] < nums[m]: go left if nums[l] <= target < nums[m], else go right.
 *   4. Else if nums[l] > nums[m]: go right if nums[m] < target <= nums[r], else go left.
 *   5. Else (equal): l++.
 *   6. Return false.
 *
 * DRY RUN   nums = [1,0,1,1,1], target = 0   (l and r are values before the step's action)
 *
 *    l | r | m | nums[l] | nums[m] | nums[r] | case                   | action
 *   ---+---+---+---------+---------+---------+------------------------+--------
 *    0 | 4 | 2 |    1    |    1    |    1    | equal, can't tell      | l = 1
 *    1 | 4 | 2 |    0    |    1    |    1    | left sorted, 0<=0<1    | r = 1
 *    1 | 1 | 1 |    0    |    0    |    0    | nums[m] == target      | found
 *
 *   Returns true.
 *
 * EDGE CASES
 *   [2,5,6,0,0,1,2], 0  -> true    official example: nums[3] = 0 is found at the first mid
 *   [2,5,6,0,0,1,2], 3  -> false   right half sorted but 3 isn't in it, search ends empty
 *   [1,1,1,1], 2        -> false   every step is the "equal" case: l++ each time, O(n)
 *   [1], 1              -> true    found at the first mid
 *   [3,1], 1            -> true    l == m counts as "equal", l++ then found
 *   []                  -> false   r = -1, the loop never runs; LeetCode guarantees n >= 1
 *
 * COMMON MISTAKES
 *   - Reusing the LeetCode 33 test nums[l] <= nums[m]: [1,0,1,1,1], 0 returns false.
 *     Handle nums[l] == nums[m] separately with l++.
 *   - Plain binary search (compare target with nums[m] only): the rotation breaks the order.
 *     Find the sorted half first, then check whether target lies in it.
 *   - Expecting O(log n): with many duplicates the l++ steps make it O(n).
 *
 * COMPLEXITY
 *   O(log n) time on average, O(n) in the worst case (all equal). O(1) extra space.
 *
 * RELATED
 *   LeetCode 33 (SearchInRotatedDistinctArray, FAQ/): values are distinct, so nums[l] <= nums[m]
 *     always identifies a sorted half and the search is always O(log n).
 *   LeetCode 852 (PeakIndexInMountainArray, FAQ/): binary search that picks a side by comparing
 *     mid with its neighbour.
 */
public class SearchInRotatedDuplicateArray {
    public boolean search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (nums[m] == target) return true;

            if (nums[l] < nums[m]) {
                if (nums[m] > target && nums[l] <= target)
                    r = m - 1;
                else
                    l = m + 1;
            } else if (nums[l] > nums[m]) {
                if (nums[m] < target && nums[r] >= target)
                    l = m + 1;
                else
                    r = m - 1;
            } else {
                l++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SearchInRotatedDuplicateArray solution = new SearchInRotatedDuplicateArray();

        System.out.println(solution.search(new int[]{2,5,6,0,0,1,2}, 0)); // true
        System.out.println(solution.search(new int[]{2,5,6,0,0,1,2}, 3)); // false
        System.out.println(solution.search(new int[]{1,0,1,1,1}, 0));     // true
    }
}

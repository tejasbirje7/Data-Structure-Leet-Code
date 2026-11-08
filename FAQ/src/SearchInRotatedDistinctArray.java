/**
 * LeetCode 33. Search in Rotated Sorted Array
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * PROBLEM
 *   - Input: an ascending array of distinct values, rotated at an unknown pivot, and a target.
 *     Example: [0,1,2,4,5,6,7] rotated at index 3 -> [4,5,6,7,0,1,2].
 *   - Return: the index of target, or -1 if it isn't there.
 *   - Constraint: O(log n) time, so a plain linear scan is not allowed.
 *
 * EXAMPLE
 *   nums = [4,5,6,7,0,1,2], target = 0
 *   Output: 4
 *   Explanation: target found at index 4.
 *
 * HOW TO THINK
 *   - Clue: "sorted" + "O(log n)" = binary search. "Rotated" means only part of the range is sorted.
 *   - Technique: binary search where, at each mid, at least one half [l..m] or [m..r] is sorted.
 *     In a sorted half you can tell by its two ends whether target is inside.
 *   - Rule that stays true: if target is in nums, its index is inside [l, r].
 *   - Decision: nums[m] == target -> return m.
 *               nums[l] <= nums[m] -> left half sorted:
 *                 nums[l] <= target < nums[m] -> r = m - 1, else l = m + 1.
 *               otherwise -> right half sorted:
 *                 nums[m] < target <= nums[r] -> l = m + 1, else r = m - 1.
 *   - Return: m when found; -1 once l > r.
 *   - Why "<=" in nums[l] <= nums[m]: when l == m the one-element left half counts as sorted.
 *
 * STEPS
 *   1. l = 0, r = n - 1.
 *   2. While l <= r: m = l + (r - l) / 2; if nums[m] == target, return m.
 *   3. If nums[l] <= nums[m] (left sorted): go left if nums[l] <= target < nums[m], else go right.
 *   4. Else (right sorted): go right if nums[m] < target <= nums[r], else go left.
 *   5. Return -1.
 *
 * DRY RUN   nums = [5,6,7,0,1,2,4], target = 1   (l and r are values before the step's action)
 *
 *    l | r | m | nums[l] | nums[m] | nums[r] | sorted half  | target inside?   | action
 *   ---+---+---+---------+---------+---------+--------------+------------------+--------
 *    0 | 6 | 3 |    5    |    0    |    4    | right [0..4] | yes, 0 < 1 <= 4  | l = 4
 *    4 | 6 | 5 |    1    |    2    |    4    | left [1..2]  | yes, 1 <= 1 < 2  | r = 4
 *    4 | 4 | 4 |    1    |    1    |    1    | -            | -                | found
 *
 *   Returns 4.
 *
 * EDGE CASES
 *   [4,5,6,7,0,1,2], 0  -> 4    official example: only the left-sorted branch is used
 *   [4,5,6,7,0,1,2], 3  -> -1   l moves right until l > r
 *   [1], 0              -> -1   one element, not the target
 *   [1], 1              -> 0    found at the first mid
 *   [3,1], 1            -> 1    l == m, so "<=" treats [3] as the sorted half and goes right
 *   [1,2,3,4,5], 4      -> 3    not rotated: plain binary search
 *   []                  -> -1   r = -1, the loop never runs; LeetCode guarantees n >= 1
 *   [1,0,1,1,1], 0      -> -1 (wrong, 0 is at index 1): duplicates break the sorted-half test;
 *                          LeetCode 33 guarantees distinct values (see LeetCode 81)
 *
 * COMMON MISTAKES
 *   - Using nums[l] < nums[m]: when l == m it picks the wrong half, e.g. [3,1], 1 returns -1.
 *   - Wrong strictness in the range check: the far end (nums[l] or nums[r]) can be the target,
 *     so compare it with <=; nums[m] was already checked, so compare it strictly.
 *   - Finding the pivot first and then searching: works, but two binary searches are more code.
 *
 * COMPLEXITY
 *   O(log n) time (half the range is dropped each step), O(1) extra space.
 *
 * RELATED
 *   LeetCode 81 (SearchInRotatedDuplicateArray, FAQ/): duplicates allowed, so when
 *     nums[l] == nums[m] it can't tell which half is sorted and just does l++.
 *   LeetCode 852 (PeakIndexInMountainArray, FAQ/): binary search that picks a side by comparing
 *     mid with its neighbour instead of a target.
 */
public class SearchInRotatedDistinctArray {

    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (nums[m] == target) return m;

            if (nums[l] <= nums[m]) {
                if (target >= nums[l] && target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            else {
                if (target > nums[m] && target <= nums[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchInRotatedDistinctArray sol = new SearchInRotatedDistinctArray();

        int[] nums1 = {5,6,7,0,1,2,4};
        int[] nums2 = {4,5,6,7,0,1,2};
        int[] nums3 = {1};

        System.out.println(sol.search(nums1, 1)); // Expected: 4
        System.out.println(sol.search(nums2, 3)); // Expected: -1
        System.out.println(sol.search(nums3, 0)); // Expected: -1
    }
}

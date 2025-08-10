
/*

Array is rotated, sorted, and has distinct values.

1. The array is sorted but rotated at some pivot.
   Example: [0,1,2,4,5,6,7] rotated at index 3 → [4,5,6,7,0,1,2]

2. Standard binary search works if we know which half is sorted.
   At any mid position, either:
      - Left half is sorted → nums[l] <= nums[m]
      - Right half is sorted → nums[m] < nums[r]

3. Once we detect the sorted half:
      - Check if target lies inside this sorted range.
      - If YES → move search boundaries inside this half.
      - If NO → search in the other half.

4. Time Complexity: O(log n)
   Space Complexity: O(1)

Example 1:
nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Explanation: target found at index 4.

Example 2:
nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Explanation: target not found.

Example 3:
nums = [1], target = 0
Output: -1
Explanation: Only one element and it’s not the target.
---------------------------------
*/

public class SearchInRotatedDistinctArray {

    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (nums[m] == target) return m;

            // Left half is sorted
            if (nums[l] <= nums[m]) {
                if (target >= nums[l] && target < nums[m]) {
                    r = m - 1; // Search left
                } else {
                    l = m + 1; // Search right
                }
            }
            // Right half is sorted
            else {
                if (target > nums[m] && target <= nums[r]) {
                    l = m + 1; // Search right
                } else {
                    r = m - 1; // Search left
                }
            }
        }
        return -1; // Not found
    }

    public static void main(String[] args) {
        SearchInRotatedDistinctArray sol = new SearchInRotatedDistinctArray();

        int[] nums1 = {4,5,6,7,0,1,2};
        int[] nums2 = {4,5,6,7,0,1,2};
        int[] nums3 = {1};

        System.out.println(sol.search(nums1, 0)); // Expected: 4
        System.out.println(sol.search(nums2, 3)); // Expected: -1
        System.out.println(sol.search(nums3, 0)); // Expected: -1
    }
}

/*
Example 1:
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Example 2:
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
*/

/*

Array is rotated, sorted, but can contain duplicates.

1. This is a variation of "Search in Rotated Sorted Array" where:
   - The array is sorted but may contain duplicates.
   - It's rotated at some pivot.

2. Standard binary search works for distinct elements, but duplicates cause ambiguity
   because we cannot always determine which half is sorted.

3. Main approach:
   - Compare nums[l], nums[m], nums[r] to find the sorted portion.
   - If nums[l] < nums[m], left half is sorted.
   - If nums[l] > nums[m], right half is sorted.
   - If nums[l] == nums[m], increment l (because we can't tell which side is sorted).

4. Complexity:
   - Worst case: O(n) when all elements are same.
   - Average: O(log n) if minimal duplicates.

5. Example:
   nums = [2,5,6,0,0,1,2], target = 0
   - Middle elements will guide which half to search.
   - Handle duplicates carefully by moving left pointer when stuck.
*/

/*
====================
3️⃣ SOLUTION (CLASS)
====================
*/
public class SearchInRotatedDuplicateArray {
    public boolean search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (nums[m] == target) return true;

            if (nums[l] < nums[m]) { // Left half sorted
                if (nums[m] > target && nums[l] <= target)
                    r = m - 1;
                else
                    l = m + 1;
            } else if (nums[l] > nums[m]) { // Right half sorted
                if (nums[m] < target && nums[r] >= target)
                    l = m + 1;
                else
                    r = m - 1;
            } else { // nums[l] == nums[m], can't decide
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

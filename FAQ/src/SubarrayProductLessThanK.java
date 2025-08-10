import java.util.*;

/*
Given an array of integers nums and an integer k, return the number of contiguous
subarrays where the product of all the elements in the subarray is strictly less than k.

Example 1:
Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: Valid subarrays are [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6].

Example 2:
Input: nums = [1,2,3], k = 0
Output: 0
Explanation: No subarray product can be less than 0.

Sliding Window Approach:
Maintain a window [l, r] where the product of all elements is < k.
Extend r (right pointer) one step at a time and multiply current element to product.
If product ≥ k, shrink window from left (l++) while dividing the product accordingly.

Why (r - l + 1)?
For each r, the number of valid subarrays ending at r is exactly the length of the current window.
Example: For [5, 2, 6], valid subarrays ending at 6 are [6], [2, 6], [5, 2, 6] → count = window length = 3.

Edge Cases:
If k <= 1, return 0 immediately (since all elements are positive integers and product will never be < 1).
Time Complexity: O(n) → Each element is processed at most twice (once by right pointer, once by left pointer).
Space Complexity: O(1) → Only a few extra variables are used.


 */
public class SubarrayProductLessThanK {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0; // no valid subarray possible

        int l = 0;             // left pointer
        int product = 1;       // product of current window
        int count = 0;         // total number of valid subarrays

        for (int r = 0; r < nums.length; r++) {
            product *= nums[r];

            // shrink window until product < k
            while (product >= k && l <= r) {
                product /= nums[l];
                l++;
            }

            // add count of subarrays ending at r
            count += (r - l + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        SubarrayProductLessThanK solver = new SubarrayProductLessThanK();

        System.out.println(solver.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100)); // 8
        System.out.println(solver.numSubarrayProductLessThanK(new int[]{1, 2, 3}, 0));       // 0
    }
}

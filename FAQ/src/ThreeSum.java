/*
Given an integer array nums, return all the triplets
[nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k,
and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:
Input: [-1,0,1,2,-1,-4]
Output: [[-1,-1,2], [-1,0,1]]

Example 2:
Input: [0,1,1]
Output: []

Example 3:
Input: [0,0,0]
Output: [[0,0,0]]

Sort the array so we can efficiently use a two-pointer technique to find pairs for each fixed number.
Iterate through the array, fixing one element (nums[i]) at a time.
Use two pointers (left & right) to find pairs whose sum with nums[i] is zero.
Skip duplicates for both the fixed number and inside the two-pointer loop to ensure no duplicate triplets.
Time Complexity: O(n²) (outer loop O(n) × two-pointer search O(n)).
Space Complexity: O(1) extra space (excluding result list).
 */

import java.util.*;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            // Skip duplicate fixed numbers
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;

                    // Skip duplicate left and right values
                    while (left < right && nums[left] == nums[left - 1])
                        left++;
                    while (left < right && nums[right] == nums[right + 1])
                        right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSum solver = new ThreeSum();

        System.out.println(solver.threeSum(new int[]{-1,0,1,2,-1,-4})); // [[-1,-1,2], [-1,0,1]]
        System.out.println(solver.threeSum(new int[]{0,1,1}));          // []
        System.out.println(solver.threeSum(new int[]{0,0,0}));          // [[0,0,0]]
    }
}

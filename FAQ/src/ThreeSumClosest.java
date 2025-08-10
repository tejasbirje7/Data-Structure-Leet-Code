/*

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum closest to 1 is 2 (-1 + 2 + 1 = 2).

Input: nums = [0,0,0], target = 1
Output: 0
Explanation: The closest sum is 0 (0 + 0 + 0 = 0).

Sort the array first so we can use a two-pointer approach for efficiency.
Fix one element at a time (nums[i]) and use left and right pointers to find the closest sum to the target.
Keep track of:
res → current best sum found.
difference → smallest difference from target so far.
If an exact match is found, return immediately (since you can't get closer than zero difference).
Time Complexity: O(n²) (outer loop O(n) × two-pointer O(n)).
Space Complexity: O(1) extra space.
 */
import java.util.*;

public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2]; // initial sum
        int difference = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == target) {
                    return target; // exact match found
                }

                // Update result if this sum is closer
                int diff = Math.abs(sum - target);
                if (diff < difference) {
                    difference = diff;
                    res = sum;
                }

                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSumClosest solver = new ThreeSumClosest();

        System.out.println(solver.threeSumClosest(new int[]{-1, 2, 1, -4}, 1)); // 2
        System.out.println(solver.threeSumClosest(new int[]{0, 0, 0}, 1));      // 0
    }
}

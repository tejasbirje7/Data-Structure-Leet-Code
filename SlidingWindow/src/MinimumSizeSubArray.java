public class MinimumSizeSubArray {

    public static void main(String[] args) {
        int target = 7;
        int[] nums = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(target,nums));
    }

    /**
     * Given an array of positive integers nums and a
     * positive integer target, return the minimal length of a
     * subarray whose sum is greater than or equal to target.
     * If there is no such subarray, return 0 instead.
     *
     *
     * Input: target = 7, nums = [2,3,1,2,4,3]
     * Output: 2
     * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
     *
     * Input: target = 4, nums = [1,4,4]
     * Output: 1
     *
     * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
     * Output: 0
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int l = 0;
        int total = 0;
        int result = Integer.MAX_VALUE;

        for (int r = 0; r < nums.length; r++) {
            total = total + nums[r];
            while (total >= target) {
                result = Math.min(r - l + 1,result);
                total = total - nums[l];
                l++;
            }
        }

        return result == Integer.MAX_VALUE ? 0 :result;
    }
}

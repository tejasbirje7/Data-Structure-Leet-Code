public class MinimumSizeSubArray {

    public static void main(String[] args) {
        int target = 7;
        int[] nums = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(target,nums));
    }

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

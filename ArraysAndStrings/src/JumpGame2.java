public class JumpGame2 {

    /**
     * You are given a 0-indexed array of integers nums of length n.
     * You are initially positioned at nums[0].
     *
     * Each element nums[i] represents the maximum length of a forward
     * jump from index i. In other words, if you are at nums[i], you can
     * jump to any nums[i + j] where:
     *
     * 0 <= j <= nums[i] and
     * i + j < n
     * Return the minimum number of jumps to reach nums[n - 1].
     *
     * Input: nums = [2,3,1,1,4]
     * Output: 2
     * Explanation: The minimum number of jumps to reach the last index is 2.
     * Jump 1 step from index 0 to index 1, then 3 steps to the last index.
     *
     * Input: nums = [2,3,0,1,4]
     * Output: 2
     */
    public static void main(String[] args) {
        int[] input = {2,3,0,1,4};
        System.out.println(jump(input));
    }

    public static int jump(int[] nums) {
        int hops = 0;
        int left = 0;
        int right = 0;

        while (right < nums.length - 1) {
            int farthest = 0;
            for (int i = left; i < right + 1; i++) {
                farthest = Math.max(farthest,i+nums[i]);
            }
            left = right + 1;
            right = farthest;
            hops++;
        }
        return hops;
    }
}

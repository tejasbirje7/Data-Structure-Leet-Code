public class JumpGame2 {

    public static void main(String[] args) {
//        int[] input = {3,2,1,0,4};
        int[] input = {2,3,1,1,4};
//        int[] input = {2,0};
        System.out.println(jump(input));
    }

    /**
     * Input: nums = [2,3,1,1,4]
     * Output: 2
     * Explanation: The minimum number of jumps to reach the last index is 2.
     * Jump 1 step from index 0 to 1, then 3 steps to the last index.
     */
    public static int jump(int[] nums) {
        int hops = 0;
        int left = 0;
        int right = 0;

        while (right < nums.length - 1) {
            int farthest = 0;
            for (int i = left; i < right+1; i++) {
                if(i + nums[i] > farthest) {
                    farthest = i + nums[i];
                }
            }
            left = right + 1;
            right = farthest;
            hops++;
        }
        return hops;
    }
}

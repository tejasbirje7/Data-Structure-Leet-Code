public class JumpGame {

    public static void main(String[] args) {
//        int[] input = {3,2,1,0,4};
        int[] input = {2,3,1,1,4};
//        int[] input = {2,0};
        System.out.println(canJump(input));
    }

    /**
     * Input: nums = [2,3,1,1,4]
     * Output: true
     * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     */
    public static boolean canJump(int[] input) {
        int goal = input.length - 1;
        for (int i = input.length - 1; i >= 0; i--) {
            if (i + input[i] >= goal) {
                goal = i;
            }
        }
        return goal == 0;
    }

}

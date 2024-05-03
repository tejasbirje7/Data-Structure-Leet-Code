import java.util.Arrays;

public class ProductExceptSelf {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }

    /**
     * Input: nums = [1,2,3,4]
     * Output: [24,12,8,6]
     */
    public static int[] productExceptSelf(int[] nums) {
        System.out.println("Before Array : "+Arrays.toString(nums));
        int prefix = 1;
        int[] output = new int[nums.length];
        for (int i=0; i < nums.length; i++) {
            output[i] = prefix;
            prefix = prefix * nums[i];
        }
        int postFix = 1;
        for (int i=nums.length-1; i >= 0; i--) {
            output[i] = postFix * output[i];
            postFix = postFix * nums[i];
        }
        return output;
    }
}

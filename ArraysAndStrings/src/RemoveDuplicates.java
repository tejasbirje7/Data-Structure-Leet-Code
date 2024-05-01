import java.util.Arrays;

public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));
    }

    /**
     * Input: nums = [1,1,2]
     * Output: 2, nums = [1,2,_]
     * Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
     * It does not matter what you leave beyond the returned k (hence they are underscores).
     */
    public static int removeDuplicates(int[] nums) {
        int k = 0;
        for(int i=0; i < nums.length; i++) {
            if(nums[k] != nums[i]) {
                nums[++k] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return k+1;
    }
}

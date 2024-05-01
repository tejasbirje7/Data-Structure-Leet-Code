import java.util.Arrays;

public class RemoveElement {
    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        System.out.println(removeElement(nums, val));
    }

    /**
     * Input: nums = [3,2,2,3], val = 3
     * Output: 2, nums = [2,2,_,_]
     * Explanation: Your function should return k = 2, with the first two elements of nums being 2.
     * It does not matter what you leave beyond the returned k (hence they are underscores).
     */
    public static int removeElement(int[] nums, int val) {

        int nonValPointer = 0;

        for (int i=0; i < nums.length; i++) {
            if(nums[i] != val) {
                nums[nonValPointer] = nums[i];
                nonValPointer++;
            }
        }

        System.out.println(Arrays.toString(nums));
        return nonValPointer;


    }
}

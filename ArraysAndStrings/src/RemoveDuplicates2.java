import java.util.Arrays;

public class RemoveDuplicates2 {

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicates(nums));
    }

    /**
     * Each unique element must appear at most twice
     * Input: nums = [1,1,1,2,2,3]
     * Output: 5, nums = [1,1,2,2,3,_]
     * Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
     * It does not matter what you leave beyond the returned k (hence they are underscores).
     */
    public static int removeDuplicates(int[] nums) {

        int i=2;
        int e=2;

        while (i < nums.length) {
            if(nums[i] != nums[e-2]) {
                nums[e] = nums[i];
                e++;
            }
            i++;
        }
        System.out.println(Arrays.toString(nums));
        return e;
    }


}

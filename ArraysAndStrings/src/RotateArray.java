
public class RotateArray {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int val = 3;
        rotate(nums, val);
    }

    /**
     * Input: nums = [1,2,3,4,5,6,7], k = 3
     * Output: [5,6,7,1,2,3,4]
     * Explanation:
     * rotate 1 steps to the right: [7,1,2,3,4,5,6]
     * rotate 2 steps to the right: [6,7,1,2,3,4,5]
     * rotate 3 steps to the right: [5,6,7,1,2,3,4]
     */
    public static void rotate(int[] nums, int k) {
        k = k % nums.length; // Let's say k=10 which is greater than array size, then rotating it nums.length time will give same array
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }

    public static void reverse(int[] nums , int from, int to) {
        while (to > from) {
            int temp = nums[to];
            nums[to] = nums[from];
            nums[from] = temp;
            from++;to--;
        }
    }

}

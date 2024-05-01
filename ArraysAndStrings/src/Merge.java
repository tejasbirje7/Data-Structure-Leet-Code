
import java.util.Arrays;
public class Merge {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        int[] m = {1,2,3,0,0,0};
        int[] n = {2,5,6};
        merge(m,3,n,3);
    }

    /**
     * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * Output: [1,2,2,3,5,6]
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int last = m + n - 1;

        while (m > 0 && n > 0) {
            if(nums1[m-1] > nums2[n-1]) {
                nums1[last] = nums1[m-1];
                m--;
            } else {
                nums1[last] = nums2[n-1];
                n--;
            }
            last--;
        }

        while (n > 0) {
            nums1[last] = nums2[n];
            n--;
            last--;
        }
        System.out.println(Arrays.toString(nums1));
    }
}
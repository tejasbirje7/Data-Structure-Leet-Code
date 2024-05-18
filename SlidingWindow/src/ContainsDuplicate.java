import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    /**
     * Given an integer array nums and an integer k,
     * return true if there are two distinct indices i and j
     * in the array such that nums[i] == nums[j] and abs(i - j) <= k.
     *
     * Input: nums = [1,2,3,1], k = 3
     * Output: true
     *
     * Input: nums = [1,0,1,1], k = 1
     * Output: true
     *
     * Input: nums = [1,2,3,1,2,3], k = 2
     * Output: false
     */
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        int k = 3;
        System.out.println(containsNearbyDuplicate(nums,k));
    }
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        int l = 0;
        Set<Integer> window = new HashSet<>();
        for (int r=0; r<nums.length; r++) {
            if(r - l > k) {
                window.remove(nums[l]);
                l++;
            }
            if(window.contains(nums[r]))
                return true;
            window.add(nums[r]);
        }
        return false;
    }
}

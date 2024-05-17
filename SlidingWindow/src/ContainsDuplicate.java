import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

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

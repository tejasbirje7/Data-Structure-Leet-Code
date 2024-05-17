import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    /**
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
     *
     * Input: nums = [3,2,4], target = 6
     * Output: [1,2]
     *
     * Input: nums = [3,3], target = 6
     * Output: [0,1]
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> trackMap = new HashMap<>();
        int[] result = new int[2];
        for (int i=0; i<nums.length; i++) {
            int difference = target - nums[i];
            if(trackMap.containsKey(difference)) {
                result[0] = trackMap.get(difference);
                result[1] = i;
                return result;
            } else {
                trackMap.put(nums[i],i);
            }
        }
        return result;

    }


}

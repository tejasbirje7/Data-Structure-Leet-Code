import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(permute(nums));
    }

    /**
     * Input: nums = [1,2,3]
     * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 1) {
            result.add(new ArrayList<>(nums[0]));
            return result;
        }
        permute(result,new ArrayList<>(),nums);
        return result;

    }

    public static void permute(List<List<Integer>> result,
                               List<Integer> temp,
                               int[] nums) {

        if(temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
        } else {
            for (int num : nums) {
                if (temp.contains(num)) continue; // element already exists so skip
                temp.add(num);
                permute(result, temp, nums);
                temp.remove(temp.size() - 1);
            }
        }
    }
}

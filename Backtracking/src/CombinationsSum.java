import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationsSum {

    /**
     * Input: candidates = [2,3,6,7], target = 7
     * Output: [[2,2,3],[7]]
     * Explanation:
     * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
     * 7 is a candidate, and 7 = 7.
     * These are the only two combinations.
     */
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    public void backtrack(List<List<Integer>> result,
                          List<Integer> temp,
                          int [] nums,
                          int remain,
                          int start) {
        if (remain < 0) return;
        else if (remain == 0)  result.add(new ArrayList<>(temp));
        else {
            for (int i = start; i < nums.length; i++) {
                temp.add(nums[i]);
                backtrack(result,temp,nums,remain - nums[i],i);
                temp.remove(temp.size() - 1);
            }
        }
    }
}

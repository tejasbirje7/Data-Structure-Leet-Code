import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 39. Combination Sum
 * https://leetcode.com/problems/combination-sum/
 *
 * PROBLEM
 *   - Input: an array of distinct positive integers (candidates, here nums) and a target.
 *   - Return: every unique combination of candidates that adds up to target.
 *   - The same candidate may be used any number of times.
 *   - Two combinations are the same if they use the same numbers the same number of times.
 *
 * EXAMPLE
 *   Input: candidates = [2,3,6,7], target = 7
 *   Output: [[2,2,3],[7]]
 *   Explanation:
 *   2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 *   7 is a candidate, and 7 = 7.
 *   These are the only two combinations.
 *
 * HOW TO THINK
 *   - Clue: "return all unique combinations" = try every choice, so backtracking.
 *   - Technique: build one combination (temp) and track remain = target - sum(temp).
 *   - Reuse trick: the next call starts at i (not i + 1), so the same number can be picked again.
 *   - No duplicates trick: never go back to an earlier index, so [3,2,2] can't follow [2,2,3].
 *   - Rule that stays true: temp only uses nums[start..] from here on; remain = what is still needed.
 *   - Decision: remain < 0  -> overshot, return.
 *               remain == 0 -> exact hit, add a copy of temp.
 *               remain > 0  -> for i = start..end: add nums[i], recurse with remain - nums[i] and i,
 *                              then remove nums[i].
 *   - Return: the result list.
 *
 * STEPS
 *   1. Sort nums (the code sorts but never uses the order to stop early).
 *   2. backtrack(result, [], nums, target, start = 0).
 *   3. If remain < 0 return; if remain == 0 add a copy of temp.
 *   4. Otherwise for i = start to the end: add nums[i], recurse with (remain - nums[i], i), remove it.
 *   5. Return result.
 *
 * DRY RUN   nums = [2,3], target = 6   (path is temp right after the pick; each pick is undone after its call)
 *
 *    depth | remain | i | pick | path    | remain after | result
 *   -------+--------+---+------+---------+--------------+-------------------------
 *      0   |   6    | 0 |  2   | [2]     |      4       |
 *      1   |   4    | 0 |  2   | [2,2]   |      2       |
 *      2   |   2    | 0 |  2   | [2,2,2] |      0       | == 0, add [2,2,2]
 *      2   |   2    | 1 |  3   | [2,2,3] |     -1       | < 0, return
 *      1   |   4    | 1 |  3   | [2,3]   |      1       |
 *      2   |   1    | 1 |  3   | [2,3,3] |     -2       | < 0, return
 *      0   |   6    | 1 |  3   | [3]     |      3       |
 *      1   |   3    | 1 |  3   | [3,3]   |      0       | == 0, add [3,3]
 *
 *   Returns [[2,2,2],[3,3]].
 *
 * EDGE CASES
 *   [2,3,6,7], 7   -> [[2,2,3],[7]]                 the LeetCode example
 *   [2,3,5], 8     -> [[2,2,2,2],[2,3,3],[3,5]]     one number reused many times
 *   [2], 1         -> []                            every path overshoots
 *   [1], 2         -> [[1,1]]                       one candidate used twice
 *   [7,3,2], 7     -> [[2,2,3],[7]]                 unsorted input works; note it also sorts the caller's array
 *   [3,5], 0       -> [[]]                          remain is 0 at once (LeetCode has target >= 1)
 *   [2,2], 4       -> [[2,2],[2,2],[2,2]]           repeated candidates give repeated answers
 *                                                   (LeetCode says candidates are distinct)
 *
 * COMMON MISTAKES
 *   - Recursing with i + 1: then each number can be used only once (that is LeetCode 40).
 *   - Recursing with 0 instead of i: you get the same combination in different orders.
 *   - Adding temp itself instead of a copy: every entry ends up as the same empty list.
 *   - Sorting and never using it: after sorting you can break the loop when nums[i] > remain.
 *     This code doesn't, so it explores dead paths and returns only on remain < 0.
 *
 * COMPLEXITY
 *   O(n^(target / min + 1)) time in the worst case (exponential), O(target / min) extra space
 *   for the recursion depth and temp, not counting the output.
 *
 * RELATED
 *   LeetCode 77 (Combinations, Backtracking/): pick exactly k numbers, each at most once (start at i + 1).
 *   LeetCode 46 (Permutations, Backtracking/): order matters, every call loops over all numbers.
 */
public class CombinationsSum {

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

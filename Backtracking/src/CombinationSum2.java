import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 40. Combination Sum II
 * https://leetcode.com/problems/combination-sum-ii/
 *
 * PROBLEM
 *   - Input: an array candidates (1..100 numbers, each 1..50, MAY contain duplicates) and a target (1..30).
 *   - Return: every unique combination of candidates that adds up to target.
 *   - Each candidate (each index) may be used at most once.
 *   - The answer must not contain the same combination twice.
 *
 * EXAMPLE
 *   Input: candidates = [10,1,2,7,6,1,5], target = 8
 *   Output: [[1,1,6],[1,2,5],[1,7],[2,6]]
 *
 * HOW TO THINK
 *   - Clue: "all unique combinations" + "used once" + duplicates in the input
 *     = backtracking with a start index, plus a rule to skip repeated values.
 *   - Technique: sort first, so equal values sit next to each other and the loop can stop early.
 *   - Use-once trick: recurse with i + 1 (not i like LeetCode 39).
 *   - No duplicates trick: at one level (one loop), use each value only once:
 *     skip nums[i] when i > start and nums[i] == nums[i - 1].
 *     i > start keeps the first copy, so [1,1,6] (two 1s at DIFFERENT levels) is still found.
 *   - Rule that stays true: temp uses picks from nums[0..start-1] in index order; remain = target - sum(temp).
 *   - Decision: remain == 0 -> save a copy of temp.
 *               loop i = start..n-1: repeat value at this level -> skip;
 *               nums[i] > remain -> break (sorted, so every later number is too big as well);
 *               else add nums[i], recurse with (remain - nums[i], i + 1), remove it.
 *   - Return: result.
 *
 * STEPS
 *   1. Sort candidates.
 *   2. backtrack(result, [], nums, remain = target, start = 0).
 *   3. If remain == 0: add a copy of temp, return.
 *   4. For i = start..n-1: if i > start and nums[i] == nums[i-1], continue;
 *      if nums[i] > remain, break; add, recurse with i + 1, remove.
 *   5. Return result.
 *
 * DRY RUN   candidates = [2,5,2,1,2], target = 5  ->  sorted nums = [1,2,2,2,5]
 *           (temp and remain are after the action)
 *
 *    depth | i | nums[i] | action               | temp      | remain
 *   -------+---+---------+----------------------+-----------+-------
 *      0   | 0 |    1    | pick                 | [1]       |   4
 *      1   | 1 |    2    | pick                 | [1,2]     |   2
 *      2   | 2 |    2    | pick                 | [1,2,2]   |   0
 *      3   | - |    -    | remain == 0, save    | [1,2,2]   |   0
 *      2   | 3 |    2    | same as nums[2], skip| [1,2]     |   2
 *      2   | 4 |    5    | 5 > 2, break         | [1,2]     |   2
 *      1   | 2 |    2    | same as nums[1], skip| [1]       |   4
 *      1   | 3 |    2    | same as nums[2], skip| [1]       |   4
 *      1   | 4 |    5    | 5 > 4, break         | [1]       |   4
 *      0   | 1 |    2    | pick                 | [2]       |   3
 *      1   | 2 |    2    | pick                 | [2,2]     |   1
 *      2   | 3 |    2    | 2 > 1, break         | [2,2]     |   1
 *      1   | 3 |    2    | same as nums[2], skip| [2]       |   3
 *      1   | 4 |    5    | 5 > 3, break         | [2]       |   3
 *      0   | 2 |    2    | same as nums[1], skip| []        |   5
 *      0   | 3 |    2    | same as nums[2], skip| []        |   5
 *      0   | 4 |    5    | pick                 | [5]       |   0
 *      1   | - |    -    | remain == 0, save    | [5]       |   0
 *
 *   Returns [[1,2,2],[5]].
 *
 * EDGE CASES
 *   [10,1,2,7,6,1,5], 8  -> [[1,1,6],[1,2,5],[1,7],[2,6]]   the LeetCode example (two 1s in one answer)
 *   [1,1,1], 2           -> [[1,1]]                          only one copy despite 3 ways to pick two 1s
 *   [2], 1               -> []                               first number already too big, break
 *   [3], 3               -> [[3]]                            one number equal to target
 *   [5,5], 5             -> [[5]]                            second 5 skipped at the top level
 *
 * COMMON MISTAKES
 *   - Skipping with i > 0 instead of i > start: that also drops the second 1 at the next level,
 *     so [1,1,6] is lost.
 *   - Forgetting to sort: equal values aren't next to each other and duplicates slip through.
 *   - Using a Set of lists to remove duplicates afterwards: works but wastes time exploring repeats.
 *   - Recursing with i instead of i + 1: reuses the same candidate (that is LeetCode 39).
 *
 * COMPLEXITY
 *   O(2^n * n) time in the worst case (each index in or out, O(n) to copy a hit), plus O(n log n) to sort.
 *   O(n) extra space for the recursion and temp, not counting the output.
 *
 * RELATED
 *   LeetCode 39 (CombinationsSum, Backtracking/): distinct candidates, each reusable; recurses with i.
 *   LeetCode 78 (Subsets, Backtracking/): same start-index loop, saves at every call.
 *   LeetCode 47 (UniquePermutations, FAQ/): the same "skip a repeat at one level" idea for permutations.
 */
public class CombinationSum2 {

    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums, int remain, int start) {
        if (remain == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            if (nums[i] > remain) break;
            temp.add(nums[i]);
            backtrack(result, temp, nums, remain - nums[i], i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}

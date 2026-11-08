import java.util.*;

/**
 * LeetCode 47. Permutations II
 * https://leetcode.com/problems/permutations-ii/
 *
 * PROBLEM
 *   - Input: an array nums that may contain duplicates.
 *   - Return: all unique permutations, in any order.
 *   - Constraints: 1 <= n <= 8; equal values must not produce the same permutation twice.
 *
 * EXAMPLE
 *   Input: nums = [1,1,2]
 *   Output: [[1,1,2],[1,2,1],[2,1,1]]
 *
 * HOW TO THINK
 *   - Clue: "all permutations" = backtracking; "contains duplicates" + "unique" = prune repeats.
 *   - Technique: backtracking with boolean[] used (by index, so equal values can both be used)
 *     plus a Set seen per call, holding the values already placed at this position.
 *   - Rule that stays true: temp holds the chosen values; used[i] is true exactly for the
 *     indexes in temp; seen holds the values already tried at the current depth.
 *   - Decision: temp is full -> add a copy to res, return.
 *               used[i] -> skip (index already in temp).
 *               nums[i] in seen -> skip (same value at the same position gives the same subtree).
 *               otherwise -> add to seen, mark used, add to temp, recurse, then undo both.
 *   - Return: res after the first call; every permutation appears exactly once.
 *   - Why no sorting: sorting groups duplicates so you can skip by comparing neighbours.
 *     The seen set finds duplicates at each depth instead, so the input order can stay as is.
 *     E.g. when building permutations that start with 1, another 1 is never tried in the first slot.
 *
 * STEPS
 *   1. permuteUnique: res = [], used = new boolean[n]; call backtrack(res, [], nums, used).
 *   2. backtrack: if temp.size() == n, add a copy of temp to res and return.
 *   3. seen = new empty set. Loop i from 0 to n - 1:
 *   4.   skip if used[i]; skip if seen contains nums[i]; else add nums[i] to seen.
 *   5.   used[i] = true, temp.add(nums[i]); recurse; used[i] = false, remove the last element.
 *
 * DRY RUN   nums = [1,1,2]   (rows in call order; temp = the list at that depth, after any undo;
 *                             seen = that call's set after the row)
 *
 *    depth | temp    | action                               | seen   | added to res
 *   -------+---------+--------------------------------------+--------+-----------------
 *      0   | []      | i=0 choose 1                         | {1}    |
 *      1   | [1]     | i=0 used, i=1 choose 1               | {1}    |
 *      2   | [1,1]   | i=0 used, i=1 used, i=2 choose 2     | {2}    |
 *      3   | [1,1,2] | full, add                            | -      | [1,1,2]
 *      1   | [1]     | undo i=1, i=2 choose 2               | {1,2}  |
 *      2   | [1,2]   | i=0 used, i=1 choose 1               | {1}    |
 *      3   | [1,2,1] | full, add                            | -      | [1,2,1]
 *      2   | [1,2]   | undo i=1, i=2 used, loop ends        | {1}    |
 *      1   | [1]     | undo i=2, loop ends                  | {1,2}  |
 *      0   | []      | undo i=0, i=1 value 1 in seen, skip  | {1}    |
 *      0   | []      | i=2 choose 2                         | {1,2}  |
 *      1   | [2]     | i=0 choose 1                         | {1}    |
 *      2   | [2,1]   | i=0 used, i=1 choose 1               | {1}    |
 *      3   | [2,1,1] | full, add                            | -      | [2,1,1]
 *      2   | [2,1]   | undo i=1, i=2 used, loop ends        | {1}    |
 *      1   | [2]     | undo i=0, i=1 value 1 in seen, skip, | {1}    |
 *          |         | i=2 used, loop ends                  |        |
 *      0   | []      | undo i=2, loop ends                  | {1,2}  |
 *
 *   Returns [[1,1,2],[1,2,1],[2,1,1]]. Final temp: [], all used[] false.
 *
 * EDGE CASES
 *   [1,2,3]   -> all 6 orderings   no duplicates, seen never skips
 *   [1,1,1]   -> [[1,1,1]]         every depth tries the value 1 once
 *   [1]       -> [[1]]             one choice
 *   [2,2,1,1] -> 6 permutations    4! / (2! * 2!) = 6
 *   []        -> [[]]              size 0 == length 0 at once; LeetCode guarantees n >= 1
 *
 * COMMON MISTAKES
 *   - Using one seen set for the whole search: it must be new in every call (one per depth),
 *     otherwise valid values are skipped at deeper levels.
 *   - Checking "contains" on temp by value (as in LeetCode 46): with duplicates, the second 1
 *     could never be picked. Track used indexes instead.
 *   - Generating all n! orderings and removing duplicates with a Set afterwards: correct but
 *     wastes time on repeated subtrees.
 *   - Forgetting to reset used[i] = false on the way back.
 *
 * COMPLEXITY
 *   O(n * n!) time in the worst case (all distinct): n! results, each copied in O(n).
 *   O(n^2) extra space: recursion depth n, each level has its own seen set of up to n values.
 *
 * RELATED
 *   LeetCode 46 (PossiblePermutations, FAQ/): values are distinct, so "contains" on the path
 *     is enough and no seen set is needed.
 *   LeetCode 46 (Permutations, Backtracking/): the same distinct-values version.
 */
class UniquePermutations {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(res, new ArrayList<>(), nums, used);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> temp, int[] nums, boolean[] used) {

        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        Set<Integer> seen = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            if (seen.contains(nums[i])) continue;
            seen.add(nums[i]);

            used[i] = true;
            temp.add(nums[i]);

            backtrack(res, temp, nums, used);

            used[i] = false;
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        UniquePermutations sol = new UniquePermutations();

        int[] nums1 = {1, 1, 2};
        System.out.println(sol.permuteUnique(nums1)); // [[1, 1, 2], [1, 2, 1], [2, 1, 1]]

        int[] nums2 = {1, 2, 3};
        System.out.println(sol.permuteUnique(nums2)); // [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
    }
}

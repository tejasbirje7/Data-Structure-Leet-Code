import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 46. Permutations
 * https://leetcode.com/problems/permutations/
 *
 * PROBLEM
 *   - Input: an array nums of distinct integers (1 to 6 of them, each in -10..10).
 *   - Return: every possible ordering (permutation) of nums, in any order.
 *
 * EXAMPLE
 *   Input: nums = [1,2,3]
 *   Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * HOW TO THINK
 *   - Clue: "return all possible permutations" = try every choice, so backtracking.
 *   - Technique: fill the permutation (temp) one slot at a time; each slot can take any unused number.
 *   - Order matters here, so every level loops over ALL of nums (no start index like in Combinations).
 *   - Rule that stays true: temp holds distinct numbers; the ones not in temp are still free.
 *   - Decision: temp.size() == nums.length -> full: add a copy to result.
 *               else -> for each num: if temp already contains it, skip (already used);
 *                       otherwise add it, recurse, remove it.
 *   - Return: result.
 *   - One number needs no special case: the first pick fills temp, so [x] gives [[x]].
 *
 * STEPS
 *   1. Call permute(result, [], nums).
 *   2. If temp is full, add a copy of temp to result.
 *   3. Else, for each num not already in temp: add it, recurse, remove the last element.
 *   4. Return result.
 *
 * DRY RUN   nums = [1,2,3]   (path is temp right after the pick; each pick is undone after its call)
 *
 *    depth | skipped (used) | pick | path    | result
 *   -------+----------------+------+---------+-------------------------------
 *      0   |                |  1   | [1]     |
 *      1   | 1              |  2   | [1,2]   |
 *      2   | 1, 2           |  3   | [1,2,3] | full, add [1,2,3]
 *      1   |                |  3   | [1,3]   |
 *      2   | 1              |  2   | [1,3,2] | full, add [1,3,2]; then skip 3
 *      0   |                |  2   | [2]     |
 *      1   |                |  1   | [2,1]   |
 *      2   | 1, 2           |  3   | [2,1,3] | full, add [2,1,3]
 *      1   | 2              |  3   | [2,3]   |
 *      2   |                |  1   | [2,3,1] | full, add [2,3,1]; then skip 2, 3
 *      0   |                |  3   | [3]     |
 *      1   |                |  1   | [3,1]   |
 *      2   | 1              |  2   | [3,1,2] | full, add [3,1,2]; then skip 3
 *      1   |                |  2   | [3,2]   |
 *      2   |                |  1   | [3,2,1] | full, add [3,2,1]; then skip 2, 3
 *      1   | 3              |      | [3]     | loop ends, nothing left to pick
 *
 *   Returns [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]] (3! = 6).
 *
 * EDGE CASES
 *   [0,1]    -> [[0,1],[1,0]]   two numbers, two orders
 *   [1]      -> [[1]]           smallest input: pick 1, temp is full, add [1]
 *   [0]      -> [[0]]           zero is a normal value
 *   [-1]     -> [[-1]]          negative values work too (LeetCode allows -10..10)
 *   [1,1]    -> []              contains() treats the second 1 as used, nothing is ever full
 *                               (LeetCode says nums are distinct)
 *   []       -> [[]]            temp is full at once (LeetCode has at least one number)
 *
 * COMMON MISTAKES
 *   - Special-casing one number with new ArrayList<>(n): that sets the capacity, it doesn't add n
 *     ([1] gives [[]], [-1] throws). Use new ArrayList<>(List.of(n)), or skip the special case.
 *   - Using a start index like Combinations: you'd only get [1,2,3]. Permutations loop over all numbers.
 *   - Adding temp itself instead of a copy: every entry is the same list, empty at the end.
 *   - temp.contains is O(n) and breaks on duplicates; a boolean[] used array is O(1) and works with
 *     duplicates too (see UniquePermutations).
 *
 * COMPLEXITY
 *   O(n! * n^2) time as written (n! leaves, each level loops over n numbers with an O(n) contains),
 *   O(n) extra space for the recursion and temp, not counting the output.
 *
 * RELATED
 *   LeetCode 46 (PossiblePermutations, FAQ/): the same problem and the same contains() approach.
 *   LeetCode 47 (UniquePermutations, FAQ/): nums may have duplicates; uses a used[] array and
 *     a per-level seen set to skip repeats.
 *   LeetCode 77 (Combinations, Backtracking/): order doesn't matter, so each call starts after the last pick.
 */
public class Permutations {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
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
                if (temp.contains(num)) continue;
                temp.add(num);
                permute(result, temp, nums);
                temp.remove(temp.size() - 1);
            }
        }
    }
}

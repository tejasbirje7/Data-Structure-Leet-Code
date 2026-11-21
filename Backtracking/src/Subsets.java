import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 78. Subsets
 * https://leetcode.com/problems/subsets/
 *
 * PROBLEM
 *   - Input: an array nums of distinct integers (1 to 10 of them, each in -10..10).
 *   - Return: every subset (the power set), with no duplicate subsets, in any order.
 *   - n numbers give 2^n subsets, including the empty one.
 *
 * EXAMPLE
 *   Input: nums = [1,2,3]
 *   Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * HOW TO THINK
 *   - Clue: "return all possible subsets" = try every choice, so backtracking.
 *   - Technique: grow one subset (temp) left to right; every call adds a copy of temp,
 *     because every partial pick is itself a valid subset.
 *   - No duplicates trick: only pick numbers after the last one picked (start index),
 *     so [2,1] never appears after [1,2].
 *   - Rule that stays true: temp holds picks from nums[0..start-1] in index order;
 *     the call may only add nums[start..].
 *   - Decision: add a copy of temp first (no base case needed), then for i = start..n-1:
 *     add nums[i], recurse with i + 1, remove nums[i].
 *   - Return: result. When start == n the loop is simply empty, so the recursion stops on its own.
 *
 * STEPS
 *   1. backtrack(result, [], nums, start = 0).
 *   2. Add a copy of temp to result.
 *   3. For i = start to n - 1: add nums[i], call backtrack with i + 1, remove the last element.
 *   4. Return result.
 *
 * DRY RUN   nums = [1,2,3]   (one row per call, in call order)
 *
 *    depth | start | temp on entry | added to result | picks tried next (i)
 *   -------+-------+---------------+-----------------+---------------------
 *      0   |   0   | []            | []              | 1, 2, 3
 *      1   |   1   | [1]           | [1]             | 2, 3
 *      2   |   2   | [1,2]         | [1,2]           | 3
 *      3   |   3   | [1,2,3]       | [1,2,3]         | none (start == n)
 *      2   |   3   | [1,3]         | [1,3]           | none
 *      1   |   2   | [2]           | [2]             | 3
 *      2   |   3   | [2,3]         | [2,3]           | none
 *      1   |   3   | [3]           | [3]             | none
 *
 *   Returns [[],[1],[1,2],[1,2,3],[1,3],[2],[2,3],[3]] (2^3 = 8 subsets).
 *
 * EDGE CASES
 *   [0]        -> [[],[0]]            one number: empty set and itself
 *   [-1,5]     -> [[],[-1],[-1,5],[5]]  negatives are normal values
 *   []         -> [[]]                only the empty set (LeetCode has at least one number)
 *   [1,1]      -> [[],[1],[1,1],[1]]  duplicate subsets appear (LeetCode says distinct;
 *                                     LeetCode 90 sorts and skips repeats)
 *
 * COMMON MISTAKES
 *   - Adding temp itself instead of a copy: every entry is the same list, empty at the end.
 *   - Recursing with start + 1 instead of i + 1: picks can go backwards and you get repeats.
 *   - Adding only when start == n: you get only the full set; every call is a subset here.
 *
 * COMPLEXITY
 *   O(n * 2^n) time (2^n subsets, each copied in O(n)), O(n) extra space for the recursion
 *   and temp, not counting the output.
 *
 * RELATED
 *   LeetCode 77 (Combinations, Backtracking/): same start-index loop, but only saves at size k.
 *   LeetCode 39 (CombinationsSum, Backtracking/): same loop, recurses with i to allow reuse, saves on sum.
 *   LeetCode 40 (CombinationSum2, Backtracking/): same loop on sorted input, skips repeats at a level.
 */
public class Subsets {

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums, int start) {
        result.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            backtrack(result, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}

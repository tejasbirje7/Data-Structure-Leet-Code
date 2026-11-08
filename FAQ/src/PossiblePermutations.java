import java.util.*;

/**
 * LeetCode 46. Permutations
 * https://leetcode.com/problems/permutations/
 *
 * PROBLEM
 *   - Input: an array nums of distinct integers.
 *   - Return: every possible ordering (permutation) of nums, in any order.
 *   - Constraints: 1 <= n <= 6, all values are distinct, so there are n! answers.
 *
 * EXAMPLE
 *   Input: nums = [1,2,3]
 *   Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * HOW TO THINK
 *   - Clue: "all possible" orderings = try every choice at every position.
 *     That is a tree of choices, which means backtracking.
 *   - Technique: build one permutation in addHere, one number at a time.
 *     Choose a number, recurse to fill the next slot, then un-choose it and try the next number.
 *   - Rule that stays true: addHere holds distinct numbers from nums, in the order they were chosen.
 *   - Decision: addHere.size() == n -> it's complete: add a copy to res, return.
 *               otherwise, for each nums[i]: already in addHere -> skip it (each number once);
 *               not in addHere -> add it, recurse, remove it again.
 *   - Return: res after the first call finishes; it holds all n! permutations.
 *   - Why "contains" works: the values are distinct, so a value already in addHere means
 *     that index was already used.
 *
 * STEPS
 *   1. permute: res = empty list; call backTrack(res, [], nums); return res.
 *   2. backTrack: if addHere.size() == nums.length, add a copy of addHere to res and return.
 *   3. Loop i from 0 to n - 1; if addHere contains nums[i], skip it.
 *   4. Otherwise: add nums[i] (choose), call backTrack (explore), remove the last element (un-choose).
 *
 * DRY RUN   nums = [1,2,3]   (rows in call order; addHere = the list at that depth, after any undo;
 *                             each "choose" starts a deeper call, "undo" runs after it returns)
 *
 *    depth | addHere | action                       | res size
 *   -------+---------+------------------------------+----------
 *      0   | []      | choose 1                     |    0
 *      1   | [1]     | skip 1, choose 2             |    0
 *      2   | [1,2]   | skip 1, skip 2, choose 3     |    0
 *      3   | [1,2,3] | full, add [1,2,3]            |    1
 *      1   | [1]     | undo 2, choose 3             |    1
 *      2   | [1,3]   | skip 1, choose 2             |    1
 *      3   | [1,3,2] | full, add [1,3,2]            |    2
 *      2   | [1,3]   | undo 2, skip 3, loop ends    |    2
 *      1   | [1]     | undo 3, loop ends            |    2
 *      0   | []      | undo 1, choose 2             |    2
 *      1   | [2]     | choose 1                     |    2
 *      2   | [2,1]   | skip 1, skip 2, choose 3     |    2
 *      3   | [2,1,3] | full, add [2,1,3]            |    3
 *      1   | [2]     | undo 1, skip 2, choose 3     |    3
 *      2   | [2,3]   | choose 1                     |    3
 *      3   | [2,3,1] | full, add [2,3,1]            |    4
 *      2   | [2,3]   | undo 1, skip 2, skip 3, ends |    4
 *      1   | [2]     | undo 3, loop ends            |    4
 *      0   | []      | undo 2, choose 3             |    4
 *      1   | [3]     | choose 1                     |    4
 *      2   | [3,1]   | skip 1, choose 2             |    4
 *      3   | [3,1,2] | full, add [3,1,2]            |    5
 *      2   | [3,1]   | undo 2, skip 3, loop ends    |    5
 *      1   | [3]     | undo 1, choose 2             |    5
 *      2   | [3,2]   | choose 1                     |    5
 *      3   | [3,2,1] | full, add [3,2,1]            |    6
 *      2   | [3,2]   | undo 1, skip 2, skip 3, ends |    6
 *      1   | [3]     | undo 2, skip 3, loop ends    |    6
 *      0   | []      | undo 3, loop ends            |    6
 *
 *   Returns [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]. Final addHere: [].
 *
 * EDGE CASES
 *   [1]       -> [[1]]             one choice, the first deeper call is already full
 *   [0,1]     -> [[0,1],[1,0]]     2! = 2 orderings
 *   [-1,5]    -> [[-1,5],[5,-1]]   negative values work the same
 *   [1,1]     -> []                duplicates break "contains": the second 1 is always skipped,
 *                                  so addHere never fills; LeetCode guarantees distinct values
 *   []        -> [[]]              size 0 == length 0 at once; LeetCode guarantees n >= 1
 *
 * COMMON MISTAKES
 *   - res.add(addHere) without a copy: every entry points to the same list, which ends empty.
 *     Add new ArrayList<>(addHere).
 *   - Forgetting the un-choose step: addHere keeps growing and later branches are wrong.
 *     Remove the last element right after the recursive call.
 *   - Using "contains" when values can repeat: use a boolean[] used by index instead (see LeetCode 47).
 *
 * COMPLEXITY
 *   O(n^2 * n!) time: about n! full lists, each copied in O(n), and every loop step runs
 *   an O(n) contains check. O(n) extra space for the recursion and addHere (output not counted).
 *
 * RELATED
 *   LeetCode 46 (Permutations, Backtracking/): same problem, also in Backtracking/.
 *   LeetCode 47 (UniquePermutations, FAQ/): nums may have duplicates. Track used indexes
 *     with boolean[] and skip a value already tried at the same depth.
 *   LeetCode 77 (Combinations, Backtracking/): order doesn't matter, so each call only picks
 *     numbers after the last one chosen.
 */
public class PossiblePermutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTrack(res, new ArrayList<>(), nums);
        return res;
    }

    public void backTrack(List<List<Integer>> res, List<Integer> addHere, int[] nums) {
        if (addHere.size() == nums.length) {
            res.add(new ArrayList<>(addHere));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (addHere.contains(nums[i]))
                continue;

            addHere.add(nums[i]);

            backTrack(res, addHere, nums);

            addHere.remove(addHere.size() - 1);
        }
    }

    public static void main(String[] args) {
        PossiblePermutations sol = new PossiblePermutations();

        int[] nums1 = {1, 2, 3};
        System.out.println("Permutations of [1, 2, 3]: " + sol.permute(nums1));

        int[] nums2 = {0, 1};
        System.out.println("Permutations of [0, 1]: " + sol.permute(nums2));

        int[] nums3 = {1};
        System.out.println("Permutations of [1]: " + sol.permute(nums3));

        /**
         * Permutations of [1, 2, 3]: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
         * Permutations of [0, 1]: [[0, 1], [1, 0]]
         * Permutations of [1]: [[1]]
         */
    }
}

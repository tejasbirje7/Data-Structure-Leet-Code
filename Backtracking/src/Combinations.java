import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 77. Combinations
 * https://leetcode.com/problems/combinations/
 *
 * PROBLEM
 *   - Input: two integers n and k.
 *   - Return: every way to choose k numbers from 1..n.
 *   - Order inside a combination doesn't matter: [1,2] and [2,1] are the same, list it once.
 *   - Constraints: 1 <= n <= 20, 1 <= k <= n.
 *
 * EXAMPLE
 *   Input: n = 4, k = 2
 *   Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 *   Explanation: There are 4 choose 2 = 6 total combinations.
 *   Note that combinations are unordered, i.e., [1,2] and [2,1]
 *   are considered to be the same combination.
 *
 * HOW TO THINK
 *   - Clue: "return all possible combinations" = generate every choice, so backtracking.
 *   - Technique: build one combination (comb) number by number; after each try, undo it.
 *   - No duplicates trick: only pick numbers bigger than the last one picked.
 *     The next call starts at i + 1, so [2,1] can never be built after [1,2].
 *   - Rule that stays true: comb is in increasing order; k = how many numbers are still needed.
 *   - Decision: k == 0 -> comb is full: add a copy to combs and return.
 *               else   -> for i = start..n: add i, recurse with (i + 1, k - 1), remove i.
 *   - Return: combs, filled in lexicographic order.
 *
 * STEPS
 *   1. combs = empty list; call combine(combs, [], start = 1, n, k).
 *   2. If k == 0: add a copy of comb to combs and return.
 *   3. For i = start to n: comb.add(i), recurse with start = i + 1 and k - 1.
 *   4. After the call returns, remove the last number (backtrack).
 *   5. Return combs.
 *
 * DRY RUN   n = 4, k = 2   (path is comb right after the pick; every pick is undone after its call)
 *
 *    depth | start | pick i | path  | k left | result
 *   -------+-------+--------+-------+--------+---------------------------------
 *      0   |   1   |   1    | [1]   |   1    |
 *      1   |   2   |   2    | [1,2] |   0    | add [1,2], undo 2
 *      1   |   2   |   3    | [1,3] |   0    | add [1,3], undo 3
 *      1   |   2   |   4    | [1,4] |   0    | add [1,4], undo 4; loop ends, undo 1
 *      0   |   1   |   2    | [2]   |   1    |
 *      1   |   3   |   3    | [2,3] |   0    | add [2,3], undo 3
 *      1   |   3   |   4    | [2,4] |   0    | add [2,4], undo 4; loop ends, undo 2
 *      0   |   1   |   3    | [3]   |   1    |
 *      1   |   4   |   4    | [3,4] |   0    | add [3,4], undo 4; loop ends, undo 3
 *      0   |   1   |   4    | [4]   |   1    | next call has start = 5 > n, nothing added; undo 4
 *
 *   Returns [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]] (6 = 4 choose 2).
 *
 * EDGE CASES
 *   n = 1, k = 1   -> [[1]]                   one pick fills it
 *   n = 3, k = 3   -> [[1,2,3]]               only one way to take everything
 *   n = 5, k = 1   -> [[1],[2],[3],[4],[5]]   every single number
 *   n = 20, k = 10 -> 184756 combinations     largest output allowed (20 choose 10)
 *   n = 3, k = 0   -> [[]]                    k == 0 at once adds the empty list (LeetCode has k >= 1)
 *   n = 2, k = 3   -> []                      runs out of numbers before k reaches 0 (LeetCode has k <= n)
 *
 * COMMON MISTAKES
 *   - Starting the loop at 1 (or at start) in the recursive call instead of i + 1:
 *     you get [1,1] or both [1,2] and [2,1]. Recurse with i + 1.
 *   - Adding comb itself instead of new ArrayList<>(comb): every entry is the same list,
 *     which is empty at the end. Always add a copy.
 *   - Forgetting to remove the last number after the call: comb keeps growing.
 *   - Optional speed-up, not in this code: stop the loop at n - k + 1, since later starts can't
 *     find enough numbers (the [4] row above is wasted work).
 *
 * COMPLEXITY
 *   O(C(n, k) * k) time (each combination costs k to copy), O(k) extra space for the recursion
 *   and comb, not counting the output.
 *
 * RELATED
 *   LeetCode 39 (CombinationsSum, Backtracking/): numbers may repeat and the stop rule is a target sum,
 *     so the next call starts at i, not i + 1.
 *   LeetCode 46 (Permutations, Backtracking/): order matters, so every call loops over all numbers
 *     and skips the ones already used.
 *   LeetCode 17 (LetterCombinations, Backtracking/): one choice per digit instead of k picks from 1..n.
 */
public class Combinations {

    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        System.out.println(combine(4,2));
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combs = new ArrayList<>();
        combine(combs,new ArrayList<>(),1,n,k);
        return combs;
    }

    public static void combine(List<List<Integer>> combs,
                               List<Integer> comb,
                               int start,
                               int n,
                               int k) {

        if ( k == 0) {
            combs.add(new ArrayList<>(comb));
            return;
        }

        for (int i=start; i<=n; i++) {
            comb.add(i);
            combine(combs,comb,i+1,n,k-1);
            comb.remove(comb.size() - 1);
        }
    }
}

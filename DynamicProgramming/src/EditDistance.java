/**
 * LeetCode 72. Edit Distance
 * https://leetcode.com/problems/edit-distance/
 *
 * PROBLEM
 *   - Input: two strings word1 and word2 (lowercase letters, length 0 to 500).
 *   - Return: the fewest operations to turn word1 into word2.
 *   - Allowed operations: insert a char, delete a char, replace a char.
 *
 * EXAMPLE
 *   Input: word1 = "horse", word2 = "ros"
 *   Output: 3
 *   Explanation:
 *   horse -> rorse (replace 'h' with 'r')
 *   rorse -> rose (remove 'r')
 *   rose -> ros (remove 'e')
 *
 * HOW TO THINK
 *   - Clue: "minimum number of operations" between two strings -> DP on two prefixes.
 *   - Technique: dp[i][j] = fewest edits to turn word1[0..i) into word2[0..j).
 *     Only the last chars word1[i-1] and word2[j-1] need a decision; the rest is a smaller cell.
 *   - Rule that stays true: row 0 is j (insert j chars), column 0 is i (delete i chars).
 *     Every cell only reads its left, top and top-left neighbours, which are already final.
 *   - Decision: last chars equal -> dp[i][j] = dp[i-1][j-1] (free, nothing to do).
 *               otherwise        -> 1 + min(dp[i-1][j-1] replace, dp[i-1][j] delete, dp[i][j-1] insert).
 *   - Return: dp[m][n]. The table is (m + 1) x (n + 1) so it has room for empty prefixes.
 *
 * STEPS
 *   1. dp = int[m + 1][n + 1]; dp[i][0] = i; dp[0][j] = j.
 *   2. Loop i from 1 to m, j from 1 to n.
 *   3. If word1[i-1] == word2[j-1], copy dp[i-1][j-1]; else 1 + min of the three neighbours.
 *   4. Return dp[m][n].
 *
 * DRY RUN   input = word1 = "horse", word2 = "ros"   (each row is dp[i][0..3] after row i is filled)
 *
 *    i | word1[i-1] | j=0 "" | j=1 'r' | j=2 'o' | j=3 's' | how the key cells were filled
 *   ---+------------+--------+---------+---------+---------+---------------------------------------
 *    0 |     -      |   0    |    1    |    2    |    3    | base row: insert j chars
 *    1 |    'h'     |   1    |    1    |    2    |    3    | (1,1) h != r: 1 + min(0,1,1) = 1 replace
 *    2 |    'o'     |   2    |    2    |    1    |    2    | (2,2) o == o: copy dp[1][1] = 1
 *    3 |    'r'     |   3    |    2    |    2    |    2    | (3,1) r == r: copy dp[2][0] = 2
 *    4 |    's'     |   4    |    3    |    3    |    2    | (4,3) s == s: copy dp[3][2] = 2
 *    5 |    'e'     |   5    |    4    |    4    |    3    | (5,3) e != s: 1 + min(3,2,4) = 3 delete
 *
 *   Returns 3 (dp[5][3]).
 *
 * EDGE CASES
 *   "", "abc"                 -> 3   only inserts (base row)
 *   "abc", ""                 -> 3   only deletes (base column)
 *   "abc", "abc"              -> 0   every char matches, all copies
 *   "ab", "ba"                -> 2   no swap operation; two replaces
 *   "intention", "execution"  -> 5   official example 2
 *
 * COMMON MISTAKES
 *   - Adding 1 when the chars match: a match costs nothing, copy dp[i-1][j-1].
 *   - Taking the min of only two neighbours: all three (replace, delete, insert) are needed.
 *   - Forgetting the base row and column: they are not 0, they are i and j.
 *   - Table of size m x n: no room for the empty prefix, every index shifts by one.
 *
 * COMPLEXITY
 *   O(m * n) time, O(m * n) extra space (can drop to one row of size n + 1).
 *
 * RELATED
 *   LeetCode 1143 (LongestCommonSubsequence, DynamicProgramming/): same two-prefix table, max instead of min.
 *   LeetCode 10 (RegularExpressionMatching, DynamicProgramming/): same table, cells are true/false.
 *   LeetCode 64 (MinimumPathSum, DynamicProgramming/): each cell is a min over its top and left neighbours.
 */
public class EditDistance {

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
    }

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[m][n];
    }

}

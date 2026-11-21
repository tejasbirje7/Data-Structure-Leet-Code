/**
 * LeetCode 1143. Longest Common Subsequence
 * https://leetcode.com/problems/longest-common-subsequence/
 *
 * PROBLEM
 *   - Input: two strings text1 and text2 (lowercase, length 1 to 1000).
 *   - Return: the length of their longest common subsequence (0 if none).
 *   - Subsequence = delete some chars, keep the order; it does not need to be contiguous.
 *
 * EXAMPLE
 *   Input: text1 = "abcde", text2 = "ace"
 *   Output: 3
 *   Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 * HOW TO THINK
 *   - Clue: two strings + "subsequence" + "longest" -> DP on two prefixes.
 *   - Technique: dp[i][j] = LCS length of text1[0..i) and text2[0..j).
 *     Only the last chars text1[i-1] and text2[j-1] need a decision.
 *   - Rule that stays true: row 0 and column 0 are 0 (an empty string shares nothing).
 *     Values never drop when moving right or down.
 *   - Decision: last chars equal -> dp[i][j] = dp[i-1][j-1] + 1 (use the pair).
 *               otherwise        -> max(dp[i-1][j], dp[i][j-1])  (drop the last char of one string).
 *   - Return: dp[m][n]. Table is (m + 1) x (n + 1).
 *
 * STEPS
 *   1. dp = int[m + 1][n + 1] (all zeros, so row 0 and column 0 are ready).
 *   2. Loop i from 1 to m, j from 1 to n.
 *   3. Equal chars: top-left + 1. Different chars: max of top and left.
 *   4. Return dp[m][n].
 *
 * DRY RUN   input = text1 = "abcde", text2 = "ace"   (each row is dp[i][0..3] after row i is filled)
 *
 *    i | text1[i-1] | j=0 "" | j=1 'a' | j=2 'c' | j=3 'e' | how the key cells were filled
 *   ---+------------+--------+---------+---------+---------+------------------------------------
 *    0 |     -      |   0    |    0    |    0    |    0    | base row
 *    1 |    'a'     |   0    |    1    |    1    |    1    | (1,1) a == a: dp[0][0] + 1 = 1
 *    2 |    'b'     |   0    |    1    |    1    |    1    | (2,1) b != a: max(1, 0) = 1
 *    3 |    'c'     |   0    |    1    |    2    |    2    | (3,2) c == c: dp[2][1] + 1 = 2
 *    4 |    'd'     |   0    |    1    |    2    |    2    | (4,3) d != e: max(2, 2) = 2
 *    5 |    'e'     |   0    |    1    |    2    |    3    | (5,3) e == e: dp[4][2] + 1 = 3
 *
 *   Returns 3 (dp[5][3]): "ace".
 *
 * EDGE CASES
 *   "abc", "abc"        -> 3   identical strings
 *   "abc", "def"        -> 0   no shared char
 *   "bl", "yby"         -> 1   only "b"
 *   "abcba", "abcbcba"  -> 5   "abcba" fits inside the second string
 *
 * COMMON MISTAKES
 *   - On a match taking max(top, left) + 1: use top-left + 1, or the same char gets used twice.
 *   - Resetting to 0 on a mismatch: that is Longest Common SUBSTRING. A subsequence carries the max forward.
 *   - Table of size m x n: no room for the empty prefix, every index shifts by one.
 *
 * COMPLEXITY
 *   O(m * n) time, O(m * n) extra space (can drop to one row of size n + 1).
 *
 * RELATED
 *   LeetCode 72 (EditDistance, DynamicProgramming/): same table, min edits instead of max matches.
 *   LeetCode 300 (LongestIncreasingSubsequence, DynamicProgramming/): subsequence DP on a single array.
 *   LeetCode 10 (RegularExpressionMatching, DynamicProgramming/): same two-prefix table with wildcard rules.
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace"));
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

}

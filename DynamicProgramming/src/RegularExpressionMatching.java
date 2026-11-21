/**
 * LeetCode 10. Regular Expression Matching
 * https://leetcode.com/problems/regular-expression-matching/
 *
 * PROBLEM
 *   - Input: a string s and a pattern p.
 *   - Return: true if p matches ALL of s (not just a part).
 *   - '.' matches any one char; '*' means zero or more copies of the char right before it.
 *   - Every '*' has a valid char before it.
 *
 * EXAMPLE
 *   Input: s = "aa", p = "a*"
 *   Output: true
 *   Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 *
 * HOW TO THINK
 *   - Clue: "x*" can eat zero, one or many chars -> many ways to match, the same suffixes repeat -> DP on two prefixes.
 *   - Technique: dp[i][j] = true if s[0..i) is matched by p[0..j).
 *     Treat "x*" as one unit: it is either skipped, or it eats one more char of s and stays.
 *   - Rule that stays true: dp[0][0] = true; dp[i][0] = false for i > 0;
 *     dp[0][j] = true only when p[0..j) is all "x*" pairs (each one used zero times).
 *   - Decision: p[j-1] is '*' -> dp[i][j-2]                            (use "x*" zero times)
 *                                OR (s[i-1] matches x AND dp[i-1][j])  (eat s[i-1], keep "x*").
 *               otherwise      -> s[i-1] matches p[j-1] AND dp[i-1][j-1].
 *     "matches" means the chars are equal or the pattern char is '.'.
 *   - Return: dp[m][n]. Table is (m + 1) x (n + 1).
 *
 * STEPS
 *   1. dp = boolean[m + 1][n + 1]; dp[0][0] = true.
 *   2. Fill row 0: dp[0][j] = p[j-1] == '*' && dp[0][j-2].
 *   3. Loop i from 1 to m, j from 1 to n and apply the star / non-star rule above.
 *   4. Return dp[m][n].
 *
 * DRY RUN   input = s = "aab", p = "c*a*b"   (each row is dp[i][0..5] after row i is filled)
 *
 *    i | s[i-1] | j=0 | j=1 c | j=2 * | j=3 a | j=4 * | j=5 b | how the key cells were filled
 *   ---+--------+-----+-------+-------+-------+-------+-------+---------------------------------------------
 *    0 |   -    |  T  |   F   |   T   |   F   |   T   |   F   | (0,2) "c*" zero times; (0,4) "c*a*" zero times
 *    1 |  'a'   |  F  |   F   |   F   |   T   |   T   |   F   | (1,3) a == a and dp[0][2]; (1,4) eat 'a', dp[0][4]
 *    2 |  'a'   |  F  |   F   |   F   |   F   |   T   |   F   | (2,4) eat 'a', dp[1][4] = T
 *    3 |  'b'   |  F  |   F   |   F   |   F   |   F   |   T   | (3,4) b != a and dp[3][2] = F; (3,5) b == b, dp[2][4]
 *
 *   Returns true (dp[3][5]): "c*" used zero times, "a*" used twice, then 'b'.
 *
 * EDGE CASES
 *   "aa", "a"                    -> false  p must match all of s
 *   "ab", ".*"                   -> true   ".*" can be any string
 *   "", "a*b*"                   -> true   every star used zero times (row 0)
 *   "a", "ab*"                   -> true   trailing "b*" used zero times
 *   "mississippi", "mis*is*p*."  -> false  official example
 *
 * COMMON MISTAKES
 *   - Treating '*' alone: it always belongs to the char before it, so look back at p[j-2].
 *   - Forgetting row 0: "" must match patterns like "a*b*", otherwise later cells are wrong.
 *   - In the "eat one char" case using dp[i-1][j-2]: use dp[i-1][j] so the star can eat more chars.
 *   - Comparing s[i-1] with p[j-1] when p[j-1] is '*': compare with p[j-2], the starred char.
 *
 * COMPLEXITY
 *   O(m * n) time, O(m * n) extra space.
 *
 * RELATED
 *   LeetCode 72 (EditDistance, DynamicProgramming/): same two-prefix table, cells hold counts instead of true/false.
 *   LeetCode 1143 (LongestCommonSubsequence, DynamicProgramming/): same table, simpler rule with no wildcards.
 *   LeetCode 139 (WordBreak, DynamicProgramming/): true/false DP on prefixes of one string.
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        System.out.println(isMatch("aab", "c*a*b"));
    }

    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 2; j <= n; j++) {
            dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);
                if (pc == '*') {
                    dp[i][j] = dp[i][j - 2] || (matches(s.charAt(i - 1), p.charAt(j - 2)) && dp[i - 1][j]);
                } else {
                    dp[i][j] = matches(s.charAt(i - 1), pc) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    private static boolean matches(char sc, char pc) {
        return pc == '.' || sc == pc;
    }

}

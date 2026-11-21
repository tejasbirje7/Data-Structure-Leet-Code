/**
 * LeetCode 91. Decode Ways
 * https://leetcode.com/problems/decode-ways/
 *
 * PROBLEM
 *   - Input: a string s of digits. Letters map as 'A' -> "1", 'B' -> "2", ..., 'Z' -> "26".
 *   - Return: the number of ways to decode s into letters (0 if there is none).
 *   - A code with a leading zero ("06") is not valid; "0" alone is not valid.
 *
 * EXAMPLE
 *   Input: s = "12"
 *   Output: 2
 *   Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 *
 * HOW TO THINK
 *   - Clue: "number of ways" + each step takes 1 or 2 chars -> Climbing Stairs with rules on which steps are allowed.
 *   - Technique: dp[i] = number of ways to decode the first i chars s[0..i).
 *     The last letter used either one digit s[i-1] or two digits s[i-2..i).
 *   - Rule that stays true: dp[0] = 1 (the empty prefix has one way: decode nothing).
 *   - Decision: s[i-1] is '1'..'9'          -> dp[i] += dp[i-1]  (one-digit letter).
 *               s[i-2..i) is "10".."26"     -> dp[i] += dp[i-2]  (two-digit letter).
 *               neither                     -> dp[i] stays 0, and the zero spreads forward.
 *   - Return: dp[n]. The array has size n + 1.
 *
 * STEPS
 *   1. dp = int[n + 1]; dp[0] = 1.
 *   2. Loop i from 1 to n.
 *   3. If s[i-1] != '0', add dp[i-1]. If i >= 2 and 10 <= number(s[i-2..i)) <= 26, add dp[i-2].
 *   4. Return dp[n].
 *
 * DRY RUN   input = s = "11106"   (dp[i] is after the step)
 *
 *    i | one digit | valid? | two digits | valid? | action            | dp[i]
 *   ---+-----------+--------+------------+--------+-------------------+-------
 *    0 |     -     |   -    |     -      |   -    | base case         |   1
 *    1 |    "1"    |  yes   |     -      |   -    | + dp[0] = 1       |   1
 *    2 |    "1"    |  yes   |    "11"    |  yes   | + dp[1] + dp[0]   |   2
 *    3 |    "1"    |  yes   |    "11"    |  yes   | + dp[2] + dp[1]   |   3
 *    4 |    "0"    |   no   |    "10"    |  yes   | + dp[2] = 2       |   2
 *    5 |    "6"    |  yes   |    "06"    |   no   | + dp[4] = 2       |   2
 *
 *   Returns 2: "1 1 10 6" (AAJF) and "11 10 6" (KJF).
 *
 * EDGE CASES
 *   "0"    -> 0   "0" is not a letter
 *   "06"   -> 0   leading zero, and "0" alone is invalid
 *   "10"   -> 1   only "10"; the "0" must pair with the "1"
 *   "100"  -> 0   second "0" can't stand alone and "00" is invalid
 *   "27"   -> 1   27 > 26, only "2 7"
 *   "226"  -> 3   "2 2 6", "22 6", "2 26"
 *
 * COMMON MISTAKES
 *   - Accepting "06" as a two-digit code: the value must be at least 10, not just at most 26.
 *   - Adding dp[i-1] when s[i-1] is '0': '0' has no letter on its own.
 *   - dp[0] = 0: then nothing ever gets counted. The empty prefix has exactly one way.
 *   - Returning early on the first '0': "10" is valid; only skip the one-digit branch.
 *
 * COMPLEXITY
 *   O(n) time, O(n) extra space (can drop to two variables, like Fibonacci).
 *
 * RELATED
 *   LeetCode 70 (ClimbingStairs, DynamicProgramming/): same 1-or-2 step recurrence with no rules.
 *   LeetCode 509 (FibonacciNumber, DynamicProgramming/): the plain dp[i-1] + dp[i-2] recurrence.
 *   LeetCode 139 (WordBreak, DynamicProgramming/): count/decide splits of a string, but pieces come from a dictionary.
 */
public class DecodeWays {

    public static void main(String[] args) {
        System.out.println(numDecodings("11106"));
    }

    public static int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            if (i >= 2) {
                int twoDigits = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
                if (twoDigits >= 10 && twoDigits <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[n];
    }

}

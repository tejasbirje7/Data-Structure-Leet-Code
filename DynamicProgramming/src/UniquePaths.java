/**
 * LeetCode 62. Unique Paths
 * https://leetcode.com/problems/unique-paths/
 *
 * PROBLEM
 *   - Input: m rows and n columns of a grid (1 <= m, n <= 100).
 *   - Return: the number of paths from the top-left cell to the bottom-right cell, moving only right or down.
 *   - The answer is guaranteed to be <= 2 * 10^9, so it fits in an int.
 *
 * EXAMPLE
 *   Input: m = 3, n = 2
 *   Output: 3
 *   Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 *     1. Right -> Down -> Down
 *     2. Down -> Down -> Right
 *     3. Down -> Right -> Down
 *
 * HOW TO THINK
 *   - Clue: "number of paths" + "only right or down" -> each cell is entered from above or from the left (grid DP).
 *   - Technique: paths(r, c) = paths(r-1, c) + paths(r, c-1).
 *     The first row and first column have exactly 1 path each.
 *     Keep one row: dp[c] holds the value from the row above until it is overwritten.
 *   - Rule that stays true: while filling row r at column c, dp[c] (old) = from above, dp[c-1] (new) = from the left.
 *   - Decision: no branching; dp[c] = dp[c] + dp[c-1] for c >= 1. dp[0] stays 1.
 *   - Return: dp[n-1] after the last row.
 *
 * STEPS
 *   1. dp = array of n ones (the first row).
 *   2. Loop r from 1 to m-1.
 *   3. Inner loop c from 1 to n-1: dp[c] += dp[c-1].
 *   4. Return dp[n-1].
 *
 * DRY RUN   input = m = 3, n = 7   (dp is after the whole row)
 *
 *    row | dp[0] dp[1] dp[2] dp[3] dp[4] dp[5] dp[6]
 *   -----+------------------------------------------
 *     0  |   1     1     1     1     1     1     1
 *     1  |   1     2     3     4     5     6     7
 *     2  |   1     3     6    10    15    21    28
 *
 *   Row 2, column 3: 10 = 4 (above) + 6 (left).
 *   Returns 28.
 *
 * EDGE CASES
 *   m = 1, n = 1   -> 1   start is the end
 *   m = 1, n = 5   -> 1   single row: only right moves
 *   m = 5, n = 1   -> 1   single column: only down moves
 *   m = 3, n = 2   -> 3   same as m = 2, n = 3 (the grid is symmetric)
 *
 * COMMON MISTAKES
 *   - Forgetting the first row and column are all 1: a 0 there makes every later cell 0.
 *   - Recursion without memo: exponential time.
 *   - Using the formula C(m+n-2, m-1) with factorials: overflows long well before m, n = 100.
 *
 * COMPLEXITY
 *   O(m * n) time, O(n) extra space.
 *
 * RELATED
 *   LeetCode 64 (MinimumPathSum, DynamicProgramming/): same grid walk, but takes the min cost instead of adding counts.
 *   LeetCode 70 (ClimbingStairs, DynamicProgramming/): the 1-D version, ways from the two previous positions.
 */
public class UniquePaths {

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7));
    }

    public static int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, 1);
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                dp[c] += dp[c - 1];
            }
        }
        return dp[n - 1];
    }

}

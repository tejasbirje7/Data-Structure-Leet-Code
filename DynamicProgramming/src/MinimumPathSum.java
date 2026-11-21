/**
 * LeetCode 64. Minimum Path Sum
 * https://leetcode.com/problems/minimum-path-sum/
 *
 * PROBLEM
 *   - Input: an m x n grid of non-negative numbers.
 *   - Return: the smallest sum of a path from the top-left cell to the bottom-right cell.
 *   - You can only move right or down.
 *
 * EXAMPLE
 *   Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 *   Output: 7
 *   Explanation: Because the path 1 -> 3 -> 1 -> 1 -> 1 minimizes the sum.
 *
 * HOW TO THINK
 *   - Clue: "minimum sum" + "only right or down" -> each cell is entered from above or from the left (grid DP).
 *   - Technique: cost(r, c) = grid[r][c] + min(cost(r-1, c), cost(r, c-1)).
 *     First row: only from the left. First column: only from above.
 *     Keep one row: dp[c] holds the row above until it is overwritten.
 *   - Rule that stays true: while filling row r at column c, dp[c] (old) = cost from above,
 *     dp[c-1] (new) = cost from the left.
 *   - Decision: c == 0 -> dp[0] += grid[r][0] (only from above).
 *               c > 0  -> dp[c] = grid[r][c] + min(dp[c], dp[c-1]).
 *   - Return: dp[n-1] after the last row.
 *
 * STEPS
 *   1. dp = int[n]; fill the first row as running sums of grid[0].
 *   2. Loop r from 1 to m-1: dp[0] += grid[r][0].
 *   3. Inner loop c from 1 to n-1: dp[c] = grid[r][c] + min(dp[c], dp[c-1]).
 *   4. Return dp[n-1].
 *
 * DRY RUN   input = [[1,3,1],[1,5,1],[4,2,1]]   (dp[c] is after the step)
 *
 *    cell  | grid | from above | from left | action        | dp[c]
 *   -------+------+------------+-----------+---------------+-------
 *    (0,0) |  1   |     -      |     -     | start         |   1
 *    (0,1) |  3   |     -      |     1     | 3 + 1         |   4
 *    (0,2) |  1   |     -      |     4     | 1 + 4         |   5
 *    (1,0) |  1   |     1      |     -     | 1 + 1         |   2
 *    (1,1) |  5   |     4      |     2     | 5 + min(4,2)  |   7
 *    (1,2) |  1   |     5      |     7     | 1 + min(5,7)  |   6
 *    (2,0) |  4   |     2      |     -     | 4 + 2         |   6
 *    (2,1) |  2   |     7      |     6     | 2 + min(7,6)  |   8
 *    (2,2) |  1   |     6      |     8     | 1 + min(6,8)  |   7
 *
 *   Returns 7: path 1 -> 3 -> 1 -> 1 -> 1.
 *
 * EDGE CASES
 *   [[5]]            -> 5   single cell
 *   [[1,2,3]]        -> 6   single row: the only path is the whole row
 *   [[1],[2],[3]]    -> 6   single column
 *   [[1,2],[1,1]]    -> 3   go down first, then right
 *
 * COMMON MISTAKES
 *   - Greedy "step to the cheaper neighbour": an early cheap step can lead into expensive cells.
 *   - Taking min with a cell outside the grid for the first row or column: those cells have only one way in.
 *   - Filling with Integer.MAX_VALUE and then adding to it: overflow. Fill the first row and column directly.
 *
 * COMPLEXITY
 *   O(m * n) time, O(n) extra space.
 *
 * RELATED
 *   LeetCode 62 (UniquePaths, DynamicProgramming/): same grid walk, but adds path counts instead of taking a min.
 *   LeetCode 72 (EditDistance, DynamicProgramming/): another 2-D table where each cell depends on its top and left.
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(grid));
    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for (int c = 1; c < n; c++) {
            dp[c] = dp[c - 1] + grid[0][c];
        }
        for (int r = 1; r < m; r++) {
            dp[0] += grid[r][0];
            for (int c = 1; c < n; c++) {
                dp[c] = grid[r][c] + Math.min(dp[c], dp[c - 1]);
            }
        }
        return dp[n - 1];
    }

}

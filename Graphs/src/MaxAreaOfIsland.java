/**
 * LeetCode 695. Max Area of Island
 * https://leetcode.com/problems/max-area-of-island/
 *
 * PROBLEM
 *   - Input: an m x n grid of 0 (water) and 1 (land).
 *   - An island is land joined up, down, left or right; its area is its number of cells.
 *   - Return: the largest island area, or 0 if there is no land.
 *
 * EXAMPLE
 *   Input: grid = [
 *     [0,0,1,0,0,0,0,1,0,0,0,0,0],
 *     [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *     [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *     [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *     [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *     [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *     [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *     [0,0,0,0,0,0,0,1,1,0,0,0,0]
 *   ]
 *   Output: 6
 *   Explanation: The answer is not 11, because the island must be connected 4-directionally.
 *
 * HOW TO THINK
 *   - Clue: connected land cells on a grid -> connected components, like Number of Islands,
 *     but now we need the SIZE of each component.
 *   - Technique: scan every cell; from each unvisited land cell, DFS the island and let the DFS return how many cells it sank.
 *   - Rule that stays true: dfs(r, c) returns the area of the land reachable from (r, c) that was not sunk yet,
 *     and sinks all of it, so no cell is counted twice.
 *   - Decision: in the DFS, out of the grid or water -> 0; else sink it and return 1 + the four neighbours' results.
 *     In the scan, land -> best = max(best, dfs(r, c)).
 *   - Return: best (0 when the grid has no land).
 *
 * STEPS
 *   1. best = 0.
 *   2. For every cell (r, c): if grid[r][c] == 1, best = max(best, dfs(r, c)).
 *   3. dfs(r, c): out of the grid or 0 -> return 0.
 *   4.   grid[r][c] = 0; return 1 + dfs down + dfs up + dfs right + dfs left.
 *   5. Return best.
 *
 * DRY RUN   input = [[1,1,0,1],
 *                    [1,0,0,1],
 *                    [0,0,1,1]]   (best is after the cell)
 *
 *    cell  | value | action                    | cells sunk by the DFS      | area | best
 *   -------+-------+---------------------------+----------------------------+------+------
 *    (0,0) |   1   | dfs                       | (0,0) (1,0) (0,1)          |  3   |  3
 *    (0,1) (0,2): 0 -> skip ((0,1) was sunk)                                |      |  3
 *    (0,3) |   1   | dfs                       | (0,3) (1,3) (2,3) (2,2)    |  4   |  4
 *    rows 1 and 2: every cell is now 0 -> skip                              |      |  4
 *
 *   Returns 4. Final state: every cell of the grid is 0.
 *
 * EDGE CASES
 *   [[0,0,0,0,0,0,0,0]]     -> 0   no land
 *   [[1]]                   -> 1   one cell
 *   [[1,0],[0,1]]           -> 1   diagonal cells are separate islands of area 1
 *   all 1                   -> m * n   one island covering the grid
 *
 * COMMON MISTAKES
 *   - Recursing before sinking the cell: neighbours count each other again and loop forever. Sink first.
 *   - Using a global counter that is not reset for each new island: areas add up across islands.
 *   - Counting diagonal neighbours: only 4 directions.
 *
 * COMPLEXITY
 *   O(m * n) time (each cell is sunk once), O(m * n) extra space in the worst case for the recursion stack.
 *
 * RELATED
 *   LeetCode 200 (NumberOfIslands, Graphs/): same flood-fill, but counts islands instead of measuring them.
 *   LeetCode 417 (PacificAtlanticWaterFlow, Graphs/): flood-fill again, starting from the grid's borders.
 */
public class MaxAreaOfIsland {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0, 1},
                {1, 0, 0, 1},
                {0, 0, 1, 1}
        };
        System.out.println(maxAreaOfIsland(grid));
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int best = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    best = Math.max(best, area(grid, r, c));
                }
            }
        }
        return best;
    }

    private static int area(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }
        grid[r][c] = 0;
        return 1 + area(grid, r + 1, c) + area(grid, r - 1, c) + area(grid, r, c + 1) + area(grid, r, c - 1);
    }

}

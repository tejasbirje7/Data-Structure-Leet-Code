/**
 * LeetCode 200. Number of Islands
 * https://leetcode.com/problems/number-of-islands/
 *
 * PROBLEM
 *   - Input: an m x n grid of '1' (land) and '0' (water).
 *   - Return: the number of islands.
 *   - An island is land joined up, down, left or right (not diagonally). Everything outside the grid is water.
 *
 * EXAMPLE
 *   Input: grid = [
 *     ["1","1","0","0","0"],
 *     ["1","1","0","0","0"],
 *     ["0","0","1","0","0"],
 *     ["0","0","0","1","1"]
 *   ]
 *   Output: 3
 *
 * HOW TO THINK
 *   - Clue: a grid where cells "connect" to their 4 neighbours -> the grid is a graph; islands are connected components.
 *   - Technique: scan every cell; each time you find land not yet seen, that is a new island,
 *     so flood-fill (DFS) the whole island and mark it as seen.
 *   - Rule that stays true: after the flood-fill from a cell, every land cell of that island is turned to '0' (sunk),
 *     so the scan can never count the same island twice.
 *   - Decision per cell: '1' -> count++ and sink the island; '0' -> skip.
 *     Inside the DFS: out of the grid or '0' -> stop; else sink it and visit the 4 neighbours.
 *   - Return: the count after the full scan.
 *
 * STEPS
 *   1. count = 0.
 *   2. For every cell (r, c), row by row:
 *   3.   If grid[r][c] == '1': count++, then dfs(r, c).
 *   4. dfs(r, c): stop if out of the grid or not '1'; set grid[r][c] = '0'; dfs down, up, right, left.
 *   5. Return count.
 *
 * DRY RUN   input = the example grid above   (count is after the cell)
 *
 *    cell  | value | action                        | cells sunk by the DFS     | count
 *   -------+-------+-------------------------------+---------------------------+------
 *    (0,0) |  '1'  | new island, dfs               | (0,0) (1,0) (1,1) (0,1)   |  1
 *    (0,1) |  '0'  | skip (sunk by the first dfs)  |                           |  1
 *    row 0 rest, all of row 1, (2,0) (2,1): '0' -> skip                       |  1
 *    (2,2) |  '1'  | new island, dfs               | (2,2)                     |  2
 *    (2,3) (2,4) (3,0) (3,1) (3,2): '0' -> skip                               |  2
 *    (3,3) |  '1'  | new island, dfs               | (3,3) (3,4)               |  3
 *    (3,4) |  '0'  | skip (sunk by the last dfs)   |                           |  3
 *
 *   Returns 3. Final state: every cell of the grid is '0'.
 *
 * EDGE CASES
 *   [["0"]]                  -> 0   no land at all
 *   [["1"]]                  -> 1   one cell is one island
 *   [["1","0"],["0","1"]]    -> 2   diagonal cells are NOT connected
 *   all '1'                  -> 1   one big island; the DFS sinks the whole grid
 *
 * COMMON MISTAKES
 *   - Recursing before marking the cell: two neighbours keep calling each other forever. Sink first, then recurse.
 *   - Checking diagonals too: only 4 directions count.
 *   - Counting land cells instead of DFS starts: count++ only when the scan finds a '1', never inside the DFS.
 *   - If the input must not change, use a separate boolean visited[m][n] instead of sinking.
 *
 * COMPLEXITY
 *   O(m * n) time (each cell is sunk at most once and checked from at most 4 neighbours).
 *   O(m * n) extra space in the worst case for the recursion stack (a grid that is all land).
 *
 * RELATED
 *   LeetCode 695 (MaxAreaOfIsland, Graphs/): same flood-fill, but the DFS returns the island's size.
 *   LeetCode 994 (RottingOranges, Graphs/): grid as a graph again, but BFS from many starts at once, counting minutes.
 *   LeetCode 417 (PacificAtlanticWaterFlow, Graphs/): flood-fill from the borders instead of from every cell.
 */
public class NumberOfIslands {

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    sink(grid, r, c);
                }
            }
        }
        return count;
    }

    private static void sink(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] != '1') {
            return;
        }
        grid[r][c] = '0';
        sink(grid, r + 1, c);
        sink(grid, r - 1, c);
        sink(grid, r, c + 1);
        sink(grid, r, c - 1);
    }

}

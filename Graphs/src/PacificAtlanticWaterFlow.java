import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 417. Pacific Atlantic Water Flow
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 *
 * PROBLEM
 *   - Input: an m x n grid of heights. The Pacific touches the top and left edges; the Atlantic touches the bottom and right edges.
 *   - Rain flows from a cell to a 4-neighbour whose height is less than or equal; edge cells flow into the ocean they touch.
 *   - Return: every [r, c] whose water can reach BOTH oceans (any order).
 *
 * EXAMPLE
 *   Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 *   Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 *   Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
 *     [0,4]: [0,4] -> Pacific Ocean
 *            [0,4] -> Atlantic Ocean
 *     [1,3]: [1,3] -> [0,3] -> Pacific Ocean
 *            [1,3] -> [1,4] -> Atlantic Ocean
 *     ... (the same check for the other five cells)
 *     Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
 *
 * HOW TO THINK
 *   - Clue: "can reach the border" for EVERY cell -> searching from each cell is O((m*n)^2).
 *     Turn it around: start at the ocean and walk uphill.
 *   - Technique: two flood-fills (DFS). One from all Pacific edge cells, one from all Atlantic edge cells.
 *     Water flows downhill, so the reverse walk goes to a neighbour with height >= the current one.
 *   - Rule that stays true: pacific[r][c] is true exactly when water at (r, c) can flow to the Pacific (same for atlantic).
 *   - Decision in the DFS: out of the grid, already marked, or lower than the cell we came from -> stop;
 *     else mark it and try the 4 neighbours with this cell's height.
 *   - Return: every cell marked in both grids, row by row.
 *
 * STEPS
 *   1. pacific, atlantic = boolean[m][n].
 *   2. DFS from every top-row and left-column cell into pacific.
 *   3. DFS from every bottom-row and right-column cell into atlantic.
 *   4. dfs(r, c, prevHeight): stop if outside, marked, or heights[r][c] < prevHeight; else mark, recurse down, up, right, left.
 *   5. Collect [r, c] where both are true.
 *
 * DRY RUN   input = [[1,2,3],
 *                    [8,9,4],
 *                    [7,6,5]]
 *
 *   Pacific (starts: top row, then left column):
 *    start | cells marked, in DFS order                                      | why the walk stops
 *   -------+-----------------------------------------------------------------+-------------------------------
 *    (0,0) | (0,0) (1,0) (1,1) (0,1) (0,2) (1,2) (2,2) (2,1) (2,0)          | every cell is marked
 *    others| none                                                            | already marked
 *
 *   Atlantic (starts: bottom row, then right column):
 *    start | cells marked, in DFS order | why the walk stops
 *   -------+----------------------------+---------------------------------------------------------
 *    (2,0) | (2,0) (1,0) (1,1)          | (0,0)=1 < 8, (0,1)=2 < 9, (2,1)=6 < 7: all downhill
 *    (2,1) | (2,1)                      | (2,2)=5 < 6
 *    (2,2) | (2,2)                      | (1,2)=4 < 5
 *    (0,2) | (0,2) (1,2)                | (0,1)=2 < 3; (1,1) and (2,2) already marked
 *    (1,2) | none                       | already marked
 *
 *   Both: every cell except (0,0) and (0,1), which only reach the Pacific.
 *   Returns [[0,2],[1,0],[1,1],[1,2],[2,0],[2,1],[2,2]].
 *
 * EDGE CASES
 *   [[1]]               -> [[0,0]]          one cell touches both oceans
 *   [[1],[1]]           -> [[0,0],[1,0]]    one column: every cell touches the left (Pacific) and right (Atlantic) edges
 *   all equal heights   -> every cell       water can flow anywhere
 *   [[2,1],[1,2]]       -> [[0,0],[0,1],[1,0],[1,1]]   (0,1) and (1,0) touch both oceans; the higher (0,0) and (1,1) flow into them
 *
 * COMMON MISTAKES
 *   - Walking downhill from the ocean: the reverse walk must go UP (neighbour >= current).
 *   - Running a search from every cell: O((m*n)^2). Two searches from the borders are O(m*n).
 *   - Sharing one visited grid for both oceans: each ocean needs its own marks.
 *   - Using > instead of >= : water also flows between equal heights.
 *
 * COMPLEXITY
 *   O(m * n) time (each cell is marked at most once per ocean), O(m * n) extra space for the two grids and the recursion.
 *
 * RELATED
 *   LeetCode 200 (NumberOfIslands, Graphs/): flood-fill from inside the grid instead of from the edges.
 *   LeetCode 994 (RottingOranges, Graphs/): also starts from many cells at once, but with BFS and a time count.
 */
public class PacificAtlanticWaterFlow {

    public static void main(String[] args) {
        int[][] heights = {
                {1, 2, 3},
                {8, 9, 4},
                {7, 6, 5}
        };
        List<List<Integer>> cells = pacificAtlantic(heights);
        System.out.println(cells);
    }

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for (int c = 0; c < n; c++) {
            climb(heights, pacific, 0, c, Integer.MIN_VALUE);
        }
        for (int r = 0; r < m; r++) {
            climb(heights, pacific, r, 0, Integer.MIN_VALUE);
        }
        for (int c = 0; c < n; c++) {
            climb(heights, atlantic, m - 1, c, Integer.MIN_VALUE);
        }
        for (int r = 0; r < m; r++) {
            climb(heights, atlantic, r, n - 1, Integer.MIN_VALUE);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }
        return result;
    }

    private static void climb(int[][] heights, boolean[][] reach, int r, int c, int prevHeight) {
        if (r < 0 || c < 0 || r >= heights.length || c >= heights[0].length
                || reach[r][c] || heights[r][c] < prevHeight) {
            return;
        }
        reach[r][c] = true;
        climb(heights, reach, r + 1, c, heights[r][c]);
        climb(heights, reach, r - 1, c, heights[r][c]);
        climb(heights, reach, r, c + 1, heights[r][c]);
        climb(heights, reach, r, c - 1, heights[r][c]);
    }

}

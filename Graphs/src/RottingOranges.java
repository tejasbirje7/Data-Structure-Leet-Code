import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 994. Rotting Oranges
 * https://leetcode.com/problems/rotting-oranges/
 *
 * PROBLEM
 *   - Input: an m x n grid: 0 = empty, 1 = fresh orange, 2 = rotten orange.
 *   - Every minute, each fresh orange next to a rotten one (up, down, left, right) becomes rotten.
 *   - Return: the minimum minutes until no fresh orange is left, or -1 if some can never rot.
 *
 * EXAMPLE
 *   Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 *   Output: 4
 *
 * HOW TO THINK
 *   - Clue: something spreads one step per minute from several places at the same time -> multi-source BFS;
 *     one BFS level = one minute.
 *   - Technique: put ALL rotten oranges in the queue at minute 0, count the fresh ones,
 *     then process the queue one level at a time.
 *   - Rule that stays true: after `minutes` levels, the queue holds exactly the oranges that rotted in that minute,
 *     and fresh is the number still fresh.
 *   - Decision for each neighbour of a polled orange: fresh (1) -> make it 2, fresh--, queue it; otherwise skip.
 *     Keep going only while the queue has oranges AND fresh > 0.
 *   - Return: fresh == 0 ? minutes : -1.
 *
 * STEPS
 *   1. Queue every 2; count every 1 as fresh.
 *   2. minutes = 0.
 *   3. While the queue is not empty and fresh > 0: minutes++; poll exactly the current level's oranges.
 *   4.   For each of the 4 neighbours that is 1: set it to 2, fresh--, queue it.
 *   5. Return fresh == 0 ? minutes : -1.
 *
 * DRY RUN   input = [[2,1,1],[1,1,0],[0,1,1]]   (start: queue = [(0,0)], fresh = 6; queue and fresh are after the minute)
 *   neighbours are checked down, up, right, left.
 *
 *    minute | oranges polled | fresh neighbours that rot | queue after   | fresh
 *   --------+----------------+---------------------------+---------------+------
 *      1    | (0,0)          | (1,0) (0,1)               | (1,0) (0,1)   |  4
 *      2    | (1,0) (0,1)    | (1,1) from (1,0); (0,2) from (0,1) | (1,1) (0,2) |  2
 *      3    | (1,1) (0,2)    | (2,1) from (1,1)          | (2,1)         |  1
 *      4    | (2,1)          | (2,2)                     | (2,2)         |  0
 *
 *   fresh == 0, so the loop stops (no 5th minute for (2,2)). Returns 4.
 *
 * EDGE CASES
 *   [[2,1,1],[0,1,1],[1,0,1]]   -> -1   the orange at (2,0) is walled off by empty cells
 *   [[0,2]]                     -> 0    no fresh oranges at the start
 *   [[0]]                       -> 0    no oranges at all
 *   [[1]]                       -> -1   a fresh orange with nothing rotten to start from
 *   [[2,1,1,1,2]]               -> 2    two sources rot the middle from both sides at once
 *
 * COMMON MISTAKES
 *   - Starting BFS from one rotten orange at a time: the spread is simultaneous. Queue them all at minute 0.
 *   - Counting one extra minute: the last level rots nothing new. Stop when fresh hits 0 (or count only levels that rot).
 *   - Returning minutes without checking fresh == 0: unreachable oranges must give -1.
 *   - Not processing level by level (no size loop): minutes then count oranges, not time.
 *
 * COMPLEXITY
 *   O(m * n) time (each orange enters the queue at most once), O(m * n) extra space for the queue.
 *
 * RELATED
 *   LeetCode 127 (WordLadder, Graphs/): BFS level by level where the level count is the answer.
 *   LeetCode 200 (NumberOfIslands, Graphs/): the same grid-as-graph neighbours, explored with DFS.
 */
public class RottingOranges {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(orangesRotting(grid));
    }

    public static int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        int fresh = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    fresh++;
                }
            }
        }
        int minutes = 0;
        while (!queue.isEmpty() && fresh > 0) {
            minutes++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                for (int[] d : DIRS) {
                    int r = cell[0] + d[0];
                    int c = cell[1] + d[1];
                    if (r >= 0 && c >= 0 && r < m && c < n && grid[r][c] == 1) {
                        grid[r][c] = 2;
                        fresh--;
                        queue.offer(new int[]{r, c});
                    }
                }
            }
        }
        return fresh == 0 ? minutes : -1;
    }

}

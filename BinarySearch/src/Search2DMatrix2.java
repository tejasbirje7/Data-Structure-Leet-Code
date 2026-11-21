/**
 * LeetCode 240. Search a 2D Matrix II
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 *
 * PROBLEM
 *   - Input: an m x n matrix and a target.
 *   - Each row is sorted left to right, and each column is sorted top to bottom.
 *   - Unlike LeetCode 74, a row does NOT continue where the row above ended.
 *   - Return: true if target is in the matrix, else false.
 *
 * EXAMPLE
 *   Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]],
 *          target = 5
 *   Output: true
 *
 * HOW TO THINK
 *   - Clue: rows AND columns sorted, but the matrix is not one sorted list, so one flat binary
 *     search doesn't work.
 *   - Technique: staircase search from the top-right corner. That cell is the largest in its row
 *     and the smallest in its column, so one comparison throws away a whole row or column:
 *       v > target -> every cell below v in this column is even bigger: drop the column (c--).
 *       v < target -> every cell left of v in this row is even smaller: drop the row (r++).
 *   - Rule that stays true: if target is in the matrix, it is in rows r..m-1 and columns 0..c.
 *   - Decision: v == target -> true. v > target -> c--. v < target -> r++.
 *   - Return: false once r == m or c < 0 (nothing left to search).
 *   - Why top-right (or bottom-left): from the top-left both moves make v bigger, so a
 *     comparison can't tell which way to go.
 *
 * STEPS
 *   1. r = 0, c = n - 1 (top-right corner).
 *   2. While r < m and c >= 0: v = matrix[r][c].
 *   3. Equal -> true. Bigger -> c--. Smaller -> r++.
 *   4. Return false.
 *
 * DRY RUN   official matrix, target = 5   (r and c are values before the step's action)
 *
 *     1  4  7 11 15
 *     2  5  8 12 19
 *     3  6  9 16 22
 *    10 13 14 17 24
 *    18 21 23 26 30
 *
 *    r | c | v  | compare  | action
 *   ---+---+----+----------+--------------------
 *    0 | 4 | 15 | 15 > 5   | c = 3 (drop col 4)
 *    0 | 3 | 11 | 11 > 5   | c = 2 (drop col 3)
 *    0 | 2 |  7 |  7 > 5   | c = 1 (drop col 2)
 *    0 | 1 |  4 |  4 < 5   | r = 1 (drop row 0)
 *    1 | 1 |  5 | equal    | return true
 *
 *   Returns true.
 *
 * EDGE CASES
 *   official matrix, 20    -> false   walks off the bottom (r == m)
 *   official matrix, 0     -> false   smaller than everything: walks off the left (c < 0)
 *   official matrix, 18    -> true    bottom-left corner: a long walk using both moves
 *   [[5]], 5               -> true    one cell
 *   [[1,2,3]], 2           -> true    one row: only c-- moves
 *   [[1],[2],[3]], 3       -> true    one column: only r++ moves
 *
 * COMMON MISTAKES
 *   - Starting at the top-left: both neighbours are bigger, so "too small" doesn't say where to go.
 *   - Flattening the matrix like LeetCode 74: rows overlap in value, e.g. 3 (row 2) < 4 (row 0),
 *     so the flat list is not sorted.
 *   - Binary search on every row: correct, but O(m log n) instead of O(m + n).
 *
 * COMPLEXITY
 *   O(m + n) time (each step drops a row or a column), O(1) extra space.
 *
 * RELATED
 *   LeetCode 74 (Search2DMatrix, BinarySearch/): rows continue each other, so the matrix is one
 *     sorted list and a flat binary search works in O(log(m * n)).
 *   LeetCode 704 (BinarySearch, BinarySearch/): the same "drop the part that can't hold target"
 *     idea, one half at a time instead of one row or column.
 */
public class Search2DMatrix2 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int r = 0;
        int c = matrix[0].length - 1;
        while (r < m && c >= 0) {
            int v = matrix[r][c];
            if (v == target) {
                return true;
            } else if (v > target) {
                c--;
            } else {
                r++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Search2DMatrix2 sol = new Search2DMatrix2();
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        System.out.println(sol.searchMatrix(matrix, 5));                     // Expected: true
        System.out.println(sol.searchMatrix(matrix, 20));                    // Expected: false
        System.out.println(sol.searchMatrix(matrix, 0));                     // Expected: false
        System.out.println(sol.searchMatrix(matrix, 18));                    // Expected: true
        System.out.println(sol.searchMatrix(new int[][]{{5}}, 5));           // Expected: true
        System.out.println(sol.searchMatrix(new int[][]{{1, 2, 3}}, 2));     // Expected: true
        System.out.println(sol.searchMatrix(new int[][]{{1}, {2}, {3}}, 3)); // Expected: true
    }
}

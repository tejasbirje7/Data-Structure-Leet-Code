/**
 * LeetCode 36. Valid Sudoku
 * https://leetcode.com/problems/valid-sudoku/
 *
 * PROBLEM
 *   - Input: a 9 x 9 board of chars '1'-'9' or '.' (empty).
 *   - Return: true if no filled digit repeats in any row, any column, or any of the nine 3 x 3 boxes.
 *   - Only filled cells are checked. The board does not have to be solvable.
 *
 * EXAMPLE
 *   Input: board =
 *     [["8","3",".",".","7",".",".",".","."]
 *     ,["6",".",".","1","9","5",".",".","."]
 *     ,[".","9","8",".",".",".",".","6","."]
 *     ,["8",".",".",".","6",".",".",".","3"]
 *     ,["4",".",".","8",".","3",".",".","1"]
 *     ,["7",".",".",".","2",".",".",".","6"]
 *     ,[".","6",".",".",".",".","2","8","."]
 *     ,[".",".",".","4","1","9",".",".","5"]
 *     ,[".",".",".",".","8",".",".","7","9"]]
 *   Output: false
 *   Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8.
 *     Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 *
 * HOW TO THINK
 *   - Clue: "no repeats" in a group -> "have I seen this before?" -> a seen-set per group.
 *   - Technique: hashing with fixed-size boolean tables: rows[9][9], cols[9][9], boxes[9][9],
 *     indexed by group number and digit. One pass over the board.
 *   - Rule that stays true: rows[r][d] is true if digit d+1 was already seen in row r (same for cols and boxes).
 *   - Decision: skip '.'. For a digit, box = (r / 3) * 3 + c / 3.
 *     Already seen in its row, column or box -> return false. Otherwise mark all three.
 *   - Return: true if the scan finishes with no repeat.
 *
 * STEPS
 *   1. Make rows, cols, boxes as 9 x 9 boolean tables.
 *   2. For every cell (r, c): skip '.'; d = ch - '1'; b = (r / 3) * 3 + c / 3.
 *   3. If rows[r][d] or cols[c][d] or boxes[b][d]: return false.
 *   4. Mark all three true. After the loop, return true.
 *
 * DRY RUN   input = the board above (row by row; '.' cells are skipped and not shown)
 *
 *    cell  | digit | box | seen in row / col / box ? | action
 *   -------+-------+-----+---------------------------+-------------------------
 *    (0,0) |   8   |  0  | no / no / no              | mark
 *    (0,1) |   3   |  0  | no / no / no              | mark
 *    (0,4) |   7   |  1  | no / no / no              | mark
 *    (1,0) |   6   |  0  | no / no / no              | mark
 *    (1,3) |   1   |  1  | no / no / no              | mark
 *    (1,4) |   9   |  1  | no / no / no              | mark
 *    (1,5) |   5   |  1  | no / no / no              | mark
 *    (2,1) |   9   |  0  | no / no / no              | mark (9 in box 1 doesn't matter)
 *    (2,2) |   8   |  0  | no / no / yes (from 0,0)  | return false
 *
 *   Returns false. Box 0 holds 8, 3, 6, 9 when the second 8 arrives.
 *   (Column 0 also repeats 8 at (3,0), but the scan stops first.)
 *
 * EDGE CASES
 *   official example 1 (5 in the corner) -> true    no repeats anywhere
 *   all '.'                              -> true    nothing to check
 *   same digit twice in one row          -> false   caught by rows[][]
 *   same digit in (0,0) and (8,8)        -> true    different row, column and box
 *   valid but unsolvable board           -> true    only repeats are checked, not solvability
 *
 * COMMON MISTAKES
 *   - Box index r / 3 + c / 3: boxes (0,1) and (1,0) both become 1. Use (r / 3) * 3 + c / 3.
 *   - Index ch - '0' into a size-9 array: '9' gives index 9, out of bounds. Use ch - '1' (or size 10).
 *   - Forgetting to skip '.': every '.' looks like a repeat.
 *   - Trying to solve the board: not asked; it only checks the filled cells.
 *
 * COMPLEXITY
 *   O(81) = O(1) time and space for a fixed 9 x 9 board (O(n^2) for an n x n board).
 *
 * RELATED
 *   LeetCode 217 (ContainsDuplicate, HashMap/): the same seen-set check, but for one group instead of 27.
 *   LeetCode 73 (SetMatrixZeroes, ArraysAndStrings/): also tracks facts per row and per column of a matrix.
 */
public class ValidSudoku {

    public static void main(String[] args) {
        char[][] board = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(isValidSudoku(board));
    }

    public static boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') {
                    continue;
                }
                int d = ch - '1';
                int b = (r / 3) * 3 + c / 3;
                if (rows[r][d] || cols[c][d] || boxes[b][d]) {
                    return false;
                }
                rows[r][d] = true;
                cols[c][d] = true;
                boxes[b][d] = true;
            }
        }
        return true;
    }

}

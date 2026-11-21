/**
 * LeetCode 37. Sudoku Solver
 * https://leetcode.com/problems/sudoku-solver/
 *
 * PROBLEM
 *   - Input: a 9 x 9 board of chars, digits '1'..'9' or '.' for an empty cell.
 *   - Fill every empty cell in place so each row, each column and each 3 x 3 box holds 1..9 once.
 *   - The puzzle is guaranteed to have exactly one solution. Return nothing (void).
 *
 * EXAMPLE
 *   Input: board =
 *     53..7....
 *     6..195...
 *     .98....6.
 *     8...6...3
 *     4..8.3..1
 *     7...2...6
 *     .6....28.
 *     ...419..5
 *     ....8..79
 *   Output:
 *     534678912
 *     672195348
 *     198342567
 *     859761423
 *     426853791
 *     713924856
 *     961537284
 *     287419635
 *     345286179
 *   Explanation: The input board is shown above and the only valid solution is shown below.
 *   (LeetCode shows each row as an array like ["5","3",".",".","7",".",".",".","."].)
 *
 * HOW TO THINK
 *   - Clue: "fill the empty cells" under rules, with no formula = try a digit, undo if stuck: backtracking.
 *   - Technique: walk the 81 cells in order (cell = r * 9 + c); at an empty cell try 1..9.
 *   - O(1) validity check: three used tables, filled once from the given digits.
 *       rows[r][d], cols[c][d], boxes[b][d] -> digit d is already in row r / column c / box b.
 *       Box index b = (r / 3) * 3 + c / 3.
 *   - Rule that stays true: every cell before `cell` is filled, no rule is broken,
 *     and the three tables match the board exactly.
 *   - Decision: cell == 81 -> solved, return true.
 *               cell already filled -> go to cell + 1.
 *               empty -> for d = 1..9: skip if used in row, column or box; else write it, mark, recurse;
 *               if the recursion returns true, return true at once (keep the digit);
 *               otherwise erase, unmark and try the next d. No d works -> return false (caller undoes).
 *   - Return: the board itself is the answer; the boolean only tells callers to stop.
 *
 * STEPS
 *   1. Mark rows/cols/boxes for every given digit.
 *   2. solve(cell = 0).
 *   3. If cell == 81 return true. If board[r][c] is a digit, return solve(cell + 1).
 *   4. For d = 1..9: skip if used; else write d, mark the 3 tables, if solve(cell + 1) return true;
 *      else write '.', unmark.
 *   5. Return false (no digit fits this cell with the choices made before it).
 *
 * DRY RUN   board = the solved board above with 3 cells emptied: (0,0) (was 5), (0,7) (was 1), (2,0) (was 1)
 *
 *    row 0 = .346789.2      column 0 = . 6 . 8 4 7 9 2 3      column 7 = . 4 6 2 9 5 8 3 7
 *
 *    cell  | digits tried (why blocked)                  | action                     | board change
 *   -------+---------------------------------------------+----------------------------+--------------
 *    (0,0) | 1 free                                      | place 1, go on             | (0,0) = 1
 *    (0,7) | 1,2,3,4 row; 5 col; 6,7,8,9 row             | no digit fits, return false |
 *    (0,0) | (1 failed) 2,3,4 row; 5 free                | undo 1, place 5, go on     | (0,0) = 5
 *    (0,7) | 1 free                                      | place 1, go on             | (0,7) = 1
 *    (2,0) | 1 free                                      | place 1, go on             | (2,0) = 1
 *    81    | all cells filled                            | return true up the chain   |
 *
 *   The board ends as the solved board above. The wrong first guess (1 at (0,0)) took the 1 that
 *   (0,7) needed, so (0,7) had no digit left and the search came back to change (0,0).
 *
 * EDGE CASES
 *   Board with no '.'                 -> unchanged   walks to cell 81 and returns true
 *   Board with one '.'                -> filled      exactly one digit fits
 *   The LeetCode example (51 '.')     -> solved      many guesses and undos, still fast
 *   A board with no solution          -> left as given (every write is undone); LeetCode never sends one
 *
 * COMMON MISTAKES
 *   - Wrong box index: it is (r / 3) * 3 + c / 3, not r / 3 + c / 3 (that maps 9 boxes into 0..4).
 *   - Not returning true right away after a successful recursion: the loop goes on, undoes the
 *     solution and the board ends with '.' again.
 *   - Forgetting to unmark the tables when undoing: later digits look used and the solver fails.
 *   - Checking validity by scanning the row, column and box each time: correct but 27 reads per check.
 *
 * COMPLEXITY
 *   O(9^E) time in the worst case (E = empty cells; each can try 9 digits), much less in practice.
 *   O(1) extra space: 3 fixed 9 x 10 tables and at most 81 levels of recursion.
 *
 * RELATED
 *   LeetCode 36 (ValidSudoku, ArraysAndStrings/): the same row/column/box checks, but only to validate.
 *   LeetCode 51 (NQueens, Backtracking/): same "place, mark, recurse, unmark" with O(1) used-arrays.
 */
public class SudokuSolver {

    public static void main(String[] args) {
        char[][] board = toBoard(
                ".346789.2",
                "672195348",
                ".98342567",
                "859761423",
                "426853791",
                "713924856",
                "961537284",
                "287419635",
                "345286179");
        solveSudoku(board);
        print(board);
    }

    public static void solveSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][] boxes = new boolean[9][10];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') continue;
                int d = board[r][c] - '0';
                rows[r][d] = cols[c][d] = boxes[(r / 3) * 3 + c / 3][d] = true;
            }
        }
        solve(board, 0, rows, cols, boxes);
    }

    private static boolean solve(char[][] board, int cell, boolean[][] rows, boolean[][] cols, boolean[][] boxes) {
        if (cell == 81) return true;
        int r = cell / 9;
        int c = cell % 9;
        if (board[r][c] != '.') return solve(board, cell + 1, rows, cols, boxes);
        int b = (r / 3) * 3 + c / 3;
        for (int d = 1; d <= 9; d++) {
            if (rows[r][d] || cols[c][d] || boxes[b][d]) continue;
            board[r][c] = (char) ('0' + d);
            rows[r][d] = cols[c][d] = boxes[b][d] = true;
            if (solve(board, cell + 1, rows, cols, boxes)) return true;
            board[r][c] = '.';
            rows[r][d] = cols[c][d] = boxes[b][d] = false;
        }
        return false;
    }

    private static char[][] toBoard(String... lines) {
        char[][] board = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) board[i] = lines[i].toCharArray();
        return board;
    }

    private static void print(char[][] board) {
        for (char[] row : board) System.out.println(new String(row));
    }
}

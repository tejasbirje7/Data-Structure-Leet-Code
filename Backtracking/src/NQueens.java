import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 51. N-Queens
 * https://leetcode.com/problems/n-queens/
 *
 * PROBLEM
 *   - Input: an int n (1..9).
 *   - Place n queens on an n x n board so no two attack each other
 *     (no two share a row, a column or a diagonal).
 *   - Return: every distinct board, each as n strings where 'Q' is a queen and '.' is empty.
 *
 * EXAMPLE
 *   Input: n = 4
 *   Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 *   Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 *
 * HOW TO THINK
 *   - Clue: "return all distinct solutions" + placement rules = try every choice, so backtracking.
 *   - Technique: exactly one queen per row, so place row by row and only choose the column.
 *   - O(1) attack check: three boolean arrays.
 *       cols[c]           -> column c is taken.
 *       diag[r - c + n-1] -> the "\" diagonal is taken (r - c is the same along it; + n-1 keeps it >= 0).
 *       anti[r + c]       -> the "/" diagonal is taken (r + c is the same along it).
 *   - Rule that stays true: rows 0..row-1 each hold one queen, none attacking another,
 *     and the three arrays mark exactly those queens.
 *   - Decision: row == n -> all placed, save a copy of the board.
 *               else for each col: if any of the three is taken, skip;
 *               else place, mark, recurse on row + 1, then unmark and remove.
 *   - Return: result. A row where every column is blocked returns with nothing (a dead end).
 *
 * STEPS
 *   1. Fill an n x n char board with '.'; make cols[n], diag[2n-1], anti[2n-1].
 *   2. place(row = 0).
 *   3. If row == n, turn each board row into a String and add the list to result.
 *   4. For col = 0..n-1: skip if cols/diag/anti is taken; else set 'Q' and mark all three,
 *      call place(row + 1), then set '.' and unmark all three.
 *   5. Return result.
 *
 * DRY RUN   n = 4   (queens = queen column per row so far; "\" = diag, "/" = anti)
 *
 *    row | columns tried (why blocked)                | action                 | queens
 *   -----+--------------------------------------------+------------------------+-----------
 *     0  | 0 free                                     | place (0,0)            | [0]
 *     1  | 0 col, 1 \, 2 free                         | place (1,2)            | [0,2]
 *     2  | 0 col, 1 /, 2 col, 3 \                     | dead end, remove (1,2) | [0]
 *     1  | 3 free                                     | place (1,3)            | [0,3]
 *     2  | 0 col, 1 free                              | place (2,1)            | [0,3,1]
 *     3  | 0 col, 1 col, 2 \, 3 col                   | dead end, remove (2,1) | [0,3]
 *     2  | 2 \, 3 col                                 | dead end, remove (1,3) | [0]
 *     1  | (no columns left)                          | dead end, remove (0,0) | []
 *     0  | 1 free                                     | place (0,1)            | [1]
 *     1  | 0 /, 1 col, 2 \, 3 free                    | place (1,3)            | [1,3]
 *     2  | 0 free                                     | place (2,0)            | [1,3,0]
 *     3  | 0 col, 1 col, 2 free                       | place (3,2)            | [1,3,0,2]
 *     4  | row == n                                   | save solution 1        | [1,3,0,2]
 *     3  | 3 col                                      | remove (3,2), (2,0)    | [1,3]
 *     2  | 1 col, 2 /, 3 col                          | dead end, remove (1,3) | [1]
 *     1  | (no columns left)                          | dead end, remove (0,1) | []
 *     0  | 2 free                                     | place (0,2)            | [2]
 *     1  | 0 free                                     | place (1,0)            | [2,0]
 *     2  | 0 col, 1 \, 2 col, 3 free                  | place (2,3)            | [2,0,3]
 *     3  | 0 col, 1 free                              | place (3,1)            | [2,0,3,1]
 *     4  | row == n                                   | save solution 2        | [2,0,3,1]
 *     3  | 2 col, 3 col                               | remove (3,1), (2,3)    | [2,0]
 *     2  | (no columns left)                          | remove (1,0)           | [2]
 *     1  | 1 /, 2 col, 3 \                            | dead end, remove (0,2) | []
 *     0  | 3 free                                     | place (0,3)            | [3]
 *     1  | 0 free                                     | place (1,0)            | [3,0]
 *     2  | 0 col, 1 \, 2 free                         | place (2,2)            | [3,0,2]
 *     3  | 0 col, 1 /, 2 col, 3 col                   | dead end, remove (2,2) | [3,0]
 *     2  | 3 col                                      | dead end, remove (1,0) | [3]
 *     1  | 1 free                                     | place (1,1)            | [3,1]
 *     2  | 0 /, 1 col, 2 \, 3 col                     | dead end, remove (1,1) | [3]
 *     1  | 2 /, 3 col                                 | dead end, remove (0,3) | []
 *     0  | (no columns left)                          | done                   | []
 *   (When several reasons block a cell, only the first one the code checks is shown: col, then \, then /.)
 *
 *   Returns [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]].
 *   Solution 1 = queens [1,3,0,2], solution 2 = queens [2,0,3,1].
 *
 * EDGE CASES
 *   n = 1   -> [["Q"]]   one queen, nothing to attack
 *   n = 2   -> []        every second-row cell is attacked
 *   n = 3   -> []        no arrangement works
 *   n = 4   -> 2 boards  the dry run
 *   n = 8   -> 92 boards the classic puzzle
 *
 * COMMON MISTAKES
 *   - Indexing diag by r - c without + n - 1: negative index, ArrayIndexOutOfBoundsException.
 *   - Using diag size n instead of 2n - 1: there are 2n - 1 diagonals in each direction.
 *   - Saving the char[][] itself: later backtracking erases it; build new Strings when saving.
 *   - Forgetting to unmark the three arrays after the call: later branches see ghost queens.
 *
 * COMPLEXITY
 *   O(n!) time roughly (row k has at most n - k free columns), plus O(n^2) to copy each solution.
 *   O(n^2) extra space for the board, O(n) for the arrays and the recursion.
 *
 * RELATED
 *   LeetCode 37 (SudokuSolver, Backtracking/): same "place, mark, recurse, unmark" with O(1)
 *     used-arrays, but for digits in cells instead of queens in rows.
 *   LeetCode 46 (Permutations, Backtracking/): a queen per row in distinct columns is a permutation
 *     of columns; N-Queens adds the diagonal check.
 */
public class NQueens {

    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        place(result, board, 0, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1]);
        return result;
    }

    private static void place(List<List<String>> result, char[][] board, int row,
                              boolean[] cols, boolean[] diag, boolean[] anti) {
        int n = board.length;
        if (row == n) {
            List<String> snapshot = new ArrayList<>();
            for (char[] r : board) snapshot.add(new String(r));
            result.add(snapshot);
            return;
        }
        for (int col = 0; col < n; col++) {
            int d = row - col + n - 1;
            int a = row + col;
            if (cols[col] || diag[d] || anti[a]) continue;
            board[row][col] = 'Q';
            cols[col] = diag[d] = anti[a] = true;
            place(result, board, row + 1, cols, diag, anti);
            board[row][col] = '.';
            cols[col] = diag[d] = anti[a] = false;
        }
    }
}

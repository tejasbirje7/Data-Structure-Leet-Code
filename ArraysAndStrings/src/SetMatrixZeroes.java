import java.util.Arrays;

/**
 * LeetCode 73. Set Matrix Zeroes
 * https://leetcode.com/problems/set-matrix-zeroes/
 *
 * PROBLEM
 *   - Input: an m x n integer matrix.
 *   - Return: nothing; if a cell is 0, set its whole row and whole column to 0.
 *   - Constraints: in place. Follow-up: O(1) extra space.
 *
 * EXAMPLE
 *   Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 *   Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 * HOW TO THINK
 *   - Clue: "in place" + "O(1) space" -> you can't keep a separate list of zero rows and columns,
 *     so store those marks inside the matrix itself.
 *   - Technique: use row 0 and column 0 as marker arrays.
 *     matrix[r][0] = 0 means "row r must be zeroed"; matrix[0][c] = 0 means "column c must be zeroed".
 *     Two booleans remember whether row 0 and column 0 had their own zeros, before they get overwritten by marks.
 *   - Rule that stays true: after the marking pass, a 0 in row 0 or column 0 is a mark;
 *     the booleans alone say whether row 0 and column 0 themselves get zeroed.
 *   - Decision: marking pass: inner cell is 0 -> zero its row marker and column marker.
 *     Filling pass: inner cell's row marker or column marker is 0 -> set the cell to 0.
 *   - Return: nothing. Zero row 0 and column 0 last, from the booleans.
 *
 * STEPS
 *   1. firstRowZero = row 0 has a 0. firstColZero = column 0 has a 0.
 *   2. For every inner cell (r >= 1, c >= 1) that is 0: matrix[r][0] = 0, matrix[0][c] = 0.
 *   3. For every inner cell: if matrix[r][0] == 0 or matrix[0][c] == 0, set it to 0.
 *   4. If firstRowZero, zero row 0. If firstColZero, zero column 0.
 *
 * DRY RUN   input = [[1,2,3,4],[5,0,7,8],[0,10,11,12]]   (matrix shown after the step)
 *
 *    step | what happens                                   | matrix after
 *   ------+------------------------------------------------+--------------------------------------
 *     1   | row 0 = 1 2 3 4: no zero  -> firstRowZero = F  | [[1,2,3,4],[5,0,7,8],[0,10,11,12]]
 *     1   | col 0 = 1 5 0: has a zero -> firstColZero = T  | (unchanged)
 *     2   | (1,1) is 0: mark matrix[1][0], matrix[0][1]    | [[1,0,3,4],[0,0,7,8],[0,10,11,12]]
 *     2   | other inner cells are not 0: no marks          | (unchanged)
 *     3   | row 1: marker matrix[1][0] = 0 -> zero c 1..3  | [[1,0,3,4],[0,0,0,0],[0,10,11,12]]
 *     3   | row 2: marker matrix[2][0] = 0 -> zero c 1..3  | [[1,0,3,4],[0,0,0,0],[0,0,0,0]]
 *     4   | firstRowZero = F: row 0 kept                   | (unchanged)
 *     4   | firstColZero = T: zero column 0                | [[0,0,3,4],[0,0,0,0],[0,0,0,0]]
 *
 *   matrix[2][0] was already 0 in the input, so it works as row 2's marker without being written.
 *   Final state: [[0,0,3,4],[0,0,0,0],[0,0,0,0]].
 *
 * EDGE CASES
 *   [[1,1,1],[1,0,1],[1,1,1]]       -> [[1,0,1],[0,0,0],[1,0,1]]           one inner zero, flags stay false
 *   [[0,1,2,0],[3,4,5,2],[1,3,1,5]] -> [[0,0,0,0],[0,4,5,0],[0,3,1,0]]     zeros only in row 0: both flags true
 *   [[0]]                           -> [[0]]                               single cell
 *   [[1,0]]                         -> [[0,0]]                             single row: only flags do the work
 *   no zeros at all                 -> unchanged                           no marks, flags false
 *
 * COMMON MISTAKES
 *   - Zeroing rows and columns as soon as a 0 is found: the new zeros spread and wipe the whole matrix.
 *     Mark first, fill in a second pass.
 *   - Checking row 0 and column 0 for zeros after the marking pass: the marks look like real zeros.
 *     Compute the two booleans first.
 *   - Zeroing row 0 or column 0 before the filling pass: every marker becomes 0 and every cell gets zeroed.
 *     Do row 0 and column 0 last.
 *
 * COMPLEXITY
 *   O(m * n) time (three passes over the matrix), O(1) extra space.
 *
 * RELATED
 *   LeetCode 48 (RotateImage, ArraysAndStrings/): another in-place matrix change with O(1) extra space.
 *   LeetCode 238 (ProductExceptSelf, ArraysAndStrings/): also reuses an existing array as storage to stay at O(1) space.
 */
public class SetMatrixZeroes {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 0, 7, 8}, {0, 10, 11, 12}};
        setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;
        for (int c = 0; c < n; c++) {
            if (matrix[0][c] == 0) {
                firstRowZero = true;
            }
        }
        for (int r = 0; r < m; r++) {
            if (matrix[r][0] == 0) {
                firstColZero = true;
            }
        }
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (matrix[r][c] == 0) {
                    matrix[r][0] = 0;
                    matrix[0][c] = 0;
                }
            }
        }
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (matrix[r][0] == 0 || matrix[0][c] == 0) {
                    matrix[r][c] = 0;
                }
            }
        }
        if (firstRowZero) {
            for (int c = 0; c < n; c++) {
                matrix[0][c] = 0;
            }
        }
        if (firstColZero) {
            for (int r = 0; r < m; r++) {
                matrix[r][0] = 0;
            }
        }
    }

}

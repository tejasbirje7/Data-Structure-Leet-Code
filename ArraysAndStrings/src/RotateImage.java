import java.util.Arrays;

/**
 * LeetCode 48. Rotate Image
 * https://leetcode.com/problems/rotate-image/
 *
 * PROBLEM
 *   - Input: an n x n matrix (1 <= n <= 20).
 *   - Return: nothing; rotate the matrix 90 degrees clockwise.
 *   - Constraints: in place. Allocating another 2D matrix is not allowed.
 *
 * EXAMPLE
 *   Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 *   Output: [[7,4,1],[8,5,2],[9,6,3]]
 *
 * HOW TO THINK
 *   - Clue: "rotate 90 degrees" + "in place" -> build the rotation out of moves that only swap cells.
 *   - Technique: clockwise rotation = transpose (flip over the main diagonal) + reverse each row.
 *     Cell (r, c) goes to (c, r) after the transpose, then to (c, n-1-r) after the row reverse,
 *     which is exactly where a clockwise turn sends it.
 *   - Rule that stays true: during the transpose, only cells above the diagonal (j > i) start a swap,
 *     so every pair is swapped exactly once.
 *   - Decision: no branching. Transpose: swap (i, j) with (j, i) for j > i.
 *     Reverse: two pointers from both ends of each row, swap and move inward.
 *   - Return: nothing; the matrix itself is the answer.
 *
 * STEPS
 *   1. For i = 0 .. n-1, for j = i+1 .. n-1: swap matrix[i][j] and matrix[j][i].
 *   2. For each row: left = 0, right = n-1.
 *   3. While left < right: swap row[left] and row[right], left++, right--.
 *
 * DRY RUN   input = [[1,2,3],[4,5,6],[7,8,9]]   (matrix shown after the swap)
 *
 *    phase     | swap                     | matrix after
 *   -----------+--------------------------+---------------------------
 *    transpose | (0,1) <-> (1,0): 2 and 4 | [[1,4,3],[2,5,6],[7,8,9]]
 *    transpose | (0,2) <-> (2,0): 3 and 7 | [[1,4,7],[2,5,6],[3,8,9]]
 *    transpose | (1,2) <-> (2,1): 6 and 8 | [[1,4,7],[2,5,8],[3,6,9]]
 *    reverse   | row 0: 1 and 7           | [[7,4,1],[2,5,8],[3,6,9]]
 *    reverse   | row 1: 2 and 8           | [[7,4,1],[8,5,2],[3,6,9]]
 *    reverse   | row 2: 3 and 9           | [[7,4,1],[8,5,2],[9,6,3]]
 *
 *   Diagonal cells (1, 5, 9) and the middle column never move in their phase.
 *   Final state: [[7,4,1],[8,5,2],[9,6,3]].
 *
 * EDGE CASES
 *   [[1]]             -> [[1]]             nothing to swap
 *   [[1,2],[3,4]]     -> [[3,1],[4,2]]     smallest case with real swaps
 *   [[5,5],[5,5]]     -> [[5,5],[5,5]]     all values equal: looks unchanged
 *   4 x 4 matrix      -> works the same    even n has no middle column, reverse still meets in the middle
 *
 * COMMON MISTAKES
 *   - Inner transpose loop starting at j = 0: each pair is swapped twice and the matrix ends unchanged.
 *     Start at j = i + 1.
 *   - Reversing each column instead of each row: gives a counter-clockwise rotation.
 *   - Copying into a new n x n matrix: correct output but breaks the in-place rule.
 *
 * COMPLEXITY
 *   O(n^2) time (every cell is touched a constant number of times), O(1) extra space.
 *
 * RELATED
 *   LeetCode 189 (RotateArray, ArraysAndStrings/): also builds a rotation out of reversals, but on a 1-D array.
 *   LeetCode 54 (SpiralMatrix, ArraysAndStrings/): walks the same matrix layer by layer instead of swapping.
 */
public class RotateImage {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int[] row : matrix) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = row[left];
                row[left] = row[right];
                row[right] = temp;
                left++;
                right--;
            }
        }
    }

}

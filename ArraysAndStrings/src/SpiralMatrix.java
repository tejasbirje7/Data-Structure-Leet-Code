import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 54. Spiral Matrix
 * https://leetcode.com/problems/spiral-matrix/
 *
 * PROBLEM
 *   - Input: an m x n matrix (1 <= m, n <= 10).
 *   - Return: all its elements in spiral order (right, down, left, up, then repeat inward).
 *   - The matrix does not have to be square.
 *
 * EXAMPLE
 *   Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 *   Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * HOW TO THINK
 *   - Clue: "spiral order" -> peel the matrix one outer ring at a time.
 *   - Technique: simulation with four boundaries: top, bottom, left, right.
 *     Walk one side, then move that boundary one step inward.
 *   - Rule that stays true: the rows top .. bottom and columns left .. right are exactly the cells not yet visited.
 *   - Decision: each round walks right along top, down along right, left along bottom, up along left.
 *     Before the last two walks, check top <= bottom and left <= right again:
 *     a single leftover row or column must not be walked twice.
 *   - Return: the list once top > bottom or left > right.
 *
 * STEPS
 *   1. top = 0, bottom = m-1, left = 0, right = n-1.
 *   2. While top <= bottom and left <= right:
 *   3.   Add row top from left to right, top++. Add column right from top to bottom, right--.
 *   4.   If top <= bottom: add row bottom from right to left, bottom--.
 *        If left <= right: add column left from bottom to top, left++.
 *   5. Return the list.
 *
 * DRY RUN   input = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]   (boundaries shown after the walk)
 *
 *    round | walk                 | added      | top bottom left right | result so far
 *   -------+----------------------+------------+-----------------------+----------------------------
 *      1   | right along row 0    | 1 2 3 4    |  1    2     0    3    | 1 2 3 4
 *      1   | down along col 3     | 8 12       |  1    2     0    2    | 1 2 3 4 8 12
 *      1   | left along row 2     | 11 10 9    |  1    1     0    2    | ... 8 12 11 10 9
 *      1   | up along col 0       | 5          |  1    1     1    2    | ... 11 10 9 5
 *      2   | right along row 1    | 6 7        |  2    1     1    2    | ... 9 5 6 7
 *      2   | down along col 2     | (none)     |  2    1     1    1    | rows 2 .. 1 is empty
 *      2   | left along row 1     | skipped    |  2    1     1    1    | top 2 > bottom 1: row 1 already done
 *      2   | up along col 1       | (none)     |  2    1     2    1    | rows 1 .. 2 going up is empty
 *
 *   Loop ends (top > bottom). Returns [1,2,3,4,8,12,11,10,9,5,6,7].
 *
 * EDGE CASES
 *   [[7]]               -> [7]         single cell
 *   [[1,2,3]]           -> [1,2,3]     single row: the "left along bottom" guard stops a second pass
 *   [[1],[2],[3]]       -> [1,2,3]     single column: the "up along left" guard stops a second pass
 *   [[1,2,3],[4,5,6],[7,8,9]] -> [1,2,3,6,9,8,7,4,5]   square: the centre cell comes last
 *
 * COMMON MISTAKES
 *   - Leaving out the two guards: a leftover single row or column gets added twice.
 *   - Forgetting to move a boundary after a walk: the same side is walked forever.
 *   - Using the old boundary in the next walk (e.g. starting "down" at the old top): the corner is added twice.
 *     Move the boundary first, then start the next walk from it.
 *
 * COMPLEXITY
 *   O(m * n) time (each cell is added once), O(1) extra space besides the output list.
 *
 * RELATED
 *   LeetCode 48 (RotateImage, ArraysAndStrings/): also works on a matrix ring by ring, but swaps cells instead of reading them.
 *   LeetCode 6 (ZigzagConversion, ArraysAndStrings/): another walk that flips direction at the edges.
 */
public class SpiralMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(spiralOrder(matrix));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int c = left; c <= right; c++) {
                result.add(matrix[top][c]);
            }
            top++;
            for (int r = top; r <= bottom; r++) {
                result.add(matrix[r][right]);
            }
            right--;
            if (top <= bottom) {
                for (int c = right; c >= left; c--) {
                    result.add(matrix[bottom][c]);
                }
                bottom--;
            }
            if (left <= right) {
                for (int r = bottom; r >= top; r--) {
                    result.add(matrix[r][left]);
                }
                left++;
            }
        }
        return result;
    }

}

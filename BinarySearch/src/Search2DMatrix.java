/**
 * LeetCode 74. Search a 2D Matrix
 * https://leetcode.com/problems/search-a-2d-matrix/
 *
 * PROBLEM
 *   - Input: an m x n matrix and a target.
 *   - Each row is sorted, and the first value of each row is greater than the last value of
 *     the row above it.
 *   - Return: true if target is in the matrix, else false.
 *   - Constraint: O(log(m * n)) time.
 *
 * EXAMPLE
 *   Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 *   Output: true
 *
 * HOW TO THINK
 *   - Clue: "each row sorted" + "first of a row > last of the row above" = reading the rows one
 *     after another gives one sorted list of m * n values.
 *   - Technique: binary search over positions 0 .. m*n - 1 of that imagined list, without building it.
 *     Position p sits at row p / n, column p % n.
 *   - Rule that stays true: if target is in the matrix, its position is inside [lo, hi].
 *   - Decision: v = matrix[mid / n][mid % n].
 *               v == target -> return true.
 *               v <  target -> lo = mid + 1.
 *               v >  target -> hi = mid - 1.
 *   - Return: false once lo > hi.
 *
 * STEPS
 *   1. m = rows, n = columns, lo = 0, hi = m * n - 1.
 *   2. While lo <= hi: mid = lo + (hi - lo) / 2, v = matrix[mid / n][mid % n].
 *   3. Equal -> true. Smaller -> lo = mid + 1. Bigger -> hi = mid - 1.
 *   4. Return false.
 *
 * DRY RUN   matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3   (n = 4)
 *           (lo and hi are values before the step's action)
 *
 *    lo | hi | mid | row,col | v  | compare  | action
 *   ----+----+-----+---------+----+----------+-------------
 *     0 | 11 |  5  |  1,1    | 11 | 11 > 3   | hi = 4
 *     0 |  4 |  2  |  0,2    |  5 | 5 > 3    | hi = 1
 *     0 |  1 |  0  |  0,0    |  1 | 1 < 3    | lo = 1
 *     1 |  1 |  1  |  0,1    |  3 | equal    | return true
 *
 *   Returns true.
 *
 * EDGE CASES
 *   official matrix, 13    -> false   between 11 and 16: lo and hi cross
 *   official matrix, 60    -> true    the very last cell
 *   [[1]], 1               -> true    one cell
 *   [[1]], 2               -> false   one cell, not the target
 *   [[1,3]], 3             -> true    one row: plain binary search
 *   [[1],[3]], 3           -> true    one column: n = 1, so row = p and col = 0
 *
 * COMMON MISTAKES
 *   - Using m instead of n in p / n and p % n: the row length (columns) decides where a row ends.
 *   - Binary search for the row, then for the column: correct, but two searches and more
 *     boundary bugs.
 *   - Using the staircase walk from LeetCode 240: correct, but O(m + n), not O(log(m * n)).
 *
 * COMPLEXITY
 *   O(log(m * n)) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 704 (BinarySearch, BinarySearch/): the same search on a plain array.
 *   LeetCode 240 (Search2DMatrix2, BinarySearch/): rows don't continue each other, so the
 *     matrix isn't one sorted list; walk a staircase from the top-right instead.
 */
public class Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int lo = 0;
        int hi = m * n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int v = matrix[mid / n][mid % n];
            if (v == target) {
                return true;
            } else if (v < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Search2DMatrix sol = new Search2DMatrix();
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};

        System.out.println(sol.searchMatrix(matrix, 3));                  // Expected: true
        System.out.println(sol.searchMatrix(matrix, 13));                 // Expected: false
        System.out.println(sol.searchMatrix(matrix, 60));                 // Expected: true
        System.out.println(sol.searchMatrix(new int[][]{{1}}, 1));        // Expected: true
        System.out.println(sol.searchMatrix(new int[][]{{1}}, 2));        // Expected: false
        System.out.println(sol.searchMatrix(new int[][]{{1, 3}}, 3));     // Expected: true
        System.out.println(sol.searchMatrix(new int[][]{{1}, {3}}, 3));   // Expected: true
    }
}

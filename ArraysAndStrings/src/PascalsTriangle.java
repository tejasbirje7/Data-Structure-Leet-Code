import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 118. Pascal's Triangle
 * https://leetcode.com/problems/pascals-triangle/
 *
 * PROBLEM
 *   - Input: numRows (1 <= numRows <= 30).
 *   - Return: the first numRows rows of Pascal's triangle.
 *   - Each number is the sum of the two numbers directly above it; every row starts and ends with 1.
 *
 * EXAMPLE
 *   Input: numRows = 5
 *   Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 *
 * HOW TO THINK
 *   - Clue: "sum of the two numbers directly above" -> each row is built only from the previous row (simple DP).
 *   - Technique: row r has r+1 values. row[c] = prev[c-1] + prev[c] for the inner positions.
 *   - Rule that stays true: when building row r, rows 0 .. r-1 are finished and already in the triangle.
 *   - Decision: c == 0 or c == r -> 1 (the edges). Otherwise -> prev[c-1] + prev[c].
 *   - Return: the triangle after numRows rows.
 *
 * STEPS
 *   1. triangle = empty list.
 *   2. For r = 0 .. numRows-1: make a new row.
 *   3. For c = 0 .. r: add 1 at the edges, else prev[c-1] + prev[c].
 *   4. Add the row to the triangle. Return the triangle.
 *
 * DRY RUN   input = numRows = 5
 *
 *    r | prev row    | inner sums (c = 1 .. r-1)     | row built
 *   ---+-------------+-------------------------------+-------------
 *    0 | -           | none (edge only)              | [1]
 *    1 | [1]         | none (both cells are edges)   | [1,1]
 *    2 | [1,1]       | c=1: 1+1 = 2                  | [1,2,1]
 *    3 | [1,2,1]     | c=1: 1+2 = 3, c=2: 2+1 = 3    | [1,3,3,1]
 *    4 | [1,3,3,1]   | 1+3 = 4, 3+3 = 6, 3+1 = 4     | [1,4,6,4,1]
 *
 *   Returns [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]].
 *
 * EDGE CASES
 *   numRows = 1    -> [[1]]          only the top edge
 *   numRows = 2    -> [[1],[1,1]]    no inner cells yet
 *   numRows = 30   -> fits in int    largest value is C(29,14) = 77558760
 *
 * COMMON MISTAKES
 *   - Reading prev[c] when c == r: prev has only r values, so it's out of bounds. Treat both edges as 1.
 *   - Reusing one list object for every row: every row in the result ends up the same list.
 *     Create a new list for each row.
 *   - Loop c from 0 to r-1: each row ends up one value short. Row r has r + 1 values.
 *
 * COMPLEXITY
 *   O(numRows^2) time and space (that's the size of the output).
 *
 * RELATED
 *   LeetCode 62 (UniquePaths, DynamicProgramming/): the same "sum of the two cells before me" rule on a grid;
 *     its answers are Pascal's triangle numbers.
 *   LeetCode 70 (ClimbingStairs, DynamicProgramming/): also builds each value from the two before it, in 1-D.
 */
public class PascalsTriangle {

    public static void main(String[] args) {
        System.out.println(generate(5));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int r = 0; r < numRows; r++) {
            List<Integer> row = new ArrayList<>();
            for (int c = 0; c <= r; c++) {
                if (c == 0 || c == r) {
                    row.add(1);
                } else {
                    List<Integer> prev = triangle.get(r - 1);
                    row.add(prev.get(c - 1) + prev.get(c));
                }
            }
            triangle.add(row);
        }
        return triangle;
    }

}

/**
 * LeetCode 6. Zigzag Conversion
 * https://leetcode.com/problems/zigzag-conversion/
 *
 * PROBLEM
 *   - Input: a string s and a number of rows numRows (1 <= numRows <= 1000).
 *   - Write s in a zigzag: down the rows, then diagonally back up, then down again.
 *   - Return: the rows read one after another, top row first.
 *
 * EXAMPLE
 *   Input: s = "PAYPALISHIRING", numRows = 3
 *   Output: "PAHNAPLSIIGYIR"
 *   Explanation:
 *     P   A   H   N
 *     A P L S I I G
 *     Y   I   R
 *
 * HOW TO THINK
 *   - Clue: only the order of characters within each row matters, not the columns or spaces.
 *   - Technique: simulation. Keep one StringBuilder per row and a pointer that bounces between row 0 and the last row.
 *   - Rule that stays true: each char goes to rows[row], and step is +1 going down or -1 going up.
 *   - Decision: after writing a char, flip direction at the edges:
 *     row == 0 -> step = +1; row == numRows-1 -> step = -1. Then row += step.
 *   - Return: all rows joined. If numRows == 1 (or >= length), the zigzag is the string itself.
 *
 * STEPS
 *   1. If numRows == 1 or numRows >= s.length(): return s.
 *   2. Make numRows StringBuilders. row = 0, step = 1.
 *   3. For each char: append to rows[row]; flip step at row 0 or the last row; row += step.
 *   4. Join the rows and return.
 *
 * DRY RUN   input = s = "PAYPALISHIRING", numRows = 3   (step and next row are after the flip)
 *
 *    i  | char | row written | step | next row
 *   ----+------+-------------+------+---------
 *     0 |  P   |     0       |  +1  |    1
 *     1 |  A   |     1       |  +1  |    2
 *     2 |  Y   |     2       |  -1  |    1
 *     3 |  P   |     1       |  -1  |    0
 *     4 |  A   |     0       |  +1  |    1
 *     5 |  L   |     1       |  +1  |    2
 *     6 |  I   |     2       |  -1  |    1
 *     7 |  S   |     1       |  -1  |    0
 *     8 |  H   |     0       |  +1  |    1
 *     9 |  I   |     1       |  +1  |    2
 *    10 |  R   |     2       |  -1  |    1
 *    11 |  I   |     1       |  -1  |    0
 *    12 |  N   |     0       |  +1  |    1
 *    13 |  G   |     1       |  +1  |    2
 *
 *   rows = ["PAHN", "APLSIIG", "YIR"]. Returns "PAHNAPLSIIGYIR".
 *
 * EDGE CASES
 *   "AB", 1          -> "AB"        one row: no zigzag, and the guard avoids row going to 1
 *   "A", 1           -> "A"         single char
 *   "ABC", 5         -> "ABC"       more rows than chars: each char sits alone in its row
 *   "ABCD", 2        -> "ACBD"      two rows: just alternates
 *   "PAYPALISHIRING", 4 -> "PINALSIGYAHRPI"   official example 2
 *
 * COMMON MISTAKES
 *   - No guard for numRows == 1: the pointer moves to row 1, which doesn't exist (index out of bounds).
 *   - Moving the row before flipping the direction: the pointer walks off the top or bottom.
 *   - Concatenating Strings in the loop instead of StringBuilder: O(n^2) copying.
 *
 * COMPLEXITY
 *   O(n) time (each char is written once and read once), O(n) extra space for the row builders.
 *
 * RELATED
 *   LeetCode 54 (SpiralMatrix, ArraysAndStrings/): another walk that changes direction when it hits an edge.
 *   LeetCode 68 (TextJustification, ArraysAndStrings/): also builds the output line by line with StringBuilders.
 */
public class ZigzagConversion {

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int r = 0; r < numRows; r++) {
            rows[r] = new StringBuilder();
        }
        int row = 0;
        int step = 1;
        for (char ch : s.toCharArray()) {
            rows[row].append(ch);
            if (row == 0) {
                step = 1;
            } else if (row == numRows - 1) {
                step = -1;
            }
            row += step;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : rows) {
            result.append(sb);
        }
        return result.toString();
    }

}

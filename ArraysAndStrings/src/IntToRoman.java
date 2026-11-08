import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LeetCode 12. Integer to Roman
 * https://leetcode.com/problems/integer-to-roman/
 *
 * PROBLEM
 *   - Input: an integer num, 1 <= num <= 3999.
 *   - Return: num written as a Roman numeral.
 *   - Subtractive forms: 4 = IV, 9 = IX, 40 = XL, 90 = XC, 400 = CD, 900 = CM.
 *     Each decimal place is converted on its own.
 *
 * EXAMPLE
 *   Input: num = 3749
 *   Output: "MMMDCCXLIX"
 *   Explanation:
 *   3000 = MMM as 1000 (M) + 1000 (M) + 1000 (M)
 *    700 = DCC as 500 (D) + 100 (C) + 100 (C)
 *     40 = XL as 10 (X) less of 50 (L)
 *      9 = IX as 1 (I) less of 10 (X)
 *   Note: 49 is not 1 (I) less of 50 (L) because the conversion is based on decimal places
 *
 * HOW TO THINK
 *   - Clue: a fixed set of symbol values, biggest first -> greedy, like paying with the fewest coins.
 *   - Technique: put the 6 subtractive pairs (CM, CD, XC, XL, IX, IV) in the table as if they were
 *     normal symbols. Then plain greedy works: take the largest value that fits, as many times as it fits.
 *   - Order matters: the table is a LinkedHashMap filled from 1000 down to 1, so it is read back in
 *     that decreasing order. A plain HashMap has no order and would break this.
 *   - Rule that stays true: (value of roman so far) + num = the original number.
 *   - Decision: num / value > 0 -> append the symbol (num / value) times, then num = num % value.
 *               otherwise -> skip this symbol.
 *   - Return: roman.toString() after all 13 symbols (num is 0 by then).
 *
 * STEPS
 *   1. Build the 13 symbols, largest first:
 *      M 1000, CM 900, D 500, CD 400, C 100, XC 90, L 50, XL 40, X 10, IX 9, V 5, IV 4, I 1.
 *   2. Loop over the symbols in that order.
 *   3. If num / value > 0: append the symbol count = num / value times, then num = num % value.
 *   4. Return the built string.
 *
 * DRY RUN   num = 3749   (num is the value before the step; roman is after)
 *
 *    symbol | value | num  | count | action      | roman
 *   --------+-------+------+-------+-------------+------------
 *      M    | 1000  | 3749 |   3   | append MMM  | MMM
 *      CM   |  900  |  749 |   0   | skip        | MMM
 *      D    |  500  |  749 |   1   | append D    | MMMD
 *      CD   |  400  |  249 |   0   | skip        | MMMD
 *      C    |  100  |  249 |   2   | append CC   | MMMDCC
 *      XC   |   90  |   49 |   0   | skip        | MMMDCC
 *      L    |   50  |   49 |   0   | skip        | MMMDCC
 *      XL   |   40  |   49 |   1   | append XL   | MMMDCCXL
 *      X    |   10  |    9 |   0   | skip        | MMMDCCXL
 *      IX   |    9  |    9 |   1   | append IX   | MMMDCCXLIX
 *      V    |    5  |    0 |   0   | skip        | MMMDCCXLIX
 *      IV   |    4  |    0 |   0   | skip        | MMMDCCXLIX
 *      I    |    1  |    0 |   0   | skip        | MMMDCCXLIX
 *
 *   Returns "MMMDCCXLIX".
 *
 * EDGE CASES
 *   58     -> "LVIII"       50 = L, 8 = VIII
 *   1994   -> "MCMXCIV"     1000 = M, 900 = CM, 90 = XC, 4 = IV
 *   1      -> "I"           smallest input
 *   4      -> "IV"          the subtractive pair is taken as one symbol
 *   3999   -> "MMMCMXCIX"   largest input
 *   0      -> ""            nothing fits; outside the constraints (num >= 1)
 *   4000   -> "MMMM"        not a valid numeral; outside the constraints (num <= 3999)
 *
 * COMMON MISTAKES
 *   - Using a HashMap: no order, so smaller symbols may be used first.
 *     Use a LinkedHashMap, or two arrays (values and symbols) in decreasing order.
 *   - Leaving out the subtractive pairs: 4 becomes IIII and 900 becomes DCCCC.
 *   - Forgetting num = num % value after appending: the same amount gets counted again later.
 *
 * COMPLEXITY
 *   O(1) time and space: always 13 symbols, and at most 15 characters for num <= 3999.
 *
 * RELATED
 *   LeetCode 13 (RomanToInt, ArraysAndStrings/): the reverse; subtract a symbol when a bigger one follows it.
 */
public class IntToRoman {

    public static void main(String[] args) {
        System.out.println(intToRoman(3749));
    }

    public static String intToRoman(int num) {
        Map<String, Integer> m = getStringIntegerMap();

        StringBuilder roman = new StringBuilder();
        for (Map.Entry<String,Integer> e : m.entrySet()) {
            if(num / e.getValue() > 0) {
                int count = num / e.getValue();

                while (count > 0) {
                    roman.append(e.getKey());
                    count--;
                }

                num = num % e.getValue();
            }
        }
        return roman.toString();
    }

    private static Map<String, Integer> getStringIntegerMap() {
        Map<String, Integer> m = new LinkedHashMap<>();
        m.put("M", 1000);
        m.put("CM", 900);
        m.put("D", 500);
        m.put("CD", 400);
        m.put("C", 100);
        m.put("XC", 90);
        m.put("L", 50);
        m.put("XL", 40);
        m.put("X", 10);
        m.put("IX", 9);
        m.put("V", 5);
        m.put("IV",4);
        m.put("I", 1);
        return m;
    }


}

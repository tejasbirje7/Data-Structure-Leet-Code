import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 13. Roman to Integer
 * https://leetcode.com/problems/roman-to-integer/
 *
 * PROBLEM
 *   - Input: a valid Roman numeral s using I=1, V=5, X=10, L=50, C=100, D=500, M=1000.
 *   - Return: its integer value (1 to 3999).
 *   - A smaller symbol written BEFORE a bigger one is subtracted (IV = 4, IX = 9, XL = 40, CM = 900).
 *
 * EXAMPLE
 *   Input: s = "III"
 *   Output: 3
 *   Explanation: III = 3.
 *
 * HOW TO THINK
 *   - Clue: symbols normally go from big to small; a small one in front of a bigger one means "minus".
 *   - Technique: one left-to-right scan with a symbol -> value HashMap, looking one symbol ahead.
 *   - Rule that stays true: number = the value of s[0 .. i-1], where each symbol already counted
 *     as + or - by comparing it with the symbol after it.
 *   - Decision: value(s[i]) < value(s[i+1]) -> subtract value(s[i]).
 *               otherwise (bigger or equal, or the last symbol) -> add value(s[i]).
 *   - Check i + 1 < s.length() first, so the last symbol never looks past the end.
 *   - Return: number after the scan.
 *
 * STEPS
 *   1. Map each Roman symbol to its value. number = 0.
 *   2. Loop i over s.
 *   3. If there is a next symbol and value(s[i]) < value(s[i+1]): number -= value(s[i]).
 *   4. Else: number += value(s[i]).
 *   5. Return number.
 *
 * DRY RUN   s = "MCMXCIV"   (number is after the step)
 *
 *    i | s[i] | value | next value | action              | number
 *   ---+------+-------+------------+---------------------+--------
 *    0 |  M   | 1000  |    100     | bigger, add 1000    |  1000
 *    1 |  C   |  100  |   1000     | 100 < 1000, -100    |   900
 *    2 |  M   | 1000  |     10     | bigger, add 1000    |  1900
 *    3 |  X   |   10  |    100     | 10 < 100, -10       |  1890
 *    4 |  C   |  100  |      1     | bigger, add 100     |  1990
 *    5 |  I   |    1  |      5     | 1 < 5, -1           |  1989
 *    6 |  V   |    5  |      -     | last symbol, add 5  |  1994
 *
 *   Returns 1994 (M + CM + XC + IV = 1000 + 900 + 90 + 4).
 *
 * EDGE CASES
 *   "I"          -> 1      one symbol, it is the last, so it is added
 *   "III"        -> 3      equal neighbours are added
 *   "IV"         -> 4      subtract 1, then add 5
 *   "LVIII"      -> 58     only additions
 *   "MMMCMXCIX"  -> 3999   the largest value
 *   ""           -> 0      the loop never runs (LeetCode guarantees at least one symbol)
 *   "A"          -> crashes (NullPointerException, map has no 'A'); LeetCode guarantees a valid numeral
 *
 * COMMON MISTAKES
 *   - Reading s[i+1] without checking i + 1 < s.length(): crashes on the last symbol.
 *   - Using <= instead of <: "II" would give 0 (-1 + 1); equal symbols must be added.
 *   - Hard-coding only the six subtraction pairs but forgetting one (e.g. CD = 400):
 *     the "smaller before bigger" rule covers them all.
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space (the map always has 7 entries).
 *
 * RELATED
 *   LeetCode 12 (IntToRoman, ArraysAndStrings/): the reverse direction; greedily take the biggest
 *     symbol (including the subtraction pairs like CM and IV) that still fits.
 */
public class RomanToInt {
    public static void main(String[] args) {
        String s = "MCMXCIV";
        System.out.println(romanToInt(s));
    }

    public static int romanToInt(String s) {
        Map<Character, Integer> m = new HashMap<>();

        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);

        int number = 0;
        for (int i=0; i < s.length(); i++) {

            if(i+1 < s.length() && m.get(s.charAt(i)) < m.get(s.charAt(i+1))) {
                number -= m.get(s.charAt(i));
            } else {
                number += m.get(s.charAt(i));
            }
        }

        return number;

    }
}

/**
 * LeetCode 8. String to Integer (atoi)
 * https://leetcode.com/problems/string-to-integer-atoi/
 *
 * PROBLEM
 *   - Input: a string s (0 <= length <= 200) of letters, digits, ' ', '+', '-' and '.'.
 *   - Return: the 32-bit signed integer it starts with, read in this order:
 *     skip leading spaces, read one optional sign, read digits until a non-digit or the end.
 *   - No digits read -> 0. Out of int range -> clamp to Integer.MAX_VALUE or Integer.MIN_VALUE.
 *
 * EXAMPLE
 *   Input: s = " -042"
 *   Output: -42
 *   Explanation:
 *     Step 1: " -042" (leading whitespace is read and ignored)
 *     Step 2: " -042" ('-' is read, so the result should be negative)
 *     Step 3: " -042" ("042" is read in, leading zeros ignored in the result)
 *
 * HOW TO THINK
 *   - Clue: the problem lists the steps in order -> a single left-to-right scan with one index, one phase per step.
 *   - Technique: parsing with an index i, plus an overflow check before each digit is added.
 *   - Rule that stays true: result = the absolute value of the digits read so far, and it always fits in an int.
 *   - Decision: for each digit, check result > (MAX - digit) / 10 first.
 *     True -> result * 10 + digit would pass MAX, so return MAX or MIN by sign. False -> result = result * 10 + digit.
 *   - Return: sign * result. MIN's absolute value is MAX + 1, and the overflow check returns MIN in that case.
 *
 * STEPS
 *   1. Skip ' ' characters.
 *   2. If the next char is '+' or '-': set sign, move past it.
 *   3. While the next char is a digit: if adding it would overflow, return MAX or MIN by sign;
 *      else result = result * 10 + digit.
 *   4. Return sign * result.
 *
 * DRY RUN   input = "  -91283472332"   (result is after the step)
 *
 *    i  | char | phase  | overflow check: result > (MAX - d) / 10 ? | action      | result
 *   ----+------+--------+--------------------------------------------+-------------+-----------
 *     0 | ' '  | space  |                                            | skip        | 0
 *     1 | ' '  | space  |                                            | skip        | 0
 *     2 | '-'  | sign   |                                            | sign = -1   | 0
 *     3 | '9'  | digit  | 0 > 214748363 ? no                         | add         | 9
 *     4 | '1'  | digit  | 9 > 214748364 ? no                         | add         | 91
 *     5 | '2'  | digit  | no                                         | add         | 912
 *     6 | '8'  | digit  | no                                         | add         | 9128
 *     7 | '3'  | digit  | no                                         | add         | 91283
 *     8 | '4'  | digit  | no                                         | add         | 912834
 *     9 | '7'  | digit  | no                                         | add         | 9128347
 *    10 | '2'  | digit  | no                                         | add         | 91283472
 *    11 | '3'  | digit  | 91283472 > 214748364 ? no                  | add         | 912834723
 *    12 | '3'  | digit  | 912834723 > 214748364 ? yes                | clamp       | -
 *
 *   Returns -2147483648 (Integer.MIN_VALUE), because sign = -1.
 *
 * EDGE CASES
 *   "42"              -> 42            plain number
 *   "1337c0d3"        -> 1337          stops at the first non-digit
 *   "words and 987"   -> 0             first non-space is not a sign or digit
 *   "" or "   "       -> 0             nothing to read
 *   "+-12"            -> 0             only one sign is allowed; '-' is not a digit
 *   "00000-42a1234"   -> 0             leading zeros give 0, then '-' stops the read
 *   "2147483648"      -> 2147483647    one past MAX: clamped
 *   "-2147483648"     -> -2147483648   exactly MIN: the overflow check returns MIN, which is also the right value
 *
 * COMMON MISTAKES
 *   - Building the number first and checking overflow after: the int has already wrapped around.
 *     Check before multiplying. (A long also overflows on a 200-digit input, so a long alone is not enough.)
 *   - Using trim() or Character.isWhitespace: only ' ' counts as leading whitespace here.
 *   - Using Character.isDigit: it also accepts non-ASCII digits. Compare with '0' and '9'.
 *   - Accepting a sign after spaces inside the number (" + 1"): stop at the first non-digit after the sign.
 *
 * COMPLEXITY
 *   O(n) time (one pass), O(1) extra space.
 *
 * RELATED
 *   LeetCode 13 (RomanToInt, ArraysAndStrings/): also turns a string into an int with one scan, but with Roman numeral rules.
 *   LeetCode 9 (Pallindrome, Math/): works with a number digit by digit, the reverse direction of this parse.
 */
public class StringToInteger {

    public static void main(String[] args) {
        System.out.println(myAtoi("  -91283472332"));
    }

    public static int myAtoi(String s) {
        int i = 0;
        int n = s.length();
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            if (s.charAt(i) == '-') {
                sign = -1;
            }
            i++;
        }
        int result = 0;
        while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            int digit = s.charAt(i) - '0';
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;
            i++;
        }
        return sign * result;
    }

}

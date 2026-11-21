/**
 * LeetCode 7. Reverse Integer
 * https://leetcode.com/problems/reverse-integer/
 *
 * PROBLEM
 *   - Input: a signed 32-bit int x.
 *   - Return: x with its digits reversed (sign kept), or 0 if the result does not fit in a 32-bit int.
 *   - You may not use 64-bit integers (no long).
 *
 * EXAMPLE
 *   Input: x = 123
 *   Output: 321
 *
 * HOW TO THINK
 *   - Clue: "reverse digits" -> pop the last digit with % 10, push it onto the result with rev * 10 + digit.
 *   - Technique: digit popping, with an overflow check BEFORE each push (because long is not allowed).
 *   - Rule that stays true: rev is the reverse of the digits popped so far, and it always fits in an int.
 *   - Decision before each push:
 *     rev > MAX / 10, or rev == MAX / 10 and digit > 7  -> the push would pass 2147483647, return 0;
 *     rev < MIN / 10, or rev == MIN / 10 and digit < -8 -> the push would pass -2147483648, return 0;
 *     otherwise rev = rev * 10 + digit.
 *   - Negative numbers need no special case: in Java, -123 % 10 = -3 and -123 / 10 = -12.
 *   - Return: rev when x reaches 0.
 *
 * STEPS
 *   1. rev = 0.
 *   2. While x != 0: digit = x % 10; x = x / 10.
 *   3.   If pushing digit would overflow (checks above), return 0.
 *   4.   rev = rev * 10 + digit.
 *   5. Return rev.
 *
 * DRY RUN   input = x = 1534236469   (MAX / 10 = 214748364; x and rev are after the step)
 *
 *    step | digit | x after      | rev before  | overflow check              | action   | rev after
 *   ------+-------+--------------+-------------+-----------------------------+----------+-----------
 *      1  |   9   | 153423646    |          0  | 0 <= 214748364              | push     | 9
 *      2  |   6   | 15342364     |          9  | ok                          | push     | 96
 *      3  |   4   | 1534236      |         96  | ok                          | push     | 964
 *      4  |   6   | 153423       |        964  | ok                          | push     | 9646
 *      5  |   3   | 15342        |       9646  | ok                          | push     | 96463
 *      6  |   2   | 1534         |      96463  | ok                          | push     | 964632
 *      7  |   4   | 153          |     964632  | ok                          | push     | 9646324
 *      8  |   3   | 15           |    9646324  | ok                          | push     | 96463243
 *      9  |   5   | 1            |   96463243  | ok                          | push     | 964632435
 *     10  |   1   | 0            |  964632435  | 964632435 > 214748364       | return 0 |
 *
 *   Returns 0: 9646324351 is bigger than 2147483647.
 *
 * EDGE CASES
 *   0             -> 0             loop never runs
 *   -123          -> -321          the sign comes along through % and /
 *   120           -> 21            trailing zero becomes a leading zero and disappears
 *   1463847412    -> 2147483641    last push has rev == MAX / 10 and digit 1 <= 7: fits
 *   2147483647    -> 0             7463847412 overflows
 *   -2147483648   -> 0             -8463847412 overflows
 *
 * COMMON MISTAKES
 *   - Checking for overflow after the push: the int has already wrapped around, so the check sees garbage.
 *   - Using Math.abs(x) to handle the sign: Math.abs(-2147483648) is still negative.
 *   - Building a string and parsing it: parsing an out-of-range value throws instead of returning 0.
 *
 * COMPLEXITY
 *   O(log10 |x|) time (one step per digit, at most 10), O(1) extra space.
 *
 * RELATED
 *   LeetCode 9 (Pallindrome, Math/): reverses digits the same way, then compares with the original.
 *   LeetCode 8 (StringToInteger, ArraysAndStrings/): builds a number digit by digit with the same overflow check before each push.
 */
public class ReverseInteger {

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }

    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }
            rev = rev * 10 + digit;
        }
        return rev;
    }

}

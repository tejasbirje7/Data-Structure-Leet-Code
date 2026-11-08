/**
 * LeetCode 50. Pow(x, n)
 * https://leetcode.com/problems/powx-n/
 *
 * PROBLEM
 *   - Input: a double x and an int n (n can be negative, down to -2^31).
 *   - Return: x raised to the power n.
 *   - n is up to 2^31, so multiplying x by itself n times is too slow.
 *   - This file has no method: both versions run inside main with int x = 7, n = 5,
 *     and main prints nothing (pow holds 16807.0 at the end of each loop).
 *
 * EXAMPLE
 *   Input: x = 2.00000, n = 10
 *   Output: 1024.00000
 *
 * HOW TO THINK
 *   - Clue: a huge n with "compute the power" = fast exponentiation (binary exponentiation).
 *   - Technique: write n in binary. x^13 = x^8 * x^4 * x^1 because 13 = 1101 in binary.
 *     Keep squaring x (x, x^2, x^4, x^8, ...) and multiply it into pow only when that bit of n is 1.
 *     Another view: 2^7 = 2^2 * 2^2 * 2^2 * 2^1.
 *   - Rule that stays true: pow * x^n = the original answer, at every step of the loop.
 *   - Decision: lowest bit of n is 1 (n is odd) -> pow *= x.
 *               every step -> x *= x and n = n / 2 (drop the lowest bit).
 *   - Negative n: x^-n = (1 / x)^n, so flip both first.
 *   - Return: pow once n reaches 0.
 *   - Two loops in main: (n & 1) and n >>>= 1 are the bitwise forms of n % 2 and n / 2.
 *     The second loop is the same algorithm with % and /, and no negative handling.
 *
 * STEPS
 *   1. If n < 0: n = -n, x = 1 / x.
 *   2. pow = 1.
 *   3. While n != 0: if n is odd, pow *= x.
 *   4.   x *= x; n >>>= 1 (n = n / 2).
 *   5. pow is the answer.
 *
 * DRY RUN   x = 7, n = 5   (5 = 101 in binary; values after the step)
 *
 *    step | n before | n odd? | pow            | x after squaring | n after
 *   ------+----------+--------+----------------+------------------+---------
 *      1  |  5 (101) |  yes   | 1 * 7 = 7      |        49        | 2 (10)
 *      2  |  2 (10)  |  no    | 7              |       2401       | 1 (1)
 *      3  |  1 (1)   |  yes   | 7 * 2401=16807 |      5764801     | 0
 *
 *   n = 0, loop stops. pow = 16807.0 = 7^5. The second loop gives the same rows.
 *
 * EDGE CASES   (x is an int in this code, not a double)
 *   x = 2, n = 10   -> 1024.0   the LeetCode example
 *   x = 2, n = 0    -> 1.0      loop never runs
 *   x = -2, n = 3   -> -8.0     sign handled by the multiplications
 *   x = 2, n = -2   -> 0.0 (should be 0.25)   BUG: x is an int, so 1 / x is integer division = 0.
 *                      The second loop has no negative handling and gives 4.0.
 *   x = 0, n = -1   -> crashes: ArithmeticException (/ by zero) in 1 / x; LeetCode guarantees
 *                      x != 0 when n < 0, and uses a double x, where 1 / 0.0 is Infinity
 *   x = 7, n = 16   -> -1.526366847E9 (should be 3.3e13)   BUG: x *= x overflows int
 *                      (5764801 * 5764801 doesn't fit) before it is multiplied into pow
 *   x = 2, n = 32   -> 0.0 (should be 4.29e9)   x reaches 2^32, which wraps to 0 in an int
 *   x = 2, n = 31   -> 2.147483648E9   correct: x wraps to 0 only after its last use
 *   x = 1, n = Integer.MIN_VALUE -> 1.0   -n overflows back to MIN_VALUE, but n >>>= 1 treats it as 2^31,
 *                      so the bitwise loop still ends after 32 steps
 *
 * COMMON MISTAKES
 *   - Looping n times: O(n), too slow for n near 2^31.
 *   - Using int for x: 1 / x becomes 0 and x *= x overflows. LeetCode's x is a double.
 *   - n = -n when n = Integer.MIN_VALUE: it stays negative. Use n >>>= 1 (as here) or store n in a long.
 *   - n >>= 1 instead of n >>>= 1 on a negative n: the sign bit is copied in and the loop never ends.
 *
 * COMPLEXITY
 *   O(log n) time (one step per bit of n), O(1) extra space.
 *
 * RELATED
 *   LeetCode 69 (SqrRtx, Math/): another O(log) idea, binary search that halves the range each step.
 *   LeetCode 172 (FactorialTrailingZero, Math/): a loop that divides n (by 5) each step.
 */
public class Pow {

    public static void main(String[] args) {
        int x = 7 ,n = 5;


        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        double pow = 1;
        while(n != 0){
            if((n & 1) != 0)
                pow *= x;

            x *= x;
            n >>>= 1;
        }


        x = 7 ;n = 5;
        pow = 1;
        while(n != 0){
            if((n % 2) != 0)
                pow *= x;

            x *= x;
            n = n / 2;
        }
    }
}

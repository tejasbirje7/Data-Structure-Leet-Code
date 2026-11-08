/**
 * LeetCode 50. Pow(x, n)
 * https://leetcode.com/problems/powx-n/
 *
 * PROBLEM
 *   - Input: a double x (-100 < x < 100) and an int n (n can be negative, down to -2^31).
 *   - Return: x raised to the power n, as a double.
 *   - n is up to 2^31, so multiplying x by itself n times is too slow.
 *   - LeetCode promises x != 0 when n <= 0, and the answer stays within -10^4 to 10^4.
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
 *   - Negative n: x^-n = (1 / x)^n, so flip both first. x is a double, so 1 / 2.0 = 0.5.
 *   - Integer.MIN_VALUE: -n overflows and stays MIN_VALUE (bits 1000...0).
 *     myPow keeps the int: n & 1 and n >>>= 1 read the bits as unsigned, so n acts as 2^31
 *     and the loop ends after 32 steps with the right answer.
 *   - Return: pow once n reaches 0.
 *   - Two methods: myPow uses (n & 1) and n >>>= 1, the bitwise forms of n % 2 and n / 2.
 *     myPowNormal is the same algorithm with % and /, on a long copy m of n,
 *     so -m = 2^31 fits and no unsigned trick is needed.
 *
 * STEPS
 *   1. If n < 0: n = -n, x = 1 / x.
 *   2. pow = 1.
 *   3. While n != 0: if the lowest bit of n is 1, pow *= x.
 *   4.   x *= x; n >>>= 1 (drop the lowest bit, unsigned).
 *   5. Return pow.
 *
 * DRY RUN   x = 2.0, n = -5   (values after the step)
 *
 *   n < 0, so flip first: n = 5 (101 in binary), x = 1 / 2.0 = 0.5, pow = 1.
 *
 *    step | n before | bit = 1? | pow                    | x after squaring | n after
 *   ------+----------+----------+------------------------+------------------+---------
 *      1  |  5 (101) |   yes    | 1 * 0.5 = 0.5          |    0.25          | 2 (10)
 *      2  |  2 (10)  |   no     | 0.5                    |    0.0625        | 1 (1)
 *      3  |  1 (1)   |   yes    | 0.5 * 0.0625 = 0.03125 |    0.00390625    | 0
 *
 *   n = 0, loop stops. Returns 0.03125 = 2^-5. myPowNormal gives the same rows.
 *
 * EDGE CASES
 *   x = 2, n = 10      -> 1024.0               the LeetCode example
 *   x = 2.1, n = 3     -> 9.261000000000001    double rounding; LeetCode shows 9.26100
 *   x = 2, n = 0       -> 1.0                  loop never runs
 *   x = -2, n = 3      -> -8.0                 odd n keeps the minus sign
 *   x = -2, n = 4      -> 16.0                 even n cancels it
 *   x = 2, n = -2      -> 0.25                 x becomes 1 / 2.0 = 0.5, then 0.5 * 0.5
 *   x = 7, n = 16      -> 3.3232930569601E13   x is a double, so squaring does not overflow
 *   x = 2, n = 32      -> 4.294967296E9        same: 2^32 fits easily in a double
 *   x = 1, n = Integer.MIN_VALUE  -> 1.0       -n stays MIN_VALUE, n >>>= 1 treats it as 2^31
 *   x = -1, n = Integer.MIN_VALUE -> 1.0       2^31 is even
 *   x = -1, n = Integer.MAX_VALUE -> -1.0      2^31 - 1 is odd
 *   x = 0, n = -1      -> Infinity             1 / 0.0 is Infinity in Java (no crash);
 *                                              LeetCode says this input won't occur
 *
 * COMMON MISTAKES
 *   - Looping n times: O(n), too slow for n near 2^31.
 *   - Using int for x: 1 / x with int x is integer division (1 / 2 = 0), and squaring an int
 *     overflows (2^32 wraps to 0). Use double for x and pow.
 *   - -n overflows for Integer.MIN_VALUE: it stays negative. Use n >>>= 1 (myPow) or a long (myPowNormal).
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
        double x = 2;
        int n = -5;

        System.out.println("myPow(" + x + ", " + n + ") = " + myPow(x, n));
        System.out.println("myPowNormal(" + x + ", " + n + ") = " + myPowNormal(x, n));
    }

    public static double myPow(double x, int n) {
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
        return pow;
    }

    public static double myPowNormal(double x, int n) {
        long m = n;
        if (m < 0) {
            m = -m;
            x = 1 / x;
        }

        double pow = 1;
        while(m != 0){
            if((m % 2) != 0)
                pow *= x;

            x *= x;
            m = m / 2;
        }
        return pow;
    }
}

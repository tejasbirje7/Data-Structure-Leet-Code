import java.math.BigInteger;

/**
 * LeetCode 172. Factorial Trailing Zeroes
 * https://leetcode.com/problems/factorial-trailing-zeroes/
 *
 * PROBLEM
 *   - Input: an integer n (0 <= n <= 10^4).
 *   - Return: how many zeros n! ends with. n! = n * (n - 1) * ... * 2 * 1.
 *   - Follow-up: do it in O(log n) time (n! itself is far too big to compute).
 *
 * EXAMPLE
 *   Input: n = 3
 *   Output: 0
 *   Explanation: 3! = 6, no trailing zero.
 *
 * HOW TO THINK
 *   - Clue: each trailing zero is a factor 10 = 2 * 5.
 *     There are always more 2s than 5s in n!, so count the 5s.
 *   - Technique: count the factors of 5 in 1..n without building n!.
 *     n / 5 numbers give one 5, n / 25 give a second 5, n / 125 a third, and so on.
 *   - Trick in the loop: divide n by 5 each time; the new n is exactly the next term
 *     (n / 5, then n / 25, ...), so just add it to count.
 *   - Rule that stays true: count = factors of 5 counted so far (all powers of 5 up to the current one).
 *   - Decision: while n > 0: n = n / 5, count += n.
 *   - Return: count.
 *   - recursiveSolution does the same sum: n / 5 + f(n / 5), stopping at n == 0.
 *
 * STEPS
 *   1. count = 0.
 *   2. While n > 0: n = n / 5 (integer division).
 *   3. count += n.
 *   4. Return count.
 *
 * DRY RUN   n = 13   (n and count are values after the step)
 *
 *    step | n before | n = n / 5 | count
 *   ------+----------+-----------+-------
 *      1  |    13    |     2     |   2     5 and 10 each give one 5
 *      2  |     2    |     0     |   2     no multiple of 25 up to 13
 *
 *   n = 0, loop stops. Returns 2 (13! = 6227020800).
 *
 * EDGE CASES
 *   n = 0       -> 0      loop never runs (0! = 1)
 *   n = 4       -> 0      4 / 5 = 0
 *   n = 5       -> 1      5! = 120
 *   n = 25      -> 6      25 adds two 5s: 25/5 = 5, plus 25/25 = 1
 *   n = 30      -> 7      6 + 1
 *   n = 100     -> 24     20 + 4
 *   n = 10000   -> 2499   2000 + 400 + 80 + 16 + 3, only 6 loop steps
 *   n = Integer.MAX_VALUE -> 536870902   no overflow, n only shrinks
 *   n = -5      -> 0 from trailingZeroes, -1 from recursiveSolution (negative / 5 rounds toward 0);
 *                  LeetCode guarantees n >= 0
 *
 * COMMON MISTAKES
 *   - Computing n! and counting zeros: overflows long by n = 21. Even BigInteger is far too slow.
 *   - Only returning n / 5: misses the extra 5s in 25, 50, 125, ... (25 would give 5, not 6).
 *   - Looping i = 5, 25, 125 ... with i *= 5 in an int: i can overflow for big n.
 *     Dividing n instead (as here) never overflows.
 *
 * COMPLEXITY
 *   O(log5 n) time, O(1) extra space (recursiveSolution: O(log5 n) stack).
 *
 * RELATED
 *   LeetCode 9 (Pallindrome, Math/): the same "keep dividing n" loop, by 10 to peel off digits.
 *   LeetCode 50 (Pow, Math/): another O(log n) loop that halves n each step.
 */
public class FactorialTrailingZero {

    public static void main(String[] args) {
        System.out.println(trailingZeroes(13));
    }

    public static int trailingZeroes(int n) {
       int count = 0;
        while (n > 0) {
            n /= 5;
            count += n;
        }

        return count;
     }

    public static int recursiveSolution(int n) {
        return n == 0 ? 0 : n / 5 + recursiveSolution(n / 5);
    }
}

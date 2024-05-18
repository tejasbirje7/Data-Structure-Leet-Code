import java.math.BigInteger;

public class FactorialTrailingZero {

    public static void main(String[] args) {
        System.out.println(trailingZeroes(13));
    }

    /**
     * Given an integer n, return the number of trailing zeroes in n!
     * Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.
     *
     * Input: n = 3
     * Output: 0
     * Explanation: 3! = 6, no trailing zero.
     *
     * Input: n = 5
     * Output: 1
     * Explanation: 5! = 120, one trailing zero.
     *
     * Input: n = 0
     * Output: 0
     */
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

import java.math.BigInteger;

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

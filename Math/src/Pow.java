public class Pow {

    /**
     * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
     *
     * Input: x = 2.00000, n = 10
     * Output: 1024.00000
     *
     * Input: x = 2.10000, n = 3
     * Output: 9.26100
     *
     * Input: x = 2.00000, n = -2
     * Output: 0.25000
     * Explanation: 2-2 = 1/22 = 1/4 = 0.25
     */
    public static void main(String[] args) {
        int x = 7 ,n = 5;


        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        // Based on 2^7 = 2^2 * 2^2 * 2^2 * 2^1
        double pow = 1;
        while(n != 0){
            if((n & 1) != 0) // equivalent to if((n % 2) != 0) i.e. multiply only when the number is odd
                pow *= x;

            x *= x;
            n >>>= 1; // equivalent to n = n / 2; i.e. keep dividing the number by 2
        }


        // Normal approach without bitwise
        x = 7 ;n = 5;
        pow = 1;
        while(n != 0){
            if((n % 2) != 0) // equivalent to if((n % 2) != 0) i.e. multiply only when the number is odd
                pow *= x;

            x *= x;
            n = n / 2; // equivalent to n = n / 2; i.e. keep dividing the number by 2
        }
    }
}

public class IsHappy {

    public static void main(String[] args) {
        int n = 2;
        System.out.println(isHappy(n));
    }

    public static boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
            if (fast == 1) return true;
        } while (slow != fast);
        return false;
    }

    public static int digitSquareSum(int n) {
        int sum = 0;
        int tmp;
        while (n != 0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n = n / 10;
        }
        return sum;
    }
}

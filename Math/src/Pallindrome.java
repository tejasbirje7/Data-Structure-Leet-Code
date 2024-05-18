public class Pallindrome {

    public static void main(String[] args) {
        int x = 121;
        System.out.println(isPalindrome(x));
    }

    /**
     * Given an integer x, return true if x is a
     * palindrome
     * , and false otherwise.
     *
     * Input: x = 121
     * Output: true
     * Explanation: 121 reads as 121 from left to right and from right to left.
     *
     * Input: x = -121
     * Output: false
     * Explanation: From left to right, it reads -121.
     * From right to left, it becomes 121-.
     * Therefore, it is not a palindrome.
     *
     * Input: x = 10
     * Output: false
     * Explanation: Reads 01 from right to left. Therefore, it is not a palindrome.
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) return false; // negative number cannot be a palindrome -121 -> 121-

        int org = x, rev =0;
        while(x != 0){
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return org == rev;
    }

}

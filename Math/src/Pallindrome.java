public class Pallindrome {

    public static void main(String[] args) {
        int x = 121;
        System.out.println(isPalindrome(x));
    }

    /**
     * Input: x = 121
     * Output: true
     * Explanation: 121 reads as 121 from left to right and from right to left.
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

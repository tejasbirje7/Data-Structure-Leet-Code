public class LongestPalindrome {

    public static class LongestPalindromeMain {

        // Solution class with longestPalindrome method
        static class Solution {
            public String longestPalindrome(String s) {

                int longestPalindromeLength = 0;
                String longestPalindrome = "";

                for (int i = 0; i < s.length(); i++) {
                    int l = i;
                    int r = i;

                    //Odd length
                    while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                        String newPalindrome = s.substring(l, r + 1);
                        if (newPalindrome.length() > longestPalindromeLength) {
                            longestPalindrome = newPalindrome;
                            longestPalindromeLength = r - l + 1;
                        }
                        l--;
                        r++;
                    }

                    // Even length
                    l = i;
                    r = i + 1;
                    while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                        String newPalindrome = s.substring(l, r + 1);
                        if (newPalindrome.length() > longestPalindromeLength) {
                            longestPalindrome = newPalindrome;
                            longestPalindromeLength = r - l + 1;
                        }
                        l--;
                        r++;
                    }
                }

                return longestPalindrome;
            }
        }

        public static void main(String[] args) {
            // Sample input
            String s = "babad";

            Solution sol = new Solution();
            String result = sol.longestPalindrome(s);

            System.out.println("Longest palindromic substring: " + result);
        }
    }

}

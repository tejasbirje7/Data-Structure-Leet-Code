/**
 * Two cases are checked for each index i:
 *
 * Odd-length palindromes (center at i).
 *
 * Even-length palindromes (center between i and i+1).
 *
 * Expands outwards while characters match.
 *
 * Keeps track of both the length and the substring.
 */
public class LongestPalindromicSubString {
    public String longestPalindrome(String s) {
        // Length of the longest palindrome found so far
        int longestPalindromeLength = 0;
        // The actual longest palindrome substring
        String longestPalindrome = "";

        // Loop through each character, treating it as the "center" of a palindrome
        for (int i = 0; i < s.length(); i++) {
            // --- Odd length palindrome (center is a single character) ---
            int l = i; // left pointer
            int r = i; // right pointer
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                // Found a palindrome from l to r
                String newPalindrome = s.substring(l, r + 1);
                // Update if it's longer than what we have
                if (newPalindrome.length() > longestPalindromeLength) {
                    longestPalindrome = newPalindrome;
                    longestPalindromeLength = r - l + 1;
                }
                // Expand outwards
                l--;
                r++;
            }

            // --- Even length palindrome (center is between two characters) ---
            l = i;       // left pointer
            r = i + 1;   // right pointer
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                // Found a palindrome from l to r
                String newPalindrome = s.substring(l, r + 1);
                // Update if it's longer than what we have
                if (newPalindrome.length() > longestPalindromeLength) {
                    longestPalindrome = newPalindrome;
                    longestPalindromeLength = r - l + 1;
                }
                // Expand outwards
                l--;
                r++;
            }
        }

        return longestPalindrome;
    }

    public static void main(String[] args) {
        LongestPalindromicSubString sol = new LongestPalindromicSubString();

        // Example 1
        String s1 = "babad";
        System.out.println("Input: " + s1);
        System.out.println("Longest Palindromic Substring: " + sol.longestPalindrome(s1)); // bab or aba

        // Example 2
        String s2 = "cbbd";
        System.out.println("Input: " + s2);
        System.out.println("Longest Palindromic Substring: " + sol.longestPalindrome(s2)); // bb

        // Example 3
        String s3 = "a";
        System.out.println("Input: " + s3);
        System.out.println("Longest Palindromic Substring: " + sol.longestPalindrome(s3)); // a

        // Example 4
        String s4 = "ac";
        System.out.println("Input: " + s4);
        System.out.println("Longest Palindromic Substring: " + sol.longestPalindrome(s4)); // a or c
    }
}

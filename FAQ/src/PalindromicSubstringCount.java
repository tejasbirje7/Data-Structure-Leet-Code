/**
 * Treat each index and gap between characters as a possible center.
 *
 * Expand left and right while characters match.
 *
 * Count each palindrome found during expansion.
 */
public class PalindromicSubstringCount {
    public int countSubstrings(String s) {
        int count = 0;

        // There are 2*n - 1 possible centers for palindromes
        // (n odd-length centers, n-1 even-length centers)
        for (int center = 0; center < 2 * s.length() - 1; center++) {
            // For even-length centers, left = center/2, right = left+1
            // For odd-length centers, left = right = center/2
            int left = center / 2;
            int right = left + center % 2;

            // Expand outward while the characters match
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                count++; // Found a palindrome
                left--;
                right++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        PalindromicSubstringCount sol = new PalindromicSubstringCount();

        String s1 = "abc";
        System.out.println("Input: " + s1);
        System.out.println("Palindromic substrings count: " + sol.countSubstrings(s1)); // 3

        String s2 = "aaa";
        System.out.println("Input: " + s2);
        System.out.println("Palindromic substrings count: " + sol.countSubstrings(s2)); // 6

        String s3 = "racecar";
        System.out.println("Input: " + s3);
        System.out.println("Palindromic substrings count: " + sol.countSubstrings(s3));
    }
}

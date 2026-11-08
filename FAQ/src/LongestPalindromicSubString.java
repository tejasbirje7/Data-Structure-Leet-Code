/**
 * LeetCode 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * PROBLEM
 *   - Input: a string s (1 <= length <= 1000, digits and English letters).
 *   - Return: the longest substring that reads the same forwards and backwards.
 *   - If several have the same length, any one is accepted.
 *
 * EXAMPLE
 *   Input: s = "babad"
 *   Output: "bab"
 *   Explanation: "aba" is also a valid answer.
 *
 * HOW TO THINK
 *   - Clue: every palindrome mirrors around its middle.
 *     So pick a middle and grow outwards while both ends match.
 *   - Technique: expand around center, two cases per index i:
 *     odd length  -> center is s[i]          (l = i, r = i).
 *     even length -> center is between i, i+1 (l = i, r = i + 1).
 *   - Rule that stays true: while expanding, s[l..r] is a palindrome.
 *     longestPalindrome = the longest one found so far.
 *   - Decision: s[l] == s[r] and both in range -> s[l..r] is a palindrome; keep it if longer, then l--, r++.
 *               otherwise -> stop this center.
 *   - Return: longestPalindrome. "Longer" is strict (>), so on a tie the first one found wins.
 *
 * STEPS
 *   1. best = "", bestLen = 0.
 *   2. For each i: set l = r = i (odd case).
 *   3.   While l >= 0, r < n and s[l] == s[r]: if r - l + 1 > bestLen, save s[l..r]; then l--, r++.
 *   4.   Set l = i, r = i + 1 (even case) and repeat step 3.
 *   5. Return best.
 *
 * DRY RUN   s = "babad"   (best is the value after the row)
 *
 *    i | case | palindromes found (l,r)          | stops at        | best
 *   ---+------+----------------------------------+-----------------+-------
 *    0 | odd  | (0,0) "b" new best               | (-1,1) out      | "b"
 *    0 | even | none                             | (0,1) b != a    | "b"
 *    1 | odd  | (1,1) "a", (0,2) "bab" new best  | (-1,3) out      | "bab"
 *    1 | even | none                             | (1,2) a != b    | "bab"
 *    2 | odd  | (2,2) "b", (1,3) "aba" not > 3  | (0,4) b != d    | "bab"
 *    2 | even | none                             | (2,3) b != a    | "bab"
 *    3 | odd  | (3,3) "a"                        | (2,4) b != d    | "bab"
 *    3 | even | none                             | (3,4) a != d    | "bab"
 *    4 | odd  | (4,4) "d"                        | (3,5) out       | "bab"
 *    4 | even | none                             | (4,5) out       | "bab"
 *
 *   Returns "bab" ("aba" has the same length but came later).
 *   Even case in action (main's "cbbd"): i = 1 even finds (1,2) "bb" as new best,
 *     then stops at (0,3) c != d. Returns "bb".
 *
 * EDGE CASES
 *   "a"      -> "a"      one character is a palindrome
 *   "ac"     -> "a"      no longer palindrome; tie goes to the first found
 *   "cbbd"   -> "bb"     only the even case finds it
 *   "aaaa"   -> "aaaa"   the whole string (found by the even case at i = 1)
 *   ""       -> ""       the loop never runs; LeetCode guarantees length >= 1
 *
 * COMMON MISTAKES
 *   - Only trying odd centers: even palindromes like "bb" are missed. Try both centers at every i.
 *   - Checking s[l] == s[r] before the bounds check: index out of range. Check l >= 0 and r < n first.
 *   - Brute force (check every substring): O(n^3).
 *   - Calling substring on every match (as this code does): fine for n <= 1000, but tracking
 *     start and length and cutting the string once at the end saves the copying.
 *
 * COMPLEXITY
 *   O(n^2) center expansions. This code also builds a substring on every match,
 *     so the worst case (e.g. "aaaa...") is O(n^3) time.
 *   O(n) extra space for the substring.
 *
 * RELATED
 *   LeetCode 5 (LongestPalindrome, TwoPointers/): same problem, also in TwoPointers/.
 *   LeetCode 647 (PalindromicSubstringCount, FAQ/): same expand-around-center, but count every palindrome.
 *   LeetCode 125 (ValidPalindrome, TwoPointers/): two pointers moving inwards to check one string.
 */
public class LongestPalindromicSubString {
    public String longestPalindrome(String s) {
        int longestPalindromeLength = 0;
        String longestPalindrome = "";

        for (int i = 0; i < s.length(); i++) {
            int l = i;
            int r = i;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                String newPalindrome = s.substring(l, r + 1);
                if (newPalindrome.length() > longestPalindromeLength) {
                    longestPalindrome = newPalindrome;
                    longestPalindromeLength = r - l + 1;
                }
                l--;
                r++;
            }

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

    public static void main(String[] args) {
        LongestPalindromicSubString sol = new LongestPalindromicSubString();

        String s1 = "babad";
        System.out.println("Input: " + s1);
        System.out.println("Longest Palindromic Substring: " + sol.longestPalindrome(s1));

        String s2 = "cbbd";
        System.out.println("Input: " + s2);
        System.out.println("Longest Palindromic Substring: " + sol.longestPalindrome(s2));

        String s3 = "a";
        System.out.println("Input: " + s3);
        System.out.println("Longest Palindromic Substring: " + sol.longestPalindrome(s3));

        String s4 = "ac";
        System.out.println("Input: " + s4);
        System.out.println("Longest Palindromic Substring: " + sol.longestPalindrome(s4));
    }
}

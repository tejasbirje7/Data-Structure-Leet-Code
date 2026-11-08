/**
 * LeetCode 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * PROBLEM
 *   - Input: a string s.
 *   - Return: the longest substring of s that reads the same forwards and backwards.
 *   - Substring = contiguous. If several have the same longest length, any one is accepted.
 *
 * EXAMPLE
 *   Input: s = "babad"
 *   Output: "bab"
 *   Explanation: "aba" is also a valid answer.
 *
 * HOW TO THINK
 *   - Clue: a palindrome mirrors around its center. If s[l..r] is a palindrome and
 *     s[l-1] == s[r+1], then s[l-1..r+1] is one too.
 *   - Technique: expand around center. Two pointers start at a center and move outwards.
 *   - Two kinds of center: odd length -> center is one char (l = i, r = i), e.g. "aba".
 *                          even length -> center is between two chars (l = i, r = i + 1), e.g. "abba".
 *     Every palindrome has exactly one such center, so trying all 2n - 1 centers finds them all.
 *   - Rule that stays true: while the loop runs, s[l..r] is a palindrome;
 *     longestPalindrome is the longest one found so far.
 *   - Decision: in range and s[l] == s[r] -> s[l..r] is a palindrome; keep it if strictly longer,
 *                                            then expand (l--, r++).
 *               out of range or mismatch  -> stop expanding this center.
 *   - Return: longestPalindrome. Strict ">" means the first one found wins a tie.
 *
 * STEPS
 *   1. longestPalindrome = "", longestPalindromeLength = 0.
 *   2. For each index i:
 *   3.   Odd: l = r = i. While l >= 0, r < n and s[l] == s[r]: keep s[l..r] if longer; l--, r++.
 *   4.   Even: l = i, r = i + 1. Same loop.
 *   5. Return longestPalindrome.
 *
 * DRY RUN   s = "aabab"   (longest is the value after the row)
 *
 *    i | center | l  | r | s[l] vs s[r] | action                   | longest
 *   ---+--------+----+---+--------------+--------------------------+---------
 *    0 |  odd   |  0 | 0 |  a = a       | "a" longer, take it      | "a"
 *    0 |  odd   | -1 | 1 | out of range | stop                     | "a"
 *    0 |  even  |  0 | 1 |  a = a       | "aa" longer, take it     | "aa"
 *    0 |  even  | -1 | 2 | out of range | stop                     | "aa"
 *    1 |  odd   |  1 | 1 |  a = a       | "a" not longer           | "aa"
 *    1 |  odd   |  0 | 2 |  a != b      | stop                     | "aa"
 *    1 |  even  |  1 | 2 |  a != b      | stop                     | "aa"
 *    2 |  odd   |  2 | 2 |  b = b       | "b" not longer           | "aa"
 *    2 |  odd   |  1 | 3 |  a = a       | "aba" longer, take it    | "aba"
 *    2 |  odd   |  0 | 4 |  a != b      | stop                     | "aba"
 *    2 |  even  |  2 | 3 |  b != a      | stop                     | "aba"
 *    3 |  odd   |  3 | 3 |  a = a       | "a" not longer           | "aba"
 *    3 |  odd   |  2 | 4 |  b = b       | "bab" same length, keep  | "aba"
 *    3 |  odd   |  1 | 5 | out of range | stop                     | "aba"
 *    3 |  even  |  3 | 4 |  a != b      | stop                     | "aba"
 *    4 |  odd   |  4 | 4 |  b = b       | "b" not longer           | "aba"
 *    4 |  odd   |  3 | 5 | out of range | stop                     | "aba"
 *    4 |  even  |  4 | 5 | out of range | stop                     | "aba"
 *
 *   Returns "aba".
 *
 * EDGE CASES
 *   "babad"   -> "bab"    "aba" is just as long but found later; strict ">" keeps the first
 *   "cbbd"    -> "bb"     only an even center gives a palindrome longer than 1
 *   "a"       -> "a"      one char is a palindrome
 *   "ac"      -> "a"      no palindrome longer than 1; the first char wins
 *   "aaaa"    -> "aaaa"   the even center at i = 1 grows to the whole string
 *   ""        -> ""       loop never runs (LeetCode guarantees length >= 1)
 *
 * COMMON MISTAKES
 *   - Trying only odd centers: misses even palindromes like "bb" in "cbbd". Expand from (i, i) and (i, i + 1).
 *   - substring(l, r) instead of substring(l, r + 1): the end index is exclusive.
 *   - Calling charAt before the bounds check: test l >= 0 && r < n first.
 *   - Checking every substring for being a palindrome: O(n^3).
 *
 * COMPLEXITY
 *   O(n^2) expansion steps (2n - 1 centers, each grows at most n / 2 times).
 *   As written, every step also calls substring(), which copies up to n chars, so the worst case
 *   (e.g. "aaaa...a") is O(n^3) time. Store start and length instead and call substring() once at the end.
 *   O(n) extra space for the substring copies.
 *
 * RELATED
 *   LeetCode 5 (LongestPalindromicSubString, FAQ/): same problem, also in FAQ/ (same expand-around-center approach).
 *   LeetCode 647 (PalindromicSubstringCount, FAQ/): same expand-around-center, but counts every
 *     palindrome instead of keeping the longest.
 *   LeetCode 125 (ValidPalindrome, TwoPointers/): checks one whole string, pointers move inwards
 *     from the ends instead of outwards from a center.
 */
public class LongestPalindrome {

    public static class LongestPalindromeMain {

        static class Solution {
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
        }

        public static void main(String[] args) {
            String s = "aabab";

            Solution sol = new Solution();
            String result = sol.longestPalindrome(s);

            System.out.println("Longest palindromic substring: " + result);
        }
    }

}

/**
 * LeetCode 647. Palindromic Substrings
 * https://leetcode.com/problems/palindromic-substrings/
 *
 * PROBLEM
 *   - Input: a string s (1 <= length <= 1000, lowercase letters).
 *   - Return: how many substrings of s are palindromes.
 *   - Substrings at different positions count separately, even if the text is the same.
 *
 * EXAMPLE
 *   Input: s = "abc"
 *   Output: 3
 *   Explanation: Three palindromic strings: "a", "b", "c".
 *
 * HOW TO THINK
 *   - Clue: every palindrome grows outwards from a middle, so count while expanding from each middle.
 *   - Technique: expand around center, with all centers in one loop.
 *     A string of length n has 2n - 1 centers: n characters (odd length) and n - 1 gaps (even length).
 *   - Index trick: left = center / 2, right = left + center % 2.
 *     Even center -> left == right (on a character). Odd center -> right = left + 1 (on a gap).
 *   - Rule that stays true: while expanding, s[left..right] is a palindrome, and each one is counted once.
 *   - Decision: both in range and s[left] == s[right] -> count++, then left--, right++.
 *               otherwise -> stop this center.
 *   - Return: count.
 *
 * STEPS
 *   1. count = 0.
 *   2. For center = 0 .. 2n - 2: left = center / 2, right = left + center % 2.
 *   3.   While left >= 0, right < n and s[left] == s[right]: count++, left--, right++.
 *   4. Return count.
 *
 * DRY RUN   s = "aaa"   (n = 3, so 5 centers; count is the value after each palindrome)
 *
 *    center | start (left,right) | on        | palindromes counted            | stops at    | count
 *   --------+--------------------+-----------+--------------------------------+-------------+-------
 *      0    |       (0,0)        | char 0    | "a"                            | (-1,1) out  |   1
 *      1    |       (0,1)        | gap 0-1   | "aa"                           | (-1,2) out  |   2
 *      2    |       (1,1)        | char 1    | "a", then (0,2) "aaa"          | (-1,3) out  |   4
 *      3    |       (1,2)        | gap 1-2   | "aa"                           | (0,3) out   |   5
 *      4    |       (2,2)        | char 2    | "a"                            | (1,3) out   |   6
 *
 *   Returns 6.
 *   Mismatch stop (main's "abc"): center 1 starts at (0,1), a != b, nothing counted. Total 3.
 *
 * EDGE CASES
 *   "a"         -> 1    one center, one palindrome
 *   "abc"       -> 3    only the single letters
 *   "aaaa"      -> 10   all the same: every substring counts, n(n+1)/2
 *   "racecar"   -> 10   7 letters + "cec", "aceca", "racecar"
 *   ""          -> 0    2*0 - 1 = -1 centers, the loop never runs; LeetCode guarantees length >= 1
 *
 * COMMON MISTAKES
 *   - Only expanding from characters: even-length palindromes like "aa" are missed.
 *   - Setting right = left + 1 for every center: the odd-length (single middle) palindromes are skipped.
 *     Use right = left + center % 2.
 *   - Counting only when the expansion stops: every step of a growing palindrome is its own substring.
 *   - Brute force (check every substring): O(n^3).
 *
 * COMPLEXITY
 *   O(n^2) time (2n - 1 centers, each expands at most n/2 times), O(1) extra space.
 *
 * RELATED
 *   LeetCode 5 (LongestPalindromicSubString, FAQ/; LongestPalindrome, TwoPointers/): same expansion,
 *     but keep the longest palindrome instead of counting.
 *   LeetCode 125 (ValidPalindrome, TwoPointers/): two pointers moving inwards to check one string.
 */
public class PalindromicSubstringCount {
    public int countSubstrings(String s) {
        int count = 0;

        for (int center = 0; center < 2 * s.length() - 1; center++) {
            int left = center / 2;
            int right = left + center % 2;

            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                count++;
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
        System.out.println("Palindromic substrings count: " + sol.countSubstrings(s1));

        String s2 = "aaa";
        System.out.println("Input: " + s2);
        System.out.println("Palindromic substrings count: " + sol.countSubstrings(s2));

        String s3 = "racecar";
        System.out.println("Input: " + s3);
        System.out.println("Palindromic substrings count: " + sol.countSubstrings(s3));
    }
}

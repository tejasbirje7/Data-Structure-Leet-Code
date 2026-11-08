/**
 * LeetCode 28. Find the Index of the First Occurrence in a String
 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
 *
 * PROBLEM
 *   - Input: two strings, haystack and needle.
 *   - Return: the index where needle first appears in haystack, or -1 if it never appears.
 *   - Lengths are at most 10^4, so trying every start index is fast enough.
 *
 * EXAMPLE
 *   Input: haystack = "sadbutsad", needle = "sad"
 *   Output: 0
 *   Explanation: "sad" occurs at index 0 and 6.
 *   The first occurrence is at index 0, so we return 0.
 *
 * HOW TO THINK
 *   - Clue: "first occurrence" -> try start indexes from left to right, stop at the first match.
 *   - Technique: brute force with a fixed-size window of n = needle.length() characters.
 *     Cut the window out with substring and compare it with needle.
 *   - Rule that stays true: no start index before i matches needle.
 *   - Decision: haystack.substring(i, i + n) equals needle -> return i.
 *               otherwise -> i++.
 *   - Loop limit: the last start that still fits is haystack.length() - n (i + n <= length).
 *   - Shortcut at the top: if both strings have the same length there is only one window,
 *     so just compare the whole strings with equals.
 *   - Return: -1 when no window matched.
 *
 * STEPS
 *   1. n = needle.length(), i = 0.
 *   2. If needle and haystack have the same length, return 0 if they are equal, else -1.
 *   3. While i + n <= haystack.length(): s = haystack.substring(i, i + n).
 *   4. If s equals needle, return i; otherwise i++.
 *   5. Return -1.
 *
 * DRY RUN   haystack = "mississippi", needle = "issip"   (n = 5, last start = 11 - 5 = 6)
 *
 *    i | window [i, i+5) | equals "issip"? | action
 *   ---+-----------------+-----------------+-----------------------------------------
 *    0 | "missi"         | no              | i++
 *    1 | "issis"         | no              | i++   (first 4 letters match, the 5th doesn't)
 *    2 | "ssiss"         | no              | i++
 *    3 | "sissi"         | no              | i++
 *    4 | "issip"         | yes             | return 4
 *
 *   Returns 4.
 *
 * EDGE CASES
 *   "sadbutsad", "sad"   -> 0    match at the very first window
 *   "leetcode", "leeto"  -> -1   no window matches; the loop stops after i = 3
 *   "abc", "abc"         -> 0    same length: the shortcut compares the whole strings
 *   "abc", "abd"         -> -1   same length, not equal
 *   "a", "ab"            -> -1   needle longer: i + n <= length is false at once
 *   "abc", "c"           -> 2    the last window that fits (i = length - n)
 *   "aaa", "aa"          -> 0    overlapping matches; the first one wins
 *   "abc", ""            -> 0    empty needle matches at 0; LeetCode guarantees needle length >= 1
 *
 * COMMON MISTAKES
 *   - Loop condition i + n < length: misses the last window ("abc", "c" would give -1). Use <=.
 *   - Skipping past a failed partial match: the real match can start inside it
 *     ("issip" starts at 4, inside the failed try at 1). Move one step at a time (or use KMP).
 *   - Comparing strings with == instead of equals: == checks references, not the text.
 *
 * COMPLEXITY
 *   O((m - n + 1) * n) time, m = haystack length (each window copies and compares n characters).
 *   O(n) extra space for the window string. KMP would make it O(m + n) time.
 *
 * RELATED
 *   LeetCode 14 (LongestPrefixString, ArraysAndStrings/): also matches one string against another,
 *     but only cares if it appears at index 0 (indexOf(prefix) == 0).
 *   LeetCode 30 (SubstringWithConcatenation, SlidingWindow/): fixed-length windows over a string too,
 *     but each window must be built from several given words.
 */
public class FirstOccurrenceOfString {

    public static void main(String[] args) {
        String haystack = "mississippi";
        String needle = "issip";
        System.out.println(strStr(haystack,needle));
    }

    public static int strStr(String haystack, String needle) {
        int i = 0;
        int n = needle.length();

        if(needle.length() == haystack.length()) {
            return needle.equals(haystack) ? 0 : -1;
        }

        while (i <= haystack.length() && i + needle.length() <= haystack.length()) {
            String s = haystack.substring(i,i+n);
            if(s.equals(needle)) {
                return i;
            } else {
                i++;
            }
        }
        return -1;
    }
}

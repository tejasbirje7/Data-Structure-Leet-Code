/**
 * LeetCode 392. Is Subsequence
 * https://leetcode.com/problems/is-subsequence/
 *
 * PROBLEM
 *   - Input: two strings s and t.
 *   - Return: true if s is a subsequence of t, else false.
 *   - Subsequence = delete some (or no) characters of t without changing the order of the rest.
 *     "ace" is a subsequence of "abcde"; "aec" is not.
 *
 * EXAMPLE
 *   Input: s = "abc", t = "ahbgdc"
 *   Output: true
 *
 * HOW TO THINK
 *   - Clue: "without disturbing the relative positions" = the characters of s must appear in t
 *     in the same order, with anything in between.
 *   - Technique: two pointers, one on each string, both moving left to right.
 *     matchPointer = next character of s we still need; wordPointer reads t.
 *   - Why greedy works: matching a character at the earliest place in t leaves the most of t
 *     for the rest of s, so it is never worse to match early.
 *   - Rule that stays true: s[0..matchPointer-1] has already been found in t[0..wordPointer-1], in order.
 *   - Decision: t[wordPointer] == s[matchPointer] -> match: matchPointer++.
 *               always                            -> wordPointer++ (each char of t is read once).
 *   - Return: true only if every character of s was matched (matchPointer == s.length()).
 *
 * STEPS
 *   1. matchPointer = 0 (on s), wordPointer = 0 (on t).
 *   2. Loop while both pointers are inside their strings.
 *   3. If the characters match (lower-case compare), matchPointer++.
 *   4. wordPointer++ every time.
 *   5. Return matchPointer == s.length().
 *
 * DRY RUN   s = "abc", t = "ahbgdc"   (matchPointer is the value after the step)
 *
 *    wordPointer | t[w] | s[m] needed | action     | matchPointer
 *   -------------+------+-------------+------------+--------------
 *         0      |  a   |      a      | match, m++ |      1
 *         1      |  h   |      b      | skip       |      1
 *         2      |  b   |      b      | match, m++ |      2
 *         3      |  g   |      c      | skip       |      2
 *         4      |  d   |      c      | skip       |      2
 *         5      |  c   |      c      | match, m++ |      3
 *
 *   Loop stops (matchPointer = 3 = s.length(), wordPointer = 6). Returns true.
 *
 * EDGE CASES
 *   s = "",    t = "abc"      -> true    loop never runs, 0 == 0
 *   s = "",    t = ""         -> true    an empty string is a subsequence of anything
 *   s = "abc", t = ""         -> false   t is empty, matchPointer stays 0
 *   s = "axc", t = "ahbgdc"   -> false   'x' is never found; t runs out with matchPointer = 1
 *   s = "abc", t = "abc"      -> true    every step is a match
 *   s = "aaaa", t = "aa"      -> false   s is longer than t
 *   s = "A",   t = "a"        -> true    the code ignores case (LeetCode would say false);
 *                                        LeetCode only uses lower-case letters, so it never matters
 *
 * COMMON MISTAKES
 *   - Moving the s pointer on a mismatch, or not moving the t pointer on a match:
 *     t's pointer moves every step; s's pointer moves only on a match.
 *   - Returning true as soon as t runs out: check matchPointer == s.length().
 *   - Using t.contains(s): that checks a substring (no gaps), not a subsequence.
 *
 * COMPLEXITY
 *   O(|t|) time (each character of t is read at most once), O(1) extra space.
 *
 * RELATED
 *   LeetCode 28 (FirstOccurrenceOfString, ArraysAndStrings/): s must appear as one block
 *     (substring, no gaps) instead of in order with gaps.
 *   LeetCode 88 (Merge, ArraysAndStrings/): also one pointer per array, but merges two sorted arrays
 *     from the back.
 */
public class IsSubsequence {

    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s,t));
    }

    public static boolean isSubsequence(String s, String t) {

        int matchPointer = 0;
        int wordPointer = 0;

        while (matchPointer < s.length() && wordPointer < t.length()) {
            if(Character.toLowerCase(t.charAt(wordPointer)) == Character.toLowerCase(s.charAt(matchPointer)))
                matchPointer++;
            wordPointer++;
        }
        return matchPointer == s.length();
    }
}

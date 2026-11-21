/**
 * LeetCode 424. Longest Repeating Character Replacement
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 *
 * PROBLEM
 *   - Input: a string s of upper-case letters and an int k.
 *   - You may change any character to any other upper-case letter, at most k times in total.
 *   - Return: the length of the longest substring that can be made of one repeated letter.
 *
 * EXAMPLE
 *   Input: s = "AABABBA", k = 1
 *   Output: 4
 *   Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 *     The substring "BBBB" has the longest repeating letters, which is 4.
 *     There may exists other ways to achieve this answer too.
 *
 * HOW TO THINK
 *   - Clue: "longest substring" + "at most k changes" -> a sliding window that stays valid.
 *   - Technique: in a window, keep the most common letter and change the rest.
 *     The window is valid when (length - count of its most common letter) <= k.
 *   - Rule that stays true: count[] holds the letters inside s[left..right];
 *     maxFreq is the highest count seen in any window so far (it never goes down).
 *   - Decision for each new right: add it, update maxFreq.
 *     If length - maxFreq > k, slide left one step (drop s[left]). Then best = max(best, length).
 *   - Why maxFreq may stay stale: the answer only grows when a window with a BIGGER maxFreq appears,
 *     so a too-high maxFreq never produces a wrong larger answer; it only keeps the window from shrinking below best.
 *   - Return: best.
 *
 * STEPS
 *   1. count = int[26], left = 0, maxFreq = 0, best = 0.
 *   2. For right = 0 .. n - 1:
 *   3.   count[s[right]]++; maxFreq = max(maxFreq, count[s[right]]).
 *   4.   While (right - left + 1) - maxFreq > k: count[s[left]]--, left++.
 *   5.   best = max(best, right - left + 1).
 *   6. Return best.
 *
 * DRY RUN   input = s = "AABABBA", k = 1   (values are after the step)
 *
 *    right | char | counts A,B | maxFreq | window before | len - maxFreq | action              | window after | best
 *   -------+------+------------+---------+---------------+---------------+---------------------+--------------+-----
 *      0   |  A   |   1,0      |    1    | A             |   1 - 1 = 0   | keep                | A            |  1
 *      1   |  A   |   2,0      |    2    | AA            |   2 - 2 = 0   | keep                | AA           |  2
 *      2   |  B   |   2,1      |    2    | AAB           |   3 - 2 = 1   | keep                | AAB          |  3
 *      3   |  A   |   3,1      |    3    | AABA          |   4 - 3 = 1   | keep                | AABA         |  4
 *      4   |  B   |   2,2      |    3    | AABAB         |   5 - 3 = 2   | shrink, drop A      | ABAB         |  4
 *      5   |  B   |   1,3      |    3    | ABABB         |   5 - 3 = 2   | shrink, drop A      | BABB         |  4
 *      6   |  A   |   2,2      |    3    | BABBA         |   5 - 3 = 2   | shrink, drop B      | ABBA         |  4
 *   (counts are after the shrink)
 *
 *   Returns 4 (the window "AABA": change the B to get "AAAA").
 *
 * EDGE CASES
 *   "A", k = 0          -> 1   one letter is already repeating
 *   "AAAA", k = 2       -> 4   no change needed
 *   "ABCD", k = 0       -> 1   no changes allowed, every letter is different
 *   "ABCD", k = 4       -> 4   k >= length: change everything
 *   "ABAB", k = 2       -> 4   change both A's (or both B's)
 *
 * COMMON MISTAKES
 *   - Recomputing the true max count after every shrink (scanning 26 letters): correct but unneeded; a stale maxFreq is safe.
 *   - Using length - maxFreq >= k as the shrink test: exactly k changes are allowed, so shrink only when it is > k.
 *   - Counting only one target letter: the best letter to keep is different for different windows.
 *
 * COMPLEXITY
 *   O(n) time (left and right each move at most n times), O(26) = O(1) extra space.
 *
 * RELATED
 *   LeetCode 3 (LongestSubstring, SlidingWindow/): longest valid window too, but valid means "no repeats".
 *   LeetCode 209 (MinimumSizeSubArray, SlidingWindow/): shortest window instead of longest; shrink while valid.
 *   LeetCode 76 (MinimumWindowSubstring, SlidingWindow/): window validity also checked with letter counts.
 */
public class LongestRepeatingCharReplacement {

    public static void main(String[] args) {
        System.out.println(characterReplacement("AABABBA", 1));
    }

    public static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0;
        int maxFreq = 0;
        int best = 0;
        for (int right = 0; right < s.length(); right++) {
            int c = s.charAt(right) - 'A';
            count[c]++;
            maxFreq = Math.max(maxFreq, count[c]);
            while (right - left + 1 - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

}

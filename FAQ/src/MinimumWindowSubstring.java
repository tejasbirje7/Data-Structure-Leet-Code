import java.util.*;

/**
 * LeetCode 76. Minimum Window Substring
 * https://leetcode.com/problems/minimum-window-substring/
 *
 * PROBLEM
 *   - Input: two strings s (length m) and t (length n).
 *   - Return: the shortest substring of s that contains every character of t, duplicates included.
 *   - Return "" if there is no such substring. The answer is unique.
 *   - Follow-up: O(m + n) time.
 *
 * EXAMPLE
 *   Input: s = "ADOBECODEBANC", t = "ABC"
 *   Output: "BANC"
 *   Explanation: The smallest substring containing 'A', 'B', and 'C' is "BANC".
 *
 * HOW TO THINK
 *   - Clue: "smallest substring that contains ..." = shortest contiguous window that meets a condition.
 *   - Technique: sliding window with two pointers and two count maps.
 *     need = counts of each char in t. window = counts of each char in s[left..right].
 *     right grows the window until it is valid; left then shrinks it while it stays valid.
 *   - Rule that stays true: have = how many distinct chars of t have window count >= need count.
 *     The window is valid exactly when have == need.size().
 *   - Decision: add s[right]; if its count just reached the needed count, have++.
 *               while valid: record the window if it is the shortest yet; drop s[left];
 *               if that count fell below the need, have--; left++.
 *   - Return: s.substring(start, start + minLen), or "" if minLen was never set.
 *
 * STEPS
 *   1. If s is shorter than t, return "".
 *   2. Build need from t. have = 0, left = 0, minLen = MAX, start = 0.
 *   3. For each right: window[s[right]]++; if the char is in need and its count == need count, have++.
 *   4.   While have == need.size(): if right - left + 1 < minLen, save minLen and start;
 *        window[s[left]]--; if the char is in need and its count < need count, have--; left++.
 *   5. Return "" if minLen is still MAX, else s.substring(start, start + minLen).
 *
 * DRY RUN   s = "ADOBECODEBANC", t = "ABC"   (need = {A:1, B:1, C:1}, need.size() = 3)
 *           (have is after adding s[right]; left and best are after the shrink loop)
 *
 *    right | c | have | shrink loop (windows checked, then chars dropped)    | left | best (minLen, start)
 *   -------+---+------+------------------------------------------------------+------+---------------------
 *      0   | A |  1   | -                                                    |  0   | none
 *      1   | D |  1   | -                                                    |  0   | none
 *      2   | O |  1   | -                                                    |  0   | none
 *      3   | B |  2   | -                                                    |  0   | none
 *      4   | E |  2   | -                                                    |  0   | none
 *      5   | C |  3   | ADOBEC (6) new best; drop A -> have 2                |  1   | 6, 0  "ADOBEC"
 *      6   | O |  2   | -                                                    |  1   | 6, 0
 *      7   | D |  2   | -                                                    |  1   | 6, 0
 *      8   | E |  2   | -                                                    |  1   | 6, 0
 *      9   | B |  2   | - (B count is now 2, more than needed)               |  1   | 6, 0
 *     10   | A |  3   | DOBECODEBA (10) .. CODEBA (6), none < 6;             |  6   | 6, 0
 *          |   |      |   drop D, O, B (B still 1), E, then C -> have 2      |      |
 *     11   | N |  2   | -                                                    |  6   | 6, 0
 *     12   | C |  3   | ODEBANC (7), DEBANC (6), EBANC (5) new best,         | 10   | 4, 9  "BANC"
 *          |   |      |   BANC (4) new best; drop O, D, E, then B -> have 2  |      |
 *
 *   Returns s.substring(9, 13) = "BANC".
 *
 * EDGE CASES
 *   s = "a", t = "a"       -> "a"     the whole string
 *   s = "a", t = "aa"      -> ""      s is shorter than t
 *   s = "aa", t = "aa"     -> "aa"    duplicates in t must all be matched
 *   s = "bba", t = "ab"    -> "ba"    shrinking drops the extra b
 *   s = "abc", t = "d"     -> ""      never valid, minLen stays MAX
 *   s = "a", t = ""        -> crashes with StringIndexOutOfBoundsException: need.size() = 0,
 *                             so have == 0 always holds and left runs past the end;
 *                             LeetCode guarantees t has at least 1 character
 *
 * COMMON MISTAKES
 *   - have++ on every needed char added: have must go up only when a count reaches the need exactly,
 *     or extra copies (like the second B) count twice.
 *   - Checking only presence, not counts: t = "aa" needs two a's.
 *   - Recording the window after dropping s[left]: record first, then shrink.
 *   - Comparing two Integer values with ==: it fails above 127. The code uses intValue().
 *
 * COMPLEXITY
 *   O(m + n) time: building need is O(n); left and right each move at most m times.
 *   O(k) extra space for the maps (k = distinct characters).
 *
 * RELATED
 *   LeetCode 76 (MinimumWindowSubstring, SlidingWindow/): same problem, also in SlidingWindow/.
 *   LeetCode 209 (MinimumSizeSubArray, SlidingWindow/): shortest window again, but the condition is a sum >= target.
 *   LeetCode 3 (LongestSubstring, SlidingWindow/): longest window instead; shrink when it becomes invalid.
 *   LeetCode 30 (SubstringWithConcatenation, SlidingWindow/): windows of whole words with count maps.
 */
public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();
        int have = 0;
        int needSize = need.size();
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            window.put(c, window.getOrDefault(c, 0) + 1);

            if (need.containsKey(c) && window.get(c).intValue() == need.get(c).intValue()) {
                have++;
            }

            while (have == needSize) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                char remove = s.charAt(left);
                window.put(remove, window.get(remove) - 1);

                if (need.containsKey(remove) && window.get(remove) < need.get(remove)) {
                    have--;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring solution = new MinimumWindowSubstring();

        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));

        System.out.println(solution.minWindow("a", "a"));

        System.out.println(solution.minWindow("a", "aa"));
    }
}

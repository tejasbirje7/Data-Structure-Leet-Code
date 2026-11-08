import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 3. Longest Substring Without Repeating Characters
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * PROBLEM
 *   - Input: a string s (letters, digits, symbols and spaces).
 *   - Return: the length of the longest substring with no repeated character.
 *   - Substring = contiguous. A subsequence (chars picked with gaps) does not count.
 *
 * EXAMPLE
 *   Input: s = "abcabcbb"
 *   Output: 3
 *   Explanation: The answer is "abc", with the length of 3.
 *
 *   Input: s = "bbbbb"
 *   Output: 1
 *   Explanation: The answer is "b", with the length of 1.
 *
 *   Input: s = "pwwkew"
 *   Output: 3
 *   Explanation: The answer is "wke", with the length of 3.
 *   Notice that the answer must be a substring, "pwke"
 *   is a subsequence and not a substring.
 *
 * HOW TO THINK
 *   - Clue: "longest substring" + "without repeating" = a contiguous window that must stay valid.
 *   - Technique: sliding window + HashSet of the chars in the window.
 *     right grows the window; left shrinks it only when a repeat would get in.
 *   - Rule that stays true: s[left..right-1] has no repeats, and the set holds exactly those chars.
 *   - Decision: s[right] already in the set -> remove s[left], left++; repeat until the old copy is gone.
 *               s[right] not in the set     -> add it, right++, update maxLength.
 *   - Return: maxLength. right has already moved past the new char,
 *     so the window length is right - left (no + 1).
 *
 * STEPS
 *   1. left = right = 0, maxLength = 0, window = empty set.
 *   2. While right < n:
 *   3.   While s[right] is in the set: remove s[left], left++.
 *   4.   Add s[right], right++.
 *   5.   maxLength = max(maxLength, right - left).
 *   6. Return maxLength.
 *
 * DRY RUN   s = "pwwkew"   (right = the index being read; the rest is after the step)
 *
 *    right | s[right] | repeat? | removed | left | window | right - left | maxLength
 *   -------+----------+---------+---------+------+--------+--------------+-----------
 *      0   |    p     |   no    |    -    |  0   | "p"    |      1       |     1
 *      1   |    w     |   no    |    -    |  0   | "pw"   |      2       |     2
 *      2   |    w     |   yes   |  p, w   |  2   | "w"    |      1       |     2
 *      3   |    k     |   no    |    -    |  2   | "wk"   |      2       |     2
 *      4   |    e     |   no    |    -    |  2   | "wke"  |      3       |     3
 *      5   |    w     |   yes   |    w    |  3   | "kew"  |      3       |     3
 *
 *   Returns 3 ("wke"; "kew" ties but does not beat it).
 *
 * EDGE CASES
 *   ""           -> 0   the loop never runs
 *   "bbbbb"      -> 1   every new b first removes the old one
 *   " "          -> 1   a space is a normal character
 *   "abcabcbb"   -> 3   "abc"
 *   "dvdf"       -> 3   "vdf": left moves just past the first d, not all the way to right
 *   "abba"       -> 2   the second b removes both a and b; then "ba"
 *
 * COMMON MISTAKES
 *   - Shrinking with if instead of while: one removal may not drop the old copy.
 *     In "pwwkew" at right = 2, both p and w must go.
 *   - Restarting the window at right on a repeat: throws away valid chars.
 *     "dvdf" would give 2 instead of 3.
 *   - Using right - left + 1 after right++: off by one. Here right already points past the window.
 *
 * COMPLEXITY
 *   O(n) time (each char is added once and removed at most once),
 *   O(min(n, alphabet size)) extra space for the set.
 *
 * RELATED
 *   LeetCode 76 (MinimumWindowSubstring, SlidingWindow/): shortest window instead of longest;
 *     uses count maps and shrinks while the window is valid.
 *   LeetCode 219 (ContainsDuplicate, SlidingWindow/): same HashSet window, but its size is fixed at k.
 */
public class LongestSubstring {

    public static void main(String[] args) {
        String s = "pwwkew";
//        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int maxLength = 0;
        Set<Character> window = new HashSet<>();

        while (right < s.length()) {
            while (window.contains(s.charAt(right))) {
                window.remove(s.charAt(left++));
            }
            window.add(s.charAt(right++));
            maxLength = Math.max(maxLength,right-left);
        }

        return maxLength;
    }
}

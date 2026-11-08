import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * LeetCode 76. Minimum Window Substring
 * https://leetcode.com/problems/minimum-window-substring/
 *
 * PROBLEM
 *   - Input: two strings s and t.
 *   - Return: the shortest substring of s that contains every char of t, duplicates included.
 *   - Return "" if no such window exists. (LeetCode says the answer is unique.)
 *
 * EXAMPLE
 *   Input: s = "ADOBECODEBANC", t = "ABC"
 *   Output: "BANC"
 *   Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 *
 *   Input: s = "a", t = "a"
 *   Output: "a"
 *   Explanation: The entire string s is the minimum window.
 *
 *   Input: s = "a", t = "aa"
 *   Output: ""
 *   Explanation: Both 'a's from t must be included in the window.
 *   Since the largest window of s only has one 'a', return empty string.
 *
 * HOW TO THINK
 *   - Clue: "minimum window substring" + "every character in t" -> variable sliding window with counts.
 *   - Technique: two count maps. countMap = what t needs; window = what s[l..r] has.
 *     have / need lets us test "is the window valid?" in O(1) instead of comparing whole maps.
 *   - Rule that stays true: window holds the char counts of s[l..r];
 *     have = how many distinct chars of t already have enough copies in the window;
 *     need = how many distinct chars t has.
 *   - Decision: add s[r]; if its count just reached the count t needs -> have++.
 *               while have == need (window valid): save it if it is shorter than the best,
 *               remove s[l]; if that drops a needed char below its count -> have--; then l++.
 *   - Return: s.substring(result[0], result[1] + 1) (substring's end is exclusive),
 *     or "" if minLength never changed.
 *   - Why have only moves on "just reached" / "dropped below": extra copies (like a second B)
 *     don't change whether that char is covered.
 *
 * STEPS
 *   1. If s or t is empty, return "". Count t's chars into countMap; need = countMap.size(), have = 0, l = 0.
 *   2. Loop r over s: window[s[r]]++. If s[r] is in t and window[s[r]] == countMap[s[r]], have++.
 *   3. While have == need: if r - l + 1 < minLength, save l, r and the length.
 *   4.   window[s[l]]--. If s[l] is in t and window[s[l]] < countMap[s[l]], have--. Then l++.
 *   5. Return the saved substring, or "" if nothing was saved.
 *
 * DRY RUN   s = "ADOBECODEBANC", t = "ABC"   (need = 3)
 *           (window = s[l..r] when the step is checked; A,B,C counts, have and l are after the step;
 *            rows with no s[r] are shrink steps of the while loop)
 *
 *     r | s[r] | action                       | window      | A,B,C | have |  l | best
 *   ----+------+------------------------------+-------------+-------+------+----+--------
 *     0 |  A   | add, A covered -> have++     | A           | 1,0,0 |  1   |  0 | -
 *     1 |  D   | add (not in t)               | AD          | 1,0,0 |  1   |  0 | -
 *     2 |  O   | add (not in t)               | ADO         | 1,0,0 |  1   |  0 | -
 *     3 |  B   | add, B covered -> have++     | ADOB        | 1,1,0 |  2   |  0 | -
 *     4 |  E   | add (not in t)               | ADOBE       | 1,1,0 |  2   |  0 | -
 *     5 |  C   | add, C covered -> have++     | ADOBEC      | 1,1,1 |  3   |  0 | -
 *     5 |      | len 6 best; drop A, have--   | ADOBEC      | 0,1,1 |  2   |  1 | ADOBEC
 *     6 |  O   | add (not in t)               | DOBECO      | 0,1,1 |  2   |  1 | ADOBEC
 *     7 |  D   | add (not in t)               | DOBECOD     | 0,1,1 |  2   |  1 | ADOBEC
 *     8 |  E   | add (not in t)               | DOBECODE    | 0,1,1 |  2   |  1 | ADOBEC
 *     9 |  B   | add, extra B, have same      | DOBECODEB   | 0,2,1 |  2   |  1 | ADOBEC
 *    10 |  A   | add, A covered -> have++     | DOBECODEBA  | 1,2,1 |  3   |  1 | ADOBEC
 *    10 |      | len 10 not shorter; drop D   | DOBECODEBA  | 1,2,1 |  3   |  2 | ADOBEC
 *    10 |      | len 9 not shorter; drop O    | OBECODEBA   | 1,2,1 |  3   |  3 | ADOBEC
 *    10 |      | len 8 not shorter; drop B    | BECODEBA    | 1,1,1 |  3   |  4 | ADOBEC
 *    10 |      | len 7 not shorter; drop E    | ECODEBA     | 1,1,1 |  3   |  5 | ADOBEC
 *    10 |      | len 6 not shorter; drop C,   | CODEBA      | 1,1,0 |  2   |  6 | ADOBEC
 *       |      |   have--                     |             |       |      |    |
 *    11 |  N   | add (not in t)               | ODEBAN      | 1,1,0 |  2   |  6 | ADOBEC
 *    12 |  C   | add, C covered -> have++     | ODEBANC     | 1,1,1 |  3   |  6 | ADOBEC
 *    12 |      | len 7 not shorter; drop O    | ODEBANC     | 1,1,1 |  3   |  7 | ADOBEC
 *    12 |      | len 6 not shorter; drop D    | DEBANC      | 1,1,1 |  3   |  8 | ADOBEC
 *    12 |      | len 5 best; drop E           | EBANC       | 1,1,1 |  3   |  9 | EBANC
 *    12 |      | len 4 best; drop B, have--   | BANC        | 1,0,1 |  2   | 10 | BANC
 *
 *   Returns "BANC" (result = [9, 12], minLength = 4).
 *   Dropping the first B at l = 3 kept have at 3: the window still had the second B.
 *
 * EDGE CASES
 *   s = "a", t = "a"         -> "a"    the whole string is the window
 *   s = "a", t = "aa"        -> ""     a never reaches 2, the window is never valid
 *   s = "aa", t = "aa"       -> "aa"   a is covered only when its count reaches 2, at r = 1
 *   s = "bba", t = "ab"      -> "ba"   the first b is dropped because the second b still covers it
 *   s = "abc", t = "d"       -> ""     d never appears
 *   s = "", t = "a"          -> ""     early return (LeetCode guarantees both lengths >= 1)
 *
 * COMMON MISTAKES
 *   - have++ every time a char of t is added: extra copies (the second B) are counted twice.
 *     Only count the moment the window count becomes equal to the need.
 *   - have-- every time a char of t leaves: only when its count drops below what t needs.
 *   - substring(l, r) instead of substring(l, r + 1): the end is exclusive, the last char is lost.
 *   - Comparing two Integer counts with ==: it only works for small cached values (-128..127).
 *     Use Objects.equals or compare int values.
 *
 * COMPLEXITY
 *   O(m + n) time: each char of s enters and leaves the window at most once.
 *   O(k) extra space, k = number of distinct chars in s and t.
 *
 * RELATED
 *   LeetCode 76 (MinimumWindowSubstring, FAQ/): same problem, also in FAQ/.
 *   LeetCode 209 (MinimumSizeSubArray, SlidingWindow/): same "shrink while valid, record the shortest" loop,
 *     but valid means "sum >= target".
 *   LeetCode 3 (LongestSubstring, SlidingWindow/): looks for the longest valid window instead of the shortest.
 *   LeetCode 30 (SubstringWithConcatenation, SlidingWindow/): also compares window counts with a target map,
 *     but counts whole words in a fixed-size window.
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s,t));
    }

    public static String minWindow(String s, String t) {
        if(s.isEmpty() || t.isEmpty())
            return "";

        Map<Character,Integer> countMap = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();

        for (int i=0; i < t.length(); i++) {
            countMap.put(t.charAt(i),countMap.getOrDefault(t.charAt(i),0) + 1);
        }

        int have = 0;
        int need = countMap.size();
        int l = 0;
        int minLength = Integer.MAX_VALUE;
        int[] result = new int[2];

        for (int r=0; r < s.length(); r++) {
            Character c = s.charAt(r);
            window.put(c,window.getOrDefault(c,0) + 1);

            if (shouldIncrementHavePointer(c,window,countMap))
                have++;

            while (have == need) {

                if ((r - l + 1) < minLength) {
                    result[0] = l;
                    result[1] = r;
                    minLength = r - l + 1;
                }

                window.put(s.charAt(l),window.get(s.charAt(l)) - 1);

                if(shouldDecrementHavePointer(s.charAt(l),window,countMap))
                    have--;

                l++;
            }
        }

        int left = result[0];
        int right = result[1];

        return minLength != Integer.MAX_VALUE ? s.substring(left,right+1) : "";
    }

    public static boolean shouldIncrementHavePointer(Character c, Map<Character,Integer> window, Map<Character,Integer> countMap) {
        return countMap.containsKey(c) && Objects.equals(window.get(c), countMap.get(c));
    }

    public static boolean shouldDecrementHavePointer(Character c, Map<Character,Integer> window, Map<Character,Integer> countMap) {
        return countMap.containsKey(c) && window.get(c) < countMap.get(c);
    }

}

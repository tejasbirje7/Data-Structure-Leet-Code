/**
 * LeetCode 387. First Unique Character in a String
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 *
 * PROBLEM
 *   - Input: a string s of lower-case letters (1..10^5 long).
 *   - Return: the index of the first character that appears exactly once, or -1 if there is none.
 *
 * EXAMPLE
 *   Input: s = "leetcode"
 *   Output: 0
 *   Explanation: The character 'l' at index 0 is the first character that does not occur at any other index.
 *
 * HOW TO THINK
 *   - Clue: "appears once" = count every character first; "first" = then scan in order.
 *   - Technique: a frequency table. Only 26 letters, so int[26] works as the hash map.
 *   - Rule that stays true: after pass 1, count[c] is the total number of times c occurs in s.
 *   - Decision in pass 2: count[s[i]] == 1 -> return i (the first such i wins).
 *   - Return: -1 if pass 2 finds nothing.
 *
 * STEPS
 *   1. count = int[26].
 *   2. Pass 1: for each char c, count[c - 'a']++.
 *   3. Pass 2: for i = 0..n-1, if count[s[i] - 'a'] == 1, return i.
 *   4. Return -1.
 *
 * DRY RUN   s = "loveleetcode"
 *
 *   Pass 1 counts: l=2, o=2, v=1, e=4, t=1, c=1, d=1
 *
 *    i | s[i] | count | action
 *   ---+------+-------+----------------
 *    0 |  l   |   2   | not unique, go on
 *    1 |  o   |   2   | not unique, go on
 *    2 |  v   |   1   | unique, return 2
 *
 *   Returns 2.
 *
 * EDGE CASES
 *   "leetcode"   -> 0    first char is already unique
 *   "aabb"       -> -1   every char repeats
 *   "z"          -> 0    one char
 *   "aadadaad"   -> -1   counts are 5 and 3, none is 1
 *   "abcabd"     -> 2    'c' is unique; 'd' is unique too but comes later
 *
 * COMMON MISTAKES
 *   - Checking uniqueness while counting (one pass): a char seen once so far may repeat later.
 *   - Scanning the count table in letter order (a..z) instead of the string order: returns
 *     the alphabetically first unique letter, not the first by position.
 *   - Using s.indexOf(c) == s.lastIndexOf(c) for each char: correct but O(n^2).
 *
 * COMPLEXITY
 *   O(n) time (two passes), O(1) extra space (26 counters).
 *
 * RELATED
 *   LeetCode 242 (ValidAnagram, HashMap/): the same int[26] counting table.
 *   LeetCode 383 (RansomNote, HashMap/): count one string, then check another against the counts.
 */
public class FirstUniqueCharacter {

    public static void main(String[] args) {
        System.out.println(firstUniqChar("loveleetcode"));
    }

    public static int firstUniqChar(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }
}

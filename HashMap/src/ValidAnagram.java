import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * LeetCode 242. Valid Anagram
 * https://leetcode.com/problems/valid-anagram/
 *
 * PROBLEM
 *   - Input: two strings s and t.
 *   - Return: true if t is an anagram of s (the same letters, the same number of times), else false.
 *
 * EXAMPLE
 *   Input: s = "anagram", t = "nagaram"
 *   Output: true
 *
 * HOW TO THINK
 *   - Clue: anagram = same letters with the same counts; the order doesn't matter.
 *   - Technique: count the letters of both strings in two hash maps, then compare the counts.
 *   - Quick exit: strings of different lengths can't be anagrams.
 *   - Rule that stays true: after the first loop, sMap and tMap hold the full letter counts.
 *   - Decision: for each letter c of s: t doesn't have c, or the counts differ -> return false.
 *   - Why checking only the letters of s is enough: the lengths are equal and every letter of s
 *     has the same count in t, so t has no room for any other letter.
 *   - Return: true if no letter failed.
 *
 * STEPS
 *   1. If the lengths differ, return false.
 *   2. Loop i over both strings: count s[i] in sMap and t[i] in tMap.
 *   3. For each letter c of s: if tMap lacks c or tMap[c] != sMap[c], return false.
 *   4. Return true.
 *
 * DRY RUN   s = "aacc", t = "ccaa"   (maps shown after the step)
 *
 *    phase | i | s[i] | t[i] | check          | sMap      | tMap
 *   -------+---+------+------+----------------+-----------+-----------
 *    count | 0 |  a   |  c   |                | {a=1}     | {c=1}
 *    count | 1 |  a   |  c   |                | {a=2}     | {c=2}
 *    count | 2 |  c   |  a   |                | {a=2,c=1} | {a=1,c=2}
 *    count | 3 |  c   |  a   |                | {a=2,c=2} | {a=2,c=2}
 *    check | 0 |  a   |      | t a=2, s a=2   | same      | same
 *    check | 1 |  a   |      | t a=2, s a=2   | same      | same
 *    check | 2 |  c   |      | t c=2, s c=2   | same      | same
 *    check | 3 |  c   |      | t c=2, s c=2   | same      | same
 *
 *   Returns true: both strings have two a's and two c's.
 *
 * EDGE CASES
 *   "rat", "car"     -> false   t has no 't'
 *   "a", "ab"        -> false   the length check fails
 *   "aab", "abb"     -> false   same letters, different counts
 *   "", ""           -> true    nothing to compare
 *   200 a's, 200 a's -> true    counts above 127 are compared with Objects.equals, not ==
 *
 * COMMON MISTAKES
 *   - Comparing the Integer counts with == or !=: only -128..127 are cached, so larger counts
 *     compare as different objects. Use Objects.equals (the code does).
 *   - Checking only that each letter exists (a Set): "aab", "abb" would return true.
 *   - Skipping the length check while checking only the letters of s: "a", "ab" would return true.
 *
 * COMPLEXITY
 *   O(n) time, O(k) extra space, k = distinct letters (at most 26 for lowercase).
 *
 * RELATED
 *   LeetCode 49 (GroupAnagrams, HashMap/): uses the letter-count map as a key to group many words.
 *   LeetCode 383 (RansomNote, HashMap/): the magazine only needs enough of each letter, not exactly the same.
 */
public class ValidAnagram {

    public static void main(String[] args) {
        String s = "aacc";
        String t = "ccaa";

        System.out.println(isAnagram(s,t));
    }

    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;

        Map<Character,Integer> sMap = new HashMap<>();
        Map<Character,Integer> tMap = new HashMap<>();

        for (int i=0; i < s.length(); i++) {
            sMap.put(s.charAt(i),sMap.getOrDefault(s.charAt(i),0)+1);
            tMap.put(t.charAt(i),tMap.getOrDefault(t.charAt(i),0)+1);
        }

        for (Character c : s.toCharArray()) {
            if(!tMap.containsKey(c) || !Objects.equals(tMap.get(c), sMap.getOrDefault(c, 0)))
                return false;
        }

        return true;

    }
}

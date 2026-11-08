import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 383. Ransom Note
 * https://leetcode.com/problems/ransom-note/
 *
 * PROBLEM
 *   - Input: two strings, ransomNote and magazine (lowercase letters).
 *   - Return: true if ransomNote can be built from the letters of magazine, false otherwise.
 *   - Each letter in magazine can be used only once.
 *
 * EXAMPLE
 *   Input: ransomNote = "a", magazine = "b"
 *   Output: false
 *
 * HOW TO THINK
 *   - Clue: "each letter can only be used once" means you compare how many of each letter you have
 *     with how many you need.
 *   - Technique: count the magazine's letters in a hash map (the supply), then spend them
 *     while reading the note.
 *   - Rule that stays true: countMap[c] = how many c's from magazine are still unused.
 *   - Decision: c not in map  -> return false (magazine never had it).
 *               count is 0    -> return false (all copies already used).
 *               otherwise     -> use one: count - 1.
 *   - Return: true if every letter of the note was paid for.
 *
 * STEPS
 *   1. Count every letter of magazine into countMap.
 *   2. For each letter c of ransomNote:
 *   3.   if c is missing or its count is 0, return false; else decrease its count by 1.
 *   4. Return true.
 *
 * DRY RUN   ransomNote = "aa", magazine = "ab"   (countMap shown after the step)
 *
 *    phase      | letter | check           | action        | countMap
 *   ------------+--------+-----------------+---------------+-------------
 *    count mag. |   a    |                 | count it      | {a=1}
 *    count mag. |   b    |                 | count it      | {a=1, b=1}
 *    read note  |   a    | a in map, a = 1 | use one       | {a=0, b=1}
 *    read note  |   a    | a in map, a = 0 | none left     | return false
 *
 *   Returns false: magazine has only one 'a', the note needs two.
 *
 * EDGE CASES
 *   "a", "b"        -> false   'a' is not in the map at all
 *   "aa", "aab"     -> true    two a's available, two used
 *   "aab", "baa"    -> true    order doesn't matter, only counts
 *   "", "abc"       -> true    nothing is needed
 *   "abc", ""       -> false   the map is empty, 'a' is missing
 *
 * COMMON MISTAKES
 *   - Not decreasing the count: "aa", "ab" would return true. Each letter can be used once.
 *   - Using a Set of magazine letters: it loses the counts, same wrong answer as above.
 *   - Counting the note instead and only checking that letters exist in magazine: counts must be compared.
 *
 * COMPLEXITY
 *   O(m + n) time, O(k) extra space, k = distinct letters (at most 26).
 *
 * RELATED
 *   LeetCode 242 (ValidAnagram, HashMap/): the counts must match exactly, not just be enough.
 *   LeetCode 76 (MinimumWindowSubstring, SlidingWindow/): the same "have enough of each letter" check,
 *     but on a moving window of s.
 */
public class RansomNote {
    public static void main(String[] args) {
        String ransomNote = "aa";
        String magazine = "ab";
        System.out.println(canConstruct(ransomNote,magazine));
    }

    public static boolean canConstruct(String ransomNote, String magazine) {

        Map<Character,Integer> countMap = new HashMap<>();

        for (Character c : magazine.toCharArray()) {
            countMap.put(c,countMap.getOrDefault(c,0) + 1);
        }

        for (Character c : ransomNote.toCharArray()) {
            if (countMap.containsKey(c)) {
                if (countMap.get(c) == 0)
                    return false;
                countMap.put(c,countMap.get(c) - 1);
            } else {
                return false;
            }
        }
        return true;
    }
}

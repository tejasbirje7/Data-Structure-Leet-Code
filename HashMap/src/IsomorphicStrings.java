import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 205. Isomorphic Strings
 * https://leetcode.com/problems/isomorphic-strings/
 *
 * PROBLEM
 *   - Input: two strings s and t of the same length.
 *   - Task: check if every character of s can be replaced to get t, keeping the order.
 *   - Each character maps to exactly one character, and no two characters map to the same one.
 *     A character may map to itself.
 *   - Return: true or false.
 *
 * EXAMPLE
 *   Input: s = "egg", t = "add"
 *   Output: true
 *
 * HOW TO THINK
 *   - Clue: "no two characters may map to the same character" means the mapping is one-to-one,
 *     so it must be checked in both directions.
 *   - Technique: two hash maps. sMap: s char -> t char. tMap: t char -> s char.
 *     One map alone misses two s chars mapping to the same t char.
 *   - Rule that stays true: every pair (s[j], t[j]) with j < i agrees with both maps.
 *   - Decision: c1 already maps to something other than c2 -> return false.
 *               c2 already maps back to something other than c1 -> return false.
 *               otherwise -> record c1 -> c2 and c2 -> c1.
 *   - Return: true if the loop finishes with no conflict.
 *
 * STEPS
 *   1. sMap = {}, tMap = {}.
 *   2. For each index i: c1 = s[i], c2 = t[i].
 *   3. If sMap has c1 with a value != c2, or tMap has c2 with a value != c1, return false.
 *   4. Put c1 -> c2 in sMap and c2 -> c1 in tMap.
 *   5. After the loop, return true.
 *
 * DRY RUN   s = "egg", t = "add"   (maps shown after the step)
 *
 *    i | c1 | c2 | check                 | sMap      | tMap
 *   ---+----+----+-----------------------+-----------+-----------
 *    0 | e  | a  | both new, record      | {e=a}     | {a=e}
 *    1 | g  | d  | both new, record      | {e=a,g=d} | {a=e,d=g}
 *    2 | g  | d  | g->d and d->g match   | {e=a,g=d} | {a=e,d=g}
 *
 *   Returns true: e->a, g->d.
 *
 * EDGE CASES
 *   "foo", "bar"      -> false   o->a, then o needs r (sMap conflict)
 *   "paper", "title"  -> true    p->t, a->i, e->l, r->e
 *   "badc", "baba"    -> false   d needs b, but b already maps back to b (tMap conflict)
 *   "a", "a"          -> true    a character may map to itself
 *   "", ""            -> true    the loop never runs
 *   "ab", "a"         -> crashes with StringIndexOutOfBoundsException (t is shorter);
 *                        LeetCode guarantees equal lengths
 *   "a", "ab"         -> true (should be false): extra chars of t are never read;
 *                        LeetCode guarantees equal lengths
 *
 * COMMON MISTAKES
 *   - Using only sMap: "badc", "baba" would return true. Check the reverse map too.
 *   - Comparing Character objects with != : only values 0..127 are cached,
 *     so other characters compare as different objects. Use equals (the code does).
 *   - Checking only containsKey: you must also compare the stored value with the new character.
 *
 * COMPLEXITY
 *   O(n) time, O(k) extra space, k = number of distinct characters.
 *
 * RELATED
 *   LeetCode 290 (WordPattern, HashMap/): the same two-map check, between pattern letters and words.
 */
public class IsomorphicStrings {

    public static void main(String[] args) {
        String s = "egg";
        String t = "add";
        System.out.println(isIsomorphic(s,t));
    }

    public static boolean isIsomorphic(String s, String t) {
        Map<Character,Character> sMap = new HashMap<>();
        Map<Character,Character> tMap = new HashMap<>();

        for (int i=0; i < s.length(); i++) {

            Character c1 = s.charAt(i);
            Character c2 = t.charAt(i);

            if(sMap.containsKey(c1) && !sMap.get(c1).equals(c2) ||
                tMap.containsKey(c2) && !tMap.get(c2).equals(c1)) {
                return false;
            }

            sMap.put(c1,c2);
            tMap.put(c2,c1);
        }
        return true;

    }

}

/**
 * LeetCode 14. Longest Common Prefix
 * https://leetcode.com/problems/longest-common-prefix/
 *
 * PROBLEM
 *   - Input: an array of strings (LeetCode guarantees at least one; this version returns "" for none).
 *   - Return: the longest prefix shared by all of them, or "" if there is none.
 *
 * EXAMPLE
 *   Input: strs = ["flower","flow","flight"]
 *   Output: "fl"
 *
 * HOW TO THINK
 *   - Clue: the common prefix can only get shorter as you see more strings,
 *     and it can never be longer than the first string.
 *   - Technique: horizontal scanning. Start with the whole first string as the prefix,
 *     then chop it until it fits each next string.
 *   - Rule that stays true: after handling strs[0..i], prefix = the longest common prefix of those strings.
 *   - Decision: strs[i].indexOf(prefix) == 0 -> strs[i] starts with prefix: go to the next string.
 *               otherwise -> chop the last character off prefix and check again.
 *   - Why it always stops: "".indexOf on any string gives 0, so the while loop ends at "" at the latest.
 *   - Return: prefix after the last string.
 *
 * STEPS
 *   1. If strs is null or empty, return "".
 *   2. prefix = strs[0].
 *   3. Loop i from 1 to the last string.
 *   4. While strs[i].indexOf(prefix) != 0: prefix = prefix.substring(0, prefix.length() - 1).
 *   5. Return prefix.
 *
 * DRY RUN   strs = ["flower","flow","flight","flew"]   (prefix is the value after the step)
 *
 *    i | strs[i]  | prefix tried | indexOf | action      | prefix
 *   ---+----------+--------------+---------+-------------+---------
 *    1 | "flow"   | "flower"     |   -1    | chop        | "flowe"
 *    1 | "flow"   | "flowe"      |   -1    | chop        | "flow"
 *    1 | "flow"   | "flow"       |    0    | fits, next  | "flow"
 *    2 | "flight" | "flow"       |   -1    | chop        | "flo"
 *    2 | "flight" | "flo"        |   -1    | chop        | "fl"
 *    2 | "flight" | "fl"         |    0    | fits, next  | "fl"
 *    3 | "flew"   | "fl"         |    0    | fits, next  | "fl"
 *
 *   Returns "fl".
 *
 * EDGE CASES
 *   ["dog","racecar","car"]  -> ""        "dog" is chopped all the way down to ""
 *   ["alone"]                -> "alone"   one string: the loop never runs
 *   ["ab","abc"]             -> "ab"      the first string is already the prefix
 *   ["abc","ab"]             -> "ab"      the first string is longer and gets chopped
 *   ["","b"]                 -> ""        "" fits every string
 *   ["c","acc"]              -> ""        "c" is inside "acc" but not at index 0
 *   []                       -> ""        no strings, returned by the guard before strs[0]
 *                                         (LeetCode guarantees at least one string anyway)
 *
 * COMMON MISTAKES
 *   - Checking indexOf(prefix) != -1 ("contains") instead of == 0 ("starts with"):
 *     ["c","acc"] would return "c".
 *   - Comparing character by character without checking lengths: index out of bounds when a later
 *     string is shorter than the prefix. indexOf / startsWith handle that for you.
 *   - No guard for an empty array: strs[0] crashes with ArrayIndexOutOfBoundsException.
 *     Check the length first and return "".
 *
 * COMPLEXITY
 *   At most n + m checks (n strings, m = length of strs[0]; each chop removes one character for good).
 *   Each check is an indexOf scan, so the worst case is about O(S * m) time, S = total characters;
 *   startsWith instead of indexOf would make it O(S). O(m) extra space for the prefix strings.
 *
 * RELATED
 *   LeetCode 28 (FirstOccurrenceOfString, ArraysAndStrings/): finds where one string appears inside
 *     another, at any index; here only index 0 counts.
 */
public class LongestPrefixString {

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight","flew"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i=1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0,prefix.length()-1);
            }
        }
        return prefix;
    }
}

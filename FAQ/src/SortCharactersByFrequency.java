import java.util.*;

/**
 * LeetCode 451. Sort Characters By Frequency
 * https://leetcode.com/problems/sort-characters-by-frequency/
 *
 * PROBLEM
 *   - Input: a string s (letters and digits; upper and lower case are different characters).
 *   - Return: s rearranged so characters appear in decreasing order of how often they occur.
 *   - Equal characters must sit together; ties between characters can be in any order.
 *
 * EXAMPLE
 *   Input: "tree"
 *   Output: "eert" or "eetr"
 *
 * HOW TO THINK
 *   - Clue: "sort by frequency" = first count, then order by the count.
 *   - Technique: frequency map, then bucket sort by count.
 *     A count is between 1 and n, so walk the counts from n down to 1 instead of sorting.
 *   - Rule that stays true: charToCount[c] = how many times c appears;
 *     countToChar[f] = all characters that appear exactly f times.
 *   - Decision: in the build loop, if bucket i exists, append each of its characters i times;
 *               otherwise skip that count.
 *   - Return: the built string; it has exactly n characters.
 *
 * STEPS
 *   1. Count every character into charToCount.
 *   2. For each (char, freq) entry, add char to the list countToChar[freq].
 *   3. Loop i from s.length() down to 1; if countToChar has i, append each char in it i times.
 *   4. Return the result string.
 *
 * DRY RUN   s = "tree"
 *
 *   charToCount = {e=2, r=1, t=1}
 *   countToChar = {1=[r, t], 2=[e]}   (HashMap order; ties could come out in another order)
 *
 *    i | bucket countToChar[i] | action              | result
 *   ---+-----------------------+---------------------+--------
 *    4 | none                  | skip                | ""
 *    3 | none                  | skip                | ""
 *    2 | [e]                   | append "ee"         | "ee"
 *    1 | [r, t]                | append "r", "t"     | "eert"
 *
 *   Returns "eert".
 *
 * EDGE CASES
 *   "cccaaa"  -> "aaaccc"   tie at count 3; "cccaaa" is just as valid
 *   "Aabb"    -> "bbAa"     'A' and 'a' are different characters
 *   "a"       -> "a"        one character, bucket 1
 *   "zzzz"    -> "zzzz"     everything the same: one bucket at count n
 *   ""        -> ""         the build loop never runs; LeetCode guarantees length >= 1
 *
 * COMMON MISTAKES
 *   - Sorting the characters of s with a comparator on count: equal-count characters can get
 *     interleaved (e.g. "abab"). Group by character first, then output each group whole.
 *   - Using a String and += in the loop: O(n^2). Use a StringBuilder.
 *   - Treating upper and lower case as the same character.
 *
 * COMPLEXITY
 *   O(n) time: counting is O(n), the build loop runs n times and appends n characters in total.
 *   O(n) extra space for the maps and the result.
 *
 * RELATED
 *   LeetCode 895 (FreqStack, FAQ/): also groups values by frequency (frequency -> stack of values)
 *     and serves the highest frequency first.
 *   LeetCode 242 (ValidAnagram, HashMap/): the same character-count map, compared between two strings.
 */
public class SortCharactersByFrequency {

    public String frequencySort(String s) {
        StringBuilder result = new StringBuilder();

        Map<Character, Integer> charToCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charToCount.put(c, charToCount.getOrDefault(c, 0) + 1);
        }

        Map<Integer, List<Character>> countToChar = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : charToCount.entrySet()) {
            int freq = entry.getValue();
            countToChar.putIfAbsent(freq, new ArrayList<>());
            countToChar.get(freq).add(entry.getKey());
        }

        for (int i = s.length(); i > 0; i--) {
            if (countToChar.containsKey(i)) {
                for (char c : countToChar.get(i)) {
                    result.append(String.valueOf(c).repeat(i));
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        SortCharactersByFrequency solver = new SortCharactersByFrequency();

        System.out.println(solver.frequencySort("tree"));    // eert or eetr
        System.out.println(solver.frequencySort("cccaaa"));  // cccaaa or aaaccc
        System.out.println(solver.frequencySort("Aabb"));    // bbAa or bbaA
    }
}

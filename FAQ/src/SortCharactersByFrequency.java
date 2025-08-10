import java.util.*;

/*
* Example 1:
Input: "tree"
Output: "eert" or "eetr"

Example 2:
Input: "cccaaa"
Output: "cccaaa" or "aaaccc"

Example 3:
Input: "Aabb"
Output: "bbAa" or "bbaA"
*
* Count how many times each character appears (frequency map).
Create a mapping from frequency → list of characters (bucket sort idea).
Iterate from the highest frequency down to 1, and append characters repeatedly to the result.
Time Complexity: O(n) to count and build buckets, O(n) to build output → overall O(n).
Space Complexity: O(n) for maps and result string.
* */
public class SortCharactersByFrequency {

    public String frequencySort(String s) {
        StringBuilder result = new StringBuilder();

        // Step 1: Count frequency of each character
        Map<Character, Integer> charToCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charToCount.put(c, charToCount.getOrDefault(c, 0) + 1);
        }

        // Step 2: Map frequency to characters (bucket sort)
        Map<Integer, List<Character>> countToChar = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : charToCount.entrySet()) {
            int freq = entry.getValue();
            countToChar.putIfAbsent(freq, new ArrayList<>());
            countToChar.get(freq).add(entry.getKey());
        }

        // Step 3: Build string from highest frequency to lowest
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

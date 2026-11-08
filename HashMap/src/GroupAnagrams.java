import java.util.*;

/**
 * LeetCode 49. Group Anagrams
 * https://leetcode.com/problems/group-anagrams/
 *
 * PROBLEM
 *   - Input: an array of strings strs (lowercase English letters).
 *   - Task: put words that are anagrams of each other (same letters, same counts) in one group.
 *   - Return: the list of groups, in any order.
 *
 * EXAMPLE
 *   Input: strs = ["eat","tea","tan","ate","nat","bat"]
 *   Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * HOW TO THINK
 *   - Clue: "group" the words means: give each word a key, then put words with the same key together.
 *   - Technique: hash map from key -> list of words.
 *     Anagrams have exactly the same letter counts, so the letter-count map is the key.
 *   - Why a Map works as a key: HashMap equals/hashCode compare the contents,
 *     so {a=1,e=1,t=1} built from "eat" equals the one built from "tea".
 *   - Rule that stays true: every word seen so far sits in the list for its letter-count key.
 *   - Decision: key already in the map -> add the word to that list.
 *               key not in the map     -> start a new list with the word (computeIfAbsent).
 *   - Return: the map's values (the lists), in whatever order the HashMap gives.
 *
 * STEPS
 *   1. result = empty map: letter-count map -> list of words.
 *   2. For each word, build keyMap = how many times each letter appears.
 *   3. computeIfAbsent(keyMap, new list), then add the word to that list.
 *   4. Return new ArrayList<>(result.values()).
 *
 * DRY RUN   strs = ["eat","tea","tan","ate","nat","bat"]
 *   (map shown after the step; keys written by their letters, e.g. aet = {a=1,e=1,t=1})
 *
 *    i | word | keyMap        | action            | map after
 *   ---+------+---------------+-------------------+-----------------------------------------------
 *    0 | eat  | {a=1,e=1,t=1} | new key, new list | {aet:[eat]}
 *    1 | tea  | {a=1,e=1,t=1} | same key, add     | {aet:[eat,tea]}
 *    2 | tan  | {a=1,n=1,t=1} | new key, new list | {aet:[eat,tea], ant:[tan]}
 *    3 | ate  | {a=1,e=1,t=1} | same key, add     | {aet:[eat,tea,ate], ant:[tan]}
 *    4 | nat  | {a=1,n=1,t=1} | same key, add     | {aet:[eat,tea,ate], ant:[tan,nat]}
 *    5 | bat  | {a=1,b=1,t=1} | new key, new list | {aet:[eat,tea,ate], ant:[tan,nat], abt:[bat]}
 *
 *   Returns 3 groups: [[tan, nat], [bat], [eat, tea, ate]] (HashMap order; any order is accepted).
 *   The method also prints each keyMap as debug lines before the result.
 *
 * EDGE CASES
 *   [""]               -> [[""]]            empty word: its key is the empty map {}
 *   ["a"]              -> [["a"]]           one word, one group
 *   []                 -> []                the loop never runs
 *   ["aab","abb"]      -> [[abb],[aab]]     same letters, different counts, so different keys
 *   ["ab","ba","abc"]  -> [[ab,ba],[abc]]   one extra letter changes the key
 *
 * COMMON MISTAKES
 *   - Using the set of letters as the key: "aab" and "abb" would be grouped. Count each letter.
 *   - Sorting the word and using the char[] as the key: arrays use identity equals, so nothing groups.
 *     Turn it into a String first (new String(chars)).
 *   - Reusing one keyMap for every word: keys already in the map would change under it.
 *     Build a new keyMap per word (the code does).
 *
 * COMPLEXITY
 *   O(n * k) time and O(n * k) extra space, for n words of length up to k.
 *   - Each key is a whole HashMap; a 26-int count array turned into a String is a lighter key.
 *   - Old TODO from this file: "Solve this without using entire word as a key in map".
 *
 * RELATED
 *   LeetCode 242 (ValidAnagram, HashMap/): checks whether two words are anagrams, no grouping.
 *   LeetCode 451 (SortCharactersByFrequency, FAQ/): builds the same letter-count map, then sorts by count.
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strs));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<Map<Character,Integer>,List<String>> result = new HashMap<>();

        for (String str : strs) {
            Map<Character, Integer> keyMap = new HashMap<>();
            for (Character c : str.toCharArray()) {
                keyMap.put(c, keyMap.getOrDefault(c, 0) + 1);
            }
            keyMap.forEach((key, value) -> System.out.println(key + ": " + value));
            result.computeIfAbsent(keyMap, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(result.values());
    }
}

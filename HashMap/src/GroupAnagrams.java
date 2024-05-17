import java.util.*;

public class GroupAnagrams {

    // #TODO Solve this without using entire word as a key in map
    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strs));
    }

    /**
     * Input: strs = ["eat","tea","tan","ate","nat","bat"]
     * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
     *
     * Input: strs = [""]
     * Output: [[""]]
     *
     * Input: strs = ["a"]
     * Output: [["a"]]
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<Map<Character,Integer>,List<String>> result = new HashMap<>();

        for (String str : strs) {
            Map<Character, Integer> keyMap = new HashMap<>();
            for (Character c : str.toCharArray()) {
                keyMap.put(c, keyMap.getOrDefault(c, 0) + 1);
            }
            result.computeIfAbsent(keyMap, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(result.values());
    }
}

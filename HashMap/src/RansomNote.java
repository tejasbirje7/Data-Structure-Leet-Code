import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    public static void main(String[] args) {
        String ransomNote = "a";
        String magazine = "b";
        System.out.println(canConstruct(ransomNote,magazine));
    }

    /**
     * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
     * Each letter in magazine can only be used once in ransomNote.
     *
     * Input: ransomNote = "a", magazine = "b"
     * Output: false
     *
     * Input: ransomNote = "aa", magazine = "ab"
     * Output: false
     *
     * Input: ransomNote = "aa", magazine = "aab"
     * Output: true
     */
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

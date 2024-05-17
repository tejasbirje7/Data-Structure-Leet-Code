import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ValidAnagram {

    public static void main(String[] args) {
        String s = "aacc";
        String t = "ccac";

        System.out.println(isAnagram(s,t));
    }

    /**
     * Input: s = "anagram", t = "nagaram"
     * Output: true
     *
     * Input: s = "rat", t = "car"
     * Output: false
     */
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WordPattern {
    public static void main(String[] args) {
        String pattern = "abba";
        String s = "dog cat cat dog";
        System.out.println(wordPattern(pattern,s));
    }

    /**
     * Input: pattern = "abba", s = "dog cat cat dog"
     * Output: true
     *
     * Input: pattern = "abba", s = "dog cat cat fish"
     * Output: false
     *
     * Input: pattern = "aaaa", s = "dog cat cat dog"
     * Output: false
     */
    public static boolean wordPattern(String pattern, String s) {

        Map<String , Character> wordToChar = new HashMap<>();
        Map<Character, String> charToWord = new HashMap<>();

        String[] words = s.split(" ");

        if (pattern.length() != words.length) return false;

        for (int i=0; i < pattern.length(); i++) {
            if(wordToChar.containsKey(words[i]) && !wordToChar.get(words[i]).equals(pattern.charAt(i))) {
                return false;
            } else if (charToWord.containsKey(pattern.charAt(i)) && !charToWord.get(pattern.charAt(i)).equals(words[i])) {
                return false;
            } else {
                wordToChar.put(words[i],pattern.charAt(i));
                charToWord.put(pattern.charAt(i),words[i]);
            }
        }
        return true;
    }
}

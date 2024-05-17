import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {

    public static void main(String[] args) {
        String s = "pwwkew";
//        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int maxLength = 0;
        Set<Character> window = new HashSet<>();

        while (right < s.length()) {
            while (window.contains(s.charAt(right))) {
                window.remove(s.charAt(left++));
            }
            window.add(s.charAt(right++));
            maxLength = Math.max(maxLength,right-left);
        }

        return maxLength;
    }
}

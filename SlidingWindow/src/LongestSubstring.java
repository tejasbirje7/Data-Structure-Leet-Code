import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {

    /**
     * Given a string s, find the length of the longest
     * substring
     *  without repeating characters.
     *
     * Input: s = "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     *
     * Input: s = "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     *
     * Input: s = "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     * Notice that the answer must be a substring, "pwke"
     * is a subsequence and not a substring.
     */
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

public class IsSubsequence {

    public static void main(String[] args) {
        String s = "";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s,t));
    }

    /**
     * Given two strings s and t, return true if
     * s is a subsequence of t, or false otherwise.
     *
     * A subsequence of a string is a new string that is formed
     * from the original string by deleting some (can be none) of the
     * characters without disturbing the relative positions of the
     * remaining characters. (i.e., "ace" is a subsequence of "abcde"
     * while "aec" is not).
     *
     * Input: s = "abc", t = "ahbgdc"
     * Output: true
     *
     * Input: s = "axc", t = "ahbgdc"
     * Output: false
     */
    public static boolean isSubsequence(String s, String t) {

        int matchPointer = 0;
        int wordPointer = 0;

        while (matchPointer < s.length() && wordPointer < t.length()) {
            if(Character.toLowerCase(t.charAt(wordPointer)) == Character.toLowerCase(s.charAt(matchPointer)))
                matchPointer++;
            wordPointer++;
        }
        return matchPointer == s.length();
    }
}

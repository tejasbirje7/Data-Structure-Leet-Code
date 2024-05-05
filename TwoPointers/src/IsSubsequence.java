public class IsSubsequence {

    public static void main(String[] args) {
        String s = "";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s,t));
    }

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

public class ReverseWordsInString {

    public static void main(String[] args) {
        String s = "  the sky is blue ";
        System.out.println(reverseWords(s));
    }

    /**
     * Input: s = "the sky is blue"
     * Output: "blue is sky the"
     * <p>
     * Input: s = "  hello world  "
     * Output: "world hello"
     * Explanation: Your reversed string should not contain leading or trailing spaces.
     * <p>
     * Input: s = "a good   example"
     * Output: "example good a"
     * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
     */
    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder(" ");
        int i = 0,f = 0;
        for(; i < s.length(); i++){
            if(s.charAt(i) != ' '){
                temp.append(s.charAt(i));
                f = 1;
            }
            else {
                if(f == 0) continue;
                sb.insert(0, temp);
                temp.setLength(0);
                temp.insert(0, " ");
                f = 0;
            }
        }
        if(f == 1) sb.insert(0, temp);
        sb.delete(0, 1);
        return String.valueOf(sb);
    }
}

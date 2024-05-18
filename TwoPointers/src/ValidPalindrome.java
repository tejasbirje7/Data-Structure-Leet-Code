import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidPalindrome {

    /**
     * Input: s = "A man, a plan, a canal: Panama"
     * Output: true
     * Explanation: "amanaplanacanalpanama" is a palindrome.
     *
     * Input: s = "race a car"
     * Output: false
     * Explanation: "raceacar" is not a palindrome.
     *
     * Input: s = " "
     * Output: true
     * Explanation: s is an empty string "" after removing non-alphanumeric characters.
     * Since an empty string reads the same forward and backward, it is a palindrome.
     */
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        Pattern p = Pattern.compile("[^A-Za-z0-9]");

        int start = 0;
        int last = s.length() - 1;

        Matcher m = p.matcher(s);
        s = s.replaceAll("\\s+","");
        s = s.replaceAll("[^A-Za-z0-9]","");

        while (last > start) {
            if(Character.toLowerCase(s.charAt(last)) == Character.toLowerCase(s.charAt(start))) {
                last--;
                start++;
            } else {
                System.out.println("Not a palindrome");
            }
        }
    }
}

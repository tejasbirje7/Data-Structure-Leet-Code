import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidPalindrome {

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

public class FirstOccurrenceOfString {

    public static void main(String[] args) {
        String haystack = "abc";
        String needle = "c";
        System.out.println(strStr(haystack,needle));
    }

    public static int strStr(String haystack, String needle) {
        int i = 0;
        int n = needle.length();
        if(needle.length() == haystack.length()) {
            return needle.equals(haystack) ? 0 : -1;
        }
        while (i <= haystack.length() && i + needle.length() <= haystack.length()) {
            String s = haystack.substring(i,i+n);
            if(s.equals(needle)) {
                return i;
            } else {
                i++;
            }
        }
        return -1;
    }
}

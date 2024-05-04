public class FirstOccurrenceOfString {

    public static void main(String[] args) {
        String haystack = "abc";
        String needle = "c";
        System.out.println(strStr(haystack,needle));
    }

    /**
     * Input: haystack = "sadbutsad", needle = "sad"
     * Output: 0
     * Explanation: "sad" occurs at index 0 and 6.
     * The first occurrence is at index 0, so we return 0.
     */
    public static int strStr(String haystack, String needle) {
        int i = 0;
        int n = needle.length();

        // Handle a case if haystack and needle only contains same character
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

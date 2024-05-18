import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenation {

    public static void main(String[] args) {
        String s = "barfoofoobarthefoobarman";
        String[] words = {"bar","foo","the"};
        System.out.println(findSubstring(s,words));
    }

    /**
     * You are given a string s and an array of strings words.
     * All the strings of words are of the same length.
     *
     * A concatenated string is a string that exactly contains
     * all the strings of any permutation of words concatenated.
     *
     * For example, if words = ["ab","cd","ef"],
     * then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab"
     * are all concatenated strings. "acdbef" is not a concatenated string
     * because it is not the concatenation of any permutation of words.
     * Return an array of the starting indices of all the
     * concatenated substrings in s. You can return the answer in any order.
     *
     * Input: s = "barfoothefoobarman", words = ["foo","bar"]
     * Output: [0,9]
     * Explanation:
     * The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
     * The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
     *
     * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
     * Output: []
     * Explanation:
     * There is no concatenated substring.
     *
     * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
     * Output: [6,9,12]
     * Explanation:
     * The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"].
     * The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"].
     * The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"].
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        if ( s==null || words == null || s.isEmpty() || words.length==0)
            return new ArrayList<>();

        Map<String,Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }

        int sLength = s.length();
        int n = words.length;
        int eachWordLength = words[0].length();
        int skipWordLength = n * eachWordLength;
        List<Integer> result = new ArrayList<>();

        for (int i =0; i < sLength - skipWordLength + 1; i++) {
            String subString = s.substring(i,i + skipWordLength);
            if(shouldConcat(subString,eachWordLength,countMap)) {
                result.add(i);
            }
        }
        return result;
    }

    public static boolean shouldConcat(String subString,int eachWordLength,Map<String,Integer> countMap) {
        Map<String,Integer> seen = new HashMap<>();
        for (int i=0; i < subString.length(); i += eachWordLength) {
            String ss = subString.substring(i,i+eachWordLength);
            seen.put(ss,seen.getOrDefault(ss,0) + 1);
        }
        return seen.equals(countMap);
    }

}

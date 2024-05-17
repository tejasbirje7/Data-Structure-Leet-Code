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

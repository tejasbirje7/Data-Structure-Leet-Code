import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 30. Substring with Concatenation of All Words
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 *
 * PROBLEM
 *   - Input: a string s and an array words; all words have the same length.
 *   - A concatenated string = all the words glued together in any order, each used exactly once.
 *     With words = ["ab","cd","ef"]: "abcdef" and "cdabef" count, "acdbef" does not.
 *   - Return: every start index in s where a concatenated string begins, in any order.
 *
 * EXAMPLE
 *   Input: s = "barfoothefoobarman", words = ["foo","bar"]
 *   Output: [0,9]
 *   Explanation:
 *   The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
 *   The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
 *
 *   Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 *   Output: []
 *   Explanation:
 *   There is no concatenated substring.
 *
 *   Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 *   Output: [6,9,12]
 *   Explanation:
 *   The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"].
 *   The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"].
 *   The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"].
 *
 * HOW TO THINK
 *   - Clue: all words have the same length, so a match always has the same total length
 *     (n * wordLength) and can be cut into words at fixed positions.
 *   - Technique: fixed-size window that slides one char at a time.
 *     For each start, cut the window into words and compare word counts with a HashMap.
 *   - Rule that stays true: countMap = how many times each word must appear; it never changes.
 *   - Decision: for each start i, seen = counts of the window's pieces.
 *               seen.equals(countMap) -> add i.   otherwise -> skip.
 *   - Return: the list of matching starts. The last start is sLength - windowLength,
 *     hence the loop bound i < sLength - skipWordLength + 1.
 *   - Why counts and not a set: words can repeat (["word","good","best","word"] needs "word" twice).
 *
 * STEPS
 *   1. If s or words is null or empty, return [].
 *   2. countMap = word -> count. windowLength = words.length * wordLength.
 *   3. For each start i from 0 to sLength - windowLength:
 *   4.   Cut s[i .. i + windowLength) into pieces of wordLength and count them in seen.
 *   5.   If seen equals countMap, add i.
 *   6. Return the list.
 *
 * DRY RUN   s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 *           (wordLength = 3, windowLength = 9, countMap = {bar=1, foo=1, the=1}, result after the step)
 *
 *     i | window      | pieces        | match? | result
 *   ----+-------------+---------------+--------+-----------
 *     0 | barfoofoo   | bar foo foo   | no     | []
 *     1 | arfoofoob   | arf oof oob   | no     | []
 *     2 | rfoofooba   | rfo ofo oba   | no     | []
 *     3 | foofoobar   | foo foo bar   | no     | []
 *     4 | oofoobart   | oof oob art   | no     | []
 *     5 | ofoobarth   | ofo oba rth   | no     | []
 *     6 | foobarthe   | foo bar the   | yes    | [6]
 *     7 | oobarthef   | oob art hef   | no     | [6]
 *     8 | obarthefo   | oba rth efo   | no     | [6]
 *     9 | barthefoo   | bar the foo   | yes    | [6,9]
 *    10 | arthefoob   | art hef oob   | no     | [6,9]
 *    11 | rthefooba   | rth efo oba   | no     | [6,9]
 *    12 | thefoobar   | the foo bar   | yes    | [6,9,12]
 *    13 | hefoobarm   | hef oob arm   | no     | [6,9,12]
 *    14 | efoobarma   | efo oba rma   | no     | [6,9,12]
 *    15 | foobarman   | foo bar man   | no     | [6,9,12]
 *
 *   Returns [6, 9, 12]. i = 0 and i = 3 fail because foo appears twice and the is missing.
 *
 * EDGE CASES
 *   s = "barfoothefoobarman", ["foo","bar"]                         -> [0, 9]
 *   s = "wordgoodgoodgoodbestword", ["word","good","best","word"]   -> []      "word" must appear twice
 *   s = "aaa", ["a","a"]                                            -> [0, 1]  overlapping matches all count
 *   s = "ab", ["abc"]                                               -> []      window longer than s, loop never runs
 *   s = "", ["a"]                                                   -> []      early return
 *
 * COMMON MISTAKES
 *   - Using a Set instead of counts: repeated words get lost (example 2 needs "word" twice).
 *   - Loop bound i < sLength - windowLength: misses the last possible start. Add 1.
 *   - Cutting pieces at every char instead of every wordLength chars: pieces start at i, i + w, i + 2w, ...
 *
 * COMPLEXITY
 *   O((L - n*w + 1) * n * w) time, L = s length, n = number of words, w = word length:
 *   every start rebuilds the counts of n pieces of length w.
 *   O(n * w) extra space for the maps and the window substring.
 *
 * RELATED
 *   LeetCode 76 (MinimumWindowSubstring, SlidingWindow/): also compares window counts with a target map,
 *     but per char and with a window that grows and shrinks.
 *   LeetCode 242 (ValidAnagram, HashMap/): the same "do the counts match?" check, on the letters of two strings.
 */
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

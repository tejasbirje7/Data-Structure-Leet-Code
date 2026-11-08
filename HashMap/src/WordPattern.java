import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 290. Word Pattern
 * https://leetcode.com/problems/word-pattern/
 *
 * PROBLEM
 *   - Input: a pattern of letters and a string s of words separated by single spaces.
 *   - Return: true if s follows the pattern: each letter matches exactly one word,
 *     and each word matches exactly one letter.
 *
 * EXAMPLE
 *   Input: pattern = "abba", s = "dog cat cat dog"
 *   Output: true
 *
 * HOW TO THINK
 *   - Clue: "each letter <-> exactly one word" is a one-to-one match, checked in both directions.
 *   - Technique: split s into words, then two hash maps: wordToChar and charToWord.
 *   - Rule that stays true: every (pattern[j], words[j]) with j < i agrees with both maps.
 *   - Decision: the word already maps to a different letter -> return false.
 *               the letter already maps to a different word -> return false.
 *               otherwise -> record word -> letter and letter -> word.
 *   - Return: false at once if the counts of letters and words differ; true if the loop finishes.
 *
 * STEPS
 *   1. words = s.split(" "); if pattern.length() != words.length, return false.
 *   2. For each index i: if wordToChar has words[i] with a different letter, return false.
 *   3. Else if charToWord has pattern[i] with a different word, return false.
 *   4. Else put words[i] -> pattern[i] and pattern[i] -> words[i].
 *   5. Return true.
 *
 * DRY RUN   pattern = "abba", s = "dog cat cat dog"   (maps shown after the step)
 *
 *    i | letter | word | check               | wordToChar     | charToWord
 *   ---+--------+------+---------------------+----------------+----------------
 *    0 |   a    | dog  | both new, record    | {dog=a}        | {a=dog}
 *    1 |   b    | cat  | both new, record    | {dog=a, cat=b} | {a=dog, b=cat}
 *    2 |   b    | cat  | cat->b, b->cat ok   | {dog=a, cat=b} | {a=dog, b=cat}
 *    3 |   a    | dog  | dog->a, a->dog ok   | {dog=a, cat=b} | {a=dog, b=cat}
 *
 *   Returns true: a <-> dog, b <-> cat.
 *
 * EDGE CASES
 *   "abba", "dog cat cat fish"  -> false   at i = 3, a already maps to dog (charToWord conflict)
 *   "aaaa", "dog cat cat dog"   -> false   at i = 1, a already maps to dog, not cat
 *   "abba", "dog dog dog dog"   -> false   at i = 1, dog already maps to a (wordToChar conflict)
 *   "aaa", "dog dog"            -> false   3 letters but 2 words
 *   "a", "dog"                  -> true    one letter, one word
 *
 * COMMON MISTAKES
 *   - Using only charToWord: "abba", "dog dog dog dog" would return true. Check both directions.
 *   - Forgetting the length check: an index goes out of bounds, or extra words are never checked.
 *   - Comparing words with == instead of equals.
 *
 * COMPLEXITY
 *   O(n + m) time and extra space, n = pattern length, m = length of s.
 *
 * RELATED
 *   LeetCode 205 (IsomorphicStrings, HashMap/): the same two-map check, character to character.
 */
public class WordPattern {
    public static void main(String[] args) {
        String pattern = "abba";
        String s = "dog cat cat dog";
        System.out.println(wordPattern(pattern,s));
    }

    public static boolean wordPattern(String pattern, String s) {

        Map<String , Character> wordToChar = new HashMap<>();
        Map<Character, String> charToWord = new HashMap<>();

        String[] words = s.split(" ");

        if (pattern.length() != words.length) return false;

        for (int i=0; i < pattern.length(); i++) {
            if(wordToChar.containsKey(words[i]) && !wordToChar.get(words[i]).equals(pattern.charAt(i))) {
                return false;
            } else if (charToWord.containsKey(pattern.charAt(i)) && !charToWord.get(pattern.charAt(i)).equals(words[i])) {
                return false;
            } else {
                wordToChar.put(words[i],pattern.charAt(i));
                charToWord.put(pattern.charAt(i),words[i]);
            }
        }
        return true;
    }
}

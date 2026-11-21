import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 140. Word Break II
 * https://leetcode.com/problems/word-break-ii/
 *
 * PROBLEM
 *   - Input: a string s and a list of dictionary words wordDict.
 *   - Return: every sentence made by adding spaces to s so that each word is in the dictionary, in any order.
 *   - A word may be reused. s is short (at most 20 chars), but there can be many sentences.
 *
 * EXAMPLE
 *   Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 *   Output: ["cats and dog","cat sand dog"]
 *
 * HOW TO THINK
 *   - Clue: "return all" sentences -> backtracking; the same suffix gets split again and again -> memo.
 *   - Technique: split(start) = every sentence for the suffix s[start..n).
 *     Try each dictionary word that starts at start, then glue it to every sentence of the rest.
 *   - Rule that stays true: memo[start], once stored, is the full answer for that suffix and never changes.
 *     split(n) = [""] (one way to split nothing), so a word that ends exactly at n is a full sentence.
 *   - Decision: s[start..end) in dict -> for each rest in split(end): add word, or word + " " + rest.
 *               not in dict          -> try the next end.
 *   - Return: split(0). An empty list means no sentence exists.
 *
 * STEPS
 *   1. Put the words in a HashSet; memo = map from start index to its list of sentences.
 *   2. split(start): if start == n return [""]; if memo has start, return it.
 *   3. For end from start + 1 to n, if s[start..end) is a word, combine it with each sentence of split(end).
 *   4. Store the list in memo and return it; the answer is split(0).
 *
 * DRY RUN   input = s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 *   (rows in the order the calls finish)
 *
 *    call      | suffix       | words found at start | from memo? | result
 *   -----------+--------------+----------------------+------------+--------------------------------
 *    split(10) | ""           | -                    | base case  | [""]
 *    split(7)  | "dog"        | "dog" -> split(10)   | no         | ["dog"]
 *    split(3)  | "sanddog"    | "sand" -> split(7)   | no         | ["sand dog"]
 *    split(4)  | "anddog"     | "and" -> split(7)    | yes (7)    | ["and dog"]
 *    split(0)  | "catsanddog" | "cat" -> split(3),   | no         | ["cat sand dog", "cats and dog"]
 *              |              | "cats" -> split(4)   |            |
 *
 *   Returns ["cat sand dog", "cats and dog"]. Any order is accepted.
 *
 * EDGE CASES
 *   "catsandog", ["cats","dog","sand","and","cat"]  -> []   "og" is never a word
 *   "a", ["a"]                                      -> ["a"]
 *   "aaaa", ["a","aa"]                              -> 5 sentences   1s and 2s that add up to 4
 *   "pineapplepenapple", ["apple","pen","applepen","pine","pineapple"]
 *                                                   -> 3 sentences   "pine apple pen apple", ...
 *
 * COMMON MISTAKES
 *   - Base case returning an empty list: then no sentence is ever built. Return a list with one "".
 *   - Adding a trailing space: only add " " + rest when rest is not empty.
 *   - No memo: the same suffix is split again for every way of reaching it (exponential).
 *   - Building the bottom-up true/false table of Word Break and stopping there: this problem needs the sentences.
 *
 * COMPLEXITY
 *   O(n^2 + total size of all sentences) time with memo: each suffix is solved once, but the output itself
 *   can be exponential (e.g. "aaaa..." with ["a","aa"]). O(n + total size of all sentences) extra space.
 *
 * RELATED
 *   LeetCode 139 (WordBreak, DynamicProgramming/): only asks true/false, so a boolean table is enough.
 *   LeetCode 131 (PalindromePartitioning, Backtracking/): also lists every way to cut a string, with a different test per piece.
 *   LeetCode 212 (WordSearch2, Backtracking/): backtracking over a dictionary, on a grid instead of a string.
 */
public class WordBreak2 {

    public static void main(String[] args) {
        System.out.println(wordBreak("catsanddog", List.of("cat", "cats", "and", "sand", "dog")));
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        return split(s, 0, new HashSet<>(wordDict), new HashMap<>());
    }

    private static List<String> split(String s, int start, Set<String> words, Map<Integer, List<String>> memo) {
        if (start == s.length()) {
            List<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        if (memo.containsKey(start)) {
            return memo.get(start);
        }
        List<String> sentences = new ArrayList<>();
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (words.contains(word)) {
                for (String rest : split(s, end, words, memo)) {
                    sentences.add(rest.isEmpty() ? word : word + " " + rest);
                }
            }
        }
        memo.put(start, sentences);
        return sentences;
    }

}

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 139. Word Break
 * https://leetcode.com/problems/word-break/
 *
 * PROBLEM
 *   - Input: a string s and a list of dictionary words wordDict.
 *   - Return: true if s can be split into a sequence of one or more dictionary words.
 *   - A word may be reused any number of times.
 *
 * EXAMPLE
 *   Input: s = "leetcode", wordDict = ["leet","code"]
 *   Output: true
 *   Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * HOW TO THINK
 *   - Clue: "can be segmented" + words can be reused -> the same suffixes get re-checked many times (DP on prefixes).
 *   - Technique: dp[i] = true if the first i characters s[0..i) can be split.
 *     s[0..i) splits if some shorter prefix s[0..j) splits AND the rest s[j..i) is a dictionary word.
 *   - Rule that stays true: dp[0] = true (empty prefix); once dp[i] is set it never changes.
 *   - Decision: for each j < i, dp[j] && dict has s[j..i) -> dp[i] = true, stop looking.
 *               otherwise                               -> try the next j.
 *   - Return: dp[n], the whole string. The array has size n + 1.
 *
 * STEPS
 *   1. Put the words in a HashSet; dp = boolean[n + 1]; dp[0] = true.
 *   2. Loop i from 1 to n; inner loop j from 0 to i-1.
 *   3. If dp[j] and the set contains s.substring(j, i), set dp[i] = true and break.
 *   4. Return dp[n].
 *
 * DRY RUN   input = s = "leetcode", wordDict = ["leet","code"]
 *
 *    i | prefix s[0..i) | split found (dp[j] && s[j..i) in dict) | dp[i]
 *   ---+----------------+-----------------------------------------+-------
 *    0 | ""             | base case                               |   T
 *    1 | "l"            | none                                    |   F
 *    2 | "le"           | none                                    |   F
 *    3 | "lee"          | none                                    |   F
 *    4 | "leet"         | j = 0: "" + "leet"                      |   T
 *    5 | "leetc"        | none (dp[4] = T but "c" not a word)     |   F
 *    6 | "leetco"       | none (dp[4] = T but "co" not a word)    |   F
 *    7 | "leetcod"      | none (dp[4] = T but "cod" not a word)   |   F
 *    8 | "leetcode"     | j = 4: "leet" + "code"                  |   T
 *
 *   Returns true (dp[8]).
 *
 * EDGE CASES
 *   "catsandog", ["cats","dog","sand","and","cat"]  -> false  "og" is never a word
 *   "applepenapple", ["apple","pen"]                -> true   "apple" is reused
 *   "cars", ["car","ca","rs"]                       -> true   "ca" + "rs"; greedy "car" leaves "s"
 *   "a", ["b"]                                      -> false  no word fits
 *
 * COMMON MISTAKES
 *   - Greedy: taking the first or longest matching word fails on "cars" with ["car","ca","rs"].
 *   - Recursion without memo: exponential on inputs like "aaaa...ab" with ["a","aa","aaa"].
 *   - dp of size n instead of n + 1: there is no slot for the empty prefix or the full string.
 *   - Calling contains on the List: O(k) per lookup. Use a HashSet.
 *
 * COMPLEXITY
 *   O(n^3) time: n^2 (j, i) pairs, each building and hashing a substring of up to n chars.
 *   O(n + total dictionary size) extra space.
 *
 * RELATED
 *   LeetCode 140 (WordBreak2, DynamicProgramming/): return every possible sentence, not just true/false.
 *   LeetCode 300 (LongestIncreasingSubsequence, DynamicProgramming/): same shape, dp[i] scans every earlier j.
 */
public class WordBreak {

    public static void main(String[] args) {
        System.out.println(wordBreak("leetcode", List.of("leet", "code")));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

}

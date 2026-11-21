import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * LeetCode 127. Word Ladder
 * https://leetcode.com/problems/word-ladder/
 *
 * PROBLEM
 *   - Input: beginWord, endWord and a wordList; all words have the same length and are lower-case.
 *   - One move changes exactly one letter, and every word after beginWord must be in wordList.
 *   - Return: the number of words in the shortest sequence from beginWord to endWord (counting both ends),
 *     or 0 if there is none.
 *
 * EXAMPLE
 *   Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 *   Output: 5
 *   Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> "cog", which is 5 words long.
 *
 * HOW TO THINK
 *   - Clue: "shortest sequence" where each step is one small change -> words are nodes, one-letter changes are edges,
 *     all edges cost the same -> BFS finds the shortest path.
 *   - Technique: BFS level by level. Neighbours are found by trying 'a'..'z' at every position
 *     and keeping the results that are still in the word set.
 *   - Rule that stays true: every word in the queue was reached in exactly `level` words,
 *     and it is already removed from the set, so no word is queued twice.
 *   - Decision for each generated word: equals endWord -> return level + 1;
 *     still in the set -> remove it and queue it; otherwise skip.
 *   - Return: level + 1 at the moment endWord is generated; 0 if the queue runs out.
 *
 * STEPS
 *   1. words = set of wordList. If endWord is not in it, return 0.
 *   2. queue = [beginWord], level = 1.
 *   3. While the queue is not empty: process exactly the words of this level.
 *   4.   For each word, each position, each letter 'a'..'z' (not the current one): build next.
 *        next == endWord -> return level + 1. words.remove(next) succeeds -> queue next.
 *   5.   level++.
 *   6. Return 0.
 *
 * DRY RUN   input = "hit" -> "cog", wordList = ["hot","dot","dog","lot","log","cog"]   (queue and set are after the level)
 *
 *    level | words processed | new words queued (removed from the set) | queue after   | set after
 *   -------+-----------------+-----------------------------------------+---------------+---------------------------
 *      1   | hit             | hot                                     | [hot]         | dot dog lot log cog
 *      2   | hot             | dot, lot (hit is not in the set)        | [dot, lot]    | dog log cog
 *      3   | dot, lot        | dog (from dot), log (from lot)          | [dog, log]    | cog
 *      4   | dog             | generates cog == endWord -> return 4+1  |               |
 *
 *   Returns 5: hit -> hot -> dot -> dog -> cog.
 *
 * EDGE CASES
 *   "hit" -> "cog", ["hot","dot","dog","lot","log"]   -> 0   endWord is not in the list
 *   "a" -> "c", ["a","b","c"]                         -> 2   one move: a -> c
 *   "hot" -> "dog", ["hot","dog"]                     -> 0   two letters differ, no word in between
 *   beginWord in wordList                             -> same answer; it is removed at the start, so it is never revisited
 *
 * COMMON MISTAKES
 *   - Comparing every pair of words to find neighbours: O(N^2 * L). Trying 26 letters per position is O(N * L * 26).
 *   - Marking a word visited when it is polled instead of when it is queued: the same word gets queued many times.
 *   - Returning level instead of level + 1: the answer counts words, including endWord.
 *   - Forgetting to put the original letter back after trying 'a'..'z' at a position.
 *
 * COMPLEXITY
 *   O(N * L * 26 * L) time: N words, L positions, 26 letters, and O(L) to build each new string.
 *   O(N * L) extra space for the set and the queue.
 *
 * RELATED
 *   LeetCode 994 (RottingOranges, Graphs/): BFS level by level, where the level count is the answer.
 *   LeetCode 207 (CourseSchedule, Graphs/): BFS over a graph built from the input, but in topological order.
 */
public class WordLadder {

    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(ladderLength("hit", "cog", wordList));
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return 0;
        }
        words.remove(beginWord);
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                char[] word = queue.poll().toCharArray();
                for (int pos = 0; pos < word.length; pos++) {
                    char original = word[pos];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (ch == original) {
                            continue;
                        }
                        word[pos] = ch;
                        String next = new String(word);
                        if (next.equals(endWord)) {
                            return level + 1;
                        }
                        if (words.remove(next)) {
                            queue.offer(next);
                        }
                    }
                    word[pos] = original;
                }
            }
            level++;
        }
        return 0;
    }

}

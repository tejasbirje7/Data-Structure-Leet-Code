import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 269. Alien Dictionary   (LeetCode Premium)
 * https://leetcode.com/problems/alien-dictionary/
 *
 * PROBLEM
 *   - Input: words from an alien language that uses lower-case English letters, sorted in the alien alphabet's order.
 *   - Return: a string with every letter that appears, in an order that fits the sorting.
 *     Return "" if no order fits. Any valid order is accepted.
 *
 * EXAMPLE
 *   Input: words = ["wrt","wrf","er","ett","rftt"]
 *   Output: "wertf"
 *
 * HOW TO THINK
 *   - Clue: "sorted" words + "find the order" -> each pair of neighbouring words gives one rule "x comes before y";
 *     rules form a directed graph, and the answer is a topological order.
 *   - Technique: compare each pair of neighbouring words; the first position where they differ gives edge x -> y.
 *     Then Kahn's BFS over the letters.
 *   - Rule that stays true: the queue holds letters with no unplaced letter before them; they can go next in the answer.
 *   - Decision per pair: first differing letters x != y -> add edge x -> y (once) and stop comparing that pair;
 *     no difference but the first word is longer (e.g. "abc" before "ab") -> invalid, return "".
 *     In the BFS: place the polled letter; each letter after it loses one in-degree; at 0, queue it.
 *   - Return: the built string if it has every letter; shorter means a cycle -> "".
 *
 * STEPS
 *   1. Mark every letter that appears in any word as present.
 *   2. For each neighbouring pair: find the first i with w1[i] != w2[i] -> edge w1[i] -> w2[i] (skip if already there).
 *      No such i and w1 is longer than w2 -> return "".
 *   3. Queue every present letter with in-degree 0 (a to z).
 *   4. While the queue is not empty: poll, append, lower the in-degree of its targets, queue those that hit 0.
 *   5. Return the string if its length == number of present letters, else "".
 *
 * DRY RUN   input = ["wrt","wrf","er","ett","rftt"]
 *
 *   Building the edges:
 *    pair          | first difference | edge
 *   ---------------+------------------+--------
 *    wrt , wrf     | index 2: t vs f  | t -> f
 *    wrf , er      | index 0: w vs e  | w -> e
 *    er  , ett     | index 1: r vs t  | r -> t
 *    ett , rftt    | index 0: e vs r  | e -> r
 *
 *   Letters: e f r t w. In-degree: e 1, f 1, r 1, t 1, w 0. Queue starts [w].
 *   Topological sort (in-degree, queue and answer are after the step):
 *    step | poll | targets: in-degree change | queue | answer
 *   ------+------+---------------------------+-------+--------
 *      1  |  w   | e: 1 -> 0, queue e        | [e]   | w
 *      2  |  e   | r: 1 -> 0, queue r        | [r]   | we
 *      3  |  r   | t: 1 -> 0, queue t        | [t]   | wer
 *      4  |  t   | f: 1 -> 0, queue f        | [f]   | wert
 *      5  |  f   | none                      | []    | wertf
 *
 *   5 letters placed out of 5: returns "wertf".
 *
 * EDGE CASES
 *   ["z","x"]          -> "zx"   one rule: z before x
 *   ["z","x","z"]      -> ""     z -> x and x -> z is a cycle
 *   ["abc","ab"]       -> ""     a longer word before its own prefix can never be sorted
 *   ["z","z"]          -> "z"    no rules, but z still appears in the answer
 *   ["ab","adc"]       -> "abcd" (one valid order) only b -> d is known; a and c have no rule, any place is fine
 *
 * COMMON MISTAKES
 *   - Comparing letters after the first difference: only the first differing pair carries information. Break there.
 *   - Missing the prefix case ("abc" before "ab"): it is invalid even though no letters differ.
 *   - Adding the same edge twice and counting its in-degree twice: keep a seen-edge check (or a set per letter).
 *   - Leaving out letters that appear but have no rules: start from every present letter, not just letters in edges.
 *
 * COMPLEXITY
 *   O(C) time where C is the total number of characters in all words (the alphabet is fixed at 26).
 *   O(26 * 26) = O(1) extra space for the edge table.
 *
 * RELATED
 *   LeetCode 207 (CourseSchedule, Graphs/): the same Kahn's BFS, but the edges are given directly.
 *   LeetCode 210 (CourseSchedule2, Graphs/): returns the topological order, as here, but of courses instead of letters.
 */
public class AlienDictionary {

    public static void main(String[] args) {
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(alienOrder(words));
    }

    public static String alienOrder(String[] words) {
        boolean[] present = new boolean[26];
        int letters = 0;
        for (String w : words) {
            for (char ch : w.toCharArray()) {
                if (!present[ch - 'a']) {
                    present[ch - 'a'] = true;
                    letters++;
                }
            }
        }
        boolean[][] edge = new boolean[26][26];
        int[] inDegree = new int[26];
        for (int k = 0; k + 1 < words.length; k++) {
            String w1 = words[k];
            String w2 = words[k + 1];
            int len = Math.min(w1.length(), w2.length());
            int i = 0;
            while (i < len && w1.charAt(i) == w2.charAt(i)) {
                i++;
            }
            if (i == len) {
                if (w1.length() > w2.length()) {
                    return "";
                }
                continue;
            }
            int from = w1.charAt(i) - 'a';
            int to = w2.charAt(i) - 'a';
            if (!edge[from][to]) {
                edge[from][to] = true;
                inDegree[to]++;
            }
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int c = 0; c < 26; c++) {
            if (present[c] && inDegree[c] == 0) {
                queue.offer(c);
            }
        }
        StringBuilder order = new StringBuilder();
        while (!queue.isEmpty()) {
            int c = queue.poll();
            order.append((char) ('a' + c));
            for (int next = 0; next < 26; next++) {
                if (edge[c][next]) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        return order.length() == letters ? order.toString() : "";
    }

}

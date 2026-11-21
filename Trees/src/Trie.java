import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 208. Implement Trie (Prefix Tree)
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * PROBLEM
 *   - Build a class with three operations on lowercase words:
 *     insert(word), search(word) -> was this exact word inserted?, startsWith(prefix) -> does any word start with it?
 *   - Words and prefixes have length 1 to 2000; up to 3 * 10^4 calls.
 *
 * EXAMPLE
 *   Input
 *   ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 *   [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 *   Output
 *   [null, null, true, false, true, null, true]
 *   Explanation
 *   Trie trie = new Trie();
 *   trie.insert("apple");
 *   trie.search("apple");   // return True
 *   trie.search("app");     // return False
 *   trie.startsWith("app"); // returns True
 *   trie.insert("app");
 *   trie.search("app");     // return True
 *
 * HOW TO THINK
 *   - Clue: "prefix" queries over many words -> share common prefixes in a tree of letters.
 *   - Technique: each node has 26 child slots (one per letter) and an isEnd flag.
 *     A word is a path from the root; walking the path costs O(length), no matter how many words are stored.
 *   - Rule that stays true: a path root -> node exists only if some inserted word starts with those letters;
 *     isEnd is true only on the last node of an inserted word.
 *   - Decision: while walking, child for the next letter missing ->
 *               insert creates it; search / startsWith stop and return false.
 *               walk finished -> search returns isEnd, startsWith returns true.
 *   - Return: search needs the whole word AND isEnd; startsWith only needs the path.
 *
 * STEPS
 *   1. Node = Node[26] children + boolean isEnd; the trie starts with an empty root.
 *   2. insert: walk letter by letter, creating missing children; mark the last node isEnd = true.
 *   3. walk(s): follow children; return null as soon as one is missing, else the last node.
 *   4. search = walk(word) != null && isEnd; startsWith = walk(prefix) != null.
 *
 * DRY RUN   input = the official example, plus search("apx") to hit the missing-child branch
 *
 *    step | operation          | walk                                    | action                     | returns
 *   ------+--------------------+-----------------------------------------+----------------------------+--------
 *     1   | insert("apple")    | a, p, p, l, e all missing               | create 5 nodes, e.isEnd    | null
 *     2   | search("apple")    | a -> p -> p -> l -> e                   | e.isEnd is true            | true
 *     3   | search("app")      | a -> p -> p                             | second p isEnd is false    | false
 *     4   | startsWith("app")  | a -> p -> p                             | path exists                | true
 *     5   | insert("app")      | a -> p -> p (all exist)                 | mark second p isEnd        | null
 *     6   | search("app")      | a -> p -> p                             | second p isEnd is true     | true
 *     7   | search("apx")      | a -> p, then no child for 'x'           | stop early                 | false
 *
 *   Prints [null, null, true, false, true, null, true, false]. Final trie: a-p-p(end)-l-e(end).
 *
 * EDGE CASES
 *   startsWith a full word ("apple")       -> true    a word is a prefix of itself
 *   search a longer word ("apples")         -> false   path runs out after "apple"
 *   insert the same word twice              -> no change, isEnd is just set again
 *   search before any insert                -> false   root has no children
 *
 * COMMON MISTAKES
 *   - search returning true when the path exists: "app" is only a word if isEnd is set on its last node.
 *   - Storing isEnd on the child of the last letter instead of the last letter's node.
 *   - Using a HashSet of all prefixes: works, but uses O(L^2) memory per word and misses the point.
 *
 * COMPLEXITY
 *   O(L) time per call, L = length of the word or prefix.
 *   O(total letters inserted * 26) extra space in the worst case (no shared prefixes).
 *
 * RELATED
 *   LeetCode 212 (WordSearch2, Backtracking/): puts every word in a trie, then walks the trie while walking the grid.
 *   LeetCode 140 (WordBreak2, DynamicProgramming/): dictionary lookups on substrings; a trie can replace the HashSet.
 *   LeetCode 706 (DesignHashMap, HashMap/): another design-a-data-structure problem with array buckets.
 */
public class Trie {

    public static void main(String[] args) {
        List<Object> out = new ArrayList<>();
        Trie trie = new Trie();
        out.add(null);
        trie.insert("apple");
        out.add(null);
        out.add(trie.search("apple"));
        out.add(trie.search("app"));
        out.add(trie.startsWith("app"));
        trie.insert("app");
        out.add(null);
        out.add(trie.search("app"));
        out.add(trie.search("apx"));
        System.out.println(out);
    }

    private final Node root = new Node();

    public void insert(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null) {
                node.children[i] = new Node();
            }
            node = node.children[i];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Node node = walk(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return walk(prefix) != null;
    }

    private Node walk(String s) {
        Node node = root;
        for (char c : s.toCharArray()) {
            node = node.children[c - 'a'];
            if (node == null) {
                return null;
            }
        }
        return node;
    }

    static class Node {
        Node[] children = new Node[26];
        boolean isEnd;
    }

}

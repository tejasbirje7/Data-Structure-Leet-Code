import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 212. Word Search II
 * https://leetcode.com/problems/word-search-ii/
 *
 * PROBLEM
 *   - Input: an m x n grid of lower-case letters (1..12 each side) and a list of unique words
 *     (up to 3 * 10^4 of them, each 1..10 letters).
 *   - Return: every word that can be traced in the grid, in any order.
 *   - Same tracing rules as Word Search: steps go up/down/left/right, a cell is used once per word.
 *
 * EXAMPLE
 *   Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
 *          words = ["oath","pea","eat","rain"]
 *   Output: ["eat","oath"]
 *
 * HOW TO THINK
 *   - Clue: Word Search, but for MANY words. Running Word Search once per word repeats the same
 *     prefixes again and again.
 *   - Technique: put all words in a trie, then do ONE grid DFS per start cell that walks the
 *     trie at the same time. Words that share a prefix share the walk.
 *   - Each trie node stores `word` at the node where a word ends (null elsewhere).
 *   - Prune: if the trie has no child for the cell's letter, no word continues this way, so stop.
 *   - Rule that stays true: when dfs(r, c, parent) runs, the path so far spells the prefix of `parent`,
 *     and every cell on that path is marked '#'.
 *   - Decision: off grid or '#' -> stop.
 *               node = parent.next[letter]; null -> stop (prefix not in any word).
 *               node.word != null -> add it and set it to null (so it is never added twice).
 *               then mark '#', go down, up, right, left with node as the parent, restore the letter.
 *   - Return: the list of found words.
 *
 * STEPS
 *   1. Build the trie: for each word walk/create nodes letter by letter; set word on the last node.
 *   2. For each cell: dfs(r, c, root).
 *   3. dfs: stop if off grid or '#'; node = parent.next[ch]; stop if null.
 *   4.      if node.word != null: add it, set node.word = null.
 *   5.      mark '#', dfs on the 4 neighbours with node, put ch back.
 *   6. Return result.
 *
 * DRY RUN   board = [["a","b"],["c","d"]], words = ["abd","ac","bd","ad"]
 *           trie: root -> a -> b -> d(abd); a -> c(ac); a -> d(ad); root -> b -> d(bd)
 *           (neighbour order: down, up, right, left; off-grid calls stop at once and are not shown)
 *
 *    depth | cell  | letter | path   | action
 *   -------+-------+--------+--------+------------------------------------
 *      0   | (0,0) |   a    | a      | trie has a, mark #
 *      1   | (1,0) |   c    | ac     | found "ac", clear it, mark #
 *      2   | (0,0) |   #    | ac     | already on the path, stop
 *      2   | (1,1) |   d    | ac     | trie node ac has no d, stop
 *      1   | (0,1) |   b    | ab     | trie has ab, mark #
 *      2   | (1,1) |   d    | abd    | found "abd", clear it, mark #
 *      3   | (0,1) |   #    | abd    | already on the path, stop
 *      3   | (1,0) |   c    | abd    | no child c, stop
 *      2   | (0,0) |   #    | ab     | already on the path, stop
 *      0   | (0,1) |   b    | b      | trie has b, mark #
 *      1   | (1,1) |   d    | bd     | found "bd", clear it, mark #
 *      2   | (0,1) |   #    | bd     | already on the path, stop
 *      2   | (1,0) |   c    | bd     | no child c, stop
 *      1   | (0,0) |   a    | b      | trie node b has no a, stop
 *      0   | (1,0) |   c    |        | root has no c, stop
 *      0   | (1,1) |   d    |        | root has no d, stop
 *
 *   Returns ["ac","abd","bd"]. "ad" is not found: a and d are only diagonal neighbours.
 *
 * EDGE CASES
 *   [["a"]], ["a"]          -> ["a"]      one cell, one letter
 *   [["a","a"]], ["a"]      -> ["a"]      found from both cells, added once (word cleared)
 *   [["a","b"]], ["aba"]    -> []         would need the a cell twice
 *   [["a"]], ["b"]          -> []         root has no b
 *   LeetCode example        -> ["oath","eat"] here; LeetCode accepts any order
 *
 * COMMON MISTAKES
 *   - Not clearing node.word after adding: a word found from two paths is added twice.
 *   - Running Word Search (LeetCode 79) once per word: correct but too slow for 3 * 10^4 words.
 *   - Restoring the cell only when a word is found: always restore before returning.
 *   - Checking '#' after indexing the trie: '#' - 'a' is negative and throws.
 *
 * COMPLEXITY
 *   O(m * n * 4 * 3^(L - 1)) time in the worst case (L = longest word, 10), plus O(total letters)
 *   to build the trie. O(total letters) extra space for the trie, O(L) for the recursion.
 *
 * RELATED
 *   LeetCode 79 (WordSearch, Backtracking/): one word, so it compares against word[i] instead of a trie.
 *   LeetCode 208 (Implement Trie): the same node with 26 children; here it also stores the word.
 */
public class WordSearch2 {

    private static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'a', 'b'},
                {'c', 'd'}
        };
        System.out.println(findWords(board, new String[]{"abd", "ac", "bd", "ad"}));
    }

    public static List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode node = root;
            for (char ch : w.toCharArray()) {
                if (node.next[ch - 'a'] == null) node.next[ch - 'a'] = new TrieNode();
                node = node.next[ch - 'a'];
            }
            node.word = w;
        }
        List<String> result = new ArrayList<>();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(board, r, c, root, result);
            }
        }
        return result;
    }

    private static void dfs(char[][] board, int r, int c, TrieNode parent, List<String> result) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) return;
        char ch = board[r][c];
        if (ch == '#') return;
        TrieNode node = parent.next[ch - 'a'];
        if (node == null) return;
        if (node.word != null) {
            result.add(node.word);
            node.word = null;
        }
        board[r][c] = '#';
        dfs(board, r + 1, c, node, result);
        dfs(board, r - 1, c, node, result);
        dfs(board, r, c + 1, node, result);
        dfs(board, r, c - 1, node, result);
        board[r][c] = ch;
    }
}

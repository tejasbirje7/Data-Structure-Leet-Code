/**
 * LeetCode 79. Word Search
 * https://leetcode.com/problems/word-search/
 *
 * PROBLEM
 *   - Input: an m x n grid of letters (board, 1..6 each side) and a word (1..15 letters).
 *   - Return: true if the word can be traced through the grid, else false.
 *   - Each step goes to a cell next to the current one (up, down, left, right, no diagonals).
 *   - The same cell can't be used twice in one word.
 *
 * EXAMPLE
 *   Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 *   Output: true
 *
 * HOW TO THINK
 *   - Clue: "adjacent cells" + "same cell not used twice" = DFS on a grid that must undo its marks,
 *     so backtracking.
 *   - Technique: try every cell as a start; from a cell that matches word[i], try all 4 neighbours for word[i + 1].
 *   - Visited trick: overwrite the cell with '#' while it is on the path, put the letter back on the way out.
 *     '#' never equals a letter, so the mismatch check also blocks reuse.
 *   - Rule that stays true: when dfs(r, c, i) is called, word[0..i-1] is already matched on a path
 *     whose cells are all '#'.
 *   - Decision: i == word length -> true (all matched).
 *               off the grid or board[r][c] != word[i] -> false.
 *               else mark '#', try down, up, right, left with i + 1, restore, return whether any worked.
 *   - Return: true as soon as one start works; false after all starts fail.
 *
 * STEPS
 *   1. For each cell (r, c): if dfs(r, c, 0) is true, return true.
 *   2. dfs: if i == word length, return true.
 *   3.      if (r, c) is off the grid or board[r][c] != word[i], return false.
 *   4.      save the letter, set '#', found = dfs on down || up || right || left with i + 1.
 *   5.      put the letter back, return found.
 *   6. Return false after the loop.
 *
 * DRY RUN   board = [["A","A","B"],["C","A","D"]], word = "AAD"   (neighbour order: down, up, right, left)
 *
 *    start | i | cell    | letter | want | action
 *   -------+---+---------+--------+------+----------------------------------------
 *    (0,0) | 0 | (0,0)   |   A    |  A   | match, mark #
 *          | 1 | (1,0)   |   C    |  A   | mismatch
 *          | 1 | (-1,0)  |   -    |  A   | off grid
 *          | 1 | (0,1)   |   A    |  A   | match, mark #
 *          | 2 | (1,1)   |   A    |  D   | mismatch
 *          | 2 | (-1,1)  |   -    |  D   | off grid
 *          | 2 | (0,2)   |   B    |  D   | mismatch
 *          | 2 | (0,0)   |   #    |  D   | mismatch (cell already on the path)
 *          | 1 | (0,1)   |        |      | all 4 failed: restore A, return false
 *          | 1 | (0,-1)  |   -    |  A   | off grid
 *          | 0 | (0,0)   |        |      | all 4 failed: restore A, return false
 *    (0,1) | 0 | (0,1)   |   A    |  A   | match, mark #
 *          | 1 | (1,1)   |   A    |  A   | match, mark #
 *          | 2 | (2,1)   |   -    |  D   | off grid
 *          | 2 | (0,1)   |   #    |  D   | mismatch (cell already on the path)
 *          | 2 | (1,2)   |   D    |  D   | match, mark #
 *          | 3 | (2,2)   |   -    |  -   | i == 3 == word length: true (checked before the grid bounds)
 *
 *   Returns true (path (0,1) -> (1,1) -> (1,2)). Every cell is restored on the way out.
 *
 * EDGE CASES
 *   [["A"]], "A"            -> true    one cell, one letter
 *   [["A"]], "AA"           -> false   the only A can't be used twice
 *   [["A","B"]], "BA"       -> true    the start doesn't have to be the first cell
 *   [["A","B"],["C","D"]], "AD"  -> false   diagonal steps are not allowed
 *   LeetCode example, "ABCB" -> false  needs the B at (0,1) twice
 *
 * COMMON MISTAKES
 *   - Forgetting to restore the cell: later starts see '#' and miss valid paths.
 *   - Checking bounds before i == word length: the call after the last match can land off the grid
 *     (like (2,2) above) and wrongly return false; on the grid it calls word.charAt(length) and throws.
 *   - Using a shared visited set without removing on the way out: same bug as not restoring.
 *   - Returning the first neighbour's result instead of OR-ing all four.
 *
 * COMPLEXITY
 *   O(m * n * 3^L) time (L = word length; after the first step each cell has at most 3 new neighbours),
 *   O(L) extra space for the recursion. The board is reused for marking, so no visited array.
 *
 * RELATED
 *   LeetCode 212 (WordSearch2, Backtracking/): many words at once; walks a trie instead of one word.
 *   LeetCode 200 (NumberOfIslands, Graphs/): same 4-direction grid DFS, but marks stay (no undo).
 */
public class WordSearch {

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'A', 'B'},
                {'C', 'A', 'D'}
        };
        System.out.println(exist(board, "AAD"));
    }

    public static boolean exist(char[][] board, String word) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (dfs(board, word, r, c, 0)) return true;
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, String word, int r, int c, int i) {
        if (i == word.length()) return true;
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) return false;
        if (board[r][c] != word.charAt(i)) return false;
        char saved = board[r][c];
        board[r][c] = '#';
        boolean found = dfs(board, word, r + 1, c, i + 1)
                || dfs(board, word, r - 1, c, i + 1)
                || dfs(board, word, r, c + 1, i + 1)
                || dfs(board, word, r, c - 1, i + 1);
        board[r][c] = saved;
        return found;
    }
}

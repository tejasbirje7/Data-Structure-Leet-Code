import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 131. Palindrome Partitioning
 * https://leetcode.com/problems/palindrome-partitioning/
 *
 * PROBLEM
 *   - Input: a string s of lower-case letters (1..16 long).
 *   - Split s into pieces so that every piece is a palindrome.
 *   - Return: every such split, as a list of pieces, in any order.
 *
 * EXAMPLE
 *   Input: s = "aab"
 *   Output: [["a","a","b"],["aa","b"]]
 *
 * HOW TO THINK
 *   - Clue: "return all possible partitions" = try every cut, so backtracking.
 *   - Technique: choose the FIRST piece s[start..end], keep it only if it is a palindrome,
 *     then split the rest s[end+1..] the same way.
 *   - Rule that stays true: temp is a list of palindromes that spells exactly s[0..start-1].
 *   - Decision: start == length -> the whole string is covered, save a copy of temp.
 *               else for end = start..n-1: if s[start..end] is not a palindrome, skip;
 *               else add it, recurse with end + 1, remove it.
 *   - Return: result. A single letter is always a palindrome, so every call finds at least one piece.
 *
 * STEPS
 *   1. backtrack(result, [], s, start = 0).
 *   2. If start == s.length(): add a copy of temp, return.
 *   3. For end = start..n-1: if isPalindrome(start, end): add s.substring(start, end + 1),
 *      recurse with end + 1, remove the last piece.
 *   4. isPalindrome: two pointers from both ends moving in; any mismatch means no.
 *   5. Return result.
 *
 * DRY RUN   s = "aab"   (temp is after the action)
 *
 *    start | end | piece | palindrome? | action             | temp
 *   -------+-----+-------+-------------+--------------------+------------
 *      0   |  0  | "a"   |    yes      | pick               | [a]
 *      1   |  1  | "a"   |    yes      | pick               | [a,a]
 *      2   |  2  | "b"   |    yes      | pick               | [a,a,b]
 *      3   |  -  |  -    |     -       | start == 3, save   | [a,a,b]
 *      1   |  2  | "ab"  |    no       | skip               | [a]
 *      0   |  1  | "aa"  |    yes      | pick               | [aa]
 *      2   |  2  | "b"   |    yes      | pick               | [aa,b]
 *      3   |  -  |  -    |     -       | start == 3, save   | [aa,b]
 *      0   |  2  | "aab" |    no       | skip               | []
 *
 *   Returns [[a,a,b],[aa,b]].
 *
 * EDGE CASES
 *   "a"     -> [[a]]                       one letter is a palindrome
 *   "ab"    -> [[a,b]]                     "ab" itself is not a palindrome
 *   "aaa"   -> [[a,a,a],[a,aa],[aa,a],[aaa]]  every cut works: 2^(n-1) splits
 *   "aba"   -> [[a,b,a],[aba]]             whole string is a palindrome but "ab", "ba" are not
 *   ""      -> [[]]                        start == 0 == length at once (LeetCode has length >= 1)
 *
 * COMMON MISTAKES
 *   - substring(start, end) instead of substring(start, end + 1): the end index is exclusive.
 *   - Adding temp itself instead of a copy: every entry is the same list, empty at the end.
 *   - Re-checking palindromes with a reversed string each time: works, but creates extra strings;
 *     a dp[i][j] table can make each check O(1) if needed.
 *
 * COMPLEXITY
 *   O(n * 2^n) time (up to 2^(n-1) splits, O(n) palindrome checks and copies),
 *   O(n) extra space for the recursion and temp, not counting the output.
 *
 * RELATED
 *   LeetCode 647 (PalindromicSubstringCount, FAQ/): counts palindromic substrings instead of splitting.
 *   LeetCode 139 (WordBreak, DynamicProgramming/): same "choose the first piece, solve the rest" cut,
 *     but a piece must be in a dictionary and only true/false is needed, so DP fits.
 *   LeetCode 78 (Subsets, Backtracking/): the same start-index loop.
 */
public class PalindromePartitioning {

    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), s, 0);
        return result;
    }

    private static void backtrack(List<List<String>> result, List<String> temp, String s, int start) {
        if (start == s.length()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            if (!isPalindrome(s, start, end)) continue;
            temp.add(s.substring(start, end + 1));
            backtrack(result, temp, s, end + 1);
            temp.remove(temp.size() - 1);
        }
    }

    private static boolean isPalindrome(String s, int lo, int hi) {
        while (lo < hi) {
            if (s.charAt(lo++) != s.charAt(hi--)) return false;
        }
        return true;
    }
}

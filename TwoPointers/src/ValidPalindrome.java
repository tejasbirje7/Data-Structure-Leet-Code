/**
 * LeetCode 125. Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome/
 *
 * PROBLEM
 *   - Input: a string s (letters, digits, spaces, punctuation).
 *   - Lower-case all letters and remove everything that isn't a letter or digit.
 *   - Return: true if what is left reads the same forwards and backwards, else false.
 *   - Here isPalindrome(s) does the work; main runs the example and prints the result.
 *
 * EXAMPLE
 *   Input: s = "A man, a plan, a canal: Panama"
 *   Output: true
 *   Explanation: "amanaplanacanalpanama" is a palindrome.
 *
 * HOW TO THINK
 *   - Clue: "reads the same forward and backward" = compare the first char with the last,
 *     the second with the second-last, and so on.
 *   - Technique: clean the string, then two pointers from both ends moving inwards.
 *   - Rule that stays true: every pair outside [start, last] has already matched.
 *   - Decision: lower-case chars equal -> start++, last--.
 *               not equal              -> return false at once.
 *   - Return: when the pointers meet or cross, every pair matched -> true.
 *     The middle char of an odd-length string needs no check.
 *
 * STEPS
 *   1. Remove whitespace, then every char that isn't A-Z, a-z or 0-9.
 *   2. start = 0, last = length - 1.
 *   3. While last > start: compare lower-case s[start] and s[last].
 *   4.   Equal -> start++, last--.  Not equal -> return false.
 *   5. After the loop, return true.
 *
 * DRY RUN   s = "A man, a plan, a canal: Panama"  ->  cleaned "AmanaplanacanalPanama" (21 chars)
 *           (start, last are values before the move)
 *
 *    start | last | s[start] | s[last] | equal (lower-case)? | action
 *   -------+------+----------+---------+---------------------+-----------------
 *      0   |  20  |    A     |    a    |        yes          | start++, last--
 *      1   |  19  |    m     |    m    |        yes          | start++, last--
 *      2   |  18  |    a     |    a    |        yes          | start++, last--
 *      3   |  17  |    n     |    n    |        yes          | start++, last--
 *      4   |  16  |    a     |    a    |        yes          | start++, last--
 *      5   |  15  |    p     |    P    |        yes          | start++, last--
 *      6   |  14  |    l     |    l    |        yes          | start++, last--
 *      7   |  13  |    a     |    a    |        yes          | start++, last--
 *      8   |  12  |    n     |    n    |        yes          | start++, last--
 *      9   |  11  |    a     |    a    |        yes          | start++, last--
 *
 *   Loop stops at start = last = 10 (the middle 'c'). Returns true; main prints "true".
 *   Mismatch branch, s = "race a car" -> cleaned "raceacar": pairs (0,7) r/r, (1,6) a/a,
 *   (2,5) c/c match, then (3,4) e vs a differ -> returns false.
 *
 * EDGE CASES
 *   " "            -> true    cleaned string is "", the loop never runs
 *   "a"            -> true    one char, the loop never runs
 *   "ab_a"         -> true    '_' is removed, "aba" is left
 *   "race a car"   -> false   cleaned "raceacar": 'e' vs 'a' differ
 *   "0P"           -> false   '0' vs 'p' differ; digits are kept
 *
 * COMMON MISTAKES
 *   - Forgetting to stop on a mismatch: if the else branch only prints, the pointers never
 *     move and the loop runs forever. Return false right away.
 *   - Forgetting case: 'P' and 'p' must match, so compare Character.toLowerCase of both.
 *   - Removing only spaces: punctuation such as ',' and ':' must go too.
 *   - Removing digits: only non-alphanumeric chars go; "0P" keeps both chars.
 *
 * COMPLEXITY
 *   O(n) time. O(n) extra space for the cleaned copy
 *   (O(1) if the pointers skip non-alphanumeric chars in place instead).
 *
 * RELATED
 *   LeetCode 5 (LongestPalindrome, TwoPointers/): pointers grow outwards from a center
 *     to find the longest palindrome inside the string.
 *   LeetCode 9 (Pallindrome, Math/): palindrome check on an integer, by reversing its digits.
 */
public class ValidPalindrome {

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }

    public static boolean isPalindrome(String s) {
        s = s.replaceAll("\\s+","");
        s = s.replaceAll("[^A-Za-z0-9]","");

        int start = 0;
        int last = s.length() - 1;

        while (last > start) {
            if(Character.toLowerCase(s.charAt(last)) == Character.toLowerCase(s.charAt(start))) {
                last--;
                start++;
            } else {
                return false;
            }
        }
        return true;
    }
}

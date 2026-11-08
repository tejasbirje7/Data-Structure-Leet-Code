import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LeetCode 125. Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome/
 *
 * PROBLEM
 *   - Input: a string s (letters, digits, spaces, punctuation).
 *   - Lower-case all letters and remove everything that isn't a letter or digit.
 *   - Return: true if what is left reads the same forwards and backwards, else false.
 *   - Here the whole solution sits in main and prints "palindrome" / "Not a palindrome".
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
 *               not equal              -> not a palindrome; should stop at once.
 *               (this code only prints and never moves the pointers, so it loops forever)
 *   - Return: when the pointers meet or cross, every pair matched -> palindrome.
 *     The middle char of an odd-length string needs no check.
 *
 * STEPS
 *   1. Remove whitespace, then every char that isn't A-Z, a-z or 0-9.
 *   2. start = 0, last = length - 1.
 *   3. While last > start: compare lower-case s[start] and s[last].
 *   4.   Equal -> start++, last--.  Not equal -> print "Not a palindrome" (should return false).
 *   5. After the loop, print "palindrome".
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
 *   Loop stops at start = last = 10 (the middle 'c'). Prints "palindrome".
 *   (The mismatch branch isn't traced: with this code it never ends, see EDGE CASES.)
 *
 * EDGE CASES
 *   " "            -> palindrome   cleaned string is "", the loop never runs
 *   "a"            -> palindrome   one char, the loop never runs
 *   "ab_a"         -> palindrome   '_' is removed, "aba" is left
 *   "race a car"   -> BUG: hangs, printing "Not a palindrome" forever (should be false).
 *                     The else branch doesn't move the pointers or stop the loop.
 *   "0P"           -> BUG: hangs the same way ('0' vs 'p'; should be false)
 *
 * COMMON MISTAKES
 *   - Not stopping on a mismatch (the bug above): return false (or break) right away.
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
        Pattern p = Pattern.compile("[^A-Za-z0-9]");

        Matcher m = p.matcher(s);
        s = s.replaceAll("\\s+","");
        s = s.replaceAll("[^A-Za-z0-9]","");

        int start = 0;
        int last = s.length() - 1;

        while (last > start) {
            if(Character.toLowerCase(s.charAt(last)) == Character.toLowerCase(s.charAt(start))) {
                last--;
                start++;
            } else {
                System.out.println("Not a palindrome");
            }
        }
        System.out.println("palindrome");
    }
}

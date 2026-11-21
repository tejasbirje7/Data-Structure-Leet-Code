import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 394. Decode String
 * https://leetcode.com/problems/decode-string/
 *
 * PROBLEM
 *   - Input: an encoded string where k[text] means text repeated k times. Brackets can nest.
 *   - Return: the decoded string.
 *   - The input is always valid: k is a positive integer (can have several digits), and digits
 *     only ever appear as repeat counts.
 *
 * EXAMPLE
 *   Input: s = "3[a2[c]]"
 *   Output: "accaccacc"
 *
 * HOW TO THINK
 *   - Clue: nested brackets, and the inner part must be finished before the outer one -> stack.
 *   - Technique: build the current bracket's text in cur. At '[' the outer text is not done yet,
 *     so save it (and the repeat count k) on two stacks and start a fresh cur.
 *     At ']' the inner text is done: pop the outer text and count, append cur that many times.
 *   - Rule that stays true: cur is the text of the innermost open bracket so far; the stacks
 *     hold the outer texts and their counts, innermost on top.
 *   - Decision per char: digit -> k = k * 10 + digit.
 *                        '['   -> push k and cur; cur = new; k = 0.
 *                        ']'   -> prev = pop text; repeat = pop count; prev += cur * repeat; cur = prev.
 *                        letter -> cur += letter.
 *   - Return: cur after the loop (all brackets are closed, so it is the whole string).
 *
 * STEPS
 *   1. counts stack, texts stack, cur = "", k = 0.
 *   2. For each char c, apply the decision above.
 *   3. Return cur.
 *
 * DRY RUN   s = "3[a2[c]]"   (stacks shown bottom -> top, after the step)
 *
 *    c | action                           | k | counts | texts     | cur
 *   ---+----------------------------------+---+--------+-----------+------------
 *    3 | k = 3                            | 3 | -      | -         | ""
 *    [ | push 3 and ""; fresh cur         | 0 | 3      | ""        | ""
 *    a | append                           | 0 | 3      | ""        | "a"
 *    2 | k = 2                            | 2 | 3      | ""        | "a"
 *    [ | push 2 and "a"; fresh cur        | 0 | 3 2    | "" "a"    | ""
 *    c | append                           | 0 | 3 2    | "" "a"    | "c"
 *    ] | "a" + "c" x 2                    | 0 | 3      | ""        | "acc"
 *    ] | "" + "acc" x 3                   | 0 | -      | -         | "accaccacc"
 *
 *   Returns "accaccacc".
 *
 * EDGE CASES
 *   "3[a]2[bc]"       -> "aaabcbc"          official example 1: brackets side by side
 *   "2[abc]3[cd]ef"   -> "abcabccdcdcdef"   official example 3: text after the last bracket
 *   "10[a]"           -> "aaaaaaaaaa"       two-digit count: k builds digit by digit
 *   "abc"             -> "abc"              no brackets at all
 *   "1[a]"            -> "a"                repeat once
 *   "2[2[2[a]]]"      -> "aaaaaaaa"         three levels: 2 * 2 * 2
 *
 * COMMON MISTAKES
 *   - Reading only one digit for k: "10[a]" would become "0[a]" after a lost "1".
 *   - Forgetting to reset k to 0 after '[': the next count would continue the old number.
 *   - Repeating the whole cur including the outer prefix: only the bracket's own text repeats,
 *     which is why the outer text is saved separately at '['.
 *
 * COMPLEXITY
 *   O(output length) time and extra space: every output character is written a bounded number
 *   of times per nesting level.
 *
 * RELATED
 *   LeetCode 224 (BasicCalculator, Stack/): the same "save outer state at '(' and restore at ')'"
 *     pattern, with a running result and sign instead of text and count.
 *   LeetCode 20 (ValidParentheses, Stack/): the bracket matching this builds on.
 *   LeetCode 227 (BasicCalculator2, Stack/): builds multi-digit numbers the same way.
 */
public class DecodeString {

    public String decodeString(String s) {
        Deque<Integer> counts = new ArrayDeque<>();
        Deque<StringBuilder> texts = new ArrayDeque<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                counts.push(k);
                texts.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (c == ']') {
                int repeat = counts.pop();
                StringBuilder prev = texts.pop();
                for (int i = 0; i < repeat; i++) {
                    prev.append(cur);
                }
                cur = prev;
            } else {
                cur.append(c);
            }
        }
        return cur.toString();
    }

    public static void main(String[] args) {
        DecodeString sol = new DecodeString();

        System.out.println(sol.decodeString("3[a2[c]]"));     // Expected: accaccacc
        System.out.println(sol.decodeString("3[a]2[bc]"));    // Expected: aaabcbc
        System.out.println(sol.decodeString("2[abc]3[cd]ef")); // Expected: abcabccdcdcdef
        System.out.println(sol.decodeString("10[a]"));        // Expected: aaaaaaaaaa
        System.out.println(sol.decodeString("abc"));          // Expected: abc
        System.out.println(sol.decodeString("1[a]"));         // Expected: a
        System.out.println(sol.decodeString("2[2[2[a]]]"));   // Expected: aaaaaaaa
    }
}

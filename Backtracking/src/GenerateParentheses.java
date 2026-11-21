import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 22. Generate Parentheses
 * https://leetcode.com/problems/generate-parentheses/
 *
 * PROBLEM
 *   - Input: an int n (1..8), the number of pairs of parentheses.
 *   - Return: every string of n '(' and n ')' that is well-formed (balanced), in any order.
 *
 * EXAMPLE
 *   Input: n = 3
 *   Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 * HOW TO THINK
 *   - Clue: "generate all combinations" of a fixed length = build char by char, so backtracking.
 *   - Technique: don't build every string and filter; only add a char when it keeps the prefix valid.
 *   - Two counters decide everything:
 *       open  = '(' used so far; can add '(' while open < n.
 *       close = ')' used so far; can add ')' while close < open (there is an unmatched '(').
 *   - Rule that stays true: the prefix in sb is always valid: close <= open <= n.
 *   - Decision: length == 2n -> full and valid, save it.
 *               else try '(' if open < n, then try ')' if close < open; undo each after its call.
 *   - Return: result. Trying '(' first gives the output in the order shown in the example.
 *
 * STEPS
 *   1. build(result, empty sb, open = 0, close = 0, n).
 *   2. If sb length == 2n: add sb.toString() and return.
 *   3. If open < n: append '(', build(open + 1, close), delete the last char.
 *   4. If close < open: append ')', build(open, close + 1), delete the last char.
 *   5. Return result.
 *
 * DRY RUN   n = 2   (one row per call, in call order)
 *
 *    sb on entry | open | close | open < n ? | close < open ? | action
 *   -------------+------+-------+------------+----------------+-------------------------
 *    ""          |  0   |   0   |    yes     |      no        | add '('
 *    "("         |  1   |   0   |    yes     |      yes       | add '(' (then ')' later)
 *    "(("        |  2   |   0   |    no      |      yes       | add ')'
 *    "(()"       |  2   |   1   |    no      |      yes       | add ')'
 *    "(())"      |  2   |   2   |     -      |       -        | length 4 = 2n, save "(())"
 *    "()"        |  1   |   1   |    yes     |      no        | add '('  (back in "(", the ')' branch)
 *    "()("       |  2   |   1   |    no      |      yes       | add ')'
 *    "()()"      |  2   |   2   |     -      |       -        | length 4 = 2n, save "()()"
 *
 *   Returns ["(())","()()"].
 *
 * EDGE CASES
 *   n = 1   -> ["()"]                 the only pair
 *   n = 2   -> ["(())","()()"]        the dry run
 *   n = 3   -> 5 strings              the LeetCode example
 *   n = 8   -> 1430 strings           the 8th Catalan number, still fast
 *
 * COMMON MISTAKES
 *   - Allowing ')' when close < n instead of close < open: you get invalid strings like ")(".
 *   - Building all 2^(2n) strings and checking each: correct but much slower.
 *   - Forgetting deleteCharAt after a call: the shared builder keeps the old char, so the
 *     ')' branch starts from the wrong prefix and strings grow past 2n.
 *
 * COMPLEXITY
 *   O(4^n / sqrt(n)) time (the number of valid strings is the nth Catalan number, each O(n) to copy),
 *   O(n) extra space for the recursion and the builder, not counting the output.
 *
 * RELATED
 *   LeetCode 20 (ValidParentheses, Stack/): checks one string; here the open/close counts
 *     replace the stack because there is only one bracket type.
 *   LeetCode 17 (LetterCombinations, Backtracking/): also builds a string char by char,
 *     but every choice is always allowed.
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(2));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        build(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private static void build(List<String> result, StringBuilder sb, int open, int close, int n) {
        if (sb.length() == 2 * n) {
            result.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append('(');
            build(result, sb, open + 1, close, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(')');
            build(result, sb, open, close + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

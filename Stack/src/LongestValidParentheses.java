import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 32. Longest Valid Parentheses
 * https://leetcode.com/problems/longest-valid-parentheses/
 *
 * PROBLEM
 *   - Input: a string s of only '(' and ')'.
 *   - Return: the length of the longest substring that is well-formed (balanced) parentheses.
 *   - s can be empty; length up to 3 * 10^4.
 *
 * EXAMPLE
 *   Input: s = ")()())"
 *   Output: 4
 *   Explanation: The longest valid parentheses substring is "()()".
 *
 * HOW TO THINK
 *   - Clue: matching brackets -> stack. "Longest substring" -> measure lengths with indices.
 *   - Technique: push INDICES. The bottom of the stack is always a "wall": the index just before
 *     the current valid run (start with -1, as if there were a wall before the string).
 *     '(' -> push its index (it waits for a match).
 *     ')' -> pop. If the stack is now empty, this ')' had no match: it becomes the new wall.
 *            Otherwise it matched, and the valid run goes from just after the new top to i:
 *            length = i - top.
 *   - Rule that stays true: the stack bottom is the last unmatched ')' (or -1), and above it are
 *     the indices of '(' still waiting for a match.
 *   - Return: the best length seen.
 *
 * STEPS
 *   1. stack = [-1], best = 0.
 *   2. For each i: if s[i] == '(' -> push i.
 *   3.   Else: pop; if stack empty -> push i (new wall); else best = max(best, i - top).
 *   4. Return best.
 *
 * DRY RUN   s = ")(()())"   (stack shown bottom -> top, after the step)
 *
 *    i | char | action                       | stack after | length    | best
 *   ---+------+------------------------------+-------------+-----------+------
 *    - |  -   | start                        | -1          |  -        |  0
 *    0 |  )   | pop -1, empty -> wall at 0   | 0           |  -        |  0
 *    1 |  (   | push 1                       | 0 1         |  -        |  0
 *    2 |  (   | push 2                       | 0 1 2       |  -        |  0
 *    3 |  )   | pop 2, top 1                 | 0 1         | 3-1 = 2   |  2
 *    4 |  (   | push 4                       | 0 1 4       |  -        |  2
 *    5 |  )   | pop 4, top 1                 | 0 1         | 5-1 = 4   |  4
 *    6 |  )   | pop 1, top 0                 | 0           | 6-0 = 6   |  6
 *
 *   Returns 6: "(()())" from index 1 to 6.
 *
 * EDGE CASES
 *   ""          -> 0   empty string: the loop never runs
 *   "(()"       -> 2   official example 1: the first '(' is never matched and stays as a limit
 *   ")()())"    -> 4   official example 2: the last ')' becomes a wall
 *   "()(())"    -> 6   two valid runs side by side join into one
 *   "((("       -> 0   nothing ever matches
 *   "))))"      -> 0   every ')' just moves the wall
 *   "()(()"     -> 2   the unmatched '(' at index 2 splits the two runs
 *
 * COMMON MISTAKES
 *   - Counting matched pairs * 2: "()(()" has 2 pairs but the longest run is only 2, not 4.
 *   - Starting with an empty stack: the first matched ')' has nothing to measure from; push -1.
 *   - Forgetting to push the unmatched ')' as the new wall: later runs measure from too far left.
 *
 * COMPLEXITY
 *   O(n) time, O(n) extra space for the stack.
 *
 * RELATED
 *   LeetCode 20 (ValidParentheses, Stack/): only checks whether the whole string is valid.
 *   LeetCode 22 (GenerateParentheses, Backtracking/): builds every valid string of n pairs.
 *   LeetCode 84 (LargestRectangleInHistogram, Stack/): also measures a width as i - top after a pop.
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int best = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    best = Math.max(best, i - stack.peek());
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        LongestValidParentheses sol = new LongestValidParentheses();

        System.out.println(sol.longestValidParentheses(")(()())")); // Expected: 6
        System.out.println(sol.longestValidParentheses(""));        // Expected: 0
        System.out.println(sol.longestValidParentheses("(()"));     // Expected: 2
        System.out.println(sol.longestValidParentheses(")()())"));  // Expected: 4
        System.out.println(sol.longestValidParentheses("()(())"));  // Expected: 6
        System.out.println(sol.longestValidParentheses("((("));     // Expected: 0
        System.out.println(sol.longestValidParentheses("))))"));    // Expected: 0
        System.out.println(sol.longestValidParentheses("()(()"));   // Expected: 2
    }
}

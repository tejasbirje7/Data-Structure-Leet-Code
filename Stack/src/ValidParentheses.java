import java.util.Stack;

/**
 * LeetCode 20. Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/
 *
 * PROBLEM
 *   - Input: a string made only of '(', ')', '{', '}', '[' and ']'.
 *   - Return: true if every open bracket is closed by the same type, in the right order,
 *     and every close bracket has a matching open one before it.
 *
 * EXAMPLE
 *   Input: s = "()"
 *   Output: true
 *
 *   Input: s = "()[]{}"
 *   Output: true
 *
 *   Input: s = "(]"
 *   Output: false
 *
 * HOW TO THINK
 *   - Clue: "closed in the correct order" -> the most recent open bracket must close first -> stack.
 *   - Technique: stack. Trick: for an open bracket, push the close bracket you expect,
 *     so checking a close bracket is just "does it equal the top?".
 *   - Rule that stays true: the stack holds the expected closers of the still-open brackets, newest on top.
 *   - Decision: '(' '{' '['  -> push ')' '}' ']'.
 *               close bracket -> if the stack is empty, or pop() != the bracket, return false.
 *   - Return: stack.isEmpty() at the end; anything left means an open bracket was never closed.
 *
 * STEPS
 *   1. Empty stack of chars.
 *   2. For each char x:
 *   3.   Open bracket: push its matching close bracket.
 *   4.   Close bracket: if the stack is empty or the popped char != x, return false.
 *   5. Return stack.isEmpty().
 *
 * DRY RUN   s = "[()[]]{}"   (stack of expected closers, bottom -> top, after the char)
 *
 *    i | x | action               | stack
 *   ---+---+----------------------+---------
 *    0 | [ | push ]               | ]
 *    1 | ( | push )               | ])
 *    2 | ) | pop ), matches       | ]
 *    3 | [ | push ]               | ]]
 *    4 | ] | pop ], matches       | ]
 *    5 | ] | pop ], matches       | (empty)
 *    6 | { | push }               | }
 *    7 | } | pop }, matches       | (empty)
 *
 *   The stack is empty at the end -> returns true.
 *
 * EDGE CASES
 *   "()"       -> true
 *   "(]"       -> false   pop gives ')' but x is ']'
 *   "([)]"     -> false   right brackets, wrong order: pop gives ']' but x is ')'
 *   "]"        -> false   close bracket with an empty stack
 *   "(("       -> false   the loop ends with the stack not empty
 *   ""         -> true    nothing to match (LeetCode guarantees length >= 1)
 *
 * COMMON MISTAKES
 *   - Forgetting the final isEmpty check: "((" would return true.
 *   - Popping without checking for empty first: "]" throws EmptyStackException.
 *     The isEmpty() || ... short-circuit avoids it.
 *   - Only counting each bracket type: counts can match while the order is wrong ("([)]").
 *
 * COMPLEXITY
 *   O(n) time, O(n) extra space (the stack).
 *
 * RELATED
 *   LeetCode 224 (BasicCalculator, Stack/): the same push at '(' / pop at ')' to save and restore state.
 *   LeetCode 71 (SimplifiedPath, Stack/): '..' pops the most recent directory, the same last-in-first-out idea.
 */
public class ValidParentheses {
    public static void main(String[] args) {
        String s = "[()[]]{}";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char x : s.toCharArray()){
            if(x=='(') {
                stack.push(')');
            }
            else if (x=='{') {
                stack.push('}');
            }
            else if (x=='['){
                stack.push(']');
            }
            else if(stack.isEmpty() || stack.pop() != x){
                return false;
            }
        }
        return stack.isEmpty();
    }
}

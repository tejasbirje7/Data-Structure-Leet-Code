import java.util.Stack;

/**
 * LeetCode 150. Evaluate Reverse Polish Notation
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *
 * PROBLEM
 *   - Input: tokens of an expression in Reverse Polish (postfix) notation: integers and + - * /.
 *   - Return: the value of the expression as an int.
 *   - Division truncates toward zero. The expression is always valid, with no division by zero,
 *     and every value fits in 32 bits.
 *
 * EXAMPLE
 *   Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 *   Output: 22
 *   Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 *   = ((10 * (6 / (12 * -11))) + 17) + 5
 *   = ((10 * (6 / -132)) + 17) + 5
 *   = ((10 * 0) + 17) + 5
 *   = (0 + 17) + 5
 *   = 17 + 5
 *   = 22
 *
 * HOW TO THINK
 *   - Clue: "postfix": every operator works on the two values just before it,
 *     which are always the two newest results -> stack.
 *   - Technique: stack of numbers. A number is pushed; an operator pops two, computes, pushes the result.
 *   - Rule that stays true: the stack holds the values of the finished sub-expressions, newest on top.
 *   - Decision: number -> push it (parseInt also handles negatives like "-11").
 *               + or * -> pop two, push their sum or product (order does not matter).
 *               - or / -> pop b (the top) first, then a; push a - b or a / b (order matters).
 *   - Return: the one value left on the stack.
 *
 * STEPS
 *   1. Empty stack.
 *   2. For each token:
 *   3.   "+" or "*": push pop() + pop() or pop() * pop().
 *   4.   "-" or "/": b = pop(), a = pop(); push a - b or a / b.
 *   5.   Otherwise: push Integer.parseInt(token).
 *   6. Return pop().
 *
 * DRY RUN   tokens = ["10","6","-4","/","*","3","-","5","+"]   (stack bottom -> top, after the token)
 *
 *    token | action                      | stack
 *   -------+-----------------------------+-----------
 *    "10"  | push 10                     | [10]
 *    "6"   | push 6                      | [10,6]
 *    "-4"  | push -4 (a number)          | [10,6,-4]
 *    "/"   | b = -4, a = 6: 6 / -4 = -1  | [10,-1]
 *    "*"   | -1 * 10 = -10               | [-10]
 *    "3"   | push 3                      | [-10,3]
 *    "-"   | b = 3, a = -10: -10 - 3     | [-13]
 *    "5"   | push 5                      | [-13,5]
 *    "+"   | 5 + -13 = -8                | [-8]
 *
 *   Returns -8. 6 / -4 = -1.5 is truncated toward zero to -1 (not -2).
 *
 * EDGE CASES
 *   ["2","1","+","3","*"]       -> 9    (2 + 1) * 3
 *   ["4","13","5","/","+"]      -> 6    13 / 5 = 2
 *   the 13-token EXAMPLE        -> 22   6 / -132 truncates to 0
 *   ["18"]                      -> 18   a single number, no operator
 *   ["-7","2","/"]              -> -3   Java's / truncates toward zero, as LeetCode wants
 *   ["3","-"]                   -> throws EmptyStackException; LeetCode guarantees a valid expression
 *
 * COMMON MISTAKES
 *   - Popping in the wrong order for - and /: the top is the right operand. Compute a - b, not b - a.
 *   - Spotting operators by the first char ('-'): "-11" is a number. Compare the whole token.
 *   - Using Math.floorDiv or double division: LeetCode wants truncation toward zero.
 *
 * COMPLEXITY
 *   O(n) time, O(n) extra space (the stack).
 *
 * RELATED
 *   LeetCode 224 (BasicCalculator, Stack/): an infix expression with parentheses;
 *     the stack saves the outer sum and sign instead of operands.
 *   LeetCode 20 (ValidParentheses, Stack/): the same push / pop-the-newest pattern, matching brackets.
 */
public class ReversePolishNotation {

    public static void main(String[] args) {
        String[] tokens = {"10","6","-4","/","*","3","-","5","+"};
        System.out.println(evalRPN(tokens));
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stk = new Stack<>();
        for (String token : tokens) {
            if(token.equals("+")){
                stk.push(stk.pop() + stk.pop());
            } else if (token.equals("-")) {
                int b = stk.pop();
                int a = stk.pop();
                stk.push(a-b);
            } else if (token.equals("*")) {
                stk.push(stk.pop()*stk.pop());
            } else if (token.equals("/")) {
                int b = stk.pop();
                int a = stk.pop();
                stk.push(a / b);
            } else {
                stk.push(Integer.parseInt(token));
            }
        }
        return stk.pop();
    }
}

import java.util.Stack;

/**
 * LeetCode 224. Basic Calculator
 * https://leetcode.com/problems/basic-calculator/
 *
 * PROBLEM
 *   - Input: a valid expression string with non-negative integers, '+', '-', '(', ')' and spaces.
 *   - Return: its value as an int.
 *   - No eval() or any built-in that evaluates strings.
 *   - '-' can be unary ("-(2+3)"). There is no '*' or '/'.
 *
 * EXAMPLE
 *   Input: s = "1 + 1"
 *   Output: 2
 *
 *   Input: s = " 2-1 + 2 "
 *   Output: 3
 *
 *   Input: s = "(1+(4+5+2)-3)+(6+8)"
 *   Output: 23
 *
 * HOW TO THINK
 *   - Clue: only + and -, but with parentheses. Each '(' starts a fresh sub-sum,
 *     and at ')' we must return to the outer sum. "Pause now, resume later" = stack.
 *   - Technique: running sum + stack. result = sum so far at this level,
 *     sign = +1 or -1 for the next number, number = the digits being read.
 *   - Rule that stays true: result = value of everything finished at the current paren level;
 *     the stack holds (outer result, sign before the '(') for every '(' still open.
 *   - Decision: digit   -> number = 10 * number + digit.
 *               + or -  -> result += sign * number; number = 0; sign = +1 or -1.
 *               (       -> push result, then push sign; reset result = 0, sign = 1.
 *               )       -> result += sign * number; number = 0;
 *                          result *= pop() (the sign before '('); result += pop() (the outer result).
 *               space   -> matches no branch, so it is skipped.
 *   - Return: after the loop, add the last pending number (result += sign * number).
 *
 * STEPS
 *   1. result = 0, number = 0, sign = 1, empty stack.
 *   2. For each char c:
 *   3.   digit: build number. '+' / '-': add sign * number to result, reset number, set sign.
 *   4.   '(': push result, then sign; reset result = 0, sign = 1.
 *   5.   ')': add sign * number, reset number, then result = result * pop() + pop().
 *   6. Add the last number (if any) and return result.
 *
 * DRY RUN   s = "(1+(4+5+2)-3)+(6+8)"   (stack bottom -> top; all values after the step)
 *
 *     i | c | action                           | stack     | result | sign | number
 *   ----+---+----------------------------------+-----------+--------+------+--------
 *     0 | ( | push 0, push +1; reset           | [0,1]     |    0   |  +1  |   0
 *     1 | 1 | digit                            | [0,1]     |    0   |  +1  |   1
 *     2 | + | result += 1                      | [0,1]     |    1   |  +1  |   0
 *     3 | ( | push 1, push +1; reset           | [0,1,1,1] |    0   |  +1  |   0
 *     4 | 4 | digit                            | [0,1,1,1] |    0   |  +1  |   4
 *     5 | + | result += 4                      | [0,1,1,1] |    4   |  +1  |   0
 *     6 | 5 | digit                            | [0,1,1,1] |    4   |  +1  |   5
 *     7 | + | result += 5                      | [0,1,1,1] |    9   |  +1  |   0
 *     8 | 2 | digit                            | [0,1,1,1] |    9   |  +1  |   2
 *     9 | ) | 9 + 2 = 11; 11 * (+1) + 1 = 12   | [0,1]     |   12   |  +1  |   0
 *    10 | - | result += 0; sign = -1           | [0,1]     |   12   |  -1  |   0
 *    11 | 3 | digit                            | [0,1]     |   12   |  -1  |   3
 *    12 | ) | 12 - 3 = 9; 9 * (+1) + 0 = 9     | []        |    9   |  -1  |   0
 *    13 | + | result += 0; sign = +1           | []        |    9   |  +1  |   0
 *    14 | ( | push 9, push +1; reset           | [9,1]     |    0   |  +1  |   0
 *    15 | 6 | digit                            | [9,1]     |    0   |  +1  |   6
 *    16 | + | result += 6                      | [9,1]     |    6   |  +1  |   0
 *    17 | 8 | digit                            | [9,1]     |    6   |  +1  |   8
 *    18 | ) | 6 + 8 = 14; 14 * (+1) + 9 = 23   | []        |   23   |  +1  |   0
 *
 *   number is 0 at the end, so nothing is added. Returns 23.
 *   After a ')' the old sign can stay (row 12): harmless, because number is 0 until the next operator.
 *
 * EDGE CASES
 *   "1 + 1"          -> 2            spaces match no branch and are skipped
 *   " 2-1 + 2 "      -> 3
 *   "-(2+3)"         -> -5           unary minus: result += 1 * 0, then sign -1 multiplies the bracket
 *   "42"             -> 42           no operator at all: the final "add the last number" does the work
 *   "1-(     -2)"    -> 3            unary minus inside a bracket
 *   "2147483647"     -> 2147483647   LeetCode guarantees every number and result fits in an int
 *
 * COMMON MISTAKES
 *   - Forgetting the last number after the loop: "1 + 1" would give 1.
 *   - Popping in the wrong order at ')': sign was pushed last, so it comes out first.
 *     Multiply by it, then add the outer result.
 *   - Not resetting number to 0 after using it: its digits glue onto the next number.
 *   - Building numbers as number = digit: multi-digit numbers break. Use number = 10 * number + digit.
 *
 * COMPLEXITY
 *   O(n) time, O(n) extra space (the stack grows with nested parentheses).
 *
 * RELATED
 *   LeetCode 150 (ReversePolishNotation, Stack/): evaluates an expression with a stack,
 *     but in postfix form, so there are no parentheses or signs to track.
 *   LeetCode 20 (ValidParentheses, Stack/): the same push at '(' / pop at ')' pairing, without the arithmetic.
 */
public class BasicCalculator {

    public static void main(String[] args) {
        String s = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(calculate(s));
    }

    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                number = 10 * number + (int)(c - '0');
            }else if(c == '+' || c == '-'){
                result += sign * number;
                number = 0;
                sign = c == '+' ? 1 : -1;
            }else if(c == '('){
                stack.push(result);
                stack.push(sign);
                sign = 1;
                result = 0;
            }else if(c == ')'){
                result += sign * number;
                number = 0;
                result *= stack.pop();
                result += stack.pop();

            }
        }
        if(number != 0) result += sign * number;
        return result;
    }
}

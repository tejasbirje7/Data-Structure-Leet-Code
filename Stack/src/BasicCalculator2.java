import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 227. Basic Calculator II
 * https://leetcode.com/problems/basic-calculator-ii/
 *
 * PROBLEM
 *   - Input: a valid expression string with non-negative integers, '+', '-', '*', '/' and spaces.
 *     No parentheses.
 *   - Return: its value. Division truncates toward zero. eval() is not allowed.
 *   - Every intermediate result fits in an int.
 *
 * EXAMPLE
 *   Input: s = " 3+5 / 2 "
 *   Output: 5
 *
 * HOW TO THINK
 *   - Clue: '*' and '/' bind tighter than '+' and '-', and there are no parentheses.
 *   - Technique: split the expression into terms that get added at the end. Keep a stack of terms.
 *     When a number is complete, look at the operator BEFORE it:
 *       '+' -> push num            '-' -> push -num
 *       '*' -> push pop() * num    '/' -> push pop() / num
 *     So '*' and '/' are done right away on the last term, and '+'/'-' wait for the final sum.
 *   - Rule that stays true: the sum of the stack is the value of everything before the current
 *     number, and op is the operator in front of the current number.
 *   - Decision: a number is complete at an operator OR at the last character of the string.
 *   - Return: the sum of the stack.
 *   - Java's / truncates toward zero, so -7 / 2 = -3 is exactly what 0 - (7 / 2) needs.
 *
 * STEPS
 *   1. Empty stack, num = 0, op = '+' (as if the expression started with "0+").
 *   2. For each i: if s[i] is a digit -> num = num * 10 + digit.
 *   3.   If s[i] is an operator or i is the last index -> apply op to num (push / combine);
 *        op = s[i]; num = 0.
 *   4. Return the sum of the stack.
 *
 * DRY RUN   s = "14-3*2+8/3"   (stack shown bottom -> top, after the step; digits only build num)
 *
 *    i | char | num after | apply op (before) | stack after  | op after
 *   ---+------+-----------+-------------------+--------------+---------
 *    0 |  1   |     1     | -                 | []           |   +
 *    1 |  4   |    14     | -                 | []           |   +
 *    2 |  -   |     0     | + : push 14       | 14           |   -
 *    3 |  3   |     3     | -                 | 14           |   -
 *    4 |  *   |     0     | - : push -3       | 14 -3        |   *
 *    5 |  2   |     2     | -                 | 14 -3        |   *
 *    6 |  +   |     0     | * : -3 * 2 = -6   | 14 -6        |   +
 *    7 |  8   |     8     | -                 | 14 -6        |   +
 *    8 |  /   |     0     | + : push 8        | 14 -6 8      |   /
 *    9 |  3   |     3     | last index:       |              |
 *      |      |           | / : 8 / 3 = 2     | 14 -6 2      |  end
 *
 *   Sum = 14 - 6 + 2. Returns 10.
 *
 * EDGE CASES
 *   "3+2*2"        -> 7    official example 1: multiplication first
 *   " 3/2 "        -> 1    official example 2: truncates, spaces ignored
 *   "42"           -> 42   one number: applied at the last index
 *   "2*3*4"        -> 24   chained '*' keep combining the top term
 *   "14/3*2"       -> 8    left to right: (14 / 3) * 2 = 4 * 2
 *   "1-7/2"        -> -2   -7 / 2 = -3 truncates toward zero, matching 1 - 3
 *
 * COMMON MISTAKES
 *   - Applying the CURRENT operator instead of the one before the number: the operator after
 *     a number hasn't seen its right side yet.
 *   - Skipping the last number: a string ends with a digit (or space), not an operator, so also
 *     apply at i == n - 1.
 *   - Treating a space as an operator: the "else" must test for + - * / only, or op becomes ' '
 *     in the middle of the string.
 *
 * COMPLEXITY
 *   O(n) time, O(n) extra space for the stack (can be O(1) by keeping only the last term).
 *
 * RELATED
 *   LeetCode 224 (BasicCalculator, Stack/): only '+' and '-' but with parentheses; saves the
 *     result and sign on the stack at each '('.
 *   LeetCode 150 (ReversePolishNotation, Stack/): operators come after their numbers, so a
 *     stack evaluates it directly with no precedence rules.
 *   LeetCode 394 (DecodeString, Stack/): another one-pass string parser that builds numbers digit by digit.
 */
public class BasicCalculator2 {

    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char op = '+';
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            boolean isOperator = c == '+' || c == '-' || c == '*' || c == '/';
            if (isOperator || i == n - 1) {
                if (op == '+') {
                    stack.push(num);
                } else if (op == '-') {
                    stack.push(-num);
                } else if (op == '*') {
                    stack.push(stack.pop() * num);
                } else {
                    stack.push(stack.pop() / num);
                }
                op = c;
                num = 0;
            }
        }
        int result = 0;
        for (int term : stack) {
            result += term;
        }
        return result;
    }

    public static void main(String[] args) {
        BasicCalculator2 sol = new BasicCalculator2();

        System.out.println(sol.calculate("14-3*2+8/3")); // Expected: 10
        System.out.println(sol.calculate(" 3+5 / 2 ")); // Expected: 5
        System.out.println(sol.calculate("3+2*2"));      // Expected: 7
        System.out.println(sol.calculate(" 3/2 "));      // Expected: 1
        System.out.println(sol.calculate("42"));         // Expected: 42
        System.out.println(sol.calculate("2*3*4"));      // Expected: 24
        System.out.println(sol.calculate("14/3*2"));     // Expected: 8
        System.out.println(sol.calculate("1-7/2"));      // Expected: -2
    }
}

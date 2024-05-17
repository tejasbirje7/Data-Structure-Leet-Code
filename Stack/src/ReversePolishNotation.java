import java.util.Stack;

public class ReversePolishNotation {

    public static void main(String[] args) {
        String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(evalRPN(tokens));
    }

    /**
     * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
     * Output: 22
     * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
     */
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

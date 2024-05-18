import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        String s = "[()[]]{}";
        System.out.println(isValid(s));
    }

    /**
     * Given a string s containing just the characters
     * '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     *
     * An input string is valid if:
     *
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * Every close bracket has a corresponding open bracket of the same type.
     *
     * Input: s = "()"
     * Output: true
     *
     * Input: s = "()[]{}"
     * Output: true
     *
     * Input: s = "(]"
     * Output: false
     */
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

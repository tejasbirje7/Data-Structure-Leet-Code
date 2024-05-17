import java.util.Stack;

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

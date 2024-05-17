import java.util.Stack;

/**
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 */
public class MinStack {
    Stack<Integer> store;
    Stack<Integer> minStore;

    public MinStack() {
        store = new Stack<>();
        minStore = new Stack<>();
    }

    public void push(int val) {
        if(!minStore.empty()) {
            int newMin = Math.min(minStore.peek(),val);
            minStore.push(newMin);
        } else {
            minStore.push(val);
        }
        this.store.push(val);
    }

    public void pop() {
        if(!store.isEmpty()) store.pop();
        if(!minStore.isEmpty()) minStore.pop();
    }

    public int top() {
        return store.peek();
    }

    public int getMin() {
        return minStore.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
         minStack.push(-2);
         minStack.push(0);
         minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
         minStack.pop();
         minStack.top();    // return 0
        System.out.println(minStack.getMin()); // return -2
    }
}

import java.util.Stack;

public class MinStack {
    Stack<Integer> store;
    Stack<Integer> minStore;

    public MinStack() {
        store = new Stack<>();
        minStore = new Stack<>();
    }

    /**
     * Design a stack that supports push, pop, top, and
     * retrieving the minimum element in constant time.
     *
     * Implement the MinStack class:
     *
     * MinStack() initializes the stack object.
     * void push(int val) pushes the element val onto the stack.
     * void pop() removes the element on the top of the stack.
     * int top() gets the top element of the stack.
     * int getMin() retrieves the minimum element in the stack.
     * You must implement a solution with O(1) time complexity for each function.
     *
     *
     * Input
     * ["MinStack","push","push","push","getMin","pop","top","getMin"]
     * [[],[-2],[0],[-3],[],[],[],[]]
     *
     * Output
     * [null,null,null,null,-3,null,0,-2]
     *
     * Explanation
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin(); // return -3
     * minStack.pop();
     * minStack.top();    // return 0
     * minStack.getMin(); // return -2
     */
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

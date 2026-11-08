import java.util.Stack;

/**
 * LeetCode 155. Min Stack
 * https://leetcode.com/problems/min-stack/
 *
 * PROBLEM
 *   - Design a stack with push(val), pop(), top() and getMin().
 *   - Every operation must run in O(1) time, getMin included.
 *   - LeetCode only calls pop, top and getMin on a non-empty stack.
 *
 * EXAMPLE
 *   Input
 *   ["MinStack","push","push","push","getMin","pop","top","getMin"]
 *   [[],[-2],[0],[-3],[],[],[],[]]
 *
 *   Output
 *   [null,null,null,null,-3,null,0,-2]
 *
 *   Explanation
 *   MinStack minStack = new MinStack();
 *   minStack.push(-2);
 *   minStack.push(0);
 *   minStack.push(-3);
 *   minStack.getMin(); // return -3
 *   minStack.pop();
 *   minStack.top();    // return 0
 *   minStack.getMin(); // return -2
 *
 * HOW TO THINK
 *   - Clue: "retrieving the minimum element in constant time" -> no scanning; the min must be stored, ready to read.
 *   - Technique: two stacks that move together. store holds the values;
 *     minStore holds, at each level, the min of that value and everything below it.
 *   - Rule that stays true: minStore has the same size as store, and minStore.peek() = min of all values in store.
 *   - Decision: push(val) -> store gets val; minStore gets min(minStore.peek(), val), or val if it is empty.
 *               pop()     -> pop both stacks, so the previous min is back on top automatically.
 *   - Return: top() = store.peek(); getMin() = minStore.peek().
 *   - Why it works: a stack only changes at the top, so the min below a level
 *     cannot change while that level is still there.
 *
 * STEPS
 *   1. Constructor: two empty stacks.
 *   2. push(val): minStore gets min(current min, val), or val if empty; store gets val.
 *   3. pop(): pop each stack if it is not empty.
 *   4. top(): peek store.   getMin(): peek minStore.
 *
 * DRY RUN   push(-2), push(0), push(-3), getMin(), pop(), top(), getMin()
 *           (stacks bottom -> top, after the operation)
 *
 *    operation | store      | minStore   | returns
 *   -----------+------------+------------+---------
 *    push(-2)  | [-2]       | [-2]       |   -
 *    push(0)   | [-2,0]     | [-2,-2]    |   -
 *    push(-3)  | [-2,0,-3]  | [-2,-2,-3] |   -
 *    getMin()  | [-2,0,-3]  | [-2,-2,-3] |   -3
 *    pop()     | [-2,0]     | [-2,-2]    |   -
 *    top()     | [-2,0]     | [-2,-2]    |   0
 *    getMin()  | [-2,0]     | [-2,-2]    |   -2
 *
 *   main prints -3 and then -2 (it calls top() but does not print it).
 *
 * EDGE CASES
 *   push(5), getMin()                              -> 5    minStore was empty, so val itself is the min
 *   push(0), push(1), push(0), pop(), getMin()     -> 0    each copy of the min has its own level
 *   pop() on an empty stack                        -> does nothing (both pops are guarded)
 *   top() or getMin() on an empty stack            -> throws EmptyStackException; LeetCode never does this
 *
 * COMMON MISTAKES
 *   - Keeping one min variable: after the min is popped, the next min is unknown without a scan.
 *   - Pushing to minStore only when val is a new min, but popping minStore on every pop:
 *     the stacks go out of sync. Either push on every push (as here), or pop only when the popped value equals the min.
 *   - In that "push only new mins" version, using < instead of <=: duplicate mins break
 *     (push 0, 1, 0, then pop: the min would be lost).
 *
 * COMPLEXITY
 *   O(1) time per operation, O(n) extra space (minStore has one entry per value).
 *
 * RELATED
 *   LeetCode 155 (StackUsingLinkedList, Stack/): the same problem with one linked list;
 *     each node stores its value and the min below it.
 *   LeetCode 895 (FreqStack, FAQ/): another stack design problem; pop returns the most frequent value instead.
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
        System.out.println(minStack.getMin());
         minStack.pop();
         minStack.top();
        System.out.println(minStack.getMin());
    }
}

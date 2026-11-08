/**
 * LeetCode 155. Min Stack (linked list approach)
 * https://leetcode.com/problems/min-stack/
 *
 * PROBLEM
 *   - Design a stack with push(val), pop(), top() and getMin().
 *   - Every operation must run in O(1) time, getMin included.
 *   - LeetCode only calls pop, top and getMin on a non-empty stack.
 *   - This version builds the stack itself as a linked list instead of using java.util.Stack.
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
 *   - Clue: getMin in O(1) -> each level must remember the min at the moment it was pushed.
 *   - Technique: a singly linked list used as a stack, head = top.
 *     Each Node stores val, min (the min of this node and every node below it) and next.
 *   - Rule that stays true: head.min = min of every value in the stack.
 *   - Decision: push(x) -> new head with min = x if the list is empty, else min(head.min, x); next = old head.
 *               pop()   -> head = head.next (the old min comes back with the old head).
 *   - Return: top() = head.val; getMin() = head.min.
 *   - Why one list is enough: the min below a node cannot change while that node is still there.
 *
 * STEPS
 *   1. head = null (empty stack).
 *   2. push(x): head = new Node(x, head == null ? x : min(head.min, x), head).
 *   3. pop(): head = head.next.
 *   4. top(): head.val.   getMin(): head.min.
 *
 * DRY RUN   push(-2), push(0), push(-3), getMin(), pop(), top(), getMin()
 *           (list from head to tail, each node as val/min, after the operation)
 *
 *    operation | list                         | returns
 *   -----------+------------------------------+---------
 *    push(-2)  | (-2/-2)                      |   -
 *    push(0)   | (0/-2) -> (-2/-2)            |   -
 *    push(-3)  | (-3/-3) -> (0/-2) -> (-2/-2) |   -
 *    getMin()  | (-3/-3) -> (0/-2) -> (-2/-2) |   -3
 *    pop()     | (0/-2) -> (-2/-2)            |   -
 *    top()     | (0/-2) -> (-2/-2)            |   0
 *    getMin()  | (0/-2) -> (-2/-2)            |   -2
 *
 *   main prints -3 and then -2 (it calls top() but does not print it).
 *
 * EDGE CASES
 *   push(5), getMin()                              -> 5    first node: its min is its own value
 *   push(0), push(1), push(0), pop(), getMin()     -> 0    every node keeps its own min
 *   pop() on an empty stack                        -> throws NullPointerException (head is null);
 *                                                     LeetCode never pops an empty stack
 *   top() or getMin() on an empty stack            -> throws NullPointerException; same guarantee
 *
 * COMMON MISTAKES
 *   - Adding new nodes at the tail: pop then has to walk the whole list. Add and remove at the head.
 *   - Storing only one global min field: it is lost once that value is popped. Store the min in every node.
 *   - Computing the new node's min as min(head.val, x): it must be head.min, the min of everything below.
 *
 * COMPLEXITY
 *   O(1) time per operation, O(n) extra space (one node per value, each with an extra min field).
 *
 * RELATED
 *   LeetCode 155 (MinStack, Stack/): the same problem with two java.util.Stack objects.
 *   LeetCode 895 (FreqStack, FAQ/): another stack design problem; pop returns the most frequent value instead.
 */
public class StackUsingLinkedList {

    private Node head;

    public void push(int x) {
        if (head == null) {
            head = new Node(x,x,null);
        } else {
            head = new Node(x,Math.min(head.min, x),head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    private class Node {
        int val;
        int min;
        Node next;

        Node(int val,int min,Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        StackUsingLinkedList minStack = new StackUsingLinkedList();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.top();
        System.out.println(minStack.getMin());
    }

}

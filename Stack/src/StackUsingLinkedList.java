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
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        minStack.top();    // return 0
        System.out.println(minStack.getMin()); // return -2
    }

}

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static class CustomLinkedList {
        ListNode head;
        public void add(int data) {
            ListNode newNode = new ListNode(data);
            if(head == null) {
                head = newNode;
            } else {
                ListNode curr = head;
                while (curr.next != null) {
                    curr = curr.next;
                }
                curr.next = newNode;
            }
        }
    }

    public static void print(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.println(current.val);
            current = current.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
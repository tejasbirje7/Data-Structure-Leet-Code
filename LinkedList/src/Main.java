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
        public void print() {
            while (head != null) {
                System.out.println(head.val);
                head = head.next;
            }
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
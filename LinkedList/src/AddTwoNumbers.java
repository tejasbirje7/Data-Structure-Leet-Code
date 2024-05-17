import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddTwoNumbers {

    public static void main(String[] args) {
        int[] d1 = {9,9,9,9,9,9,9};
        int[] d2 = {9,9,9,9};

        CustomLinkedList l1 = new CustomLinkedList();
        Arrays.stream(d1).forEach(l1::add);

        CustomLinkedList l2 = new CustomLinkedList();
        Arrays.stream(d2).forEach(l2::add);

//        System.out.println(addTwoNumbers(l1,l2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;

            int sum = val1 + val2 + carry;
            int digit = sum % 10;
            carry = sum / 10;

            tail.next = new ListNode(digit);
            tail = tail.next;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        ListNode result = dummyHead.next;
        dummyHead.next = null;
        return result;
    }

    private static class CustomLinkedList {
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


    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


}

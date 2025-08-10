public class AddTwoNumbers {

    /**
     * Input: l1 = [2,4,3], l2 = [5,6,4]
     * Output: [7,0,8]
     * Explanation: 342 + 465 = 807.
     *
     * Input: l1 = [0], l2 = [0]
     * Output: [0]
     *
     * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * Output: [8,9,9,9,0,0,0,1]
     */

    public static void main(String[] args) {
        int[] d1 = {9,9,9,9,9,9,9};
        int[] d2 = {9,9,9,9};

        // Creating Linked List 1: 9 -> 9 -> 9 -> 9 -> 9 -> 9 -> 9
        ListNode d1n7 = new ListNode(9);
        ListNode d1n6 = new ListNode(9, d1n7);
        ListNode d1n5 = new ListNode(9, d1n6);
        ListNode d1n4 = new ListNode(9, d1n5);
        ListNode d1n3 = new ListNode(9, d1n4);
        ListNode d1n2 = new ListNode(9, d1n3);
        ListNode d1n1 = new ListNode(9, d1n2); // Head of list 1

        // Creating Linked List 2: 9 -> 9 -> 9 -> 9
        ListNode d2n4 = new ListNode(9);
        ListNode d2n3 = new ListNode(9, d2n4);
        ListNode d2n2 = new ListNode(9, d2n3);
        ListNode d2n1 = new ListNode(9, d2n2); // Head of list 2

        System.out.println(addTwoNumbers(d1n1,d2n1));
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
        printList(result);
        return result;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Method to print the linked list
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println(); // For new line at the end
    }


}

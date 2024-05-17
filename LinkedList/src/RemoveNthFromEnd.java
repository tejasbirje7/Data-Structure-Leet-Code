public class RemoveNthFromEnd {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0, head);
        ListNode leftPointer = dummyNode;
        ListNode rightPointer = head;

        while (n > 0 && rightPointer != null) {
            rightPointer = rightPointer.next;
            n--;
        }

        while (rightPointer != null) {
            leftPointer = leftPointer.next;
            rightPointer = rightPointer.next;
        }

        // Step 1 :
        // l
        // 1 -> 2-> 3
        // Step 2 :
        //          l
        // 1-> 2 -> 3
        // Step 3 : So it becomes
        // 1 -> 3
        leftPointer.next = leftPointer.next.next;

        return dummyNode.next;

    }
}

import java.util.List;

public class RotateList {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode rotateRight(ListNode head, int k) {

        if (head == null) return head;

        int length = 1;
        ListNode tail = head;

        // Get length
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Check if size exceeds, so that we would
        // not end up creating same list again
        k = k % length;
        if(k == 0) return head;

        // Move pivot until the node from where we need to rotate
        ListNode cur = head;
        for (int i=0; i < length - k - 1; i++) {
            cur = cur.next;
        }

        // Rotate the list
        ListNode newHead = cur.next;
        cur.next = null;
        tail.next = head;

        return newHead;
    }
}

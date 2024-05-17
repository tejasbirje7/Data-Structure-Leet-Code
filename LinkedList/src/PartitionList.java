import java.util.List;

public class PartitionList {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode partition(ListNode head, int x) {

        ListNode leftListNode = new ListNode();
        ListNode rightListNode = new ListNode();
        ListNode leftTail = leftListNode;
        ListNode rightTail = rightListNode;

        while (head != null) {

            if (head.val < x) {
                leftTail.next = head;
                leftTail = leftTail.next;
            } else  {
                rightTail.next = head;
                rightTail = rightTail.next;
            }
            head = head.next;
        }

        leftTail.next = rightListNode.next;
        rightTail.next = null;

        return leftListNode.next;
    }


}

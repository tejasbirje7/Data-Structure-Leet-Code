import java.util.List;

public class PartitionList {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Given the head of a linked list and a value x, partition it such that all nodes
     * less than x come before nodes greater than or equal to x.
     *
     * You should preserve the original relative order of the nodes in each of the two partitions.
     *
     * Input: head = [1,4,3,2,5,2], x = 3
     * Output: [1,2,2,4,3,5]
     */

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

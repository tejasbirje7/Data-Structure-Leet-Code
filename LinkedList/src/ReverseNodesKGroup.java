public class ReverseNodesKGroup {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Given the head of a linked list, reverse the nodes
     * of the list k at a time, and return the modified list.
     *
     * k is a positive integer and is less than or equal to the length of the linked list.
     * If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
     *
     * You may not alter the values in the list's nodes, only nodes themselves may be changed.
     *
     *
     * Input: head = [1,2,3,4,5], k = 2
     * Output: [2,1,4,3,5]
     *
     * Input: head = [1,2,3,4,5], k = 3
     * Output: [3,2,1,4,5]
     */

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0,head);
        ListNode groupPrev = dummy;

        while (true) {

            // Get kth element from the list and set next group
            ListNode kth = getKth(groupPrev,k);
            if (kth == null)
                break;
            ListNode groupNext = kth.next;

            // Reverse the existing group
            ListNode prev = kth.next;
            ListNode curr = groupPrev.next;
            while ( curr != groupNext ) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // Move pointers to iterate in next kth group
            ListNode tmp = groupPrev.next;
            groupPrev.next = kth; // Next kth group's last node
            groupPrev = tmp; // Previously saved next node

        }

        return dummy.next;
    }

    public ListNode getKth(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k -= 1;
        }
        return curr;
    }
}

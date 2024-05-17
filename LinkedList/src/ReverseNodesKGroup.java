public class ReverseNodesKGroup {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

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


public class RemoveDuplicatesfromSortedList {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Given the head of a sorted linked list, delete all nodes that
     * have duplicate numbers, leaving only distinct numbers from
     * the original list. Return the linked list sorted as well.
     *
     * Input: head = [1,2,3,3,4,4,5]
     * Output: [1,2,5]
     *
     * Input: head = [1,1,1,2,3]
     * Output: [2,3]
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyNode = new ListNode(0, head);
        ListNode prev = dummyNode; // To track the node before duplicates
        ListNode curr = head; // To find the last node of duplicate
        prev.next = curr;

        while (curr != null) {

            // Move cur until we find duplicate
            while (curr.next != null && curr.val == curr.next.val) {
                curr = curr.next;
            }

            if (prev.next != curr) { // duplicates detected
                prev.next = curr.next; // Remove duplicate
                curr = prev.next; // Reposition the forward pointer
            } else { // no duplicates, move both pointers
                curr = curr.next;
                prev = prev.next;
            }
        }
        return dummyNode.next;
    }
}

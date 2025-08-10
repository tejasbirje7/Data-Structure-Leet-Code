class ListNode {
    int val;
    SinglyLinkListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedListCycleDetector {

    public boolean hasCycle(SinglyLinkListNode head) {
        // Two pointers: slow moves 1 step at a time, fast moves 2 steps at a time
        SinglyLinkListNode slow = head;
        SinglyLinkListNode fast = head;

        // Continue until fast reaches end (no cycle) or they meet (cycle)
        while (fast != null && fast.next != null) {
            slow = slow.next;         // move slow by 1
            fast = fast.next.next;    // move fast by 2
            if (slow == fast)         // cycle detected
                return true;
        }

        // No cycle found
        return false;
    }

    public static void main(String[] args) {
        LinkedListCycleDetector detector = new LinkedListCycleDetector();

        // Example 1: Cyclic linked list
        SinglyLinkListNode head1 = new SinglyLinkListNode(1);
        head1.next = new SinglyLinkListNode(2);
        head1.next.next = new SinglyLinkListNode(3);
        head1.next.next.next = new SinglyLinkListNode(4);
        head1.next.next.next.next = head1.next; // create cycle

        System.out.println("Has cycle (example 1): " + detector.hasCycle(head1));
        // Expected: true

        // Example 2: Non-cyclic linked list
        SinglyLinkListNode head2 = new SinglyLinkListNode(1);
        head2.next = new SinglyLinkListNode(2);
        head2.next.next = new SinglyLinkListNode(3);
        head2.next.next.next = new SinglyLinkListNode(4);

        System.out.println("Has cycle (example 2): " + detector.hasCycle(head2));
        // Expected: false
    }
}

// Definition for singly-linked list node
class ListNode2 {
    int val;
    ListNode2 next;
    ListNode2(int x) {
        val = x;
        next = null;
    }
}

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values
 * in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 *
 * Phase 1 (Cycle Detection)
 * Use two pointers (slow and fast) moving at different speeds.
 * If they meet, a cycle exists.
 * Phase 2 (Finding Cycle Start)
 * Reset one pointer to head.
 * Move both pointers at the same speed (1 step each).
 * The node where they meet again is the start of the cycle.
 */
public class StartOfCycle {

    /**
     * Detects the start of a cycle in a linked list using Floyd's Cycle Detection Algorithm.
     * If no cycle exists, returns null.
     */
    public ListNode2 detectCycle(ListNode2 head) {
        ListNode2 slow = head; // moves 1 step at a time
        ListNode2 fast = head; // moves 2 steps at a time

        // Step 1: Detect if a cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;           // move slow by 1
            fast = fast.next.next;      // move fast by 2

            if (slow == fast) { // Cycle detected
                // Step 2: Find the entry point of the cycle
                fast = head; // reset fast to head
                while (slow != fast) {
                    slow = slow.next;   // move slow by 1
                    fast = fast.next;   // move fast by 1
                }
                return fast; // or slow — both point to cycle start
            }
        }

        return null; // No cycle found
    }

    public static void main(String[] args) {
        // Create nodes
        ListNode2 n1 = new ListNode2(3);
        ListNode2 n2 = new ListNode2(2);
        ListNode2 n3 = new ListNode2(0);
        ListNode2 n4 = new ListNode2(-4);

        // Connect nodes: 3 -> 2 -> 0 -> -4
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        // Create cycle: -4 -> 2
        n4.next = n2;

        StartOfCycle sol = new StartOfCycle();
        ListNode2 cycleStart = sol.detectCycle(n1);

        if (cycleStart != null) {
            System.out.println("Cycle starts at node with value: " + cycleStart.val); // Cycle starts at node with value: 2
        } else {
            System.out.println("No cycle detected.");
        }
    }
}

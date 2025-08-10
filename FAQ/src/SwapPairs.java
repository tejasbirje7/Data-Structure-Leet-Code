// Definition for singly-linked list
class SinglyLinkListNode {
    int val;
    SinglyLinkListNode next;
    SinglyLinkListNode(int val) { this.val = val; }
    SinglyLinkListNode(int val, SinglyLinkListNode next) { this.val = val; this.next = next; }
}

public class SwapPairs {
    /**
     * Swap Nodes in Pairs
     * Given a linked list, swap every two adjacent nodes and return its head.
     * You must solve the problem without modifying the values in the
     * list's nodes (i.e., only nodes themselves may be changed.)
     *
     *
     * Swaps every two adjacent nodes in a linked list.
     * Uses a dummy node to simplify edge cases.
     *
     *
     * Use a dummy node to handle swaps involving the head easily.
     * pointer moves through the list, processing two nodes at a time.
     * Swap is done by adjusting next pointers without modifying node values.
     * After each swap, pointer moves to the end of the swapped pair.
     */
    public SinglyLinkListNode swapPairs(SinglyLinkListNode head) {
        SinglyLinkListNode dummy = new SinglyLinkListNode(0);
        dummy.next = head;
        SinglyLinkListNode pointer = dummy;

        while (pointer.next != null && pointer.next.next != null) {
            // Identify the two nodes to swap
            SinglyLinkListNode swap1 = pointer.next;
            SinglyLinkListNode swap2 = pointer.next.next;

            // Perform the swap
            swap1.next = swap2.next;
            swap2.next = swap1;
            pointer.next = swap2;

            // Move pointer ahead to next pair
            pointer = swap1;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // Create list: 1 -> 2 -> 3 -> 4
        SinglyLinkListNode n4 = new SinglyLinkListNode(4);
        SinglyLinkListNode n3 = new SinglyLinkListNode(3, n4);
        SinglyLinkListNode n2 = new SinglyLinkListNode(2, n3);
        SinglyLinkListNode n1 = new SinglyLinkListNode(1, n2);

        SwapPairs sol = new SwapPairs();

        // Print swapped list
        SinglyLinkListNode current = sol.swapPairs(n1);
        while (current != null) {
            System.out.print(current.val); // 2 -> 1 -> 4 -> 3
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
    }
}

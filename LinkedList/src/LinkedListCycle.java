public class LinkedListCycle {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        // Example 1: List with no cycle
        ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        System.out.println("List 1 (no cycle):");
        printList(node1);
        System.out.println("Has cycle? " + hasCycle(node1));

        // Example 2: List with cycle
        ListNode c3 = new ListNode(6);
        ListNode c2 = new ListNode(5, c3);
        ListNode c1 = new ListNode(4, c2);
        c3.next = c2; // create a cycle: 6 -> 5 -> 6 ...

        System.out.println("\nList 2 (with cycle):");
        printList(c1); // will print with cycle limit
        System.out.println("Has cycle? " + hasCycle(c1));
    }

    /**
     * Given head, the head of a linked list, determine if the
     * linked list has a cycle in it.
     *
     * There is a cycle in a linked list if there is some node in the list
     * that can be reached again by continuously following the next pointer.
     * Internally, pos is used to denote the index of the node that tail's
     * next pointer is connected to. Note that pos is not passed as a parameter.
     *
     * Return true if there is a cycle in the linked list. Otherwise, return false.
     */
    public static boolean hasCycle(ListNode head) {
        ListNode walker = head;
        ListNode runner = head;
        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (runner == walker) return true;
        }
        return false;
    }


    public static void printList(ListNode head) {
        ListNode current = head;
        int limit = 20; // prevent infinite loop in case of cycle
        while (current != null && limit-- > 0) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        if (current != null) {
            System.out.println("...cycle detected / list too long");
        } else {
            System.out.println("null");
        }
    }
}

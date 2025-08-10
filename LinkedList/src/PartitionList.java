public class PartitionList {

    /**
     * Given the head of a linked list and a value x, partition it such that all nodes
     * less than x come before nodes greater than or equal to x.
     *
     * You should preserve the original relative order of the nodes in each of the two partitions.
     *
     * Input: head = [1,4,3,2,5,2], x = 3
     * Output: [1,2,2,4,3,5]
     */


    public static void main(String[] args) {
        PartitionList partitioner = new PartitionList();

        // Create list: 1 -> 4 -> 3 -> 2 -> 5 -> 2
        ListNode head = buildList(new int[]{1, 4, 3, 2, 5, 2});

        System.out.println("Original List:");
        printList(head);

        // Partition around x = 3
        int x = 3;
        ListNode result = partitioner.partition(head, x);

        System.out.println("\nPartitioned List (x = " + x + "):");
        printList(result);
    }

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
     * The original relative order of the nodes is preserved.
     */
    public ListNode partition(ListNode head, int x) {
        ListNode leftListNode = new ListNode(); // dummy head for < x
        ListNode rightListNode = new ListNode(); // dummy head for >= x
        ListNode leftTail = leftListNode;
        ListNode rightTail = rightListNode;

        while (head != null) {
            if (head.val < x) {
                leftTail.next = head;
                leftTail = leftTail.next;
            } else {
                rightTail.next = head;
                rightTail = rightTail.next;
            }
            head = head.next;
        }

        leftTail.next = rightListNode.next;
        rightTail.next = null; // very important to terminate the final list

        return leftListNode.next;
    }

    // Utility method to build linked list from array
    public static ListNode buildList(int[] values) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // Utility method to print linked list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
}

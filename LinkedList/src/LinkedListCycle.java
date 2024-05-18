public class LinkedListCycle {

    public static void main(String[] args) {

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
    public boolean hasCycle(ListNode head) {
        ListNode walker = head;
        ListNode runner = head;
        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (runner == walker) return true;
        }
        return false;
    }


    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}

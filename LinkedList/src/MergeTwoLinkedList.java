import java.util.Arrays;

public class MergeTwoLinkedList {
    /**
     * You are given the heads of two sorted linked lists list1 and list2.
     * Merge the two lists into one sorted list. The list should be
     * made by splicing together the nodes of the first two lists.
     * Return the head of the merged linked list.
     *
     * Input: list1 = [1,2,4], list2 = [1,3,4]
     * Output: [1,1,2,3,4,4]
     *
     * Input: list1 = [], list2 = []
     * Output: []
     *
     * Input: list1 = [], list2 = [0]
     */

    public static void main(String[] args) {
        // Create first sorted list: 1 -> 3 -> 5
        CustomLinkedList list1 = new CustomLinkedList();
        list1.add(1);
        list1.add(3);
        list1.add(5);

        // Create second sorted list: 2 -> 4 -> 6
        CustomLinkedList list2 = new CustomLinkedList();
        list2.add(2);
        list2.add(4);
        list2.add(6);

        System.out.println("List 1:");
        list1.print();

        System.out.println("List 2:");
        list2.print();

        System.out.println("Merged List:");
        ListNode merged = mergeTwoLists(list1.head, list2.head);
        print(merged);
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (list1 != null && list2 != null) {
            int val1 = list1.val;
            int val2 = list2.val;

            if (val1 < val2) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        if (list1 != null) {
            tail.next = list1;
        } else if (list2 != null) {
            tail.next = list2;
        }

        return dummy.next;
    }

    // Helper method to print list from a given node
    public static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.println("null");
    }

    public static class CustomLinkedList {
        ListNode head;

        public void add(int data) {
            ListNode newNode = new ListNode(data);
            if (head == null) {
                head = newNode;
            } else {
                ListNode curr = head;
                while (curr.next != null) {
                    curr = curr.next;
                }
                curr.next = newNode;
            }
        }

        public void print() {
            ListNode current = head;
            while (current != null) {
                System.out.print(current.val + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}

import java.util.Arrays;

public class MergeTwoLinkedList {
    public static void main(String[] args) {
        int[] list1 = {1,2,4};
        int[] list2 = {1,3,4};

        Main.CustomLinkedList l1 = new Main.CustomLinkedList();
        Arrays.stream(list1).forEach(l1::add);

        Main.CustomLinkedList l2 = new Main.CustomLinkedList();
        Arrays.stream(list2).forEach(l2::add);

        System.out.println();
    }

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

    public Main.ListNode mergeTwoLists(Main.ListNode list1, Main.ListNode list2) {
        Main.ListNode dummy = new Main.ListNode(0);
        Main.ListNode tail = dummy;

        while (list1.next != null && list2.next != null) {
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

        if(list1 != null) {
            tail.next = list1;
        } else if (list2 != null) {
            tail.next = list2;
        }

        Main.ListNode result = dummy.next;
        dummy.next = null;
        return result.next;
    }
}

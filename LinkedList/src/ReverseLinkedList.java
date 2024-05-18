public class ReverseLinkedList {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Given the head of a singly linked list and two integers left and right
     * where left <= right, reverse the nodes of the list from position
     * left to position right, and return the reversed list.
     *
     * Input: head = [1,2,3,4,5], left = 2, right = 4
     * Output: [1,4,3,2,5]
     *
     * Input: head = [5], left = 1, right = 1
     * Output: [5]
     */

    public ListNode reverseBetween(ListNode head, int left, int right) {

     ListNode dummy = new ListNode(0);
     dummy.next = head;

     ListNode curr = dummy;
     ListNode prev = null;

     int i=0;
     while (i<left) {
         prev = curr;
         curr= curr.next;
         i++;
     }

     ListNode curr2 = curr;
     ListNode prev2 = prev;
     ListNode temp;

     while (i <= right) {
         temp = curr2.next;
         curr2.next = prev2;
         prev2 = curr2;
         curr2 = temp;
         i++;
     }

     prev.next = prev2;
     curr.next = curr2;

     return dummy.next;



    }
}

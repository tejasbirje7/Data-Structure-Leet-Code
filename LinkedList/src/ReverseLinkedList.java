public class ReverseLinkedList {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

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

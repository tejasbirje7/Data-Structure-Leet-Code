/**
 * LeetCode 92. Reverse Linked List II
 * https://leetcode.com/problems/reverse-linked-list-ii/
 *
 * PROBLEM
 *   - Input: head of a singly linked list and two positions left <= right (1-based).
 *   - Task: reverse only the nodes from position left to position right.
 *   - Return: the head of the changed list (it changes when left = 1).
 *   - Follow-up: do it in one pass.
 *
 * EXAMPLE
 *   Input: head = [1,2,3,4,5], left = 2, right = 4
 *   Output: [1,4,3,2,5]
 *
 *   Input: head = [5], left = 1, right = 1
 *   Output: [5]
 *
 * HOW TO THINK
 *   - Clue: reverse only a middle part, in one pass, so the parts before and after must stay linked.
 *   - Technique: dummy node + "move to front".
 *     prev = the node just before position left (fixed for the whole loop).
 *     cur = the original left node; it slides one place back each step and ends as the last of the part.
 *     Each step takes the node after cur and moves it to the front of the part (right after prev).
 *   - Rule that stays true: prev.next is the current front of the reversed part, cur is its last node,
 *     and cur.next is the next node to move.
 *   - Decision: repeat right - left times:
 *               temp = cur.next; cur.next = temp.next (unlink temp);
 *               temp.next = prev.next (temp points at the front); prev.next = temp (temp is the new front).
 *   - Return: dummy.next (the dummy handles left = 1, where the head changes).
 *
 * STEPS
 *   1. If head is null or left == right, return head.
 *   2. dummy -> head; move prev from dummy left - 1 steps.
 *   3. cur = prev.next.
 *   4. Repeat right - left times: temp = cur.next; cur.next = temp.next; temp.next = prev.next; prev.next = temp.
 *   5. Return dummy.next.
 *
 * DRY RUN   head = [1,2,3,4,5], left = 2, right = 4   (setup: prev = 1, cur = 2; both stay fixed)
 *
 *    i | temp | cur.next = temp.next | temp.next = prev.next | prev.next = temp | list after
 *   ---+------+----------------------+-----------------------+------------------+-----------
 *    0 |  3   | 2 -> 4               | 3 -> 2                | 1 -> 3           | 1,3,2,4,5
 *    1 |  4   | 2 -> 5               | 4 -> 3                | 1 -> 4           | 1,4,3,2,5
 *
 *   Returns [1,4,3,2,5].
 *
 * EDGE CASES
 *   [5], 1, 1               -> [5]           left == right: early return
 *   null                    -> null          early return
 *   [1,2,3], 1, 3           -> [3,2,1]       whole list: prev = dummy, the head changes
 *   [1,2], 1, 2             -> [2,1]
 *   [1,2,3,4,5], 4, 5       -> [1,2,3,5,4]   part at the end: cur.next becomes null
 *   [1,2], 1, 3             -> crashes: right > length (ruled out), temp is null in the 2nd step
 *
 * COMMON MISTAKES
 *   - Moving prev or cur inside the loop: both stay fixed; only temp changes.
 *   - Doing the three link updates in another order: unlink temp first (cur.next = temp.next),
 *     or the rest of the list is lost.
 *   - Looping right - left + 1 times: one move too many pulls in the node after the part.
 *   - Returning head instead of dummy.next: wrong when left = 1.
 *
 * COMPLEXITY
 *   O(n) time in one pass, O(1) extra space.
 *
 * RELATED
 *   LeetCode 25 (ReverseNodesKGroup, LinkedList/): reverse every block of k nodes instead of one range.
 *   LeetCode 24 (SwapPairs, FAQ/): the k = 2 case: swap each pair of neighbours.
 */
public class ReverseLinkedList {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        ListNode cur = prev.next;

        for (int i = 0; i < right - left; i++) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = prev.next;
            prev.next = temp;
        }

        return dummy.next;

    }
}

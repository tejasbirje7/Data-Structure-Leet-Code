import java.util.List;

/**
 * LeetCode 61. Rotate List
 * https://leetcode.com/problems/rotate-list/
 *
 * PROBLEM
 *   - Input: head of a linked list (0 to 500 nodes) and k (0 <= k <= 2 * 10^9).
 *   - Task: rotate the list to the right by k places.
 *   - Return: the new head.
 *   - k can be far larger than the list, so you can't move one step at a time k times.
 *
 * EXAMPLE
 *   Input: head = [1,2,3,4,5], k = 2
 *   Output: [4,5,1,2,3]
 *
 *   Input: head = [0,1,2], k = 4
 *   Output: [2,0,1]
 *
 * HOW TO THINK
 *   - Clue: rotating right by k = the last k nodes move to the front, in order.
 *     Rotating by the length gives the same list back, so only k % length matters.
 *   - Technique: count the length and find the tail in one walk, then cut and reconnect.
 *     The new tail is node number length - k (1-based); the node after it is the new head.
 *   - Rule that stays true: during the first walk, length = number of nodes from head to tail.
 *   - Decision: k % length == 0 -> nothing changes, return head.
 *               else walk cur length - k - 1 steps from head (to the new tail),
 *               newHead = cur.next, cur.next = null, tail.next = head.
 *   - Return: newHead.
 *
 * STEPS
 *   1. If head is null, return it.
 *   2. Walk tail to the last node, counting length (starts at 1 for head).
 *   3. k = k % length; if k == 0, return head.
 *   4. Walk cur length - k - 1 steps from head.
 *   5. newHead = cur.next; cur.next = null; tail.next = head; return newHead.
 *
 * DRY RUN   head = [1,2,3,4,5], k = 2   (values after the step)
 *
 *    step                         | tail | length | k | cur | list(s) after
 *   ------------------------------+------+--------+---+-----+------------------
 *    start                        |  1   |   1    | 2 |  -  | 1,2,3,4,5
 *    tail walk                    |  2   |   2    | 2 |  -  | 1,2,3,4,5
 *    tail walk                    |  3   |   3    | 2 |  -  | 1,2,3,4,5
 *    tail walk                    |  4   |   4    | 2 |  -  | 1,2,3,4,5
 *    tail walk                    |  5   |   5    | 2 |  -  | 1,2,3,4,5
 *    k = 2 % 5 = 2, not 0         |  5   |   5    | 2 |  1  | 1,2,3,4,5
 *    cur walk (5 - 2 - 1 = 2)     |  5   |   5    | 2 |  2  | 1,2,3,4,5
 *    cur walk                     |  5   |   5    | 2 |  3  | 1,2,3,4,5
 *    newHead = 4, cut 3.next      |  5   |   5    | 2 |  3  | 1,2,3 and 4,5
 *    tail.next = head (5 -> 1)    |  5   |   5    | 2 |  3  | 4,5,1,2,3
 *
 *   Returns [4,5,1,2,3].
 *
 * EDGE CASES
 *   []              -> []          head is null: early return
 *   [0,1,2], k = 4  -> [2,0,1]     4 % 3 = 1
 *   [1,2], k = 2    -> [1,2]       k % length = 0: return head unchanged
 *   [1], k = 99     -> [1]         length 1: any k % 1 = 0
 *   [1,2,3], k = 0  -> [1,2,3]
 *   [1,2,3], k = 1  -> [3,1,2]     cur walks 1 step
 *
 * COMMON MISTAKES
 *   - Skipping k % length: k can be 2 * 10^9, so rotating step by step times out.
 *   - Off-by-one when walking to the new tail: it is node length - k (1-based),
 *     so walk length - k - 1 steps from head.
 *   - Forgetting cur.next = null: the list becomes a cycle.
 *   - Starting length at 0 while tail starts at head: the count is one short.
 *
 * COMPLEXITY
 *   O(n) time (at most two walks), O(1) extra space.
 *
 * RELATED
 *   LeetCode 189 (RotateArray, ArraysAndStrings/): the same rotation on an array, done with three reversals.
 *   LeetCode 189 (RotateArrayCyclic, ArraysAndStrings/): the array version with cyclic replacements.
 *   LeetCode 19 (RemoveNthFromEnd, LinkedList/): also finds the node just before a spot counted from the end.
 */
public class RotateList {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode rotateRight(ListNode head, int k) {

        if (head == null) return head;

        int length = 1;
        ListNode tail = head;

        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        k = k % length;
        if(k == 0) return head;

        ListNode cur = head;
        for (int i=0; i < length - k - 1; i++) {
            cur = cur.next;
        }

        ListNode newHead = cur.next;
        cur.next = null;
        tail.next = head;

        return newHead;
    }
}

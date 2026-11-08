/**
 * LeetCode 25. Reverse Nodes in k-Group
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 *
 * PROBLEM
 *   - Input: head of a linked list and k (1 <= k <= list length).
 *   - Task: reverse the nodes k at a time; a last group with fewer than k nodes stays as it is.
 *   - Return: the head of the changed list.
 *   - Only relink nodes; don't change the values inside them.
 *
 * EXAMPLE
 *   Input: head = [1,2,3,4,5], k = 2
 *   Output: [2,1,4,3,5]
 *
 *   Input: head = [1,2,3,4,5], k = 3
 *   Output: [3,2,1,4,5]
 *
 * HOW TO THINK
 *   - Clue: "k at a time" + "left-out nodes stay" -> handle one group at a time,
 *     and first check that a full group of k exists.
 *   - Technique: dummy node + plain in-place reversal of each group.
 *     getKth walks k steps from groupPrev; null means fewer than k nodes are left.
 *     The reversal starts prev at groupNext (not null), so the group's old first node
 *     ends up linked to the next group without extra work.
 *   - Rule that stays true: groupPrev = the node just before the group being handled
 *     (dummy at first, then the last node of the previous reversed group). Everything up to it is final.
 *   - Decision: kth == null -> stop.
 *               else reverse curr from groupPrev.next up to groupNext,
 *               then groupPrev.next = kth (kth is the group's new first node),
 *               and groupPrev = the old first node (saved in tmp; now the group's last node).
 *   - Return: dummy.next.
 *
 * STEPS
 *   1. dummy -> head, groupPrev = dummy.
 *   2. Loop: kth = node k steps after groupPrev; if null, break.
 *   3. groupNext = kth.next; reverse the group with prev = groupNext, curr = groupPrev.next,
 *      until curr == groupNext.
 *   4. tmp = groupPrev.next (old first node); groupPrev.next = kth; groupPrev = tmp.
 *   5. Return dummy.next.
 *
 * DRY RUN   head = [1,2,3,4,5], k = 2   (prev / curr after the step)
 *
 *    group | groupPrev | kth  | step                          | prev | curr | list from dummy after
 *   -------+-----------+------+-------------------------------+------+------+----------------------
 *      1   |   dummy   |  2   | groupNext = 3, prev = 3       |  3   |  1   | 1,2,3,4,5
 *      1   |           |      | 1.next = 3                    |  1   |  2   | 1,3,4,5   (2 -> 3 still)
 *      1   |           |      | 2.next = 1, curr = groupNext  |  2   |  3   | 1,3,4,5   (2 -> 1 -> 3)
 *      1   |           |      | dummy.next = 2, groupPrev = 1 |      |      | 2,1,3,4,5
 *      2   |     1     |  4   | groupNext = 5, prev = 5       |  5   |  3   | 2,1,3,4,5
 *      2   |           |      | 3.next = 5                    |  3   |  4   | 2,1,3,5   (4 -> 5 still)
 *      2   |           |      | 4.next = 3, curr = groupNext  |  4   |  5   | 2,1,3,5   (4 -> 3 -> 5)
 *      2   |           |      | 1.next = 4, groupPrev = 3     |      |      | 2,1,4,3,5
 *      3   |     3     | null | only 5 is left -> break       |      |      | 2,1,4,3,5
 *
 *   Returns [2,1,4,3,5].
 *
 * EDGE CASES
 *   [1,2,3,4,5], k = 3   -> [3,2,1,4,5]   the left-over 4,5 is not reversed
 *   [1,2,3], k = 1       -> [1,2,3]       each group of one is "reversed" onto itself
 *   [1,2], k = 2         -> [2,1]         k = length: the whole list is reversed
 *   [1,2,3], k = 2       -> [2,1,3]
 *   [1,2], k = 5         -> [1,2]         k > length (ruled out): the first getKth returns null
 *   []                   -> []            getKth hits null at once; LeetCode guarantees at least 1 node
 *
 * COMMON MISTAKES
 *   - Reversing the last short group: check with getKth that k nodes exist before reversing.
 *   - Starting the reversal with prev = null: the group's new last node would cut off the rest.
 *     Start prev at groupNext.
 *   - Overwriting groupPrev.next before saving it: that old first node is the next groupPrev.
 *   - Swapping values instead of nodes: not allowed by the problem.
 *
 * COMPLEXITY
 *   O(n) time (each node is visited twice: once by getKth, once by the reversal), O(1) extra space.
 *
 * RELATED
 *   LeetCode 24 (SwapPairs, FAQ/): the same problem with k fixed at 2.
 *   LeetCode 92 (ReverseLinkedList, LinkedList/): reverse one given range instead of every block.
 */
public class ReverseNodesKGroup {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0,head);
        ListNode groupPrev = dummy;

        while (true) {

            ListNode kth = getKth(groupPrev,k);
            if (kth == null)
                break;
            ListNode groupNext = kth.next;

            ListNode prev = kth.next;
            ListNode curr = groupPrev.next;
            while ( curr != groupNext ) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            ListNode tmp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = tmp;

        }

        return dummy.next;
    }

    public ListNode getKth(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k -= 1;
        }
        return curr;
    }
}

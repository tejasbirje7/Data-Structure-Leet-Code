/**
 * LeetCode 19. Remove Nth Node From End of List
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * PROBLEM
 *   - Input: head of a linked list (1 to 30 nodes) and n, with 1 <= n <= list length.
 *   - Task: remove the nth node counted from the end.
 *   - Return: the head of the changed list (it changes when the head itself is removed).
 *   - Follow-up: do it in one pass.
 *
 * EXAMPLE
 *   Input: head = [1,2,3,4,5], n = 2
 *   Output: [1,2,3,5]
 *
 *   Input: head = [1], n = 1
 *   Output: []
 *
 *   Input: head = [1,2], n = 1
 *   Output: [1]
 *
 * HOW TO THINK
 *   - Clue: "nth from the end" in one pass -> you can't count the length first.
 *   - Technique: two pointers with a fixed gap. Move rightPointer n nodes ahead,
 *     then move both together; when rightPointer falls off the end, leftPointer is in place.
 *   - To delete a node you need the node BEFORE it, so leftPointer starts at a dummy node before head.
 *     The dummy also makes "remove the head" work like any other case.
 *   - Rule that stays true: after the first loop, rightPointer is n + 1 steps ahead of leftPointer.
 *     So when rightPointer == null (one past the last node), leftPointer is just before the target.
 *   - Decision: first loop: n > 0 -> move rightPointer, n--.
 *               second loop: rightPointer != null -> move both.
 *   - Return: dummyNode.next, after leftPointer.next = leftPointer.next.next skips the target.
 *
 * STEPS
 *   1. dummyNode -> head; leftPointer = dummyNode, rightPointer = head.
 *   2. Move rightPointer n steps forward.
 *   3. Move both pointers until rightPointer is null.
 *   4. leftPointer.next = leftPointer.next.next (skip the target).
 *   5. Return dummyNode.next.
 *
 * DRY RUN   head = [1,2,3,4,5], n = 2   (pointers after the step)
 *
 *    phase | step | leftPointer | rightPointer | n
 *   -------+------+-------------+--------------+---
 *    start |  -   |   dummy     |      1       | 2
 *    gap   |  1   |   dummy     |      2       | 1
 *    gap   |  2   |   dummy     |      3       | 0
 *    walk  |  1   |     1       |      4       | 0
 *    walk  |  2   |     2       |      5       | 0
 *    walk  |  3   |     3       |     null     | 0
 *    remove: leftPointer = 3, so 3 -> 4 -> 5 becomes 3 -> 5 (node 4 is skipped)
 *
 *   Returns [1,2,3,5].
 *
 * EDGE CASES
 *   [1], n = 1         -> []        rightPointer reaches null at once; dummy.next = null
 *   [1,2], n = 1       -> [1]       remove the last node: 1.next = null
 *   [1,2], n = 2       -> [2]       remove the head: leftPointer stays on dummy
 *   [1,2], n = 5       -> [2]       n > length (ruled out): the gap loop stops at null, head removed silently
 *   [1,2], n = 0       -> crashes: leftPointer ends on the last node, leftPointer.next.next is a
 *                                   NullPointerException; LeetCode guarantees n >= 1
 *   []                 -> crashes on dummyNode.next.next; LeetCode guarantees at least 1 node
 *
 * COMMON MISTAKES
 *   - Starting leftPointer at head instead of the dummy: it stops on the target, not before it,
 *     and removing the head (n = length) needs special code.
 *   - Off-by-one in the gap: rightPointer moves exactly n steps from head, leftPointer starts one before head.
 *   - Returning head instead of dummyNode.next: wrong when the head was removed.
 *   - Counting the length first: correct, but two passes; the follow-up asks for one.
 *
 * COMPLEXITY
 *   O(L) time in one pass (L = list length), O(1) extra space.
 *
 * RELATED
 *   LeetCode 61 (RotateList, LinkedList/): also finds a node counted from the end (the new tail), using the length.
 *   LeetCode 141 (LinkedListCycle, LinkedList/): another two-pointer walk, with different speeds instead of a gap.
 */
public class RemoveNthFromEnd {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0, head);
        ListNode leftPointer = dummyNode;
        ListNode rightPointer = head;

        while (n > 0 && rightPointer != null) {
            rightPointer = rightPointer.next;
            n--;
        }

        while (rightPointer != null) {
            leftPointer = leftPointer.next;
            rightPointer = rightPointer.next;
        }

        leftPointer.next = leftPointer.next.next;

        return dummyNode.next;

    }
}

/**
 * LeetCode 160. Intersection of Two Linked Lists
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 *
 * PROBLEM
 *   - Input: the heads of two singly linked lists, headA and headB.
 *   - Return: the node where they start sharing nodes, or null if they never meet.
 *   - The lists have no cycles and must keep their structure.
 *   - Follow-up: O(m + n) time and O(1) extra space.
 *
 * EXAMPLE
 *   Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 *   Output: Intersected at '8'
 *   Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
 *     From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5].
 *     There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 *     Note that the intersected node's value is not 1 because the nodes with value 1 in A and B
 *     (2nd node in A and 3rd node in B) are different node references.
 *
 * HOW TO THINK
 *   - Clue: the lists have different lengths before the shared part, so walking both at the same speed doesn't line up.
 *   - Technique: two pointers that switch lists. Pointer a walks A then B; pointer b walks B then A.
 *     Both then walk (length of A + length of B) steps in total, so they line up at the shared part.
 *   - Rule that stays true: after the same number of steps, a and b have both walked
 *     (A's own part + B's own part + some shared nodes), so they reach the first shared node together.
 *   - Decision: each step, a pointer at null jumps to the other list's head; otherwise it moves to next.
 *     Stop when a == b (the same node, not the same value).
 *   - Return: a. If there's no shared node, both reach null at the same step, so a == b == null.
 *
 * STEPS
 *   1. a = headA, b = headB.
 *   2. While a != b:
 *   3.   a = (a == null) ? headB : a.next.  b = (b == null) ? headA : b.next.
 *   4. Return a.
 *
 * DRY RUN   input = A: 4 -> 1 -> [8 -> 4 -> 5], B: 5 -> 6 -> 1 -> [8 -> 4 -> 5]   ([..] = shared nodes, * = shared)
 *
 *    step | a after          | b after          | a == b ?
 *   ------+------------------+------------------+---------
 *      0  | 4 (A)            | 5 (B)            | no
 *      1  | 1 (A)            | 6 (B)            | no
 *      2  | 8*               | 1 (B)            | no
 *      3  | 4*               | 8*               | no
 *      4  | 5*               | 4*               | no
 *      5  | null             | 5*               | no
 *      6  | 5 (B head)       | null             | no  (a jumps to B)
 *      7  | 6 (B)            | 4 (A head)       | no  (b jumps to A)
 *      8  | 1 (B)            | 1 (A)            | no  (same value, different nodes)
 *      9  | 8*               | 8*               | yes
 *
 *   Returns the node with value 8. Each pointer walked 9 steps = 2 + 3 (own parts) + 3 (shared) + 1 (null).
 *
 * EDGE CASES
 *   A = [2,6,4], B = [1,5] (no shared nodes)   -> null          both reach null together after 3 + 2 + 1 steps
 *   A and B are the same list                  -> head          a == b before the loop starts
 *   A = [1,9,1,2,4], B = [3] -> shared [2,4]   -> node 2        official example 2
 *   same length before the shared part         -> found in the first pass, no switching needed
 *
 * COMMON MISTAKES
 *   - Comparing values instead of node references: the two 1's above have the same value but are different nodes.
 *   - Switching with "a.next == null ? headB : a.next": a never becomes null, so with no shared node
 *     the pointers never meet and the loop runs forever.
 *   - Using a HashSet of A's nodes: correct, but O(m) extra space breaks the follow-up.
 *
 * COMPLEXITY
 *   O(m + n) time (each pointer walks at most m + n + 1 steps), O(1) extra space.
 *
 * RELATED
 *   LeetCode 141 (LinkedListCycle, LinkedList/): another two-pointer trick where the pointers must meet.
 *   LeetCode 142 (StartOfCycle, FAQ/): finds where a cycle starts by making two pointers walk equal distances.
 *   LeetCode 19 (RemoveNthFromEnd, LinkedList/): also lines pointers up by controlling how far each one walks.
 */
public class IntersectionOfTwoLinkedLists {

    public static void main(String[] args) {
        ListNode shared = build(8, 4, 5);
        ListNode headA = new ListNode(4, new ListNode(1, shared));
        ListNode headB = new ListNode(5, new ListNode(6, new ListNode(1, shared)));
        ListNode meet = getIntersectionNode(headA, headB);
        System.out.println(meet == null ? "null" : "Intersected at '" + meet.val + "'");
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }
        return a;
    }

    private static ListNode build(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : values) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}

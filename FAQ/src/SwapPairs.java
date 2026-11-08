// Definition for singly-linked list
class SinglyLinkListNode {
    int val;
    SinglyLinkListNode next;
    SinglyLinkListNode(int val) { this.val = val; }
    SinglyLinkListNode(int val, SinglyLinkListNode next) { this.val = val; this.next = next; }
}

/**
 * LeetCode 24. Swap Nodes in Pairs
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 *
 * PROBLEM
 *   - Input: the head of a singly linked list.
 *   - Task: swap every two adjacent nodes; an odd last node stays where it is.
 *   - Return: the new head.
 *   - Constraint: don't change the values in the nodes; only relink the nodes themselves.
 *
 * EXAMPLE
 *   Input: head = [1,2,3,4]
 *   Output: [2,1,4,3]
 *
 * HOW TO THINK
 *   - Clue: "swap adjacent nodes" + "don't change values" = pointer rewiring, two nodes at a time.
 *   - Technique: dummy node before head, so swapping the first pair is the same as any other
 *     pair (the head changes, and dummy.next always holds the real head).
 *   - Rule that stays true: pointer is the node just before the next pair to swap;
 *     everything up to pointer is already swapped.
 *   - Decision: pointer.next and pointer.next.next both exist -> swap them:
 *                 swap1.next = swap2.next; swap2.next = swap1; pointer.next = swap2;
 *                 then pointer = swap1 (swap1 is now the end of the swapped pair).
 *               fewer than two nodes left -> stop.
 *   - Return: dummy.next.
 *
 * STEPS
 *   1. dummy.next = head, pointer = dummy.
 *   2. While pointer.next and pointer.next.next are not null:
 *   3.   swap1 = pointer.next, swap2 = swap1's next.
 *   4.   swap1.next = swap2.next; swap2.next = swap1; pointer.next = swap2.
 *   5.   pointer = swap1.
 *   6. Return dummy.next.
 *
 * DRY RUN   head = 1 -> 2 -> 3 -> 4
 *
 *    step | pointer | swap1 | swap2 | list after rewiring       | pointer after
 *   ------+---------+-------+-------+---------------------------+---------------
 *     1   | dummy   |   1   |   2   | dummy -> 2 -> 1 -> 3 -> 4 |      1
 *     2   |   1     |   3   |   4   | dummy -> 2 -> 1 -> 4 -> 3 |      3
 *     3   |   3     |   -   |   -   | 3.next is null, stop      |      3
 *
 *   Returns dummy.next = 2: 2 -> 1 -> 4 -> 3.
 *
 * EDGE CASES
 *   []          -> []          dummy.next is null, the loop never runs
 *   [1]         -> [1]         only one node, nothing to swap
 *   [1,2]       -> [2,1]       one swap, the head changes
 *   [1,2,3]     -> [2,1,3]     the odd last node stays in place
 *
 * COMMON MISTAKES
 *   - Wrong order of the three assignments: set swap1.next = swap2.next first,
 *     or the rest of the list is lost.
 *   - Forgetting pointer.next = swap2: the node before the pair still points to swap1,
 *     so swap2 drops out of the list.
 *   - Moving pointer to swap2 instead of swap1: after the swap, swap1 is the second node.
 *   - Swapping the val fields: easy, but the problem forbids it.
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 25 (ReverseNodesKGroup, LinkedList/): reverse every k nodes; this problem is k = 2.
 *   LeetCode 92 (ReverseLinkedList, LinkedList/): same dummy-node plus relinking, on one range.
 */
public class SwapPairs {
    public SinglyLinkListNode swapPairs(SinglyLinkListNode head) {
        SinglyLinkListNode dummy = new SinglyLinkListNode(0);
        dummy.next = head;
        SinglyLinkListNode pointer = dummy;

        while (pointer.next != null && pointer.next.next != null) {
            SinglyLinkListNode swap1 = pointer.next;
            SinglyLinkListNode swap2 = pointer.next.next;

            swap1.next = swap2.next;
            swap2.next = swap1;
            pointer.next = swap2;

            pointer = swap1;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // Create list: 1 -> 2 -> 3 -> 4
        SinglyLinkListNode n4 = new SinglyLinkListNode(4);
        SinglyLinkListNode n3 = new SinglyLinkListNode(3, n4);
        SinglyLinkListNode n2 = new SinglyLinkListNode(2, n3);
        SinglyLinkListNode n1 = new SinglyLinkListNode(1, n2);

        SwapPairs sol = new SwapPairs();

        SinglyLinkListNode current = sol.swapPairs(n1);
        while (current != null) {
            System.out.print(current.val); // 2 -> 1 -> 4 -> 3
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
    }
}

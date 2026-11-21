/**
 * LeetCode 206. Reverse Linked List
 * https://leetcode.com/problems/reverse-linked-list/
 *
 * PROBLEM
 *   - Input: the head of a singly linked list (0 to 5000 nodes).
 *   - Return: the head of the reversed list.
 *   - Rewire the existing nodes; no new nodes are needed.
 *
 * EXAMPLE
 *   Input: head = [1,2,3,4,5]
 *   Output: [5,4,3,2,1]
 *
 * HOW TO THINK
 *   - Clue: "reverse" a singly linked list -> each node's next must point back to the node before it.
 *   - Technique: three pointers walking once through the list: prev, curr, next.
 *   - Rule that stays true: prev is the head of the part already reversed; curr is the head of the part not yet reversed.
 *   - Decision: no branching. Each step: save next = curr.next, point curr.next back to prev,
 *     then move prev and curr one step forward.
 *   - Return: prev. When curr becomes null, prev is the old last node, the new head.
 *
 * STEPS
 *   1. prev = null, curr = head.
 *   2. While curr != null:
 *   3.   next = curr.next; curr.next = prev; prev = curr; curr = next.
 *   4. Return prev.
 *
 * DRY RUN   input = 1 -> 2 -> 3 -> 4 -> 5   (prev, curr and the reversed part are after the step)
 *
 *    step | curr before | next saved | curr.next set to | prev | curr  | reversed part
 *   ------+-------------+------------+------------------+------+-------+------------------
 *      1  |      1      |     2      |      null        |  1   |   2   | 1
 *      2  |      2      |     3      |       1          |  2   |   3   | 2 -> 1
 *      3  |      3      |     4      |       2          |  3   |   4   | 3 -> 2 -> 1
 *      4  |      4      |     5      |       3          |  4   |   5   | 4 -> 3 -> 2 -> 1
 *      5  |      5      |    null    |       4          |  5   | null  | 5 -> 4 -> 3 -> 2 -> 1
 *
 *   curr is null: returns prev = 5. Final list: 5 -> 4 -> 3 -> 2 -> 1 -> null.
 *
 * EDGE CASES
 *   []          -> []          loop never runs, prev is null
 *   [1]         -> [1]         one step: 1.next becomes null (it already was)
 *   [1,2]       -> [2,1]       smallest real reversal
 *   [7,7,7]     -> [7,7,7]     same values, but the nodes are still rewired
 *
 * COMMON MISTAKES
 *   - Setting curr.next = prev before saving curr.next: the rest of the list is lost. Save next first.
 *   - Returning curr or head: curr is null at the end and head is now the tail. Return prev.
 *   - Starting prev at head instead of null: the old head points back to the second node, making a cycle.
 *
 * COMPLEXITY
 *   O(n) time (each node is visited once), O(1) extra space.
 *
 * RELATED
 *   LeetCode 92 (ReverseLinkedList, LinkedList/): the same rewiring, but only between two positions.
 *   LeetCode 25 (ReverseNodesKGroup, LinkedList/): repeats this reversal on every block of k nodes.
 *   LeetCode 234 (PalindromeLinkedList, LinkedList/): reverses the second half and then compares the two halves.
 */
public class ReverseList {

    public static void main(String[] args) {
        ListNode head = build(1, 2, 3, 4, 5);
        print(reverseList(head));
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
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

    private static void print(ListNode node) {
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(node.val).append(" -> ");
            node = node.next;
        }
        System.out.println(sb.append("null"));
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}

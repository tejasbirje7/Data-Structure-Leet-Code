/**
 * LeetCode 143. Reorder List
 * https://leetcode.com/problems/reorder-list/
 *
 * PROBLEM
 *   - Input: the head of a singly linked list L0 -> L1 -> ... -> Ln-1 -> Ln (1 to 5 * 10^4 nodes).
 *   - Return: nothing; reorder it in place to L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
 *   - Constraints: change the links only, not the values.
 *
 * EXAMPLE
 *   Input: head = [1,2,3,4,5]
 *   Output: [1,5,2,4,3]
 *
 * HOW TO THINK
 *   - Clue: the order alternates "next from the front, next from the back".
 *     The back half is needed in reverse order, and a singly list can't walk backwards.
 *   - Technique: three steps: find the middle (slow/fast), reverse the second half, then merge the two halves
 *     by taking one node from each in turn.
 *   - Rule that stays true: while merging, first and second are the next unused nodes of each half;
 *     everything before first is already in its final order.
 *   - Decision: middle loop stops when fast has no two steps left, so the first half keeps the extra node
 *     on odd lengths. Merge loop: splice second between first and first's old next, until second runs out.
 *   - Return: nothing. Cut the first half (slow.next = null) before merging, or the list ends in a cycle.
 *
 * STEPS
 *   1. If the list has fewer than 2 nodes, return.
 *   2. slow = fast = head. While fast.next and fast.next.next aren't null: slow 1 step, fast 2 steps.
 *   3. second = reverse(slow.next); slow.next = null; first = head.
 *   4. While second != null: save both nexts, first.next = second, second.next = firstNext, move both on.
 *
 * DRY RUN   input = 1 -> 2 -> 3 -> 4 -> 5   (lists are after the step)
 *
 *    phase   | step | what happens                              | state after
 *   ---------+------+-------------------------------------------+----------------------------------
 *    middle  |  1   | slow 1 -> 2, fast 1 -> 3                  | slow = 2, fast = 3
 *    middle  |  2   | slow 2 -> 3, fast 3 -> 5                  | slow = 3, fast = 5 (no next: stop)
 *    split   |  -   | second = reverse(4 -> 5); 3.next = null   | first half 1 -> 2 -> 3, second 5 -> 4
 *    merge   |  1   | first = 1, second = 5: put 5 after 1      | 1 -> 5 -> 2 -> 3;  first = 2, second = 4
 *    merge   |  2   | first = 2, second = 4: put 4 after 2      | 1 -> 5 -> 2 -> 4 -> 3;  first = 3, second = null
 *
 *   second is null: stop. Final list: 1 -> 5 -> 2 -> 4 -> 3 -> null.
 *
 * EDGE CASES
 *   [1]          -> [1]           nothing to reorder
 *   [1,2]        -> [1,2]         second half is just 2, it stays at the end
 *   [1,2,3]      -> [1,3,2]       odd length: the middle node ends last
 *   [1,2,3,4]    -> [1,4,2,3]     even length (official example 1)
 *
 * COMMON MISTAKES
 *   - Not cutting slow.next = null: the first half still points into the old second half, and the merge creates a cycle.
 *   - Looping the merge while first != null: the first half can be one node longer, so second becomes null first
 *     and second.next throws. Loop while second != null.
 *   - Swapping values instead of nodes, or pushing nodes into a stack: values must not change,
 *     and a stack costs O(n) extra space.
 *
 * COMPLEXITY
 *   O(n) time (three passes), O(1) extra space.
 *
 * RELATED
 *   LeetCode 234 (PalindromeLinkedList, LinkedList/): same find-middle + reverse, then compares instead of merging.
 *   LeetCode 206 (ReverseList, LinkedList/): the reverse used on the second half.
 *   LeetCode 21 (MergeTwoLinkedList, LinkedList/): also merges two lists, but by value instead of alternating.
 */
public class ReorderList {

    public static void main(String[] args) {
        ListNode head = build(1, 2, 3, 4, 5);
        reorderList(head);
        print(head);
    }

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode second = reverse(slow.next);
        slow.next = null;
        ListNode first = head;
        while (second != null) {
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;
            first.next = second;
            second.next = firstNext;
            first = firstNext;
            second = secondNext;
        }
    }

    private static ListNode reverse(ListNode head) {
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

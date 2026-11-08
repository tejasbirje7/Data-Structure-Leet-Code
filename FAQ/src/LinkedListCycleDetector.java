class ListNode {
    int val;
    SinglyLinkListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

/**
 * LeetCode 141. Linked List Cycle
 * https://leetcode.com/problems/linked-list-cycle/
 *
 * PROBLEM
 *   - Input: head of a singly linked list.
 *   - Return: true if some node can be reached again by following next pointers (a cycle), else false.
 *   - Follow-up: O(1) extra memory.
 *
 * EXAMPLE
 *   Input: head = [3,2,0,-4], pos = 1
 *   Output: true
 *   Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 *
 * HOW TO THINK
 *   - Clue: "cycle" + "O(1) memory" = you can't remember visited nodes, so use two speeds.
 *   - Technique: Floyd's tortoise and hare (fast and slow pointers).
 *     slow moves 1 step, fast moves 2 steps.
 *   - Why it works: with no cycle, fast falls off the end.
 *     With a cycle, both end up going round it; fast gains 1 node per step, so it must land on slow.
 *   - Rule that stays true: fast has moved exactly twice as far as slow.
 *   - Decision: fast or fast.next is null -> end of list, no cycle.
 *               after moving, slow == fast (same node, not same value) -> cycle.
 *   - Return: true as soon as they meet; false when the loop ends.
 *
 * STEPS
 *   1. slow = head, fast = head.
 *   2. While fast != null and fast.next != null:
 *   3.   slow = slow.next; fast = fast.next.next.
 *   4.   If slow == fast, return true.
 *   5. Return false.
 *
 * DRY RUN   1 -> 2 -> 3 -> 4 -> back to 2   (main's example 1; pointers shown after the move)
 *
 *    step | slow | fast | slow == fast?
 *   ------+------+------+---------------------------------
 *      0  |  1   |  1   | start, not checked
 *      1  |  2   |  3   | no
 *      2  |  3   |  2   | no   (fast went 4 -> 2 round the loop)
 *      3  |  4   |  4   | yes -> return true
 *
 *   Returns true.
 *   Main's example 2 (1 -> 2 -> 3 -> 4, no cycle): step 1 slow = 2, fast = 3;
 *     step 2 slow = 3, fast = null -> loop stops, returns false.
 *
 * EDGE CASES
 *   null                    -> false   the loop never runs
 *   [1], no cycle           -> false   fast.next is null
 *   [1], 1 -> itself        -> true    step 1: slow = fast = node 1
 *   [1,2], 2 -> 1           -> true    meet after one step
 *   [1,2,3,4], no cycle     -> false   fast falls off the end
 *   Note: this file uses SinglyLinkListNode from SwapPairs.java (the ListNode class above is unused),
 *     so compile the whole FAQ/src folder, not this file alone.
 *
 * COMMON MISTAKES
 *   - Checking slow == fast before moving: both start at head, so it would always say true.
 *   - Loop condition only "fast != null": fast.next.next throws when fast.next is null.
 *   - Comparing values (slow.val == fast.val): different nodes can hold the same value. Compare references.
 *   - Using a HashSet of visited nodes: works, but O(n) memory instead of O(1).
 *
 * COMPLEXITY
 *   O(n) time (fast catches slow within one lap of the cycle), O(1) extra space.
 *
 * RELATED
 *   LeetCode 141 (LinkedListCycle, LinkedList/): same problem, also in LinkedList/.
 *   LeetCode 142 (StartOfCycle, FAQ/): after they meet, reset one pointer to head
 *     and move both 1 step to find where the cycle starts.
 *   LeetCode 19 (RemoveNthFromEnd, LinkedList/): two pointers again, but a fixed gap instead of two speeds.
 */
public class LinkedListCycleDetector {

    public boolean hasCycle(SinglyLinkListNode head) {
        SinglyLinkListNode slow = head;
        SinglyLinkListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        LinkedListCycleDetector detector = new LinkedListCycleDetector();

        SinglyLinkListNode head1 = new SinglyLinkListNode(1);
        head1.next = new SinglyLinkListNode(2);
        head1.next.next = new SinglyLinkListNode(3);
        head1.next.next.next = new SinglyLinkListNode(4);
        head1.next.next.next.next = head1.next;

        System.out.println("Has cycle (example 1): " + detector.hasCycle(head1));

        SinglyLinkListNode head2 = new SinglyLinkListNode(1);
        head2.next = new SinglyLinkListNode(2);
        head2.next.next = new SinglyLinkListNode(3);
        head2.next.next.next = new SinglyLinkListNode(4);

        System.out.println("Has cycle (example 2): " + detector.hasCycle(head2));
    }
}

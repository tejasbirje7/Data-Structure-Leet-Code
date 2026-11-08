/**
 * LeetCode 141. Linked List Cycle
 * https://leetcode.com/problems/linked-list-cycle/
 *
 * PROBLEM
 *   - Input: head of a linked list (0 to 10^4 nodes).
 *   - Return: true if some node can be reached again by following next (a cycle), else false.
 *   - pos (where the tail links back to) is only used to build the test; it is not passed in.
 *   - Follow-up: O(1) extra memory.
 *
 * EXAMPLE
 *   Input: head = [3,2,0,-4], pos = 1
 *   Output: true
 *   Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 *
 * HOW TO THINK
 *   - Clue: "reached again by following next" + O(1) memory -> Floyd's tortoise and hare.
 *   - Technique: two pointers from head: walker moves 1 step, runner moves 2 steps.
 *     No cycle: runner falls off the end. Cycle: runner laps round and lands on walker.
 *   - Why it works: once both are in the cycle, the gap between them shrinks by 1 every step,
 *     so runner can't jump over walker; they must meet.
 *   - Rule that stays true: after k steps, walker is k nodes from head and runner is 2k.
 *   - Guard: 0 or 1 node with no next -> no cycle, return false before touching runner.next.
 *   - Decision: runner can take 2 more steps -> move both, then same node? -> return true.
 *               runner.next or runner.next.next is null -> end of list, no cycle.
 *   - Return: true on the first meeting, false when the loop ends.
 *
 * STEPS
 *   1. If head == null or head.next == null, return false.
 *   2. walker = head, runner = head.
 *   3. Loop while runner.next != null && runner.next.next != null.
 *   4. walker = walker.next, runner = runner.next.next.
 *   5. If runner == walker (same node, not same value), return true.
 *   6. After the loop, return false.
 *
 * DRY RUN   main's list 2: 4 -> 5 -> 6 -> back to 5   (walker / runner after the step)
 *
 *    step | runner.next / runner.next.next | walker | runner | same node?
 *   ------+--------------------------------+--------+--------+------------------
 *    start|  -                             |   4    |   4    |  -  (not checked)
 *      1  |  5 / 6, both non-null          |   5    |   6    |  no
 *      2  |  5 / 6 (6 -> 5 -> 6)           |   6    |   6    |  yes, return true
 *
 *   Guard first: head = 4 and head.next = 5 are both non-null, so the loop runs.
 *   Returns true. (main's list 1, 1 -> 2 -> 3: after step 1 runner = 3, runner.next is null, returns false.)
 *
 * EDGE CASES
 *   1 -> 2 -> 3              -> false   runner reaches the last node; the loop stops
 *   [1], no cycle            -> false   head.next is null, the guard returns false
 *   [1], pos = 0 (self loop) -> true    head.next is head, so the guard passes;
 *                                       walker = 1, runner = 1.next.next = 1, they meet at once
 *   [1,2], pos = 0           -> true    they meet on node 1 after 2 steps
 *   [] (head = null)         -> false   the guard returns before runner.next is read
 *
 * COMMON MISTAKES
 *   - Checking fast.next (runner.next) before checking that fast or head is null: an empty list
 *     crashes with NullPointerException. Guard head == null first (as here),
 *     or loop while (runner != null && runner.next != null).
 *   - Comparing values instead of nodes: different nodes can hold the same value. Compare with ==.
 *   - Checking runner == walker before the first move: both start at head, so it says true at once.
 *   - Using a HashSet of visited nodes: works, but O(n) space; the follow-up asks for O(1).
 *
 * COMPLEXITY
 *   O(n) time (runner meets walker within one lap after walker enters the cycle), O(1) extra space.
 *
 * RELATED
 *   LeetCode 141 (LinkedListCycleDetector, FAQ/): the same problem, with the null-safe loop check.
 *   LeetCode 142 (StartOfCycle, FAQ/): also return the node where the cycle starts
 *     (after they meet, reset one pointer to head and move both 1 step at a time).
 *   LeetCode 202 (IsHappy, TwoPointers/): the same slow/fast cycle check on a number sequence.
 */
public class LinkedListCycle {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        System.out.println("List 1 (no cycle):");
        printList(node1);
        System.out.println("Has cycle? " + hasCycle(node1));

        ListNode c3 = new ListNode(6);
        ListNode c2 = new ListNode(5, c3);
        ListNode c1 = new ListNode(4, c2);
        c3.next = c2;

        System.out.println("\nList 2 (with cycle):");
        printList(c1);
        System.out.println("Has cycle? " + hasCycle(c1));
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode walker = head;
        ListNode runner = head;
        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (runner == walker) return true;
        }
        return false;
    }


    public static void printList(ListNode head) {
        ListNode current = head;
        int limit = 20;
        while (current != null && limit-- > 0) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        if (current != null) {
            System.out.println("...cycle detected / list too long");
        } else {
            System.out.println("null");
        }
    }
}

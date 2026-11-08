// Definition for singly-linked list node
class ListNode2 {
    int val;
    ListNode2 next;
    ListNode2(int x) {
        val = x;
        next = null;
    }
}

/**
 * LeetCode 142. Linked List Cycle II
 * https://leetcode.com/problems/linked-list-cycle-ii/
 *
 * PROBLEM
 *   - Input: the head of a singly linked list that may contain a cycle.
 *   - Return: the node where the cycle begins, or null if there is no cycle.
 *   - Do not modify the list. Follow-up: O(1) extra memory (so no HashSet of visited nodes).
 *
 * EXAMPLE
 *   Input: head = [3,2,0,-4], pos = 1
 *   Output: tail connects to node index 1
 *   Explanation: There is a cycle in the linked list, where the tail connects to the second node.
 *
 * HOW TO THINK
 *   - Clue: "cycle" + "O(1) memory" = Floyd's fast and slow pointers.
 *   - Technique, phase 1 (detect): slow moves 1 step, fast moves 2. If fast reaches null there
 *     is no cycle; if they meet, there is one.
 *   - Technique, phase 2 (find start): put fast back at head; move both 1 step at a time.
 *     The node where they meet again is the start of the cycle.
 *   - Why it works: let a = steps from head to the cycle start, b = steps from the start to the
 *     meeting point, c = cycle length. Fast walked twice as far: 2(a + b) = a + b + k*c,
 *     so a = k*c - b. Walking a steps from the meeting point lands exactly on the start.
 *   - Rule that stays true: phase 1, fast is always twice as many steps from head as slow.
 *     Phase 2, both pointers are the same number of steps away from the cycle start.
 *   - Decision: fast or fast.next is null -> no cycle, return null.
 *               slow == fast -> run phase 2 and return the meeting node.
 *   - Return: the node itself (not its index or value).
 *
 * STEPS
 *   1. slow = head, fast = head.
 *   2. While fast and fast.next are not null: slow = slow.next, fast = fast.next.next.
 *   3. If slow == fast: set fast = head; while slow != fast move both one step; return fast.
 *   4. If the loop ends, return null.
 *
 * DRY RUN   3 -> 2 -> 0 -> -4 -> (back to 2)   (slow and fast after the step's moves)
 *
 *    step | phase | slow | fast | action
 *   ------+-------+------+------+-------------------------------
 *    0    |   -   |  3   |  3   | start at head
 *    1    |   1   |  2   |  0   | slow +1, fast +2, not equal
 *    2    |   1   |  0   |  2   | slow +1, fast +2, not equal
 *    3    |   1   |  -4  |  -4  | slow +1, fast +2, meet
 *    4    |   2   |  -4  |  3   | fast = head
 *    5    |   2   |  2   |  2   | both +1, meet: cycle start
 *
 *   Returns the node with value 2 (index 1). Here a = 1, b = 2, c = 3: a = 1*3 - 2.
 *
 * EDGE CASES
 *   1 -> 2 -> null          -> null     fast reaches null, no cycle
 *   1 -> (back to 1)        -> node 1   self-loop: they meet at 1 and fast = head is already 1
 *   1 -> 2 -> (back to 1)   -> node 1   cycle starts at head, phase 2 takes 0 steps
 *   1 -> null               -> null     fast.next is null at once
 *   null                    -> null     the loop never runs
 *
 * COMMON MISTAKES
 *   - Returning the phase 1 meeting point: it is somewhere in the cycle, not always the start.
 *   - Moving fast 2 steps in phase 2: both must move 1 step for the a = k*c - b argument.
 *   - Checking slow == fast before moving: they start equal at head, so it "finds" a cycle at once.
 *   - Checking only fast != null: fast.next.next then crashes on the last node.
 *
 * COMPLEXITY
 *   O(n) time (they meet within one extra lap), O(1) extra space.
 *
 * RELATED
 *   LeetCode 141 (LinkedListCycle, LinkedList/): only phase 1, return true/false.
 *   LeetCode 141 (LinkedListCycleDetector, FAQ/): the same detection problem in this folder.
 */
public class StartOfCycle {

    public ListNode2 detectCycle(ListNode2 head) {
        ListNode2 slow = head;
        ListNode2 fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                fast = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return fast;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        ListNode2 n1 = new ListNode2(3);
        ListNode2 n2 = new ListNode2(2);
        ListNode2 n3 = new ListNode2(0);
        ListNode2 n4 = new ListNode2(-4);

        // Connect nodes: 3 -> 2 -> 0 -> -4
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        // Create cycle: -4 -> 2
        n4.next = n2;

        StartOfCycle sol = new StartOfCycle();
        ListNode2 cycleStart = sol.detectCycle(n1);

        if (cycleStart != null) {
            System.out.println("Cycle starts at node with value: " + cycleStart.val); // Cycle starts at node with value: 2
        } else {
            System.out.println("No cycle detected.");
        }
    }
}

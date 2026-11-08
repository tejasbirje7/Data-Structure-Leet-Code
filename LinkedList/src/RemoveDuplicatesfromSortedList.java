
/**
 * LeetCode 82. Remove Duplicates from Sorted List II
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 *
 * PROBLEM
 *   - Input: head of a linked list sorted in non-decreasing order.
 *   - Task: delete EVERY node whose value appears more than once (no copy of it is kept).
 *   - Return: the remaining list, still sorted.
 *   - Not to be confused with LeetCode 83, which keeps one copy of each value.
 *
 * EXAMPLE
 *   Input: head = [1,2,3,3,4,4,5]
 *   Output: [1,2,5]
 *
 *   Input: head = [1,1,1,2,3]
 *   Output: [2,3]
 *
 * HOW TO THINK
 *   - Clue: "sorted" means all copies of a value form one run of neighbours.
 *     So the job is: find each run, and drop the whole run if it is longer than 1.
 *   - Technique: dummy node + two pointers.
 *     prev = the last node kept for sure; curr = scans to the last node of the current run.
 *     The dummy matters because the head itself may be deleted ([1,1,1,2,3]).
 *   - Rule that stays true: dummyNode.next..prev holds only distinct values (final answer so far);
 *     prev.next is the first node of the run being checked.
 *   - Decision: move curr to the last node of its run (while curr.next has the same value).
 *               curr moved (prev.next != curr) -> duplicates: cut out the whole run,
 *                 prev.next = curr.next, curr = prev.next. prev stays, the next run may also repeat.
 *               curr didn't move -> its value is unique: keep it, move prev and curr one step.
 *   - Return: dummyNode.next.
 *
 * STEPS
 *   1. dummyNode -> head, prev = dummyNode, curr = head.
 *   2. Loop while curr != null.
 *   3. While curr.next != null and curr.val == curr.next.val, curr = curr.next.
 *   4. If prev.next != curr: prev.next = curr.next; curr = prev.next.
 *      Else: curr = curr.next; prev = prev.next.
 *   5. Return dummyNode.next.
 *
 * DRY RUN   head = [1,2,3,3,4,4,5]   (3a/3b and 4a/4b are the two copies; prev / curr after the step)
 *
 *    step | curr at start | end of run | prev.next == curr? | action                   | prev | curr | list from dummy
 *   ------+---------------+------------+--------------------+--------------------------+------+------+----------------
 *      1  |       1       |     1      | yes                | keep 1                   |  1   |  2   | 1,2,3,3,4,4,5
 *      2  |       2       |     2      | yes                | keep 2                   |  2   |  3a  | 1,2,3,3,4,4,5
 *      3  |       3a      |     3b     | no (prev.next=3a)  | cut run: 2.next = 4a     |  2   |  4a  | 1,2,4,4,5
 *      4  |       4a      |     4b     | no (prev.next=4a)  | cut run: 2.next = 5      |  2   |  5   | 1,2,5
 *      5  |       5       |     5      | yes                | keep 5                   |  5   | null | 1,2,5
 *
 *   Returns [1,2,5].
 *
 * EDGE CASES
 *   []              -> []        the loop never runs
 *   [1,1,1,2,3]     -> [2,3]     run at the head: dummy.next is moved past it
 *   [1,1]           -> []        everything removed
 *   [1,2,3]         -> [1,2,3]   no duplicates: every step is "keep"
 *   [1,2,2]         -> [1]       run at the end: prev.next becomes null
 *   [1,2,2,3,3]     -> [1]       two runs in a row: prev stays on 1 for both cuts
 *
 * COMMON MISTAKES
 *   - Solving LeetCode 83 by mistake (keeping one copy): here every copy of a repeated value goes.
 *   - Moving prev after cutting a run: the next run may also repeat ([1,2,2,3,3] would give [1,3,3]).
 *   - No dummy node: deleting a run at the head then needs special code.
 *   - Checking curr.val against prev.val instead of prev.next != curr: prev may be the dummy.
 *     The node check simply asks "did curr move inside a run?".
 *
 * COMPLEXITY
 *   O(n) time (each node is passed once by curr), O(1) extra space.
 *
 * RELATED
 *   LeetCode 26 (RemoveDuplicates, ArraysAndStrings/): sorted array, but keep one copy of each value.
 *   LeetCode 80 (RemoveDuplicates2, ArraysAndStrings/): sorted array, keep at most two copies.
 *   LeetCode 86 (PartitionList, LinkedList/): another dummy-head list rebuild in one pass.
 */
public class RemoveDuplicatesfromSortedList {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyNode = new ListNode(0, head);
        ListNode prev = dummyNode;
        ListNode curr = head;
        prev.next = curr;

        while (curr != null) {

            while (curr.next != null && curr.val == curr.next.val) {
                curr = curr.next;
            }

            if (prev.next != curr) {
                prev.next = curr.next;
                curr = prev.next;
            } else {
                curr = curr.next;
                prev = prev.next;
            }
        }
        return dummyNode.next;
    }
}

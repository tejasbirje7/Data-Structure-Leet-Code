/**
 * LeetCode 86. Partition List
 * https://leetcode.com/problems/partition-list/
 *
 * PROBLEM
 *   - Input: head of a linked list and a value x.
 *   - Return: the list rearranged so every node < x comes before every node >= x.
 *   - Keep the original relative order inside each of the two parts (a stable partition).
 *
 * EXAMPLE
 *   Input: head = [1,4,3,2,5,2], x = 3
 *   Output: [1,2,2,4,3,5]
 *
 * HOW TO THINK
 *   - Clue: "preserve the original relative order" rules out swapping values around.
 *     Each node only needs to know which side it belongs to.
 *   - Technique: build two separate lists while walking once, each behind its own dummy head:
 *     left for nodes < x, right for nodes >= x. Then join left's tail to right's head.
 *   - Rule that stays true: leftListNode.next..leftTail = nodes < x seen so far, in order;
 *     rightListNode.next..rightTail = nodes >= x seen so far, in order.
 *   - Decision: head.val < x -> append to left.
 *               otherwise    -> append to right (nodes equal to x go right).
 *   - Finish: leftTail.next = right's first node, and rightTail.next = null.
 *     The null is essential: the last right node may still point to an old neighbour, making a cycle.
 *   - Return: leftListNode.next (if left is empty, that is right's first node).
 *
 * STEPS
 *   1. Two dummy heads (leftListNode, rightListNode) with tails leftTail, rightTail.
 *   2. Walk head through the list.
 *   3. If head.val < x append it to leftTail, else to rightTail; move that tail.
 *   4. Join: leftTail.next = rightListNode.next; cut: rightTail.next = null.
 *   5. Return leftListNode.next.
 *
 * DRY RUN   head = [1,4,3,2,5,2], x = 3   (lists after the step)
 *
 *    step | head.val | < 3? | goes to | left list | right list
 *   ------+----------+------+---------+-----------+-----------
 *      1  |    1     | yes  | left    | 1         | -
 *      2  |    4     | no   | right   | 1         | 4
 *      3  |    3     | no   | right   | 1         | 4,3
 *      4  |    2     | yes  | left    | 1,2       | 4,3
 *      5  |    5     | no   | right   | 1,2       | 4,3,5
 *      6  |    2     | yes  | left    | 1,2,2     | 4,3,5
 *     join| leftTail (2).next = 4; rightTail (5).next = null (it still pointed at the last 2)
 *
 *   Returns [1,2,2,4,3,5].
 *
 * EDGE CASES
 *   [], x = 1            -> []        both lists empty: dummy.next = null
 *   [2,1], x = 2         -> [1,2]
 *   [1,2], x = 5         -> [1,2]     all < x: right is empty, leftTail.next = null
 *   [5,6], x = 1         -> [5,6]     all >= x: left is empty, returns right's first node
 *   [3,1], x = 3         -> [1,3]     a node equal to x goes to the right part
 *
 * COMMON MISTAKES
 *   - Forgetting rightTail.next = null: in the example node 5 still points to the last 2,
 *     so the result loops 2 -> 4 -> 3 -> 5 -> 2 ... forever.
 *   - Using <= x for the left part: nodes equal to x must go right.
 *   - Swapping values or sorting: breaks the relative order inside each part.
 *   - Returning leftListNode (the dummy) instead of leftListNode.next.
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space (two dummy nodes; existing nodes are relinked).
 *
 * RELATED
 *   LeetCode 21 (MergeTwoLinkedList, LinkedList/): the opposite move: two lists joined into one with a dummy and a tail.
 *   LeetCode 27 (RemoveElement, ArraysAndStrings/): also splits values by a test in one stable pass, on an array, but drops the other part.
 */
public class PartitionList {

    public static void main(String[] args) {
        PartitionList partitioner = new PartitionList();

        ListNode head = buildList(new int[]{1, 4, 3, 2, 5, 2});

        System.out.println("Original List:");
        printList(head);

        int x = 3;
        ListNode result = partitioner.partition(head, x);

        System.out.println("\nPartitioned List (x = " + x + "):");
        printList(result);
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode partition(ListNode head, int x) {
        ListNode leftListNode = new ListNode();
        ListNode rightListNode = new ListNode();
        ListNode leftTail = leftListNode;
        ListNode rightTail = rightListNode;

        while (head != null) {
            if (head.val < x) {
                leftTail.next = head;
                leftTail = leftTail.next;
            } else {
                rightTail.next = head;
                rightTail = rightTail.next;
            }
            head = head.next;
        }

        leftTail.next = rightListNode.next;
        rightTail.next = null;

        return leftListNode.next;
    }

    public static ListNode buildList(int[] values) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
}

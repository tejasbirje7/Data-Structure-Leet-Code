/**
 * LeetCode 2. Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 *
 * PROBLEM
 *   - Input: two non-empty linked lists; each holds the digits of a number in reverse order (head = ones digit).
 *   - Return: the sum, as a linked list in the same reverse order.
 *   - Lists can have up to 100 digits each, so the numbers don't fit in an int or a long.
 *   - No leading zeros, except the number 0 itself.
 *
 * EXAMPLE
 *   Input: l1 = [2,4,3], l2 = [5,6,4]
 *   Output: [7,0,8]
 *   Explanation: 342 + 465 = 807.
 *
 *   Input: l1 = [0], l2 = [0]
 *   Output: [0]
 *
 *   Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 *   Output: [8,9,9,9,0,0,0,1]
 *
 * HOW TO THINK
 *   - Clue: "reverse order" means the head is the ones digit.
 *     That is exactly the order you add on paper: right to left, column by column.
 *   - Technique: walk both lists together and do column addition with a carry.
 *     Build the answer behind a dummy head, with a tail pointer to append in O(1).
 *   - Rule that stays true: dummyHead.next..tail = the digits of the sum so far;
 *     carry = 0 or 1, the carry into the next column.
 *   - Decision: a list that has ended counts as digit 0.
 *               sum = val1 + val2 + carry -> append sum % 10, carry = sum / 10.
 *               keep going while either list has nodes OR carry != 0 (the last carry makes a new digit).
 *   - Return: dummyHead.next (the dummy itself is not part of the answer).
 *
 * STEPS
 *   1. dummyHead = new node, tail = dummyHead, carry = 0.
 *   2. Loop while l1 != null || l2 != null || carry != 0.
 *   3. val1 / val2 = the node's value, or 0 if that list has ended.
 *   4. sum = val1 + val2 + carry; append new node (sum % 10); carry = sum / 10.
 *   5. Move tail, and move l1 / l2 forward if they are not null.
 *   6. Return dummyHead.next.
 *
 * DRY RUN   l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]   (carry in = before the step, carry out = after)
 *
 *    step | val1 | val2     | carry in | sum | digit | carry out | result so far
 *   ------+------+----------+----------+-----+-------+-----------+-----------------
 *      1  |  9   |  9       |    0     | 18  |   8   |     1     | 8
 *      2  |  9   |  9       |    1     | 19  |   9   |     1     | 8,9
 *      3  |  9   |  9       |    1     | 19  |   9   |     1     | 8,9,9
 *      4  |  9   |  9       |    1     | 19  |   9   |     1     | 8,9,9,9
 *      5  |  9   |  0 (end) |    1     | 10  |   0   |     1     | 8,9,9,9,0
 *      6  |  9   |  0 (end) |    1     | 10  |   0   |     1     | 8,9,9,9,0,0
 *      7  |  9   |  0 (end) |    1     | 10  |   0   |     1     | 8,9,9,9,0,0,0
 *      8  |  0   |  0 (end) |    1     |  1  |   1   |     0     | 8,9,9,9,0,0,0,1
 *     (step 8 runs only because carry != 0; both lists have already ended)
 *
 *   Returns [8,9,9,9,0,0,0,1] (9999999 + 9999 = 10009998).
 *
 * EDGE CASES
 *   [0] + [0]           -> [0]       one step, sum 0
 *   [5] + [5]           -> [0,1]     the final carry adds a new node
 *   [1,8] + [0]         -> [1,8]     different lengths: missing digits count as 0
 *   [2,4,3] + [5,6,4]   -> [7,0,8]   carry in the middle only
 *   null + null         -> null      the loop never runs; LeetCode guarantees non-empty lists
 *
 * COMMON MISTAKES
 *   - Leaving carry != 0 out of the loop condition: the last digit is lost ([5] + [5] gives [0]).
 *   - Stopping when the shorter list ends: treat a missing node as 0 and keep going.
 *   - Converting the lists to int/long and adding: overflows for 100-digit numbers.
 *   - Returning dummyHead instead of dummyHead.next: an extra 0 at the front.
 *
 * COMPLEXITY
 *   O(max(m, n)) time, O(1) extra space besides the result list (max(m, n) + 1 nodes at most).
 *
 * RELATED
 *   LeetCode 66 (PlusOne, Math/): add 1 to a digit array; same carry idea, but the ones digit is at the end.
 *   LeetCode 21 (MergeTwoLinkedList, LinkedList/): also walks two lists together behind a dummy head.
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        int[] d1 = {9,9,9,9,9,9,9};
        int[] d2 = {9,9,9,9};

        ListNode d1n7 = new ListNode(9);
        ListNode d1n6 = new ListNode(9, d1n7);
        ListNode d1n5 = new ListNode(9, d1n6);
        ListNode d1n4 = new ListNode(9, d1n5);
        ListNode d1n3 = new ListNode(9, d1n4);
        ListNode d1n2 = new ListNode(9, d1n3);
        ListNode d1n1 = new ListNode(9, d1n2);

        ListNode d2n4 = new ListNode(9);
        ListNode d2n3 = new ListNode(9, d2n4);
        ListNode d2n2 = new ListNode(9, d2n3);
        ListNode d2n1 = new ListNode(9, d2n2);

        System.out.println(addTwoNumbers(d1n1,d2n1));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;

            int sum = val1 + val2 + carry;
            int digit = sum % 10;
            carry = sum / 10;

            tail.next = new ListNode(digit);
            tail = tail.next;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        ListNode result = dummyHead.next;
        dummyHead.next = null;
        printList(result);
        return result;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }


}

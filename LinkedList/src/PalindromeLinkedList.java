/**
 * LeetCode 234. Palindrome Linked List
 * https://leetcode.com/problems/palindrome-linked-list/
 *
 * PROBLEM
 *   - Input: the head of a singly linked list (1 to 10^5 nodes, values 0-9).
 *   - Return: true if the values read the same forwards and backwards.
 *   - Follow-up: O(n) time and O(1) extra space.
 *
 * EXAMPLE
 *   Input: head = [1,2,2,1]
 *   Output: true
 *
 * HOW TO THINK
 *   - Clue: "palindrome" needs reading from both ends, but a singly linked list only goes forward.
 *     O(1) space rules out copying it into an array.
 *   - Technique: three linked-list moves in a row:
 *     slow/fast pointers find the middle, reverse the second half, then walk both halves side by side.
 *   - Rule that stays true: fast moves two steps for each step of slow, so when fast reaches the end, slow is at the middle.
 *     For an odd length, slow lands exactly on the middle node; for an even length, on the first node of the second half.
 *   - Decision: in the compare loop, values differ -> return false. Equal -> move both pointers.
 *   - Return: true if the reversed second half runs out with no mismatch.
 *     Loop on the second half: it is never longer than the first.
 *
 * STEPS
 *   1. slow = fast = head. While fast and fast.next aren't null: slow = slow.next, fast = fast.next.next.
 *   2. second = reverse(slow). first = head.
 *   3. While second != null: if first.val != second.val, return false; else move both.
 *   4. Return true.
 *
 * DRY RUN   input = 1 -> 2 -> 3 -> 3 -> 1   (indexes 0..4; pointers are after the step)
 *
 *    phase       | step | pointers after                     | compare | action
 *   -------------+------+------------------------------------+---------+------------------------------
 *    find middle |  0   | slow = idx 0, fast = idx 0         |         | start
 *    find middle |  1   | slow = idx 1, fast = idx 2         |         | move
 *    find middle |  2   | slow = idx 2, fast = idx 4         |         | move; fast.next is null: stop
 *    reverse     |  -   | second = 1 -> 3 -> 3, first = head |         | idx 2..4 (3 -> 3 -> 1) reversed
 *    compare     |  1   | first val 1, second val 1          | 1 == 1  | match, move both
 *    compare     |  2   | first val 2, second val 3          | 2 != 3  | return false
 *
 *   Returns false. (Middle node idx 2 is the last node of both halves; the first half is 1 -> 2 -> 3.)
 *
 * EDGE CASES
 *   [1]           -> true    one node: the second half is the node itself
 *   [1,1]         -> true    even length, two nodes
 *   [1,2]         -> false   mismatch on the first compare
 *   [1,2,2,1]     -> true    even palindrome: slow stops on the second 2
 *   [1,2,3,2,1]   -> true    odd palindrome: the middle 3 is compared with itself
 *
 * COMMON MISTAKES
 *   - Running the compare loop on the first half: for even lengths it is one node longer than the reversed half,
 *     so second becomes null first and second.val throws. Loop while second != null.
 *   - Copying values into an ArrayList and using two indexes: correct, but O(n) extra space breaks the follow-up.
 *   - Forgetting the list is now changed: the second half stays reversed.
 *     Reverse it back if the caller still needs the list.
 *
 * COMPLEXITY
 *   O(n) time (three passes of at most n steps), O(1) extra space.
 *
 * RELATED
 *   LeetCode 206 (ReverseList, LinkedList/): the reverse used in step 2.
 *   LeetCode 143 (ReorderList, LinkedList/): same find-middle + reverse-second-half, then interleaves instead of comparing.
 *   LeetCode 125 (ValidPalindrome, TwoPointers/): the same check on a string, where both ends can be reached directly.
 */
public class PalindromeLinkedList {

    public static void main(String[] args) {
        System.out.println(isPalindrome(build(1, 2, 3, 3, 1)));
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode second = reverse(slow);
        ListNode first = head;
        while (second != null) {
            if (first.val != second.val) {
                return false;
            }
            first = first.next;
            second = second.next;
        }
        return true;
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

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}

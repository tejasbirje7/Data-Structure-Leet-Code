import java.util.Arrays;

/**
 * LeetCode 21. Merge Two Sorted Lists
 * https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * PROBLEM
 *   - Input: heads of two linked lists, each sorted in non-decreasing order (either may be empty).
 *   - Return: the head of one merged sorted list.
 *   - Build it by splicing the existing nodes together (no new nodes needed).
 *
 * EXAMPLE
 *   Input: list1 = [1,2,4], list2 = [1,3,4]
 *   Output: [1,1,2,3,4,4]
 *
 *   Input: list1 = [], list2 = []
 *   Output: []
 *
 *   Input: list1 = [], list2 = [0]
 *   Output: [0]
 *
 * HOW TO THINK
 *   - Clue: both lists are "sorted", so the smallest remaining node is always at one of the two heads.
 *   - Technique: two pointers, one per list, like the merge step of merge sort.
 *     A dummy node in front means the first node needs no special case; tail appends in O(1).
 *   - Rule that stays true: dummy.next..tail is sorted and holds every node taken so far;
 *     list1 and list2 point at the smallest nodes not taken yet.
 *   - Decision: val1 < val2  -> link list1's node, move list1.
 *               otherwise    -> link list2's node, move list2 (ties take list2 first; still sorted).
 *               one list empty -> stop, attach the rest of the other list in one step.
 *   - Return: dummy.next.
 *
 * STEPS
 *   1. dummy = new node, tail = dummy.
 *   2. Loop while both list1 and list2 are not null.
 *   3. Link the smaller head to tail.next, move that list forward, then tail = tail.next.
 *   4. After the loop, tail.next = whichever list is not null (already sorted).
 *   5. Return dummy.next.
 *
 * DRY RUN   list1 = 1 -> 3 -> 5, list2 = 2 -> 4 -> 6   (list1 / list2 = heads before the step)
 *
 *    step | list1 | list2 | check          | action                 | merged after (dummy.next..tail)
 *   ------+-------+-------+----------------+------------------------+--------------------------------
 *      1  |   1   |   2   | 1 < 2          | take 1, move list1     | 1
 *      2  |   3   |   2   | 3 < 2 no       | take 2, move list2     | 1,2
 *      3  |   3   |   4   | 3 < 4          | take 3, move list1     | 1,2,3
 *      4  |   5   |   4   | 5 < 4 no       | take 4, move list2     | 1,2,3,4
 *      5  |   5   |   6   | 5 < 6          | take 5, move list1     | 1,2,3,4,5
 *     end | null  |   6   | list1 is empty | tail.next = list2 (6)  | 1,2,3,4,5,6
 *
 *   Returns 1 -> 2 -> 3 -> 4 -> 5 -> 6.
 *
 * EDGE CASES
 *   [] + []                -> []              loop skipped, nothing to attach, dummy.next = null
 *   [] + [0]               -> [0]             loop skipped, list2 attached
 *   [1,2,3] + []           -> [1,2,3]         loop skipped, list1 attached
 *   [5] + [1,2,3]          -> [1,2,3,5]       list2 runs out first, then 5 is attached
 *   [1,2,4] + [1,3,4]      -> [1,1,2,3,4,4]   on a tie list2's node goes first
 *
 * COMMON MISTAKES
 *   - Forgetting to attach the leftover list after the loop: the largest nodes are lost.
 *   - Forgetting tail = tail.next: every node overwrites the same next pointer.
 *   - Returning dummy instead of dummy.next: an extra 0 at the front.
 *   - Creating new nodes for the result: works, but uses O(m + n) extra space instead of splicing.
 *
 * COMPLEXITY
 *   O(m + n) time, O(1) extra space (existing nodes are relinked).
 *
 * RELATED
 *   LeetCode 88 (Merge, ArraysAndStrings/): the same merge on two sorted arrays, filled from the back.
 *   LeetCode 86 (PartitionList, LinkedList/): the reverse idea: split one list into two dummy-headed lists, then join.
 *   LeetCode 2 (AddTwoNumbers, LinkedList/): also walks two lists side by side behind a dummy head.
 */
public class MergeTwoLinkedList {

    public static void main(String[] args) {
        CustomLinkedList list1 = new CustomLinkedList();
        list1.add(1);
        list1.add(3);
        list1.add(5);

        CustomLinkedList list2 = new CustomLinkedList();
        list2.add(2);
        list2.add(4);
        list2.add(6);

        System.out.println("List 1:");
        list1.print();

        System.out.println("List 2:");
        list2.print();

        System.out.println("Merged List:");
        ListNode merged = mergeTwoLists(list1.head, list2.head);
        print(merged);
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (list1 != null && list2 != null) {
            int val1 = list1.val;
            int val2 = list2.val;

            if (val1 < val2) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        if (list1 != null) {
            tail.next = list1;
        } else if (list2 != null) {
            tail.next = list2;
        }

        return dummy.next;
    }

    public static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.println("null");
    }

    public static class CustomLinkedList {
        ListNode head;

        public void add(int data) {
            ListNode newNode = new ListNode(data);
            if (head == null) {
                head = newNode;
            } else {
                ListNode curr = head;
                while (curr.next != null) {
                    curr = curr.next;
                }
                curr.next = newNode;
            }
        }

        public void print() {
            ListNode current = head;
            while (current != null) {
                System.out.print(current.val + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}

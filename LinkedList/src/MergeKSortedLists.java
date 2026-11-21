import java.util.PriorityQueue;

/**
 * LeetCode 23. Merge k Sorted Lists
 * https://leetcode.com/problems/merge-k-sorted-lists/
 *
 * PROBLEM
 *   - Input: an array of k linked lists, each sorted ascending (0 <= k <= 10^4, up to 10^4 nodes in total).
 *   - Return: one sorted linked list made of all their nodes.
 *   - Some lists (or the whole array) may be empty.
 *
 * EXAMPLE
 *   Input: lists = [[1,4,5],[1,3,4],[2,6]]
 *   Output: [1,1,2,3,4,4,5,6]
 *   Explanation: The linked-lists are:
 *     [
 *       1->4->5,
 *       1->3->4,
 *       2->6
 *     ]
 *     merging them into one sorted linked list:
 *     1->1->2->3->4->4->5->6
 *
 * HOW TO THINK
 *   - Clue: "k sorted lists" -> the next node of the answer is always one of the k current heads.
 *     Picking the smallest of k values again and again = a min-heap.
 *   - Technique: min-heap (PriorityQueue) of list nodes ordered by val, plus a dummy head to build the answer.
 *   - Rule that stays true: the heap holds exactly one node per list that still has nodes left:
 *     the smallest node of that list not yet used.
 *   - Decision: poll the smallest node and append it.
 *     If it has a next node, push that next (its list continues); otherwise its list is finished.
 *   - Return: dummy.next once the heap is empty.
 *
 * STEPS
 *   1. Push every non-null head into the min-heap.
 *   2. dummy = new node, tail = dummy.
 *   3. While the heap isn't empty: smallest = poll; tail.next = smallest; tail = smallest.
 *   4.   If smallest.next != null: push smallest.next.
 *   5. Return dummy.next.
 *
 * DRY RUN   input = a: 1->4->5, b: 1->3->4, c: 2->6   (heap and result are after the step)
 *
 *    step | polled | push next?       | heap after   | result so far
 *   ------+--------+------------------+--------------+------------------
 *      0  |   -    | heads            | 1a 1b 2c     | -
 *      1  |   1a   | yes: 4a          | 1b 2c 4a     | 1
 *      2  |   1b   | yes: 3b          | 2c 3b 4a     | 1 1
 *      3  |   2c   | yes: 6c          | 3b 4a 6c     | 1 1 2
 *      4  |   3b   | yes: 4b          | 4a 4b 6c     | 1 1 2 3
 *      5  |   4a   | yes: 5a          | 4b 5a 6c     | 1 1 2 3 4
 *      6  |   4b   | no: b finished   | 5a 6c        | 1 1 2 3 4 4
 *      7  |   5a   | no: a finished   | 6c           | 1 1 2 3 4 4 5
 *      8  |   6c   | no: c finished   | (empty)      | 1 1 2 3 4 4 5 6
 *
 *   Equal values (1a and 1b, 4a and 4b) may come out in either order; the result is the same.
 *   Returns 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6.
 *
 * EDGE CASES
 *   []              -> []          no lists: heap stays empty
 *   [[]]            -> []          one empty list: null heads are not pushed
 *   [[],[1]]        -> [1]         mix of empty and non-empty
 *   [[1]]           -> [1]         one list: returned as is
 *   [[2,2],[2]]     -> [2,2,2]     all values equal
 *
 * COMMON MISTAKES
 *   - Pushing null heads: the comparator reads null.val and throws. Skip empty lists.
 *   - Forgetting to push polled.next: each list contributes only its first node.
 *   - Merging the lists one by one into a growing result: correct but O(k * N) time.
 *   - Comparator a.val - b.val: can overflow for extreme values; Integer.compare is always safe.
 *
 * COMPLEXITY
 *   O(N log k) time for N nodes in total (each node is pushed and polled once on a heap of size <= k).
 *   O(k) extra space for the heap.
 *
 * RELATED
 *   LeetCode 21 (MergeTwoLinkedList, LinkedList/): the k = 2 case, where a simple comparison replaces the heap.
 *   LeetCode 215 (KthLargestElement, Heap/): also keeps a small heap and repeatedly takes its top.
 *   LeetCode 347 (TopKFrequentElements, Heap/): another heap that holds at most k entries.
 */
public class MergeKSortedLists {

    public static void main(String[] args) {
        ListNode[] lists = {build(1, 4, 5), build(1, 3, 4), build(2, 6)};
        print(mergeKLists(lists));
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        for (ListNode node : lists) {
            if (node != null) {
                heap.offer(node);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode smallest = heap.poll();
            tail.next = smallest;
            tail = smallest;
            if (smallest.next != null) {
                heap.offer(smallest.next);
            }
        }
        return dummy.next;
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

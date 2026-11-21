import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LeetCode 347. Top K Frequent Elements
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * PROBLEM
 *   - Input: an integer array nums and an integer k.
 *   - Return: the k most frequent elements, in any order.
 *   - The answer is guaranteed to be unique; the follow-up asks for better than O(n log n).
 *
 * EXAMPLE
 *   Input: nums = [1,1,1,2,2,3], k = 2
 *   Output: [1,2]
 *   Explanation: (none on LeetCode) 1 appears 3 times and 2 appears 2 times, more than 3 (once).
 *
 * HOW TO THINK
 *   - Clue: "k most frequent" -> count first, then a "top k" selection: a heap of size k.
 *   - Technique: count with a HashMap, then keep a MIN-heap ordered by count, capped at k entries.
 *     The heap's top is the weakest of the current top k, so it is the one to throw out.
 *   - Rule that stays true: after each entry, the heap holds the k most frequent values seen so far.
 *   - Decision: offer every (value, count); heap size > k -> poll the smallest count.
 *               otherwise                                 -> keep it.
 *   - Return: the k values left in the heap. Poll them into the result from the back,
 *     so the most frequent ends up first (any order is accepted).
 *
 * STEPS
 *   1. Count each value in a HashMap.
 *   2. Min-heap of values, compared by count.
 *   3. For each value: offer it; if size > k, poll.
 *   4. Poll the k values into res from index k-1 down to 0; return res.
 *
 * DRY RUN   input = nums = [1,1,1,2,2,3,3,3,3,4], k = 2
 *   counts = {1=3, 2=2, 3=4, 4=1}   (heap is after the step, written value:count, smallest count first)
 *
 *    entry | heap after offer   | size > k? | action    | heap after
 *   -------+--------------------+-----------+-----------+-------------
 *    1:3   | [1:3]              |  1 > 2 no | keep      | [1:3]
 *    2:2   | [2:2, 1:3]         |  2 > 2 no | keep      | [2:2, 1:3]
 *    3:4   | [2:2, 1:3, 3:4]    |  3 > 2 yes| poll 2:2  | [1:3, 3:4]
 *    4:1   | [4:1, 1:3, 3:4]    |  3 > 2 yes| poll 4:1  | [1:3, 3:4]
 *
 *   Poll 1 -> res[1], poll 3 -> res[0]. Returns [3, 1].
 *
 * EDGE CASES
 *   [1], k = 1               -> [1]      single value
 *   [4,5,6], k = 3           -> all 3    k equals the number of distinct values: nothing is polled
 *   [-1,-1,2], k = 1         -> [-1]     negatives are just map keys
 *   [1,1,1,2,2,3], k = 2     -> [1,2]    the LeetCode example (printed as [1, 2])
 *
 * COMMON MISTAKES
 *   - Ordering the heap by value instead of by count.
 *   - Using a max-heap and polling when size > k: that throws out the MOST frequent value.
 *   - Comparing Integer counts with == inside a comparator: use Integer.compare.
 *
 * COMPLEXITY
 *   O(n log k) time: n to count, then at most n distinct offers into a heap of size k + 1.
 *   O(n) extra space for the map.
 *
 * RELATED
 *   LeetCode 215 (KthLargestElement, Heap/): the same size-k min-heap, on the raw values instead of counts.
 *   LeetCode 621 (TaskScheduler, Heap/): count tasks first, then drive a heap by those counts.
 */
public class TopKFrequentElements {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3, 3, 3, 3, 4};
        System.out.println(Arrays.toString(topKFrequent(nums, 2)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int x : nums) {
            count.merge(x, 1, Integer::sum);
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> Integer.compare(count.get(a), count.get(b)));
        for (int value : count.keySet()) {
            heap.offer(value);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = heap.poll();
        }
        return res;
    }

}

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * LeetCode 295. Find Median from Data Stream
 * https://leetcode.com/problems/find-median-from-data-stream/
 *
 * PROBLEM
 *   - Design MedianFinder with addNum(num) and findMedian().
 *   - findMedian returns the median of every number added so far
 *     (the middle value, or the average of the two middle values when the count is even).
 *   - Many calls, so both operations must be faster than re-sorting.
 *
 * EXAMPLE
 *   Input:
 *     ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 *     [[], [1], [2], [], [3], []]
 *   Output: [null, null, null, 1.5, null, 2.0]
 *   Explanation:
 *     MedianFinder medianFinder = new MedianFinder();
 *     medianFinder.addNum(1);    // arr = [1]
 *     medianFinder.addNum(2);    // arr = [1, 2]
 *     medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 *     medianFinder.addNum(3);    // arr[1, 2, 3]
 *     medianFinder.findMedian(); // return 2.0
 *
 * HOW TO THINK
 *   - Clue: "median" of a growing stream -> only the middle one or two values matter, not the full order.
 *   - Technique: two heaps that split the numbers into halves.
 *     low  = max-heap with the smaller half (its top is the largest small number).
 *     high = min-heap with the larger half (its top is the smallest large number).
 *   - Rule that stays true: every number in low <= every number in high,
 *     and low.size() == high.size() or low.size() == high.size() + 1.
 *   - Decision (addNum): push into low, then move low's top to high (this keeps the order rule).
 *     If high is now bigger than low, move high's top back to low (this keeps the size rule).
 *   - Return (findMedian): low is bigger -> low.peek().
 *                          sizes equal   -> (low.peek() + high.peek()) / 2.0.
 *
 * STEPS
 *   1. low = max-heap, high = min-heap.
 *   2. addNum: low.offer(num); high.offer(low.poll()).
 *   3. addNum: if high.size() > low.size(), low.offer(high.poll()).
 *   4. findMedian: odd count -> low.peek(); even count -> average of both tops, divided by 2.0.
 *
 * DRY RUN   addNum(5), addNum(15), findMedian(), addNum(1), findMedian(), addNum(3), findMedian()
 *   (low and high are after the step; low lists largest first, high lists smallest first)
 *
 *    op            | detail                                       | returns | low      | high
 *   ---------------+----------------------------------------------+---------+----------+---------
 *    new           |                                              |    -    | []       | []
 *    addNum(5)     | low gets 5, top 5 moves to high              |         | []       | [5]
 *                  | high bigger, top 5 moves back                |         | [5]      | []
 *    addNum(15)    | low gets 15, top 15 moves to high            |         | [5]      | [15]
 *                  | sizes 1 and 1, no move                       |         | [5]      | [15]
 *    findMedian()  | even count: (5 + 15) / 2.0                   |  10.0   | [5]      | [15]
 *    addNum(1)     | low gets 1, top 5 moves to high              |         | [1]      | [5,15]
 *                  | high bigger, top 5 moves back                |         | [5,1]    | [15]
 *    findMedian()  | odd count: low's top                         |   5.0   | [5,1]    | [15]
 *    addNum(3)     | low gets 3, top 5 moves to high              |         | [3,1]    | [5,15]
 *                  | sizes 2 and 2, no move                       |         | [3,1]    | [5,15]
 *    findMedian()  | even count: (3 + 5) / 2.0                    |   4.0   | [3,1]    | [5,15]
 *
 *   Final state: sorted stream [1,3,5,15]; low holds [1,3], high holds [5,15]; last median 4.0.
 *
 * EDGE CASES
 *   addNum(7), findMedian()              -> 7.0    one number: low's top
 *   addNum(2) x3, findMedian()           -> 2.0    duplicates can sit in either heap
 *   addNum(-1), addNum(-2), findMedian() -> -1.5   negatives work the same way
 *   values near Integer.MAX_VALUE        -> low.peek() + high.peek() overflows int;
 *                                           LeetCode limits values to +-10^5, so it is safe here
 *
 * COMMON MISTAKES
 *   - Dividing by 2 instead of 2.0: integer division turns 1.5 into 1.
 *   - Pushing num straight into the smaller heap: a big number can land in low and break the order rule.
 *     Always pass it through low and then high.
 *   - Forgetting the reverse-order comparator for low: then both heaps are min-heaps.
 *   - Keeping a list and sorting on every findMedian: O(n log n) per call, times out.
 *
 * COMPLEXITY
 *   addNum O(log n), findMedian O(1), O(n) extra space for the two heaps.
 *
 * RELATED
 *   LeetCode 215 (KthLargestElement, Heap/): one heap capped at size k instead of two halves.
 *   LeetCode 4 (MedianOfTwoSortedArrays, BinarySearch/): the median of two fixed sorted arrays, found by binary search.
 */
public class MedianFromDataStream {

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(5);
        finder.addNum(15);
        System.out.println(finder.findMedian());
        finder.addNum(1);
        System.out.println(finder.findMedian());
        finder.addNum(3);
        System.out.println(finder.findMedian());
    }

    static class MedianFinder {
        private final PriorityQueue<Integer> low = new PriorityQueue<>(Collections.reverseOrder());
        private final PriorityQueue<Integer> high = new PriorityQueue<>();

        public void addNum(int num) {
            low.offer(num);
            high.offer(low.poll());
            if (high.size() > low.size()) {
                low.offer(high.poll());
            }
        }

        public double findMedian() {
            if (low.size() > high.size()) {
                return low.peek();
            }
            return (low.peek() + high.peek()) / 2.0;
        }
    }

}

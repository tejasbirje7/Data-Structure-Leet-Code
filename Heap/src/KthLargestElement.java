import java.util.PriorityQueue;

/**
 * LeetCode 215. Kth Largest Element in an Array
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * PROBLEM
 *   - Input: an integer array nums and an integer k (1 <= k <= n).
 *   - Return: the k-th largest element in sorted order (duplicates count, it is not the k-th distinct).
 *   - The problem asks: can you solve it without sorting?
 *
 * EXAMPLE
 *   Input: nums = [3,2,1,5,6,4], k = 2
 *   Output: 5
 *
 * HOW TO THINK
 *   - Clue: "k-th largest" -> only the top k values matter; keep exactly those in a heap of size k.
 *   - Technique: a MIN-heap capped at k. Its top is the smallest of the k largest, which is the answer.
 *   - Rule that stays true: after each element, the heap holds the k largest values seen so far.
 *   - Decision: offer x; heap size > k -> poll the smallest (it cannot be in the top k).
 *               otherwise             -> keep it.
 *   - Return: heap.peek().
 *
 * STEPS
 *   1. Min-heap.
 *   2. For each x in nums: offer x.
 *   3. If size > k, poll.
 *   4. Return peek.
 *
 * DRY RUN   input = nums = [3,2,1,5,6,4], k = 2   (heap is after the step, smallest first)
 *
 *    x | heap after offer | size > k? | action  | heap after
 *   ---+------------------+-----------+---------+------------
 *    3 | [3]              |  1 > 2 no | keep    | [3]
 *    2 | [2,3]            |  2 > 2 no | keep    | [2,3]
 *    1 | [1,2,3]          |  3 > 2 yes| poll 1  | [2,3]
 *    5 | [2,3,5]          |  3 > 2 yes| poll 2  | [3,5]
 *    6 | [3,5,6]          |  3 > 2 yes| poll 3  | [5,6]
 *    4 | [4,5,6]          |  3 > 2 yes| poll 4  | [5,6]
 *
 *   Returns 5 (heap top). Final heap = the 2 largest values [5,6].
 *
 * EDGE CASES
 *   [1], k = 1                     -> 1    single element
 *   [3,2,3,1,2,4,5,5,6], k = 4     -> 4    duplicates count: sorted desc 6,5,5,4
 *   [7,3,9], k = 3                 -> 3    k = n gives the minimum
 *   [-1,-1], k = 2                 -> -1   negatives and duplicates
 *
 * COMMON MISTAKES
 *   - Using a max-heap capped at k: it keeps the k SMALLEST values.
 *   - Removing duplicates first (e.g. a TreeSet): the k-th largest counts duplicates.
 *   - Off-by-one after sorting ascending: the answer is nums[n - k], not nums[k].
 *
 * COMPLEXITY
 *   O(n log k) time, O(k) extra space.
 *   (Quickselect gives O(n) average time, O(n^2) worst case.)
 *
 * RELATED
 *   LeetCode 347 (TopKFrequentElements, Heap/): the same size-k min-heap, ordered by counts.
 *   LeetCode 295 (MedianFromDataStream, Heap/): two heaps keep the middle of a stream instead of the top k.
 */
public class KthLargestElement {

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargest(nums, 2));
    }

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int x : nums) {
            heap.offer(x);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }

}

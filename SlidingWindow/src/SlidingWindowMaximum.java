import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode 239. Sliding Window Maximum
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * PROBLEM
 *   - Input: an int array nums and a window size k (1 <= k <= nums.length).
 *   - A window of k elements slides from the left end to the right end, one step at a time.
 *   - Return: the maximum of every window, in order (nums.length - k + 1 values).
 *
 * EXAMPLE
 *   Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 *   Output: [3,3,5,5,6,7]
 *   Explanation:
 *     Window position                Max
 *     ---------------               -----
 *     [1  3  -1] -3  5  3  6  7       3
 *      1 [3  -1  -3] 5  3  6  7       3
 *      1  3 [-1  -3  5] 3  6  7       5
 *      1  3  -1 [-3  5  3] 6  7       5
 *      1  3  -1  -3 [5  3  6] 7       6
 *      1  3  -1  -3  5 [3  6  7]      7
 *
 * HOW TO THINK
 *   - Clue: "maximum of every window" -> rescanning each window is O(n * k); we need the max in O(1) per step.
 *   - Technique: monotonic deque of INDEXES whose values go down from front to back.
 *     A smaller value to the left of a bigger new value can never be a maximum again, so throw it away.
 *   - Rule that stays true: the deque holds indexes inside the current window, values strictly decreasing,
 *     so the front is always the window's maximum.
 *   - Decision for each new index i:
 *     front index <= i - k -> it has left the window, poll it from the front;
 *     back value < nums[i] -> it can never win again, poll it from the back (repeat);
 *     then add i at the back. Once i >= k - 1, the front's value is this window's answer.
 *   - Return: the array of answers.
 *
 * STEPS
 *   1. deque = empty, result = new int[n - k + 1].
 *   2. For i = 0 .. n - 1:
 *   3.   If the front index <= i - k, poll the front.
 *   4.   While the back value < nums[i], poll the back. Then add i at the back.
 *   5.   If i >= k - 1: result[i - k + 1] = nums[front].
 *   6. Return result.
 *
 * DRY RUN   input = nums = [1,3,-1,-3,5,3,6,7], k = 3   (deque shows index(value), after the step)
 *
 *    i | nums[i] | front out of window?   | back values popped | deque after               | answer
 *   ---+---------+------------------------+--------------------+---------------------------+-------
 *    0 |    1    | empty                  | none               | 0(1)                      |  -
 *    1 |    3    | 0 > -2, stays          | 1                  | 1(3)                      |  -
 *    2 |   -1    | 1 > -1, stays          | none               | 1(3) 2(-1)                |  3
 *    3 |   -3    | 1 > 0, stays           | none               | 1(3) 2(-1) 3(-3)          |  3
 *    4 |    5    | 1 <= 1, poll 1(3)      | -3, -1             | 4(5)                      |  5
 *    5 |    3    | 4 > 2, stays           | none               | 4(5) 5(3)                 |  5
 *    6 |    6    | 4 > 3, stays           | 3, 5               | 6(6)                      |  6
 *    7 |    7    | 6 > 4, stays           | 6                  | 7(7)                      |  7
 *
 *   Returns [3,3,5,5,6,7].
 *
 * EDGE CASES
 *   [1], k = 1                 -> [1]           one window
 *   [1,-1], k = 1              -> [1,-1]        k = 1: every element is its own max
 *   [1,3,2], k = 3             -> [3]           k = n: one window, the whole array
 *   [5,4,3,2,1], k = 2         -> [5,4,3,2]     falling values: nothing is popped from the back; old fronts expire
 *   [7,7,7], k = 2             -> [7,7]         equal values are kept (pop only strictly smaller), still correct
 *
 * COMMON MISTAKES
 *   - Storing values instead of indexes: you can't tell when the front has left the window.
 *   - Checking the window with < instead of <= : index i - k is already outside the window [i - k + 1, i].
 *   - Writing the answer before the first full window (i < k - 1).
 *   - Using a max-heap without removing expired entries: wrong answers, or O(n log n) with lazy removal.
 *
 * COMPLEXITY
 *   O(n) time (each index is added once and removed at most once), O(k) extra space for the deque.
 *
 * RELATED
 *   LeetCode 209 (MinimumSizeSubArray, SlidingWindow/): a window again, but it grows and shrinks; no deque needed.
 *   LeetCode 3 (LongestSubstring, SlidingWindow/): variable window with a map instead of a fixed window with a deque.
 *   LeetCode 295 (MedianFromDataStream, Heap/): another "keep a running statistic" problem, solved with two heaps.
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

}

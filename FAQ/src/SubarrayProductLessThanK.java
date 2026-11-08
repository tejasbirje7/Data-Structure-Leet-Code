import java.util.*;

/**
 * LeetCode 713. Subarray Product Less Than K
 * https://leetcode.com/problems/subarray-product-less-than-k/
 *
 * PROBLEM
 *   - Input: an array nums of positive integers and an integer k.
 *   - Return: how many contiguous subarrays have a product strictly less than k.
 *   - Constraints: 1 <= nums[i] <= 1000, 0 <= k <= 10^6, n up to 3 * 10^4 (O(n^2) is too slow).
 *
 * EXAMPLE
 *   Input: nums = [10,5,2,6], k = 100
 *   Output: 8
 *   Explanation: Valid subarrays are [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6].
 *
 * HOW TO THINK
 *   - Clue: "contiguous subarrays" + all values positive = sliding window.
 *     Adding an element can only grow the product, removing one can only shrink it.
 *   - Technique: grow the window on the right one step at a time; while the product is too big,
 *     shrink it from the left by dividing out nums[l].
 *   - Rule that stays true: after shrinking, product = nums[l] * ... * nums[r] and it is < k
 *     (or the window is empty, l = r + 1, product = 1).
 *   - Decision: product >= k (and l <= r) -> divide by nums[l], l++.
 *               then every subarray ending at r and starting at l..r is valid: add r - l + 1.
 *   - Why r - l + 1: for [5,2,6] ending at 6, the valid subarrays are [6], [2,6], [5,2,6] = 3.
 *   - Return: count. Shortcut: k <= 1 -> 0, because every product is at least 1.
 *
 * STEPS
 *   1. If k <= 1, return 0.
 *   2. l = 0, product = 1, count = 0.
 *   3. For each r: product *= nums[r].
 *   4.   While product >= k and l <= r: product /= nums[l], l++.
 *   5.   count += r - l + 1.
 *   6. Return count.
 *
 * DRY RUN   nums = [10,5,2,6], k = 100   (product, l and count are values after the step)
 *
 *    r | nums[r] | product before shrink | action           | product | l | added | count
 *   ---+---------+-----------------------+------------------+---------+---+-------+-------
 *    0 |   10    |          10           | keep             |   10    | 0 |   1   |   1
 *    1 |    5    |          50           | keep             |   50    | 0 |   2   |   3
 *    2 |    2    |         100           | shrink, drop 10  |   10    | 1 |   2   |   5
 *    3 |    6    |          60           | keep             |   60    | 1 |   3   |   8
 *
 *   Returns 8. Final window [5,2,6], product 60.
 *
 * EDGE CASES
 *   [1,2,3], k = 0     -> 0   k <= 1 returns at once
 *   [1,1,1], k = 1     -> 0   product is never < 1
 *   [1,1,1], k = 2     -> 6   every subarray is valid: 1 + 2 + 3
 *   [5], k = 5         -> 0   window shrinks to empty (l = r + 1), adds 0
 *   [100,1], k = 50    -> 1   100 is dropped right away; only [1] counts
 *   [], k = 10         -> 0   the loop never runs; LeetCode guarantees n >= 1
 *
 * COMMON MISTAKES
 *   - Dropping both the k <= 1 check and the l <= r guard: with k <= 1 even the empty window's
 *     product (1) is >= k, so l runs past r and can read past the end of the array.
 *   - Adding 1 per valid window instead of r - l + 1: that counts windows, not subarrays.
 *   - Using "product > k" to shrink: the product must be strictly less than k.
 *   - Trying this with zeros or negatives: the window logic needs positive values.
 *
 * COMPLEXITY
 *   O(n) time (each element enters and leaves the window at most once), O(1) extra space.
 *   No overflow: product < k <= 10^6 before multiplying by at most 1000.
 *
 * RELATED
 *   LeetCode 209 (MinimumSizeSubArray, SlidingWindow/): same grow-right / shrink-left window
 *     on positive numbers, but it looks for the shortest window with sum >= target.
 *   LeetCode 3 (LongestSubstring, SlidingWindow/): window shrinks when a character repeats.
 */
public class SubarrayProductLessThanK {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;

        int l = 0;
        int product = 1;
        int count = 0;

        for (int r = 0; r < nums.length; r++) {
            product *= nums[r];

            while (product >= k && l <= r) {
                product /= nums[l];
                l++;
            }

            count += (r - l + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        SubarrayProductLessThanK solver = new SubarrayProductLessThanK();

        System.out.println(solver.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100)); // 8
        System.out.println(solver.numSubarrayProductLessThanK(new int[]{1, 2, 3}, 0));       // 0
    }
}

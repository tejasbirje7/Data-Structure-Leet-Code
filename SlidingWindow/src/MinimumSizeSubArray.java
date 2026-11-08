/**
 * LeetCode 209. Minimum Size Subarray Sum
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 *
 * PROBLEM
 *   - Input: an array of positive integers nums and a positive integer target.
 *   - Return: the length of the shortest contiguous subarray whose sum is >= target.
 *   - Return 0 if no subarray reaches target.
 *   - All numbers are positive: adding one grows the sum, dropping one shrinks it.
 *     That is what makes the sliding window work.
 *
 * EXAMPLE
 *   Input: target = 7, nums = [2,3,1,2,4,3]
 *   Output: 2
 *   Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 *
 *   Input: target = 4, nums = [1,4,4]
 *   Output: 1
 *
 *   Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 *   Output: 0
 *
 * HOW TO THINK
 *   - Clue: "subarray" (contiguous) + "sum >= target" + all positive -> sliding window.
 *   - Technique: variable-size window. r adds the next number; while the sum is big enough,
 *     record the length and drop from the left to try a shorter window.
 *   - Rule that stays true: total = sum of nums[l..r].
 *   - Decision: total >= target -> record r - l + 1, subtract nums[l], l++ (repeat while still >= target).
 *               total < target  -> stop shrinking, move r on.
 *   - Return: result, or 0 if result is still Integer.MAX_VALUE (no window ever reached target).
 *
 * STEPS
 *   1. l = 0, total = 0, result = Integer.MAX_VALUE.
 *   2. Loop r from 0 to n - 1: total += nums[r].
 *   3. While total >= target: result = min(result, r - l + 1); total -= nums[l]; l++.
 *   4. Return result == Integer.MAX_VALUE ? 0 : result.
 *
 * DRY RUN   target = 7, nums = [2,3,1,2,4,3]
 *           (one row per add or drop; window and length are measured before the drop,
 *            total, l and result are after the step)
 *
 *    r | action           | window [l..r] | length | total | l | result
 *   ---+------------------+---------------+--------+-------+---+--------
 *    0 | add 2            | [2]           |   -    |   2   | 0 |  MAX
 *    1 | add 3            | [2,3]         |   -    |   5   | 0 |  MAX
 *    2 | add 1            | [2,3,1]       |   -    |   6   | 0 |  MAX
 *    3 | add 2            | [2,3,1,2]     |   -    |   8   | 0 |  MAX
 *    3 | 8 >= 7, drop 2   | [2,3,1,2]     |   4    |   6   | 1 |   4
 *    4 | add 4            | [3,1,2,4]     |   -    |  10   | 1 |   4
 *    4 | 10 >= 7, drop 3  | [3,1,2,4]     |   4    |   7   | 2 |   4
 *    4 | 7 >= 7, drop 1   | [1,2,4]       |   3    |   6   | 3 |   3
 *    5 | add 3            | [2,4,3]       |   -    |   9   | 3 |   3
 *    5 | 9 >= 7, drop 2   | [2,4,3]       |   3    |   7   | 4 |   3
 *    5 | 7 >= 7, drop 4   | [4,3]         |   2    |   3   | 5 |   2
 *
 *   Returns 2: the subarray [4,3].
 *
 * EDGE CASES
 *   target = 4, [1,4,4]                -> 1   a single element reaches target
 *   target = 11, [1,1,1,1,1,1,1,1]     -> 0   total never reaches 11, result stays MAX_VALUE
 *   target = 15, [1,2,3,4,5]           -> 5   only the whole array works
 *   target = 3, [5]                    -> 1   one element, already big enough
 *   target = 7, []                     -> 0   the loop never runs (LeetCode guarantees at least one element)
 *
 * COMMON MISTAKES
 *   - Shrinking with if instead of while: one drop may not be enough.
 *     [2,3,1,2,4,3], target = 7 would give 4 instead of 2.
 *   - Forgetting the "no answer" case: returning Integer.MAX_VALUE instead of 0.
 *   - Length off by one: nums[l..r] has r - l + 1 elements.
 *   - Using this on arrays with negative numbers: dropping a value may raise the sum, so the window breaks.
 *
 * COMPLEXITY
 *   O(n) time (each element is added once and dropped at most once), O(1) extra space.
 *
 * RELATED
 *   LeetCode 713 (SubarrayProductLessThanK, FAQ/): same grow/shrink window on a product;
 *     counts the valid windows instead of finding the shortest.
 *   LeetCode 76 (MinimumWindowSubstring, SlidingWindow/): same "shrink while valid, record the shortest" loop,
 *     but valid means "has all the chars of t".
 */
public class MinimumSizeSubArray {

    public static void main(String[] args) {
        int target = 7;
        int[] nums = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(target,nums));
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int l = 0;
        int total = 0;
        int result = Integer.MAX_VALUE;

        for (int r = 0; r < nums.length; r++) {
            total = total + nums[r];
            while (total >= target) {
                result = Math.min(r - l + 1,result);
                total = total - nums[l];
                l++;
            }
        }

        return result == Integer.MAX_VALUE ? 0 :result;
    }
}

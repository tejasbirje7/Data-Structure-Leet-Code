/**
 * LeetCode 53. Maximum Subarray
 * https://leetcode.com/problems/maximum-subarray/
 *
 * PROBLEM
 *   - Input: an integer array nums (may contain negatives), length >= 1.
 *   - Return: the largest sum of any non-empty contiguous subarray.
 *
 * EXAMPLE
 *   Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 *   Output: 6
 *   Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 *
 * HOW TO THINK
 *   - Clue: "contiguous subarray" + "largest sum" -> look at the best subarray ENDING at each index (Kadane).
 *   - Technique: 1-D DP squeezed into one variable.
 *     The best subarray ending at i either extends the one ending at i-1, or starts fresh at i.
 *   - Rule that stays true: cur = best sum of a subarray that ends exactly at i; best = best sum seen anywhere so far.
 *   - Decision: cur + x > x  -> extend (the running sum still helps).
 *               otherwise    -> restart at x (a negative running sum only drags x down).
 *   - Return: best, not cur (the best subarray may end before the last index).
 *
 * STEPS
 *   1. cur = best = nums[0].
 *   2. Loop i from 1 to n-1, with x = nums[i].
 *   3. cur = max(x, cur + x); best = max(best, cur).
 *   4. Return best.
 *
 * DRY RUN   input = [-2,1,-3,4,-1,2,1,-5,4]   (cur and best are after the step)
 *
 *    i |  x | cur + x | action  | cur | best
 *   ---+----+---------+---------+-----+------
 *    0 | -2 |    -    | init    |  -2 |  -2
 *    1 |  1 |   -1    | restart |   1 |   1
 *    2 | -3 |   -2    | extend  |  -2 |   1
 *    3 |  4 |    2    | restart |   4 |   4
 *    4 | -1 |    3    | extend  |   3 |   4
 *    5 |  2 |    5    | extend  |   5 |   5
 *    6 |  1 |    6    | extend  |   6 |   6
 *    7 | -5 |    1    | extend  |   1 |   6
 *    8 |  4 |    5    | extend  |   5 |   6
 *
 *   Returns 6: the subarray [4,-1,2,1] (indexes 3..6).
 *
 * EDGE CASES
 *   [5]            -> 5    single element
 *   [-3,-1,-2]     -> -1   all negative: the answer is the largest single element
 *   [5,4,-1,7,8]   -> 23   the whole array; the -1 is worth keeping
 *   [0,0]          -> 0    zeros never change the sum
 *
 * COMMON MISTAKES
 *   - Starting best at 0: all-negative arrays would wrongly return 0. Start at nums[0].
 *   - Returning cur at the end: that is only the best subarray ending at the last index.
 *   - Restarting when cur + x is negative instead of when cur is negative: compare cur + x with x.
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 152 (MaximumProductSubarray, DynamicProgramming/): product instead of sum, so also track the minimum.
 *   LeetCode 121 (MaxProfit, ArraysAndStrings/): same "best ending here" idea on day-to-day price changes.
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        int cur = nums[0];
        int best = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(nums[i], cur + nums[i]);
            best = Math.max(best, cur);
        }
        return best;
    }

}

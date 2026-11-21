/**
 * LeetCode 152. Maximum Product Subarray
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * PROBLEM
 *   - Input: an integer array nums (may contain negatives and zeros), length >= 1.
 *   - Return: the largest product of any non-empty contiguous subarray.
 *   - The answer fits in a 32-bit int.
 *
 * EXAMPLE
 *   Input: nums = [2,3,-2,4]
 *   Output: 6
 *   Explanation: [2,3] has the largest product 6.
 *
 * HOW TO THINK
 *   - Clue: "contiguous subarray" + "largest" -> Kadane, like Maximum Subarray.
 *     But a negative number flips the sign: the smallest product can become the largest.
 *   - Technique: track BOTH the max and the min product of a subarray ending at i.
 *   - Rule that stays true: maxEnd / minEnd = largest / smallest product of a subarray ending exactly at i;
 *     best = largest product seen anywhere so far.
 *   - Decision: x < 0  -> swap maxEnd and minEnd first (multiplying by x flips which is bigger).
 *               then maxEnd = max(x, maxEnd * x), minEnd = min(x, minEnd * x)
 *               (taking x alone means restarting, which also handles zeros).
 *   - Return: best.
 *
 * STEPS
 *   1. maxEnd = minEnd = best = nums[0].
 *   2. Loop i from 1 to n-1, with x = nums[i].
 *   3. If x < 0, swap maxEnd and minEnd. Then maxEnd = max(x, maxEnd * x); minEnd = min(x, minEnd * x).
 *   4. best = max(best, maxEnd); return best at the end.
 *
 * DRY RUN   input = [2,3,-2,4,-1]   (values are after the step)
 *
 *    i |  x | swap?               | maxEnd = max(x, maxEnd*x) | minEnd = min(x, minEnd*x) | best
 *   ---+----+---------------------+---------------------------+---------------------------+------
 *    0 |  2 | init                |             2             |             2             |   2
 *    1 |  3 | no                  |     max(3, 6)   =  6      |     min(3, 6)   =   3     |   6
 *    2 | -2 | yes -> (3, 6)       |     max(-2, -6) = -2      |     min(-2,-12) = -12     |   6
 *    3 |  4 | no                  |     max(4, -8)  =  4      |     min(4,-48)  = -48     |   6
 *    4 | -1 | yes -> (-48, 4)     |     max(-1, 48) = 48      |     min(-1, -4) =  -4     |  48
 *
 *   Returns 48: the whole array, 2 * 3 * -2 * 4 * -1. The second negative turns minEnd -48 into 48.
 *
 * EDGE CASES
 *   [-2]          -> -2   single negative element
 *   [-2,0,-1]     -> 0    zero breaks the array; 0 beats both negatives
 *   [-2,3,-4]     -> 24   two negatives cancel out
 *   [0,2]         -> 2    restart after zero: max(2, 0 * 2)
 *
 * COMMON MISTAKES
 *   - Tracking only the max: a negative times the smallest (most negative) product is lost.
 *   - Updating maxEnd, then using the NEW maxEnd to compute minEnd: swap first, or save the old value.
 *   - Starting best at 0: [-2] would wrongly return 0. Start at nums[0].
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 53 (MaximumSubarray, DynamicProgramming/): sum instead of product, so only the max is needed.
 *   LeetCode 238 (ProductExceptSelf, ArraysAndStrings/): another running-product problem.
 */
public class MaximumProductSubarray {

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4, -1};
        System.out.println(maxProduct(nums));
    }

    public static int maxProduct(int[] nums) {
        int maxEnd = nums[0];
        int minEnd = nums[0];
        int best = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int x = nums[i];
            if (x < 0) {
                int temp = maxEnd;
                maxEnd = minEnd;
                minEnd = temp;
            }
            maxEnd = Math.max(x, maxEnd * x);
            minEnd = Math.min(x, minEnd * x);
            best = Math.max(best, maxEnd);
        }
        return best;
    }

}

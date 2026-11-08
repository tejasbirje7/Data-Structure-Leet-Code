import java.util.Arrays;

/**
 * LeetCode 238. Product of Array Except Self
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * PROBLEM
 *   - Input: an integer array nums.
 *   - Return: answer where answer[i] = product of every value in nums except nums[i].
 *   - Constraints: O(n) time and no division.
 *   - Follow-up: O(1) extra space (the output array doesn't count).
 *
 * EXAMPLE
 *   Input: nums = [1,2,3,4]
 *   Output: [24,12,8,6]
 *
 * HOW TO THINK
 *   - Clue: "except self" = (product of everything LEFT of i) * (product of everything RIGHT of i).
 *     "No division" rules out "total product / nums[i]" (it also breaks on zeros).
 *   - Technique: prefix and suffix products, two passes over the same output array.
 *     Pass 1 (left to right) writes the left product; pass 2 (right to left) multiplies in the right product.
 *   - Rule that stays true: pass 1: prefix = product of nums[0 .. i-1] when you write output[i].
 *                           pass 2: postFix = product of nums[i+1 .. n-1] when you update output[i].
 *   - Decision: no branches. Write first, then multiply the running product by nums[i],
 *     so nums[i] itself is never included in output[i].
 *   - Return: the output array.
 *
 * STEPS
 *   1. prefix = 1. For i = 0 .. n-1: output[i] = prefix, then prefix *= nums[i].
 *   2. postFix = 1. For i = n-1 .. 0: output[i] *= postFix, then postFix *= nums[i].
 *   3. Return output.
 *
 * DRY RUN   nums = [1,2,3,4]
 *
 *    pass  | i | nums[i] | running product before | output[i] after | running product after
 *   -------+---+---------+------------------------+-----------------+-----------------------
 *    left  | 0 |    1    | prefix  = 1            |  1              | prefix  = 1
 *    left  | 1 |    2    | prefix  = 1            |  1              | prefix  = 2
 *    left  | 2 |    3    | prefix  = 2            |  2              | prefix  = 6
 *    left  | 3 |    4    | prefix  = 6            |  6              | prefix  = 24
 *    right | 3 |    4    | postFix = 1            |  6 * 1  = 6     | postFix = 4
 *    right | 2 |    3    | postFix = 4            |  2 * 4  = 8     | postFix = 12
 *    right | 1 |    2    | postFix = 12           |  1 * 12 = 12    | postFix = 24
 *    right | 0 |    1    | postFix = 24           |  1 * 24 = 24    | postFix = 24
 *
 *   After pass 1: output = [1,1,2,6]. Returns [24,12,8,6].
 *
 * EDGE CASES
 *   [2,3]            -> [3,2]          smallest LeetCode input
 *   [-1,1,0,-3,3]    -> [0,0,9,0,0]    one zero: only the zero's own slot is non-zero
 *   [0,0,5]          -> [0,0,0]        two zeros: every product contains a zero
 *   [5]              -> [1]            empty product is 1 (LeetCode guarantees at least 2 values)
 *   []               -> []             both loops are skipped
 *
 * COMMON MISTAKES
 *   - Multiplying before writing (prefix *= nums[i]; output[i] = prefix): includes nums[i] itself.
 *     Write first, then update the running product.
 *   - Dividing the total product by nums[i]: not allowed, and divides by zero when nums has a 0.
 *   - Using separate left[] and right[] arrays: correct, but O(n) extra space; reuse output instead.
 *   - Starting prefix or postFix at 0 instead of 1: every product becomes 0.
 *
 * COMPLEXITY
 *   O(n) time (two passes), O(1) extra space besides the output array.
 *
 * RELATED
 *   LeetCode 42 (RainWater, ArraysAndStrings/): also combines "best from the left" and "best from the right"
 *     at each index, but with max heights instead of products.
 *   LeetCode 135 (Candy, ArraysAndStrings/): same left-to-right pass then right-to-left pass shape.
 */
public class ProductExceptSelf {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }

    public static int[] productExceptSelf(int[] nums) {
        int prefix = 1;
        int[] output = new int[nums.length];
        System.out.println(Arrays.toString(nums));
        for (int i=0; i < nums.length; i++) {
            output[i] = prefix;
            prefix = prefix * nums[i];
        }
        System.out.println(Arrays.toString(output));
        int postFix = 1;
        for (int i=nums.length-1; i >= 0; i--) {
            output[i] = postFix * output[i];
            postFix = postFix * nums[i];
        }
        return output;
    }
}

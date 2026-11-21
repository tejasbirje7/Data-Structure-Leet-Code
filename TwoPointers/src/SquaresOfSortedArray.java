import java.util.Arrays;

/**
 * LeetCode 977. Squares of a Sorted Array
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 *
 * PROBLEM
 *   - Input: an int array nums sorted in non-decreasing order (may contain negatives).
 *   - Return: a new array of the squares of each number, also sorted in non-decreasing order.
 *   - Follow-up: O(n) instead of squaring and sorting (O(n log n)).
 *
 * EXAMPLE
 *   Input: nums = [-4,-1,0,3,10]
 *   Output: [0,1,9,16,100]
 *   Explanation: After squaring, the array becomes [16,1,0,9,100].
 *   After sorting, it becomes [0,1,9,16,100].
 *
 * HOW TO THINK
 *   - Clue: "sorted" + negatives: the biggest squares are at the two ENDS
 *     (most negative on the left, most positive on the right), the smallest in the middle.
 *   - Technique: two pointers at both ends; the bigger absolute value has the bigger square,
 *     so it goes into the result from the back.
 *   - Rule that stays true: result[p+1..n-1] holds the largest squares, sorted;
 *     nums[left..right] are the numbers not placed yet.
 *   - Decision at each p (from n - 1 down to 0):
 *       |nums[left]| > |nums[right]| -> result[p] = nums[left]^2, left++.
 *       else                         -> result[p] = nums[right]^2, right--.
 *   - Return: result.
 *
 * STEPS
 *   1. result = new int[n]; left = 0, right = n - 1.
 *   2. For p = n - 1 down to 0:
 *   3.   if |nums[left]| > |nums[right]|: result[p] = nums[left]^2, left++.
 *   4.   else: result[p] = nums[right]^2, right--.
 *   5. Return result.
 *
 * DRY RUN   nums = [-4,-1,0,3,10]   (result and pointers are after the step)
 *
 *    p | nums[left] | nums[right] | compare        | write       | result             | left,right after
 *   ---+------------+-------------+----------------+-------------+--------------------+-----------------
 *    4 |    -4      |     10      | 4 <= 10, right | 100         | [0,0,0,0,100]      |   0, 3
 *    3 |    -4      |      3      | 4 > 3, left    | 16          | [0,0,0,16,100]     |   1, 3
 *    2 |    -1      |      3      | 1 <= 3, right  | 9           | [0,0,9,16,100]     |   1, 2
 *    1 |    -1      |      0      | 1 > 0, left    | 1           | [0,1,9,16,100]     |   2, 2
 *    0 |     0      |      0      | 0 <= 0, right  | 0           | [0,1,9,16,100]     |   2, 1
 *
 *   Returns [0,1,9,16,100].
 *
 * EDGE CASES
 *   [-7,-3,2,3,11]   -> [4,9,9,49,121]   LeetCode example 2, equal squares (9, 9)
 *   [5]              -> [25]             one element
 *   [-3,-2,-1]       -> [1,4,9]          all negative: every pick comes from the left
 *   [1,2,3]          -> [1,4,9]          all positive: every pick comes from the right
 *   [-10000,10000]   -> [10^8,10^8]      largest values still fit in an int
 *
 * COMMON MISTAKES
 *   - Filling result from the front: the smallest square is in the middle, not at an end.
 *   - Writing into nums itself: you overwrite values the pointers still need; use a new array.
 *   - Comparing nums[left] > nums[right] without abs (or squares): wrong for negatives.
 *
 * COMPLEXITY
 *   O(n) time (each number is placed once), O(1) extra space besides the output array.
 *
 * RELATED
 *   LeetCode 88 (Merge, ArraysAndStrings/): also fills from the back with the larger of two candidates.
 *   LeetCode 11 (MostWaterInContainer, TwoPointers/): two pointers from both ends that move inwards.
 */
public class SquaresOfSortedArray {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortedSquares(new int[]{-4, -1, 0, 3, 10})));
    }

    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;
        for (int p = n - 1; p >= 0; p--) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                result[p] = nums[left] * nums[left];
                left++;
            } else {
                result[p] = nums[right] * nums[right];
                right--;
            }
        }
        return result;
    }
}

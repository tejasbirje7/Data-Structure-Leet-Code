/**
 * LeetCode 268. Missing Number
 * https://leetcode.com/problems/missing-number/
 *
 * PROBLEM
 *   - Input: nums with n distinct numbers, all taken from the range [0, n].
 *   - Return: the one number in [0, n] that is not in nums.
 *   - Follow-up: O(1) extra space and O(n) time.
 *
 * EXAMPLE
 *   Input: nums = [3,0,1]
 *   Output: 2
 *   Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3].
 *   2 is the missing number in the range since it does not appear in nums.
 *
 * HOW TO THINK
 *   - Clue: "range [0, n]" + "exactly one missing" = missing = sum(0..n) - sum(nums).
 *   - Technique: one running total instead of two separate sums.
 *     The indexes 0..n-1 give every number in the range except n, so start the total at n.
 *     Then add each index i and subtract each value nums[i].
 *   - Rule that stays true: after step i, result = n + (0 + 1 + ... + i) - (nums[0] + ... + nums[i]).
 *   - Decision: none; every step does result += i - nums[i].
 *   - Why it works: at the end result = (0 + 1 + ... + n) - sum(nums) = the missing number.
 *     Adding and subtracting as you go keeps the total small (no overflow from big sums).
 *   - Return: result.
 *
 * STEPS
 *   1. result = n (= nums.length).
 *   2. For each index i: result = result + (i - nums[i]).
 *   3. Return result.
 *
 * DRY RUN   nums = [3,0,1]   (n = 3; result is the value after the step)
 *
 *    i | nums[i] | i - nums[i] | result
 *   ---+---------+-------------+--------
 *    - |    -    |      -      |   3     start at n
 *    0 |    3    |     -3      |   0
 *    1 |    0    |      1      |   1
 *    2 |    1    |      1      |   2
 *
 *   Returns 2.
 *
 * EDGE CASES
 *   [0]                     -> 1   the missing number is n itself
 *   [1]                     -> 0   the missing number is 0
 *   [0,1]                   -> 2   missing is n; the "start at n" handles it
 *   [9,6,4,2,3,5,7,0,1]     -> 8   order does not matter
 *   []                      -> 0   loop never runs; LeetCode guarantees n >= 1
 *
 * COMMON MISTAKES
 *   - Starting result at 0: n is never added, so [0,1] would give 0 instead of 2.
 *   - Sorting and scanning for a gap: works, but O(n log n).
 *   - A HashSet or boolean array: works, but O(n) extra space.
 *   - n * (n + 1) / 2 can overflow int for very large n; the running total avoids that.
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 136 Single Number (not in this repo): XOR every value; pairs cancel and the odd one is left.
 *     The same XOR trick also solves this problem (XOR all indexes, values and n).
 *   LeetCode 41 First Missing Positive (not in this repo): values are not limited to [0, n],
 *     so it needs in-place swapping instead of a sum.
 */
public class MissingNumberFinder {

    public int missingNumber(int[] nums) {
        int result = nums.length;

        for (int i = 0; i < nums.length; i++) {
            result = result + (i - nums[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        MissingNumberFinder finder = new MissingNumberFinder();

        int[] nums1 = {3, 0, 1};
        System.out.println("Missing number in [3, 0, 1] is: " + finder.missingNumber(nums1));

        int[] nums2 = {0, 1};
        System.out.println("Missing number in [0, 1] is: " + finder.missingNumber(nums2));

        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println("Missing number in [9, 6, 4, 2, 3, 5, 7, 0, 1] is: " + finder.missingNumber(nums3));
    }
}

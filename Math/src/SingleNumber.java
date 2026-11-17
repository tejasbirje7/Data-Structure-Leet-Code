/**
 * LeetCode 136. Single Number
 * https://leetcode.com/problems/single-number/
 *
 * PROBLEM
 *   - Input: a non-empty integer array where every value appears twice, except one value that appears once.
 *   - Return: the value that appears once.
 *   - Constraint: O(n) time and O(1) extra space.
 *
 * EXAMPLE
 *   Input: nums = [2,2,1]
 *   Output: 1
 *
 *   Input: nums = [4,1,2,1,2]
 *   Output: 4
 *
 *   Input: nums = [1]
 *   Output: 1
 *
 * HOW TO THINK
 *   - Clue: "every value appears twice except one" + "O(1) extra space": no set or map allowed,
 *     so look for an operation where a pair cancels out.
 *   - Technique: XOR (^). Three rules make it work:
 *     a ^ a = 0        (a pair cancels)
 *     a ^ 0 = a        (0 changes nothing)
 *     order doesn't matter (a ^ b ^ a = a ^ a ^ b = b)
 *   - So XOR of the whole array = XOR of all pairs (each 0) ^ the single value = the single value.
 *   - Rule that stays true: result = XOR of nums[0..i]. That is the XOR of every value
 *     seen an odd number of times so far.
 *   - Decision: none. XOR every value into result, no if needed.
 *   - Return: result after the loop.
 *
 * STEPS
 *   1. result = 0.
 *   2. Loop over every num in nums.
 *   3. result = result ^ num.
 *   4. Return result.
 *
 * DRY RUN   nums = [4,1,2,1,2]   (result in decimal and binary, after the step)
 *
 *    i | nums[i] | binary | result ^ nums[i]   | result | values seen an odd number of times
 *   ---+---------+--------+--------------------+--------+------------------------------------
 *    - |    -    |   -    | start              | 0 (000)| none
 *    0 |    4    |  100   | 000 ^ 100 = 100    | 4      | 4
 *    1 |    1    |  001   | 100 ^ 001 = 101    | 5      | 4, 1
 *    2 |    2    |  010   | 101 ^ 010 = 111    | 7      | 4, 1, 2
 *    3 |    1    |  001   | 111 ^ 001 = 110    | 6      | 4, 2       (the 1s cancel)
 *    4 |    2    |  010   | 110 ^ 010 = 100    | 4      | 4          (the 2s cancel)
 *
 *   Returns 4. The middle results (5, 7, 6) mean nothing by themselves; only the final value counts.
 *
 * EDGE CASES
 *   [1]             -> 1    one value: 0 ^ 1 = 1
 *   [2,2,1]         -> 1    the single value is last
 *   [-1,5,5]        -> -1   negative values work: XOR works bit by bit, sign bit included
 *   [0,3,3]         -> 0    the single value can be 0
 *   [7,9,7]         -> 9    the pair doesn't have to be next to each other
 *
 * COMMON MISTAKES
 *   - Starting result at 1 instead of 0: 0 is the "changes nothing" value for XOR.
 *   - Using a HashSet or HashMap of counts: correct, but O(n) extra space breaks the constraint.
 *   - Sorting and checking neighbours: O(n log n), and changes the input.
 *   - Using this when a value appears three times (LeetCode 137): three copies XOR to the value
 *     itself, not 0, so the trick breaks.
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 268 (MissingNumberFinder, FAQ/): XOR all indexes 0..n with all values;
 *     every number present cancels and the missing one is left.
 *   LeetCode 137 Single Number II (not in this repo): every other value appears three times;
 *     count each bit position modulo 3 instead of XOR.
 *   LeetCode 260 Single Number III (not in this repo): two values appear once; XOR everything,
 *     then split the values into two groups by one bit where the two singles differ.
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = {4,1,2,1,2};
        System.out.println(singleNumber(nums));
    }

    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result = result ^ num;
        }
        return result;
    }
}

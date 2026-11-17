/**
 * LeetCode 137. Single Number II
 * https://leetcode.com/problems/single-number-ii/
 *
 * PROBLEM
 *   - Input: an integer array where every value appears three times, except one value that appears once.
 *   - Return: the value that appears once.
 *   - Constraint: O(n) time and O(1) extra space.
 *
 * EXAMPLE
 *   Input: nums = [2,2,3,2]
 *   Output: 3
 *
 *   Input: nums = [0,1,0,1,0,1,99]
 *   Output: 99
 *
 * HOW TO THINK
 *   - Clue: same as LeetCode 136 (O(1) space, everything repeats except one), but values come in threes.
 *   - Why XOR fails: a ^ a ^ a = a, not 0, so the triples don't cancel.
 *   - Technique: look at one bit position at a time.
 *     Every value that appears three times adds 0 or 3 to that bit's count.
 *     So count % 3 is exactly that bit of the single value (0 or 1).
 *   - Rule that stays true: after handling bit b, result holds the correct bits 0..b of the single value.
 *   - Decision: count % 3 != 0 -> set bit b in result.
 *               count % 3 == 0 -> leave bit b as 0.
 *   - Do all 32 bits, including bit 31 (the sign bit), so negative answers come out right.
 *   - Return: result after all 32 bits.
 *
 * STEPS
 *   1. result = 0.
 *   2. Loop bit from 0 to 31.
 *   3. count = how many nums have this bit set ((num >> bit) & 1).
 *   4. If count % 3 != 0, set the bit: result |= (1 << bit).
 *   5. Return result.
 *
 * DRY RUN   nums = [2,2,3,2]   (2 = 10, 3 = 11 in binary; result is after the step)
 *
 *    bit      | bit of 2, 2, 3, 2 | count | count % 3 | action    | result
 *   ----------+-------------------+-------+-----------+-----------+-------------
 *    0        | 0, 0, 1, 0        |   1   |     1     | set bit 0 | 01 = 1
 *    1        | 1, 1, 1, 1        |   4   |     1     | set bit 1 | 11 = 3
 *    2 .. 31  | 0, 0, 0, 0        |   0   |     0     | nothing   | 11 = 3
 *
 *   Returns 3. Bit 1: the three 2s give 3, the single 3 gives 1 more, so 4 % 3 = 1.
 *
 * EDGE CASES
 *   [1]                           -> 1     one value: every set bit has count 1
 *   [0,1,0,1,0,1,99]              -> 99    the single value is last
 *   [-2,-2,1,-2]                  -> 1     the triple is negative: bit 31 count = 3, 3 % 3 = 0
 *   [-2,-2,-2,-5]                 -> -5    the single value is negative: bit 31 count = 4, so it gets set
 *   [30000,500,100,30000,100,30000,100] -> 500
 *
 * COMMON MISTAKES
 *   - Using XOR like LeetCode 136: the triples don't cancel, so the answer is wrong.
 *   - Looping only bits 0..30: the sign bit is never set, so negative answers come out positive.
 *   - A HashMap of counts: correct, but O(n) extra space breaks the constraint.
 *   - Writing 1 << bit as a long or with Math.pow: use the int shift; 1 << 31 is exactly the sign bit.
 *
 * COMPLEXITY
 *   O(32 * n) = O(n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 136 (SingleNumber, Math/): values come in pairs, so plain XOR is enough.
 *   LeetCode 260 (SingleNumber3, Math/): values come in pairs, but two values appear once.
 */
public class SingleNumber2 {

    public static void main(String[] args) {
        int[] nums = {2,2,3,2};
        System.out.println(singleNumber(nums));
    }

    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int bit = 0; bit < 32; bit++) {
            int count = 0;
            for (int num : nums) {
                count += (num >> bit) & 1;
            }
            if (count % 3 != 0) {
                result |= (1 << bit);
            }
        }
        return result;
    }
}

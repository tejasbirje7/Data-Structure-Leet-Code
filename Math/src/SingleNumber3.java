import java.util.Arrays;

/**
 * LeetCode 260. Single Number III
 * https://leetcode.com/problems/single-number-iii/
 *
 * PROBLEM
 *   - Input: an integer array where exactly two values appear once, and every other value appears twice.
 *   - Return: the two values that appear once, in any order.
 *   - Constraint: O(n) time and O(1) extra space.
 *
 * EXAMPLE
 *   Input: nums = [1,2,1,3,2,5]
 *   Output: [3,5]
 *   Explanation: [5, 3] is also a valid answer.
 *
 *   Input: nums = [-1,0]
 *   Output: [-1,0]
 *
 *   Input: nums = [0,1]
 *   Output: [1,0]
 *
 * HOW TO THINK
 *   - Clue: pairs + O(1) space = XOR, like LeetCode 136. But XOR of everything gives a ^ b, not a and b.
 *   - Key fact: a != b, so a ^ b has at least one 1-bit. At that bit, a has 1 and b has 0 (or the other way).
 *   - Technique: pick that bit and split all values into two groups by it.
 *     a and b land in different groups.
 *     Both copies of a pair have the same bits, so a pair always lands in the same group.
 *     Now each group is LeetCode 136: XOR the group and the pairs cancel.
 *   - Which bit: the lowest 1-bit, diff & -diff (it keeps only the lowest 1 of diff).
 *   - Rule that stays true: groupA = XOR of the values seen so far without the bit,
 *     groupB = XOR of the values seen so far with the bit.
 *   - Decision: (num & lowBit) == 0 -> XOR into groupA.
 *               otherwise           -> XOR into groupB.
 *   - Return: [groupA, groupB].
 *
 * STEPS
 *   1. diff = XOR of every value (= a ^ b).
 *   2. lowBit = diff & -diff.
 *   3. groupA = 0, groupB = 0.
 *   4. Loop over nums: if (num & lowBit) == 0, groupA ^= num; else groupB ^= num.
 *   5. Return [groupA, groupB].
 *
 * DRY RUN   nums = [1,2,1,3,2,5]
 *
 *   First pass: diff = 1 ^ 2 ^ 1 ^ 3 ^ 2 ^ 5 = 3 ^ 5 = 011 ^ 101 = 110 (6).
 *   lowBit = 6 & -6 = 010 (2). So 3 (011) and 5 (101) differ at bit 1.
 *
 *    i | num | binary | num & 010 | group | groupA | groupB   (after the step)
 *   ---+-----+--------+-----------+-------+--------+--------
 *    0 |  1  |  001   |     0     |   A   |   1    |   0
 *    1 |  2  |  010   |     2     |   B   |   1    |   2
 *    2 |  1  |  001   |     0     |   A   |   0    |   2      (the 1s cancel)
 *    3 |  3  |  011   |     2     |   B   |   0    |   1
 *    4 |  2  |  010   |     2     |   B   |   0    |   3      (the 2s cancel)
 *    5 |  5  |  101   |     0     |   A   |   5    |   3
 *
 *   Returns [5,3]. Group A held 1, 1, 5; group B held 2, 3, 2.
 *
 * EDGE CASES
 *   [-1,0]                      -> [0,-1]   no pairs at all; diff = -1, lowBit = 1
 *   [0,1]                       -> [0,1]    one of the answers is 0
 *   [2,1,2,3]                   -> [1,3]    diff = 2, lowBit = 2: the two answers differ only at bit 1
 *   [Integer.MIN_VALUE, 0]      -> [0,MIN]  -diff overflows back to MIN_VALUE, and diff & -diff is
 *                                           still the sign bit, so it still works
 *
 * COMMON MISTAKES
 *   - Checking (num & lowBit) == 1: lowBit can be 2, 4, 8 ..., so the check is always false.
 *     Compare with 0 instead.
 *   - Using diff & (diff - 1): that removes the lowest 1-bit instead of keeping it.
 *   - Returning diff (a ^ b) as if it were an answer: it is only used to find the splitting bit.
 *   - A HashSet of values: correct, but O(n) extra space breaks the constraint.
 *
 * COMPLEXITY
 *   O(n) time (two passes), O(1) extra space.
 *
 * RELATED
 *   LeetCode 136 (SingleNumber, Math/): one single value; the XOR of everything is the answer.
 *   LeetCode 137 (SingleNumber2, Math/): values come in threes; count each bit modulo 3.
 */
public class SingleNumber3 {

    public static void main(String[] args) {
        int[] nums = {1,2,1,3,2,5};
        System.out.println(Arrays.toString(singleNumber(nums)));
    }

    public static int[] singleNumber(int[] nums) {
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        int lowBit = diff & -diff;

        int groupA = 0;
        int groupB = 0;
        for (int num : nums) {
            if ((num & lowBit) == 0) {
                groupA ^= num;
            } else {
                groupB ^= num;
            }
        }
        return new int[]{groupA, groupB};
    }
}

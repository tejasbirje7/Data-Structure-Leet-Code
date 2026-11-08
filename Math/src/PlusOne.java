import java.util.Arrays;

/**
 * LeetCode 66. Plus One
 * https://leetcode.com/problems/plus-one/
 *
 * PROBLEM
 *   - Input: a big number stored as an array of digits, most significant digit first, no leading 0s.
 *   - Return: the digits of that number + 1.
 *   - Constraints: 1 to 100 digits, so it can't be turned into an int or long.
 *
 * EXAMPLE
 *   Input: digits = [1,2,3]
 *   Output: [1,2,4]
 *   Explanation: The array represents the integer 123.
 *   Incrementing by one gives 123 + 1 = 124.
 *   Thus, the result should be [1,2,4].
 *
 * HOW TO THINK
 *   - Clue: "add one" to digits = schoolbook addition from the right, with a carry.
 *   - Technique: walk from the last digit to the first, adding the carry (which starts at 1).
 *   - Key fact: a carry only continues past a 9 (9 + 1 = 10 -> write 0, carry 1).
 *     Any other digit just goes up by one and the carry stops.
 *   - Rule that stays true: everything right of index is already final; carry = what still has to be added.
 *   - Decision: digit = (digit + carry) % 10; carry = 1 if the new digit is 0 (it was a 9), else 0.
 *               The loop stops as soon as carry is 0.
 *   - Return: digits, or, if a carry is left after the first digit (all 9s), a new array
 *     one longer: 1 followed by zeros.
 *   - main calls PlusOne (capital P). plusOne (small p) is the same idea: return at the first digit
 *     below 9 after adding 1, else set it to 0 and move left; all 9s -> new array [1,0,...,0].
 *
 * STEPS
 *   1. carry = 1, index = last position.
 *   2. While index >= 0 and carry > 0: digits[index] = (digits[index] + carry) % 10.
 *   3.   carry = (digits[index] == 0) ? 1 : 0; index--.
 *   4. If carry is still 1: digits = new array of length n + 1 with digits[0] = 1.
 *   5. Return digits.
 *
 * DRY RUN   digits = [9,8,9]   (values after the step)
 *
 *    index | digit before | (digit + carry) % 10 | carry after | digits
 *   -------+--------------+----------------------+-------------+---------
 *      2   |      9       |   (9 + 1) % 10 = 0   |      1      | [9,8,0]
 *      1   |      8       |   (8 + 1) % 10 = 9   |      0      | [9,9,0]
 *
 *   carry = 0, loop stops with index = 0. No carry left, returns [9,9,0] (989 + 1 = 990).
 *
 * EDGE CASES
 *   [1,2,3]     -> [1,2,4]     no carry, one step
 *   [9]         -> [1,0]       carry out of the only digit, new longer array
 *   [9,9,9]     -> [1,0,0,0]   every digit carries
 *   [1,9,9]     -> [2,0,0]     carry stops at the first digit
 *   [0]         -> [1]         the number zero
 *   []          -> [1]         carry is left, new array of length 1 (LeetCode has at least one digit)
 *   Both methods change the input array in place when no new array is needed.
 *
 * COMMON MISTAKES
 *   - Converting to int or long and adding 1: overflows with up to 100 digits.
 *   - Not handling all 9s: you return [0,0,0] instead of [1,0,0,0]. Make a new array, one longer.
 *   - Copying the old digits into the new array: not needed, they are all 0; only set index 0 to 1.
 *   - Looping over every digit even after the carry is 0: still right, but does useless work.
 *
 * COMPLEXITY
 *   O(n) time in the worst case (all 9s), O(1) extra space, or O(n) when a new array is needed.
 *
 * RELATED
 *   LeetCode 2 (AddTwoNumbers, LinkedList/): the same digit-by-digit addition with a carry,
 *     on two linked lists stored in reverse order.
 */
public class PlusOne {

    public static void main(String[] args) {
        int[] arr = {9,8,9};
        System.out.println(Arrays.toString(PlusOne(arr)));
    }

    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int [n+1];
        newNumber[0] = 1;

        return newNumber;
    }

    public static int[] PlusOne(int[] digits) {
        int carry = 1;
        int index = digits.length - 1;

        while (index >= 0 && carry > 0) {
            digits[index] = (digits[index] + carry) % 10;
            carry = digits[index] == 0 ? 1 : 0;
            index--;
        }

        if (carry > 0) {
            digits = new int[digits.length + 1];
            digits[0] = 1;
        }

        return digits;
    }

}

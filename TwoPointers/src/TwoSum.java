import java.util.Arrays;

/**
 * LeetCode 167. Two Sum II - Input Array Is Sorted
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 *
 * PROBLEM
 *   - Input: numbers, sorted in non-decreasing order, and a target.
 *   - Return: the 1-based indexes [index1, index2] (index1 < index2) of the two numbers that add up to target.
 *   - Exactly one answer exists; the same element can't be used twice.
 *   - Use only constant extra space.
 *
 * EXAMPLE
 *   Input: numbers = [2,7,11,15], target = 9
 *   Output: [1,2]
 *   Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 *
 * HOW TO THINK
 *   - Clue: "sorted" + "pair that sums to target" + "constant extra space" = two pointers from both ends.
 *   - Technique: l at the smallest number, r at the largest; move them towards each other.
 *   - Why it works: sum too small -> only a bigger left number can help (l++).
 *                   sum too big   -> only a smaller right number can help (r--).
 *     Each move throws away one number that can't be part of the answer.
 *   - Rule that stays true: the answer pair is always inside [l, r].
 *   - Decision: sum <  target -> l++.
 *               sum >  target -> r--.
 *               sum == target -> stop (the while condition).
 *   - Return: the answer is 1-based, so return [l + 1, r + 1].
 *
 * STEPS
 *   1. l = 0, r = n - 1.
 *   2. While numbers[l] + numbers[r] != target:
 *   3.   if the sum < target, l++; else r--.
 *   4. Return [l + 1, r + 1].
 *
 * DRY RUN   numbers = [2,7,11,15], target = 18   (l, r are values before the move)
 *
 *    l | r | numbers[l] | numbers[r] | sum | action
 *   ---+---+------------+------------+-----+-----------------
 *    0 | 3 |     2      |     15     |  17 | < 18, l++
 *    1 | 3 |     7      |     15     |  22 | > 18, r--
 *    1 | 2 |     7      |     11     |  18 | == 18, stop
 *
 *   Returns [l + 1, r + 1] = [2,3]   (7 + 11 = 18).
 *
 * EDGE CASES
 *   [2,7,11,15], target = 9   -> [1,2]   only r moves
 *   [2,3,4], target = 6       -> [1,3]   the first pair already matches
 *   [-1,0], target = -1       -> [1,2]   negatives work the same way
 *   [1,5], target = 10        -> [2,2]   wrong: l meets r and 5 is used twice; LeetCode guarantees one valid answer
 *   [1,2], target = 10        -> crashes with ArrayIndexOutOfBoundsException (l runs past the end);
 *                                LeetCode guarantees an answer exists
 *   []                        -> crashes on numbers[0]; LeetCode guarantees at least 2 numbers
 *
 * COMMON MISTAKES
 *   - Returning 0-based indexes: this problem wants 1-based, so add 1 to both.
 *   - Moving the wrong pointer: too small -> l++, too big -> r--.
 *   - Using a HashMap (the LeetCode 1 way): works, but breaks the constant extra space rule.
 *   - No l < r check: fine only because an answer is guaranteed (see EDGE CASES).
 *
 * COMPLEXITY
 *   O(n) time (each step moves one pointer), O(1) extra space.
 *
 * RELATED
 *   LeetCode 1 (TwoSum, HashMap/): variant of this problem. The array is not sorted and the answer is
 *     0-based, so it uses a hash map of value -> index instead of two pointers.
 *   LeetCode 15 (ThreeSum, TwoPointers/): fixes one number, then runs this loop on the rest.
 *   LeetCode 11 (MostWaterInContainer, TwoPointers/): same "start at both ends, move one inwards" pattern.
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] numbers = {2,7,11,15};
        int target = 18;
        System.out.println(Arrays.toString(twoSum(numbers, target)));
    }

    public static int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;

        while (numbers[l] + numbers[r] != target) {
            if(numbers[l] + numbers[r] < target) l++;
            else r--;
        }

        return new int[] {l+1,r+1};
    }
}

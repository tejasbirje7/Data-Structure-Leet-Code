import java.util.Arrays;

/**
 * LeetCode 27. Remove Element
 * https://leetcode.com/problems/remove-element/
 *
 * PROBLEM
 *   - Input: an integer array nums (not sorted) and a value val.
 *   - Task: remove every occurrence of val in place. The order of the kept values may change.
 *   - Return: k = how many values are not equal to val; they must sit in the first k slots.
 *   - Whatever is left after the first k slots doesn't matter.
 *
 * EXAMPLE
 *   Input: nums = [3,2,2,3], val = 3
 *   Output: 2, nums = [2,2,_,_]
 *   Explanation: Your function should return k = 2, with the first two elements of nums being 2.
 *   It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * HOW TO THINK
 *   - Clue: "in place" + "return a count" = two pointers on the same array.
 *   - Technique: i reads every value; nonValPointer is the next slot to write a kept value.
 *     Writing never gets ahead of reading, so no unread value is lost.
 *   - Rule that stays true: nums[0 .. nonValPointer-1] holds every non-val value seen so far, in order.
 *   - Decision: nums[i] != val -> keep: nums[nonValPointer] = nums[i], nonValPointer++.
 *               nums[i] == val -> skip.
 *   - Return: nonValPointer is the next write slot, which is also the count of kept values.
 *
 * STEPS
 *   1. nonValPointer = 0.
 *   2. Loop i over the whole array.
 *   3. If nums[i] != val: nums[nonValPointer] = nums[i], then nonValPointer++.
 *   4. Return nonValPointer.
 *
 * DRY RUN   nums = [0,1,2,2,3,0,4,2], val = 2   (nonValPointer and the kept part are after the step)
 *
 *    i | nums[i] | action            | nonValPointer | nums[0 .. nonValPointer-1]
 *   ---+---------+-------------------+---------------+----------------------------
 *    0 |    0    | keep, nums[0] = 0 |       1       | [0]
 *    1 |    1    | keep, nums[1] = 1 |       2       | [0,1]
 *    2 |    2    | val, skip         |       2       | [0,1]
 *    3 |    2    | val, skip         |       2       | [0,1]
 *    4 |    3    | keep, nums[2] = 3 |       3       | [0,1,3]
 *    5 |    0    | keep, nums[3] = 0 |       4       | [0,1,3,0]
 *    6 |    4    | keep, nums[4] = 4 |       5       | [0,1,3,0,4]
 *    7 |    2    | val, skip         |       5       | [0,1,3,0,4]
 *
 *   Returns 5. Final array: [0,1,3,0,4,0,4,2]; only the first 5 slots count.
 *
 * EDGE CASES
 *   [], val = 0          -> 0   the loop never runs
 *   [2], val = 2         -> 0   the only value is val
 *   [3,3,3], val = 3     -> 0   every step is a skip
 *   [1,2,3], val = 4     -> 3   val never appears; each value is copied onto itself
 *   [3,2,2,3], val = 3   -> 2   LeetCode example 1, kept part [2,2]
 *
 * COMMON MISTAKES
 *   - Writing before checking: copy only when nums[i] != val.
 *   - Incrementing nonValPointer before the write: the first kept value lands in slot 1, slot 0 is junk.
 *   - Returning nonValPointer - 1: it is already a count, not an index.
 *   - Building a new array or list: works, but breaks the "in place" rule.
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 26 (RemoveDuplicates, ArraysAndStrings/): same read/write pointers, but on a sorted array;
 *     skip a value when it equals the last kept value instead of val.
 *   LeetCode 80 (RemoveDuplicates2, ArraysAndStrings/): skip a value when it equals the kept value two slots back.
 */
public class RemoveElement {
    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        System.out.println(removeElement(nums, val));
    }

    public static int removeElement(int[] nums, int val) {

        int nonValPointer = 0;

        for (int i=0; i < nums.length; i++) {
            if(nums[i] != val) {
                nums[nonValPointer] = nums[i];
                nonValPointer++;
            }
        }

        System.out.println(Arrays.toString(nums));
        return nonValPointer;


    }
}

import java.util.Arrays;

/**
 * LeetCode 80. Remove Duplicates from Sorted Array II
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 *
 * PROBLEM
 *   - Input: an integer array sorted in non-decreasing order.
 *   - Task: remove extra copies in place, so each unique element appears at most twice, in the original order.
 *   - Return: k = the length of the kept part.
 *   - O(1) extra space; only the first k slots are checked.
 *
 * EXAMPLE
 *   Input: nums = [1,1,1,2,2,3]
 *   Output: 5, nums = [1,1,2,2,3,_]
 *   Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 *   It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * HOW TO THINK
 *   - Clue: "sorted" means all copies of a value sit together;
 *     "at most twice" means a value is too many only if the kept value two slots back is the same.
 *   - Technique: two pointers on the same array. i reads; e is the next slot to write.
 *     The first two values are always kept, so both start at 2.
 *     If there are 2 or fewer values, all of them stay: return the length right away.
 *   - Rule that stays true: nums[0..e-1] is the answer so far, with each value at most twice.
 *   - Decision: nums[i] != nums[e-2] -> keep it: nums[e] = nums[i], e++.
 *               nums[i] == nums[e-2] -> already two copies kept (nums[e-2] and nums[e-1]): skip.
 *   - Return: e is the next write slot, which is also the count of kept values.
 *
 * STEPS
 *   1. If n <= 2, return n (every value stays).
 *   2. i = 2, e = 2 (the first two values always stay).
 *   3. While i < n:
 *   4.   If nums[i] != nums[e-2]: nums[e] = nums[i], e++.
 *   5.   i++.
 *   6. Return e.
 *
 * DRY RUN   nums = [0,0,1,1,1,1,2,3,3]   (nums[e-2] is the value before the step; e and the kept part are after)
 *
 *    i | nums[i] | nums[e-2] | action            | e | nums[0..e-1]
 *   ---+---------+-----------+-------------------+---+-----------------
 *    2 |    1    |     0     | keep, nums[2] = 1 | 3 | [0,0,1]
 *    3 |    1    |     0     | keep, nums[3] = 1 | 4 | [0,0,1,1]
 *    4 |    1    |     1     | third 1, skip     | 4 | [0,0,1,1]
 *    5 |    1    |     1     | fourth 1, skip    | 4 | [0,0,1,1]
 *    6 |    2    |     1     | keep, nums[4] = 2 | 5 | [0,0,1,1,2]
 *    7 |    3    |     1     | keep, nums[5] = 3 | 6 | [0,0,1,1,2,3]
 *    8 |    3    |     2     | keep, nums[6] = 3 | 7 | [0,0,1,1,2,3,3]
 *
 *   Returns 7. Final array: [0,0,1,1,2,3,3,3,3]; only the first 7 slots count.
 *
 * EDGE CASES
 *   [1]            -> 1   length <= 2, returned right away
 *   [1,1]          -> 2   length <= 2, returned right away; both values stay
 *   [1,1,1]        -> 2   the third 1 matches nums[0], skipped
 *   [2,2,2,2]      -> 2   every step after the first two is a skip
 *   [1,2,3]        -> 3   every value is new, each is copied onto itself
 *   []             -> 0   outside LeetCode's constraints (at least one element), but the length check handles it
 *
 * COMMON MISTAKES
 *   - Comparing with nums[i-2] instead of nums[e-2]: slots behind i may already be overwritten.
 *     Compare with what you KEPT (the write side).
 *   - Comparing with nums[e-1]: that allows only one copy (that's LeetCode 26).
 *   - Starting the write pointer at 2 without checking the length: a one-element array returns 2.
 *     Return n first when n <= 2.
 *   - Returning e - 1 or e + 1: e is already a count.
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 26 (RemoveDuplicates, ArraysAndStrings/): each value at most once.
 *     Same idea, but compare with the last kept value and return k + 1.
 *   LeetCode 27 (RemoveElement, ArraysAndStrings/): same read/write pointers; keep every value that isn't val.
 */
public class RemoveDuplicates2 {

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {

        if(nums.length <= 2) {
            return nums.length;
        }

        int i=2;
        int e=2;

        while (i < nums.length) {
            if(nums[i] != nums[e-2]) {
                nums[e] = nums[i];
                e++;
            }
            i++;
        }
        System.out.println(Arrays.toString(nums));
        return e;
    }


}

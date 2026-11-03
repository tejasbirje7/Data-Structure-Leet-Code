import java.util.Arrays;

/**
 * LeetCode 26. Remove Duplicates from Sorted Array
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *
 * PROBLEM
 *   The array is sorted in non-decreasing order. Remove the duplicates in place so each value
 *   appears once, in the original order, and return k = the number of unique values.
 *   Only the first k slots are checked; whatever is left after them doesn't matter.
 *
 * EXAMPLE
 *   Input: nums = [1,1,2]
 *   Output: 2, nums = [1,2,_]
 *   Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
 *   It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * HOW TO THINK
 *   Sorted means every copy of a value sits right next to the others, so you never need to look
 *   further back than the last unique value you kept. "In place" plus "return a length" is the cue
 *   for two pointers on the same array: i reads every element, and k marks the slot holding the last
 *   unique value written so far. One rule stays true the whole time: nums[0..k] are unique and in
 *   order. If nums[i] differs from nums[k], it's a new value, so move k forward and copy it there.
 *   If it's equal, it's a duplicate, so skip it. At the end k is an index, so the count is k + 1.
 *
 * STEPS
 *   1. Start with k = 0 (nums[0] is always unique).
 *   2. Loop i over the whole array.
 *   3. If nums[i] != nums[k]: k++, then nums[k] = nums[i].
 *   4. Return k + 1.
 *
 * DRY RUN   nums = [0,0,1,1,1,2,2,3,3,4]   (nums[k] is the value before the step's action)
 *
 *    i | nums[i] | nums[k] | action           | k | nums[0..k]
 *   ---+---------+---------+------------------+---+-------------
 *    0 |    0    |    0    | same, skip       | 0 | [0]
 *    1 |    0    |    0    | same, skip       | 0 | [0]
 *    2 |    1    |    0    | new, nums[1] = 1 | 1 | [0,1]
 *    3 |    1    |    1    | same, skip       | 1 | [0,1]
 *    4 |    1    |    1    | same, skip       | 1 | [0,1]
 *    5 |    2    |    1    | new, nums[2] = 2 | 2 | [0,1,2]
 *    6 |    2    |    2    | same, skip       | 2 | [0,1,2]
 *    7 |    3    |    2    | new, nums[3] = 3 | 3 | [0,1,2,3]
 *    8 |    3    |    3    | same, skip       | 3 | [0,1,2,3]
 *    9 |    4    |    3    | new, nums[4] = 4 | 4 | [0,1,2,3,4]
 *
 *   Returns k + 1 = 5. Final array: [0,1,2,3,4,2,2,3,3,4]; only the first 5 slots count.
 *
 * EDGE CASES
 *   [7]          -> 1   nothing new is ever found, k stays 0
 *   [2,2,2,2]    -> 1   every step is a skip
 *   [1,2,3]      -> 3   every value is new, so each one is copied onto itself
 *   []           -> returns 1 (should be 0); LeetCode guarantees at least one element
 *
 * COMMON MISTAKES
 *   - Returning k instead of k + 1: k is an index, not a count.
 *   - Writing nums[k++] = nums[i] with k = 0: that overwrites the first unique value.
 *     Move k first, then write (nums[++k] = nums[i]).
 *   - Using a Set or a second array: works, but breaks the "in place, O(1) extra space" rule.
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 80 (RemoveDuplicates2): each value may appear at most twice. Same idea, but compare
 *     with the value two slots behind the next write position (nums[e - 2]).
 *   LeetCode 83 (LinkedList/RemoveDuplicatesfromSortedList): the same problem on a linked list.
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
        int k = 0;
        for(int i=0; i < nums.length; i++) {
            if(nums[k] != nums[i]) {
                nums[++k] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return k+1;
    }
}

import java.util.Arrays;

/**
 * LeetCode 31. Next Permutation
 * https://leetcode.com/problems/next-permutation/
 *
 * PROBLEM
 *   - Input: an integer array nums (values may repeat).
 *   - Return: nothing; rearrange nums into the next larger arrangement in dictionary order.
 *     If nums is already the largest arrangement, rearrange it into the smallest (sorted ascending).
 *   - Constraints: in place, O(1) extra memory.
 *
 * EXAMPLE
 *   Input: nums = [1,2,3]
 *   Output: [1,3,2]
 *
 * HOW TO THINK
 *   - Clue: "next" in dictionary order -> change as far to the right as possible, by as little as possible.
 *   - Technique: scan from the right.
 *     The longest non-increasing suffix is already at its largest, so it can't be made bigger.
 *     The element just before it (the pivot) must grow by the smallest possible step.
 *   - Rule that stays true: everything right of i is non-increasing while i moves left.
 *   - Decision: find pivot i = last index with nums[i] < nums[i+1].
 *     Found -> swap it with the rightmost element bigger than it, then reverse the suffix to make it smallest.
 *     Not found (i = -1) -> the whole array is descending; reverse all of it.
 *   - Return: nothing. After the swap the suffix is still non-increasing, so reversing it sorts it ascending.
 *
 * STEPS
 *   1. i = n-2. While i >= 0 and nums[i] >= nums[i+1]: i--.
 *   2. If i >= 0: j = n-1. While nums[j] <= nums[i]: j--. Swap nums[i] and nums[j].
 *   3. Reverse nums[i+1 .. n-1].
 *
 * DRY RUN   input = [2,3,6,5,4,1]
 *
 *    phase       | index | compare               | action          | nums after
 *   -------------+-------+-----------------------+-----------------+--------------
 *    find pivot  | i = 4 | 4 >= 1 ? yes          | i--             | [2,3,6,5,4,1]
 *    find pivot  | i = 3 | 5 >= 4 ? yes          | i--             | [2,3,6,5,4,1]
 *    find pivot  | i = 2 | 6 >= 5 ? yes          | i--             | [2,3,6,5,4,1]
 *    find pivot  | i = 1 | 3 >= 6 ? no           | pivot = 3 at 1  | [2,3,6,5,4,1]
 *    find swap   | j = 5 | 1 <= 3 ? yes          | j--             | [2,3,6,5,4,1]
 *    find swap   | j = 4 | 4 <= 3 ? no           | swap i=1, j=4   | [2,4,6,5,3,1]
 *    reverse     | 2..5  | 6,5,3,1 -> 1,3,5,6    | reverse         | [2,4,1,3,5,6]
 *
 *   Final state: [2,4,1,3,5,6] (the next number after 236541 is 241356).
 *
 * EDGE CASES
 *   [1,2,3]   -> [1,3,2]   pivot is the second-to-last element
 *   [3,2,1]   -> [1,2,3]   no pivot (i = -1): reverse the whole array
 *   [1,1,5]   -> [1,5,1]   duplicates: pivot is the second 1
 *   [1,5,1]   -> [5,1,1]   swap target is 5, not the equal 1 on the right
 *   [1]       -> [1]       nothing to do
 *
 * COMMON MISTAKES
 *   - Using > instead of >= in the pivot scan: with duplicates (e.g. [1,5,1,1]) an equal pair is taken as the pivot.
 *   - Swapping with the first larger element from the left of the suffix instead of the rightmost larger one:
 *     the step is no longer the smallest possible.
 *   - Sorting the suffix: correct but O(n log n); it is already non-increasing, so a reverse is enough.
 *   - Forgetting to reverse when there is no pivot: the largest arrangement must wrap around to the smallest.
 *
 * COMPLEXITY
 *   O(n) time (at most three passes), O(1) extra space.
 *
 * RELATED
 *   LeetCode 46 (Permutations, Backtracking/): lists every arrangement at once; this finds only the next one.
 *   LeetCode 47 (UniquePermutations, FAQ/): every arrangement with duplicates, which this handles through >=.
 *   LeetCode 189 (RotateArray, ArraysAndStrings/): uses the same in-place reverse helper.
 */
public class NextPermutation {

    public static void main(String[] args) {
        int[] nums = {2, 3, 6, 5, 4, 1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    private static void reverse(int[] nums, int from, int to) {
        while (from < to) {
            swap(nums, from, to);
            from++;
            to--;
        }
    }

}

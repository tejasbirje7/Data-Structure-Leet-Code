import java.util.Arrays;

/**
 * LeetCode 75. Sort Colors
 * https://leetcode.com/problems/sort-colors/
 *
 * PROBLEM
 *   - Input: an array nums of 0s, 1s and 2s (red, white, blue), 1..300 long.
 *   - Sort it in place so all 0s come first, then 1s, then 2s.
 *   - No library sort. Follow-up: one pass, O(1) extra space.
 *
 * EXAMPLE
 *   Input: nums = [2,0,2,1,1,0]
 *   Output: [0,0,1,1,2,2]
 *
 * HOW TO THINK
 *   - Clue: only 3 values + "in place, one pass" = Dutch national flag: three pointers.
 *   - Technique: low, mid, high split the array into four zones.
 *   - Rule that stays true:
 *       nums[0..low-1]      are 0
 *       nums[low..mid-1]    are 1
 *       nums[mid..high]     not looked at yet
 *       nums[high+1..n-1]   are 2
 *   - Decision on nums[mid]:
 *       0 -> swap with nums[low]; low++, mid++ (what came from low is a 1, or mid == low, so it is done).
 *       1 -> already in the 1 zone; mid++.
 *       2 -> swap with nums[high]; high-- only. Do NOT move mid: the value that came from high is unseen.
 *   - Stop when mid > high (no unseen zone left). Nothing to return; the array is sorted.
 *
 * STEPS
 *   1. low = 0, mid = 0, high = n - 1.
 *   2. While mid <= high:
 *   3.   0 -> swap(low, mid), low++, mid++.  1 -> mid++.  2 -> swap(mid, high), high--.
 *   4. Done: the array is sorted in place.
 *
 * DRY RUN   nums = [2,0,2,1,1,0]   (pointers and array are after the step)
 *
 *    step | low,mid,high before | nums[mid] | action                       | array after     | low,mid,high after
 *   ------+---------------------+-----------+------------------------------+-----------------+-------------------
 *     1   |      0, 0, 5        |    2      | swap(mid,high), high--       | [0,0,2,1,1,2]   |     0, 0, 4
 *     2   |      0, 0, 4        |    0      | swap(low,mid), low++, mid++  | [0,0,2,1,1,2]   |     1, 1, 4
 *     3   |      1, 1, 4        |    0      | swap(low,mid), low++, mid++  | [0,0,2,1,1,2]   |     2, 2, 4
 *     4   |      2, 2, 4        |    2      | swap(mid,high), high--       | [0,0,1,1,2,2]   |     2, 2, 3
 *     5   |      2, 2, 3        |    1      | mid++                        | [0,0,1,1,2,2]   |     2, 3, 3
 *     6   |      2, 3, 3        |    1      | mid++                        | [0,0,1,1,2,2]   |     2, 4, 3
 *
 *   mid (4) > high (3): stop. Final array [0,0,1,1,2,2].
 *
 * EDGE CASES
 *   [0]           -> [0]           one element
 *   [2,0,1]       -> [0,1,2]       LeetCode example 2
 *   [2,2,2]       -> [2,2,2]       every step swaps mid with itself and moves high
 *   [0,0,0]       -> [0,0,0]       every step swaps low with itself
 *   [1,0]         -> [0,1]         a 0 after 1s swaps back into place
 *
 * COMMON MISTAKES
 *   - Moving mid after swapping with high: the value that came from high was never checked
 *     (e.g. [2,0,0] would leave a 0 in the middle).
 *   - Loop condition mid < high: the last unseen element (mid == high) is never checked.
 *   - Counting 0s/1s/2s and rewriting: correct, but two passes; the follow-up wants one.
 *
 * COMPLEXITY
 *   O(n) time (each step either moves mid forward or high back), O(1) extra space.
 *
 * RELATED
 *   LeetCode 283 (MoveZeroes, TwoPointers/): the same idea with two zones (non-zero, zero) instead of three.
 *   LeetCode 26 (RemoveDuplicates, ArraysAndStrings/): a write pointer that marks where the "done" zone ends.
 */
public class SortColors {

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low++, mid++);
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high--);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}

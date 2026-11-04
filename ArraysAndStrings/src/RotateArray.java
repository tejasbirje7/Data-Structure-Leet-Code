import java.util.Arrays;

/**
 * LeetCode 189. Rotate Array
 * https://leetcode.com/problems/rotate-array/
 *
 * PROBLEM
 *   - Input: an integer array nums and a non-negative number k.
 *   - Task: rotate the array to the right by k steps, in place.
 *   - Return: nothing; nums itself must end up rotated.
 *   - Follow-up: do it with O(1) extra space.
 *
 * EXAMPLE
 *   Input: nums = [1,2,3,4,5,6,7], k = 3
 *   Output: [5,6,7,1,2,3,4]
 *   Explanation:
 *   rotate 1 steps to the right: [7,1,2,3,4,5,6]
 *   rotate 2 steps to the right: [6,7,1,2,3,4,5]
 *   rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 * HOW TO THINK
 *   - Clue: rotating right by k = the last k elements move to the front,
 *     and the order inside each part stays the same.
 *   - Picture: split the array into [A | B], where B = the last k elements. The answer is [B | A].
 *   - Technique: three reversals.
 *     Reverse everything -> [B reversed | A reversed]: B is in front, but backwards.
 *     Reverse each part again -> [B | A]: each part is back in its original order.
 *   - First, k = k % n: rotating by n gives the same array, so only the remainder matters
 *     (e.g. n = 7, k = 10 is the same as k = 3).
 *   - reverse(from, to): swap the two ends, move both inward, stop when they meet.
 *   - Return: nothing, the array is changed in place.
 *
 * STEPS
 *   1. k = k % n.
 *   2. Reverse the whole array (0 .. n-1).
 *   3. Reverse the first k elements (0 .. k-1).
 *   4. Reverse the rest (k .. n-1).
 *
 * DRY RUN   nums = [1,2,3,4,5,6,7], k = 3   (n = 7, k % n = 3)
 *
 *    step | reverse range | part                 | array after
 *   ------+---------------+----------------------+-----------------
 *    start|       -       | -                    | [1,2,3,4,5,6,7]
 *      1  |    0 .. 6     | whole array          | [7,6,5,4,3,2,1]
 *      2  |    0 .. 2     | first k (B, fixed)   | [5,6,7,4,3,2,1]
 *      3  |    3 .. 6     | the rest (A, fixed)  | [5,6,7,1,2,3,4]
 *
 *   Final array: [5,6,7,1,2,3,4].
 *
 * EDGE CASES
 *   k = 0                    -> unchanged   whole array reversed twice, first-k reverse does nothing
 *   k = n                    -> unchanged   k % n = 0
 *   [1,2,3], k = 4           -> [3,1,2]     k % n = 1
 *   [5], any k               -> [5]         k % 1 = 0
 *   [-1,-100,3,99], k = 2    -> [3,99,-1,-100]
 *   []                       -> crashes on k % 0; LeetCode guarantees at least one element
 *
 * COMMON MISTAKES
 *   - Forgetting k = k % n: when k > n, reverse(0, k-1) goes out of bounds.
 *   - Splitting at n-k instead of k after the full reverse: that rotates left, not right.
 *   - Rotating one step at a time, k times: O(n * k), too slow for large inputs.
 *
 * COMPLEXITY
 *   O(n) time (each element is swapped about twice), O(1) extra space.
 *
 * RELATED
 *   LeetCode 61 (RotateList, LinkedList/): the same rotation on a linked list.
 *     Also uses k % length, then reconnects the list at the new head instead of reversing.
 */
public class RotateArray {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int val = 3;
        rotate(nums, val);
        System.out.println(Arrays.toString(nums));
    }

    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }

    public static void reverse(int[] nums , int from, int to) {
        while (from < to) {
            int temp = nums[to];
            nums[to] = nums[from];
            nums[from] = temp;
            from++;to--;
        }
    }

}

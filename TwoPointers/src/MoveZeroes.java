import java.util.Arrays;

/**
 * LeetCode 283. Move Zeroes
 * https://leetcode.com/problems/move-zeroes/
 *
 * PROBLEM
 *   - Input: an int array nums (1..10^4 long).
 *   - Move all 0s to the end, keeping the order of the non-zero numbers.
 *   - In place, no copy of the array. Follow-up: as few operations as possible.
 *
 * EXAMPLE
 *   Input: nums = [0,1,0,3,12]
 *   Output: [1,3,12,0,0]
 *
 * HOW TO THINK
 *   - Clue: "in place" + "keep relative order" = a read pointer and a write pointer.
 *   - Technique: i reads every element; k is where the next non-zero belongs.
 *   - Rule that stays true: nums[0..k-1] are the non-zeros seen so far, in order;
 *     nums[k..i-1] are all 0.
 *   - Decision for each i: nums[i] != 0 -> swap(i, k), k++ (moves the non-zero down and a 0 up).
 *                          nums[i] == 0 -> skip (it stays in the zero zone).
 *   - Return: nothing; after the loop nums[k..] are the zeros.
 *
 * STEPS
 *   1. k = 0.
 *   2. For i = 0..n-1:
 *   3.   if nums[i] != 0: swap(nums[i], nums[k]), k++.
 *   4. Done: the array is rearranged in place.
 *
 * DRY RUN   nums = [0,1,0,3,12]   (array and k are after the step)
 *
 *    i | nums[i] | k before | action           | array after     | k after
 *   ---+---------+----------+------------------+-----------------+--------
 *    0 |    0    |    0     | skip             | [0,1,0,3,12]    |   0
 *    1 |    1    |    0     | swap(1,0), k++   | [1,0,0,3,12]    |   1
 *    2 |    0    |    1     | skip             | [1,0,0,3,12]    |   1
 *    3 |    3    |    1     | swap(3,1), k++   | [1,3,0,0,12]    |   2
 *    4 |   12    |    2     | swap(4,2), k++   | [1,3,12,0,0]    |   3
 *
 *   Final array [1,3,12,0,0]; k = 3 non-zeros.
 *
 * EDGE CASES
 *   [0]          -> [0]           one zero
 *   [1]          -> [1]           i == k, swaps with itself
 *   [1,2,3]      -> [1,2,3]       no zeros, every swap is with itself
 *   [0,0,1]      -> [1,0,0]       zeros first
 *   [-1,0,5]     -> [-1,5,0]      negatives are non-zero too
 *
 * COMMON MISTAKES
 *   - Removing zeros and shifting the rest each time: O(n^2).
 *   - Putting k++ outside the if: k then always equals i, every swap is with itself and nothing moves.
 *   - Using nums[i] > 0 as the test: negative numbers are moved to the end too.
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space. Each non-zero is swapped once.
 *
 * RELATED
 *   LeetCode 27 (RemoveElement, ArraysAndStrings/): same read/write pointers; drops a value instead of moving it.
 *   LeetCode 26 (RemoveDuplicates, ArraysAndStrings/): write pointer that keeps only new values.
 *   LeetCode 75 (SortColors, TwoPointers/): three zones instead of two.
 */
public class MoveZeroes {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int t = nums[i];
                nums[i] = nums[k];
                nums[k] = t;
                k++;
            }
        }
    }
}

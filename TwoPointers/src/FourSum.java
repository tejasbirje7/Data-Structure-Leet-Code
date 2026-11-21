import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 18. 4Sum
 * https://leetcode.com/problems/4sum/
 *
 * PROBLEM
 *   - Input: an int array nums (1..200 long, values -10^9..10^9) and a target (same range).
 *   - Return: every unique quadruplet [a,b,c,d] of values at four different indexes with
 *     a + b + c + d == target, in any order.
 *   - No duplicate quadruplets in the answer.
 *
 * EXAMPLE
 *   Input: nums = [1,0,-1,0,-2,2], target = 0
 *   Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * HOW TO THINK
 *   - Clue: "unique quadruplets that sum to target" = 3Sum with one more outer loop.
 *   - Technique: sort; fix the first two numbers with loops i and j; find the last two
 *     with two pointers (left, right) on the sorted rest.
 *   - Overflow: four values of 10^9 overflow an int, so add them as a long.
 *   - No duplicates trick: in each of the four positions, skip a value equal to the one just used there:
 *       i > 0 and nums[i] == nums[i-1]           -> skip i
 *       j > i + 1 and nums[j] == nums[j-1]       -> skip j
 *       after a match, move left past equal values and right past equal values.
 *   - Rule that stays true (inner loop): every pair outside [left..right] can't complete a new quadruplet
 *     with nums[i], nums[j].
 *   - Decision: sum == target -> save, move both inwards past repeats.
 *               sum < target  -> left++ (need bigger).
 *               sum > target  -> right-- (need smaller).
 *   - Return: result.
 *
 * STEPS
 *   1. Sort nums.
 *   2. For i = 0..n-4 (skip repeats of nums[i]); for j = i+1..n-3 (skip repeats of nums[j]):
 *   3.   left = j + 1, right = n - 1; while left < right: sum = (long) the four values.
 *   4.   == target: save, left++, right--, skip repeats on both sides. < target: left++. > target: right--.
 *   5. Return result.
 *
 * DRY RUN   nums = [1,0,-1,0,-2,2], target = 0  ->  sorted [-2,-1,0,0,1,2] (indexes 0..5)
 *
 *    i | j | left | right | values            | sum | action
 *   ---+---+------+-------+-------------------+-----+------------------------------
 *    0 | 1 |  2   |   5   | -2,-1, 0, 2       | -1  | < 0, left++
 *    0 | 1 |  3   |   5   | -2,-1, 0, 2       | -1  | < 0, left++
 *    0 | 1 |  4   |   5   | -2,-1, 1, 2       |  0  | save [-2,-1,1,2], left++, right--
 *    0 | 2 |  3   |   5   | -2, 0, 0, 2       |  0  | save [-2,0,0,2], left++, right--
 *    0 | 3 |  -   |   -   | nums[3] == nums[2]|  -  | skip j (same 0 as before)
 *    1 | 2 |  3   |   5   | -1, 0, 0, 2       |  1  | > 0, right--
 *    1 | 2 |  3   |   4   | -1, 0, 0, 1       |  0  | save [-1,0,0,1], left++, right--
 *    1 | 3 |  -   |   -   | nums[3] == nums[2]|  -  | skip j
 *    2 | 3 |  4   |   5   |  0, 0, 1, 2       |  3  | > 0, right--
 *   (only steps inside the while loop are shown; once left >= right the loop for that (i, j) just ends)
 *
 *   Returns [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]].
 *
 * EDGE CASES
 *   [2,2,2,2,2], 8                            -> [[2,2,2,2]]   repeats skipped at every position
 *   [1,2,3], 6                                -> []            fewer than 4 numbers, loops don't run
 *   [0,0,0,0], 0                              -> [[0,0,0,0]]   all equal
 *   [1000000000,1000000000,1000000000,1000000000], -294967296 -> []   int sum would overflow to the target
 *   [-3,-1,0,2,4,5], 2                        -> [[-3,-1,2,4]] negatives and positives mixed
 *
 * COMMON MISTAKES
 *   - Adding the four values as int: 4 * 10^9 overflows and can falsely equal target. Use long.
 *   - Skipping j repeats with j > 0 instead of j > i + 1: drops valid answers like [-2,0,0,2].
 *   - Forgetting to skip repeats after a match: the same quadruplet is saved again.
 *   - Using a Set of lists to remove duplicates: works but hides the logic and is slower.
 *
 * COMPLEXITY
 *   O(n^3) time (two loops times a linear two-pointer scan), plus O(n log n) to sort.
 *   O(1) extra space besides the output (O(log n) for the sort).
 *
 * RELATED
 *   LeetCode 15 (ThreeSum, TwoPointers/): one fixed number instead of two; uses a Set for duplicates.
 *   LeetCode 167 (TwoSum, TwoPointers/): the inner left/right loop on its own.
 *   LeetCode 40 (CombinationSum2, Backtracking/): the same "skip a repeated value at one position" rule.
 */
public class FourSum {

    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }
}

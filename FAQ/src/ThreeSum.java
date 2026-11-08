import java.util.*;

/**
 * LeetCode 15. 3Sum
 * https://leetcode.com/problems/3sum/
 *
 * PROBLEM
 *   - Input: an integer array nums (3 <= n <= 3000, values can repeat and be negative).
 *   - Return: all triplets [nums[i], nums[j], nums[k]] with i, j, k all different and sum == 0.
 *   - The answer must not contain duplicate triplets (same three values in any order).
 *
 * EXAMPLE
 *   Input: [-1,0,1,2,-1,-4]
 *   Output: [[-1,-1,2], [-1,0,1]]
 *
 * HOW TO THINK
 *   - Clue: "triplets that sum to a target" = fix one number, then it's Two Sum on the rest.
 *     "No duplicate triplets" = sort, so equal values sit next to each other and can be skipped.
 *   - Technique: sort, loop i over the fixed number, then two pointers left = i + 1, right = n - 1.
 *   - Rule that stays true: every pair outside [left, right] (for this i) is already checked;
 *     result holds each found triplet once.
 *   - Decision: nums[i] equals nums[i - 1] -> skip i (same triplets as before).
 *               sum < 0 -> left++ (need a bigger number).
 *               sum > 0 -> right-- (need a smaller number).
 *               sum == 0 -> add the triplet, move both pointers,
 *                           then skip left over repeats of the old left value
 *                           and right over repeats of the old right value.
 *   - Return: result (the triplets come out sorted inside, smallest i first).
 *
 * STEPS
 *   1. Sort nums.
 *   2. For each i: if i > 0 and nums[i] == nums[i - 1], continue.
 *   3.   left = i + 1, right = n - 1. While left < right: sum = nums[i] + nums[left] + nums[right].
 *   4.   sum < 0 -> left++; sum > 0 -> right--;
 *        sum == 0 -> add, left++, right--, then skip duplicate lefts and duplicate rights.
 *   5. Return result.
 *
 * DRY RUN   nums = [-2,-1,-1,0,3,3] (already sorted)   (index(value); pointers before the action)
 *
 *    i | nums[i] | left  | right | sum | action                                   | result
 *   ---+---------+-------+-------+-----+------------------------------------------+-----------
 *    0 |   -2    | 1(-1) | 5(3)  |  0  | add; l=2, r=4; skip dup l->3, dup r->3   | [[-2,-1,3]]
 *    1 |   -1    | 2(-1) | 5(3)  |  1  | too big, right--                         |
 *    1 |   -1    | 2(-1) | 4(3)  |  1  | too big, right--                         |
 *    1 |   -1    | 2(-1) | 3(0)  | -2  | too small, left++ -> 3, left == right    |
 *    2 |   -1    |   -   |   -   |  -  | same as nums[1], skip i                  |
 *    3 |    0    | 4(3)  | 5(3)  |  6  | too big, right-- -> 4, left == right     |
 *    4 |    3    | 5(3)  | 5(3)  |  -  | left == right, no pair                   |
 *    5 |    3    |   -   |   -   |  -  | same as nums[4], skip i                  |
 *
 *   Returns [[-2,-1,3]]. Without the skips, -1 at index 2 or 3 at index 4 would repeat it.
 *
 * EDGE CASES
 *   [-1,0,1,2,-1,-4] -> [[-1,-1,2],[-1,0,1]]   official example; sorted to [-4,-1,-1,0,1,2]
 *   [0,1,1]          -> []          the only triplet sums to 2
 *   [0,0,0]          -> [[0,0,0]]   one triplet; i = 1 and 2 are skipped as duplicates
 *   [0,0,0,0]        -> [[0,0,0]]   the skips stop repeats
 *   [-2,0,0,2,2]     -> [[-2,0,2]]  after the find, the left skip passes the second 0
 *   [1,2]            -> []          fewer than 3 numbers: no pair ever forms (LeetCode guarantees n >= 3)
 *   Note: Arrays.sort changes the caller's array.
 *
 * COMMON MISTAKES
 *   - Skipping with nums[i] == nums[i + 1]: that drops valid triplets like [-1,-1,2].
 *     Compare with the previous value, nums[i - 1].
 *   - Not skipping duplicates after a find: the same triplet is added again.
 *   - Forgetting to move the pointers after a find: infinite loop.
 *   - Collecting into a HashSet instead of skipping: works, but costs extra memory and time.
 *
 * COMPLEXITY
 *   O(n^2) time (n fixed numbers, each with an O(n) two-pointer scan; sorting is O(n log n)).
 *   O(1) extra space apart from the sort and the output.
 *
 * RELATED
 *   LeetCode 15 (ThreeSum, TwoPointers/): same problem, also in TwoPointers/.
 *     That copy removes duplicates with a HashSet instead of skipping.
 *   LeetCode 16 (ThreeSumClosest, FAQ/): same sort + fix + two pointers, but keeps the sum
 *     closest to a target instead of collecting exact matches.
 *   LeetCode 167 (TwoSum, TwoPointers/): the inner two-pointer loop on its own.
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;

                    while (left < right && nums[left] == nums[left - 1])
                        left++;
                    while (left < right && nums[right] == nums[right + 1])
                        right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSum solver = new ThreeSum();

        System.out.println(solver.threeSum(new int[]{-2,-1,-1,0,3,3})); // [[-2,-1,3]]
        System.out.println(solver.threeSum(new int[]{0,1,1}));          // []
        System.out.println(solver.threeSum(new int[]{0,0,0}));          // [[0,0,0]]
    }
}

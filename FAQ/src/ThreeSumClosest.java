import java.util.*;

/**
 * LeetCode 16. 3Sum Closest
 * https://leetcode.com/problems/3sum-closest/
 *
 * PROBLEM
 *   - Input: an integer array nums (3 <= n <= 500) and an integer target.
 *   - Return: the sum of three numbers at different indexes that is closest to target.
 *   - Exactly one answer is guaranteed.
 *
 * EXAMPLE
 *   Input: nums = [-1,2,1,-4], target = 1
 *   Output: 2
 *   Explanation: The sum closest to 1 is 2 (-1 + 2 + 1 = 2).
 *
 * HOW TO THINK
 *   - Clue: "three numbers" + "sum" = sort, fix one number, two pointers on the rest (like 3Sum).
 *   - Technique: for each i, left = i + 1 and right = n - 1. The sorted order tells you which
 *     pointer to move: too small -> left++, too big -> right--.
 *   - Rule that stays true: res = the closest sum seen so far, difference = |res - target|.
 *   - Decision: sum == target -> return target at once (nothing is closer than 0).
 *               |sum - target| < difference -> new best: update difference and res.
 *               sum < target -> left++; otherwise -> right--.
 *   - Return: res after all i are done.
 *
 * STEPS
 *   1. Sort nums. res = nums[0] + nums[1] + nums[2], difference = Integer.MAX_VALUE.
 *   2. For i from 0 to n - 3: left = i + 1, right = n - 1.
 *   3.   While left < right: sum = nums[i] + nums[left] + nums[right]; if sum == target return target.
 *   4.   If |sum - target| < difference, save difference and res = sum.
 *   5.   If sum < target, left++; else right--.
 *   6. Return res.
 *
 * DRY RUN   nums = [-1,2,1,-4], target = 1   (sorted: [-4,-1,1,2]; dist = |sum - target|;
 *                                            res and difference after the step)
 *
 *    i | left | right | sum               |   dist    | action           | res | difference
 *   ---+------+-------+-------------------+-----------+------------------+-----+------------
 *    0 |  1   |   3   | -4 + -1 + 2 = -3  |     4     | closer, left++   | -3  |     4
 *    0 |  2   |   3   | -4 +  1 + 2 = -1  |     2     | closer, left++   | -1  |     2
 *    1 |  2   |   3   | -1 +  1 + 2 =  2  |     1     | closer, right--  |  2  |     1
 *
 *   Returns 2. (i stops at n - 3 = 1.)
 *
 * EDGE CASES
 *   [0,0,0], target = 1        -> 0     only one triplet
 *   [0,0,0], target = 0        -> 0     exact match, returns right away
 *   [1,1,1,0], target = -100   -> 2     smallest possible sum 0 + 1 + 1
 *   [1,1,1,0], target = 100    -> 3     largest possible sum 1 + 1 + 1
 *   [1,2], target = 3          -> crashes on nums[2]; LeetCode guarantees n >= 3
 *
 * COMMON MISTAKES
 *   - Starting difference at 0: no sum is ever "closer", so res never changes.
 *     Start difference at Integer.MAX_VALUE (or at the distance of the first triplet).
 *   - Comparing sums instead of distances: keep |sum - target|, not the sum itself.
 *   - Moving the pointers by the distance instead of by sum < target: the sign decides the direction.
 *   - Forgetting to sort first: the two-pointer moves only make sense on sorted data.
 *
 * COMPLEXITY
 *   O(n^2) time (n fixed numbers, each with an O(n) two-pointer scan), O(1) extra space
 *   apart from the sort.
 *
 * RELATED
 *   LeetCode 15 (ThreeSum, FAQ/): same sort + fix + two pointers, but collects every exact
 *     zero-sum triplet and skips duplicates.
 *   LeetCode 15 (ThreeSum, TwoPointers/): the same 3Sum problem, using a HashSet for duplicates.
 *   LeetCode 167 (TwoSum, TwoPointers/): the inner two-pointer loop on its own.
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        int difference = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == target) {
                    return target;
                }

                int diff = Math.abs(sum - target);
                if (diff < difference) {
                    difference = diff;
                    res = sum;
                }

                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSumClosest solver = new ThreeSumClosest();

        System.out.println(solver.threeSumClosest(new int[]{-1, 2, 1, -4}, 1)); // 2
        System.out.println(solver.threeSumClosest(new int[]{0, 0, 0}, 1));      // 0
    }
}

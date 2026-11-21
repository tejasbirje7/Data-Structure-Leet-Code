/**
 * LeetCode 198. House Robber
 * https://leetcode.com/problems/house-robber/
 *
 * PROBLEM
 *   - Input: nums[i] = money in house i, houses in a row, all values >= 0, length >= 1.
 *   - Return: the most money you can rob without robbing two adjacent houses.
 *
 * EXAMPLE
 *   Input: nums = [2,7,9,3,1]
 *   Output: 12
 *   Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 *     Total amount you can rob = 2 + 9 + 1 = 12.
 *
 * HOW TO THINK
 *   - Clue: "maximum" + a choice at each item that blocks its neighbour -> take/skip DP.
 *   - Technique: best(i) = max(skip house i -> best(i-1), take house i -> best(i-2) + nums[i]).
 *     Only the last two best values are needed.
 *   - Rule that stays true: before house i, prev1 = best up to house i-1, prev2 = best up to house i-2.
 *   - Decision: prev2 + x > prev1 -> take house i.
 *               otherwise         -> skip it and keep prev1.
 *   - Return: prev1 after the last house.
 *
 * STEPS
 *   1. prev2 = 0, prev1 = 0 (nothing robbed yet).
 *   2. Loop over each house value x.
 *   3. cur = max(prev1, prev2 + x); prev2 = prev1; prev1 = cur.
 *   4. Return prev1.
 *
 * DRY RUN   input = [2,7,9,3,1]   (prev2 and prev1 are after the step)
 *
 *    x | take = prev2 + x | skip = prev1 | action | prev2 | prev1
 *   ---+------------------+--------------+--------+-------+-------
 *    - | init             |      -       |   -    |   0   |   0
 *    2 |    0 + 2 = 2     |      0       |  take  |   0   |   2
 *    7 |    0 + 7 = 7     |      2       |  take  |   2   |   7
 *    9 |    2 + 9 = 11    |      7       |  take  |   7   |  11
 *    3 |    7 + 3 = 10    |     11       |  skip  |  11   |  11
 *    1 |   11 + 1 = 12    |     11       |  take  |  11   |  12
 *
 *   Returns 12: houses with 2, 9 and 1.
 *
 * EDGE CASES
 *   [5]          -> 5   one house: rob it
 *   [2,1]        -> 2   pick the larger of two neighbours
 *   [2,1,1,2]    -> 4   rob the two ends; skipping two houses in a row is allowed
 *   [0,0,0]      -> 0   nothing to take
 *
 * COMMON MISTAKES
 *   - Summing all even indexes vs all odd indexes: fails on [2,1,1,2] (gives 3, answer is 4).
 *   - Greedy "take the biggest house first": a big house can block two bigger neighbours.
 *   - Updating prev2 before computing cur: the take option then uses the wrong value.
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 70 (ClimbingStairs, DynamicProgramming/): same two-variable rolling dp, but adds instead of taking a max.
 *   LeetCode 53 (MaximumSubarray, DynamicProgramming/): another one-pass dp with a "take or restart" choice.
 */
public class HouseRobber {

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {
        int prev2 = 0;
        int prev1 = 0;
        for (int x : nums) {
            int cur = Math.max(prev1, prev2 + x);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

}

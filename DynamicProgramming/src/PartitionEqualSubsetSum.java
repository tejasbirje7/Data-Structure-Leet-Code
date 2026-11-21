/**
 * LeetCode 416. Partition Equal Subset Sum
 * https://leetcode.com/problems/partition-equal-subset-sum/
 *
 * PROBLEM
 *   - Input: an array nums of positive integers (length 1 to 200, values 1 to 100).
 *   - Return: true if nums can be split into two groups with equal sums.
 *   - Every number goes into exactly one group.
 *
 * EXAMPLE
 *   Input: nums = [1,5,11,5]
 *   Output: true
 *   Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * HOW TO THINK
 *   - Clue: two equal halves -> each half must sum to total / 2. So: "is there a subset that sums to target?"
 *   - Technique: 0/1 knapsack on sums. dp[t] = true if some subset of the numbers seen so far sums to t.
 *     Small values (sum at most 20000) make a table over sums cheap.
 *   - Rule that stays true: dp[0] = true (empty subset). After processing a number, dp holds every
 *     reachable sum using each number at most once.
 *   - Decision: for each num, for t from target DOWN to num: dp[t] = dp[t] || dp[t - num].
 *     Going down means dp[t - num] still holds the value from before this num, so num is used once.
 *   - Return: dp[target]. If the total is odd, return false right away.
 *
 * STEPS
 *   1. sum = total of nums; if sum is odd return false; target = sum / 2.
 *   2. dp = boolean[target + 1]; dp[0] = true.
 *   3. For each num, loop t from target down to num and set dp[t] |= dp[t - num].
 *   4. Return dp[target].
 *
 * DRY RUN   input = nums = [1,5,11,5]   sum = 22, target = 11   (true sums are after the step)
 *
 *    step | num | new sums made (t <- t - num)    | true sums in dp after the step
 *   ------+-----+---------------------------------+-------------------------------
 *     0   |  -  | -                               | {0}
 *     1   |  1  | 1 <- 0                          | {0, 1}
 *     2   |  5  | 6 <- 1, 5 <- 0                  | {0, 1, 5, 6}
 *     3   | 11  | 11 <- 0                         | {0, 1, 5, 6, 11}
 *     4   |  5  | 10 <- 5 (11, 6, 5 already true) | {0, 1, 5, 6, 10, 11}
 *
 *   Returns true (dp[11]): [11] and [1, 5, 5].
 *
 * EDGE CASES
 *   [1,2,3,5]              -> false  sum 11 is odd
 *   [1]                    -> false  odd sum, a single number can't be split
 *   [1,2,5]                -> false  sum 8, but no subset makes 4
 *   [2,2]                  -> true   one number per group
 *   [100,100,100,100]      -> true   200 + 200
 *
 * COMMON MISTAKES
 *   - Looping t upward: dp[t - num] may already include num, so num gets reused.
 *     [1,2,5] would wrongly return true (2 + 2 = 4).
 *   - Forgetting the odd-sum check: sum / 2 rounds down and can give a false true.
 *   - Trying every subset with recursion and no memo: 2^200 subsets.
 *
 * COMPLEXITY
 *   O(n * target) time, O(target) extra space. target is at most 200 * 100 / 2 = 10000.
 *
 * RELATED
 *   LeetCode 322 (CoinChange, DynamicProgramming/): dp over sums too, but each coin can be reused, so t goes up.
 *   LeetCode 78 (Subsets, Backtracking/): lists every subset instead of asking if one hits a sum.
 *   LeetCode 40 (CombinationSum2, Backtracking/): each number used once, lists the subsets that hit the target.
 */
public class PartitionEqualSubsetSum {

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
    }

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int t = target; t >= num; t--) {
                dp[t] = dp[t] || dp[t - num];
            }
        }
        return dp[target];
    }

}

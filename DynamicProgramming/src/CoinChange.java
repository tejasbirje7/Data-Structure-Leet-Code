import java.util.Arrays;

/**
 * LeetCode 322. Coin Change
 * https://leetcode.com/problems/coin-change/
 *
 * PROBLEM
 *   - Input: coin values coins (each coin can be used any number of times) and a target amount.
 *   - Return: the fewest coins that add up to amount, or -1 if it cannot be made.
 *   - amount can be 0 (answer 0).
 *
 * EXAMPLE
 *   Input: coins = [1,2,5], amount = 11
 *   Output: 3
 *   Explanation: 11 = 5 + 5 + 1
 *
 * HOW TO THINK
 *   - Clue: "fewest" + unlimited reuse + greedy fails (coins [1,3,4], amount 6) -> DP over amounts.
 *   - Technique: dp[a] = fewest coins to make amount a.
 *     The last coin used is some c, so dp[a] = 1 + min over coins of dp[a - c].
 *   - Rule that stays true: dp[0] = 0; dp[x] is final for every x < a when a is processed.
 *     "Impossible" is stored as amount + 1, which is more than any real answer.
 *   - Decision: for each coin c, c <= a -> dp[a] = min(dp[a], dp[a - c] + 1).
 *               c > a                 -> skip (the coin is too big).
 *   - Return: dp[amount], or -1 if it is still amount + 1.
 *
 * STEPS
 *   1. dp = int[amount + 1] filled with amount + 1; dp[0] = 0.
 *   2. Loop a from 1 to amount; inner loop over each coin c.
 *   3. If c <= a, dp[a] = min(dp[a], dp[a - c] + 1).
 *   4. Return dp[amount] > amount ? -1 : dp[amount].
 *
 * DRY RUN   input = coins = [1,3,4], amount = 6   ("-" = coin too big; dp[a] is after the step)
 *
 *    a | via 1: dp[a-1]+1 | via 3: dp[a-3]+1 | via 4: dp[a-4]+1 | dp[a]
 *   ---+------------------+------------------+------------------+-------
 *    0 | base case        |                  |                  |   0
 *    1 |    dp[0]+1 = 1   |        -         |        -         |   1
 *    2 |    dp[1]+1 = 2   |        -         |        -         |   2
 *    3 |    dp[2]+1 = 3   |    dp[0]+1 = 1   |        -         |   1
 *    4 |    dp[3]+1 = 2   |    dp[1]+1 = 2   |    dp[0]+1 = 1   |   1
 *    5 |    dp[4]+1 = 2   |    dp[2]+1 = 3   |    dp[1]+1 = 2   |   2
 *    6 |    dp[5]+1 = 3   |    dp[3]+1 = 2   |    dp[2]+1 = 3   |   2
 *
 *   Returns 2: 3 + 3. (Greedy would take 4 + 1 + 1 = 3 coins.)
 *
 * EDGE CASES
 *   [2], 3            -> -1  odd amount from even coins
 *   [1], 0            -> 0   nothing to make
 *   [1,2,5], 11       -> 3   5 + 5 + 1
 *   [2147483647], 2   -> -1  coin bigger than the amount is skipped, no overflow
 *
 * COMMON MISTAKES
 *   - Using Integer.MAX_VALUE as "impossible": dp[a - c] + 1 overflows to a negative number. Use amount + 1.
 *   - Greedy biggest-coin-first: fails on [1,3,4], amount 6.
 *   - Forgetting to turn the "impossible" value into -1 at the end.
 *
 * COMPLEXITY
 *   O(amount * coins) time, O(amount) extra space.
 *
 * RELATED
 *   LeetCode 39 (CombinationsSum, Backtracking/): lists every combination that hits the target instead of the fewest coins.
 *   LeetCode 416 (PartitionEqualSubsetSum, DynamicProgramming/): dp over sums, but each number is used at most once.
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1, 3, 4};
        System.out.println(coinChange(coins, 6));
    }

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int a = 1; a <= amount; a++) {
            for (int c : coins) {
                if (c <= a) {
                    dp[a] = Math.min(dp[a], dp[a - c] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}

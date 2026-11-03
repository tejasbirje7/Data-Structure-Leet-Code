/**
 * LeetCode 122. Best Time to Buy and Sell Stock II
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * PROBLEM
 *   - Input: prices[i] = the stock price on day i.
 *   - Task: buy and sell as many times as you like, holding at most one share at a time.
 *     You may buy and sell on the same day.
 *   - Return: the maximum total profit.
 *
 * EXAMPLE
 *   Input: prices = [7,1,5,3,6,4]
 *   Output: 7
 *   Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 *   Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 *   Total profit is 4 + 3 = 7.
 *
 * HOW TO THINK
 *   - Clue: unlimited transactions, so there's no need to choose one best buy and sell pair.
 *   - Technique: greedy, the "graph" idea. Draw the prices as a line graph:
 *     the best profit is the total height of every upward step.
 *   - Why it works: a long rise from day a to day b equals the sum of the daily rises in between,
 *     so collecting every daily rise is never worse than any other plan.
 *   - Rule that stays true: profit = the best total using days 0..i.
 *   - Decision: prices[i] > prices[i-1] -> price went up: add the difference.
 *               otherwise               -> price fell or stayed: skip.
 *   - Return: the total profit.
 *
 * STEPS
 *   1. profit = 0.
 *   2. Loop i from day 1 to the last day.
 *   3. If prices[i] > prices[i-1], add prices[i] - prices[i-1] to profit.
 *   4. Return profit.
 *
 * DRY RUN   prices = [7,1,5,3,6,4]   (profit is the value after the step)
 *
 *    i | prices[i-1] | prices[i] | action      | profit
 *   ---+-------------+-----------+-------------+--------
 *    1 |      7      |     1     | down, skip  |   0
 *    2 |      1      |     5     | up, add 4   |   4
 *    3 |      5      |     3     | down, skip  |   4
 *    4 |      3      |     6     | up, add 3   |   7
 *    5 |      6      |     4     | down, skip  |   7
 *
 *   Returns 7: buy at 1, sell at 5, then buy at 3, sell at 6.
 *
 * EDGE CASES
 *   [5]            -> 0   one day, the loop never runs
 *   [1,2,3,4,5]    -> 4   one long rise = 1 + 1 + 1 + 1 (same as buying at 1, selling at 5)
 *   [7,6,4,3,1]    -> 0   prices only fall, nothing to add
 *   [3,3]          -> 0   no change, nothing to add
 *
 * COMMON MISTAKES
 *   - Starting the loop at i = 0: prices[i-1] goes out of bounds. Start at i = 1.
 *   - Adding negative differences: only add when the price went up.
 *   - Reusing the LeetCode 121 single-pair approach: it gives 5 here instead of 7.
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 121 (MaxProfit, ArraysAndStrings/): only one transaction allowed.
 *     Track the best single buy and sell pair instead of adding up every rise.
 */
public class MaxProfit2 {

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }
    public static int maxProfit(int[] prices) {
        int profit = 0;
        for(int i=1; i < prices.length; i++) {
            if(prices[i] > prices[i-1]) {
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }
}

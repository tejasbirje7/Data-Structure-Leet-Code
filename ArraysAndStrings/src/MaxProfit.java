/**
 * LeetCode 121. Best Time to Buy and Sell Stock
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * PROBLEM
 *   - Input: prices[i] = the stock price on day i.
 *   - Task: pick one day to buy and a later day to sell (one transaction only).
 *   - Return: the maximum profit, or 0 if no profit is possible.
 *
 * EXAMPLE
 *   Input: prices = [7,1,5,3,6,4]
 *   Output: 5
 *   Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *   Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 *
 * HOW TO THINK
 *   - Clue: "buy before you sell" means the sell day must come after the buy day.
 *     So for each day, the best sell price is the highest price from that day onward.
 *   - Technique: one pass from right to left, keeping a running maximum (maxRight).
 *     No need to check every pair of days.
 *   - Rule that stays true: maxRight = the highest price from day i to the last day.
 *   - Decision: prices[i] > maxRight -> new highest, update maxRight (profit today is 0).
 *               otherwise           -> profit = maxRight - prices[i]; keep it if it's the best.
 *   - Return: the best profit seen; it starts at 0, so "no profit" returns 0.
 *   - Same idea left to right: keep the lowest price so far and sell today.
 *
 * STEPS
 *   1. maxRight = last price, maxProfit = 0.
 *   2. Loop i from the second-last day down to day 0.
 *   3. If prices[i] > maxRight, set maxRight = prices[i].
 *   4. profit = maxRight - prices[i]; maxProfit = max(maxProfit, profit).
 *   5. Return maxProfit.
 *
 * DRY RUN   prices = [7,1,5,3,6,4]   (maxRight and maxProfit are values after the step)
 *
 *     i  | prices[i] | action           | maxRight | profit | maxProfit
 *   -----+-----------+------------------+----------+--------+-----------
 *   start|     4     | set from last    |    4     |   -    |     0
 *     4  |     6     | new highest      |    6     |   0    |     0
 *     3  |     3     | sell at 6        |    6     |   3    |     3
 *     2  |     5     | sell at 6        |    6     |   1    |     3
 *     1  |     1     | sell at 6        |    6     |   5    |     5
 *     0  |     7     | new highest      |    7     |   0    |     5
 *
 *   Returns 5: buy at 1 (day 2), sell at 6 (day 5).
 *
 * EDGE CASES
 *   [5]            -> 0   one day, the loop never runs
 *   [7,6,4,3,1]    -> 0   prices only fall; every day is a new highest, profit is always 0
 *   [1,2,4]        -> 3   buy on the first day, sell on the last
 *   [3,3,3]        -> 0   no price ever goes up
 *   []             -> crashes on prices[-1]; LeetCode guarantees at least one price
 *
 * COMMON MISTAKES
 *   - Returning max(prices) - min(prices): the lowest price may come after the highest.
 *     [7,6,4,3,1] would give 6 instead of 0.
 *   - Starting maxProfit below 0: the answer must be 0 when no profit is possible.
 *   - Checking every pair of days: O(n^2), too slow for 10^5 prices.
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 122 (MaxProfit2, ArraysAndStrings/): unlimited transactions instead of one.
 *     Add up every day-to-day rise instead of finding a single best pair.
 */
public class MaxProfit {

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
//        int[] prices = {1,2,4};
//        int[] prices = {7,6,4,3,1};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int maxRight = prices[prices.length - 1];
        int maxProfit = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            if(prices[i] > maxRight) {
                maxRight = prices[i];
            }
            int profit = maxRight - prices[i];
            maxProfit = Math.max(profit,maxProfit);
        }
        return maxProfit;
    }
}

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
 *   - Clue: "buy before you sell" means the buy day must come before the sell day.
 *     So if you sell today, the best buy price is the lowest price seen so far.
 *   - Technique: one pass from left to right, keeping a running minimum (minPrice).
 *     No need to check every pair of days.
 *   - Rule that stays true: minPrice = the lowest price from day 0 up to today.
 *   - Decision: price < minPrice -> new lowest, update minPrice (profit today is 0).
 *               every day        -> profit = price - minPrice (sell today); keep it if it's the best.
 *   - Return: the best profit seen; it starts at 0, so "no profit" returns 0.
 *   - Same idea right to left: keep the highest price still ahead and buy today.
 *
 * STEPS
 *   1. minPrice = Integer.MAX_VALUE (so the first price becomes the minimum), maxProfit = 0.
 *   2. Loop over each price from the first day to the last.
 *   3. If price < minPrice, set minPrice = price.
 *   4. If price - minPrice > maxProfit, set maxProfit = price - minPrice.
 *   5. Return maxProfit.
 *
 * DRY RUN   prices = [7,1,5,3,6,4]   (minPrice and maxProfit are values after the step)
 *
 *    day | price | action          | minPrice | price - minPrice | maxProfit
 *   -----+-------+-----------------+----------+------------------+-----------
 *     0  |   7   | new lowest      |    7     |        0         |     0
 *     1  |   1   | new lowest      |    1     |        0         |     0
 *     2  |   5   | sell, new best  |    1     |        4         |     4
 *     3  |   3   | sell, not best  |    1     |        2         |     4
 *     4  |   6   | sell, new best  |    1     |        5         |     5
 *     5  |   4   | sell, not best  |    1     |        3         |     5
 *
 *   Returns 5: buy at 1 (day 2), sell at 6 (day 5).
 *
 * EDGE CASES
 *   [5]            -> 0   one day: it becomes minPrice, profit 0
 *   [7,6,4,3,1]    -> 0   prices only fall; every day is a new lowest, profit is always 0
 *   [1,2,4]        -> 3   buy on the first day, sell on the last
 *   [3,3,3]        -> 0   no price ever goes up
 *   []             -> 0   the loop never runs (LeetCode guarantees at least one price anyway)
 *
 * COMMON MISTAKES
 *   - Returning max(prices) - min(prices): the lowest price may come after the highest.
 *     [7,6,4,3,1] would give 6 instead of 0.
 *   - Starting minPrice at 0: no price is ever lower, so you'd return the highest price as profit.
 *     Start at Integer.MAX_VALUE (or prices[0]).
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
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if(price < minPrice) {
                minPrice = price;
            }

            if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }
}

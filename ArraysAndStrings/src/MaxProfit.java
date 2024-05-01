public class MaxProfit {

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
//        int[] prices = {1,2,4};
//        int[] prices = {7,6,4,3,1};
        System.out.println(maxProfit(prices));
    }

    /**
     * Input: prices = [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
     */
    public static int maxProfit(int[] prices) {
        int maxRight = prices[prices.length-1];
        int maxProfit = 0;
        for (int i = prices.length-2; i >= 0; i--) {
            if(prices[i] > maxRight) {
                maxRight = prices[i];
            }
            int profit = maxRight - prices[i];
            if(profit > maxProfit) {
                maxProfit = profit;
            }
        }
        return maxProfit;
    }
}

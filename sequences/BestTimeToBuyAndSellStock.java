package leet75.sequences;

/**
 * Problem: Best Time to Buy and Sell Stock
 * 
 * Description:
 * Given an array prices where prices[i] is the price of a given stock on the ith day,
 * maximize your profit by choosing a single day to buy and a different day in the future to sell.
 * If you cannot achieve any profit, return 0.
 * 
 * Example:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5
 * 
 * Approach:
 * 1. Track minimum price seen so far (buy price)
 * 2. For each price, calculate potential profit (current price - min price)
 * 3. Update maximum profit if current profit is larger
 * 4. Continue this process for all prices
 * 
 * Time Complexity: O(n) - Single pass through the array
 * Space Complexity: O(1) - Only using two variables
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        // Initialize minimum price and maximum profit
        int minPrice = prices[0];
        int maxProfit = 0;
        
        // Iterate through prices starting from second day
        for (int i = 1; i < prices.length; i++) {
            // Update minimum price if we find a lower price
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                // Calculate potential profit and update max profit if higher
                int currentProfit = prices[i] - minPrice;
                maxProfit = Math.max(maxProfit, currentProfit);
            }
        }
        
        return maxProfit;
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock solution = new BestTimeToBuyAndSellStock();
        
        // Test case 1: Normal case
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Test case 1 (should be 5): " + 
                          solution.maxProfit(prices1));
        
        // Test case 2: Decreasing prices
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Test case 2 (should be 0): " + 
                          solution.maxProfit(prices2));
        
        // Test case 3: All same prices
        int[] prices3 = {1, 1, 1, 1};
        System.out.println("Test case 3 (should be 0): " + 
                          solution.maxProfit(prices3));
    }
}
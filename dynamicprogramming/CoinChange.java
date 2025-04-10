package leet75.dynamicprogramming;

import java.util.Arrays;

/**
 * Problem: Coin Change
 * 
 * Given an array of coins representing different denominations and a total amount,
 * find the fewest number of coins needed to make up that amount.
 * Return -1 if the amount cannot be made up by any combination of the coins.
 * 
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * 
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1 (impossible to make 3 with given coins)
 * 
 * Approach:
 * 1. Use bottom-up dynamic programming
 * 2. Create dp array where dp[i] represents minimum coins needed for amount i
 * 3. For each amount from 1 to target:
 *    - Try each coin denomination
 *    - Update dp[amount] if using current coin gives better result
 * 4. Initialize dp array with amount + 1 (impossible value)
 * 5. Base case: dp[0] = 0 (need 0 coins to make amount 0)
 * 
 * Time Complexity: O(amount * number of coins) 
 * - We need to try each coin for each amount from 1 to target
 * 
 * Space Complexity: O(amount) 
 * - We need to store minimum coins needed for each amount from 0 to target
 */
public class CoinChange {
    
    public int coinChange(int[] coins, int amount) {
        // dp[i] represents minimum coins needed to make amount i
        int[] dp = new int[amount + 1];
        
        // Fill array with amount + 1 (impossible value as we can't need more coins than amount)
        Arrays.fill(dp, amount + 1);
        
        // Base case: 0 coins needed to make amount 0
        dp[0] = 0;
        
        // For each amount from 1 to target
        for (int i = 1; i <= amount; i++) {
            // Try each coin
            for (int coin : coins) {
                // If we can use this coin (amount - coin >= 0)
                if (i >= coin) {
                    // Update minimum coins needed if using current coin gives better result
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        // Return -1 if amount can't be made up, otherwise return minimum coins needed
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    // Test the solution
    public static void main(String[] args) {
        CoinChange solution = new CoinChange();
        
        int[] coins1 = {1, 2, 5};
        System.out.println("Minimum coins needed for amount 11: " + 
            solution.coinChange(coins1, 11)); // Should output 3
        
        int[] coins2 = {2};
        System.out.println("Minimum coins needed for amount 3: " + 
            solution.coinChange(coins2, 3));  // Should output -1
        
        int[] coins3 = {1, 5, 10, 25};
        System.out.println("Minimum coins needed for amount 30: " + 
            solution.coinChange(coins3, 30)); // Should output 3 (25 + 5)
    }
}

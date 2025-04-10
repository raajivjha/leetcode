package leet75.dynamicprogramming;

/**
 * Problem: House Robber
 * 
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle.
 * You cannot rob adjacent houses, as they are connected to security system.
 * 
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * 
 * Example 2:
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * 
 * Approach:
 * 1. Use DP to track maximum money at each house
 * 2. For each house i, we have two options:
 *    a) Rob this house: add current money to max money from i-2
 *    b) Skip this house: keep max money from i-1
 * 3. Take maximum of these two options
 * 4. Can optimize space by only keeping last two max values
 * 
 * Time Complexity: O(n) where n is the number of houses
 * Space Complexity: O(1) as we only store two variables
 */
public class HouseRobber {
    
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        // Instead of array, just keep track of last two maximum values
        int twoHousesBack = nums[0];  // max money if we rob up to two houses back
        int oneHouseBack = Math.max(nums[0], nums[1]);  // max money if we rob up to previous house
        int currentMax = oneHouseBack;
        
        // For each house starting from third house
        for (int i = 2; i < nums.length; i++) {
            // Current maximum is either:
            // 1. Rob current house + max money from two houses back
            // 2. Skip current house and keep max money from previous house
            currentMax = Math.max(twoHousesBack + nums[i], oneHouseBack);
            
            // Update previous maximums for next iteration
            twoHousesBack = oneHouseBack;
            oneHouseBack = currentMax;
        }
        
        return currentMax;
    }
    
    // Test the solution
    public static void main(String[] args) {
        HouseRobber solution = new HouseRobber();
        
        int[] houses1 = {1, 2, 3, 1};
        System.out.println("Maximum money from houses1: " + 
            solution.rob(houses1));  // Should output 4
        
        int[] houses2 = {2, 7, 9, 3, 1};
        System.out.println("Maximum money from houses2: " + 
            solution.rob(houses2));  // Should output 12
        
        int[] houses3 = {2, 1, 1, 2};
        System.out.println("Maximum money from houses3: " + 
            solution.rob(houses3));  // Should output 4
    }
}
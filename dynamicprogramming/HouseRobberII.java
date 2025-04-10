package leet75.dynamicprogramming;

/**
 * Problem: House Robber II
 * 
 * Description:
 * You are a professional robber planning to rob houses along a street arranged in a circle.
 * Adjacent houses have security systems connected, and it will automatically contact the police
 * if two adjacent houses were broken into on the same night.
 * Given an array of integers representing money in each house,
 * return the maximum amount of money you can rob without alerting the police.
 * 
 * Example 1:
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * 
 * Example 2:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * 
 * Approach:
 * 1. Since houses are in a circle, we cannot rob both first and last house
 * 2. Break into two subproblems:
 *    a) Rob houses from index 0 to n-2 (excluding last house)
 *    b) Rob houses from index 1 to n-1 (excluding first house)
 * 3. Take maximum of these two scenarios
 * 4. Each subproblem can be solved using the original House Robber logic
 * 
 * Time Complexity: O(n) - we traverse the array twice
 * Space Complexity: O(1) - we only use a few variables
 */
public class HouseRobberII {
    
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        // Get maximum amount if we skip the last house
        int maxExcludingLast = robRange(nums, 0, nums.length - 2);
        // Get maximum amount if we skip the first house
        int maxExcludingFirst = robRange(nums, 1, nums.length - 1);
        
        // Return the maximum of the two scenarios
        return Math.max(maxExcludingFirst, maxExcludingLast);
    }
    
    // Helper method to solve the original House Robber problem for a range of houses
    private int robRange(int[] nums, int start, int end) {
        int twoHousesBack = 0;  // max money if we rob up to two houses back
        int oneHouseBack = nums[start];  // max money if we rob up to previous house
        int currentMax = oneHouseBack;
        
        // For each house in the range
        for (int i = start + 1; i <= end; i++) {
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
        HouseRobberII solution = new HouseRobberII();
        
        int[] houses1 = {2,3,2};
        System.out.println("Maximum money from houses1: " + 
            solution.rob(houses1));  // Should output 3
        
        int[] houses2 = {1,2,3,1};
        System.out.println("Maximum money from houses2: " + 
            solution.rob(houses2));  // Should output 4
        
        int[] houses3 = {1,2,3};
        System.out.println("Maximum money from houses3: " + 
            solution.rob(houses3));  // Should output 3
    }
}

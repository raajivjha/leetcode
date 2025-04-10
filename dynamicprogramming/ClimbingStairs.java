package leet75.dynamicprogramming;

/**
 * Problem: Climbing Stairs
 * 
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. 
 * Return the number of distinct ways you can climb to the top.
 * 
 * Example 1:
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * 
 * Example 2:
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * 
 * Approach:
 * 1. This is essentially a Fibonacci sequence problem
 * 2. For any step n, we can reach it from either (n-1) or (n-2)
 * 3. Therefore, dp[n] = dp[n-1] + dp[n-2]
 * 4. We can optimize space by just keeping track of the last two numbers
 * 
 * Time Complexity: O(n) - we need to calculate for each step up to n
 * Space Complexity: O(1) - we only store two variables
 */
public class ClimbingStairs {
    
    public int climbStairs(int n) {
        if (n <= 1) return 1;
        
        // Initialize first two numbers
        int oneStepBefore = 1;  // ways to climb 1 step
        int twoStepsBefore = 1; // ways to climb 0 steps
        int allWays = 0;
        
        // Calculate ways for each step using previous results
        for (int i = 2; i <= n; i++) {
            allWays = oneStepBefore + twoStepsBefore;
            twoStepsBefore = oneStepBefore;
            oneStepBefore = allWays;
        }
        
        return oneStepBefore;
    }
    
    // Test the solution
    public static void main(String[] args) {
        ClimbingStairs solution = new ClimbingStairs();
        System.out.println("Ways to climb 2 stairs: " + solution.climbStairs(2)); // Should output 2
        System.out.println("Ways to climb 3 stairs: " + solution.climbStairs(3)); // Should output 3
        System.out.println("Ways to climb 4 stairs: " + solution.climbStairs(4)); // Should output 5
    }
}
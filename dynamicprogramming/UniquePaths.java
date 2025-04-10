package leet75.dynamicprogramming;

/**
 * Problem: Unique Paths
 * 
 * Description:
 * A robot is located at the top-left corner of an m x n grid.
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid.
 * How many possible unique paths are there?
 * 
 * Example 1:
 * Input: m = 3, n = 7
 * Output: 28
 * 
 * Example 2:
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 * 
 * Approach:
 * 1. Use a 2D DP array where dp[i][j] represents the number of unique paths to cell (i, j).
 * 2. Base case: dp[0][j] = 1 and dp[i][0] = 1 (only one way to reach cells in the first row or column).
 * 3. Transition: dp[i][j] = dp[i-1][j] + dp[i][j-1] (paths from top + paths from left).
 * 4. Return dp[m-1][n-1] as the result.
 * 
 * Time Complexity: O(m * n) - we fill the entire grid.
 * Space Complexity: O(m * n) - we use a 2D DP array.
 */
public class UniquePaths {
    
    public int uniquePaths(int m, int n) {
        // Create a 2D DP array
        int[][] dp = new int[m][n];
        
        // Initialize the first row and first column
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        
        // Fill the DP array
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        
        return dp[m - 1][n - 1];
    }
    
    // Test the solution
    public static void main(String[] args) {
        UniquePaths solution = new UniquePaths();
        
        System.out.println("Unique paths for a 3x7 grid: " + solution.uniquePaths(3, 7)); // Should output 28
        System.out.println("Unique paths for a 3x2 grid: " + solution.uniquePaths(3, 2)); // Should output 3
        System.out.println("Unique paths for a 7x3 grid: " + solution.uniquePaths(7, 3)); // Should output 28
    }
}
package leet75.dynamicprogramming;

/**
 * Problem: Decode Ways
 * 
 * Description:
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * 'A' -> 1, 'B' -> 2, ..., 'Z' -> 26.
 * Given a string s containing only digits, return the number of ways to decode it.
 * 
 * Example 1:
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" can be decoded as "AB" (1 2) or "L" (12).
 * 
 * Example 2:
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" can be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * 
 * Approach:
 * 1. Use Dynamic Programming to calculate the number of ways to decode the string.
 * 2. dp[i] represents the number of ways to decode the substring s[0..i-1].
 * 3. Transition:
 *    a) If s[i-1] is valid (1-9), add dp[i-1] to dp[i].
 *    b) If s[i-2..i-1] is valid (10-26), add dp[i-2] to dp[i].
 * 4. Base cases:
 *    a) dp[0] = 1 (empty string has one way to decode).
 *    b) dp[1] = 1 if s[0] is valid, otherwise 0.
 * 
 * Time Complexity: O(n) - we iterate through the string once.
 * Space Complexity: O(n) - we use a dp array of size n+1.
 */
public class DecodeWays {
    
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int n = s.length();
        int[] dp = new int[n + 1];
        
        // Base cases
        dp[0] = 1; // Empty string
        dp[1] = s.charAt(0) != '0' ? 1 : 0; // First character must be valid
        
        // Fill dp array
        for (int i = 2; i <= n; i++) {
            // Check single digit
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            // Check two digits
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        
        return dp[n];
    }
    
    // Test the solution
    public static void main(String[] args) {
        DecodeWays solution = new DecodeWays();
        
        String s1 = "12";
        System.out.println("Number of ways to decode '12': " + solution.numDecodings(s1)); // Should output 2
        
        String s2 = "226";
        System.out.println("Number of ways to decode '226': " + solution.numDecodings(s2)); // Should output 3
        
        String s3 = "06";
        System.out.println("Number of ways to decode '06': " + solution.numDecodings(s3)); // Should output 0
    }
}
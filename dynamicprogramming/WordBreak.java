package leet75.dynamicprogramming;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

/**
 * Problem: Word Break
 * 
 * Description:
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into
 * a space-separated sequence of one or more dictionary words.
 * 
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * 
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * 
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 * 
 * Approach:
 * 1. Use Dynamic Programming to determine if the string can be segmented.
 * 2. dp[i] represents whether the substring s[0..i-1] can be segmented into words from the dictionary.
 * 3. Transition:
 *    a) For each index i, check all substrings s[j..i-1] (where 0 <= j < i).
 *    b) If dp[j] is true and s[j..i-1] is in the dictionary, set dp[i] = true.
 * 4. Base case: dp[0] = true (empty string can be segmented).
 * 
 * Time Complexity: O(n^2) - we check all substrings for each index.
 * Space Complexity: O(n) - we use a dp array of size n+1.
 */
public class WordBreak {
    
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict); // Convert list to set for O(1) lookups
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        
        // Base case
        dp[0] = true;
        
        // Fill dp array
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
    
    // Test the solution
    public static void main(String[] args) {
        WordBreak solution = new WordBreak();
        
        List<String> wordDict1 = List.of("leet", "code");
        System.out.println("Can segment 'leetcode': " + solution.wordBreak("leetcode", wordDict1)); // Should output true
        
        List<String> wordDict2 = List.of("apple", "pen");
        System.out.println("Can segment 'applepenapple': " + solution.wordBreak("applepenapple", wordDict2)); // Should output true
        
        List<String> wordDict3 = List.of("cats", "dog", "sand", "and", "cat");
        System.out.println("Can segment 'catsandog': " + solution.wordBreak("catsandog", wordDict3)); // Should output false
    }
}
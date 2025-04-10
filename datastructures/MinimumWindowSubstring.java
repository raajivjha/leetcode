package leet75.datastructures;

import java.util.*;

/**
 * Problem: Minimum Window Substring
 * 
 * Description:
 * Given two strings s and t, return the minimum window substring of s such that every 
 * character in t (including duplicates) is included in the window. If there is no such 
 * substring, return the empty string "".
 * 
 * Example:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * 
 * Approach:
 * 1. Use sliding window technique with two pointers (left and right)
 * 2. Use two hash maps to keep track of:
 *    - Required characters and their frequencies from string t
 *    - Current window characters and their frequencies
 * 3. Use two variables to track:
 *    - Number of unique characters required (from t)
 *    - Number of unique characters currently matched
 * 4. Expand window until we find a valid substring
 * 5. Contract window from left to find minimum window
 * 
 * Time Complexity: O(|S| + |T|) where |S| and |T| are lengths of strings
 * Space Complexity: O(|S| + |T|) for the hash maps
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }

        // Create frequency map for pattern string t
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        int required = targetMap.size();
        int formed = 0;
        int[] ans = {-1, 0, 0}; // length, left, right
        
        // Current window character count
        Map<Character, Integer> windowMap = new HashMap<>();
        
        // Sliding window pointers
        int left = 0, right = 0;
        
        while (right < s.length()) {
            // Add one character from the right
            char c = s.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
            
            // Check if we have all occurrences of current character
            if (targetMap.containsKey(c) && windowMap.get(c).intValue() == targetMap.get(c).intValue()) {
                formed++;
            }
            
            // Try to contract window from left
            while (left <= right && formed == required) {
                c = s.charAt(left);
                
                // Save smallest window until now
                if (ans[0] == -1 || right - left + 1 < ans[0]) {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }
                
                // Remove leftmost character of window
                windowMap.put(c, windowMap.get(c) - 1);
                if (targetMap.containsKey(c) && windowMap.get(c).intValue() < targetMap.get(c).intValue()) {
                    formed--;
                }
                
                left++;
            }
            
            right++;
        }
        
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}
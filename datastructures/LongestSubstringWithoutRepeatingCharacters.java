package leet75.datastructures;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem: Longest Substring Without Repeating Characters
 * 
 * Description:
 * Given a string s, find the length of the longest substring without repeating characters.
 * 
 * Example:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * 
 * Approach (Sliding Window with HashSet):
 * 1. Use sliding window with HashSet to track unique characters
 * 2. For each character:
 *    - While current char exists in set, remove chars from window start
 *    - Add current char to set
 *    - Update max length if current window is longer
 * 3. Return maximum length found
 * 
 * Alternative Approach (Sliding Window with Array):
 * - Use array/map to store last position of each character
 * - Jump left pointer directly to position after last occurrence
 * 
 * Time Complexity: O(n) - each character is processed at most twice
 * Space Complexity: O(min(m,n)) where m is charset size and n is string length
 */
public class LongestSubstringWithoutRepeatingCharacters {
    
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Set<Character> charSet = new HashSet<>();
        int maxLength = 0;
        int left = 0;
        
        // Expand window with right pointer
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // Shrink window while we have duplicates
            while (charSet.contains(currentChar)) {
                charSet.remove(s.charAt(left));
                left++;
            }
            
            // Add current character and update max length
            charSet.add(currentChar);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    // Alternative solution using array to store last positions
    public int lengthOfLongestSubstringOptimized(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // Store index of last occurrence of each character
        int[] lastPos = new int[128];  // Assuming ASCII
        for (int i = 0; i < 128; i++) {
            lastPos[i] = -1;
        }
        
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // If character was seen before, move left pointer
            // to position after its last occurrence
            left = Math.max(left, lastPos[currentChar] + 1);
            
            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
            
            // Store current position
            lastPos[currentChar] = right;
        }
        
        return maxLength;
    }

    // Test method to verify both solutions
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters solution = 
            new LongestSubstringWithoutRepeatingCharacters();
        
        // Test case 1: Regular case
        String s1 = "abcabcbb";
        System.out.println("Test case 1 (should be 3):");
        System.out.println("HashSet solution: " + 
                          solution.lengthOfLongestSubstring(s1));
        System.out.println("Optimized solution: " + 
                          solution.lengthOfLongestSubstringOptimized(s1));
        
        // Test case 2: All same characters
        String s2 = "bbbbb";
        System.out.println("\nTest case 2 (should be 1):");
        System.out.println("HashSet solution: " + 
                          solution.lengthOfLongestSubstring(s2));
        System.out.println("Optimized solution: " + 
                          solution.lengthOfLongestSubstringOptimized(s2));
        
        // Test case 3: No repeating characters
        String s3 = "abcdef";
        System.out.println("\nTest case 3 (should be 6):");
        System.out.println("HashSet solution: " + 
                          solution.lengthOfLongestSubstring(s3));
        System.out.println("Optimized solution: " + 
                          solution.lengthOfLongestSubstringOptimized(s3));
        
        // Test case 4: Empty string
        String s4 = "";
        System.out.println("\nTest case 4 (should be 0):");
        System.out.println("HashSet solution: " + 
                          solution.lengthOfLongestSubstring(s4));
        System.out.println("Optimized solution: " + 
                          solution.lengthOfLongestSubstringOptimized(s4));
        
        // Test case 5: Complex pattern
        String s5 = "pwwkew";
        System.out.println("\nTest case 5 (should be 3):");
        System.out.println("HashSet solution: " + 
                          solution.lengthOfLongestSubstring(s5));
        System.out.println("Optimized solution: " + 
                          solution.lengthOfLongestSubstringOptimized(s5));
    }
}
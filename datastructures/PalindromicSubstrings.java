package leet75.datastructures;

/**
 * Problem: Palindromic Substrings
 * 
 * Description:
 * Given a string s, return the number of palindromic substrings in it.
 * A substring is a contiguous sequence of characters within the string.
 * A palindrome is a string that reads the same forward and backward.
 * 
 * Example:
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c"
 * 
 * Example 2:
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa"
 * 
 * Approach (Expand Around Center):
 * 1. For each position in string:
 *    - Treat it as center of odd-length palindromes
 *    - Treat it and next character as center of even-length palindromes
 * 2. Expand around each center while characters match
 * 3. Count all valid palindromes found
 * 
 * Time Complexity: O(n²) where n is length of string
 * Space Complexity: O(1) - constant extra space
 */
public class PalindromicSubstrings {
    
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int count = 0;
        
        // Check each center position
        for (int i = 0; i < s.length(); i++) {
            // Count odd length palindromes with current position as center
            count += expandAroundCenter(s, i, i);
            
            // Count even length palindromes with current position and next as centers
            count += expandAroundCenter(s, i, i + 1);
        }
        
        return count;
    }
    
    // Helper method to expand around center and count palindromes
    private int expandAroundCenter(String s, int left, int right) {
        int count = 0;
        
        // Expand while within bounds and characters match
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;  // Found a palindrome
            left--;   // Expand left
            right++;  // Expand right
        }
        
        return count;
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        PalindromicSubstrings solution = new PalindromicSubstrings();
        
        // Test case 1: Basic case
        String s1 = "abc";
        System.out.println("Test case 1 (should be 3): " + 
                          solution.countSubstrings(s1));
        
        // Test case 2: Multiple palindromes
        String s2 = "aaa";
        System.out.println("Test case 2 (should be 6): " + 
                          solution.countSubstrings(s2));
        
        // Test case 3: Even length palindrome
        String s3 = "abba";
        System.out.println("Test case 3 (should be 6): " + 
                          solution.countSubstrings(s3));
        
        // Test case 4: Single character
        String s4 = "a";
        System.out.println("Test case 4 (should be 1): " + 
                          solution.countSubstrings(s4));
        
        // Test case 5: Mixed palindromes
        String s5 = "abbaba";
        System.out.println("Test case 5 (should be 11): " + 
                          solution.countSubstrings(s5));
    }
    
    // Helper method to print all palindromic substrings (for debugging)
    private void printAllPalindromes(String s) {
        System.out.println("All palindromes in \"" + s + "\":");
        for (int i = 0; i < s.length(); i++) {
            // Odd length palindromes
            expandAndPrint(s, i, i);
            
            // Even length palindromes
            expandAndPrint(s, i, i + 1);
        }
    }
    
    private void expandAndPrint(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            System.out.println(s.substring(left, right + 1));
            left--;
            right++;
        }
    }
}
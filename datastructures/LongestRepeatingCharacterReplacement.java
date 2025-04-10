package leet75.datastructures;

/**
 * Problem: Longest Repeating Character Replacement
 * 
 * Description:
 * Given a string s and an integer k, you can choose any character of the string and change it to 
 * any other uppercase English character. You can perform this operation at most k times.
 * Return the length of the longest substring containing the same letter you can get after 
 * performing the above operations.
 * 
 * Example:
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * 
 * Approach (Sliding Window):
 * 1. Use sliding window with character frequency count
 * 2. For each window:
 *    - Track frequency of each character
 *    - Find the most frequent character
 *    - If (window length - most frequent count) > k
 *      then window is invalid, shrink it
 * 3. Update max length when valid window is found
 * 
 * Time Complexity: O(n) - single pass through string
 * Space Complexity: O(1) - fixed size array for character count
 */
public class LongestRepeatingCharacterReplacement {
    
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // Array to store character frequencies
        int[] charCount = new int[26];
        int maxLength = 0;
        int maxCount = 0;  // Count of most frequent character in current window
        int left = 0;
        
        // Expand window
        for (int right = 0; right < s.length(); right++) {
            // Add new character to window
            charCount[s.charAt(right) - 'A']++;
            
            // Update max frequency in current window
            maxCount = Math.max(maxCount, charCount[s.charAt(right) - 'A']);
            
            // Current window size - max frequency = number of characters to be replaced
            // If this is greater than k, window is invalid
            while (right - left + 1 - maxCount > k) {
                // Shrink window
                charCount[s.charAt(left) - 'A']--;
                left++;
                
                // Recalculate maxCount
                maxCount = 0;
                for (int count : charCount) {
                    maxCount = Math.max(maxCount, count);
                }
            }
            
            // Update max length when window is valid
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement solution = new LongestRepeatingCharacterReplacement();
        
        // Test case 1: Basic case
        String s1 = "ABAB";
        int k1 = 2;
        System.out.println("Test case 1 (should be 4): " + 
                          solution.characterReplacement(s1, k1));
        
        // Test case 2: All same characters
        String s2 = "AAAA";
        int k2 = 2;
        System.out.println("Test case 2 (should be 4): " + 
                          solution.characterReplacement(s2, k2));
        
        // Test case 3: No replacements allowed
        String s3 = "ABCD";
        int k3 = 0;
        System.out.println("Test case 3 (should be 1): " + 
                          solution.characterReplacement(s3, k3));
        
        // Test case 4: All characters can be replaced
        String s4 = "ABCD";
        int k4 = 4;
        System.out.println("Test case 4 (should be 4): " + 
                          solution.characterReplacement(s4, k4));
        
        // Test case 5: Longer string
        String s5 = "AABABBA";
        int k5 = 1;
        System.out.println("Test case 5 (should be 4): " + 
                          solution.characterReplacement(s5, k5));
    }
}
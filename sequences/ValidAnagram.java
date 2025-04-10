package leet75.sequences;

/**
 * Problem: Valid Anagram
 * 
 * Description:
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An anagram is a word formed by rearranging the letters of another word.
 * All inputs consist of lowercase English letters.
 * 
 * Example:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * 
 * Approach 1 (Character Count):
 * 1. Create int[26] array for character frequencies (since all lowercase English letters)
 * 2. Increment count for chars in s, decrement for chars in t
 * 3. Check if all counts are zero
 * 
 * Alternative Approach (Sorting):
 * 1. Sort both strings
 * 2. Compare if they are equal
 * 
 * Time Complexity: O(n) - We traverse each string once
 * Space Complexity: O(1) - Fixed size array of 26 characters
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        // Quick check on lengths
        if (s.length() != t.length()) {
            return false;
        }
        
        // Create character frequency array
        int[] charCount = new int[26];
        
        // Count characters in both strings
        for (int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i) - 'a']++;  // Increment for s
            charCount[t.charAt(i) - 'a']--;  // Decrement for t
        }
        
        // Check if all counts are zero
        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }
        
        return true;
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        ValidAnagram solution = new ValidAnagram();
        
        // Test case 1: Valid anagram
        String s1 = "anagram";
        String t1 = "nagaram";
        System.out.println("Test case 1 (should be true): " + 
                          solution.isAnagram(s1, t1));
        
        // Test case 2: Not anagram
        String s2 = "rat";
        String t2 = "car";
        System.out.println("Test case 2 (should be false): " + 
                          solution.isAnagram(s2, t2));
        
        // Test case 3: Different lengths
        String s3 = "ab";
        String t3 = "abc";
        System.out.println("Test case 3 (should be false): " + 
                          solution.isAnagram(s3, t3));
    }
}
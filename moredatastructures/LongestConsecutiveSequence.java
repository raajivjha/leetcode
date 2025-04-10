package leet75.moredatastructures;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem: Longest Consecutive Sequence
 * 
 * Description:
 * Given an unsorted array of integers nums, return the length of the longest 
 * consecutive elements sequence. Must run in O(n) time.
 * 
 * Example:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive sequence is [1,2,3,4]
 * 
 * Approach:
 * 1. Add all numbers to a HashSet for O(1) lookups
 * 2. For each number n in array:
 *    - If n-1 is not in set (start of sequence)
 *    - Count consecutive numbers starting from n
 * 3. Keep track of longest sequence found
 * 
 * Time Complexity: O(n) - each number is visited at most twice
 * Space Complexity: O(n) - to store the HashSet
 */
public class LongestConsecutiveSequence {
    
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Add all numbers to HashSet for O(1) lookup
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        
        int longestStreak = 0;
        
        // Check each number that could be start of a sequence
        for (int num : nums) {
            // Only check numbers that could be start of a sequence
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                
                // Count consecutive numbers
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }
                
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        
        return longestStreak;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        LongestConsecutiveSequence solution = new LongestConsecutiveSequence();
        
        // Test Case 1: Standard case
        int[] nums1 = {100,4,200,1,3,2};
        System.out.println("Test Case 1:");
        System.out.println("Input: " + java.util.Arrays.toString(nums1));
        System.out.println("Output: " + solution.longestConsecutive(nums1));  // Expected: 4
        
        // Test Case 2: No consecutive numbers
        int[] nums2 = {1,3,5,7,9};
        System.out.println("\nTest Case 2:");
        System.out.println("Input: " + java.util.Arrays.toString(nums2));
        System.out.println("Output: " + solution.longestConsecutive(nums2));  // Expected: 1
        
        // Test Case 3: All consecutive numbers
        int[] nums3 = {1,2,3,4,5};
        System.out.println("\nTest Case 3:");
        System.out.println("Input: " + java.util.Arrays.toString(nums3));
        System.out.println("Output: " + solution.longestConsecutive(nums3));  // Expected: 5
        
        // Test Case 4: Empty array
        int[] nums4 = {};
        System.out.println("\nTest Case 4:");
        System.out.println("Input: " + java.util.Arrays.toString(nums4));
        System.out.println("Output: " + solution.longestConsecutive(nums4));  // Expected: 0
        
        // Test Case 5: Array with duplicates
        int[] nums5 = {0,3,7,2,5,8,4,6,0,1};
        System.out.println("\nTest Case 5:");
        System.out.println("Input: " + java.util.Arrays.toString(nums5));
        System.out.println("Output: " + solution.longestConsecutive(nums5));  // Expected: 9
    }
}
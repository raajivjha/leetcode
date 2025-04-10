package leet75.sequences;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem: Contains Duplicate
 * 
 * Description:
 * Given an integer array nums, return true if any value appears at least twice in the array,
 * and return false if every element is distinct.
 * 
 * Example:
 * Input: nums = [1,2,3,1]
 * Output: true
 * Explanation: '1' appears twice in the array
 * 
 * Approach 1 (HashSet):
 * 1. Create HashSet to track unique elements
 * 2. Iterate through array, add to set
 * 3. If element already exists in set, return true
 * 
 * Alternative Approach (Sorting):
 * 1. Sort the array
 * 2. Check adjacent elements for equality
 * 
 * Time Complexity: O(n) with HashSet approach
 * Space Complexity: O(n) to store the HashSet
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        // Create a set to store unique numbers
        Set<Integer> numSet = new HashSet<>();
        
        // Check each number
        for (int num : nums) {
            // If number already exists in set, we found a duplicate
            if (numSet.contains(num)) {
                return true;
            }
            // Add number to set
            numSet.add(num);
        }
        
        // No duplicates found
        return false;
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        ContainsDuplicate solution = new ContainsDuplicate();
        
        // Test case 1: Has duplicates
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Test case 1 (should be true): " + 
                          solution.containsDuplicate(nums1));
        
        // Test case 2: No duplicates
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("Test case 2 (should be false): " + 
                          solution.containsDuplicate(nums2));
    }
}
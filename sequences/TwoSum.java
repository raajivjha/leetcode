package leet75.sequences;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Two Sum
 * 
 * Description:
 * Given an array of integers nums and an integer target, return indices of the two numbers 
 * in the array that add up to target. You may assume that each input would have exactly 
 * one solution, and you may not use the same element twice.
 * 
 * Example:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1]
 * 
 * Approach:
 * 1. Use HashMap to store complement values
 * 2. For each number x, check if target - x exists in map
 * 3. If found, return current index and stored index
 * 4. If not, store current number and index
 * 
 * Time Complexity: O(n) - We traverse the array once
 * Space Complexity: O(n) - We store at most n elements in the HashMap
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // Map to store number -> index mapping
        Map<Integer, Integer> numMap = new HashMap<>();
        
        // Traverse the array
        for (int i = 0; i < nums.length; i++) {
            // Calculate the complement needed
            int complement = target - nums[i];
            
            // If complement exists in map, we found our pair
            if (numMap.containsKey(complement)) {
                return new int[]{numMap.get(complement), i};
            }
            
            // Store current number and its index
            numMap.put(nums[i], i);
        }
        
        // No solution found (though problem guarantees a solution)
        return new int[]{};
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        TwoSum solution = new TwoSum();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        
        int[] result = solution.twoSum(nums, target);
        System.out.printf("For target %d, indices are: [%d, %d]%n", 
                         target, result[0], result[1]);
    }
}
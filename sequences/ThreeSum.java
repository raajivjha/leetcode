package leet75.sequences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem: 3Sum
 * 
 * Description:
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that 
 * i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 * 
 * Example:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation: 
 * - nums[0] + nums[2] + nums[4] = (-1) + 1 + (-1) = -1
 * - nums[1] + nums[2] + nums[3] = 0 + 1 + 2 = 3
 * 
 * Approach:
 * 1. Sort the array (helps in avoiding duplicates and enables two-pointer technique)
 * 2. For each number nums[i]:
 *    - Use two pointers (left and right) to find pairs that sum to -nums[i]
 *    - Skip duplicates to avoid duplicate triplets
 *    - Move pointers based on sum comparison
 * 3. Skip duplicates for the first number as well
 * 
 * Time Complexity: O(n²) - One loop for first number, two pointers for remaining array
 * Space Complexity: O(1) - Not counting the space used for output
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        
        // Sort array to handle duplicates and enable two-pointer technique
        Arrays.sort(nums);
        
        // Fix first number and use two pointers for remaining array
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for first number
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    // Found a triplet
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicates for second number
                    while (left < right && nums[left] == nums[left+1]) {
                        left++;
                    }
                    // Skip duplicates for third number
                    while (left < right && nums[right] == nums[right-1]) {
                        right--;
                    }
                    
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return result;
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        
        // Test case 1: Standard case
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Test case 1: " + solution.threeSum(nums1));
        
        // Test case 2: All zeros
        int[] nums2 = {0, 0, 0};
        System.out.println("Test case 2: " + solution.threeSum(nums2));
        
        // Test case 3: No solution
        int[] nums3 = {1, 2, 3, 4, 5};
        System.out.println("Test case 3: " + solution.threeSum(nums3));
        
        // Test case 4: With duplicates
        int[] nums4 = {-2, 0, 1, 1, 2};
        System.out.println("Test case 4: " + solution.threeSum(nums4));
    }
}
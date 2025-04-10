package leet75.dynamicprogramming;

import java.util.Arrays;

/**
 * Problem: Longest Increasing Subsequence
 * 
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements
 * without changing the order of the remaining elements.
 * 
 * Example 1:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], length is 4.
 * 
 * Example 2:
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Explanation: The longest increasing subsequence is [0,1,2,3], length is 4.
 * 
 * Approaches implemented:
 * 1. Dynamic Programming O(n²)
 * 2. Binary Search O(n log n)
 * 
 * DP Approach:
 * - dp[i] represents the length of LIS ending at index i
 * - For each position, look back at all previous positions
 * - If current number is greater than previous number, we can extend that subsequence
 * 
 * Binary Search Approach:
 * - Maintain a sorted subsequence
 * - For each number, find its position in the subsequence
 * - Replace the next larger element with current element
 */
public class LongestIncreasingSubsequence {
    
    // Dynamic Programming approach - O(n²)
    public int lengthOfLIS_DP(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        // dp[i] represents length of LIS ending at index i
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1); // each number by itself is a subsequence of length 1
        
        int maxLength = 1;
        
        // For each position
        for (int i = 1; i < nums.length; i++) {
            // Look back at all previous positions
            for (int j = 0; j < i; j++) {
                // If current number is greater, we can extend that subsequence
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        
        return maxLength;
    }
    
    // Binary Search approach - O(n log n)
    public int lengthOfLIS_BinarySearch(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        // tails[i] represents the smallest value that ends an increasing subsequence of length i+1
        int[] tails = new int[nums.length];
        int size = 0;
        
        for (int num : nums) {
            // Binary search to find the position to insert current number
            int left = 0, right = size;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            
            // Insert the current number at the found position
            tails[left] = num;
            // Extend the subsequence if we inserted at the end
            if (left == size) size++;
        }
        
        return size;
    }
    
    // Test the solution
    public static void main(String[] args) {
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
        
        int[] nums1 = {10,9,2,5,3,7,101,18};
        System.out.println("Length of LIS (DP): " + 
            solution.lengthOfLIS_DP(nums1)); // Should output 4
        System.out.println("Length of LIS (Binary Search): " + 
            solution.lengthOfLIS_BinarySearch(nums1)); // Should output 4
        
        int[] nums2 = {0,1,0,3,2,3};
        System.out.println("Length of LIS (DP): " + 
            solution.lengthOfLIS_DP(nums2)); // Should output 4
        System.out.println("Length of LIS (Binary Search): " + 
            solution.lengthOfLIS_BinarySearch(nums2)); // Should output 4
    }
}

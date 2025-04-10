package leet75.sequences;

/**
 * Problem: Maximum Subarray
 * 
 * Description:
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * 
 * Example:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * 
 * Approach (Kadane's Algorithm):
 * 1. Keep track of two values:
 *    - currentSum: sum of current subarray
 *    - maxSum: maximum sum found so far
 * 2. For each number:
 *    - Add it to currentSum
 *    - If currentSum becomes negative, reset it to 0 (start new subarray)
 *    - Update maxSum if currentSum is larger
 * 3. Handle edge case of all negative numbers
 * 
 * Time Complexity: O(n) - Single pass through the array
 * Space Complexity: O(1) - Only using two variables
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int currentSum = 0;
        int maxSum = nums[0]; // Initialize to first element for handling all negative case
        
        for (int num : nums) {
            // Add current number to sum
            currentSum = Math.max(num, currentSum + num);
            // Update maximum sum if current sum is larger
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        MaximumSubarray solution = new MaximumSubarray();
        
        // Test case 1: Mixed positive and negative numbers
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Test case 1 (should be 6): " + 
                          solution.maxSubArray(nums1));
        
        // Test case 2: All positive numbers
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("Test case 2 (should be 10): " + 
                          solution.maxSubArray(nums2));
        
        // Test case 3: All negative numbers
        int[] nums3 = {-2, -1, -3, -4};
        System.out.println("Test case 3 (should be -1): " + 
                          solution.maxSubArray(nums3));
        
        // Test case 4: Single element
        int[] nums4 = {1};
        System.out.println("Test case 4 (should be 1): " + 
                          solution.maxSubArray(nums4));
    }
}
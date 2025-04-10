package leet75.sequences;

/**
 * Problem: Maximum Product Subarray
 * 
 * Description:
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has
 * the largest product, and return the product.
 * 
 * Example:
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * 
 * Approach:
 * 1. Track both maximum and minimum products ending at current position
 *    (because a negative number can make the smallest product become largest)
 * 2. For each number:
 *    - Calculate possible products (current number, max*current, min*current)
 *    - Update max and min products
 *    - Update overall maximum product
 * 3. Handle edge cases (empty array, single element)
 * 
 * Time Complexity: O(n) - Single pass through the array
 * Space Complexity: O(1) - Only using a few variables
 */
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Initialize variables
        int maxSoFar = nums[0];  // Maximum product found so far
        int currentMax = nums[0]; // Maximum product ending at current position
        int currentMin = nums[0]; // Minimum product ending at current position
        
        // Start from second element
        for (int i = 1; i < nums.length; i++) {
            // Store current max for use in min calculation
            int temp = currentMax;
            
            // Calculate new max product ending at current position
            currentMax = Math.max(nums[i], Math.max(currentMax * nums[i], 
                                                   currentMin * nums[i]));
            
            // Calculate new min product ending at current position
            currentMin = Math.min(nums[i], Math.min(temp * nums[i], 
                                                   currentMin * nums[i]));
            
            // Update maximum product found so far
            maxSoFar = Math.max(maxSoFar, currentMax);
        }
        
        return maxSoFar;
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        MaximumProductSubarray solution = new MaximumProductSubarray();
        
        // Test case 1: Mix of positive and negative numbers
        int[] nums1 = {2, 3, -2, 4};
        System.out.println("Test case 1 (should be 6): " + 
                          solution.maxProduct(nums1));
        
        // Test case 2: All positive numbers
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("Test case 2 (should be 24): " + 
                          solution.maxProduct(nums2));
        
        // Test case 3: Contains zero
        int[] nums3 = {-2, 0, -1};
        System.out.println("Test case 3 (should be 0): " + 
                          solution.maxProduct(nums3));
        
        // Test case 4: All negative numbers
        int[] nums4 = {-2, -3, -4};
        System.out.println("Test case 4 (should be 12): " + 
                          solution.maxProduct(nums4));
        
        // Test case 5: Single element
        int[] nums5 = {-2};
        System.out.println("Test case 5 (should be -2): " + 
                          solution.maxProduct(nums5));
    }
}
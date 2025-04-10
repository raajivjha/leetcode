package leet75.sequences;

/**
 * Problem: Product of Array Except Self
 * 
 * Description:
 * Given an integer array nums, return an array answer such that answer[i] is equal to 
 * the product of all the elements of nums except nums[i]. Must solve without using division
 * and in O(n) time.
 * 
 * Example:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Explanation: 
 * - answer[0] = 2*3*4 = 24
 * - answer[1] = 1*3*4 = 12
 * - answer[2] = 1*2*4 = 8
 * - answer[3] = 1*2*3 = 6
 * 
 * Approach:
 * 1. Use two passes through the array:
 *    - First pass: Calculate prefix products from left to right
 *    - Second pass: Calculate suffix products from right to left
 * 2. For each position i, final result is prefix[i-1] * suffix[i+1]
 * 3. Optimize space by using output array to store prefix products
 *    and using a single variable for suffix products
 * 
 * Time Complexity: O(n) - Two passes through the array
 * Space Complexity: O(1) - Only output array, no extra space
 */
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Calculate prefix products
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i-1] * nums[i-1];
        }
        
        // Calculate suffix products and combine with prefix products
        int suffixProduct = 1;
        for (int i = n-1; i >= 0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        
        return result;
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        ProductOfArrayExceptSelf solution = new ProductOfArrayExceptSelf();
        
        // Test case 1: Basic case
        int[] nums1 = {1, 2, 3, 4};
        int[] result1 = solution.productExceptSelf(nums1);
        System.out.print("Test case 1 (should be [24,12,8,6]): ");
        printArray(result1);
        
        // Test case 2: Array with zeros
        int[] nums2 = {1, 0, 3, 4};
        int[] result2 = solution.productExceptSelf(nums2);
        System.out.print("Test case 2 (should be [0,12,0,0]): ");
        printArray(result2);
        
        // Test case 3: Array with multiple zeros
        int[] nums3 = {0, 0, 3, 4};
        int[] result3 = solution.productExceptSelf(nums3);
        System.out.print("Test case 3 (should be [0,0,0,0]): ");
        printArray(result3);
    }
    
    // Helper method to print array
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
}
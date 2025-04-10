package leet75.dynamicprogramming;

/**
 * Problem: Jump Game
 * 
 * Description:
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * 
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * 
 * Example 2:
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 * 
 * Approach:
 * 1. Use a greedy approach to track the farthest index that can be reached.
 * 2. Iterate through the array and update the farthest index.
 * 3. If at any point the current index is greater than the farthest index, return false.
 * 4. If the loop completes, return true.
 * 
 * Time Complexity: O(n) - we iterate through the array once.
 * Space Complexity: O(1) - we use a constant amount of extra space.
 */
public class JumpGame {
    
    public boolean canJump(int[] nums) {
        int farthest = 0; // Tracks the farthest index that can be reached
        
        for (int i = 0; i < nums.length; i++) {
            // If the current index is beyond the farthest reachable index, return false
            if (i > farthest) {
                return false;
            }
            // Update the farthest reachable index
            farthest = Math.max(farthest, i + nums[i]);
        }
        
        return true; // If we complete the loop, the last index is reachable
    }
    
    // Test the solution
    public static void main(String[] args) {
        JumpGame solution = new JumpGame();
        
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Can jump (nums1): " + solution.canJump(nums1)); // Should output true
        
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Can jump (nums2): " + solution.canJump(nums2)); // Should output false
        
        int[] nums3 = {0};
        System.out.println("Can jump (nums3): " + solution.canJump(nums3)); // Should output true
    }
}
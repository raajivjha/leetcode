package leet75.datastructures;

/**
 * Problem: Container With Most Water
 * 
 * Description:
 * Given n non-negative integers height[1..n] where each represents a point at coordinate (i, height[i]).
 * n vertical lines are drawn at points with coordinate i, with length height[i].
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * 
 * Example:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. 
 * In this case, the max area of water (blue section) the container can contain is 49 
 * (between the lines at indices 1 and 8).
 * 
 * Approach (Two Pointer):
 * 1. Start with widest possible container (left=0, right=n-1)
 * 2. Calculate area = width * min(height[left], height[right])
 * 3. Move the pointer with smaller height inward
 *    (since moving the larger height would only decrease the area)
 * 4. Keep track of maximum area seen so far
 * 
 * Time Complexity: O(n) - single pass with two pointers
 * Space Complexity: O(1) - only using a few variables
 */
public class ContainerWithMostWater {
    
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        
        while (left < right) {
            // Calculate width between lines
            int width = right - left;
            
            // Calculate area using smaller height
            int area = width * Math.min(height[left], height[right]);
            
            // Update maximum area if current area is larger
            maxArea = Math.max(maxArea, area);
            
            // Move pointer with smaller height inward
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();
        
        // Test case 1: Example case
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Test case 1 (should be 49): " + 
                          solution.maxArea(height1));
        
        // Test case 2: All same height
        int[] height2 = {5, 5, 5, 5};
        System.out.println("Test case 2 (should be 15): " + 
                          solution.maxArea(height2));
        
        // Test case 3: Increasing heights
        int[] height3 = {1, 2, 3, 4, 5};
        System.out.println("Test case 3 (should be 6): " + 
                          solution.maxArea(height3));
        
        // Test case 4: Two elements
        int[] height4 = {1, 2};
        System.out.println("Test case 4 (should be 1): " + 
                          solution.maxArea(height4));
        
        // Test case 5: Decreasing heights
        int[] height5 = {5, 4, 3, 2, 1};
        System.out.println("Test case 5 (should be 6): " + 
                          solution.maxArea(height5));
    }
}
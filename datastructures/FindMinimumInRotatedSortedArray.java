package leet75.datastructures;

/**
 * Problem: Find Minimum in Rotated Sorted Array
 * 
 * Description:
 * Suppose an array of unique elements was sorted in ascending order and then rotated at some 
 * pivot index. Given the rotated array, find the minimum element in O(log n) time.
 * 
 * Example:
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] and it was rotated 3 times.
 * 
 * Approach (Modified Binary Search):
 * 1. Compare middle element with right element to determine which half is sorted
 * 2. If mid > right, minimum is in right half
 * 3. If mid ≤ right, minimum is in left half (including mid)
 * 4. Key insight: minimum element is the only element smaller than its previous element
 * 
 * Time Complexity: O(log n) - binary search
 * Space Complexity: O(1) - constant extra space
 */
public class FindMinimumInRotatedSortedArray {
    
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        // Handle single element case
        if (nums.length == 1) {
            return nums[0];
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        // If array is not rotated
        if (nums[right] > nums[left]) {
            return nums[left];
        }
        
        while (left <= right) {
            // Handle two elements case
            if (right - left == 1) {
                return Math.min(nums[left], nums[right]);
            }
            
            int mid = left + (right - left) / 2;
            
            // If mid element is greater than next element, next element is minimum
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            
            // If mid element is less than previous element, mid element is minimum
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }
            
            // Decide which half to search
            if (nums[mid] > nums[right]) {
                // Minimum is in right half
                left = mid + 1;
            } else {
                // Minimum is in left half
                right = mid - 1;
            }
        }
        
        return nums[0]; // This line should never be reached if input is valid
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray solution = new FindMinimumInRotatedSortedArray();
        
        // Test case 1: Standard rotated array
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println("Test case 1 (should be 1): " + 
                          solution.findMin(nums1));
        
        // Test case 2: Array rotated n times (not rotated)
        int[] nums2 = {1, 2, 3, 4, 5};
        System.out.println("Test case 2 (should be 1): " + 
                          solution.findMin(nums2));
        
        // Test case 3: Array with two elements
        int[] nums3 = {2, 1};
        System.out.println("Test case 3 (should be 1): " + 
                          solution.findMin(nums3));
        
        // Test case 4: Single element array
        int[] nums4 = {1};
        System.out.println("Test case 4 (should be 1): " + 
                          solution.findMin(nums4));
        
        // Test case 5: Larger rotated array
        int[] nums5 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Test case 5 (should be 0): " + 
                          solution.findMin(nums5));
    }
}
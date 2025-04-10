package leet75.sequences;

/**
 * Problem: Search in Rotated Sorted Array
 * 
 * Description:
 * Given a sorted array nums that is rotated at some pivot index and a target value,
 * return the index of target if it exists in the array, or -1 if it does not exist.
 * The array was originally sorted in ascending order before being rotated.
 * You must write an algorithm with O(log n) runtime complexity.
 * 
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * 
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * 
 * Approach (Modified Binary Search):
 * 1. Find the sorted half of the array by comparing middle with left end
 * 2. Check if target lies in the sorted half:
 *    - If yes, search in that half
 *    - If no, search in the other half
 * 3. Use binary search properties while maintaining rotated array characteristics
 * 
 * Time Complexity: O(log n) - Binary search on rotated array
 * Space Complexity: O(1) - Only using a few variables
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Found target
            if (nums[mid] == target) {
                return mid;
            }
            
            // Left half is sorted
            if (nums[left] <= nums[mid]) {
                // Target is in left half
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    // Target is in right half
                    left = mid + 1;
                }
            }
            // Right half is sorted
            else {
                // Target is in right half
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    // Target is in left half
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();
        
        // Test case 1: Standard rotated array
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        System.out.println("Test case 1 (should be 4): " + 
                          solution.search(nums1, target1));
        
        // Test case 2: Target not found
        int target2 = 3;
        System.out.println("Test case 2 (should be -1): " + 
                          solution.search(nums1, target2));
        
        // Test case 3: Array not rotated
        int[] nums2 = {1, 2, 3, 4, 5};
        int target3 = 3;
        System.out.println("Test case 3 (should be 2): " + 
                          solution.search(nums2, target3));
        
        // Test case 4: Single element array
        int[] nums3 = {1};
        int target4 = 1;
        System.out.println("Test case 4 (should be 0): " + 
                          solution.search(nums3, target4));
        
        // Test case 5: Two elements array
        int[] nums4 = {3, 1};
        int target5 = 1;
        System.out.println("Test case 5 (should be 1): " + 
                          solution.search(nums4, target5));
    }
}
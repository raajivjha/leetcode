package leet75.moredatastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Problem: Insert Interval
 * 
 * Description:
 * Given a set of non-overlapping intervals sorted by start time, insert a new interval 
 * into the intervals (merge if necessary). You may assume that the intervals were 
 * initially sorted according to their start times.
 * 
 * Example:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * 
 * Approach:
 * 1. Handle intervals that come before newInterval (no overlap)
 * 2. Merge overlapping intervals with newInterval
 * 3. Add remaining intervals that come after newInterval
 * 4. Handle edge cases: empty intervals, non-overlapping cases
 * 
 * Time Complexity: O(n) - single pass through intervals
 * Space Complexity: O(n) - to store result
 */
public class InsertInterval {
    
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;
        
        // Add all intervals that come before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        
        // Merge overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);
        
        // Add remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }
        
        return result.toArray(new int[result.size()][]);
    }

    // Test method to verify solution
    public static void main(String[] args) {
        InsertInterval solution = new InsertInterval();
        
        // Test Case 1: Standard case with overlap
        int[][] intervals1 = {{1,3}, {6,9}};
        int[] newInterval1 = {2,5};
        System.out.println("Test Case 1:");
        System.out.println("Original intervals: " + Arrays.deepToString(intervals1));
        System.out.println("New interval: " + Arrays.toString(newInterval1));
        System.out.println("Result: " + Arrays.deepToString(solution.insert(intervals1, newInterval1)));
        
        // Test Case 2: No overlap
        int[][] intervals2 = {{1,2}, {3,5}, {6,7}, {8,10}, {12,16}};
        int[] newInterval2 = {4,8};
        System.out.println("\nTest Case 2:");
        System.out.println("Original intervals: " + Arrays.deepToString(intervals2));
        System.out.println("New interval: " + Arrays.toString(newInterval2));
        System.out.println("Result: " + Arrays.deepToString(solution.insert(intervals2, newInterval2)));
        
        // Test Case 3: Empty intervals
        int[][] intervals3 = {};
        int[] newInterval3 = {5,7};
        System.out.println("\nTest Case 3:");
        System.out.println("Original intervals: " + Arrays.deepToString(intervals3));
        System.out.println("New interval: " + Arrays.toString(newInterval3));
        System.out.println("Result: " + Arrays.deepToString(solution.insert(intervals3, newInterval3)));
        
        // Test Case 4: Insert at beginning
        int[][] intervals4 = {{3,5}, {6,7}, {8,10}};
        int[] newInterval4 = {1,2};
        System.out.println("\nTest Case 4:");
        System.out.println("Original intervals: " + Arrays.deepToString(intervals4));
        System.out.println("New interval: " + Arrays.toString(newInterval4));
        System.out.println("Result: " + Arrays.deepToString(solution.insert(intervals4, newInterval4)));
        
        // Test Case 5: Insert at end
        int[][] intervals5 = {{1,3}, {6,7}};
        int[] newInterval5 = {8,10};
        System.out.println("\nTest Case 5:");
        System.out.println("Original intervals: " + Arrays.deepToString(intervals5));
        System.out.println("New interval: " + Arrays.toString(newInterval5));
        System.out.println("Result: " + Arrays.deepToString(solution.insert(intervals5, newInterval5)));
    }
}
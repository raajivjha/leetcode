package leet75.nonlinear;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Problem: Non-overlapping Intervals
 * 
 * Description:
 * Given an array of intervals where intervals[i] = [starti, endi], return the minimum number
 * of intervals you need to remove to make the rest of the intervals non-overlapping.
 * 
 * Example:
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 * 
 * Approach:
 * 1. Sort intervals by end time (greedy approach)
 * 2. Keep track of the end time of last kept interval
 * 3. If current interval starts before last end time, it overlaps - remove it
 * 4. Otherwise, update last end time to current interval's end time
 * 
 * Time Complexity: O(n log n) - dominated by sorting
 * Space Complexity: O(1) - if we don't count space used by sorting
 */
public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        // Sort intervals by end time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        
        int removals = 0;
        int lastEnd = intervals[0][1];
        
        // Check each interval for overlap
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < lastEnd) {
                // Current interval overlaps with previous - remove it
                removals++;
            } else {
                // No overlap - update last end time
                lastEnd = intervals[i][1];
            }
        }
        
        return removals;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        NonOverlappingIntervals solution = new NonOverlappingIntervals();
        
        // Test Case 1: Example from problem
        int[][] test1 = {{1,2}, {2,3}, {3,4}, {1,3}};
        System.out.println("Test Case 1: " + solution.eraseOverlapIntervals(test1));  // Should print 1
        
        // Test Case 2: All overlapping intervals
        int[][] test2 = {{1,2}, {1,2}, {1,2}};
        System.out.println("Test Case 2: " + solution.eraseOverlapIntervals(test2));  // Should print 2
        
        // Test Case 3: No overlapping intervals
        int[][] test3 = {{1,2}, {2,3}, {3,4}};
        System.out.println("Test Case 3: " + solution.eraseOverlapIntervals(test3));  // Should print 0
        
        // Test Case 4: Multiple overlapping intervals
        int[][] test4 = {{1,100}, {11,22}, {1,11}, {2,12}};
        System.out.println("Test Case 4: " + solution.eraseOverlapIntervals(test4));  // Should print 2
    }
}
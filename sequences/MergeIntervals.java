package leet75.sequences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem: Merge Intervals
 * 
 * Description:
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * 
 * Example:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * 
 * Approach:
 * 1. Sort intervals based on start time
 * 2. Initialize result list with first interval
 * 3. For each remaining interval:
 *    - If current interval overlaps with last result interval, merge them
 *    - Otherwise, add current interval to result
 * 4. Convert result list to array
 * 
 * Time Complexity: O(n log n) - Dominated by sorting
 * Space Complexity: O(n) - To store the result
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        
        // Sort intervals based on start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);
        
        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            int[] last = merged.get(merged.size() - 1);
            
            // Check if current interval overlaps with last result interval
            if (current[0] <= last[1]) {
                // Merge the intervals by updating the end time
                last[1] = Math.max(last[1], current[1]);
            } else {
                // No overlap, add current interval to result
                merged.add(current);
            }
        }
        
        return merged.toArray(new int[merged.size()][]);
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        MergeIntervals solution = new MergeIntervals();
        
        // Test case 1: Standard case with overlap
        int[][] intervals1 = {{1,3}, {2,6}, {8,10}, {15,18}};
        System.out.println("Test case 1:");
        printIntervals(solution.merge(intervals1));
        
        // Test case 2: All intervals overlap
        int[][] intervals2 = {{1,4}, {2,3}, {3,6}};
        System.out.println("\nTest case 2:");
        printIntervals(solution.merge(intervals2));
        
        // Test case 3: No overlapping intervals
        int[][] intervals3 = {{1,2}, {3,4}, {5,6}};
        System.out.println("\nTest case 3:");
        printIntervals(solution.merge(intervals3));
        
        // Test case 4: Single interval
        int[][] intervals4 = {{1,1}};
        System.out.println("\nTest case 4:");
        printIntervals(solution.merge(intervals4));
    }
    
    // Helper method to print intervals
    private static void printIntervals(int[][] intervals) {
        for (int[] interval : intervals) {
            System.out.print("[" + interval[0] + "," + interval[1] + "] ");
        }
        System.out.println();
    }
}
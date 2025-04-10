package leet75.moredatastructures;

import java.util.*;

/**
 * Problem: Meeting Rooms I & II
 * 
 * Description:
 * Meeting Rooms I: Given an array of meeting time intervals where intervals[i] = [starti, endi],
 * determine if a person could attend all meetings (no overlapping intervals).
 * 
 * Meeting Rooms II: Given an array of meeting time intervals, find the minimum number of
 * conference rooms required.
 * 
 * Example 1 (Meeting Rooms I):
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 * 
 * Example 2 (Meeting Rooms II):
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 * 
 * Approach for Meeting Rooms I:
 * 1. Sort intervals by start time
 * 2. Check if any meeting overlaps with next meeting
 * 
 * Approach for Meeting Rooms II:
 * 1. Separate start and end times into arrays
 * 2. Sort both arrays
 * 3. Use two pointers to count overlapping meetings
 * 
 * Time Complexity: O(n log n) for sorting
 * Space Complexity: O(n) for sorting
 */
public class MeetingRooms {
    
    // Meeting Rooms I: Check if one person can attend all meetings
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }
        
        // Sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // Check for any overlap
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i-1][1]) {
                return false;
            }
        }
        
        return true;
    }
    
    // Meeting Rooms II: Find minimum number of rooms needed
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        int n = intervals.length;
        
        // Separate start and end times
        int[] start = new int[n];
        int[] end = new int[n];
        
        for (int i = 0; i < n; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        
        // Sort start and end times
        Arrays.sort(start);
        Arrays.sort(end);
        
        int rooms = 0;
        int maxRooms = 0;
        int s = 0;
        int e = 0;
        
        // Process meetings in chronological order
        while (s < n) {
            if (start[s] < end[e]) {
                rooms++;
                s++;
            } else {
                rooms--;
                e++;
            }
            maxRooms = Math.max(maxRooms, rooms);
        }
        
        return maxRooms;
    }
    
    // Alternative solution for Meeting Rooms II using Priority Queue
    public int minMeetingRoomsPQ(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        // Sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // Min heap to track earliest ending meeting
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(intervals[0][1]);
        
        for (int i = 1; i < intervals.length; i++) {
            // If current meeting starts after earliest ending
            if (intervals[i][0] >= pq.peek()) {
                pq.poll();
            }
            pq.offer(intervals[i][1]);
        }
        
        return pq.size();
    }

    // Test method to verify solution
    public static void main(String[] args) {
        MeetingRooms solution = new MeetingRooms();
        
        // Test Case 1: Standard case
        int[][] intervals1 = {{0,30}, {5,10}, {15,20}};
        System.out.println("Test Case 1:");
        System.out.println("Intervals: " + Arrays.deepToString(intervals1));
        System.out.println("Can attend all meetings: " + solution.canAttendMeetings(intervals1));
        System.out.println("Minimum rooms needed (Two Pointer): " + solution.minMeetingRooms(intervals1));
        System.out.println("Minimum rooms needed (Priority Queue): " + solution.minMeetingRoomsPQ(intervals1));
        
        // Test Case 2: No overlap
        int[][] intervals2 = {{7,10}, {2,4}};
        System.out.println("\nTest Case 2:");
        System.out.println("Intervals: " + Arrays.deepToString(intervals2));
        System.out.println("Can attend all meetings: " + solution.canAttendMeetings(intervals2));
        System.out.println("Minimum rooms needed (Two Pointer): " + solution.minMeetingRooms(intervals2));
        System.out.println("Minimum rooms needed (Priority Queue): " + solution.minMeetingRoomsPQ(intervals2));
        
        // Test Case 3: Multiple overlapping meetings
        int[][] intervals3 = {{1,4}, {2,5}, {3,6}, {4,7}};
        System.out.println("\nTest Case 3:");
        System.out.println("Intervals: " + Arrays.deepToString(intervals3));
        System.out.println("Can attend all meetings: " + solution.canAttendMeetings(intervals3));
        System.out.println("Minimum rooms needed (Two Pointer): " + solution.minMeetingRooms(intervals3));
        System.out.println("Minimum rooms needed (Priority Queue): " + solution.minMeetingRoomsPQ(intervals3));
        
        // Test Case 4: Empty and single meeting
        int[][] intervals4 = {};
        int[][] intervals5 = {{1,5}};
        System.out.println("\nTest Case 4:");
        System.out.println("Empty intervals: " + Arrays.deepToString(intervals4));
        System.out.println("Can attend all meetings: " + solution.canAttendMeetings(intervals4));
        System.out.println("Minimum rooms needed (Two Pointer): " + solution.minMeetingRooms(intervals4));
        System.out.println("Minimum rooms needed (Priority Queue): " + solution.minMeetingRoomsPQ(intervals4));
        
        System.out.println("\nTest Case 5:");
        System.out.println("Single meeting: " + Arrays.deepToString(intervals5));
        System.out.println("Can attend all meetings: " + solution.canAttendMeetings(intervals5));
        System.out.println("Minimum rooms needed (Two Pointer): " + solution.minMeetingRooms(intervals5));
        System.out.println("Minimum rooms needed (Priority Queue): " + solution.minMeetingRoomsPQ(intervals5));
    }
}
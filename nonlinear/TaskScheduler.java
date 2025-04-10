package leet75.nonlinear;

import java.util.*;

/**
 * Problem: Task Scheduler
 * 
 * Description:
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter
 * represents a different task. Tasks could be done in any order. Each task is done in one unit
 * of time. For each unit of time, the CPU could complete one task or just be idle.
 * There must be at least n units of time between any two same tasks.
 * 
 * Example:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B
 * 
 * Approach:
 * 1. Count frequency of each task
 * 2. Find task with maximum frequency (maxFreq) and count of tasks with maxFreq
 * 3. Calculate minimum slots needed:
 *    - Each group of same task needs (n+1) slots, except last group
 *    - Total groups = maxFreq - 1
 *    - Last group size = count of tasks with maxFreq
 * 4. Return max of (calculated slots, total tasks)
 * 
 * Time Complexity: O(N) where N is the number of tasks
 * Space Complexity: O(1) since we only store at most 26 characters
 */
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        // Count frequency of each task
        int[] frequencies = new int[26];
        for (char task : tasks) {
            frequencies[task - 'A']++;
        }
        
        // Find the maximum frequency
        int maxFreq = 0;
        for (int freq : frequencies) {
            maxFreq = Math.max(maxFreq, freq);
        }
        
        // Count number of tasks with maximum frequency
        int maxCount = 0;
        for (int freq : frequencies) {
            if (freq == maxFreq) {
                maxCount++;
            }
        }
        
        // Calculate minimum required slots
        // Formula: (maxFreq - 1) * (n + 1) + maxCount
        int slots = (maxFreq - 1) * (n + 1) + maxCount;
        
        // Return maximum of calculated slots and total tasks
        return Math.max(slots, tasks.length);
    }

    // Test method to verify solution
    public static void main(String[] args) {
        TaskScheduler solution = new TaskScheduler();
        
        // Test Case 1: Example from problem
        char[] tasks1 = {'A','A','A','B','B','B'};
        int n1 = 2;
        System.out.println("Test Case 1: " + solution.leastInterval(tasks1, n1));  // Expected: 8
        
        // Test Case 2: No cooling time required
        char[] tasks2 = {'A','A','A','B','B','B'};
        int n2 = 0;
        System.out.println("Test Case 2: " + solution.leastInterval(tasks2, n2));  // Expected: 6
        
        // Test Case 3: Long cooling time
        char[] tasks3 = {'A','A','A','A'};
        int n3 = 3;
        System.out.println("Test Case 3: " + solution.leastInterval(tasks3, n3));  // Expected: 13
        
        // Test Case 4: All different tasks
        char[] tasks4 = {'A','B','C','D','E','F'};
        int n4 = 2;
        System.out.println("Test Case 4: " + solution.leastInterval(tasks4, n4));  // Expected: 6
        
        // Test Case 5: Multiple tasks with same frequency
        char[] tasks5 = {'A','A','A','B','B','B','C','C','C'};
        int n5 = 2;
        System.out.println("Test Case 5: " + solution.leastInterval(tasks5, n5));  // Expected: 9
    }
}
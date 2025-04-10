package leet75.moredatastructures;

import java.util.PriorityQueue;

/**
 * Problem: Find Median from Data Stream
 * 
 * Description:
 * The median is the middle value in an ordered integer list. If the size of the list is even,
 * there is no middle value and the median is the mean of the two middle values.
 * 
 * Implement the MedianFinder class:
 * - MedianFinder() initializes the MedianFinder object.
 * - void addNum(int num) adds the integer num from the data stream to the data structure.
 * - double findMedian() returns the median of all elements so far.
 * 
 * Example:
 * Input:
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output:
 * [null, null, null, 1.5, null, 2.0]
 * 
 * Approach:
 * 1. Use two heaps:
 *    - maxHeap for lower half (smaller numbers)
 *    - minHeap for upper half (larger numbers)
 * 2. Balance heaps to have equal size (or maxHeap one element larger)
 * 3. Median is:
 *    - maxHeap.peek() if odd number of elements
 *    - average of both heap tops if even
 * 
 * Time Complexity: 
 * - addNum: O(log n)
 * - findMedian: O(1)
 * Space Complexity: O(n)
 */
public class FindMedianFromDataStream {
    private PriorityQueue<Integer> maxHeap; // stores the lower half
    private PriorityQueue<Integer> minHeap; // stores the upper half
    
    public FindMedianFromDataStream() {
        // maxHeap will store the lower half (using negation for max heap behavior)
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        // minHeap will store the upper half
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        // First element goes to maxHeap
        if (maxHeap.isEmpty()) {
            maxHeap.offer(num);
            return;
        }
        
        // Add to appropriate heap
        if (num < maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        
        // Balance heaps
        // maxHeap can have at most one more element than minHeap
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    public double findMedian() {
        if (maxHeap.isEmpty()) {
            return 0.0;
        }
        
        // If total number of elements is odd
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        
        // If total number of elements is even
        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        FindMedianFromDataStream medianFinder = new FindMedianFromDataStream();
        
        // Test Case 1: Add numbers one by one
        System.out.println("Test Case 1:");
        medianFinder.addNum(1);
        System.out.println("After adding 1: " + medianFinder.findMedian());  // Expected: 1.0
        
        medianFinder.addNum(2);
        System.out.println("After adding 2: " + medianFinder.findMedian());  // Expected: 1.5
        
        medianFinder.addNum(3);
        System.out.println("After adding 3: " + medianFinder.findMedian());  // Expected: 2.0
        
        // Test Case 2: New instance with different sequence
        System.out.println("\nTest Case 2:");
        FindMedianFromDataStream medianFinder2 = new FindMedianFromDataStream();
        
        medianFinder2.addNum(2);
        System.out.println("After adding 2: " + medianFinder2.findMedian());  // Expected: 2.0
        
        medianFinder2.addNum(3);
        System.out.println("After adding 3: " + medianFinder2.findMedian());  // Expected: 2.5
        
        medianFinder2.addNum(1);
        System.out.println("After adding 1: " + medianFinder2.findMedian());  // Expected: 2.0
        
        // Test Case 3: Adding duplicate numbers
        System.out.println("\nTest Case 3:");
        FindMedianFromDataStream medianFinder3 = new FindMedianFromDataStream();
        
        medianFinder3.addNum(1);
        medianFinder3.addNum(1);
        System.out.println("After adding two 1s: " + medianFinder3.findMedian());  // Expected: 1.0
        
        medianFinder3.addNum(2);
        System.out.println("After adding 2: " + medianFinder3.findMedian());  // Expected: 1.0
    }
}
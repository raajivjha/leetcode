package leet75.nonlinear;

import java.util.*;

/**
 * Problem: Top K Frequent Elements
 * 
 * Description:
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 * 
 * Example:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * 
 * Approach 1 (Using Heap):
 * 1. Count frequency of each number using HashMap
 * 2. Use MinHeap of size k to maintain top k frequent elements
 * 3. Return the elements in heap
 * 
 * Alternative Approach (Bucket Sort):
 * 1. Count frequency using HashMap
 * 2. Create array of lists where index represents frequency
 * 3. Get k most frequent elements from highest frequencies
 * 
 * Time Complexity: O(n log k) for heap approach, O(n) for bucket sort
 * Space Complexity: O(n) to store the frequency map
 */
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        // Count the frequency of each element
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Create min heap based on frequency
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (a, b) -> frequencyMap.get(a) - frequencyMap.get(b)
        );
        
        // Add elements to heap, maintaining size k
        for (int num : frequencyMap.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        // Build result array from heap
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll();
        }
        
        return result;
    }

    // Alternative implementation using bucket sort approach
    public int[] topKFrequentBucketSort(int[] nums, int k) {
        // Count frequency
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Create bucket array where index represents frequency
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        
        // Put numbers in buckets based on their frequency
        for (int num : frequencyMap.keySet()) {
            int freq = frequencyMap.get(num);
            buckets[freq].add(num);
        }
        
        // Get top k frequent elements
        int[] result = new int[k];
        int count = 0;
        for (int i = buckets.length - 1; i >= 0 && count < k; i--) {
            for (int num : buckets[i]) {
                if (count < k) {
                    result[count++] = num;
                }
            }
        }
        
        return result;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();
        
        // Test Case 1: Standard case
        int[] nums1 = {1,1,1,2,2,3};
        int k1 = 2;
        System.out.println("Test Case 1 (Heap):");
        System.out.println(Arrays.toString(solution.topKFrequent(nums1, k1)));
        System.out.println("Test Case 1 (Bucket Sort):");
        System.out.println(Arrays.toString(solution.topKFrequentBucketSort(nums1, k1)));
        
        // Test Case 2: Single number
        int[] nums2 = {1};
        int k2 = 1;
        System.out.println("\nTest Case 2 (Heap):");
        System.out.println(Arrays.toString(solution.topKFrequent(nums2, k2)));
        System.out.println("Test Case 2 (Bucket Sort):");
        System.out.println(Arrays.toString(solution.topKFrequentBucketSort(nums2, k2)));
        
        // Test Case 3: All numbers have same frequency
        int[] nums3 = {1,2,3,4};
        int k3 = 2;
        System.out.println("\nTest Case 3 (Heap):");
        System.out.println(Arrays.toString(solution.topKFrequent(nums3, k3)));
        System.out.println("Test Case 3 (Bucket Sort):");
        System.out.println(Arrays.toString(solution.topKFrequentBucketSort(nums3, k3)));
    }
}
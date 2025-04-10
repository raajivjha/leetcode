package leet75.moredatastructures;

import leet75.datastructures.ListNode;
import java.util.PriorityQueue;

/**
 * Problem: Merge K Sorted Lists
 * 
 * Description:
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 * 
 * Example:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * 
 * Approaches:
 * 1. Using MinHeap:
 *    - Add first node from each list to MinHeap
 *    - Poll smallest node and add its next node to heap
 *    - Continue until heap is empty
 * 
 * 2. Divide and Conquer:
 *    - Pair up lists and merge them
 *    - Continue until only one list remains
 * 
 * Time Complexity: O(N log k) where N is total nodes, k is number of lists
 * Space Complexity: O(k) for the heap or recursion stack
 */
public class MergeKSortedLists {
    
    // Solution using MinHeap
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // Create MinHeap comparing nodes by their values
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        // Add first node of each list to heap
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        
        // Build merged list using heap
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            curr.next = node;
            curr = curr.next;
            
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }
        
        return dummy.next;
    }
    
    // Alternative solution using Divide and Conquer
    public ListNode mergeKListsDivideConquer(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }
    
    private ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        if (start + 1 == end) {
            return mergeTwoLists(lists[start], lists[end]);
        }
        
        int mid = start + (end - start) / 2;
        ListNode left = mergeKListsHelper(lists, start, mid);
        ListNode right = mergeKListsHelper(lists, mid + 1, end);
        return mergeTwoLists(left, right);
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        
        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    // Helper method to create a linked list from array
    private ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : arr) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }
    
    // Helper method to print linked list
    private void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    // Test method to verify solution
    public static void main(String[] args) {
        MergeKSortedLists solution = new MergeKSortedLists();
        
        // Test Case 1: Standard case with 3 lists
        int[][] arrays1 = {{1,4,5}, {1,3,4}, {2,6}};
        ListNode[] lists1 = new ListNode[arrays1.length];
        for (int i = 0; i < arrays1.length; i++) {
            lists1[i] = solution.createList(arrays1[i]);
        }
        
        System.out.println("Test Case 1:");
        System.out.println("Original lists:");
        for (ListNode list : lists1) {
            solution.printList(list);
        }
        System.out.println("Merged list (MinHeap):");
        solution.printList(solution.mergeKLists(lists1));
        
        // Reset lists for divide and conquer approach
        for (int i = 0; i < arrays1.length; i++) {
            lists1[i] = solution.createList(arrays1[i]);
        }
        System.out.println("Merged list (Divide and Conquer):");
        solution.printList(solution.mergeKListsDivideConquer(lists1));
        
        // Test Case 2: Empty lists
        ListNode[] lists2 = new ListNode[3];
        System.out.println("\nTest Case 2 (Empty lists):");
        System.out.println("MinHeap approach: ");
        solution.printList(solution.mergeKLists(lists2));
        System.out.println("Divide and Conquer approach: ");
        solution.printList(solution.mergeKListsDivideConquer(lists2));
        
        // Test Case 3: Single list
        int[] array3 = {1, 2, 3};
        ListNode[] lists3 = new ListNode[]{solution.createList(array3)};
        System.out.println("\nTest Case 3 (Single list):");
        System.out.println("Original list:");
        solution.printList(lists3[0]);
        System.out.println("MinHeap approach: ");
        solution.printList(solution.mergeKLists(lists3));
        System.out.println("Divide and Conquer approach: ");
        solution.printList(solution.mergeKListsDivideConquer(lists3));
    }
}
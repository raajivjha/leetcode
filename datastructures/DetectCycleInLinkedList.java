package leet75.datastructures;

/**
 * Problem: Detect Cycle in a Linked List
 * 
 * Description:
 * Given head of a linked list, determine if the linked list has a cycle in it.
 * There is a cycle in a linked list if there is some node in the list that can 
 * be reached again by continuously following the next pointer.
 * 
 * Example:
 * Input: head = [3,2,0,-4], pos = 1 (position where tail connects to)
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 * 
 * Approach (Floyd's Cycle Finding Algorithm):
 * 1. Use two pointers: slow (moves one step) and fast (moves two steps)
 * 2. If there's a cycle, the pointers will meet
 * 3. If fast reaches null, there's no cycle
 * 
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(1) as we only use two pointers
 */
public class DetectCycleInLinkedList {
    
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        // Initialize slow and fast pointers
        ListNode slow = head;
        ListNode fast = head;
        
        // Move pointers until they meet or fast reaches end
        while (fast != null && fast.next != null) {
            slow = slow.next;          // Move one step
            fast = fast.next.next;     // Move two steps
            
            // If pointers meet, there's a cycle
            if (slow == fast) {
                return true;
            }
        }
        
        // Fast reached end, no cycle
        return false;
    }

    // Helper method to create a linked list with a cycle
    private ListNode createCyclicList(int[] arr, int pos) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        
        // Create the list
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        ListNode cycleNode = null;
        
        // Create nodes and track cycle position
        for (int i = 0; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
            if (i == pos) {
                cycleNode = curr;
            }
        }
        
        // Create cycle if pos is valid
        if (pos >= 0 && pos < arr.length) {
            curr.next = cycleNode;
        }
        
        return dummy.next;
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        DetectCycleInLinkedList solution = new DetectCycleInLinkedList();
        
        // Test case 1: List with cycle
        int[] arr1 = {3, 2, 0, -4};
        ListNode head1 = solution.createCyclicList(arr1, 1);
        System.out.println("Test case 1 (should be true): " + 
                          solution.hasCycle(head1));
        
        // Test case 2: List without cycle
        int[] arr2 = {1, 2, 3, 4};
        ListNode head2 = solution.createCyclicList(arr2, -1);
        System.out.println("Test case 2 (should be false): " + 
                          solution.hasCycle(head2));
        
        // Test case 3: Single node with self-cycle
        int[] arr3 = {1};
        ListNode head3 = solution.createCyclicList(arr3, 0);
        System.out.println("Test case 3 (should be true): " + 
                          solution.hasCycle(head3));
        
        // Test case 4: Empty list
        ListNode head4 = null;
        System.out.println("Test case 4 (should be false): " + 
                          solution.hasCycle(head4));
    }
}
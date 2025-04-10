package leet75.datastructures;

/**
 * Problem: Reverse a Linked List
 * 
 * Description:
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * 
 * Example:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * 
 * Approach 1 (Iterative):
 * 1. Use three pointers (prev, curr, next) to reverse links
 * 2. For each node:
 *    - Save next node
 *    - Reverse current node's pointer
 *    - Move prev and curr forward
 * 3. Return new head (prev)
 * 
 * Approach 2 (Recursive):
 * 1. Base case: null or single node
 * 2. Recursively reverse the rest of the list
 * 3. Fix the links:
 *    - Make next node point to current
 *    - Make current point to null
 * 4. Return new head
 * 
 * Time Complexity: O(n) for both approaches
 * Space Complexity: 
 * - O(1) for iterative approach
 * - O(n) for recursive approach (due to call stack)
 */
public class ReverseLinkedList {
    
    // Iterative solution
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            // Save next node
            ListNode nextTemp = curr.next;
            // Reverse current node's pointer
            curr.next = prev;
            // Move prev and curr one step forward
            prev = curr;
            curr = nextTemp;
        }
        
        return prev;
    }
    
    // Recursive solution
    public ListNode reverseListRecursive(ListNode head) {
        // Base case: null or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursively reverse the rest of the list
        ListNode newHead = reverseListRecursive(head.next);
        
        // Fix the links
        head.next.next = head;
        head.next = null;
        
        return newHead;
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

    // Test method to verify both solutions
    public static void main(String[] args) {
        ReverseLinkedList solution = new ReverseLinkedList();
        
        // Test case 1: Standard case
        int[] arr1 = {1, 2, 3, 4, 5};
        ListNode head1 = solution.createList(arr1);
        System.out.println("Original list:");
        solution.printList(head1);
        System.out.println("Reversed list (iterative):");
        solution.printList(solution.reverseList(head1));
        
        // Test case 2: Single node
        ListNode head2 = new ListNode(1);
        System.out.println("\nOriginal list:");
        solution.printList(head2);
        System.out.println("Reversed list (recursive):");
        solution.printList(solution.reverseListRecursive(head2));
        
        // Test case 3: Empty list
        ListNode head3 = null;
        System.out.println("\nOriginal list:");
        solution.printList(head3);
        System.out.println("Reversed list (iterative):");
        solution.printList(solution.reverseList(head3));
        
        // Test case 4: Two nodes
        int[] arr4 = {1, 2};
        ListNode head4 = solution.createList(arr4);
        System.out.println("\nOriginal list:");
        solution.printList(head4);
        System.out.println("Reversed list (recursive):");
        solution.printList(solution.reverseListRecursive(head4));
    }
}
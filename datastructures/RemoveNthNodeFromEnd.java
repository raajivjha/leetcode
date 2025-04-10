package leet75.datastructures;

/**
 * Problem: Remove Nth Node From End of List
 * 
 * Description:
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * Follow up: Could you do this in one pass?
 * 
 * Example:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * 
 * Approach (Two Pointers):
 * 1. Use two pointers: fast and slow
 * 2. Move fast pointer n steps ahead
 * 3. Move both pointers until fast reaches the end
 * 4. Remove the node at slow pointer
 * 5. Handle edge cases (empty list, removing first node)
 * 
 * Time Complexity: O(n) - one pass through the list
 * Space Complexity: O(1) - constant extra space
 */
public class RemoveNthNodeFromEnd {
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Handle edge case: empty list
        if (head == null) {
            return null;
        }
        
        // Create dummy node to handle edge case of removing first node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        // Move fast pointer n steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // Move both pointers until fast reaches end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        // Remove nth node from end
        slow.next = slow.next.next;
        
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

    // Test method to verify the solution
    public static void main(String[] args) {
        RemoveNthNodeFromEnd solution = new RemoveNthNodeFromEnd();
        
        // Test case 1: Remove 2nd node from end
        int[] arr1 = {1, 2, 3, 4, 5};
        ListNode head1 = solution.createList(arr1);
        System.out.println("Test case 1 - Original:");
        solution.printList(head1);
        head1 = solution.removeNthFromEnd(head1, 2);
        System.out.println("After removing 2nd node from end:");
        solution.printList(head1);
        
        // Test case 2: Remove first node
        int[] arr2 = {1, 2, 3};
        ListNode head2 = solution.createList(arr2);
        System.out.println("\nTest case 2 - Original:");
        solution.printList(head2);
        head2 = solution.removeNthFromEnd(head2, 3);
        System.out.println("After removing 3rd node from end (first node):");
        solution.printList(head2);
        
        // Test case 3: Remove last node
        int[] arr3 = {1, 2};
        ListNode head3 = solution.createList(arr3);
        System.out.println("\nTest case 3 - Original:");
        solution.printList(head3);
        head3 = solution.removeNthFromEnd(head3, 1);
        System.out.println("After removing last node:");
        solution.printList(head3);
        
        // Test case 4: Single node
        ListNode head4 = new ListNode(1);
        System.out.println("\nTest case 4 - Original:");
        solution.printList(head4);
        head4 = solution.removeNthFromEnd(head4, 1);
        System.out.println("After removing only node:");
        solution.printList(head4);
    }
}
package leet75.moredatastructures;

import leet75.nonlinear.TreeNode;

/**
 * Problem: Kth Smallest Element in a BST
 * 
 * Description:
 * Given the root of a binary search tree, and an integer k, return the kth smallest 
 * value (1-indexed) of all the values of the nodes in the tree.
 * 
 * Example:
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 * 
 * Approach:
 * 1. Use inorder traversal (gives sorted order in BST)
 * 2. Count nodes visited during traversal
 * 3. Return value when kth node is reached
 * 4. Can be done iteratively or recursively
 * 
 * Time Complexity: O(H + k) where H is height of tree
 * Space Complexity: O(H) for recursion stack
 */
public class KthSmallestElementBST {
    private int count = 0;
    private int result = 0;
    
    // Recursive solution
    public int kthSmallest(TreeNode root, int k) {
        count = k;
        inorderTraversal(root);
        return result;
    }
    
    private void inorderTraversal(TreeNode node) {
        if (node == null || count == 0) {
            return;
        }
        
        // Visit left subtree
        inorderTraversal(node.left);
        
        // Process current node
        count--;
        if (count == 0) {
            result = node.val;
            return;
        }
        
        // Visit right subtree
        inorderTraversal(node.right);
    }
    
    // Iterative solution using stack
    public int kthSmallestIterative(TreeNode root, int k) {
        java.util.Stack<TreeNode> stack = new java.util.Stack<>();
        TreeNode current = root;
        int count = 0;
        
        while (current != null || !stack.isEmpty()) {
            // Traverse to leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            // Process current node
            current = stack.pop();
            count++;
            
            if (count == k) {
                return current.val;
            }
            
            // Move to right subtree
            current = current.right;
        }
        
        return -1; // k is larger than number of nodes
    }

    // Test method to verify solution
    public static void main(String[] args) {
        KthSmallestElementBST solution = new KthSmallestElementBST();
        
        // Test Case 1: Standard BST
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);
        
        System.out.println("Test Case 1:");
        System.out.println("Recursive - 1st smallest: " + solution.kthSmallest(root1, 1));  // Expected: 1
        System.out.println("Iterative - 1st smallest: " + solution.kthSmallestIterative(root1, 1));  // Expected: 1
        
        // Test Case 2: Larger BST
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.left.left.left = new TreeNode(1);
        
        System.out.println("\nTest Case 2:");
        System.out.println("Recursive - 3rd smallest: " + solution.kthSmallest(root2, 3));  // Expected: 3
        System.out.println("Iterative - 3rd smallest: " + solution.kthSmallestIterative(root2, 3));  // Expected: 3
        
        // Test Case 3: Single node
        TreeNode root3 = new TreeNode(1);
        System.out.println("\nTest Case 3:");
        System.out.println("Recursive - 1st smallest: " + solution.kthSmallest(root3, 1));  // Expected: 1
        System.out.println("Iterative - 1st smallest: " + solution.kthSmallestIterative(root3, 1));  // Expected: 1
    }
}
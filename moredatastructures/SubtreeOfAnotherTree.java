package leet75.moredatastructures;

import leet75.nonlinear.TreeNode;

/**
 * Problem: Subtree of Another Tree
 * 
 * Description:
 * Given the roots of two binary trees root and subRoot, return true if there is a 
 * subtree of root with the same structure and node values of subRoot and false otherwise.
 * 
 * Example:
 * Input: root = [3,4,5,1,2], subRoot = [4,1,2]
 * Output: true
 * 
 * Approach:
 * 1. For each node in the main tree:
 *    - Check if the subtree rooted at this node matches the subRoot tree
 *    - If match found, return true
 * 2. For checking if two trees are same:
 *    - Use recursive comparison of nodes
 *    - Check values and structure match
 * 
 * Time Complexity: O(m*n) where m,n are sizes of trees
 * Space Complexity: O(h) where h is height of main tree
 */
public class SubtreeOfAnotherTree {
    
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // Base cases
        if (root == null) {
            return subRoot == null;
        }
        if (subRoot == null) {
            return true;
        }
        
        // Check if current root matches subRoot
        if (isSameTree(root, subRoot)) {
            return true;
        }
        
        // Check left and right subtrees
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
    
    private boolean isSameTree(TreeNode p, TreeNode q) {
        // Both null - trees are same
        if (p == null && q == null) {
            return true;
        }
        
        // One null, other not null - trees are different
        if (p == null || q == null) {
            return false;
        }
        
        // Values different - trees are different
        if (p.val != q.val) {
            return false;
        }
        
        // Recursively check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // Test method to verify solution
    public static void main(String[] args) {
        SubtreeOfAnotherTree solution = new SubtreeOfAnotherTree();
        
        // Test Case 1: Standard case from example
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(2);
        
        TreeNode subRoot1 = new TreeNode(4);
        subRoot1.left = new TreeNode(1);
        subRoot1.right = new TreeNode(2);
        
        System.out.println("Test Case 1: " + solution.isSubtree(root1, subRoot1));  // Expected: true
        
        // Test Case 2: Not a subtree (different values)
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(5);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(2);
        root2.left.right.left = new TreeNode(0);
        
        TreeNode subRoot2 = new TreeNode(4);
        subRoot2.left = new TreeNode(1);
        subRoot2.right = new TreeNode(2);
        
        System.out.println("Test Case 2: " + solution.isSubtree(root2, subRoot2));  // Expected: false
        
        // Test Case 3: Empty trees
        System.out.println("Test Case 3: " + solution.isSubtree(null, null));  // Expected: true
        
        // Test Case 4: Single node trees
        TreeNode root4 = new TreeNode(1);
        TreeNode subRoot4 = new TreeNode(1);
        System.out.println("Test Case 4: " + solution.isSubtree(root4, subRoot4));  // Expected: true
    }
}
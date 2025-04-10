package leet75.moredatastructures;

import leet75.nonlinear.TreeNode;

/**
 * Problem: Lowest Common Ancestor of BST
 * 
 * Description:
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes p and q.
 * The LCA is the lowest node in the tree that has both p and q as descendants.
 * 
 * Example:
 * Input: root = [6,2,8,0,4,7,9], p = 2, q = 8
 * Output: 6 (LCA of nodes 2 and 8 is 6)
 * 
 * Approach:
 * 1. Use BST property: left subtree values < node value < right subtree values
 * 2. If both p and q are less than current node, LCA is in left subtree
 * 3. If both p and q are greater than current node, LCA is in right subtree
 * 4. Otherwise, current node is the LCA
 * 
 * Time Complexity: O(h) where h is height of tree
 * Space Complexity: O(1) for iterative, O(h) for recursive
 */
public class LowestCommonAncestorBST {
    
    // Iterative solution
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode current = root;
        
        while (current != null) {
            // Both nodes are in right subtree
            if (p.val > current.val && q.val > current.val) {
                current = current.right;
            }
            // Both nodes are in left subtree
            else if (p.val < current.val && q.val < current.val) {
                current = current.left;
            }
            // Nodes are in different subtrees or one is the current node
            else {
                return current;
            }
        }
        
        return null;
    }
    
    // Recursive solution (alternative approach)
    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        // Base case
        if (root == null) {
            return null;
        }
        
        // Both nodes are in right subtree
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestorRecursive(root.right, p, q);
        }
        // Both nodes are in left subtree
        else if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestorRecursive(root.left, p, q);
        }
        // Nodes are in different subtrees or one is the current node
        else {
            return root;
        }
    }

    // Test method to verify solution
    public static void main(String[] args) {
        LowestCommonAncestorBST solution = new LowestCommonAncestorBST();
        
        // Test Case 1: Standard BST
        TreeNode root1 = new TreeNode(6);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(7);
        root1.right.right = new TreeNode(9);
        
        TreeNode p1 = root1.left;  // node with value 2
        TreeNode q1 = root1.right; // node with value 8
        
        TreeNode lca1 = solution.lowestCommonAncestor(root1, p1, q1);
        System.out.println("Test Case 1 (Iterative): " + lca1.val);  // Expected: 6
        System.out.println("Test Case 1 (Recursive): " + 
            solution.lowestCommonAncestorRecursive(root1, p1, q1).val);  // Expected: 6
        
        // Test Case 2: One node is ancestor of other
        TreeNode p2 = root1.left;  // node with value 2
        TreeNode q2 = root1.left.right;  // node with value 4
        
        TreeNode lca2 = solution.lowestCommonAncestor(root1, p2, q2);
        System.out.println("\nTest Case 2 (Iterative): " + lca2.val);  // Expected: 2
        System.out.println("Test Case 2 (Recursive): " + 
            solution.lowestCommonAncestorRecursive(root1, p2, q2).val);  // Expected: 2
        
        // Test Case 3: Single node tree
        TreeNode root3 = new TreeNode(1);
        TreeNode lca3 = solution.lowestCommonAncestor(root3, root3, root3);
        System.out.println("\nTest Case 3 (Iterative): " + lca3.val);  // Expected: 1
        System.out.println("Test Case 3 (Recursive): " + 
            solution.lowestCommonAncestorRecursive(root3, root3, root3).val);  // Expected: 1
    }
}
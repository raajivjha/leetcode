package leet75.nonlinear;

/**
 * Problem: Validate Binary Search Tree
 * 
 * Description:
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined as follows:
 * - The left subtree of a node contains only nodes with keys less than the node's key.
 * - The right subtree of a node contains only nodes with keys greater than the node's key.
 * - Both the left and right subtrees must also be binary search trees.
 * 
 * Example:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * Output: false (3 is not greater than 5)
 * 
 * Approach:
 * 1. Use recursion with valid range for each node
 * 2. For left subtree, upper bound is current node's value
 * 3. For right subtree, lower bound is current node's value
 * 4. Pass these bounds down during recursion
 * 
 * Time Complexity: O(n) - visit each node once
 * Space Complexity: O(h) - recursion stack where h is height of tree
 */
public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }
    
    private boolean validate(TreeNode node, Integer min, Integer max) {
        // Base case: empty tree is valid BST
        if (node == null) {
            return true;
        }
        
        // Check if current node violates the min/max constraints
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) {
            return false;
        }
        
        // Recursively check left and right subtrees
        // Left subtree values must be less than node's value (new max)
        // Right subtree values must be greater than node's value (new min)
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    // Test method to verify solution
    public static void main(String[] args) {
        ValidateBinarySearchTree solution = new ValidateBinarySearchTree();
        
        // Test Case 1: Valid BST
        TreeNode validRoot = new TreeNode(5);
        validRoot.left = new TreeNode(3);
        validRoot.right = new TreeNode(7);
        validRoot.left.left = new TreeNode(1);
        validRoot.left.right = new TreeNode(4);
        System.out.println("Test Case 1 (Valid BST): " + solution.isValidBST(validRoot));  // Should print true
        
        // Test Case 2: Invalid BST (from example)
        TreeNode invalidRoot = new TreeNode(5);
        invalidRoot.left = new TreeNode(1);
        invalidRoot.right = new TreeNode(4);
        invalidRoot.right.left = new TreeNode(3);
        invalidRoot.right.right = new TreeNode(6);
        System.out.println("Test Case 2 (Invalid BST): " + solution.isValidBST(invalidRoot));  // Should print false
        
        // Test Case 3: Single node
        TreeNode singleNode = new TreeNode(1);
        System.out.println("Test Case 3 (Single Node): " + solution.isValidBST(singleNode));  // Should print true
        
        // Test Case 4: Tree with duplicate values
        TreeNode duplicateRoot = new TreeNode(1);
        duplicateRoot.left = new TreeNode(1);
        System.out.println("Test Case 4 (Duplicate Values): " + solution.isValidBST(duplicateRoot));  // Should print false
    }
}
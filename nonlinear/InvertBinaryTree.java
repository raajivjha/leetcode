package leet75.nonlinear;

/**
 * Problem: Invert/Flip Binary Tree
 * 
 * Description:
 * Given the root of a binary tree, invert the tree, and return its root.
 * For example:
 * Input:      4
 *           /   \
 *          2     7
 *         / \   / \
 *        1   3 6   9
 * 
 * Output:     4
 *           /   \
 *          7     2
 *         / \   / \
 *        9   6 3   1
 * 
 * Approach:
 * 1. Base case: if root is null, return null
 * 2. Recursively invert left and right subtrees
 * 3. Swap the left and right children
 * 4. Return the root
 * 
 * Time Complexity: O(n) - visit each node once
 * Space Complexity: O(h) - recursion stack, where h is height of tree
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        // Base case: empty tree
        if (root == null) {
            return null;
        }
        
        // Recursively invert left and right subtrees
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        
        // Swap children
        root.left = right;
        root.right = left;
        
        return root;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        InvertBinaryTree solution = new InvertBinaryTree();
        
        // Create test tree: [4,2,7,1,3,6,9]
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        
        // Print original tree structure (pre-order)
        System.out.println("Original Tree (pre-order):");
        printPreOrder(root);
        
        // Invert the tree
        root = solution.invertTree(root);
        
        // Print inverted tree structure (pre-order)
        System.out.println("\nInverted Tree (pre-order):");
        printPreOrder(root);
    }
    
    // Helper method to print tree in pre-order
    private static void printPreOrder(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }
}
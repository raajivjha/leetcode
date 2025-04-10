package leet75.nonlinear;

/**
 * Problem: Binary Tree Maximum Path Sum
 * 
 * Description:
 * Given a non-empty binary tree, find the maximum path sum. A path is defined as any
 * sequence of nodes from some starting node to any node in the tree along the parent-child
 * connections. The path must contain at least one node and does not need to go through the root.
 * 
 * Example:
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: Optimal path is 2 -> 1 -> 3 with path sum = 2 + 1 + 3 = 6
 * 
 * Approach:
 * 1. Use recursion to compute:
 *    a) Maximum path sum through current node (for parent's calculation)
 *    b) Maximum path sum including current node as turn-around point
 * 2. At each node:
 *    - Calculate left and right max paths
 *    - Update global maximum with current node as turn-around point
 *    - Return maximum single path through current node
 * 3. Handle negative values by using Math.max with 0
 * 
 * Time Complexity: O(n) - visit each node once
 * Space Complexity: O(h) - recursion stack where h is height of tree
 */
public class BinaryTreeMaximumPathSum {
    private int maxSum;
    
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return maxSum;
    }
    
    private int maxPathSumHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        // Get max path sums from left and right subtrees
        // Use Math.max with 0 to handle negative values
        int leftMax = Math.max(0, maxPathSumHelper(node.left));
        int rightMax = Math.max(0, maxPathSumHelper(node.right));
        
        // Update global maximum (current node as turn-around point)
        maxSum = Math.max(maxSum, leftMax + rightMax + node.val);
        
        // Return maximum path sum through current node (for parent's calculation)
        return Math.max(leftMax, rightMax) + node.val;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        BinaryTreeMaximumPathSum solution = new BinaryTreeMaximumPathSum();
        
        // Test Case 1: Simple tree with positive values
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        System.out.println("Test Case 1: " + solution.maxPathSum(root1));  // Expected: 6
        
        // Test Case 2: Tree with negative values
        TreeNode root2 = new TreeNode(-10);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);
        System.out.println("Test Case 2: " + solution.maxPathSum(root2));  // Expected: 42
        
        // Test Case 3: Single node
        TreeNode root3 = new TreeNode(-3);
        System.out.println("Test Case 3: " + solution.maxPathSum(root3));  // Expected: -3
        
        // Test Case 4: All negative values
        TreeNode root4 = new TreeNode(-1);
        root4.left = new TreeNode(-2);
        root4.right = new TreeNode(-3);
        System.out.println("Test Case 4: " + solution.maxPathSum(root4));  // Expected: -1
        
        // Test Case 5: Complex tree with mixed values
        TreeNode root5 = new TreeNode(5);
        root5.left = new TreeNode(4);
        root5.right = new TreeNode(8);
        root5.left.left = new TreeNode(11);
        root5.left.left.left = new TreeNode(7);
        root5.left.left.right = new TreeNode(2);
        root5.right.left = new TreeNode(13);
        root5.right.right = new TreeNode(4);
        root5.right.right.right = new TreeNode(1);
        System.out.println("Test Case 5: " + solution.maxPathSum(root5));  // Expected: 48
    }
}
package leet75.nonlinear;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem: Maximum Depth of Binary Tree
 * 
 * Description:
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 * 
 * Example:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * 
 * Approaches:
 * 1. Recursive DFS:
 *    - Base case: null node has depth 0
 *    - Return 1 + max(left subtree depth, right subtree depth)
 * 
 * 2. Iterative BFS (Level Order):
 *    - Use queue to track nodes at each level
 *    - Increment depth for each level processed
 * 
 * Time Complexity: O(n) for both approaches
 * Space Complexity: 
 * - DFS: O(h) for recursion stack where h is height
 * - BFS: O(w) where w is max width of tree
 */
public class MaximumDepthBinaryTree {
    // Recursive DFS approach
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    
    // Iterative BFS approach
    public int maxDepthBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            depth++;
            
            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        return depth;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        MaximumDepthBinaryTree solution = new MaximumDepthBinaryTree();
        
        // Test Case 1: Standard tree
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        
        System.out.println("Test Case 1:");
        System.out.println("DFS approach: " + solution.maxDepth(root1));  // Expected: 3
        System.out.println("BFS approach: " + solution.maxDepthBFS(root1));  // Expected: 3
        
        // Test Case 2: Empty tree
        TreeNode root2 = null;
        System.out.println("\nTest Case 2 (Empty Tree):");
        System.out.println("DFS approach: " + solution.maxDepth(root2));  // Expected: 0
        System.out.println("BFS approach: " + solution.maxDepthBFS(root2));  // Expected: 0
        
        // Test Case 3: Single node
        TreeNode root3 = new TreeNode(1);
        System.out.println("\nTest Case 3 (Single Node):");
        System.out.println("DFS approach: " + solution.maxDepth(root3));  // Expected: 1
        System.out.println("BFS approach: " + solution.maxDepthBFS(root3));  // Expected: 1
        
        // Test Case 4: Left-skewed tree
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.left.left = new TreeNode(3);
        root4.left.left.left = new TreeNode(4);
        
        System.out.println("\nTest Case 4 (Left-skewed Tree):");
        System.out.println("DFS approach: " + solution.maxDepth(root4));  // Expected: 4
        System.out.println("BFS approach: " + solution.maxDepthBFS(root4));  // Expected: 4
        
        // Test Case 5: Complete binary tree
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.right = new TreeNode(3);
        root5.left.left = new TreeNode(4);
        root5.left.right = new TreeNode(5);
        root5.right.left = new TreeNode(6);
        root5.right.right = new TreeNode(7);
        
        System.out.println("\nTest Case 5 (Complete Binary Tree):");
        System.out.println("DFS approach: " + solution.maxDepth(root5));  // Expected: 3
        System.out.println("BFS approach: " + solution.maxDepthBFS(root5));  // Expected: 3
    }
}
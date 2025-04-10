package leet75.nonlinear;

import java.util.*;

/**
 * Problem: Binary Tree Level Order Traversal
 * 
 * Description:
 * Given the root of a binary tree, return the level order traversal of its nodes' values.
 * (i.e., from left to right, level by level).
 * 
 * Example:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * 
 * Approach:
 * 1. Use BFS with a queue to process nodes level by level
 * 2. For each level:
 *    - Get the size of current level
 *    - Process all nodes at current level
 *    - Add their children to queue for next level
 * 3. Add each level's values to result list
 * 
 * Alternative Approach (DFS):
 * - Use DFS with level tracking
 * - Add values to corresponding level in result list
 * 
 * Time Complexity: O(n) where n is number of nodes
 * Space Complexity: O(w) where w is maximum width of tree
 */
public class BinaryTreeLevelOrder {
    // BFS approach
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                
                // Add children to queue for next level
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            
            result.add(currentLevel);
        }
        
        return result;
    }
    
    // DFS approach
    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        levelOrderDFS(root, 0, result);
        return result;
    }
    
    private void levelOrderDFS(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null) {
            return;
        }
        
        // Add new level list if needed
        if (level >= result.size()) {
            result.add(new ArrayList<>());
        }
        
        // Add current node's value to its level
        result.get(level).add(node.val);
        
        // Process children at next level
        levelOrderDFS(node.left, level + 1, result);
        levelOrderDFS(node.right, level + 1, result);
    }

    // Test method to verify solution
    public static void main(String[] args) {
        BinaryTreeLevelOrder solution = new BinaryTreeLevelOrder();
        
        // Test Case 1: Standard binary tree
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        
        System.out.println("Test Case 1:");
        System.out.println("BFS approach: " + solution.levelOrder(root1));
        System.out.println("DFS approach: " + solution.levelOrderDFS(root1));
        
        // Test Case 2: Empty tree
        TreeNode root2 = null;
        System.out.println("\nTest Case 2 (Empty Tree):");
        System.out.println("BFS approach: " + solution.levelOrder(root2));
        System.out.println("DFS approach: " + solution.levelOrderDFS(root2));
        
        // Test Case 3: Single node
        TreeNode root3 = new TreeNode(1);
        System.out.println("\nTest Case 3 (Single Node):");
        System.out.println("BFS approach: " + solution.levelOrder(root3));
        System.out.println("DFS approach: " + solution.levelOrderDFS(root3));
        
        // Test Case 4: Complete binary tree
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(3);
        root4.left.left = new TreeNode(4);
        root4.left.right = new TreeNode(5);
        root4.right.left = new TreeNode(6);
        root4.right.right = new TreeNode(7);
        
        System.out.println("\nTest Case 4 (Complete Binary Tree):");
        System.out.println("BFS approach: " + solution.levelOrder(root4));
        System.out.println("DFS approach: " + solution.levelOrderDFS(root4));
        
        // Test Case 5: Left-skewed tree
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.left.left = new TreeNode(3);
        root5.left.left.left = new TreeNode(4);
        
        System.out.println("\nTest Case 5 (Left-skewed Tree):");
        System.out.println("BFS approach: " + solution.levelOrder(root5));
        System.out.println("DFS approach: " + solution.levelOrderDFS(root5));
    }
}
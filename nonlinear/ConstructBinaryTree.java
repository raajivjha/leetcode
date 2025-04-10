package leet75.nonlinear;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Construct Binary Tree from Preorder and Inorder Traversal
 * 
 * Description:
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal 
 * of a binary tree and inorder is the inorder traversal of the same tree, construct and 
 * return the binary tree.
 * 
 * Example:
 * Input: 
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * 
 * Approach:
 * 1. First element in preorder is always the root
 * 2. Find root's position in inorder to determine left and right subtrees
 * 3. Recursively construct left and right subtrees
 * 4. Use HashMap to store inorder indices for O(1) lookup
 * 
 * Time Complexity: O(n) - visit each node once
 * Space Complexity: O(n) - for storing HashMap and recursion stack
 */
public class ConstructBinaryTree {
    private Map<Integer, Integer> inorderMap;
    private int preorderIndex;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Create map of value -> index for inorder array
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        preorderIndex = 0;
        return buildTreeHelper(preorder, 0, inorder.length - 1);
    }
    
    private TreeNode buildTreeHelper(int[] preorder, int left, int right) {
        // Base case: no elements to construct the tree
        if (left > right) {
            return null;
        }
        
        // Create root node from current preorder element
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);
        
        // Find position of root in inorder traversal
        int rootIndex = inorderMap.get(rootValue);
        
        // Recursively build left and right subtrees
        // Left subtree: elements before root in inorder
        root.left = buildTreeHelper(preorder, left, rootIndex - 1);
        // Right subtree: elements after root in inorder
        root.right = buildTreeHelper(preorder, rootIndex + 1, right);
        
        return root;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        ConstructBinaryTree solution = new ConstructBinaryTree();
        
        // Test Case 1: Standard case
        int[] preorder1 = {3,9,20,15,7};
        int[] inorder1 = {9,3,15,20,7};
        TreeNode root1 = solution.buildTree(preorder1, inorder1);
        System.out.println("Test Case 1 - Level Order Traversal:");
        printLevelOrder(root1);
        
        // Test Case 2: Single node
        int[] preorder2 = {1};
        int[] inorder2 = {1};
        TreeNode root2 = solution.buildTree(preorder2, inorder2);
        System.out.println("\nTest Case 2 - Single Node:");
        printLevelOrder(root2);
        
        // Test Case 3: Left-skewed tree
        int[] preorder3 = {1,2,3};
        int[] inorder3 = {3,2,1};
        TreeNode root3 = solution.buildTree(preorder3, inorder3);
        System.out.println("\nTest Case 3 - Left-skewed Tree:");
        printLevelOrder(root3);
    }
    
    // Helper method to print level order traversal
    private static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            System.out.println();
        }
    }
}
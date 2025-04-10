package leet75.nonlinear;

import java.util.*;

/**
 * Problem: Serialize and Deserialize Binary Tree
 * 
 * Description:
 * Design an algorithm to serialize and deserialize a binary tree. The serialization 
 * and deserialization should work for any binary tree, including those with null nodes.
 * 
 * Example:
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * 
 * Approach:
 * 1. Serialization:
 *    - Use preorder traversal (root -> left -> right)
 *    - Use a special marker "null" for null nodes
 *    - Join values with a delimiter
 * 
 * 2. Deserialization:
 *    - Split string into tokens
 *    - Use a queue to process nodes in order
 *    - Recursively construct the tree
 * 
 * Time Complexity: 
 * - Serialize: O(n) where n is number of nodes
 * - Deserialize: O(n)
 * Space Complexity: O(n) for both operations
 */
public class SerializeDeserializeBinaryTree {
    private static final String NULL = "null";
    private static final String DELIMITER = ",";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return NULL;
        }
        
        // Use StringBuilder for efficient string concatenation
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }
    
    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL).append(DELIMITER);
            return;
        }
        
        // Preorder traversal: root -> left -> right
        sb.append(node.val).append(DELIMITER);
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.equals(NULL)) {
            return null;
        }
        
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(DELIMITER)));
        return deserializeHelper(queue);
    }
    
    private TreeNode deserializeHelper(Queue<String> queue) {
        String val = queue.poll();
        if (val == null || val.equals(NULL)) {
            return null;
        }
        
        // Create node and recursively construct left and right subtrees
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);
        
        return node;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        SerializeDeserializeBinaryTree solution = new SerializeDeserializeBinaryTree();
        
        // Test Case 1: Standard binary tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(5);
        
        System.out.println("Test Case 1:");
        String serialized1 = solution.serialize(root1);
        System.out.println("Serialized: " + serialized1);
        TreeNode deserialized1 = solution.deserialize(serialized1);
        System.out.println("Verify Serialization (should be same):");
        System.out.println("Re-serialized: " + solution.serialize(deserialized1));
        
        // Test Case 2: Empty tree
        TreeNode root2 = null;
        System.out.println("\nTest Case 2 (Empty Tree):");
        String serialized2 = solution.serialize(root2);
        System.out.println("Serialized: " + serialized2);
        TreeNode deserialized2 = solution.deserialize(serialized2);
        System.out.println("Verify Serialization (should be same):");
        System.out.println("Re-serialized: " + solution.serialize(deserialized2));
        
        // Test Case 3: Single node tree
        TreeNode root3 = new TreeNode(1);
        System.out.println("\nTest Case 3 (Single Node):");
        String serialized3 = solution.serialize(root3);
        System.out.println("Serialized: " + serialized3);
        TreeNode deserialized3 = solution.deserialize(serialized3);
        System.out.println("Verify Serialization (should be same):");
        System.out.println("Re-serialized: " + solution.serialize(deserialized3));
        
        // Test Case 4: Complex tree with null nodes
        TreeNode root4 = new TreeNode(5);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(3);
        root4.left.right = new TreeNode(4);
        root4.right.right = new TreeNode(1);
        
        System.out.println("\nTest Case 4 (Complex Tree):");
        String serialized4 = solution.serialize(root4);
        System.out.println("Serialized: " + serialized4);
        TreeNode deserialized4 = solution.deserialize(serialized4);
        System.out.println("Verify Serialization (should be same):");
        System.out.println("Re-serialized: " + solution.serialize(deserialized4));
    }
}
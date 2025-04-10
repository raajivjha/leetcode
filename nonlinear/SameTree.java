package leet75.nonlinear;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem: Same Tree
 * 
 * Description:
 * Given the roots of two binary trees p and q, write a function to check if they are
 * the same or not. Two binary trees are considered the same if they are structurally
 * identical, and the nodes have the same value.
 * 
 * Example:
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * 
 * Approaches:
 * 1. Recursive DFS:
 *    - Compare current nodes
 *    - Recursively compare left and right subtrees
 * 
 * 2. Iterative BFS:
 *    - Use two queues to traverse both trees simultaneously
 *    - Compare nodes at each level
 * 
 * Time Complexity: O(min(n,m)) where n,m are sizes of trees
 * Space Complexity: O(min(h1,h2)) for recursion, O(w) for BFS
 * where h1,h2 are heights and w is max width
 */
public class SameTree {
    // Recursive DFS approach
    public boolean isSameTree(TreeNode p, TreeNode q) {
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
    
    // Iterative BFS approach
    public boolean isSameTreeBFS(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        
        queue1.offer(p);
        queue2.offer(q);
        
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            
            if (!checkNodes(node1, node2)) {
                return false;
            }
            
            // If one node exists, both must exist at this point
            if (node1 != null) {
                queue1.offer(node1.left);
                queue1.offer(node1.right);
                queue2.offer(node2.left);
                queue2.offer(node2.right);
            }
        }
        
        return queue1.isEmpty() && queue2.isEmpty();
    }
    
    private boolean checkNodes(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        return node1.val == node2.val;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        SameTree solution = new SameTree();
        
        // Test Case 1: Identical trees
        TreeNode p1 = new TreeNode(1);
        p1.left = new TreeNode(2);
        p1.right = new TreeNode(3);
        
        TreeNode q1 = new TreeNode(1);
        q1.left = new TreeNode(2);
        q1.right = new TreeNode(3);
        
        System.out.println("Test Case 1:");
        System.out.println("DFS approach: " + solution.isSameTree(p1, q1));  // Expected: true
        System.out.println("BFS approach: " + solution.isSameTreeBFS(p1, q1));  // Expected: true
        
        // Test Case 2: Different values
        TreeNode p2 = new TreeNode(1);
        p2.left = new TreeNode(2);
        
        TreeNode q2 = new TreeNode(1);
        q2.left = new TreeNode(3);
        
        System.out.println("\nTest Case 2 (Different values):");
        System.out.println("DFS approach: " + solution.isSameTree(p2, q2));  // Expected: false
        System.out.println("BFS approach: " + solution.isSameTreeBFS(p2, q2));  // Expected: false
        
        // Test Case 3: Different structures
        TreeNode p3 = new TreeNode(1);
        p3.left = new TreeNode(2);
        
        TreeNode q3 = new TreeNode(1);
        q3.right = new TreeNode(2);
        
        System.out.println("\nTest Case 3 (Different structures):");
        System.out.println("DFS approach: " + solution.isSameTree(p3, q3));  // Expected: false
        System.out.println("BFS approach: " + solution.isSameTreeBFS(p3, q3));  // Expected: false
        
        // Test Case 4: Empty trees
        System.out.println("\nTest Case 4 (Empty trees):");
        System.out.println("DFS approach: " + solution.isSameTree(null, null));  // Expected: true
        System.out.println("BFS approach: " + solution.isSameTreeBFS(null, null));  // Expected: true
        
        // Test Case 5: One empty, one not
        TreeNode p5 = new TreeNode(1);
        
        System.out.println("\nTest Case 5 (One empty, one not):");
        System.out.println("DFS approach: " + solution.isSameTree(p5, null));  // Expected: false
        System.out.println("BFS approach: " + solution.isSameTreeBFS(p5, null));  // Expected: false
    }
}
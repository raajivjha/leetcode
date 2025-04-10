package leet75.nonlinear;

import java.util.*;

/**
 * Problem: Clone Graph
 * 
 * Description:
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone)
 * of the graph. Each node in the graph contains a value (int) and a list of its neighbors.
 * 
 * Example:
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * 
 * Approach:
 * 1. Use DFS or BFS traversal with a HashMap to track cloned nodes
 * 2. For each node:
 *    - If already cloned, return the clone from HashMap
 *    - Otherwise, create new node and recursively clone neighbors
 * 3. Store mapping of original node to cloned node to handle cycles
 * 
 * Time Complexity: O(V + E) where V is vertices and E is edges
 * Space Complexity: O(V) for HashMap and recursion stack
 */
public class CloneGraph {
    private Map<Node, Node> visited = new HashMap<>();
    
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        
        // If node was already cloned, return the clone
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        
        // Create clone of current node
        Node clone = new Node(node.val);
        visited.put(node, clone);
        
        // Recursively clone all neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }
        
        return clone;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        CloneGraph solution = new CloneGraph();
        
        // Test Case 1: Simple graph with 4 nodes
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        
        Node clonedNode = solution.cloneGraph(node1);
        System.out.println("Test Case 1:");
        printGraph(clonedNode, new HashSet<>());
        
        // Test Case 2: Single node
        Node singleNode = new Node(1);
        Node clonedSingleNode = solution.cloneGraph(singleNode);
        System.out.println("\nTest Case 2 (Single Node):");
        printGraph(clonedSingleNode, new HashSet<>());
        
        // Test Case 3: Null graph
        Node clonedNull = solution.cloneGraph(null);
        System.out.println("\nTest Case 3 (Null Graph):");
        System.out.println(clonedNull == null ? "null" : "not null");
    }
    
    // Helper method to print the graph using DFS
    private static void printGraph(Node node, Set<Node> visited) {
        if (node == null || visited.contains(node)) {
            return;
        }
        
        visited.add(node);
        System.out.print("Node " + node.val + " -> Neighbors: [");
        for (Node neighbor : node.neighbors) {
            System.out.print(neighbor.val + " ");
        }
        System.out.println("]");
        
        for (Node neighbor : node.neighbors) {
            printGraph(neighbor, visited);
        }
    }
}
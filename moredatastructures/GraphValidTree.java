package leet75.moredatastructures;

import java.util.*;

/**
 * Problem: Graph Valid Tree
 * 
 * Description:
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges, write a function 
 * to check whether these edges make up a valid tree.
 * 
 * Example:
 * Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * Output: true
 * 
 * Approach:
 * 1. A graph is a valid tree if:
 *    - It has exactly n-1 edges
 *    - It is fully connected
 *    - It contains no cycles
 * 2. Use Union-Find to check connectivity and detect cycles
 * 3. Verify edge count equals n-1
 * 
 * Alternative Approach:
 * 1. Use DFS/BFS to check connectivity
 * 2. Keep track of parent to avoid back edges
 * 3. Verify edge count and connectivity
 * 
 * Time Complexity: O(N*α(N)) for Union-Find, where α is inverse Ackermann function
 * Space Complexity: O(N) for the parent array
 */
public class GraphValidTree {
    // Solution using Union-Find
    public boolean validTree(int n, int[][] edges) {
        // A tree with n nodes must have exactly n-1 edges
        if (edges.length != n - 1) {
            return false;
        }
        
        UnionFind uf = new UnionFind(n);
        
        // Process each edge
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            
            // If nodes are already connected, adding this edge creates a cycle
            if (uf.find(x) == uf.find(y)) {
                return false;
            }
            
            uf.union(x, y);
        }
        
        // Check if graph is fully connected (only one component)
        int components = 0;
        for (int i = 0; i < n; i++) {
            if (uf.find(i) == i) {
                components++;
            }
        }
        
        return components == 1;
    }
    
    // Union-Find data structure implementation
    private class UnionFind {
        private int[] parent;
        private int[] rank;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }
    
    // Alternative solution using DFS
    public boolean validTreeDFS(int n, int[][] edges) {
        // Create adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Build undirected graph
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        // Keep track of visited nodes
        boolean[] visited = new boolean[n];
        
        // Check for cycle and connectivity starting from node 0
        if (hasCycle(0, -1, adj, visited)) {
            return false;
        }
        
        // Check if all nodes are connected
        for (boolean v : visited) {
            if (!v) return false;
        }
        
        // Must have n-1 edges for a valid tree
        return edges.length == n - 1;
    }
    
    private boolean hasCycle(int node, int parent, List<List<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                if (hasCycle(neighbor, node, adj, visited)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }
        
        return false;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        GraphValidTree solution = new GraphValidTree();
        
        // Test Case 1: Valid tree
        int n1 = 5;
        int[][] edges1 = {{0,1}, {0,2}, {0,3}, {1,4}};
        System.out.println("Test Case 1:");
        System.out.println("n = " + n1);
        System.out.println("edges = " + Arrays.deepToString(edges1));
        System.out.println("Union-Find approach: " + solution.validTree(n1, edges1));  // Expected: true
        System.out.println("DFS approach: " + solution.validTreeDFS(n1, edges1));      // Expected: true
        
        // Test Case 2: Graph with cycle
        int n2 = 5;
        int[][] edges2 = {{0,1}, {1,2}, {2,3}, {1,3}, {1,4}};
        System.out.println("\nTest Case 2:");
        System.out.println("n = " + n2);
        System.out.println("edges = " + Arrays.deepToString(edges2));
        System.out.println("Union-Find approach: " + solution.validTree(n2, edges2));  // Expected: false
        System.out.println("DFS approach: " + solution.validTreeDFS(n2, edges2));      // Expected: false
        
        // Test Case 3: Disconnected graph
        int n3 = 4;
        int[][] edges3 = {{0,1}, {2,3}};
        System.out.println("\nTest Case 3:");
        System.out.println("n = " + n3);
        System.out.println("edges = " + Arrays.deepToString(edges3));
        System.out.println("Union-Find approach: " + solution.validTree(n3, edges3));  // Expected: false
        System.out.println("DFS approach: " + solution.validTreeDFS(n3, edges3));      // Expected: false
        
        // Test Case 4: Single node
        int n4 = 1;
        int[][] edges4 = {};
        System.out.println("\nTest Case 4:");
        System.out.println("n = " + n4);
        System.out.println("edges = " + Arrays.deepToString(edges4));
        System.out.println("Union-Find approach: " + solution.validTree(n4, edges4));  // Expected: true
        System.out.println("DFS approach: " + solution.validTreeDFS(n4, edges4));      // Expected: true
    }
}
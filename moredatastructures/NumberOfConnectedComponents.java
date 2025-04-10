package leet75.moredatastructures;

import java.util.*;

/**
 * Problem: Number of Connected Components in an Undirected Graph
 * 
 * Description:
 * You have a graph of n nodes. You are given an integer n and an array edges where 
 * edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi.
 * Return the number of connected components in the graph.
 * 
 * Example:
 * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 * Output: 2
 * 
 * Approach 1 (Union-Find):
 * 1. Initialize each node as its own component
 * 2. Process each edge and union the nodes
 * 3. Count number of distinct roots
 * 
 * Approach 2 (DFS):
 * 1. Build adjacency list
 * 2. DFS from each unvisited node
 * 3. Count number of DFS calls needed
 * 
 * Time Complexity: O(N + E) where N is nodes and E is edges
 * Space Complexity: O(N) for the parent array or visited set
 */
public class NumberOfConnectedComponents {
    // Solution using Union-Find
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        
        // Process each edge
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        
        // Count number of distinct components
        int components = 0;
        for (int i = 0; i < n; i++) {
            if (uf.find(i) == i) {
                components++;
            }
        }
        
        return components;
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
    public int countComponentsDFS(int n, int[][] edges) {
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Add edges to adjacency list
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        // Keep track of visited nodes
        boolean[] visited = new boolean[n];
        int components = 0;
        
        // DFS from each unvisited node
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, adj, visited);
                components++;
            }
        }
        
        return components;
    }
    
    private void dfs(int node, List<List<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited);
            }
        }
    }

    // Test method to verify solution
    public static void main(String[] args) {
        NumberOfConnectedComponents solution = new NumberOfConnectedComponents();
        
        // Test Case 1: Standard case
        int n1 = 5;
        int[][] edges1 = {{0,1}, {1,2}, {3,4}};
        System.out.println("Test Case 1:");
        System.out.println("n = " + n1);
        System.out.println("edges = " + Arrays.deepToString(edges1));
        System.out.println("Union-Find approach: " + solution.countComponents(n1, edges1));  // Expected: 2
        System.out.println("DFS approach: " + solution.countComponentsDFS(n1, edges1));      // Expected: 2
        
        // Test Case 2: Single component
        int n2 = 4;
        int[][] edges2 = {{0,1}, {1,2}, {2,3}};
        System.out.println("\nTest Case 2:");
        System.out.println("n = " + n2);
        System.out.println("edges = " + Arrays.deepToString(edges2));
        System.out.println("Union-Find approach: " + solution.countComponents(n2, edges2));  // Expected: 1
        System.out.println("DFS approach: " + solution.countComponentsDFS(n2, edges2));      // Expected: 1
        
        // Test Case 3: No edges (all isolated nodes)
        int n3 = 3;
        int[][] edges3 = {};
        System.out.println("\nTest Case 3:");
        System.out.println("n = " + n3);
        System.out.println("edges = " + Arrays.deepToString(edges3));
        System.out.println("Union-Find approach: " + solution.countComponents(n3, edges3));  // Expected: 3
        System.out.println("DFS approach: " + solution.countComponentsDFS(n3, edges3));      // Expected: 3
        
        // Test Case 4: Multiple small components
        int n4 = 6;
        int[][] edges4 = {{0,1}, {2,3}, {4,5}};
        System.out.println("\nTest Case 4:");
        System.out.println("n = " + n4);
        System.out.println("edges = " + Arrays.deepToString(edges4));
        System.out.println("Union-Find approach: " + solution.countComponents(n4, edges4));  // Expected: 3
        System.out.println("DFS approach: " + solution.countComponentsDFS(n4, edges4));      // Expected: 3
    }
}
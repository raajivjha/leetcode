package leet75.datastructures;

import java.util.*;

/**
 * Problem: Pacific Atlantic Water Flow
 * 
 * Description:
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
 * where the Pacific ocean touches the left and top edges and the Atlantic ocean touches the right and bottom edges,
 * find the list of grid coordinates where water can flow to both the Pacific and Atlantic oceans.
 * 
 * Example:
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * 
 * Approach:
 * 1. Use DFS from both Pacific and Atlantic edges
 * 2. Keep track of cells reachable from both oceans
 * 3. Start DFS from edges and only move to adjacent cells with equal or higher height
 * 4. Find cells that can reach both oceans
 * 
 * Time Complexity: O(m*n) where m and n are dimensions of the matrix
 * Space Complexity: O(m*n) for visited sets
 */
public class PacificAtlanticWaterFlow {
    private int[][] heights;
    private int m, n;
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }
        
        this.heights = heights;
        this.m = heights.length;
        this.n = heights[0].length;
        
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        
        // DFS from Pacific edges (top and left)
        for (int i = 0; i < m; i++) {
            dfs(i, 0, pacific);
        }
        for (int j = 0; j < n; j++) {
            dfs(0, j, pacific);
        }
        
        // DFS from Atlantic edges (bottom and right)
        for (int i = 0; i < m; i++) {
            dfs(i, n-1, atlantic);
        }
        for (int j = 0; j < n; j++) {
            dfs(m-1, j, atlantic);
        }
        
        // Find cells reachable from both oceans
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        
        return result;
    }
    
    private void dfs(int i, int j, boolean[][] visited) {
        visited[i][j] = true;
        
        for (int[] dir : dirs) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            
            if (newI >= 0 && newI < m && newJ >= 0 && newJ < n 
                && !visited[newI][newJ] 
                && heights[newI][newJ] >= heights[i][j]) {
                dfs(newI, newJ, visited);
            }
        }
    }
}
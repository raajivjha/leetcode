package leet75.datastructures;

/**
 * Problem: Number of Islands
 * 
 * Description:
 * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically.
 * 
 * Example:
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * 
 * Approach (DFS):
 * 1. Iterate through each cell in the grid
 * 2. When we find a '1':
 *    - Increment island count
 *    - Use DFS to mark all connected '1's as visited (change to '0')
 * 3. Continue until all cells are processed
 * 
 * Time Complexity: O(m * n) where m is rows and n is columns
 * Space Complexity: O(m * n) in worst case for DFS stack
 */
public class NumberOfIslands {
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int islands = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Iterate through each cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    islands++;  // Found new island
                    dfs(grid, i, j);  // Mark all connected land
                }
            }
        }
        
        return islands;
    }
    
    // DFS to mark all connected land cells
    private void dfs(char[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Check bounds and if current cell is land
        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] != '1') {
            return;
        }
        
        // Mark current cell as visited
        grid[row][col] = '0';
        
        // Check all four directions
        dfs(grid, row + 1, col);  // Down
        dfs(grid, row - 1, col);  // Up
        dfs(grid, row, col + 1);  // Right
        dfs(grid, row, col - 1);  // Left
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        NumberOfIslands solution = new NumberOfIslands();
        
        // Test case 1: Single island
        char[][] grid1 = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        System.out.println("Test case 1 (should be 1): " + 
                          solution.numIslands(grid1));
        
        // Test case 2: Multiple islands
        char[][] grid2 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println("Test case 2 (should be 3): " + 
                          solution.numIslands(grid2));
        
        // Test case 3: No islands
        char[][] grid3 = {
            {'0','0','0','0','0'},
            {'0','0','0','0','0'}
        };
        System.out.println("Test case 3 (should be 0): " + 
                          solution.numIslands(grid3));
        
        // Test case 4: All islands
        char[][] grid4 = {
            {'1','1'},
            {'1','1'}
        };
        System.out.println("Test case 4 (should be 1): " + 
                          solution.numIslands(grid4));
        
        // Test case 5: Single cell
        char[][] grid5 = {{'1'}};
        System.out.println("Test case 5 (should be 1): " + 
                          solution.numIslands(grid5));
    }
}
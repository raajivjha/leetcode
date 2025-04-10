package leet75.moredatastructures;

import java.util.*;

/**
 * Problem: Word Search II
 * 
 * Description:
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells
 * are horizontally or vertically neighboring. The same letter cell may not be used more than once
 * in a word.
 * 
 * Example:
 * Input: 
 * board = [["o","a","a","n"],
 *          ["e","t","a","e"],
 *          ["i","h","k","r"],
 *          ["i","f","l","v"]]
 * words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 * 
 * Approach:
 * 1. Build a Trie from the words list for efficient prefix matching
 * 2. DFS through the board, maintaining visited cells
 * 3. For each cell, explore all possible paths that match words in Trie
 * 4. Use TrieNode to track complete words during traversal
 * 
 * Time Complexity: O(M * N * 4^L) where M,N are board dimensions, L is max word length
 * Space Complexity: O(K) where K is total length of all words
 */
public class WordSearchII {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    // Trie Node class
    private class TrieNode {
        Map<Character, TrieNode> children;
        String word;  // Store complete word at leaf nodes
        
        public TrieNode() {
            children = new HashMap<>();
            word = null;
        }
    }
    
    // Build Trie from words array
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
            }
            node.word = word;  // Store word at leaf node
        }
        return root;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }
        
        return result;
    }
    
    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        // Check boundaries and if current character exists in trie
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length ||
            board[i][j] == '#' || !node.children.containsKey(board[i][j])) {
            return;
        }
        
        char c = board[i][j];
        node = node.children.get(c);
        
        // If we found a word, add it to result
        if (node.word != null) {
            result.add(node.word);
            node.word = null;  // De-duplicate
        }
        
        // Mark current cell as visited
        board[i][j] = '#';
        
        // Explore all directions
        for (int[] dir : DIRECTIONS) {
            dfs(board, i + dir[0], j + dir[1], node, result);
        }
        
        // Restore the cell
        board[i][j] = c;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        WordSearchII solution = new WordSearchII();
        
        // Test Case 1: Standard case from example
        char[][] board1 = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        String[] words1 = {"oath","pea","eat","rain"};
        System.out.println("Test Case 1:");
        System.out.println("Board:");
        printBoard(board1);
        System.out.println("Words to find: " + Arrays.toString(words1));
        System.out.println("Found words: " + solution.findWords(board1, words1));  // Expected: [eat, oath]
        
        // Test Case 2: Single character words
        char[][] board2 = {
            {'a','b'},
            {'c','d'}
        };
        String[] words2 = {"a","b","c","d","ab","ac"};
        System.out.println("\nTest Case 2:");
        System.out.println("Board:");
        printBoard(board2);
        System.out.println("Words to find: " + Arrays.toString(words2));
        System.out.println("Found words: " + solution.findWords(board2, words2));  // Expected: [a, b, c, d, ab, ac]
        
        // Test Case 3: No words found
        char[][] board3 = {
            {'a','b'},
            {'c','d'}
        };
        String[] words3 = {"xyz","zzz"};
        System.out.println("\nTest Case 3:");
        System.out.println("Board:");
        printBoard(board3);
        System.out.println("Words to find: " + Arrays.toString(words3));
        System.out.println("Found words: " + solution.findWords(board3, words3));  // Expected: []
        
        // Test Case 4: Duplicate words
        char[][] board4 = {
            {'a','a','a'},
            {'a','a','a'},
            {'a','a','a'}
        };
        String[] words4 = {"a","aa","aaa","aaaa"};
        System.out.println("\nTest Case 4:");
        System.out.println("Board:");
        printBoard(board4);
        System.out.println("Words to find: " + Arrays.toString(words4));
        System.out.println("Found words: " + solution.findWords(board4, words4));  // Expected: [a, aa, aaa]
    }
    
    // Helper method to print the board
    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}
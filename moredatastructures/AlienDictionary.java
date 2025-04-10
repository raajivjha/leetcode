package leet75.moredatastructures;

import java.util.*;

/**
 * Problem: Alien Dictionary
 * 
 * Description:
 * There is a new alien language that uses the English alphabet. The order of letters
 * in this language is unknown to you. You are given a list of strings words from the
 * alien language's dictionary, where the strings are sorted lexicographically by the
 * rules of this new language. Return a string of the unique letters sorted in the
 * alien language's order. If there is no solution, return "". If multiple solutions
 * exist, return any of them.
 * 
 * Example:
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 * 
 * Approach:
 * 1. Build graph of letter dependencies:
 *    - Compare adjacent words to find character ordering
 *    - Create edges between characters in order
 * 2. Perform topological sort using:
 *    - Track indegree of each vertex
 *    - Use BFS/Queue for nodes with zero indegree
 * 3. Handle special cases and cycles
 * 
 * Time Complexity: O(C) where C is total length of all words
 * Space Complexity: O(1) since alphabet size is fixed at 26
 */
public class AlienDictionary {
    
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        
        // Step 1: Build adjacency list and indegree map
        Map<Character, Set<Character>> adj = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        
        // Initialize maps with all characters
        for (String word : words) {
            for (char c : word.toCharArray()) {
                adj.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }
        
        // Build graph by comparing adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            
            // Check if word2 is prefix of word1, which is invalid
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            
            // Find first different character
            int minLen = Math.min(word1.length(), word2.length());
            for (int j = 0; j < minLen; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                if (c1 != c2) {
                    // Add edge if not already present
                    if (!adj.get(c1).contains(c2)) {
                        adj.get(c1).add(c2);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    break;
                }
            }
        }
        
        // Step 2: Topological sort using BFS
        StringBuilder result = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        
        // Add characters with zero indegree to queue
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }
        
        // Process queue
        while (!queue.isEmpty()) {
            char curr = queue.poll();
            result.append(curr);
            
            // Reduce indegree of neighbors
            for (char next : adj.get(curr)) {
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }
        
        // Check if we found all characters (no cycle)
        return result.length() == inDegree.size() ? result.toString() : "";
    }

    // Test method to verify solution
    public static void main(String[] args) {
        AlienDictionary solution = new AlienDictionary();
        
        // Test Case 1: Standard case
        String[] words1 = {"wrt","wrf","er","ett","rftt"};
        System.out.println("Test Case 1:");
        System.out.println("Words: " + Arrays.toString(words1));
        System.out.println("Alien order: " + solution.alienOrder(words1));  // Expected: "wertf"
        
        // Test Case 2: Simple case
        String[] words2 = {"z","x"};
        System.out.println("\nTest Case 2:");
        System.out.println("Words: " + Arrays.toString(words2));
        System.out.println("Alien order: " + solution.alienOrder(words2));  // Expected: "zx"
        
        // Test Case 3: Invalid case (cycle)
        String[] words3 = {"z","x","z"};
        System.out.println("\nTest Case 3:");
        System.out.println("Words: " + Arrays.toString(words3));
        System.out.println("Alien order: " + solution.alienOrder(words3));  // Expected: ""
        
        // Test Case 4: Invalid case (prefix)
        String[] words4 = {"abc","ab"};
        System.out.println("\nTest Case 4:");
        System.out.println("Words: " + Arrays.toString(words4));
        System.out.println("Alien order: " + solution.alienOrder(words4));  // Expected: ""
        
        // Test Case 5: Multiple valid solutions
        String[] words5 = {"ac","ab","b"};
        System.out.println("\nTest Case 5:");
        System.out.println("Words: " + Arrays.toString(words5));
        System.out.println("Alien order: " + solution.alienOrder(words5));  // Expected: "acb" or "cab"
    }
}
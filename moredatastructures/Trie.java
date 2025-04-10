package leet75.moredatastructures;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Implement Trie (Prefix Tree)
 * 
 * Description:
 * Implement a trie with insert, search, and startsWith methods.
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently 
 * store and retrieve strings. The trie should support the following operations:
 * - Trie() Initializes the trie object.
 * - void insert(String word) Inserts the string word into the trie.
 * - boolean search(String word) Returns true if the string word is in the trie, false otherwise.
 * - boolean startsWith(String prefix) Returns true if there is a previously inserted string 
 *   word that has the prefix prefix, and false otherwise.
 * 
 * Example:
 * Input:
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output:
 * [null, null, true, false, true, null, true]
 * 
 * Approach:
 * 1. Use TrieNode class with:
 *    - Map of children nodes (character -> TrieNode)
 *    - Boolean to mark end of word
 * 2. For insert:
 *    - Create nodes for each character
 *    - Mark last node as end of word
 * 3. For search:
 *    - Follow path of characters
 *    - Check if last node is marked as end of word
 * 4. For startsWith:
 *    - Similar to search but don't need to check end of word
 * 
 * Time Complexity: 
 * - Insert: O(m) where m is word length
 * - Search: O(m)
 * - StartsWith: O(m)
 * Space Complexity: O(ALPHABET_SIZE * N * M) where N is number of words
 */
public class Trie {
    private class TrieNode {
        private Map<Character, TrieNode> children;
        private boolean isEndOfWord;
        
        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }
    
    private final TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        current.isEndOfWord = true;
    }
    
    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEndOfWord;
    }
    
    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }
    
    private TrieNode searchNode(String str) {
        TrieNode current = root;
        for (char c : str.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return null;
            }
            current = current.children.get(c);
        }
        return current;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        Trie trie = new Trie();
        
        // Test Case 1: Basic operations
        System.out.println("Test Case 1:");
        trie.insert("apple");
        System.out.println("Search 'apple': " + trie.search("apple"));    // true
        System.out.println("Search 'app': " + trie.search("app"));        // false
        System.out.println("StartsWith 'app': " + trie.startsWith("app")); // true
        
        // Test Case 2: Insert duplicate
        System.out.println("\nTest Case 2:");
        trie.insert("apple");
        System.out.println("Search 'apple': " + trie.search("apple"));    // true
        
        // Test Case 3: Insert and search empty string
        System.out.println("\nTest Case 3:");
        trie.insert("");
        System.out.println("Search '': " + trie.search(""));             // true
        System.out.println("StartsWith '': " + trie.startsWith(""));     // true
        
        // Test Case 4: Search non-existent strings
        System.out.println("\nTest Case 4:");
        System.out.println("Search 'app': " + trie.search("app"));       // false
        System.out.println("Search 'appl': " + trie.search("appl"));     // false
        System.out.println("StartsWith 'b': " + trie.startsWith("b"));   // false
        
        // Test Case 5: Multiple words sharing prefix
        System.out.println("\nTest Case 5:");
        trie.insert("app");
        trie.insert("apple");
        trie.insert("apricot");
        System.out.println("Search 'app': " + trie.search("app"));       // true
        System.out.println("Search 'apple': " + trie.search("apple"));   // true
        System.out.println("StartsWith 'apr': " + trie.startsWith("apr")); // true
    }
}
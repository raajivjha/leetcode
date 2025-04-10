package leet75.moredatastructures;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Add and Search Word - Data structure design
 * 
 * Description:
 * Design a data structure that supports adding new words and finding if a string matches any 
 * previously added string. Word may contain dots '.' where dots can be matched with any letter.
 * 
 * Example:
 * Input:
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output:
 * [null,null,null,null,false,true,true,true]
 * 
 * Approach:
 * 1. Use Trie with support for wildcard matching
 * 2. For regular words:
 *    - Insert normally into trie
 *    - Search normally through trie
 * 3. For patterns with dots:
 *    - Try all possible characters at dot positions
 *    - Use DFS/backtracking to explore all possibilities
 * 
 * Time Complexity:
 * - addWord: O(m) where m is word length
 * - search: O(m) for words without dots
 *          O(26^n) worst case for words with all dots
 * Space Complexity: O(ALPHABET_SIZE * N * M) where N is number of words
 */
public class WordDictionary {
    private class TrieNode {
        private Map<Character, TrieNode> children;
        private boolean isEndOfWord;
        
        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }
    
    private final TrieNode root;
    
    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        current.isEndOfWord = true;
    }
    
    public boolean search(String word) {
        return searchHelper(word, 0, root);
    }
    
    private boolean searchHelper(String word, int index, TrieNode node) {
        // Base case: reached end of word
        if (index == word.length()) {
            return node.isEndOfWord;
        }
        
        char c = word.charAt(index);
        if (c == '.') {
            // Try all possible characters
            for (TrieNode child : node.children.values()) {
                if (searchHelper(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            // Regular character
            TrieNode child = node.children.get(c);
            if (child == null) {
                return false;
            }
            return searchHelper(word, index + 1, child);
        }
    }

    // Test method to verify solution
    public static void main(String[] args) {
        WordDictionary dict = new WordDictionary();
        
        // Test Case 1: Basic operations
        System.out.println("Test Case 1:");
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        System.out.println("Search 'pad': " + dict.search("pad"));    // false
        System.out.println("Search 'bad': " + dict.search("bad"));    // true
        System.out.println("Search '.ad': " + dict.search(".ad"));    // true
        System.out.println("Search 'b..': " + dict.search("b.."));    // true
        
        // Test Case 2: Multiple wildcards
        System.out.println("\nTest Case 2:");
        dict.addWord("boat");
        System.out.println("Search 'b..t': " + dict.search("b..t")); // true
        System.out.println("Search '...t': " + dict.search("...t")); // true
        System.out.println("Search '....': " + dict.search("....")); // true
        
        // Test Case 3: Empty string and single character
        System.out.println("\nTest Case 3:");
        dict.addWord("");
        dict.addWord("a");
        System.out.println("Search '': " + dict.search(""));         // true
        System.out.println("Search '.': " + dict.search("."));       // true
        System.out.println("Search 'b': " + dict.search("b"));      // false
        
        // Test Case 4: Non-existent patterns
        System.out.println("\nTest Case 4:");
        System.out.println("Search 'b.x': " + dict.search("b.x"));  // false
        System.out.println("Search '.x.': " + dict.search(".x."));  // false
        
        // Test Case 5: Longer words with wildcards
        System.out.println("\nTest Case 5:");
        dict.addWord("battery");
        System.out.println("Search 'b.tt..y': " + dict.search("b.tt..y")); // true
        System.out.println("Search '.att.r.': " + dict.search(".att.r.")); // true
    }
}
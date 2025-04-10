package leet75.sequences;

import java.util.*;

/**
 * Problem: Group Anagrams
 * 
 * Description:
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * 
 * Example:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 
 * Approach 1 (Sort Characters):
 * 1. For each string, sort its characters to create a key
 * 2. Use HashMap to group strings with the same sorted key
 * 
 * Alternative Approach (Character Count):
 * 1. Create a character count array for each string
 * 2. Convert count array to string to use as key
 * 3. Group strings with same character count pattern
 * 
 * Time Complexity: O(n * k log k) where n is number of strings and k is max string length
 * Space Complexity: O(n * k) to store the grouped anagrams
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        // Map to store sorted string -> list of original strings
        Map<String, List<String>> anagramMap = new HashMap<>();
        
        for (String str : strs) {
            // Convert string to char array and sort
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);
            
            // Add to map, creating new list if needed
            anagramMap.computeIfAbsent(sortedStr, k -> new ArrayList<>()).add(str);
        }
        
        // Convert map values to result list
        return new ArrayList<>(anagramMap.values());
    }
    
    // Alternative solution using character count
    public List<List<String>> groupAnagramsCount(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, List<String>> anagramMap = new HashMap<>();
        
        for (String str : strs) {
            // Create character count array
            int[] charCount = new int[26];
            for (char c : str.toCharArray()) {
                charCount[c - 'a']++;
            }
            
            // Convert count array to string to use as key
            StringBuilder key = new StringBuilder();
            for (int count : charCount) {
                key.append('#').append(count);
            }
            
            // Add to map
            anagramMap.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(anagramMap.values());
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        GroupAnagrams solution = new GroupAnagrams();
        
        // Test case 1: Standard case
        String[] strs1 = {"eat","tea","tan","ate","nat","bat"};
        System.out.println("Test case 1 (Sort method):");
        System.out.println(solution.groupAnagrams(strs1));
        System.out.println("Test case 1 (Count method):");
        System.out.println(solution.groupAnagramsCount(strs1));
        
        // Test case 2: All same anagrams
        String[] strs2 = {"abc","cba","bac"};
        System.out.println("\nTest case 2 (Sort method):");
        System.out.println(solution.groupAnagrams(strs2));
        
        // Test case 3: No anagrams
        String[] strs3 = {"cat","dog","rat"};
        System.out.println("\nTest case 3 (Sort method):");
        System.out.println(solution.groupAnagrams(strs3));
        
        // Test case 4: Empty strings
        String[] strs4 = {"", ""};
        System.out.println("\nTest case 4 (Sort method):");
        System.out.println(solution.groupAnagrams(strs4));
    }
}
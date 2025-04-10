package leet75.nonlinear;

import java.util.*;

/**
 * Problem: Encode and Decode Strings
 * 
 * Description:
 * Design an algorithm to encode a list of strings to a single string and decode
 * this string back to the original list of strings. The strings can contain any
 * possible characters including special characters.
 * 
 * Example:
 * Input: ["Hello","World"]
 * Output: ["Hello","World"]
 * Explanation: encode -> decode should return original list
 * 
 * Approach 1 (Length Prefixing):
 * 1. Encode:
 *    - For each string, prefix with its length and a delimiter
 *    - Format: {length}#{string}
 * 2. Decode:
 *    - Parse length prefix
 *    - Extract substring of that length
 *    - Repeat until input is exhausted
 * 
 * Approach 2 (Escaping):
 * 1. Encode:
 *    - Escape special characters
 *    - Add delimiter between strings
 * 2. Decode:
 *    - Split by delimiter
 *    - Unescape special characters
 * 
 * Time Complexity: O(n) where n is total length of all strings
 * Space Complexity: O(n) to store the encoded/decoded strings
 */
public class EncodeDecodeStrings {
    private static final String DELIMITER = "#";
    
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if (strs == null || strs.isEmpty()) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length())
              .append(DELIMITER)
              .append(str);
        }
        
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return result;
        }
        
        int i = 0;
        while (i < s.length()) {
            // Find delimiter
            int delimiterIndex = s.indexOf(DELIMITER, i);
            if (delimiterIndex == -1) {
                break;
            }
            
            // Parse length
            int length = Integer.parseInt(s.substring(i, delimiterIndex));
            
            // Extract string
            int stringStart = delimiterIndex + 1;
            String str = s.substring(stringStart, stringStart + length);
            result.add(str);
            
            // Move pointer
            i = stringStart + length;
        }
        
        return result;
    }
    
    // Alternative implementation using chunked transfer encoding
    public String encodeChunked(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            for (char c : str.toCharArray()) {
                if (c == ':' || c == '\\') {
                    sb.append('\\');
                }
                sb.append(c);
            }
            sb.append(':');  // Use : as chunk delimiter
        }
        return sb.toString();
    }
    
    public List<String> decodeChunked(String s) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean escaped = false;
        
        for (char c : s.toCharArray()) {
            if (escaped) {
                current.append(c);
                escaped = false;
            } else if (c == '\\') {
                escaped = true;
            } else if (c == ':') {
                result.add(current.toString());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }
        
        return result;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        EncodeDecodeStrings solution = new EncodeDecodeStrings();
        
        // Test Case 1: Basic strings
        List<String> input1 = Arrays.asList("Hello", "World");
        System.out.println("Test Case 1:");
        String encoded1 = solution.encode(input1);
        System.out.println("Encoded: " + encoded1);
        System.out.println("Decoded: " + solution.decode(encoded1));
        
        // Test Case 2: Empty strings
        List<String> input2 = Arrays.asList("", "", "");
        System.out.println("\nTest Case 2 (Empty strings):");
        String encoded2 = solution.encode(input2);
        System.out.println("Encoded: " + encoded2);
        System.out.println("Decoded: " + solution.decode(encoded2));
        
        // Test Case 3: Strings with special characters
        List<String> input3 = Arrays.asList("Hello#World", "Test:123", "Special\\Chars");
        System.out.println("\nTest Case 3 (Special characters):");
        System.out.println("Using length prefixing:");
        String encoded3 = solution.encode(input3);
        System.out.println("Encoded: " + encoded3);
        System.out.println("Decoded: " + solution.decode(encoded3));
        
        System.out.println("Using chunked encoding:");
        String encodedChunked3 = solution.encodeChunked(input3);
        System.out.println("Encoded: " + encodedChunked3);
        System.out.println("Decoded: " + solution.decodeChunked(encodedChunked3));
        
        // Test Case 4: Unicode characters
        List<String> input4 = Arrays.asList("你好", "世界", "🌍");
        System.out.println("\nTest Case 4 (Unicode characters):");
        String encoded4 = solution.encode(input4);
        System.out.println("Encoded: " + encoded4);
        System.out.println("Decoded: " + solution.decode(encoded4));
        
        // Test Case 5: Single string
        List<String> input5 = Arrays.asList("SingleString");
        System.out.println("\nTest Case 5 (Single string):");
        String encoded5 = solution.encode(input5);
        System.out.println("Encoded: " + encoded5);
        System.out.println("Decoded: " + solution.decode(encoded5));
    }
}
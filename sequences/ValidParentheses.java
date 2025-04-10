package leet75.sequences;

import java.util.Stack;

/**
 * Problem: Valid Parentheses
 * 
 * Description:
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid. An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 * 
 * Example 1:
 * Input: s = "()"
 * Output: true
 * 
 * Example 2:
 * Input: s = "([)]"
 * Output: false
 * 
 * Approach:
 * 1. Use a stack to track opening brackets
 * 2. When encountering an opening bracket, push to stack
 * 3. When encountering a closing bracket:
 *    - If stack is empty, return false (no matching open bracket)
 *    - If top of stack doesn't match current closing bracket, return false
 *    - Otherwise, pop the top bracket
 * 4. At the end, stack should be empty for valid string
 * 
 * Time Complexity: O(n) - We traverse the string once
 * Space Complexity: O(n) - In worst case, all opening brackets
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            // If it's an opening bracket, push to stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            // If it's a closing bracket
            else {
                // Check if stack is empty (no matching opening bracket)
                if (stack.isEmpty()) {
                    return false;
                }
                
                // Get the top of the stack
                char top = stack.pop();
                
                // Check if brackets match
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        
        // Stack should be empty if all brackets are matched
        return stack.isEmpty();
    }

    // Test method to verify the solution
    public static void main(String[] args) {
        ValidParentheses solution = new ValidParentheses();
        
        // Test case 1: Valid parentheses
        String s1 = "()[]{}";
        System.out.println("Test case 1 (should be true): " + 
                          solution.isValid(s1));
        
        // Test case 2: Invalid order
        String s2 = "([)]";
        System.out.println("Test case 2 (should be false): " + 
                          solution.isValid(s2));
        
        // Test case 3: Unmatched opening bracket
        String s3 = "((";
        System.out.println("Test case 3 (should be false): " + 
                          solution.isValid(s3));
        
        // Test case 4: Unmatched closing bracket
        String s4 = "))";
        System.out.println("Test case 4 (should be false): " + 
                          solution.isValid(s4));
    }
}
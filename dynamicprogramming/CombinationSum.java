package leet75.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem: Combination Sum
 * 
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order.
 * 
 * The same number may be chosen from candidates an unlimited number of times.
 * 
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation: 
 * 2 + 2 + 3 = 7
 * 7 = 7
 * 
 * Example 2:
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * 
 * Approach:
 * 1. Use backtracking to try all possible combinations
 * 2. For each candidate:
 *    - Include it in current combination if it doesn't exceed target
 *    - Recursively try to find combinations with remaining target
 *    - Remove the candidate (backtrack) and try next candidate
 * 3. Base cases:
 *    - If target == 0, we found a valid combination
 *    - If target < 0, current combination exceeds target
 * 
 * Time Complexity: O(N^(T/M)) where
 * - N is the number of candidates
 * - T is the target value
 * - M is the minimum value among candidates
 * 
 * Space Complexity: O(T/M) for recursion depth
 */
public class CombinationSum {
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrack(int[] candidates, int remaining, int start, 
                         List<Integer> current, List<List<Integer>> result) {
        // Base cases
        if (remaining < 0) return;  // Current combination exceeds target
        if (remaining == 0) {       // Found valid combination
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Try each candidate starting from 'start' index
        // (to avoid duplicate combinations in different orders)
        for (int i = start; i < candidates.length; i++) {
            // Include current candidate
            current.add(candidates[i]);
            
            // Recursively try to find combinations with remaining target
            // We can reuse current number, so we pass i instead of i+1
            backtrack(candidates, remaining - candidates[i], i, current, result);
            
            // Backtrack: remove the candidate to try next possibility
            current.remove(current.size() - 1);
        }
    }
    
    // Test the solution
    public static void main(String[] args) {
        CombinationSum solution = new CombinationSum();
        
        int[] candidates1 = {2, 3, 6, 7};
        System.out.println("Combinations for target 7: " + 
            solution.combinationSum(candidates1, 7));
        
        int[] candidates2 = {2, 3, 5};
        System.out.println("Combinations for target 8: " + 
            solution.combinationSum(candidates2, 8));
    }
}
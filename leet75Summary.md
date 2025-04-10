# LeetCode 75 Questions - Comprehensive Guide

## 1. Sequences

### 1. Two Sum
/**
 * Problem: Two Sum
 * 
 * Description:
 * Given an array of integers nums and an integer target, return indices of the two numbers in the array
 * that add up to target. You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 * 
 * Example:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1]
 * 
 * Approach:
 * 1. Use HashMap to store complement values
 * 2. For each number x, check if target - x exists in map
 * 3. If found, return current index and stored index
 * 4. If not, store current number and index
 * 
 * Alternative Approach:
 * - Sort array and use two pointers (but loses original indices)
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) to store the HashMap
 */

### 2. Contains Duplicate
/**
 * Problem: Contains Duplicate
 * 
 * Description:
 * Given an integer array nums, return true if any value appears at least twice in the array,
 * and return false if every element is distinct.
 * 
 * Example:
 * Input: nums = [1,2,3,1]
 * Output: true
 * Explanation: '1' appears twice in the array
 * 
 * Approach 1 (HashSet):
 * 1. Create HashSet to track unique elements
 * 2. Iterate through array, add to set
 * 3. If element already exists in set, return true
 * 
 * Approach 2 (Sorting):
 * 1. Sort the array
 * 2. Check adjacent elements for equality
 * 
 * Time Complexity: O(n) with HashSet, O(n log n) with sorting
 * Space Complexity: O(n) with HashSet, O(1) with sorting
 */

### 3. Best Time to Buy and Sell Stock
/**
 * Problem: Best Time to Buy and Sell Stock
 * 
 * Description:
 * Given an array prices where prices[i] is the price of a given stock on the ith day,
 * maximize your profit by choosing a single day to buy and a different day in the future to sell.
 * 
 * Example:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5
 * 
 * Approach:
 * 1. Track minimum price seen so far
 * 2. For each price, calculate potential profit (current price - min price)
 * 3. Update maximum profit if current profit is larger
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

### 4. Valid Anagram
/**
 * Problem: Valid Anagram
 * 
 * Description:
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An anagram is a word formed by rearranging the letters of another word.
 * 
 * Example:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * 
 * Approach 1 (Character Count):
 * 1. Create int[26] array for character frequencies
 * 2. Increment count for chars in s, decrement for chars in t
 * 3. Check if all counts are zero
 * 
 * Approach 2 (Sorting):
 * 1. Sort both strings
 * 2. Compare if they are equal
 * 
 * Time Complexity: O(n) with counting, O(n log n) with sorting
 * Space Complexity: O(1) - fixed size array
 */

### 5. Valid Parentheses
/**
 * Problem: Valid Parentheses
 * 
 * Description:
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 
 * Example:
 * Input: s = "()[]{}"
 * Output: true
 * 
 * Approach:
 * 1. Use a stack to track opening brackets
 * 2. For each closing bracket, check if it matches the top of the stack
 * 3. If it matches, pop the stack; otherwise, return false
 * 4. At the end, the stack should be empty if all brackets are matched
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

### 6. Maximum Subarray
/**
 * Problem: Maximum Subarray
 * 
 * Description:
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * 
 * Example:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6
 * 
 * Approach:
 * 1. Use Kadane's Algorithm to track the maximum subarray sum
 * 2. Iterate through the array, updating the current sum and maximum sum
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

### 7. Product of Array Except Self
/**
 * Problem: Product of Array Except Self
 * 
 * Description:
 * Given an array nums of n integers where n > 1, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * 
 * Example:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * 
 * Approach:
 * 1. Create two arrays to store the product of elements to the left and right of each element
 * 2. Calculate the left and right products for each element
 * 3. Multiply the left and right products to get the final result
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) - output array not counted
 */

### 8. 3Sum
/**
 * Problem: 3Sum
 * 
 * Description:
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * 
 * Example:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * 
 * Approach:
 * 1. Sort the array
 * 2. Use two pointers to find pairs that sum to the target value
 * 3. Skip duplicate elements to avoid duplicate triplets
 * 
 * Time Complexity: O(n²)
 * Space Complexity: O(1) or O(n) depending on sorting implementation
 */

### 9. Merge Intervals
/**
 * Problem: Merge Intervals
 * 
 * Description:
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * 
 * Example:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * 
 * Approach:
 * 1. Sort the intervals by start time
 * 2. Iterate through the intervals, merging overlapping intervals
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

### 10. Group Anagrams
/**
 * Problem: Group Anagrams
 * 
 * Description:
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * 
 * Example:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 
 * Approach:
 * 1. Use a HashMap to group anagrams by sorted character strings or character frequency counts
 * 2. Iterate through the strings, adding them to the appropriate group in the HashMap
 * 
 * Time Complexity: O(n * k log k) where k is the maximum string length
 * Space Complexity: O(n)
 */

### 11. Maximum Product Subarray
/**
 * Problem: Maximum Product Subarray
 * 
 * Description:
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * 
 * Example:
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product = 6
 * 
 * Approach:
 * 1. Track both the maximum and minimum products at each position
 * 2. Update the maximum product by considering the current element, the product of the current element and the previous maximum, and the product of the current element and the previous minimum
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

### 12. Search in Rotated Sorted Array
/**
 * Problem: Search in Rotated Sorted Array
 * 
 * Description:
 * Given an integer array nums sorted in ascending order, and an integer target, search for the target in the rotated array.
 * 
 * Example:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * 
 * Approach:
 * 1. Use a modified binary search to find the target
 * 2. Determine which half of the array is sorted and adjust the search range accordingly
 * 
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */

## 2. Data Structures

### 1. Reverse a Linked List
/**
 * Problem: Reverse a Linked List
 * 
 * Description:
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * 
 * Example:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * 
 * Approach:
 * 1. Use three pointers to reverse the list iteratively
 * 2. Alternatively, use recursion to reverse the list
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) iterative, O(n) recursive
 */

### 2. Detect Cycle in a Linked List
/**
 * Problem: Detect Cycle in a Linked List
 * 
 * Description:
 * Given the head of a linked list, determine if the linked list has a cycle in it.
 * 
 * Example:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * 
 * Approach:
 * 1. Use Floyd's Tortoise and Hare algorithm with slow and fast pointers
 * 2. If the slow and fast pointers meet, there is a cycle
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

### 3. Container With Most Water
/**
 * Problem: Container With Most Water
 * 
 * Description:
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which together with the x-axis forms a container, such that the container contains the most water.
 * 
 * Example:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * 
 * Approach:
 * 1. Use two pointers starting from the ends of the array
 * 2. Move the pointer with the smaller height towards the center
 * 3. Calculate the area and update the maximum area
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

### 4. Find Minimum in Rotated Sorted Array
/**
 * Problem: Find Minimum in Rotated Sorted Array
 * 
 * Description:
 * Given a rotated sorted array, find the minimum element.
 * 
 * Example:
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * 
 * Approach:
 * 1. Use a modified binary search to find the minimum element
 * 2. Compare the middle element with the rightmost element to determine the search range
 * 
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */

### 5. Longest Repeating Character Replacement
/**
 * Problem: Longest Repeating Character Replacement
 * 
 * Description:
 * Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string. In one operation, you can choose any character of the string and change it to any other uppercase English character. Find the length of the longest substring containing the same letter you can get after performing the above operations.
 * 
 * Example:
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * 
 * Approach:
 * 1. Use a sliding window to track the longest substring with the same letter
 * 2. Use a character count array to track the frequency of each character
 * 3. Adjust the window size based on the character frequencies and the allowed number of operations
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

### 6. Longest Substring Without Repeating Characters
/**
 * Problem: Longest Substring Without Repeating Characters
 * 
 * Description:
 * Given a string s, find the length of the longest substring without repeating characters.
 * 
 * Example:
 * Input: s = "abcabcbb"
 * Output: 3
 * 
 * Approach:
 * 1. Use a sliding window with a HashSet or HashMap to track the characters in the current window
 * 2. Adjust the window size to maintain unique characters
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(min(m,n)) where m is the charset size
 */

### 7. Number of Islands
/**
 * Problem: Number of Islands
 * 
 * Description:
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
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
 * Approach:
 * 1. Use DFS or BFS to traverse the grid and mark visited cells
 * 2. Count the number of connected components of '1's
 * 
 * Time Complexity: O(m*n)
 * Space Complexity: O(m*n)
 */

### 8. Remove Nth Node From End Of List
/**
 * Problem: Remove Nth Node From End Of List
 * 
 * Description:
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * 
 * Example:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * 
 * Approach:
 * 1. Use two pointers with a gap of n nodes
 * 2. Move both pointers until the fast pointer reaches the end
 * 3. Remove the node at the slow pointer
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

### 9. Palindromic Substrings
/**
 * Problem: Palindromic Substrings
 * 
 * Description:
 * Given a string s, return the number of palindromic substrings in it.
 * 
 * Example:
 * Input: s = "abc"
 * Output: 3
 * 
 * Approach:
 * 1. Use the expand around center technique to find all palindromic substrings
 * 2. For each character and pair of characters, expand outwards to check for palindromes
 * 
 * Time Complexity: O(n²)
 * Space Complexity: O(1)
 */

### 10. Pacific Atlantic Water Flow
/**
 * Problem: Pacific Atlantic Water Flow
 * 
 * Description:
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges. Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * 
 * Example:
 * Input: heights = [
 *   [1,2,2,3,5],
 *   [3,2,3,4,4],
 *   [2,4,5,3,1],
 *   [6,7,1,4,5],
 *   [5,1,1,2,4]
 * ]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * 
 * Approach:
 * 1. Use DFS or BFS to traverse from the borders of the matrix
 * 2. Mark cells that can flow to each ocean
 * 3. Find the intersection of cells that can flow to both oceans
 * 
 * Time Complexity: O(m*n)
 * Space Complexity: O(m*n)
 */

### 11. Minimum Window Substring
/**
 * Problem: Minimum Window Substring
 * 
 * Description:
 * Given two strings s and t, return the minimum window in s which will contain all the characters in t.
 * 
 * Example:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * 
 * Approach:
 * 1. Use a sliding window to track the characters in the current window
 * 2. Use a character count array to track the frequency of each character in t
 * 3. Adjust the window size to find the minimum window that contains all characters in t
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(k) where k is the charset size
 */

## 3. Non-linear Data Structures

### 1. Invert/Flip Binary Tree
/**
 * Problem: Invert/Flip Binary Tree
 * 
 * Description:
 * Given the root of a binary tree, invert the tree, and return its root.
 * 
 * Example:
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 * 
 * Approach:
 * 1. Use recursion to swap the left and right children of each node
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(h) where h is the height of the tree
 */

### 2. Validate Binary Search Tree
/**
 * Problem: Validate Binary Search Tree
 * 
 * Description:
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * Example:
 * Input: root = [2,1,3]
 * Output: true
 * 
 * Approach:
 * 1. Use DFS with min and max bounds to validate the BST
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */

### 3. Non-overlapping Intervals
/**
 * Problem: Non-overlapping Intervals
 * 
 * Description:
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * 
 * Example:
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * 
 * Approach:
 * 1. Sort the intervals by end time
 * 2. Use a greedy approach to select the maximum number of non-overlapping intervals
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(1)
 */

### 4. Construct Binary Tree from Preorder and Inorder Traversal
/**
 * Problem: Construct Binary Tree from Preorder and Inorder Traversal
 * 
 * Description:
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 * 
 * Example:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * 
 * Approach:
 * 1. Use recursion with index tracking to construct the tree
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

### 5. Top K Frequent Elements
/**
 * Problem: Top K Frequent Elements
 * 
 * Description:
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * 
 * Example:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * 
 * Approach:
 * 1. Use a HashMap to count the frequency of each element
 * 2. Use a Heap or QuickSelect to find the top k frequent elements
 * 
 * Time Complexity: O(n log k)
 * Space Complexity: O(n)
 */

### 6. Clone Graph
/**
 * Problem: Clone Graph
 * 
 * Description:
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
 * 
 * Example:
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * 
 * Approach:
 * 1. Use DFS or BFS with a HashMap to map original nodes to their clones
 * 
 * Time Complexity: O(V+E)
 * Space Complexity: O(V)
 */

### 7. Task Schedule
/**
 * Problem: Task Schedule
 * 
 * Description:
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete one task or just be idle.
 * 
 * Example:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * 
 * Approach:
 * 1. Use a greedy approach with a Priority Queue to schedule tasks
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(1)
 */

### 8. Serialize and Deserialize Binary Tree
/**
 * Problem: Serialize and Deserialize Binary Tree
 * 
 * Description:
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * 
 * Example:
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * 
 * Approach:
 * 1. Use preorder traversal with null markers to serialize the tree
 * 2. Use the serialized string to reconstruct the tree
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

### 9. Binary Tree Maximum Path Sum
/**
 * Problem: Binary Tree Maximum Path Sum
 * 
 * Description:
 * Given a non-empty binary tree, find the maximum path sum. For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 * 
 * Example:
 * Input: root = [1,2,3]
 * Output: 6
 * 
 * Approach:
 * 1. Use DFS to traverse the tree and track the global maximum path sum
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */

### 10. Maximum Depth of Binary Tree
/**
 * Problem: Maximum Depth of Binary Tree
 * 
 * Description:
 * Given the root of a binary tree, return its maximum depth. A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * 
 * Example:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * 
 * Approach:
 * 1. Use DFS or BFS to find the maximum depth of the tree
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */

### 11. Same Tree
/**
 * Problem: Same Tree
 * 
 * Description:
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not. Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 * 
 * Example:
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * 
 * Approach:
 * 1. Use recursion to compare the nodes of both trees
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */

### 12. Binary Tree Level Order Traversal
/**
 * Problem: Binary Tree Level Order Traversal
 * 
 * Description:
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 * 
 * Example:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * 
 * Approach:
 * 1. Use BFS with a queue to traverse the tree level by level
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(w) where w is the maximum width of the tree
 */

### 13. Encode and Decode Strings
/**
 * Problem: Encode and Decode Strings
 * 
 * Description:
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.
 * 
 * Example:
 * Input: strs = ["Hello","World"]
 * Output: ["Hello","World"]
 * 
 * Approach:
 * 1. Use length encoding with a delimiter to encode the strings
 * 2. Decode the strings by parsing the length and extracting the substrings
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

## 4. More Data Structures

### 1. Subtree of Another Tree
/**
 * Problem: Subtree of Another Tree
 * 
 * Description:
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
 * 
 * Example:
 * Input: root = [3,4,5,1,2], subRoot = [4,1,2]
 * Output: true
 * 
 * Approach:
 * 1. Use recursion to match the subtrees
 * 
 * Time Complexity: O(m*n)
 * Space Complexity: O(h)
 */

### 2. Lowest Common Ancestor of BST
/**
 * Problem: Lowest Common Ancestor of BST
 * 
 * Description:
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * 
 * Example:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * 
 * Approach:
 * 1. Use binary search with BST properties to find the LCA
 * 
 * Time Complexity: O(h)
 * Space Complexity: O(1)
 */

### 3. Implement Trie (Prefix Tree)
/**
 * Problem: Implement Trie (Prefix Tree)
 * 
 * Description:
 * Implement a trie with insert, search, and startsWith methods.
 * 
 * Example:
 * Input: ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * Output: [null, null, true, false, true, null, true]
 * 
 * Approach:
 * 1. Use a TrieNode with a children map to implement the trie
 * 
 * Time Complexity: O(k) for operations
 * Space Complexity: O(n*k)
 */

### 4. Add and Search Word
/**
 * Problem: Add and Search Word
 * 
 * Description:
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 * 
 * Example:
 * Input: ["WordDictionary", "addWord", "addWord", "search", "search", "search", "search"]
 * Output: [null, null, null, true, false, true, false]
 * 
 * Approach:
 * 1. Use a Trie with wildcard handling to implement the data structure
 * 
 * Time Complexity: O(n) for insert, O(26^n) worst case for search
 * Space Complexity: O(n*k)
 */

### 5. Kth Smallest Element in a BST
/**
 * Problem: Kth Smallest Element in a BST
 * 
 * Description:
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 * 
 * Example:
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 * 
 * Approach:
 * 1. Use inorder traversal to find the kth smallest element
 * 
 * Time Complexity: O(H + k)
 * Space Complexity: O(H)
 */

### 6. Merge K Sorted Lists
/**
 * Problem: Merge K Sorted Lists
 * 
 * Description:
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order. Merge all the linked-lists into one sorted linked-list and return it.
 * 
 * Example:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * 
 * Approach:
 * 1. Use a Min Heap or Divide and Conquer to merge the lists
 * 
 * Time Complexity: O(N log k)
 * Space Complexity: O(k)
 */

### 7. Find Median from Data Stream
/**
 * Problem: Find Median from Data Stream
 * 
 * Description:
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.
 * 
 * Example:
 * Input: ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * Output: [null, null, null, 1.5, null, 2.0]
 * 
 * Approach:
 * 1. Use two heaps (max and min) to maintain the median
 * 
 * Time Complexity: O(log n) insert, O(1) find
 * Space Complexity: O(n)
 */

### 8. Insert Interval
/**
 * Problem: Insert Interval
 * 
 * Description:
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * 
 * Example:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * 
 * Approach:
 * 1. Use a linear scan to find the correct position for the new interval
 * 2. Merge overlapping intervals
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

### 9. Longest Consecutive Sequence
/**
 * Problem: Longest Consecutive Sequence
 * 
 * Description:
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * 
 * Example:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * 
 * Approach:
 * 1. Use a HashSet to store the elements
 * 2. Build the longest sequence by checking for consecutive elements
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

### 10. Word Search II
/**
 * Problem: Word Search II
 * 
 * Description:
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * 
 * Example:
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 * 
 * Approach:
 * 1. Use a Trie to store the words
 * 2. Use DFS to search for words on the board
 * 
 * Time Complexity: O(m*n*4^k)
 * Space Complexity: O(k) where k is the word length
 */

### 11. Meeting Rooms
/**
 * Problem: Meeting Rooms
 * 
 * Description:
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
 * 
 * Example:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 * 
 * Approach:
 * 1. Sort the intervals by start time
 * 2. Check for overlapping intervals
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(1)
 */

### 12. Meeting Rooms II
/**
 * Problem: Meeting Rooms II
 * 
 * Description:
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
 * 
 * Example:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 * 
 * Approach:
 * 1. Use a Min Heap or chronological ordering to track the end times of meetings
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

### 13. Graph Valid Tree
/**
 * Problem: Graph Valid Tree
 * 
 * Description:
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges, write a function to check whether these edges make up a valid tree.
 * 
 * Example:
 * Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * Output: true
 * 
 * Approach:
 * 1. Use Union Find or DFS to check for cycles and connectivity
 * 
 * Time Complexity: O(V+E)
 * Space Complexity: O(V)
 */

### 14. Number of Connected Components in an Undirected Graph
/**
 * Problem: Number of Connected Components in an Undirected Graph
 * 
 * Description:
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges, write a function to find the number of connected components in an undirected graph.
 * 
 * Example:
 * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 * Output: 2
 * 
 * Approach:
 * 1. Use Union Find or DFS to count the number of connected components
 * 
 * Time Complexity: O(V+E)
 * Space Complexity: O(V)
 */

### 15. Alien Dictionary
/**
 * Problem: Alien Dictionary
 * 
 * Description:
 * Given a list of words from the alien language's dictionary, find the order of characters in the alien language.
 * 
 * Example:
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 * 
 * Approach:
 * 1. Use Topological Sort with a Graph to determine the order of characters
 * 
 * Time Complexity: O(C) where C is the total number of characters
 * Space Complexity: O(1)
 */

## 5. Dynamic Programming

### 1. Climbing Stairs
/**
 * Problem: Climbing Stairs
 * 
 * Description:
 * You are climbing a staircase. It takes n steps to reach the top. Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * 
 * Example:
 * Input: n = 2
 * Output: 2
 * 
 * Approach:
 * 1. Use DP with a fibonacci pattern to find the number of ways to climb the stairs
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

### 2. Coin Change
/**
 * Problem: Coin Change
 * 
 * Description:
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money. Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * 
 * Example:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * 
 * Approach:
 * 1. Use bottom-up DP to find the minimum number of coins needed
 * 
 * Time Complexity: O(amount * coins)
 * Space Complexity: O(amount)
 */

### 3. Longest Increasing Subsequence
/**
 * Problem: Longest Increasing Subsequence
 * 
 * Description:
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * 
 * Example:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * 
 * Approach:
 * 1. Use DP or Binary Search to find the longest increasing subsequence
 * 
 * Time Complexity: O(n²) or O(n log n)
 * Space Complexity: O(n)
 */

### 4. Combination Sum
/**
 * Problem: Combination Sum
 * 
 * Description:
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 * 
 * Example:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * 
 * Approach:
 * 1. Use backtracking to find all unique combinations
 * 
 * Time Complexity: O(2^n)
 * Space Complexity: O(n)
 */

### 5. House Robber
/**
 * Problem: House Robber
 * 
 * Description:
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * 
 * Example:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * 
 * Approach:
 * 1. Use DP with two states to find the maximum amount of money that can be robbed
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

### 6. House Robber II
/**
 * Problem: House Robber II
 * 
 * Description:
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * 
 * Example:
 * Input: nums = [2,3,2]
 * Output: 3
 * 
 * Approach:
 * 1. Run House Robber twice, once excluding the first house and once excluding the last house
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

### 7. Decode Ways
/**
 * Problem: Decode Ways
 * 
 * Description:
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a string s containing only digits, return the number of ways to decode it.
 * 
 * Example:
 * Input: s = "12"
 * Output: 2
 * 
 * Approach:
 * 1. Use DP with string parsing to find the number of ways to decode the message
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

### 8. Unique Paths
/**
 * Problem: Unique Paths
 * 
 * Description:
 * A robot is located at the top-left corner of a m x n grid. The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid. How many possible unique paths are there?
 * 
 * Example:
 * Input: m = 3, n = 7
 * Output: 28
 * 
 * Approach:
 * 1. Use 2D DP or Combinatorics to find the number of unique paths
 * 
 * Time Complexity: O(m*n)
 * Space Complexity: O(min(m,n))
 */

### 9. Jump Game
/**
 * Problem: Jump Game
 * 
 * Description:
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array. Each element in the array represents your maximum jump length at that position. Determine if you are able to reach the last index.
 * 
 * Example:
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * 
 * Approach:
 * 1. Use a greedy approach to check if you can reach the end of the array
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

### 10. Word Break
/**
 * Problem: Word Break
 * 
 * Description:
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * 
 * Example:
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * 
 * Approach:
 * 1. Use DP with substring checking to determine if the string can be segmented
 * 
 * Time Complexity: O(n²)
 * Space Complexity: O(n)
 */


# Study Plan

All **193** problems in this repo in one order: **Easy → Medium → Hard**, and inside each level, the most-asked first.

- Easy: **47**, Medium: **120**, Hard: **26**.
- "Most asked" = how many of the 11 sources in [INTERVIEW-PREP.md](INTERVIEW-PREP.md#3-cross-checked-list) list the problem (the `Src` column).
- Problems with the same `Src` are grouped by pattern, so similar problems sit next to each other.
- The `File` column gives the solution to open. Problems solved with two approaches list both files.

## Contents

1. [How to prepare](#1-how-to-prepare)
2. [Fast track: Tier 1 only](#2-fast-track-tier-1-only)
3. [Phase 1: Easy (47)](#3-phase-1-easy)
4. [Phase 2: Medium (120)](#4-phase-2-medium)
5. [Phase 3: Hard (26)](#5-phase-3-hard)
6. [Revision schedule](#6-revision-schedule)

## 1. How to prepare

### Why this order

- **Easy first** builds the basic moves (two pointers, hash map counting, stack, recursion) that the Medium and Hard problems reuse.
- **Most asked first** inside each level means that, if you run out of time, what's left is what's asked least.
- **Grouped by pattern** within the same count, so a pattern sticks after two or three problems in a row.

### For each problem (about 45–60 minutes)

1. Open the file and read only `PROBLEM` and `EXAMPLE`. Don't scroll further yet.
2. Try it yourself for **20 minutes** (Easy: 10–15). Say out loud which pattern it is and why.
3. Stuck? Read only `HOW TO THINK`, then try again for 10 more minutes.
4. Read `STEPS` and the `DRY RUN`. Close the file and **write the code from scratch** in a blank file.
5. Run it on the `EXAMPLE` and every row of `EDGE CASES`. Compare with the repo solution.
6. Read `COMMON MISTAKES` and `COMPLEXITY`. Say the complexity out loud as you would in an interview.
7. Change ⬜ to ✅. If you needed step 3 or 4, use 🔁 instead (revise it again).

### Pace

| Phase | Problems | Per day | Days |
|---|---|---|---|
| Easy | 47 | 6 | ~8 |
| Medium | 120 | 4 | ~30 |
| Hard | 26 | 2 | ~13 |
| **Total** | **193** | | **~51 days (~8 weeks)** |

- Keep one day a week free for revision (section 6) and one timed mock: 2 random problems from the ✅ list, 45 minutes, no notes.
- If a Medium needs a pattern you haven't seen yet, do that pattern's Easy problems from Phase 1 first. The `RELATED` section in each file points to them.

## 2. Fast track: Tier 1 only

Short on time? Do just these **56** problems first: they are in 8 or more of the 11 sources. They are the Tier 1 rows of Phases 1–3, in the same order, so a ✅ there counts here too.

- **Easy (12):** 21 Merge Two Sorted Lists, 206 Reverse Linked List, 70 Climbing Stairs, 1 Two Sum, 121 Best Time to Buy and Sell Stock, 242 Valid Anagram, 141 Linked List Cycle, 104 Maximum Depth of Binary Tree, 543 Diameter of Binary Tree, 125 Valid Palindrome, 20 Valid Parentheses, 283 Move Zeroes
- **Medium (36):** 53 Maximum Subarray, 300 Longest Increasing Subsequence, 49 Group Anagrams, 78 Subsets, 79 Word Search, 33 Search in Rotated Sorted Array, 198 House Robber, 200 Number of Islands, 3 Longest Substring Without Repeating Characters, 5 Longest Palindromic Substring, 48 Rotate Image, 54 Spiral Matrix, 238 Product of Array Except Self, 39 Combination Sum, 46 Permutations, 62 Unique Paths, 139 Word Break, 322 Coin Change, 207 Course Schedule, 347 Top K Frequent Elements, 56 Merge Intervals, 19 Remove Nth Node From End of List, 155 Min Stack, 11 Container With Most Water, 55 Jump Game, 73 Set Matrix Zeroes, 152 Maximum Product Subarray, 133 Clone Graph, 146 LRU Cache, 215 Kth Largest Element in an Array, 2 Add Two Numbers, 50 Pow(x, n), 98 Validate Binary Search Tree, 102 Binary Tree Level Order Traversal, 75 Sort Colors, 647 Palindromic Substrings
- **Hard (8):** 295 Find Median from Data Stream, 42 Trapping Rain Water, 127 Word Ladder, 239 Sliding Window Maximum, 297 Serialize and Deserialize Binary Tree, 23 Merge k Sorted Lists, 76 Minimum Window Substring, 124 Binary Tree Maximum Path Sum

## 3. Phase 1: Easy

47 problems.

### Easy · Tier 1: in 8–11 sources (must do) (12)

| Done | # | LC | Problem | Src | Pattern | File |
|---|---|---|---|---|---|---|
| ⬜ | 1 | 21 | [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) | 11/11 | Linked List | `LinkedList/src/MergeTwoLinkedList.java` |
| ⬜ | 2 | 206 | [Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) | 11/11 | Linked List | `LinkedList/src/ReverseList.java` |
| ⬜ | 3 | 70 | [Climbing Stairs](https://leetcode.com/problems/climbing-stairs/) | 10/11 | Dynamic Programming | `DynamicProgramming/src/ClimbingStairs.java` |
| ⬜ | 4 | 1 | [Two Sum](https://leetcode.com/problems/two-sum/) | 10/11 | Hash Map | `HashMap/src/TwoSum.java` |
| ⬜ | 5 | 121 | [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | 9/11 | Arrays & Strings | `ArraysAndStrings/src/MaxProfit.java` |
| ⬜ | 6 | 242 | [Valid Anagram](https://leetcode.com/problems/valid-anagram/) | 9/11 | Hash Map | `HashMap/src/ValidAnagram.java` |
| ⬜ | 7 | 141 | [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) | 9/11 | Linked List | `LinkedList/src/LinkedListCycle.java` |
| ⬜ | 8 | 104 | [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) | 9/11 | Trees | `Trees/src/MaximumDepthOfBinaryTree.java` |
| ⬜ | 9 | 543 | [Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/) | 9/11 | Trees | `FAQ/src/DiameterOfBinaryTree.java` |
| ⬜ | 10 | 125 | [Valid Palindrome](https://leetcode.com/problems/valid-palindrome/) | 9/11 | Two Pointers | `TwoPointers/src/ValidPalindrome.java` |
| ⬜ | 11 | 20 | [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) | 8/11 | Stack | `Stack/src/ValidParentheses.java` |
| ⬜ | 12 | 283 | [Move Zeroes](https://leetcode.com/problems/move-zeroes/) | 8/11 | Two Pointers | `TwoPointers/src/MoveZeroes.java` |

### Easy · Tier 2: in 6–7 sources (10)

| Done | # | LC | Problem | Src | Pattern | File |
|---|---|---|---|---|---|---|
| ⬜ | 13 | 14 | [Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/) | 7/11 | Arrays & Strings | `ArraysAndStrings/src/LongestPrefixString.java` |
| ⬜ | 14 | 26 | [Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) | 7/11 | Arrays & Strings | `ArraysAndStrings/src/RemoveDuplicates.java` |
| ⬜ | 15 | 28 | [Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/) | 7/11 | Arrays & Strings | `ArraysAndStrings/src/FirstOccurrenceOfString.java` |
| ⬜ | 16 | 88 | [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/) | 7/11 | Arrays & Strings | `ArraysAndStrings/src/Merge.java` |
| ⬜ | 17 | 169 | [Majority Element](https://leetcode.com/problems/majority-element/) | 7/11 | Arrays & Strings | `ArraysAndStrings/src/MajorityElement.java` |
| ⬜ | 18 | 387 | [First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/) | 7/11 | Hash Map | `HashMap/src/FirstUniqueCharacter.java` |
| ⬜ | 19 | 234 | [Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/) | 7/11 | Linked List | `LinkedList/src/PalindromeLinkedList.java` |
| ⬜ | 20 | 13 | [Roman to Integer](https://leetcode.com/problems/roman-to-integer/) | 6/11 | Arrays & Strings | `ArraysAndStrings/src/RomanToInt.java` |
| ⬜ | 21 | 136 | [Single Number](https://leetcode.com/problems/single-number/) | 6/11 | Math | `Math/src/SingleNumber.java` |
| ⬜ | 22 | 268 | [Missing Number](https://leetcode.com/problems/missing-number/) | 6/11 | Math | `FAQ/src/MissingNumberFinder.java` |

### Easy · Tier 3: in 4–5 sources (18)

| Done | # | LC | Problem | Src | Pattern | File |
|---|---|---|---|---|---|---|
| ⬜ | 23 | 118 | [Pascal's Triangle](https://leetcode.com/problems/pascals-triangle/) | 5/11 | Arrays & Strings | `ArraysAndStrings/src/PascalsTriangle.java` |
| ⬜ | 24 | 704 | [Binary Search](https://leetcode.com/problems/binary-search/) | 5/11 | Binary Search | `BinarySearch/src/BinarySearch.java` |
| ⬜ | 25 | 349 | [Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/) | 5/11 | Hash Map | `HashMap/src/IntersectionOfTwoArrays.java` |
| ⬜ | 26 | 9 | [Palindrome Number](https://leetcode.com/problems/palindrome-number/) | 5/11 | Math | `Math/src/Pallindrome.java` |
| ⬜ | 27 | 66 | [Plus One](https://leetcode.com/problems/plus-one/) | 5/11 | Math | `Math/src/PlusOne.java` |
| ⬜ | 28 | 69 | [Sqrt(x)](https://leetcode.com/problems/sqrtx/) | 5/11 | Math | `Math/src/SqrRtx.java` |
| ⬜ | 29 | 496 | [Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) | 5/11 | Stack | `Stack/src/NextGreaterElement.java` |
| ⬜ | 30 | 101 | [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/) | 5/11 | Trees | `Trees/src/SymmetricTree.java` |
| ⬜ | 31 | 977 | [Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/) | 5/11 | Two Pointers | `TwoPointers/src/SquaresOfSortedArray.java` |
| ⬜ | 32 | 509 | [Fibonacci Number](https://leetcode.com/problems/fibonacci-number/) | 4/11 | Dynamic Programming | `DynamicProgramming/src/FibonacciNumber.java` |
| ⬜ | 33 | 205 | [Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings/) | 4/11 | Hash Map | `HashMap/src/IsomorphicStrings.java` |
| ⬜ | 34 | 217 | [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) | 4/11 | Hash Map | `HashMap/src/ContainsDuplicate.java` |
| ⬜ | 35 | 706 | [Design HashMap](https://leetcode.com/problems/design-hashmap/) | 4/11 | Hash Map | `HashMap/src/DesignHashMap.java` |
| ⬜ | 36 | 160 | [Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/) | 4/11 | Linked List | `LinkedList/src/IntersectionOfTwoLinkedLists.java` |
| ⬜ | 37 | 100 | [Same Tree](https://leetcode.com/problems/same-tree/) | 4/11 | Trees | `Trees/src/SameTree.java` |
| ⬜ | 38 | 110 | [Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/) | 4/11 | Trees | `Trees/src/BalancedBinaryTree.java` |
| ⬜ | 39 | 226 | [Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/) | 4/11 | Trees | `Trees/src/InvertBinaryTree.java` |
| ⬜ | 40 | 202 | [Happy Number](https://leetcode.com/problems/happy-number/) | 4/11 | Two Pointers | `TwoPointers/src/IsHappy.java` |

### Easy · Extra practice: in 0–3 sources (7)

Not in the cross-checked list, but solved in this repo. Good extra reps for the same patterns.

| Done | # | LC | Problem | Src | Pattern | File |
|---|---|---|---|---|---|---|
| ⬜ | 41 | 27 | [Remove Element](https://leetcode.com/problems/remove-element/) | 3/11 | Arrays & Strings | `ArraysAndStrings/src/RemoveElement.java` |
| ⬜ | 42 | 290 | [Word Pattern](https://leetcode.com/problems/word-pattern/) | – | Hash Map | `HashMap/src/WordPattern.java` |
| ⬜ | 43 | 350 | [Intersection of Two Arrays II](https://leetcode.com/problems/intersection-of-two-arrays-ii/) | – | Hash Map | `HashMap/src/IntersectionOfTwoArrays2.java`<br>`HashMap/src/IntersectionOfTwoArrays2Sorting.java` |
| ⬜ | 44 | 383 | [Ransom Note](https://leetcode.com/problems/ransom-note/) | – | Hash Map | `HashMap/src/RansomNote.java` |
| ⬜ | 45 | 228 | [Summary Ranges](https://leetcode.com/problems/summary-ranges/) | – | Intervals | `Intervals/src/SummaryRanges.java` |
| ⬜ | 46 | 219 | [Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/) | – | Sliding Window | `SlidingWindow/src/ContainsDuplicate.java` |
| ⬜ | 47 | 392 | [Is Subsequence](https://leetcode.com/problems/is-subsequence/) | – | Two Pointers | `TwoPointers/src/IsSubsequence.java` |

## 4. Phase 2: Medium

120 problems.

### Medium · Tier 1: in 8–11 sources (must do) (36)

| Done | # | LC | Problem | Src | Pattern | File |
|---|---|---|---|---|---|---|
| ⬜ | 48 | 53 | [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) | 11/11 | Dynamic Programming | `DynamicProgramming/src/MaximumSubarray.java` |
| ⬜ | 49 | 300 | [Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/) | 11/11 | Dynamic Programming | `DynamicProgramming/src/LongestIncreasingSubsequence.java` |
| ⬜ | 50 | 49 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | 11/11 | Hash Map | `HashMap/src/GroupAnagrams.java` |
| ⬜ | 51 | 78 | [Subsets](https://leetcode.com/problems/subsets/) | 10/11 | Backtracking | `Backtracking/src/Subsets.java` |
| ⬜ | 52 | 79 | [Word Search](https://leetcode.com/problems/word-search/) | 10/11 | Backtracking | `Backtracking/src/WordSearch.java` |
| ⬜ | 53 | 33 | [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/) | 10/11 | Binary Search | `FAQ/src/SearchInRotatedDistinctArray.java` |
| ⬜ | 54 | 198 | [House Robber](https://leetcode.com/problems/house-robber/) | 10/11 | Dynamic Programming | `DynamicProgramming/src/HouseRobber.java` |
| ⬜ | 55 | 200 | [Number of Islands](https://leetcode.com/problems/number-of-islands/) | 10/11 | Graphs | `Graphs/src/NumberOfIslands.java` |
| ⬜ | 56 | 3 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | 10/11 | Sliding Window | `SlidingWindow/src/LongestSubstring.java` |
| ⬜ | 57 | 5 | [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) | 10/11 | Two Pointers | `TwoPointers/src/LongestPalindrome.java` |
| ⬜ | 58 | 48 | [Rotate Image](https://leetcode.com/problems/rotate-image/) | 9/11 | Arrays & Strings | `ArraysAndStrings/src/RotateImage.java` |
| ⬜ | 59 | 54 | [Spiral Matrix](https://leetcode.com/problems/spiral-matrix/) | 9/11 | Arrays & Strings | `ArraysAndStrings/src/SpiralMatrix.java` |
| ⬜ | 60 | 238 | [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) | 9/11 | Arrays & Strings | `ArraysAndStrings/src/ProductExceptSelf.java` |
| ⬜ | 61 | 39 | [Combination Sum](https://leetcode.com/problems/combination-sum/) | 9/11 | Backtracking | `Backtracking/src/CombinationsSum.java` |
| ⬜ | 62 | 46 | [Permutations](https://leetcode.com/problems/permutations/) | 9/11 | Backtracking | `Backtracking/src/Permutations.java` |
| ⬜ | 63 | 62 | [Unique Paths](https://leetcode.com/problems/unique-paths/) | 9/11 | Dynamic Programming | `DynamicProgramming/src/UniquePaths.java` |
| ⬜ | 64 | 139 | [Word Break](https://leetcode.com/problems/word-break/) | 9/11 | Dynamic Programming | `DynamicProgramming/src/WordBreak.java` |
| ⬜ | 65 | 322 | [Coin Change](https://leetcode.com/problems/coin-change/) | 9/11 | Dynamic Programming | `DynamicProgramming/src/CoinChange.java` |
| ⬜ | 66 | 207 | [Course Schedule](https://leetcode.com/problems/course-schedule/) | 9/11 | Graphs | `Graphs/src/CourseSchedule.java` |
| ⬜ | 67 | 347 | [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | 9/11 | Heap | `Heap/src/TopKFrequentElements.java` |
| ⬜ | 68 | 56 | [Merge Intervals](https://leetcode.com/problems/merge-intervals/) | 9/11 | Intervals | `Intervals/src/MergeIntervals.java` |
| ⬜ | 69 | 19 | [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) | 9/11 | Linked List | `LinkedList/src/RemoveNthFromEnd.java` |
| ⬜ | 70 | 155 | [Min Stack](https://leetcode.com/problems/min-stack/) | 9/11 | Stack | `Stack/src/MinStack.java`<br>`Stack/src/StackUsingLinkedList.java` |
| ⬜ | 71 | 11 | [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) | 9/11 | Two Pointers | `TwoPointers/src/MostWaterInContainer.java` |
| ⬜ | 72 | 55 | [Jump Game](https://leetcode.com/problems/jump-game/) | 8/11 | Arrays & Strings | `ArraysAndStrings/src/JumpGame.java` |
| ⬜ | 73 | 73 | [Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/) | 8/11 | Arrays & Strings | `ArraysAndStrings/src/SetMatrixZeroes.java` |
| ⬜ | 74 | 152 | [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/) | 8/11 | Dynamic Programming | `DynamicProgramming/src/MaximumProductSubarray.java` |
| ⬜ | 75 | 133 | [Clone Graph](https://leetcode.com/problems/clone-graph/) | 8/11 | Graphs | `Graphs/src/CloneGraph.java` |
| ⬜ | 76 | 146 | [LRU Cache](https://leetcode.com/problems/lru-cache/) | 8/11 | Hash Map | `HashMap/src/LRUCache.java` |
| ⬜ | 77 | 215 | [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) | 8/11 | Heap | `Heap/src/KthLargestElement.java` |
| ⬜ | 78 | 2 | [Add Two Numbers](https://leetcode.com/problems/add-two-numbers/) | 8/11 | Linked List | `LinkedList/src/AddTwoNumbers.java` |
| ⬜ | 79 | 50 | [Pow(x, n)](https://leetcode.com/problems/powx-n/) | 8/11 | Math | `Math/src/Pow.java` |
| ⬜ | 80 | 98 | [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/) | 8/11 | Trees | `Trees/src/ValidateBinarySearchTree.java` |
| ⬜ | 81 | 102 | [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/) | 8/11 | Trees | `Trees/src/LevelOrderTraversal.java` |
| ⬜ | 82 | 75 | [Sort Colors](https://leetcode.com/problems/sort-colors/) | 8/11 | Two Pointers | `TwoPointers/src/SortColors.java` |
| ⬜ | 83 | 647 | [Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/) | 8/11 | Two Pointers | `FAQ/src/PalindromicSubstringCount.java` |

### Medium · Tier 2: in 6–7 sources (24)

| Done | # | LC | Problem | Src | Pattern | File |
|---|---|---|---|---|---|---|
| ⬜ | 84 | 8 | [String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi/) | 7/11 | Arrays & Strings | `ArraysAndStrings/src/StringToInteger.java` |
| ⬜ | 85 | 31 | [Next Permutation](https://leetcode.com/problems/next-permutation/) | 7/11 | Arrays & Strings | `ArraysAndStrings/src/NextPermutation.java` |
| ⬜ | 86 | 45 | [Jump Game II](https://leetcode.com/problems/jump-game-ii/) | 7/11 | Arrays & Strings | `ArraysAndStrings/src/JumpGame2.java` |
| ⬜ | 87 | 17 | [Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) | 7/11 | Backtracking | `Backtracking/src/LetterCombinations.java` |
| ⬜ | 88 | 34 | [Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | 7/11 | Binary Search | `BinarySearch/src/FirstAndLastPosition.java` |
| ⬜ | 89 | 74 | [Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/) | 7/11 | Binary Search | `BinarySearch/src/Search2DMatrix.java` |
| ⬜ | 90 | 64 | [Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/) | 7/11 | Dynamic Programming | `DynamicProgramming/src/MinimumPathSum.java` |
| ⬜ | 91 | 72 | [Edit Distance](https://leetcode.com/problems/edit-distance/) | 7/11 | Dynamic Programming | `DynamicProgramming/src/EditDistance.java` |
| ⬜ | 92 | 210 | [Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) | 7/11 | Graphs | `Graphs/src/CourseSchedule2.java` |
| ⬜ | 93 | 128 | [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | 7/11 | Hash Map | `HashMap/src/LongestConsecutive.java` |
| ⬜ | 94 | 560 | [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) | 7/11 | Hash Map | `HashMap/src/SubarraySumEqualsK.java` |
| ⬜ | 95 | 253 | [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) 🔒 | 7/11 | Intervals | `Intervals/src/MeetingRoomsII.java` |
| ⬜ | 96 | 7 | [Reverse Integer](https://leetcode.com/problems/reverse-integer/) | 7/11 | Math | `Math/src/ReverseInteger.java` |
| ⬜ | 97 | 105 | [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) | 7/11 | Trees | `Trees/src/ConstructTreeFromPreorderInorder.java` |
| ⬜ | 98 | 15 | [3Sum](https://leetcode.com/problems/3sum/) | 7/11 | Two Pointers | `TwoPointers/src/ThreeSum.java`<br>`TwoPointers/src/ThreeSumSkipDuplicates.java` |
| ⬜ | 99 | 151 | [Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/) | 6/11 | Arrays & Strings | `ArraysAndStrings/src/ReverseWordsInString.java` |
| ⬜ | 100 | 380 | [Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/) | 6/11 | Arrays & Strings | `ArraysAndStrings/src/RandomizedSet.java` |
| ⬜ | 101 | 162 | [Find Peak Element](https://leetcode.com/problems/find-peak-element/) | 6/11 | Binary Search | `BinarySearch/src/FindPeakElement.java` |
| ⬜ | 102 | 91 | [Decode Ways](https://leetcode.com/problems/decode-ways/) | 6/11 | Dynamic Programming | `DynamicProgramming/src/DecodeWays.java` |
| ⬜ | 103 | 138 | [Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/) | 6/11 | Linked List | `LinkedList/src/CopyListWithRandomPointer.java` |
| ⬜ | 104 | 739 | [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) | 6/11 | Stack | `Stack/src/DailyTemperatures.java` |
| ⬜ | 105 | 199 | [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/) | 6/11 | Trees | `Trees/src/RightSideView.java` |
| ⬜ | 106 | 235 | [Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) | 6/11 | Trees | `Trees/src/LowestCommonAncestorBST.java` |
| ⬜ | 107 | 287 | [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) | 6/11 | Two Pointers | `TwoPointers/src/FindDuplicateNumber.java` |

### Medium · Tier 3: in 4–5 sources (40)

| Done | # | LC | Problem | Src | Pattern | File |
|---|---|---|---|---|---|---|
| ⬜ | 108 | 6 | [Zigzag Conversion](https://leetcode.com/problems/zigzag-conversion/) | 5/11 | Arrays & Strings | `ArraysAndStrings/src/ZigzagConversion.java` |
| ⬜ | 109 | 12 | [Integer to Roman](https://leetcode.com/problems/integer-to-roman/) | 5/11 | Arrays & Strings | `ArraysAndStrings/src/IntToRoman.java` |
| ⬜ | 110 | 36 | [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) | 5/11 | Arrays & Strings | `ArraysAndStrings/src/ValidSudoku.java` |
| ⬜ | 111 | 122 | [Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/) | 5/11 | Arrays & Strings | `ArraysAndStrings/src/MaxProfit2.java` |
| ⬜ | 112 | 134 | [Gas Station](https://leetcode.com/problems/gas-station/) | 5/11 | Arrays & Strings | `ArraysAndStrings/src/GasStation.java` |
| ⬜ | 113 | 189 | [Rotate Array](https://leetcode.com/problems/rotate-array/) | 5/11 | Arrays & Strings | `ArraysAndStrings/src/RotateArray.java`<br>`ArraysAndStrings/src/RotateArrayCyclic.java` |
| ⬜ | 114 | 22 | [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) | 5/11 | Backtracking | `Backtracking/src/GenerateParentheses.java` |
| ⬜ | 115 | 153 | [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) | 5/11 | Binary Search | `BinarySearch/src/MinInRotatedSortedArray.java` |
| ⬜ | 116 | 240 | [Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/) | 5/11 | Binary Search | `BinarySearch/src/Search2DMatrix2.java` |
| ⬜ | 117 | 1143 | [Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/) | 5/11 | Dynamic Programming | `DynamicProgramming/src/LongestCommonSubsequence.java` |
| ⬜ | 118 | 695 | [Max Area of Island](https://leetcode.com/problems/max-area-of-island/) | 5/11 | Graphs | `Graphs/src/MaxAreaOfIsland.java` |
| ⬜ | 119 | 362 | [Design Hit Counter](https://leetcode.com/problems/design-hit-counter/) 🔒 | 5/11 | Hash Map | `HashMap/src/DesignHitCounter.java` |
| ⬜ | 120 | 981 | [Time Based Key-Value Store](https://leetcode.com/problems/time-based-key-value-store/) | 5/11 | Hash Map | `HashMap/src/TimeBasedKeyValueStore.java` |
| ⬜ | 121 | 621 | [Task Scheduler](https://leetcode.com/problems/task-scheduler/) | 5/11 | Heap | `Heap/src/TaskScheduler.java` |
| ⬜ | 122 | 24 | [Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/) | 5/11 | Linked List | `FAQ/src/SwapPairs.java` |
| ⬜ | 123 | 209 | [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/) | 5/11 | Sliding Window | `SlidingWindow/src/MinimumSizeSubArray.java` |
| ⬜ | 124 | 424 | [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/) | 5/11 | Sliding Window | `SlidingWindow/src/LongestRepeatingCharReplacement.java` |
| ⬜ | 125 | 227 | [Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/) | 5/11 | Stack | `Stack/src/BasicCalculator2.java` |
| ⬜ | 126 | 394 | [Decode String](https://leetcode.com/problems/decode-string/) | 5/11 | Stack | `Stack/src/DecodeString.java` |
| ⬜ | 127 | 208 | [Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/) | 5/11 | Trees | `Trees/src/Trie.java` |
| ⬜ | 128 | 230 | [Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) | 5/11 | Trees | `Trees/src/KthSmallestInBST.java` |
| ⬜ | 129 | 236 | [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) | 5/11 | Trees | `Trees/src/LowestCommonAncestor.java` |
| ⬜ | 130 | 443 | [String Compression](https://leetcode.com/problems/string-compression/) | 5/11 | Two Pointers | `TwoPointers/src/StringCompression.java` |
| ⬜ | 131 | 40 | [Combination Sum II](https://leetcode.com/problems/combination-sum-ii/) | 4/11 | Backtracking | `Backtracking/src/CombinationSum2.java` |
| ⬜ | 132 | 131 | [Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/) | 4/11 | Backtracking | `Backtracking/src/PalindromePartitioning.java` |
| ⬜ | 133 | 875 | [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/) | 4/11 | Binary Search | `BinarySearch/src/KokoEatingBananas.java` |
| ⬜ | 134 | 416 | [Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/) | 4/11 | Dynamic Programming | `DynamicProgramming/src/PartitionEqualSubsetSum.java` |
| ⬜ | 135 | 399 | [Evaluate Division](https://leetcode.com/problems/evaluate-division/) | 4/11 | Graphs | `Graphs/src/EvaluateDivision.java` |
| ⬜ | 136 | 417 | [Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/) | 4/11 | Graphs | `Graphs/src/PacificAtlanticWaterFlow.java` |
| ⬜ | 137 | 994 | [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) | 4/11 | Graphs | `Graphs/src/RottingOranges.java` |
| ⬜ | 138 | 57 | [Insert Interval](https://leetcode.com/problems/insert-interval/) | 4/11 | Intervals | `Intervals/src/InsertInterval.java` |
| ⬜ | 139 | 143 | [Reorder List](https://leetcode.com/problems/reorder-list/) | 4/11 | Linked List | `LinkedList/src/ReorderList.java` |
| ⬜ | 140 | 204 | [Count Primes](https://leetcode.com/problems/count-primes/) | 4/11 | Math | `Math/src/CountPrimes.java` |
| ⬜ | 141 | 150 | [Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) | 4/11 | Stack | `Stack/src/ReversePolishNotation.java` |
| ⬜ | 142 | 341 | [Flatten Nested List Iterator](https://leetcode.com/problems/flatten-nested-list-iterator/) | 4/11 | Stack | `Stack/src/FlattenNestedListIterator.java` |
| ⬜ | 143 | 503 | [Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/) | 4/11 | Stack | `Stack/src/NextGreaterElement2.java` |
| ⬜ | 144 | 735 | [Asteroid Collision](https://leetcode.com/problems/asteroid-collision/) | 4/11 | Stack | `Stack/src/AsteroidCollision.java` |
| ⬜ | 145 | 103 | [Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/) | 4/11 | Trees | `Trees/src/ZigzagLevelOrderTraversal.java` |
| ⬜ | 146 | 173 | [Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator/) | 4/11 | Trees | `Trees/src/BSTIterator.java` |
| ⬜ | 147 | 18 | [4Sum](https://leetcode.com/problems/4sum/) | 4/11 | Two Pointers | `TwoPointers/src/FourSum.java` |

### Medium · Extra practice: in 0–3 sources (20)

Not in the cross-checked list, but solved in this repo. Good extra reps for the same patterns.

| Done | # | LC | Problem | Src | Pattern | File |
|---|---|---|---|---|---|---|
| ⬜ | 148 | 713 | [Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/) | 3/11 | Sliding Window | `FAQ/src/SubarrayProductLessThanK.java` |
| ⬜ | 149 | 16 | [3Sum Closest](https://leetcode.com/problems/3sum-closest/) | 3/11 | Two Pointers | `FAQ/src/ThreeSumClosest.java` |
| ⬜ | 150 | 80 | [Remove Duplicates from Sorted Array II](https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/) | – | Arrays & Strings | `ArraysAndStrings/src/RemoveDuplicates2.java` |
| ⬜ | 151 | 274 | [H-Index](https://leetcode.com/problems/h-index/) | – | Arrays & Strings | `ArraysAndStrings/src/HIndex.java` |
| ⬜ | 152 | 47 | [Permutations II](https://leetcode.com/problems/permutations-ii/) | – | Backtracking | `FAQ/src/UniquePermutations.java` |
| ⬜ | 153 | 77 | [Combinations](https://leetcode.com/problems/combinations/) | – | Backtracking | `Backtracking/src/Combinations.java` |
| ⬜ | 154 | 81 | [Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/) | – | Binary Search | `FAQ/src/SearchInRotatedDuplicateArray.java` |
| ⬜ | 155 | 852 | [Peak Index in a Mountain Array](https://leetcode.com/problems/peak-index-in-a-mountain-array/) | – | Binary Search | `FAQ/src/PeakIndexInMountainArray.java` |
| ⬜ | 156 | 451 | [Sort Characters By Frequency](https://leetcode.com/problems/sort-characters-by-frequency/) | – | Hash Map | `FAQ/src/SortCharactersByFrequency.java` |
| ⬜ | 157 | 452 | [Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/) | – | Intervals | `Intervals/src/MinimumArrowsToBurst.java` |
| ⬜ | 158 | 61 | [Rotate List](https://leetcode.com/problems/rotate-list/) | – | Linked List | `LinkedList/src/RotateList.java` |
| ⬜ | 159 | 82 | [Remove Duplicates from Sorted List II](https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/) | – | Linked List | `LinkedList/src/RemoveDuplicatesfromSortedList.java` |
| ⬜ | 160 | 86 | [Partition List](https://leetcode.com/problems/partition-list/) | – | Linked List | `LinkedList/src/PartitionList.java` |
| ⬜ | 161 | 92 | [Reverse Linked List II](https://leetcode.com/problems/reverse-linked-list-ii/) | – | Linked List | `LinkedList/src/ReverseLinkedList.java` |
| ⬜ | 162 | 142 | [Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/) | – | Linked List | `FAQ/src/StartOfCycle.java` |
| ⬜ | 163 | 137 | [Single Number II](https://leetcode.com/problems/single-number-ii/) | – | Math | `Math/src/SingleNumber2.java` |
| ⬜ | 164 | 172 | [Factorial Trailing Zeroes](https://leetcode.com/problems/factorial-trailing-zeroes/) | – | Math | `Math/src/FactorialTrailingZero.java` |
| ⬜ | 165 | 260 | [Single Number III](https://leetcode.com/problems/single-number-iii/) | – | Math | `Math/src/SingleNumber3.java` |
| ⬜ | 166 | 71 | [Simplify Path](https://leetcode.com/problems/simplify-path/) | – | Stack | `Stack/src/SimplifiedPath.java` |
| ⬜ | 167 | 167 | [Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) | – | Two Pointers | `TwoPointers/src/TwoSum.java` |

## 5. Phase 3: Hard

26 problems.

### Hard · Tier 1: in 8–11 sources (must do) (8)

| Done | # | LC | Problem | Src | Pattern | File |
|---|---|---|---|---|---|---|
| ⬜ | 168 | 295 | [Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) | 10/11 | Heap | `Heap/src/MedianFromDataStream.java` |
| ⬜ | 169 | 42 | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) | 9/11 | Arrays & Strings | `ArraysAndStrings/src/RainWater.java` |
| ⬜ | 170 | 127 | [Word Ladder](https://leetcode.com/problems/word-ladder/) | 9/11 | Graphs | `Graphs/src/WordLadder.java` |
| ⬜ | 171 | 239 | [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) | 9/11 | Sliding Window | `SlidingWindow/src/SlidingWindowMaximum.java` |
| ⬜ | 172 | 297 | [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/) | 9/11 | Trees | `Trees/src/SerializeDeserializeTree.java` |
| ⬜ | 173 | 23 | [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) | 8/11 | Linked List | `LinkedList/src/MergeKSortedLists.java` |
| ⬜ | 174 | 76 | [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) | 8/11 | Sliding Window | `SlidingWindow/src/MinimumWindowSubstring.java` |
| ⬜ | 175 | 124 | [Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/) | 8/11 | Trees | `Trees/src/TreeMaxPathSum.java` |

### Hard · Tier 2: in 6–7 sources (6)

| Done | # | LC | Problem | Src | Pattern | File |
|---|---|---|---|---|---|---|
| ⬜ | 176 | 51 | [N-Queens](https://leetcode.com/problems/n-queens/) | 7/11 | Backtracking | `Backtracking/src/NQueens.java` |
| ⬜ | 177 | 4 | [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) | 7/11 | Binary Search | `BinarySearch/src/MedianOfTwoSortedArrays.java` |
| ⬜ | 178 | 84 | [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) | 7/11 | Stack | `Stack/src/LargestRectangleInHistogram.java` |
| ⬜ | 179 | 10 | [Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/) | 6/11 | Dynamic Programming | `DynamicProgramming/src/RegularExpressionMatching.java` |
| ⬜ | 180 | 460 | [LFU Cache](https://leetcode.com/problems/lfu-cache/) | 6/11 | Hash Map | `HashMap/src/LFUCache.java` |
| ⬜ | 181 | 32 | [Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/) | 6/11 | Stack | `Stack/src/LongestValidParentheses.java` |

### Hard · Tier 3: in 4–5 sources (7)

| Done | # | LC | Problem | Src | Pattern | File |
|---|---|---|---|---|---|---|
| ⬜ | 182 | 68 | [Text Justification](https://leetcode.com/problems/text-justification/) | 5/11 | Arrays & Strings | `ArraysAndStrings/src/TextJustification.java` |
| ⬜ | 183 | 37 | [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/) | 5/11 | Backtracking | `Backtracking/src/SudokuSolver.java` |
| ⬜ | 184 | 212 | [Word Search II](https://leetcode.com/problems/word-search-ii/) | 5/11 | Backtracking | `Backtracking/src/WordSearch2.java` |
| ⬜ | 185 | 269 | [Alien Dictionary](https://leetcode.com/problems/alien-dictionary/) 🔒 | 5/11 | Graphs | `Graphs/src/AlienDictionary.java` |
| ⬜ | 186 | 25 | [Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/) | 5/11 | Linked List | `LinkedList/src/ReverseNodesKGroup.java` |
| ⬜ | 187 | 140 | [Word Break II](https://leetcode.com/problems/word-break-ii/) | 4/11 | Dynamic Programming | `DynamicProgramming/src/WordBreak2.java` |
| ⬜ | 188 | 1235 | [Maximum Profit in Job Scheduling](https://leetcode.com/problems/maximum-profit-in-job-scheduling/) | 4/11 | Dynamic Programming | `DynamicProgramming/src/MaxProfitJobScheduling.java` |

### Hard · Extra practice: in 0–3 sources (5)

Not in the cross-checked list, but solved in this repo. Good extra reps for the same patterns.

| Done | # | LC | Problem | Src | Pattern | File |
|---|---|---|---|---|---|---|
| ⬜ | 189 | 224 | [Basic Calculator](https://leetcode.com/problems/basic-calculator/) | 3/11 | Stack | `Stack/src/BasicCalculator.java` |
| ⬜ | 190 | 135 | [Candy](https://leetcode.com/problems/candy/) | – | Arrays & Strings | `ArraysAndStrings/src/Candy.java` |
| ⬜ | 191 | 149 | [Max Points on a Line](https://leetcode.com/problems/max-points-on-a-line/) | – | Math | `Math/src/MaxPointsOnLine.java` |
| ⬜ | 192 | 30 | [Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words/) | – | Sliding Window | `SlidingWindow/src/SubstringWithConcatenation.java` |
| ⬜ | 193 | 895 | [Maximum Frequency Stack](https://leetcode.com/problems/maximum-frequency-stack/) | – | Stack | `FAQ/src/FreqStack.java` |

## 6. Revision schedule

Solving a problem once isn't enough: most of it is forgotten within a week. Re-solve on this schedule:

| When | What |
|---|---|
| Next day | Re-read `HOW TO THINK` and `STEPS` of yesterday's problems (5 min each). |
| After 3 days | Re-code every 🔁 problem from scratch without opening the file. |
| After 7 days | Re-code 3 random ✅ problems from that week, timed. |
| After 30 days | Re-code all Tier 1 problems once more, timed. |
| Before an interview | Read only the `HOW TO THINK` and `COMMON MISTAKES` of the Tier 1 and Tier 2 problems. |

A problem is done for good when you can solve it in under 20 minutes (Medium) without help, twice in a row.

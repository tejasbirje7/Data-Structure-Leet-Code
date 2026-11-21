# Problem Tracker

The 161 problems from the cross-checked list in [INTERVIEW-PREP.md](INTERVIEW-PREP.md#3-cross-checked-list) (found in 4+ of 11 sources), split into batches to solve and push one at a time.

**Progress: 153 / 161 done, 8 to do.**

## How to work a batch

1. Take the next batch with ⬜ problems.
2. For each problem, add `<Folder>/src/<Class>.java` with the revision header from [README.md](README.md#4-revision-notes-the-class-header).
3. Run each `main`, check the dry run and edge cases.
4. Change ⬜ to ✅, then commit and push the batch to `main`.

Legend:

- ⬜ to do, ✅ done.
- Tier 1 = 8–11 sources (must do), Tier 2 = 6–7, Tier 3 = 4–5.
- 🔒 = LeetCode Premium.
- **New folder** = the folder doesn't exist yet. Create it as an IntelliJ module with a `src/` folder, like the existing topic folders, and add it to the README layout table.

## Batches

| Batch | Folder | Problems | Tier 1 | Status |
|---|---|---|---|---|
| [1](#batch-1) | DynamicProgramming (new) | 9 | 8 | ✅ 9/9 |
| [2](#batch-2) | Heap (new) | 4 | 3 | ✅ 4/4 |
| [3](#batch-3) | Trees (new) | 9 | 5 | ✅ 9/9 |
| [4](#batch-4) | ArraysAndStrings | 8 | 3 | ✅ 8/8 |
| [5](#batch-5) | LinkedList | 5 | 2 | ✅ 5/5 |
| [6](#batch-6) | Graphs (new) | 10 | 4 | ✅ 10/10 |
| [7](#batch-7) | SlidingWindow, Math, Intervals | 5 | 1 | ✅ 5/5 |
| [8](#batch-8) | Backtracking | 8 | 2 | ✅ 8/8 |
| [9](#batch-9) | TwoPointers | 6 | 2 | ✅ 6/6 |
| [10](#batch-10) | HashMap | 7 | 1 | ✅ 7/7 |
| [11](#batch-11) | BinarySearch (new) | 8 | 0 | ✅ 8/8 |
| [12](#batch-12) | Stack | 9 | 0 | ✅ 9/9 |
| [13](#batch-13) | DynamicProgramming | 8 | 0 | ✅ 8/8 |
| [14](#batch-14) | Trees | 8 | 0 | ⬜ 0/8 |

### Batch 1

Folder: `DynamicProgramming/` **(new folder)**

| Status | # | Problem | Difficulty | Tier | Sources | File |
|---|---|---|---|---|---|---|
| ✅ | 53 | [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) | Medium | 1 | 11/11 | `DynamicProgramming/src/MaximumSubarray.java` |
| ✅ | 300 | [Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/) | Medium | 1 | 11/11 | `DynamicProgramming/src/LongestIncreasingSubsequence.java` |
| ✅ | 70 | [Climbing Stairs](https://leetcode.com/problems/climbing-stairs/) | Easy | 1 | 10/11 | `DynamicProgramming/src/ClimbingStairs.java` |
| ✅ | 198 | [House Robber](https://leetcode.com/problems/house-robber/) | Medium | 1 | 10/11 | `DynamicProgramming/src/HouseRobber.java` |
| ✅ | 62 | [Unique Paths](https://leetcode.com/problems/unique-paths/) | Medium | 1 | 9/11 | `DynamicProgramming/src/UniquePaths.java` |
| ✅ | 139 | [Word Break](https://leetcode.com/problems/word-break/) | Medium | 1 | 9/11 | `DynamicProgramming/src/WordBreak.java` |
| ✅ | 322 | [Coin Change](https://leetcode.com/problems/coin-change/) | Medium | 1 | 9/11 | `DynamicProgramming/src/CoinChange.java` |
| ✅ | 152 | [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/) | Medium | 1 | 8/11 | `DynamicProgramming/src/MaximumProductSubarray.java` |
| ✅ | 64 | [Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/) | Medium | 2 | 7/11 | `DynamicProgramming/src/MinimumPathSum.java` |

### Batch 2

Folder: `Heap/` **(new folder)**

| Status | # | Problem | Difficulty | Tier | Sources | File |
|---|---|---|---|---|---|---|
| ✅ | 295 | [Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) | Hard | 1 | 10/11 | `Heap/src/MedianFromDataStream.java` |
| ✅ | 347 | [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Medium | 1 | 9/11 | `Heap/src/TopKFrequentElements.java` |
| ✅ | 215 | [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) | Medium | 1 | 8/11 | `Heap/src/KthLargestElement.java` |
| ✅ | 621 | [Task Scheduler](https://leetcode.com/problems/task-scheduler/) | Medium | 3 | 5/11 | `Heap/src/TaskScheduler.java` |

### Batch 3

Folder: `Trees/` **(new folder)**

| Status | # | Problem | Difficulty | Tier | Sources | File |
|---|---|---|---|---|---|---|
| ✅ | 104 | [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) | Easy | 1 | 9/11 | `Trees/src/MaximumDepthOfBinaryTree.java` |
| ✅ | 297 | [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/) | Hard | 1 | 9/11 | `Trees/src/SerializeDeserializeTree.java` |
| ✅ | 98 | [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/) | Medium | 1 | 8/11 | `Trees/src/ValidateBinarySearchTree.java` |
| ✅ | 102 | [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/) | Medium | 1 | 8/11 | `Trees/src/LevelOrderTraversal.java` |
| ✅ | 124 | [Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/) | Hard | 1 | 8/11 | `Trees/src/TreeMaxPathSum.java` |
| ✅ | 105 | [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) | Medium | 2 | 7/11 | `Trees/src/ConstructTreeFromPreorderInorder.java` |
| ✅ | 199 | [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/) | Medium | 2 | 6/11 | `Trees/src/RightSideView.java` |
| ✅ | 235 | [Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) | Medium | 2 | 6/11 | `Trees/src/LowestCommonAncestorBST.java` |
| ✅ | 101 | [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/) | Easy | 3 | 5/11 | `Trees/src/SymmetricTree.java` |

### Batch 4

Folder: `ArraysAndStrings/`

| Status | # | Problem | Difficulty | Tier | Sources | File |
|---|---|---|---|---|---|---|
| ✅ | 48 | [Rotate Image](https://leetcode.com/problems/rotate-image/) | Medium | 1 | 9/11 | `ArraysAndStrings/src/RotateImage.java` |
| ✅ | 54 | [Spiral Matrix](https://leetcode.com/problems/spiral-matrix/) | Medium | 1 | 9/11 | `ArraysAndStrings/src/SpiralMatrix.java` |
| ✅ | 73 | [Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/) | Medium | 1 | 8/11 | `ArraysAndStrings/src/SetMatrixZeroes.java` |
| ✅ | 8 | [String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi/) | Medium | 2 | 7/11 | `ArraysAndStrings/src/StringToInteger.java` |
| ✅ | 31 | [Next Permutation](https://leetcode.com/problems/next-permutation/) | Medium | 2 | 7/11 | `ArraysAndStrings/src/NextPermutation.java` |
| ✅ | 6 | [Zigzag Conversion](https://leetcode.com/problems/zigzag-conversion/) | Medium | 3 | 5/11 | `ArraysAndStrings/src/ZigzagConversion.java` |
| ✅ | 36 | [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) | Medium | 3 | 5/11 | `ArraysAndStrings/src/ValidSudoku.java` |
| ✅ | 118 | [Pascal's Triangle](https://leetcode.com/problems/pascals-triangle/) | Easy | 3 | 5/11 | `ArraysAndStrings/src/PascalsTriangle.java` |

### Batch 5

Folder: `LinkedList/`

| Status | # | Problem | Difficulty | Tier | Sources | File |
|---|---|---|---|---|---|---|
| ✅ | 206 | [Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) | Easy | 1 | 11/11 | `LinkedList/src/ReverseList.java` |
| ✅ | 23 | [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) | Hard | 1 | 8/11 | `LinkedList/src/MergeKSortedLists.java` |
| ✅ | 234 | [Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/) | Easy | 2 | 7/11 | `LinkedList/src/PalindromeLinkedList.java` |
| ✅ | 143 | [Reorder List](https://leetcode.com/problems/reorder-list/) | Medium | 3 | 4/11 | `LinkedList/src/ReorderList.java` |
| ✅ | 160 | [Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/) | Easy | 3 | 4/11 | `LinkedList/src/IntersectionOfTwoLinkedLists.java` |

### Batch 6

Folder: `Graphs/` **(new folder)**

| Status | # | Problem | Difficulty | Tier | Sources | File |
|---|---|---|---|---|---|---|
| ✅ | 200 | [Number of Islands](https://leetcode.com/problems/number-of-islands/) | Medium | 1 | 10/11 | `Graphs/src/NumberOfIslands.java` |
| ✅ | 127 | [Word Ladder](https://leetcode.com/problems/word-ladder/) | Hard | 1 | 9/11 | `Graphs/src/WordLadder.java` |
| ✅ | 207 | [Course Schedule](https://leetcode.com/problems/course-schedule/) | Medium | 1 | 9/11 | `Graphs/src/CourseSchedule.java` |
| ✅ | 133 | [Clone Graph](https://leetcode.com/problems/clone-graph/) | Medium | 1 | 8/11 | `Graphs/src/CloneGraph.java` |
| ✅ | 210 | [Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) | Medium | 2 | 7/11 | `Graphs/src/CourseSchedule2.java` |
| ✅ | 269 | [Alien Dictionary](https://leetcode.com/problems/alien-dictionary/) 🔒 | Hard | 3 | 5/11 | `Graphs/src/AlienDictionary.java` |
| ✅ | 695 | [Max Area of Island](https://leetcode.com/problems/max-area-of-island/) | Medium | 3 | 5/11 | `Graphs/src/MaxAreaOfIsland.java` |
| ✅ | 399 | [Evaluate Division](https://leetcode.com/problems/evaluate-division/) | Medium | 3 | 4/11 | `Graphs/src/EvaluateDivision.java` |
| ✅ | 417 | [Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/) | Medium | 3 | 4/11 | `Graphs/src/PacificAtlanticWaterFlow.java` |
| ✅ | 994 | [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) | Medium | 3 | 4/11 | `Graphs/src/RottingOranges.java` |

### Batch 7

Folder: `SlidingWindow/`, `Math/`, `Intervals/`

| Status | # | Problem | Difficulty | Tier | Sources | File |
|---|---|---|---|---|---|---|
| ✅ | 239 | [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) | Hard | 1 | 9/11 | `SlidingWindow/src/SlidingWindowMaximum.java` |
| ✅ | 424 | [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/) | Medium | 3 | 5/11 | `SlidingWindow/src/LongestRepeatingCharReplacement.java` |
| ✅ | 7 | [Reverse Integer](https://leetcode.com/problems/reverse-integer/) | Medium | 2 | 7/11 | `Math/src/ReverseInteger.java` |
| ✅ | 204 | [Count Primes](https://leetcode.com/problems/count-primes/) | Medium | 3 | 4/11 | `Math/src/CountPrimes.java` |
| ✅ | 253 | [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) 🔒 | Medium | 2 | 7/11 | `Intervals/src/MeetingRoomsII.java` |

### Batch 8

Folder: `Backtracking/`

| Status | # | Problem | Difficulty | Tier | Sources | File |
|---|---|---|---|---|---|---|
| ✅ | 78 | [Subsets](https://leetcode.com/problems/subsets/) | Medium | 1 | 10/11 | `Backtracking/src/Subsets.java` |
| ✅ | 79 | [Word Search](https://leetcode.com/problems/word-search/) | Medium | 1 | 10/11 | `Backtracking/src/WordSearch.java` |
| ✅ | 51 | [N-Queens](https://leetcode.com/problems/n-queens/) | Hard | 2 | 7/11 | `Backtracking/src/NQueens.java` |
| ✅ | 22 | [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) | Medium | 3 | 5/11 | `Backtracking/src/GenerateParentheses.java` |
| ✅ | 37 | [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/) | Hard | 3 | 5/11 | `Backtracking/src/SudokuSolver.java` |
| ✅ | 212 | [Word Search II](https://leetcode.com/problems/word-search-ii/) | Hard | 3 | 5/11 | `Backtracking/src/WordSearch2.java` |
| ✅ | 40 | [Combination Sum II](https://leetcode.com/problems/combination-sum-ii/) | Medium | 3 | 4/11 | `Backtracking/src/CombinationSum2.java` |
| ✅ | 131 | [Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/) | Medium | 3 | 4/11 | `Backtracking/src/PalindromePartitioning.java` |

### Batch 9

Folder: `TwoPointers/`

| Status | # | Problem | Difficulty | Tier | Sources | File |
|---|---|---|---|---|---|---|
| ✅ | 75 | [Sort Colors](https://leetcode.com/problems/sort-colors/) | Medium | 1 | 8/11 | `TwoPointers/src/SortColors.java` |
| ✅ | 283 | [Move Zeroes](https://leetcode.com/problems/move-zeroes/) | Easy | 1 | 8/11 | `TwoPointers/src/MoveZeroes.java` |
| ✅ | 287 | [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) | Medium | 2 | 6/11 | `TwoPointers/src/FindDuplicateNumber.java` |
| ✅ | 443 | [String Compression](https://leetcode.com/problems/string-compression/) | Medium | 3 | 5/11 | `TwoPointers/src/StringCompression.java` |
| ✅ | 977 | [Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/) | Easy | 3 | 5/11 | `TwoPointers/src/SquaresOfSortedArray.java` |
| ✅ | 18 | [4Sum](https://leetcode.com/problems/4sum/) | Medium | 3 | 4/11 | `TwoPointers/src/FourSum.java` |

### Batch 10

Folder: `HashMap/`

| Status | # | Problem | Difficulty | Tier | Sources | File |
|---|---|---|---|---|---|---|
| ✅ | 146 | [LRU Cache](https://leetcode.com/problems/lru-cache/) | Medium | 1 | 8/11 | `HashMap/src/LRUCache.java` |
| ✅ | 387 | [First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/) | Easy | 2 | 7/11 | `HashMap/src/FirstUniqueCharacter.java` |
| ✅ | 560 | [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) | Medium | 2 | 7/11 | `HashMap/src/SubarraySumEqualsK.java` |
| ✅ | 460 | [LFU Cache](https://leetcode.com/problems/lfu-cache/) | Hard | 2 | 6/11 | `HashMap/src/LFUCache.java` |
| ✅ | 362 | [Design Hit Counter](https://leetcode.com/problems/design-hit-counter/) 🔒 | Medium | 3 | 5/11 | `HashMap/src/DesignHitCounter.java` |
| ✅ | 981 | [Time Based Key-Value Store](https://leetcode.com/problems/time-based-key-value-store/) | Medium | 3 | 5/11 | `HashMap/src/TimeBasedKeyValueStore.java` |
| ✅ | 706 | [Design HashMap](https://leetcode.com/problems/design-hashmap/) | Easy | 3 | 4/11 | `HashMap/src/DesignHashMap.java` |

### Batch 11

Folder: `BinarySearch/` **(new folder)**

| Status | # | Problem | Difficulty | Tier | Sources | File |
|---|---|---|---|---|---|---|
| ✅ | 4 | [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) | Hard | 2 | 7/11 | `BinarySearch/src/MedianOfTwoSortedArrays.java` |
| ✅ | 34 | [Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | Medium | 2 | 7/11 | `BinarySearch/src/FirstAndLastPosition.java` |
| ✅ | 74 | [Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/) | Medium | 2 | 7/11 | `BinarySearch/src/Search2DMatrix.java` |
| ✅ | 162 | [Find Peak Element](https://leetcode.com/problems/find-peak-element/) | Medium | 2 | 6/11 | `BinarySearch/src/FindPeakElement.java` |
| ✅ | 153 | [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) | Medium | 3 | 5/11 | `BinarySearch/src/MinInRotatedSortedArray.java` |
| ✅ | 240 | [Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/) | Medium | 3 | 5/11 | `BinarySearch/src/Search2DMatrix2.java` |
| ✅ | 704 | [Binary Search](https://leetcode.com/problems/binary-search/) | Easy | 3 | 5/11 | `BinarySearch/src/BinarySearch.java` |
| ✅ | 875 | [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/) | Medium | 3 | 4/11 | `BinarySearch/src/KokoEatingBananas.java` |

### Batch 12

Folder: `Stack/`

| Status | # | Problem | Difficulty | Tier | Sources | File |
|---|---|---|---|---|---|---|
| ✅ | 84 | [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) | Hard | 2 | 7/11 | `Stack/src/LargestRectangleInHistogram.java` |
| ✅ | 32 | [Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/) | Hard | 2 | 6/11 | `Stack/src/LongestValidParentheses.java` |
| ✅ | 739 | [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) | Medium | 2 | 6/11 | `Stack/src/DailyTemperatures.java` |
| ✅ | 227 | [Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/) | Medium | 3 | 5/11 | `Stack/src/BasicCalculator2.java` |
| ✅ | 394 | [Decode String](https://leetcode.com/problems/decode-string/) | Medium | 3 | 5/11 | `Stack/src/DecodeString.java` |
| ✅ | 496 | [Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) | Easy | 3 | 5/11 | `Stack/src/NextGreaterElement.java` |
| ✅ | 341 | [Flatten Nested List Iterator](https://leetcode.com/problems/flatten-nested-list-iterator/) | Medium | 3 | 4/11 | `Stack/src/FlattenNestedListIterator.java` |
| ✅ | 503 | [Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/) | Medium | 3 | 4/11 | `Stack/src/NextGreaterElement2.java` |
| ✅ | 735 | [Asteroid Collision](https://leetcode.com/problems/asteroid-collision/) | Medium | 3 | 4/11 | `Stack/src/AsteroidCollision.java` |

### Batch 13

Folder: `DynamicProgramming/`

| Status | # | Problem | Difficulty | Tier | Sources | File |
|---|---|---|---|---|---|---|
| ✅ | 72 | [Edit Distance](https://leetcode.com/problems/edit-distance/) | Medium | 2 | 7/11 | `DynamicProgramming/src/EditDistance.java` |
| ✅ | 10 | [Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/) | Hard | 2 | 6/11 | `DynamicProgramming/src/RegularExpressionMatching.java` |
| ✅ | 91 | [Decode Ways](https://leetcode.com/problems/decode-ways/) | Medium | 2 | 6/11 | `DynamicProgramming/src/DecodeWays.java` |
| ✅ | 1143 | [Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/) | Medium | 3 | 5/11 | `DynamicProgramming/src/LongestCommonSubsequence.java` |
| ✅ | 140 | [Word Break II](https://leetcode.com/problems/word-break-ii/) | Hard | 3 | 4/11 | `DynamicProgramming/src/WordBreak2.java` |
| ✅ | 416 | [Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/) | Medium | 3 | 4/11 | `DynamicProgramming/src/PartitionEqualSubsetSum.java` |
| ✅ | 509 | [Fibonacci Number](https://leetcode.com/problems/fibonacci-number/) | Easy | 3 | 4/11 | `DynamicProgramming/src/FibonacciNumber.java` |
| ✅ | 1235 | [Maximum Profit in Job Scheduling](https://leetcode.com/problems/maximum-profit-in-job-scheduling/) | Hard | 3 | 4/11 | `DynamicProgramming/src/MaxProfitJobScheduling.java` |

### Batch 14

Folder: `Trees/`

| Status | # | Problem | Difficulty | Tier | Sources | File |
|---|---|---|---|---|---|---|
| ⬜ | 208 | [Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/) | Medium | 3 | 5/11 | `Trees/src/Trie.java` |
| ⬜ | 230 | [Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) | Medium | 3 | 5/11 | `Trees/src/KthSmallestInBST.java` |
| ⬜ | 236 | [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) | Medium | 3 | 5/11 | `Trees/src/LowestCommonAncestor.java` |
| ⬜ | 100 | [Same Tree](https://leetcode.com/problems/same-tree/) | Easy | 3 | 4/11 | `Trees/src/SameTree.java` |
| ⬜ | 103 | [Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/) | Medium | 3 | 4/11 | `Trees/src/ZigzagLevelOrderTraversal.java` |
| ⬜ | 110 | [Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/) | Easy | 3 | 4/11 | `Trees/src/BalancedBinaryTree.java` |
| ⬜ | 173 | [Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator/) | Medium | 3 | 4/11 | `Trees/src/BSTIterator.java` |
| ⬜ | 226 | [Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/) | Easy | 3 | 4/11 | `Trees/src/InvertBinaryTree.java` |

## Already done (57)

| Status | # | Problem | Difficulty | Tier | Sources | File |
|---|---|---|---|---|---|---|
| ✅ | 21 | [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) | Easy | 1 | 11/11 | `LinkedList/src/MergeTwoLinkedList.java` |
| ✅ | 49 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Medium | 1 | 11/11 | `HashMap/src/GroupAnagrams.java` |
| ✅ | 1 | [Two Sum](https://leetcode.com/problems/two-sum/) | Easy | 1 | 10/11 | `HashMap/src/TwoSum.java` |
| ✅ | 3 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Medium | 1 | 10/11 | `SlidingWindow/src/LongestSubstring.java` |
| ✅ | 5 | [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) | Medium | 1 | 10/11 | `TwoPointers/src/LongestPalindrome.java`, `FAQ/src/LongestPalindromicSubString.java` |
| ✅ | 33 | [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/) | Medium | 1 | 10/11 | `FAQ/src/SearchInRotatedDistinctArray.java` |
| ✅ | 11 | [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) | Medium | 1 | 9/11 | `TwoPointers/src/MostWaterInContainer.java` |
| ✅ | 19 | [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) | Medium | 1 | 9/11 | `LinkedList/src/RemoveNthFromEnd.java` |
| ✅ | 39 | [Combination Sum](https://leetcode.com/problems/combination-sum/) | Medium | 1 | 9/11 | `Backtracking/src/CombinationsSum.java` |
| ✅ | 42 | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) | Hard | 1 | 9/11 | `ArraysAndStrings/src/RainWater.java` |
| ✅ | 46 | [Permutations](https://leetcode.com/problems/permutations/) | Medium | 1 | 9/11 | `FAQ/src/PossiblePermutations.java`, `Backtracking/src/Permutations.java` |
| ✅ | 56 | [Merge Intervals](https://leetcode.com/problems/merge-intervals/) | Medium | 1 | 9/11 | `FAQ/src/MergeIntervals.java`, `Intervals/src/MergeIntervals.java` |
| ✅ | 121 | [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | Easy | 1 | 9/11 | `ArraysAndStrings/src/MaxProfit.java` |
| ✅ | 125 | [Valid Palindrome](https://leetcode.com/problems/valid-palindrome/) | Easy | 1 | 9/11 | `TwoPointers/src/ValidPalindrome.java` |
| ✅ | 141 | [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) | Easy | 1 | 9/11 | `FAQ/src/LinkedListCycleDetector.java`, `LinkedList/src/LinkedListCycle.java` |
| ✅ | 155 | [Min Stack](https://leetcode.com/problems/min-stack/) | Medium | 1 | 9/11 | `Stack/src/MinStack.java`, `Stack/src/StackUsingLinkedList.java` |
| ✅ | 238 | [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) | Medium | 1 | 9/11 | `ArraysAndStrings/src/ProductExceptSelf.java` |
| ✅ | 242 | [Valid Anagram](https://leetcode.com/problems/valid-anagram/) | Easy | 1 | 9/11 | `HashMap/src/ValidAnagram.java` |
| ✅ | 543 | [Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/) | Easy | 1 | 9/11 | `FAQ/src/DiameterOfBinaryTree.java` |
| ✅ | 2 | [Add Two Numbers](https://leetcode.com/problems/add-two-numbers/) | Medium | 1 | 8/11 | `LinkedList/src/AddTwoNumbers.java` |
| ✅ | 20 | [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) | Easy | 1 | 8/11 | `Stack/src/ValidParentheses.java` |
| ✅ | 50 | [Pow(x, n)](https://leetcode.com/problems/powx-n/) | Medium | 1 | 8/11 | `Math/src/Pow.java` |
| ✅ | 55 | [Jump Game](https://leetcode.com/problems/jump-game/) | Medium | 1 | 8/11 | `ArraysAndStrings/src/JumpGame.java` |
| ✅ | 76 | [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) | Hard | 1 | 8/11 | `FAQ/src/MinimumWindowSubstring.java`, `SlidingWindow/src/MinimumWindowSubstring.java` |
| ✅ | 647 | [Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/) | Medium | 1 | 8/11 | `FAQ/src/PalindromicSubstringCount.java` |
| ✅ | 14 | [Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/) | Easy | 2 | 7/11 | `ArraysAndStrings/src/LongestPrefixString.java` |
| ✅ | 15 | [3Sum](https://leetcode.com/problems/3sum/) | Medium | 2 | 7/11 | `TwoPointers/src/ThreeSum.java`, `FAQ/src/ThreeSum.java` |
| ✅ | 17 | [Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) | Medium | 2 | 7/11 | `Backtracking/src/LetterCombinations.java` |
| ✅ | 26 | [Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) | Easy | 2 | 7/11 | `ArraysAndStrings/src/RemoveDuplicates.java` |
| ✅ | 28 | [Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/) | Easy | 2 | 7/11 | `ArraysAndStrings/src/FirstOccurrenceOfString.java` |
| ✅ | 45 | [Jump Game II](https://leetcode.com/problems/jump-game-ii/) | Medium | 2 | 7/11 | `ArraysAndStrings/src/JumpGame2.java` |
| ✅ | 88 | [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/) | Easy | 2 | 7/11 | `ArraysAndStrings/src/Merge.java` |
| ✅ | 128 | [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | Medium | 2 | 7/11 | `HashMap/src/LongestConsecutive.java` |
| ✅ | 169 | [Majority Element](https://leetcode.com/problems/majority-element/) | Easy | 2 | 7/11 | `ArraysAndStrings/src/MajorityElement.java` |
| ✅ | 13 | [Roman to Integer](https://leetcode.com/problems/roman-to-integer/) | Easy | 2 | 6/11 | `ArraysAndStrings/src/RomanToInt.java` |
| ✅ | 136 | [Single Number](https://leetcode.com/problems/single-number/) | Easy | 2 | 6/11 | `Math/src/SingleNumber.java` |
| ✅ | 138 | [Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/) | Medium | 2 | 6/11 | `LinkedList/src/CopyListWithRandomPointer.java` |
| ✅ | 151 | [Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/) | Medium | 2 | 6/11 | `ArraysAndStrings/src/ReverseWordsInString.java` |
| ✅ | 268 | [Missing Number](https://leetcode.com/problems/missing-number/) | Easy | 2 | 6/11 | `FAQ/src/MissingNumberFinder.java` |
| ✅ | 380 | [Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/) | Medium | 2 | 6/11 | `ArraysAndStrings/src/RandomizedSet.java` |
| ✅ | 9 | [Palindrome Number](https://leetcode.com/problems/palindrome-number/) | Easy | 3 | 5/11 | `Math/src/Pallindrome.java` |
| ✅ | 12 | [Integer to Roman](https://leetcode.com/problems/integer-to-roman/) | Medium | 3 | 5/11 | `ArraysAndStrings/src/IntToRoman.java` |
| ✅ | 24 | [Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/) | Medium | 3 | 5/11 | `FAQ/src/SwapPairs.java` |
| ✅ | 25 | [Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/) | Hard | 3 | 5/11 | `LinkedList/src/ReverseNodesKGroup.java` |
| ✅ | 66 | [Plus One](https://leetcode.com/problems/plus-one/) | Easy | 3 | 5/11 | `Math/src/PlusOne.java` |
| ✅ | 68 | [Text Justification](https://leetcode.com/problems/text-justification/) | Hard | 3 | 5/11 | `ArraysAndStrings/src/TextJustification.java` |
| ✅ | 69 | [Sqrt(x)](https://leetcode.com/problems/sqrtx/) | Easy | 3 | 5/11 | `Math/src/SqrRtx.java` |
| ✅ | 122 | [Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/) | Medium | 3 | 5/11 | `ArraysAndStrings/src/MaxProfit2.java` |
| ✅ | 134 | [Gas Station](https://leetcode.com/problems/gas-station/) | Medium | 3 | 5/11 | `ArraysAndStrings/src/GasStation.java` |
| ✅ | 189 | [Rotate Array](https://leetcode.com/problems/rotate-array/) | Medium | 3 | 5/11 | `ArraysAndStrings/src/RotateArray.java`, `ArraysAndStrings/src/RotateArrayCyclic.java` |
| ✅ | 209 | [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/) | Medium | 3 | 5/11 | `SlidingWindow/src/MinimumSizeSubArray.java` |
| ✅ | 349 | [Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/) | Easy | 3 | 5/11 | `HashMap/src/IntersectionOfTwoArrays.java` |
| ✅ | 57 | [Insert Interval](https://leetcode.com/problems/insert-interval/) | Medium | 3 | 4/11 | `FAQ/src/InsertIntervals.java`, `Intervals/src/InsertInterval.java` |
| ✅ | 150 | [Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) | Medium | 3 | 4/11 | `Stack/src/ReversePolishNotation.java` |
| ✅ | 202 | [Happy Number](https://leetcode.com/problems/happy-number/) | Easy | 3 | 4/11 | `TwoPointers/src/IsHappy.java` |
| ✅ | 205 | [Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings/) | Easy | 3 | 4/11 | `HashMap/src/IsomorphicStrings.java` |
| ✅ | 217 | [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) | Easy | 3 | 4/11 | `HashMap/src/ContainsDuplicate.java` |

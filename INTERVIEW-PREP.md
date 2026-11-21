# Interview Prep: Most-Asked DSA Problems

Research notes on which data structures and algorithms problems companies ask most, checked against this repo.

- Data snapshot: LeetCode company tags as of **20 Oct 2025** (dataset repo last pushed 20 Oct 2026).
- Research done: **21 Nov 2026**.
- **Cross-checked list (section 3):** 161 problems found in 4+ of 11 sources; **92** are in the repo.
- **Top 100 from one dataset (section 4):** **55 of 100** are in the repo.
- ✅ = already solved in this repo (matched by the LeetCode link in each file's header).
- **Working list:** [PROBLEM-TRACKER.md](PROBLEM-TRACKER.md) splits the missing problems from section 3 into batches.

## Contents

1. [How the ranking was built](#1-how-the-ranking-was-built)
2. [Key findings](#2-key-findings)
3. [Cross-checked list](#3-cross-checked-list)
4. [Top 100, grouped by pattern](#4-top-100-grouped-by-pattern)
5. [Most asked by company segment](#5-most-asked-by-company-segment)
6. [Difficulty and topics](#6-difficulty-and-topics)
7. [YouTube playlist: DSA In 90 Days (CTO Bhaiya)](#7-youtube-playlist-dsa-in-90-days-cto-bhaiya)
8. [Resources](#8-resources)
9. [Refreshing this file](#9-refreshing-this-file)

## 1. How the ranking was built

This describes the single-dataset ranking used in sections 4–6. Section 3 explains how the cross-check across 11 sources works.

- **Source:** [liquidslr/interview-company-wise-problems](https://github.com/liquidslr/interview-company-wise-problems).
  - One folder per company (471 companies), built from LeetCode's company tags.
  - Each company has five CSV files: last 30 days, 3 months, 6 months, older than 6 months, all time.
  - Columns: Difficulty, Title, Frequency, Acceptance Rate, Link, Topics.
- **Window used:** the last-6-months list, so the ranking reflects what is being asked now.
- **Target companies (51):**
  - Big Tech (23): Google, Amazon, Microsoft, Meta, Apple, Uber, LinkedIn, Atlassian, Salesforce, Oracle, Adobe, Bloomberg, Netflix, Airbnb, Stripe, Walmart Labs, Intuit, PayPal, ServiceNow, Nutanix, VMware, Booking.com, DoorDash.
  - India product (17): Flipkart, PhonePe, Swiggy, Zomato, razorpay, Paytm, CRED, Meesho, Myntra, Groww, Navi, Dream11, Zeta, Juspay, Sprinklr, InMobi, Media.net.
  - Banks & fintech (11): Goldman Sachs, J.P. Morgan, Morgan Stanley, Deutsche Bank, Barclays, Visa, Mastercard, American Express, Capital One, Arcesium, DE Shaw.
- **Score:** how many of the 51 companies asked the problem; ties broken by the sum of LeetCode's frequency values.
- **Excluded:** SQL (Database), Shell and Concurrency problems, since they are not data structures problems.
- **Limits:**
  - LeetCode tags don't record the candidate's level, so this is what companies ask in general, not per level.
  - India product companies have very few recent tags (0–9 problems each in 6 months), so their own list in section 5 uses all-time data.

## 2. Key findings

- **Cross-check:** 161 problems are in 4+ of 11 sources. The 56 in 8+ sources are the safest bets; 10 of those are still missing here.
- **In all 11 sources:** Group Anagrams, Maximum Subarray, Longest Increasing Subsequence, Merge Two Sorted Lists, Reverse Linked List.
- **Top 100 vs cross-check:** 86 of the single-dataset top 100 are also in 4+ sources; the rest (for example Fruit Into Baskets, Predict the Winner, Concatenation of Array) are only popular in recent tags.
- **Asked almost everywhere:** Merge Intervals (16 of 51 companies), Longest Substring Without Repeating Characters, Number of Islands, LRU Cache, Best Time to Buy and Sell Stock, Group Anagrams.
- **India product favourites (all time):** Trapping Rain Water (8 of 17), Longest Substring Without Repeating Characters, LRU Cache, Merge Intervals (7 each), Asteroid Collision, Maximum Subarray, Best Time to Buy and Sell Stock (6 each).
- **Mostly Medium:** see section 6 for the split.
- **Top topics:** Array, String, Hash Table, Dynamic Programming, Sorting, Two Pointers, then Binary Search, Stack, DFS/BFS.
- **Data-structure design problems show up often:** LRU Cache, Insert Delete GetRandom O(1), Time Based Key-Value Store, Find Median from Data Stream, Serialize and Deserialize Binary Tree.
- **Biggest gaps in this repo:** Stack (1/7), Binary Search (1/8), Graphs (BFS / DFS / Union-Find / Topological Sort) (0/7), Backtracking (1/5).

## 3. Cross-checked list

**161 problems** that at least 4 of 11 sources agree on.

Section 4's top 100 uses one dataset. This section checks every problem against all the sources that could be read, and keeps the ones that at least 4 of them agree on.

### 3.1 Sources used

| Code | Source | Type | Problems counted | How it was counted |
|---|---|---|---|---|
| D1 | [liquidslr (Oct 2025, 6 months)](https://github.com/liquidslr/interview-company-wise-problems) | Company tags | 150 | Top 150 by number of the 51 target companies, last 6 months (51 of the 51 target companies found) |
| D2 | [rahulb99 (Jun 2025, 6 months)](https://github.com/rahulb99/leetcode) | Company tags | 150 | Top 150, same method; older snapshot that keeps each company's top 100 (51 of the 51 target companies found) |
| D3 | [snehasishroy (Aug 2026, 6 months)](https://github.com/snehasishroy/leetcode-companywise-interview-questions) | Company tags | 150 | Top 150, same method (39 of the 51 target companies found) |
| D4 | [krishnadey30 (2023, 6 months)](https://www.github.com/krishnadey30/LeetCode-Questions-CompanyWise) | Company tags | 150 | Top 150, same method (24 of the 51 target companies found) |
| D5 | [crakcode-hub (Apr 2025, all time)](https://github.com/crakcode-hub/dsa-leetcode-questions) | Company tags | 150 | Top 150 by number of target companies in its Companies column (36 of the 51 target companies found) |
| D6 | [MysteryVaibhav (Apr 2019, 1 year)](https://github.com/MysteryVaibhav/leetcode_company_wise_questions) | Company tags (PDF) | 150 | Top 150, same method, from the PDF text (29 of the 51 target companies found) |
| C1 | [VikashPR MAANG 100](https://github.com/VikashPR/MAANG-Interview-Prep-100-DSA) | Curated list | 119 | Every LeetCode link in the list |
| C2 | [DEV devcorner (product companies)](https://dev.to/devcorner/top-dsa-questions-asked-in-product-based-companies-2c2a) | Curated list | 128 | Every LeetCode link in the article |
| C3 | [Preplaced 50](https://preplaced.in/blog/50-most-asked-dsa-interview-questions) | Curated list | 48 | Every LeetCode number in the list |
| C4 | [GeeksforGeeks Top 100](https://www.geeksforgeeks.org/dsa/top-100-data-structure-and-algorithms-dsa-interview-questions-topic-wise/) | Curated list | 79 | Titles matched by hand to the LeetCode equivalent |
| C5 | [Top 150 DSA (Avinash-singh11)](https://github.com/Avinash-singh11/top-150-DSA-questions-) | Curated list | 93 | Titles matched by hand to the LeetCode equivalent |

A problem's score is the number of these 11 sources that include it.

Sources that could not be used:

- [LeetCode Discuss: Top 100 DSA Interview Questions (4258631)](https://leetcode.com/discuss/post/4258631/top-100-dsa-interview-questions-by-anony-jlgg/), [study guide 4255692](https://leetcode.com/discuss/study-guide/4255692/top-100-dsa-interview-questions) and [post 7417074](https://leetcode.com/discuss/post/7417074/): blocked by LeetCode's Cloudflare check (HTTP 403).
- [singhsidhukuldeep: DSA interview questions](https://singhsidhukuldeep.github.io/Interview-Questions/data-structures-algorithms): page removed (HTTP 404; the site's repo no longer has it).
- [devidnyk/leetcode-wrapper](https://github.com/devidnyk/leetcode-wrapper): an app with no data of its own; it pulls the snehasishroy dataset (D3), so counting it would count D3 twice.

Notes:

- All six datasets come from LeetCode's company tags, taken at different times (2019 to 2026), so they are not fully independent.
- GeeksforGeeks: 21 of 100 items have no LeetCode equivalent (for example 0/1 Knapsack, Count Inversions, Topological Sorting) and are left out.
- Top 150 DSA: 42 of 138 titles are classic algorithms with no single LeetCode problem (for example Dijkstra, Kruskal, Rat in a Maze) and are left out.
- SQL, Shell and Concurrency problems are excluded, as in the rest of this file.

### 3.2 Summary

| Tier | Problems | In repo | Missing |
|---|---|---|---|
| Tier 1: 8–11 sources (must do) | 56 | 46 | 10 |
| Tier 2: 6–7 sources | 40 | 22 | 18 |
| Tier 3: 4–5 sources | 65 | 24 | 41 |
| **Total** | **161** | **92** | **69** |

### 3.3 Tier 1: 8–11 sources (must do)

| # | LeetCode | Problem | Difficulty | Sources | Found in | Repo |
|---|---|---|---|---|---|---|
| 1 | 49 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Medium | 11/11 | C1 C2 C3 C4 C5 D1 D2 D3 D4 D5 D6 | ✅ `HashMap/src/GroupAnagrams.java` |
| 2 | 53 | [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) | Medium | 11/11 | C1 C2 C3 C4 C5 D1 D2 D3 D4 D5 D6 | ✅ `DynamicProgramming/src/MaximumSubarray.java` |
| 3 | 300 | [Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/) | Medium | 11/11 | C1 C2 C3 C4 C5 D1 D2 D3 D4 D5 D6 | ✅ `DynamicProgramming/src/LongestIncreasingSubsequence.java` |
| 4 | 21 | [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) | Easy | 11/11 | C1 C2 C3 C4 C5 D1 D2 D3 D4 D5 D6 | ✅ `LinkedList/src/MergeTwoLinkedList.java` |
| 5 | 206 | [Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) | Easy | 11/11 | C1 C2 C3 C4 C5 D1 D2 D3 D4 D5 D6 | ✅ `LinkedList/src/ReverseList.java` |
| 6 | 3 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Medium | 10/11 | C1 C2 C3 C4 C5 D1 D2 D3 D4 D5 | ✅ `SlidingWindow/src/LongestSubstring.java` |
| 7 | 200 | [Number of Islands](https://leetcode.com/problems/number-of-islands/) | Medium | 10/11 | C1 C2 C3 C4 C5 D1 D2 D3 D4 D5 |  |
| 8 | 295 | [Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) | Hard | 10/11 | C1 C2 C3 C4 D1 D2 D3 D4 D5 D6 | ✅ `Heap/src/MedianFromDataStream.java` |
| 9 | 5 | [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) | Medium | 10/11 | C1 C2 C3 C4 D1 D2 D3 D4 D5 D6 | ✅ `TwoPointers/src/LongestPalindrome.java`, `FAQ/src/LongestPalindromicSubString.java` |
| 10 | 1 | [Two Sum](https://leetcode.com/problems/two-sum/) | Easy | 10/11 | C1 C2 C3 C4 C5 D1 D2 D3 D4 D5 | ✅ `HashMap/src/TwoSum.java` |
| 11 | 70 | [Climbing Stairs](https://leetcode.com/problems/climbing-stairs/) | Easy | 10/11 | C1 C2 C3 C4 D1 D2 D3 D4 D5 D6 | ✅ `DynamicProgramming/src/ClimbingStairs.java` |
| 12 | 198 | [House Robber](https://leetcode.com/problems/house-robber/) | Medium | 10/11 | C1 C2 C3 C4 C5 D1 D2 D3 D5 D6 | ✅ `DynamicProgramming/src/HouseRobber.java` |
| 13 | 33 | [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/) | Medium | 10/11 | C1 C2 C3 C4 C5 D1 D2 D3 D4 D5 | ✅ `FAQ/src/SearchInRotatedDistinctArray.java` |
| 14 | 79 | [Word Search](https://leetcode.com/problems/word-search/) | Medium | 10/11 | C1 C2 C3 C4 C5 D1 D2 D3 D4 D5 |  |
| 15 | 78 | [Subsets](https://leetcode.com/problems/subsets/) | Medium | 10/11 | C1 C2 C3 C5 D1 D2 D3 D4 D5 D6 |  |
| 16 | 56 | [Merge Intervals](https://leetcode.com/problems/merge-intervals/) | Medium | 9/11 | C1 C2 C3 C4 D1 D2 D3 D4 D5 | ✅ `FAQ/src/MergeIntervals.java`, `Intervals/src/MergeIntervals.java` |
| 17 | 121 | [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | Easy | 9/11 | C1 C2 C3 C4 D1 D2 D3 D4 D5 | ✅ `ArraysAndStrings/src/MaxProfit.java` |
| 18 | 239 | [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) | Hard | 9/11 | C2 C3 C4 C5 D1 D2 D3 D4 D5 |  |
| 19 | 347 | [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Medium | 9/11 | C1 C2 C4 D1 D2 D3 D4 D5 D6 | ✅ `Heap/src/TopKFrequentElements.java` |
| 20 | 11 | [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) | Medium | 9/11 | C1 C2 C4 D1 D2 D3 D4 D5 D6 | ✅ `TwoPointers/src/MostWaterInContainer.java` |
| 21 | 207 | [Course Schedule](https://leetcode.com/problems/course-schedule/) | Medium | 9/11 | C1 C2 C3 C4 D1 D2 D3 D4 D5 |  |
| 22 | 42 | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) | Hard | 9/11 | C1 C2 C4 C5 D1 D2 D3 D4 D5 | ✅ `ArraysAndStrings/src/RainWater.java` |
| 23 | 104 | [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) | Easy | 9/11 | C1 C2 C3 C4 C5 D1 D3 D5 D6 | ✅ `Trees/src/MaximumDepthOfBinaryTree.java` |
| 24 | 297 | [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/) | Hard | 9/11 | C1 C2 C3 C4 C5 D1 D2 D4 D5 | ✅ `Trees/src/SerializeDeserializeTree.java` |
| 25 | 54 | [Spiral Matrix](https://leetcode.com/problems/spiral-matrix/) | Medium | 9/11 | C1 C2 C3 C4 D1 D2 D3 D4 D5 | ✅ `ArraysAndStrings/src/SpiralMatrix.java` |
| 26 | 125 | [Valid Palindrome](https://leetcode.com/problems/valid-palindrome/) | Easy | 9/11 | C1 C4 C5 D1 D2 D3 D4 D5 D6 | ✅ `TwoPointers/src/ValidPalindrome.java` |
| 27 | 139 | [Word Break](https://leetcode.com/problems/word-break/) | Medium | 9/11 | C1 C2 C4 C5 D1 D2 D3 D4 D5 | ✅ `DynamicProgramming/src/WordBreak.java` |
| 28 | 322 | [Coin Change](https://leetcode.com/problems/coin-change/) | Medium | 9/11 | C1 C2 C3 C5 D1 D2 D3 D4 D5 | ✅ `DynamicProgramming/src/CoinChange.java` |
| 29 | 39 | [Combination Sum](https://leetcode.com/problems/combination-sum/) | Medium | 9/11 | C1 C2 C3 C4 C5 D2 D4 D5 D6 | ✅ `Backtracking/src/CombinationsSum.java` |
| 30 | 543 | [Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/) | Easy | 9/11 | C1 C2 C3 C5 D1 D3 D4 D5 D6 | ✅ `FAQ/src/DiameterOfBinaryTree.java` |
| 31 | 155 | [Min Stack](https://leetcode.com/problems/min-stack/) | Medium | 9/11 | C1 C2 C5 D1 D2 D3 D4 D5 D6 | ✅ `Stack/src/MinStack.java`, `Stack/src/StackUsingLinkedList.java` |
| 32 | 46 | [Permutations](https://leetcode.com/problems/permutations/) | Medium | 9/11 | C1 C2 C3 C5 D1 D2 D3 D4 D5 | ✅ `FAQ/src/PossiblePermutations.java`, `Backtracking/src/Permutations.java` |
| 33 | 238 | [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) | Medium | 9/11 | C1 C2 C3 C4 D1 D2 D3 D4 D5 | ✅ `ArraysAndStrings/src/ProductExceptSelf.java` |
| 34 | 19 | [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) | Medium | 9/11 | C1 C3 C4 C5 D2 D3 D4 D5 D6 | ✅ `LinkedList/src/RemoveNthFromEnd.java` |
| 35 | 48 | [Rotate Image](https://leetcode.com/problems/rotate-image/) | Medium | 9/11 | C1 C2 C3 D1 D2 D3 D4 D5 D6 | ✅ `ArraysAndStrings/src/RotateImage.java` |
| 36 | 62 | [Unique Paths](https://leetcode.com/problems/unique-paths/) | Medium | 9/11 | C1 C2 C5 D1 D2 D3 D4 D5 D6 | ✅ `DynamicProgramming/src/UniquePaths.java` |
| 37 | 242 | [Valid Anagram](https://leetcode.com/problems/valid-anagram/) | Easy | 9/11 | C1 C3 C5 D1 D2 D3 D4 D5 D6 | ✅ `HashMap/src/ValidAnagram.java` |
| 38 | 127 | [Word Ladder](https://leetcode.com/problems/word-ladder/) | Hard | 9/11 | C1 C2 C3 D1 D2 D3 D4 D5 D6 |  |
| 39 | 141 | [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) | Easy | 9/11 | C1 C2 C3 C4 C5 D2 D4 D5 D6 | ✅ `FAQ/src/LinkedListCycleDetector.java`, `LinkedList/src/LinkedListCycle.java` |
| 40 | 146 | [LRU Cache](https://leetcode.com/problems/lru-cache/) | Medium | 8/11 | C2 C3 C5 D1 D2 D3 D4 D5 |  |
| 41 | 23 | [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) | Hard | 8/11 | C1 C2 C4 D1 D2 D3 D4 D5 | ✅ `LinkedList/src/MergeKSortedLists.java` |
| 42 | 20 | [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) | Easy | 8/11 | C1 C2 C3 D1 D2 D3 D4 D5 | ✅ `Stack/src/ValidParentheses.java` |
| 43 | 124 | [Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/) | Hard | 8/11 | C1 C2 C4 D1 D2 D3 D4 D5 | ✅ `Trees/src/TreeMaxPathSum.java` |
| 44 | 55 | [Jump Game](https://leetcode.com/problems/jump-game/) | Medium | 8/11 | C1 C2 C4 D1 D2 D3 D4 D5 | ✅ `ArraysAndStrings/src/JumpGame.java` |
| 45 | 215 | [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) | Medium | 8/11 | C1 C2 C3 D1 D2 D3 D4 D5 | ✅ `Heap/src/KthLargestElement.java` |
| 46 | 283 | [Move Zeroes](https://leetcode.com/problems/move-zeroes/) | Easy | 8/11 | C2 C5 D1 D2 D3 D4 D5 D6 |  |
| 47 | 75 | [Sort Colors](https://leetcode.com/problems/sort-colors/) | Medium | 8/11 | C1 C4 D1 D2 D3 D4 D5 D6 |  |
| 48 | 2 | [Add Two Numbers](https://leetcode.com/problems/add-two-numbers/) | Medium | 8/11 | C2 C3 C5 D1 D2 D3 D4 D5 | ✅ `LinkedList/src/AddTwoNumbers.java` |
| 49 | 152 | [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/) | Medium | 8/11 | C1 C5 D1 D2 D3 D4 D5 D6 | ✅ `DynamicProgramming/src/MaximumProductSubarray.java` |
| 50 | 76 | [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) | Hard | 8/11 | C1 C2 C4 D1 D2 D3 D4 D5 | ✅ `FAQ/src/MinimumWindowSubstring.java`, `SlidingWindow/src/MinimumWindowSubstring.java` |
| 51 | 50 | [Pow(x, n)](https://leetcode.com/problems/powx-n/) | Medium | 8/11 | C1 C2 D1 D2 D3 D4 D5 D6 | ✅ `Math/src/Pow.java` |
| 52 | 73 | [Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/) | Medium | 8/11 | C1 C2 C3 D1 D2 D3 D4 D6 | ✅ `ArraysAndStrings/src/SetMatrixZeroes.java` |
| 53 | 98 | [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/) | Medium | 8/11 | C1 C2 C4 C5 D1 D2 D4 D5 | ✅ `Trees/src/ValidateBinarySearchTree.java` |
| 54 | 102 | [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/) | Medium | 8/11 | C1 C2 C4 C5 D2 D4 D5 D6 | ✅ `Trees/src/LevelOrderTraversal.java` |
| 55 | 647 | [Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/) | Medium | 8/11 | C1 C2 C4 C5 D2 D3 D4 D5 | ✅ `FAQ/src/PalindromicSubstringCount.java` |
| 56 | 133 | [Clone Graph](https://leetcode.com/problems/clone-graph/) | Medium | 8/11 | C1 C2 C3 C4 C5 D2 D4 D6 |  |

### 3.4 Tier 2: 6–7 sources

| # | LeetCode | Problem | Difficulty | Sources | Found in | Repo |
|---|---|---|---|---|---|---|
| 1 | 210 | [Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) | Medium | 7/11 | C1 C2 D1 D2 D3 D4 D5 |  |
| 2 | 560 | [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) | Medium | 7/11 | C2 C5 D1 D2 D3 D4 D5 |  |
| 3 | 15 | [3Sum](https://leetcode.com/problems/3sum/) | Medium | 7/11 | C1 C2 D1 D2 D3 D4 D5 | ✅ `TwoPointers/src/ThreeSum.java`, `FAQ/src/ThreeSum.java` |
| 4 | 45 | [Jump Game II](https://leetcode.com/problems/jump-game-ii/) | Medium | 7/11 | C1 C2 D1 D2 D3 D4 D5 | ✅ `ArraysAndStrings/src/JumpGame2.java` |
| 5 | 17 | [Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) | Medium | 7/11 | C1 C2 D1 D2 D3 D4 D5 | ✅ `Backtracking/src/LetterCombinations.java` |
| 6 | 34 | [Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | Medium | 7/11 | C5 D1 D2 D3 D4 D5 D6 |  |
| 7 | 387 | [First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/) | Easy | 7/11 | C5 D1 D2 D3 D4 D5 D6 |  |
| 8 | 14 | [Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/) | Easy | 7/11 | C5 D1 D2 D3 D4 D5 D6 | ✅ `ArraysAndStrings/src/LongestPrefixString.java` |
| 9 | 128 | [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | Medium | 7/11 | C2 C4 D1 D2 D3 D4 D5 | ✅ `HashMap/src/LongestConsecutive.java` |
| 10 | 4 | [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) | Hard | 7/11 | C1 C2 D1 D2 D3 D4 D5 |  |
| 11 | 64 | [Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/) | Medium | 7/11 | C2 C5 D1 D2 D4 D5 D6 | ✅ `DynamicProgramming/src/MinimumPathSum.java` |
| 12 | 8 | [String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi/) | Medium | 7/11 | C5 D1 D2 D3 D4 D5 D6 | ✅ `ArraysAndStrings/src/StringToInteger.java` |
| 13 | 105 | [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) | Medium | 7/11 | C1 C2 C4 C5 D1 D4 D6 | ✅ `Trees/src/ConstructTreeFromPreorderInorder.java` |
| 14 | 72 | [Edit Distance](https://leetcode.com/problems/edit-distance/) | Medium | 7/11 | C1 C2 C5 D2 D3 D5 D6 |  |
| 15 | 28 | [Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/) | Easy | 7/11 | C3 D1 D2 D3 D4 D5 D6 | ✅ `ArraysAndStrings/src/FirstOccurrenceOfString.java` |
| 16 | 84 | [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) | Hard | 7/11 | C1 C2 C4 C5 D1 D2 D3 |  |
| 17 | 169 | [Majority Element](https://leetcode.com/problems/majority-element/) | Easy | 7/11 | C3 C5 D1 D2 D3 D4 D6 | ✅ `ArraysAndStrings/src/MajorityElement.java` |
| 18 | 253 | [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) | Medium | 7/11 | C1 C5 D1 D2 D3 D4 D5 |  |
| 19 | 88 | [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/) | Easy | 7/11 | C4 C5 D1 D2 D3 D4 D5 | ✅ `ArraysAndStrings/src/Merge.java` |
| 20 | 51 | [N-Queens](https://leetcode.com/problems/n-queens/) | Hard | 7/11 | C1 C2 C3 C5 D1 D2 D3 |  |
| 21 | 31 | [Next Permutation](https://leetcode.com/problems/next-permutation/) | Medium | 7/11 | C1 C5 D1 D2 D3 D4 D5 | ✅ `ArraysAndStrings/src/NextPermutation.java` |
| 22 | 234 | [Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/) | Easy | 7/11 | C2 C5 D1 D3 D4 D5 D6 | ✅ `LinkedList/src/PalindromeLinkedList.java` |
| 23 | 26 | [Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) | Easy | 7/11 | C2 D1 D2 D3 D4 D5 D6 | ✅ `ArraysAndStrings/src/RemoveDuplicates.java` |
| 24 | 7 | [Reverse Integer](https://leetcode.com/problems/reverse-integer/) | Medium | 7/11 | C1 D1 D2 D3 D4 D5 D6 |  |
| 25 | 74 | [Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/) | Medium | 7/11 | C1 C2 C5 D1 D2 D3 D6 |  |
| 26 | 162 | [Find Peak Element](https://leetcode.com/problems/find-peak-element/) | Medium | 6/11 | C4 C5 D1 D2 D3 D6 |  |
| 27 | 380 | [Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/) | Medium | 6/11 | D1 D2 D3 D4 D5 D6 | ✅ `ArraysAndStrings/src/RandomizedSet.java` |
| 28 | 739 | [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) | Medium | 6/11 | C1 C2 C5 D1 D3 D5 |  |
| 29 | 287 | [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) | Medium | 6/11 | C1 C5 D1 D4 D5 D6 |  |
| 30 | 32 | [Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/) | Hard | 6/11 | C4 D1 D2 D3 D4 D5 |  |
| 31 | 460 | [LFU Cache](https://leetcode.com/problems/lfu-cache/) | Hard | 6/11 | C3 D1 D2 D3 D5 D6 |  |
| 32 | 10 | [Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/) | Hard | 6/11 | C1 C2 D2 D3 D4 D5 |  |
| 33 | 151 | [Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/) | Medium | 6/11 | D1 D2 D3 D4 D5 D6 | ✅ `ArraysAndStrings/src/ReverseWordsInString.java` |
| 34 | 13 | [Roman to Integer](https://leetcode.com/problems/roman-to-integer/) | Easy | 6/11 | D1 D2 D3 D4 D5 D6 | ✅ `ArraysAndStrings/src/RomanToInt.java` |
| 35 | 136 | [Single Number](https://leetcode.com/problems/single-number/) | Easy | 6/11 | C2 D1 D2 D3 D4 D5 | ✅ `Math/src/SingleNumber.java` |
| 36 | 138 | [Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/) | Medium | 6/11 | C2 C4 D2 D3 D4 D5 | ✅ `LinkedList/src/CopyListWithRandomPointer.java` |
| 37 | 235 | [Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) | Medium | 6/11 | C1 C2 C3 C4 D5 D6 | ✅ `Trees/src/LowestCommonAncestorBST.java` |
| 38 | 268 | [Missing Number](https://leetcode.com/problems/missing-number/) | Easy | 6/11 | C1 C4 C5 D2 D5 D6 | ✅ `FAQ/src/MissingNumberFinder.java` |
| 39 | 199 | [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/) | Medium | 6/11 | C1 D2 D3 D4 D5 D6 | ✅ `Trees/src/RightSideView.java` |
| 40 | 91 | [Decode Ways](https://leetcode.com/problems/decode-ways/) | Medium | 6/11 | C1 C4 D2 D4 D5 D6 |  |

### 3.5 Tier 3: 4–5 sources

| # | LeetCode | Problem | Difficulty | Sources | Found in | Repo |
|---|---|---|---|---|---|---|
| 1 | 122 | [Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/) | Medium | 5/11 | D1 D2 D3 D4 D5 | ✅ `ArraysAndStrings/src/MaxProfit2.java` |
| 2 | 134 | [Gas Station](https://leetcode.com/problems/gas-station/) | Medium | 5/11 | C1 C2 D1 D2 D3 | ✅ `ArraysAndStrings/src/GasStation.java` |
| 3 | 1143 | [Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/) | Medium | 5/11 | C1 C2 C4 C5 D1 |  |
| 4 | 424 | [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/) | Medium | 5/11 | C1 C2 C4 D1 D3 |  |
| 5 | 443 | [String Compression](https://leetcode.com/problems/string-compression/) | Medium | 5/11 | D1 D2 D3 D4 D6 |  |
| 6 | 496 | [Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) | Easy | 5/11 | C2 C4 C5 D1 D3 |  |
| 7 | 189 | [Rotate Array](https://leetcode.com/problems/rotate-array/) | Medium | 5/11 | C5 D1 D2 D3 D6 | ✅ `ArraysAndStrings/src/RotateArray.java`, `ArraysAndStrings/src/RotateArrayCyclic.java` |
| 8 | 240 | [Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/) | Medium | 5/11 | D1 D3 D4 D5 D6 |  |
| 9 | 977 | [Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/) | Easy | 5/11 | D1 D3 D4 D5 D6 |  |
| 10 | 621 | [Task Scheduler](https://leetcode.com/problems/task-scheduler/) | Medium | 5/11 | C1 D1 D2 D3 D5 | ✅ `Heap/src/TaskScheduler.java` |
| 11 | 981 | [Time Based Key-Value Store](https://leetcode.com/problems/time-based-key-value-store/) | Medium | 5/11 | C1 D1 D3 D4 D5 |  |
| 12 | 36 | [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) | Medium | 5/11 | D1 D2 D3 D5 D6 | ✅ `ArraysAndStrings/src/ValidSudoku.java` |
| 13 | 6 | [Zigzag Conversion](https://leetcode.com/problems/zigzag-conversion/) | Medium | 5/11 | D1 D2 D3 D5 D6 | ✅ `ArraysAndStrings/src/ZigzagConversion.java` |
| 14 | 269 | [Alien Dictionary](https://leetcode.com/problems/alien-dictionary/) | Hard | 5/11 | C1 D1 D4 D5 D6 |  |
| 15 | 227 | [Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/) | Medium | 5/11 | D2 D3 D4 D5 D6 |  |
| 16 | 704 | [Binary Search](https://leetcode.com/problems/binary-search/) | Easy | 5/11 | C1 C2 C3 C5 D1 |  |
| 17 | 394 | [Decode String](https://leetcode.com/problems/decode-string/) | Medium | 5/11 | D1 D2 D3 D4 D5 |  |
| 18 | 153 | [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) | Medium | 5/11 | C1 C5 D4 D5 D6 |  |
| 19 | 22 | [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) | Medium | 5/11 | D1 D2 D3 D4 D5 |  |
| 20 | 12 | [Integer to Roman](https://leetcode.com/problems/integer-to-roman/) | Medium | 5/11 | D1 D2 D4 D5 D6 | ✅ `ArraysAndStrings/src/IntToRoman.java` |
| 21 | 349 | [Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/) | Easy | 5/11 | C5 D1 D3 D5 D6 | ✅ `HashMap/src/IntersectionOfTwoArrays.java` |
| 22 | 230 | [Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) | Medium | 5/11 | C1 C2 C4 D5 D6 |  |
| 23 | 209 | [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/) | Medium | 5/11 | C2 D1 D3 D4 D6 | ✅ `SlidingWindow/src/MinimumSizeSubArray.java` |
| 24 | 9 | [Palindrome Number](https://leetcode.com/problems/palindrome-number/) | Easy | 5/11 | D1 D2 D3 D5 D6 | ✅ `Math/src/Pallindrome.java` |
| 25 | 118 | [Pascal's Triangle](https://leetcode.com/problems/pascals-triangle/) | Easy | 5/11 | D1 D2 D3 D5 D6 | ✅ `ArraysAndStrings/src/PascalsTriangle.java` |
| 26 | 66 | [Plus One](https://leetcode.com/problems/plus-one/) | Easy | 5/11 | D1 D2 D3 D4 D6 | ✅ `Math/src/PlusOne.java` |
| 27 | 25 | [Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/) | Hard | 5/11 | C5 D1 D2 D4 D6 | ✅ `LinkedList/src/ReverseNodesKGroup.java` |
| 28 | 69 | [Sqrt(x)](https://leetcode.com/problems/sqrtx/) | Easy | 5/11 | C5 D1 D2 D3 D6 | ✅ `Math/src/SqrRtx.java` |
| 29 | 101 | [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/) | Easy | 5/11 | C2 D3 D4 D5 D6 | ✅ `Trees/src/SymmetricTree.java` |
| 30 | 68 | [Text Justification](https://leetcode.com/problems/text-justification/) | Hard | 5/11 | D1 D2 D3 D5 D6 | ✅ `ArraysAndStrings/src/TextJustification.java` |
| 31 | 362 | [Design Hit Counter](https://leetcode.com/problems/design-hit-counter/) | Medium | 5/11 | D2 D3 D4 D5 D6 |  |
| 32 | 208 | [Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/) | Medium | 5/11 | C1 C2 C4 D5 D6 |  |
| 33 | 236 | [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) | Medium | 5/11 | C2 C5 D2 D4 D5 |  |
| 34 | 695 | [Max Area of Island](https://leetcode.com/problems/max-area-of-island/) | Medium | 5/11 | C4 D2 D4 D5 D6 |  |
| 35 | 37 | [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/) | Hard | 5/11 | C2 C5 D4 D5 D6 |  |
| 36 | 24 | [Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/) | Medium | 5/11 | C5 D2 D4 D5 D6 | ✅ `FAQ/src/SwapPairs.java` |
| 37 | 212 | [Word Search II](https://leetcode.com/problems/word-search-ii/) | Hard | 5/11 | C1 C2 D2 D4 D5 |  |
| 38 | 735 | [Asteroid Collision](https://leetcode.com/problems/asteroid-collision/) | Medium | 4/11 | C2 D1 D2 D3 |  |
| 39 | 994 | [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) | Medium | 4/11 | C2 D1 D2 D3 |  |
| 40 | 18 | [4Sum](https://leetcode.com/problems/4sum/) | Medium | 4/11 | C2 D1 D2 D3 |  |
| 41 | 131 | [Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/) | Medium | 4/11 | C1 C5 D1 D3 |  |
| 42 | 143 | [Reorder List](https://leetcode.com/problems/reorder-list/) | Medium | 4/11 | C1 C4 D1 D3 | ✅ `LinkedList/src/ReorderList.java` |
| 43 | 110 | [Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/) | Easy | 4/11 | C5 D1 D3 D4 |  |
| 44 | 103 | [Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/) | Medium | 4/11 | C2 D2 D4 D5 |  |
| 45 | 217 | [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) | Easy | 4/11 | C1 D1 D2 D3 | ✅ `HashMap/src/ContainsDuplicate.java` |
| 46 | 509 | [Fibonacci Number](https://leetcode.com/problems/fibonacci-number/) | Easy | 4/11 | C2 C5 D1 D3 |  |
| 47 | 202 | [Happy Number](https://leetcode.com/problems/happy-number/) | Easy | 4/11 | D1 D2 D5 D6 | ✅ `TwoPointers/src/IsHappy.java` |
| 48 | 205 | [Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings/) | Easy | 4/11 | C2 D2 D4 D5 | ✅ `HashMap/src/IsomorphicStrings.java` |
| 49 | 875 | [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/) | Medium | 4/11 | C1 D1 D2 D3 |  |
| 50 | 1235 | [Maximum Profit in Job Scheduling](https://leetcode.com/problems/maximum-profit-in-job-scheduling/) | Hard | 4/11 | C5 D1 D2 D3 |  |
| 51 | 503 | [Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/) | Medium | 4/11 | C2 C4 D1 D3 |  |
| 52 | 40 | [Combination Sum II](https://leetcode.com/problems/combination-sum-ii/) | Medium | 4/11 | C1 C2 D2 D5 |  |
| 53 | 204 | [Count Primes](https://leetcode.com/problems/count-primes/) | Medium | 4/11 | C2 D4 D5 D6 |  |
| 54 | 706 | [Design HashMap](https://leetcode.com/problems/design-hashmap/) | Easy | 4/11 | C5 D4 D5 D6 |  |
| 55 | 399 | [Evaluate Division](https://leetcode.com/problems/evaluate-division/) | Medium | 4/11 | D2 D3 D4 D6 |  |
| 56 | 150 | [Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) | Medium | 4/11 | C1 C5 D4 D5 | ✅ `Stack/src/ReversePolishNotation.java` |
| 57 | 160 | [Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/) | Easy | 4/11 | C2 C5 D5 D6 | ✅ `LinkedList/src/IntersectionOfTwoLinkedLists.java` |
| 58 | 226 | [Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/) | Easy | 4/11 | C1 C3 C4 D6 |  |
| 59 | 416 | [Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/) | Medium | 4/11 | C1 C2 C3 C5 |  |
| 60 | 100 | [Same Tree](https://leetcode.com/problems/same-tree/) | Easy | 4/11 | C1 C3 D3 D6 |  |
| 61 | 341 | [Flatten Nested List Iterator](https://leetcode.com/problems/flatten-nested-list-iterator/) | Medium | 4/11 | D2 D4 D5 D6 |  |
| 62 | 140 | [Word Break II](https://leetcode.com/problems/word-break-ii/) | Hard | 4/11 | D2 D4 D5 D6 |  |
| 63 | 57 | [Insert Interval](https://leetcode.com/problems/insert-interval/) | Medium | 4/11 | C1 C4 D5 D6 | ✅ `FAQ/src/InsertIntervals.java`, `Intervals/src/InsertInterval.java` |
| 64 | 417 | [Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/) | Medium | 4/11 | C1 C2 C3 C4 |  |
| 65 | 173 | [Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator/) | Medium | 4/11 | C2 D4 D5 D6 |  |

<details>
<summary>Also seen in exactly 3 sources (28 problems, 4 in repo)</summary>

| # | LeetCode | Problem | Difficulty | Sources | Found in | Repo |
|---|---|---|---|---|---|---|
| 1 | 1004 | [Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/) | Medium | 3/11 | D1 D2 D3 |  |
| 2 | 721 | [Accounts Merge](https://leetcode.com/problems/accounts-merge/) | Medium | 3/11 | D1 D3 D4 |  |
| 3 | 16 | [3Sum Closest](https://leetcode.com/problems/3sum-closest/) | Medium | 3/11 | D1 D2 D5 | ✅ `FAQ/src/ThreeSumClosest.java` |
| 4 | 67 | [Add Binary](https://leetcode.com/problems/add-binary/) | Easy | 3/11 | D1 D3 D6 |  |
| 5 | 224 | [Basic Calculator](https://leetcode.com/problems/basic-calculator/) | Hard | 3/11 | D2 D5 D6 | ✅ `Stack/src/BasicCalculator.java` |
| 6 | 567 | [Permutation in String](https://leetcode.com/problems/permutation-in-string/) | Medium | 3/11 | C1 C2 D3 |  |
| 7 | 27 | [Remove Element](https://leetcode.com/problems/remove-element/) | Easy | 3/11 | D1 D2 D3 | ✅ `ArraysAndStrings/src/RemoveElement.java` |
| 8 | 344 | [Reverse String](https://leetcode.com/problems/reverse-string/) | Easy | 3/11 | D1 D5 D6 |  |
| 9 | 35 | [Search Insert Position](https://leetcode.com/problems/search-insert-position/) | Easy | 3/11 | D1 D2 D3 |  |
| 10 | 540 | [Single Element in a Sorted Array](https://leetcode.com/problems/single-element-in-a-sorted-array/) | Medium | 3/11 | D1 D3 D4 |  |
| 11 | 410 | [Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum/) | Hard | 3/11 | C4 D1 D3 |  |
| 12 | 713 | [Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/) | Medium | 3/11 | D1 D2 D3 | ✅ `FAQ/src/SubarrayProductLessThanK.java` |
| 13 | 41 | [First Missing Positive](https://leetcode.com/problems/first-missing-positive/) | Hard | 3/11 | D2 D4 D5 |  |
| 14 | 232 | [Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/) | Easy | 3/11 | C2 C5 D6 |  |
| 15 | 528 | [Random Pick with Weight](https://leetcode.com/problems/random-pick-with-weight/) | Medium | 3/11 | D4 D5 D6 |  |
| 16 | 759 | [Employee Free Time](https://leetcode.com/problems/employee-free-time/) | Hard | 3/11 | D4 D5 D6 |  |
| 17 | 412 | [Fizz Buzz](https://leetcode.com/problems/fizz-buzz/) | Easy | 3/11 | D3 D5 D6 |  |
| 18 | 114 | [Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/) | Medium | 3/11 | C5 D4 D5 |  |
| 19 | 572 | [Subtree of Another Tree](https://leetcode.com/problems/subtree-of-another-tree/) | Easy | 3/11 | C1 C4 D6 |  |
| 20 | 338 | [Counting Bits](https://leetcode.com/problems/counting-bits/) | Easy | 3/11 | C1 C2 C4 |  |
| 21 | 450 | [Delete Node in a BST](https://leetcode.com/problems/delete-node-in-a-bst/) | Medium | 3/11 | C2 D5 D6 |  |
| 22 | 191 | [Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits/) | Easy | 3/11 | C1 C2 D6 |  |
| 23 | 332 | [Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/) | Hard | 3/11 | D2 D4 D6 |  |
| 24 | 692 | [Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words/) | Medium | 3/11 | C2 D4 D5 |  |
| 25 | 116 | [Populating Next Right Pointers in Each Node](https://leetcode.com/problems/populating-next-right-pointers-in-each-node/) | Medium | 3/11 | D4 D5 D6 |  |
| 26 | 218 | [The Skyline Problem](https://leetcode.com/problems/the-skyline-problem/) | Hard | 3/11 | D4 D5 D6 |  |
| 27 | 348 | [Design Tic-Tac-Toe](https://leetcode.com/problems/design-tic-tac-toe/) | Medium | 3/11 | D4 D5 D6 |  |
| 28 | 323 | [Number of Connected Components in an Undirected Graph](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/) | Medium | 3/11 | C1 C2 C5 |  |

</details>

## 4. Top 100, grouped by pattern

From one dataset only (liquidslr, last 6 months, 51 companies). See section 3 for the cross-checked list.

Rank = overall rank (1 = asked by the most companies). Cos = companies out of 51 that asked it in the last 6 months.

| Pattern | In repo |
|---|---|
| Arrays & Hashing | 15/20 |
| Two Pointers | 7/12 |
| Sliding Window | 2/6 |
| Stack | 1/7 |
| Binary Search | 1/8 |
| Linked List | 5/5 |
| Trees | 3/3 |
| Graphs (BFS / DFS / Union-Find / Topological Sort) | 0/7 |
| Heap | 4/4 |
| Intervals & Greedy | 4/5 |
| Backtracking | 1/5 |
| Dynamic Programming | 6/9 |
| Design (data structure design) | 1/3 |
| Strings & Math | 5/6 |

### Arrays & Hashing (15/20 in repo)

| Rank | Problem | Difficulty | Cos | Repo |
|---|---|---|---|---|
| 5 | [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | Easy | 10 | ✅ `ArraysAndStrings/src/MaxProfit.java` |
| 6 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Medium | 10 | ✅ `HashMap/src/GroupAnagrams.java` |
| 11 | [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) | Medium | 8 |  |
| 17 | [Two Sum](https://leetcode.com/problems/two-sum/) | Easy | 7 | ✅ `HashMap/src/TwoSum.java` |
| 24 | [Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/) | Medium | 7 | ✅ `ArraysAndStrings/src/MaxProfit2.java` |
| 41 | [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | Medium | 6 | ✅ `HashMap/src/LongestConsecutive.java` |
| 51 | [Rotate Array](https://leetcode.com/problems/rotate-array/) | Medium | 6 | ✅ `ArraysAndStrings/src/RotateArray.java`, `ArraysAndStrings/src/RotateArrayCyclic.java` |
| 53 | [Spiral Matrix](https://leetcode.com/problems/spiral-matrix/) | Medium | 6 | ✅ `ArraysAndStrings/src/SpiralMatrix.java` |
| 66 | [First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/) | Easy | 6 |  |
| 69 | [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) | Medium | 6 |  |
| 73 | [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) | Medium | 6 | ✅ `ArraysAndStrings/src/ValidSudoku.java` |
| 78 | [Maximum Product of Three Numbers](https://leetcode.com/problems/maximum-product-of-three-numbers/) | Easy | 6 |  |
| 83 | [Majority Element](https://leetcode.com/problems/majority-element/) | Easy | 5 | ✅ `ArraysAndStrings/src/MajorityElement.java` |
| 85 | [Valid Anagram](https://leetcode.com/problems/valid-anagram/) | Easy | 5 | ✅ `HashMap/src/ValidAnagram.java` |
| 86 | [Next Permutation](https://leetcode.com/problems/next-permutation/) | Medium | 5 | ✅ `ArraysAndStrings/src/NextPermutation.java` |
| 90 | [Concatenation of Array](https://leetcode.com/problems/concatenation-of-array/) | Easy | 5 |  |
| 92 | [Single Number](https://leetcode.com/problems/single-number/) | Easy | 5 | ✅ `Math/src/SingleNumber.java` |
| 94 | [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) | Easy | 5 | ✅ `HashMap/src/ContainsDuplicate.java` |
| 96 | [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) | Medium | 5 | ✅ `ArraysAndStrings/src/ProductExceptSelf.java` |
| 100 | [Rotate Image](https://leetcode.com/problems/rotate-image/) | Medium | 5 | ✅ `ArraysAndStrings/src/RotateImage.java` |

### Two Pointers (7/12 in repo)

| Rank | Problem | Difficulty | Cos | Repo |
|---|---|---|---|---|
| 10 | [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) | Medium | 8 | ✅ `TwoPointers/src/LongestPalindrome.java`, `FAQ/src/LongestPalindromicSubString.java` |
| 18 | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) | Hard | 7 | ✅ `ArraysAndStrings/src/RainWater.java` |
| 19 | [3Sum](https://leetcode.com/problems/3sum/) | Medium | 7 | ✅ `TwoPointers/src/ThreeSum.java`, `FAQ/src/ThreeSum.java` |
| 20 | [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) | Medium | 7 | ✅ `TwoPointers/src/MostWaterInContainer.java` |
| 29 | [String Compression](https://leetcode.com/problems/string-compression/) | Medium | 7 |  |
| 42 | [Move Zeroes](https://leetcode.com/problems/move-zeroes/) | Easy | 6 |  |
| 44 | [Valid Palindrome](https://leetcode.com/problems/valid-palindrome/) | Easy | 6 | ✅ `TwoPointers/src/ValidPalindrome.java` |
| 46 | [Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/) | Easy | 6 |  |
| 47 | [4Sum](https://leetcode.com/problems/4sum/) | Medium | 6 |  |
| 52 | [Sort Colors](https://leetcode.com/problems/sort-colors/) | Medium | 6 |  |
| 81 | [Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) | Easy | 5 | ✅ `ArraysAndStrings/src/RemoveDuplicates.java` |
| 88 | [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/) | Easy | 5 | ✅ `ArraysAndStrings/src/Merge.java` |

### Sliding Window (2/6 in repo)

| Rank | Problem | Difficulty | Cos | Repo |
|---|---|---|---|---|
| 2 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Medium | 12 | ✅ `SlidingWindow/src/LongestSubstring.java` |
| 7 | [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) | Hard | 9 |  |
| 23 | [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/) | Medium | 7 |  |
| 25 | [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/) | Medium | 7 |  |
| 30 | [Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/) | Medium | 7 |  |
| 87 | [Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/) | Medium | 5 | ✅ `FAQ/src/SubarrayProductLessThanK.java` |

### Stack (1/7 in repo)

| Rank | Problem | Difficulty | Cos | Repo |
|---|---|---|---|---|
| 8 | [Asteroid Collision](https://leetcode.com/problems/asteroid-collision/) | Medium | 9 |  |
| 9 | [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) | Easy | 8 | ✅ `Stack/src/ValidParentheses.java` |
| 35 | [Valid Parenthesis String](https://leetcode.com/problems/valid-parenthesis-string/) | Medium | 7 |  |
| 48 | [Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/) | Hard | 6 |  |
| 50 | [Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) | Easy | 6 |  |
| 58 | [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) | Medium | 6 |  |
| 89 | [Decode String](https://leetcode.com/problems/decode-string/) | Medium | 5 |  |

### Binary Search (1/8 in repo)

| Rank | Problem | Difficulty | Cos | Repo |
|---|---|---|---|---|
| 27 | [Find Peak Element](https://leetcode.com/problems/find-peak-element/) | Medium | 7 |  |
| 36 | [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) | Hard | 6 |  |
| 38 | [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/) | Medium | 6 | ✅ `FAQ/src/SearchInRotatedDistinctArray.java` |
| 56 | [Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | Medium | 6 |  |
| 71 | [Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/) | Medium | 6 |  |
| 91 | [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/) | Medium | 5 |  |
| 95 | [Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum/) | Hard | 5 |  |
| 98 | [Search Insert Position](https://leetcode.com/problems/search-insert-position/) | Easy | 5 |  |

### Linked List (5/5 in repo)

| Rank | Problem | Difficulty | Cos | Repo |
|---|---|---|---|---|
| 16 | [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) | Hard | 8 | ✅ `LinkedList/src/MergeKSortedLists.java` |
| 40 | [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) | Easy | 6 | ✅ `LinkedList/src/MergeTwoLinkedList.java` |
| 57 | [Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) | Easy | 6 | ✅ `LinkedList/src/ReverseList.java` |
| 62 | [Reorder List](https://leetcode.com/problems/reorder-list/) | Medium | 6 | ✅ `LinkedList/src/ReorderList.java` |
| 79 | [Add Two Numbers](https://leetcode.com/problems/add-two-numbers/) | Medium | 5 | ✅ `LinkedList/src/AddTwoNumbers.java` |

### Trees (3/3 in repo)

| Rank | Problem | Difficulty | Cos | Repo |
|---|---|---|---|---|
| 64 | [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) | Easy | 6 | ✅ `Trees/src/MaximumDepthOfBinaryTree.java` |
| 65 | [Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/) | Hard | 6 | ✅ `Trees/src/TreeMaxPathSum.java` |
| 77 | [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/) | Hard | 6 | ✅ `Trees/src/SerializeDeserializeTree.java` |

### Graphs (BFS / DFS / Union-Find / Topological Sort) (0/7 in repo)

| Rank | Problem | Difficulty | Cos | Repo |
|---|---|---|---|---|
| 3 | [Number of Islands](https://leetcode.com/problems/number-of-islands/) | Medium | 12 |  |
| 14 | [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) | Medium | 8 |  |
| 15 | [Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) | Medium | 8 |  |
| 32 | [Course Schedule](https://leetcode.com/problems/course-schedule/) | Medium | 7 |  |
| 33 | [Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/) | Medium | 7 |  |
| 60 | [Make Lexicographically Smallest Array by Swapping Elements](https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/) | Medium | 6 |  |
| 61 | [Accounts Merge](https://leetcode.com/problems/accounts-merge/) | Medium | 6 |  |

### Heap (4/4 in repo)

| Rank | Problem | Difficulty | Cos | Repo |
|---|---|---|---|---|
| 12 | [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Medium | 8 | ✅ `Heap/src/TopKFrequentElements.java` |
| 13 | [Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) | Hard | 8 | ✅ `Heap/src/MedianFromDataStream.java` |
| 49 | [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) | Medium | 6 | ✅ `Heap/src/KthLargestElement.java` |
| 68 | [Task Scheduler](https://leetcode.com/problems/task-scheduler/) | Medium | 6 | ✅ `Heap/src/TaskScheduler.java` |

### Intervals & Greedy (4/5 in repo)

| Rank | Problem | Difficulty | Cos | Repo |
|---|---|---|---|---|
| 1 | [Merge Intervals](https://leetcode.com/problems/merge-intervals/) | Medium | 16 | ✅ `FAQ/src/MergeIntervals.java`, `Intervals/src/MergeIntervals.java` |
| 26 | [Jump Game II](https://leetcode.com/problems/jump-game-ii/) | Medium | 7 | ✅ `ArraysAndStrings/src/JumpGame2.java` |
| 28 | [Gas Station](https://leetcode.com/problems/gas-station/) | Medium | 7 | ✅ `ArraysAndStrings/src/GasStation.java` |
| 54 | [Jump Game](https://leetcode.com/problems/jump-game/) | Medium | 6 | ✅ `ArraysAndStrings/src/JumpGame.java` |
| 82 | [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) | Medium | 5 |  |

### Backtracking (1/5 in repo)

| Rank | Problem | Difficulty | Cos | Repo |
|---|---|---|---|---|
| 22 | [Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) | Medium | 7 | ✅ `Backtracking/src/LetterCombinations.java` |
| 67 | [Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/) | Medium | 6 |  |
| 70 | [Word Search](https://leetcode.com/problems/word-search/) | Medium | 6 |  |
| 93 | [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) | Medium | 5 |  |
| 97 | [Subsets](https://leetcode.com/problems/subsets/) | Medium | 5 |  |

### Dynamic Programming (6/9 in repo)

| Rank | Problem | Difficulty | Cos | Repo |
|---|---|---|---|---|
| 21 | [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) | Medium | 7 | ✅ `DynamicProgramming/src/MaximumSubarray.java` |
| 34 | [Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/) | Medium | 7 |  |
| 39 | [Climbing Stairs](https://leetcode.com/problems/climbing-stairs/) | Easy | 6 | ✅ `DynamicProgramming/src/ClimbingStairs.java` |
| 43 | [House Robber](https://leetcode.com/problems/house-robber/) | Medium | 6 | ✅ `DynamicProgramming/src/HouseRobber.java` |
| 55 | [Distinct Subsequences](https://leetcode.com/problems/distinct-subsequences/) | Hard | 6 |  |
| 59 | [Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/) | Medium | 6 | ✅ `DynamicProgramming/src/LongestIncreasingSubsequence.java` |
| 74 | [Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/) | Medium | 6 | ✅ `DynamicProgramming/src/MinimumPathSum.java` |
| 75 | [Word Break](https://leetcode.com/problems/word-break/) | Medium | 6 | ✅ `DynamicProgramming/src/WordBreak.java` |
| 76 | [Predict the Winner](https://leetcode.com/problems/predict-the-winner/) | Medium | 6 |  |

### Design (data structure design) (1/3 in repo)

| Rank | Problem | Difficulty | Cos | Repo |
|---|---|---|---|---|
| 4 | [LRU Cache](https://leetcode.com/problems/lru-cache/) | Medium | 11 |  |
| 31 | [Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/) | Medium | 7 | ✅ `ArraysAndStrings/src/RandomizedSet.java` |
| 63 | [Time Based Key-Value Store](https://leetcode.com/problems/time-based-key-value-store/) | Medium | 6 |  |

### Strings & Math (5/6 in repo)

| Rank | Problem | Difficulty | Cos | Repo |
|---|---|---|---|---|
| 37 | [Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/) | Easy | 6 | ✅ `ArraysAndStrings/src/LongestPrefixString.java` |
| 45 | [Zigzag Conversion](https://leetcode.com/problems/zigzag-conversion/) | Medium | 6 | ✅ `ArraysAndStrings/src/ZigzagConversion.java` |
| 72 | [String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi/) | Medium | 6 | ✅ `ArraysAndStrings/src/StringToInteger.java` |
| 80 | [Palindrome Number](https://leetcode.com/problems/palindrome-number/) | Easy | 5 | ✅ `Math/src/Pallindrome.java` |
| 84 | [Reverse Integer](https://leetcode.com/problems/reverse-integer/) | Medium | 5 |  |
| 99 | [Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/) | Easy | 5 | ✅ `ArraysAndStrings/src/FirstOccurrenceOfString.java` |

## 5. Most asked by company segment

### Big Tech (23 companies, last 6 months)

| # | Problem | Companies | Repo |
|---|---|---|---|
| 1 | [Merge Intervals](https://leetcode.com/problems/merge-intervals/) | 11/23 | ✅ |
| 2 | [Number of Islands](https://leetcode.com/problems/number-of-islands/) | 10/23 |  |
| 3 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | 9/23 | ✅ |
| 4 | [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | 8/23 | ✅ |
| 5 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | 8/23 | ✅ |
| 6 | [LRU Cache](https://leetcode.com/problems/lru-cache/) | 8/23 |  |
| 7 | [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) | 8/23 | ✅ |
| 8 | [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) | 8/23 | ✅ |
| 9 | [Two Sum](https://leetcode.com/problems/two-sum/) | 7/23 | ✅ |
| 10 | [3Sum](https://leetcode.com/problems/3sum/) | 7/23 | ✅ |
| 11 | [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) | 7/23 | ✅ |
| 12 | [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) | 7/23 |  |
| 13 | [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) | 7/23 |  |
| 14 | [Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) | 7/23 | ✅ |
| 15 | [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | 7/23 | ✅ |

### Banks & fintech (11 companies, last 6 months)

| # | Problem | Companies | Repo |
|---|---|---|---|
| 1 | [Merge Intervals](https://leetcode.com/problems/merge-intervals/) | 5/11 | ✅ |
| 2 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | 3/11 | ✅ |
| 3 | [Asteroid Collision](https://leetcode.com/problems/asteroid-collision/) | 2/11 |  |
| 4 | [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/) | 2/11 |  |
| 5 | [LRU Cache](https://leetcode.com/problems/lru-cache/) | 2/11 |  |
| 6 | [Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/) | 2/11 |  |
| 7 | [Number of Islands](https://leetcode.com/problems/number-of-islands/) | 2/11 |  |
| 8 | [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) | 2/11 | ✅ |
| 9 | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) | 1/11 | ✅ |
| 10 | [Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/) | 1/11 | ✅ |
| 11 | [Remove Boxes](https://leetcode.com/problems/remove-boxes/) | 1/11 |  |
| 12 | [Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/) | 1/11 | ✅ |
| 13 | [Beautiful Arrangement](https://leetcode.com/problems/beautiful-arrangement/) | 1/11 |  |
| 14 | [Exclusive Time of Functions](https://leetcode.com/problems/exclusive-time-of-functions/) | 1/11 |  |
| 15 | [Strange Printer](https://leetcode.com/problems/strange-printer/) | 1/11 |  |

### India product companies (17 companies, all time)

Their last-6-months lists are tiny (0–9 problems each), so this uses the all-time lists.

| # | Problem | Companies | Asked at | Repo |
|---|---|---|---|---|
| 1 | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) | 8/17 | Flipkart, Media.net, Navi, Paytm, PhonePe, Sprinklr, Swiggy, Zeta | ✅ |
| 2 | [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | 7/17 | Dream11, Media.net, Myntra, Paytm, Swiggy, Zeta, Zomato | ✅ |
| 3 | [LRU Cache](https://leetcode.com/problems/lru-cache/) | 7/17 | Flipkart, Paytm, PhonePe, Sprinklr, Swiggy, Zomato, razorpay |  |
| 4 | [Merge Intervals](https://leetcode.com/problems/merge-intervals/) | 7/17 | Flipkart, Juspay, Sprinklr, Swiggy, Zeta, Zomato, razorpay | ✅ |
| 5 | [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | 6/17 | Flipkart, Groww, Media.net, Myntra, Paytm, Swiggy | ✅ |
| 6 | [Asteroid Collision](https://leetcode.com/problems/asteroid-collision/) | 6/17 | Dream11, Flipkart, Juspay, Meesho, PhonePe, Sprinklr |  |
| 7 | [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) | 6/17 | Media.net, Meesho, PhonePe, Swiggy, Zeta, Zomato | ✅ |
| 8 | [Gas Station](https://leetcode.com/problems/gas-station/) | 5/17 | Dream11, Flipkart, Juspay, PhonePe, Sprinklr | ✅ |
| 9 | [3Sum](https://leetcode.com/problems/3sum/) | 5/17 | Dream11, Flipkart, Meesho, Myntra, PhonePe | ✅ |
| 10 | [Number of Islands](https://leetcode.com/problems/number-of-islands/) | 5/17 | Flipkart, Juspay, Meesho, PhonePe, Zomato |  |
| 11 | [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) | 4/17 | Dream11, Flipkart, Juspay, Myntra | ✅ |
| 12 | [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | 4/17 | Myntra, Paytm, PhonePe, Swiggy | ✅ |
| 13 | [Capacity To Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/) | 4/17 | Flipkart, InMobi, Myntra, Zeta |  |
| 14 | [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) | 4/17 | Juspay, PhonePe, Sprinklr, Zeta |  |
| 15 | [First Missing Positive](https://leetcode.com/problems/first-missing-positive/) | 4/17 | Myntra, PhonePe, Swiggy, Zomato |  |
| 16 | [Maximum Points You Can Obtain from Cards](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/) | 3/17 | Flipkart, PhonePe, Sprinklr |  |
| 17 | [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) | 3/17 | Dream11, Flipkart, Myntra |  |
| 18 | [Maximum Profit in Job Scheduling](https://leetcode.com/problems/maximum-profit-in-job-scheduling/) | 3/17 | Meesho, PhonePe, Swiggy |  |
| 19 | [Cherry Pickup](https://leetcode.com/problems/cherry-pickup/) | 3/17 | Flipkart, InMobi, PhonePe |  |
| 20 | [Course Schedule](https://leetcode.com/problems/course-schedule/) | 3/17 | Flipkart, Swiggy, Zomato |  |
| 21 | [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) | 3/17 | Meesho, Swiggy, Zeta | ✅ |
| 22 | [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) | 3/17 | Flipkart, PhonePe, Swiggy | ✅ |
| 23 | [Edit Distance](https://leetcode.com/problems/edit-distance/) | 3/17 | Flipkart, Sprinklr, Swiggy |  |
| 24 | [Russian Doll Envelopes](https://leetcode.com/problems/russian-doll-envelopes/) | 3/17 | Flipkart, Media.net, Zomato |  |
| 25 | [Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum/) | 3/17 | Flipkart, PhonePe, Zeta |  |
| 26 | [Jump Game](https://leetcode.com/problems/jump-game/) | 3/17 | Flipkart, Navi, PhonePe | ✅ |
| 27 | [Jump Game II](https://leetcode.com/problems/jump-game-ii/) | 3/17 | Flipkart, Meesho, PhonePe | ✅ |
| 28 | [Maximal Square](https://leetcode.com/problems/maximal-square/) | 3/17 | Flipkart, PhonePe, Zomato |  |
| 29 | [Spiral Matrix](https://leetcode.com/problems/spiral-matrix/) | 3/17 | Flipkart, Paytm, PhonePe | ✅ |
| 30 | [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) | 3/17 | Flipkart, Sprinklr, Zeta |  |

## 6. Difficulty and topics

Weighted by how many target companies asked each problem (top 300 problems).

| Difficulty | Share |
|---|---|
| Easy | 29% |
| Medium | 58% |
| Hard | 13% |

| Topic | Weight |
|---|---|
| Array | 859 |
| String | 388 |
| Hash Table | 349 |
| Dynamic Programming | 283 |
| Two Pointers | 228 |
| Sorting | 224 |
| Math | 210 |
| Binary Search | 180 |
| Stack | 156 |
| Depth-First Search | 152 |
| Breadth-First Search | 131 |
| Greedy | 117 |
| Sliding Window | 115 |
| Linked List | 106 |
| Prefix Sum | 104 |
| Tree | 102 |
| Binary Tree | 102 |
| Matrix | 92 |
| Heap (Priority Queue) | 87 |
| Design | 78 |

## 7. YouTube playlist: DSA In 90 Days (CTO Bhaiya)

- Playlist: [DSA In 90 Days For Placements! Beginner, Medium & Advanced Level Playlist](https://www.youtube.com/playlist?list=PLVItHqpXY_DArKRcfmGWykqV3u4hDaJLo)
- 337 videos, about 87.5 hours, 170 distinct LeetCode problems. About 60 videos are intros, theory, templates or channel updates.
- 63 of the 170 problems are in this repo.

| Videos | Section | ✅ In repo | Missing |
|---|---|---|---|
| 3–16 | Array | 26, 121, 122, 189, 217, 136, 349, 350, 66, 1, 36, 48 | 283 |
| 17–47 | Arrays (Theory + Code) | 88, 27, 26 | 485, 1295, 977, 1089, 1346, 941, 1299, 283, 905, 1051, 487, 414, 448 |
| 48–70, 79–90 | Array And String | 66, 167, 189, 209, 151, 14, 28, 118, 54 | 724, 747, 561, 119, 67, 498, 344, 186, 557 |
| 71–78 | String | 242, 125, 28, 14, 8 | 344, 7, 387 |
| 91–97 | Linked List | 19, 21, 141, 206, 234 | 237 |
| 98–130 | Linked List (Theory + Code) | 141, 142, 19, 21, 2, 61, 138, 160, 206, 234 | 707, 203, 328, 708, 430 |
| 131–152 | Hash Table | 217, 136, 202, 205, 350, 219, 49, 380, 3, 347, 36 | 705, 706, 599, 387, 359, 249, 771, 454, 170, 288, 652 |
| 153–165 | Binary Search | 69, 33 | 704, 34, 374, 278, 153, 162, 658 |
| 168–188 | Sorting | none | 75, 1051, 147, 1200, 164, 2343 |
| 189–195 | Math | 13 | 412, 326, 204, 1232, 2481, 836 |
| 196–202 | Miscellaneous | 268, 118 | 191, 461, 190 |
| 203–204 | Design | none | 384 |
| 206–224 | Stack | 155, 20, 150, 42 | 739, 496, 503, 84, 85, 232, 394, 907, 735, 2104, 402, 901 |
| 225–230 | Queue | none | 622, 346, 200 |
| 232–251 | Heap | 215, 347, 295 | 703, 1046, 1337, 378, 973, 1642 |
| 253–267 | Binary Tree | 102, 104, 101 | 144, 94, 145, 112 |
| 268–282 | Binary Search Tree | 98, 235 | 700, 701, 450, 285, 173, 703, 110, 108, 220 |
| 285–322 | Recursion | 24, 50, 21, 77, 17, 46, 70, 98, 206 | 344, 700, 509, 119, 779, 240, 51, 52, 37, 100, 22 |
| 325–335 | DP | 198 | 1137, 746, 740, 1770 |

Some problems appear in more than one section, so the Missing column has repeats.

<details>
<summary>All 337 videos, with links</summary>

| # | LeetCode | Video | Length | In repo |
|---|---|---|---|---|
| 1 |  | [DSA in 90 Days for Placements (Full Course)! 🔥 Beginner, Medium & Advanced Level! #dsa #leetcode](https://www.youtube.com/watch?v=hw2nv3jIgZs) | 4:07 |  |
| 2 |  | [🚀 DSA in 90 Days for Placements 2025 / Course Outline / Crack Coding Interviews! 💻](https://www.youtube.com/watch?v=fmxRItl_CfY) | 5:18 |  |
| 3 | 26 | [Array-1 / 26. Remove Duplicates from Sorted Array / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=rlqssRvFSZs) | 7:10 | ✅ ArraysAndStrings/src/RemoveDuplicates.java |
| 4 | 121 | [Array-2 / 121. Best Time to Buy and Sell Stock / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=2G4VmL-OVS8) | 9:04 | ✅ ArraysAndStrings/src/MaxProfit.java |
| 5 | 122 | [Array-3 / 122. Best Time to Buy and Sell Stock II / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=T8ezJHsXXow) | 11:32 | ✅ ArraysAndStrings/src/MaxProfit2.java |
| 6 | 189 | [Array-4 / 189. Rotate Array / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=pyZFIcdz4uk) | 31:50 | ✅ ArraysAndStrings/src/RotateArray.java, ArraysAndStrings/src/RotateArrayCyclic.java |
| 7 | 217 | [Array-5 / 217. Contains Duplicate / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=Mb0YGs0blhg) | 6:15 | ✅ HashMap/src/ContainsDuplicate.java |
| 8 | 136 | [Array-6 / 136. Single Number / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=RYnrtrCFmKM) | 8:14 | ✅ Math/src/SingleNumber.java |
| 9 | 349 | [Array-7 / 349. Intersection of Two Arrays / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=nDKnfQVKReE) | 12:13 | ✅ HashMap/src/IntersectionOfTwoArrays.java |
| 10 | 350 | [Array-8 / 350. Intersection of Two Arrays II / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=uZUvzFpLp1I) | 23:47 | ✅ HashMap/src/IntersectionOfTwoArrays2.java, HashMap/src/IntersectionOfTwoArrays2Sorting.java |
| 11 | 66 | [Array-9 / 66. Plus One / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=4fG-PBorGOY) | 7:30 | ✅ Math/src/PlusOne.java |
| 12 | 283 | [Array-10 / 283. Move Zeroes / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=wwzGaII21rU) | 11:37 |  |
| 13 | 1 | [Array-11 / 1. Two Sum / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=XliE4F0h4wM) | 17:58 | ✅ HashMap/src/TwoSum.java |
| 14 | 36 | [Array-12 / 36. Valid Sudoku / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=Y-zTQZLhQsc) | 19:54 | ✅ ArraysAndStrings/src/ValidSudoku.java |
| 15 | 48 | [Array-13 / 48. Rotate Image / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=Th-4e85_cUg) | 17:23 | ✅ ArraysAndStrings/src/RotateImage.java |
| 16 | 48 | [Array-14 / 48. Rotate Image (Optimised) / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=FuNOB5NldIk) | 32:08 | ✅ ArraysAndStrings/src/RotateImage.java |
| 17 | 485 | [485.1 Max Consecutive Ones (Theory) / #1 / Arrays Playlist / #leetcode #array #dsa #interview](https://www.youtube.com/watch?v=4UbULlkevGs) | 5:24 |  |
| 18 | 485 | [485.2 Max Consecutive Ones (Code) / #1 / Arrays Playlist / #leetcode #array #dsa #interview](https://www.youtube.com/watch?v=2hzERLjR2_k) | 2:06 |  |
| 19 | 1295 | [1295.1 Find Number with Even Number of Digits(Theory) / #2 / Arrays Playlist / #leetcode #array](https://www.youtube.com/watch?v=c3XuLlIQ_fw) | 13:20 |  |
| 20 | 1295 | [1295.2 Find Number with Even Number of Digits (Code) / #2 / Arrays Playlist / #leetcode #array #dsa](https://www.youtube.com/watch?v=fGowSrWq5QI) | 7:44 |  |
| 21 | 977 | [977.1 Squares of a Sorted array (Theory) / #3 / Arrays Playlist / #leetcode #array #dsa #interview](https://www.youtube.com/watch?v=fV1q5N2YZX8) | 7:19 |  |
| 22 | 977 | [977.2 Squares of a Sorted array (Code)  / #3 / Arrays Playlist / #leetcode #array #dsa #interview](https://www.youtube.com/watch?v=6b0EqlyOhLo) | 4:16 |  |
| 23 | 1089 | [1089.1 Duplicate Zeros (Theory)  / #4 / Arrays Playlist / #leetcode #array #dsa #interview](https://www.youtube.com/watch?v=fs5Idcn-8b0) | 12:42 |  |
| 24 | 1089 | [1089.2 Duplicate Zeros (Code)  / #4 / Arrays Playlist /  #leetcode #array #dsa #interview](https://www.youtube.com/watch?v=AAkM5vnn7sc) | 9:51 |  |
| 25 | 88 | [88.1 Merge Sorted Array (Theory) /  #5 / Arrays Playlist /  #leetcode #array #dsa #interview](https://www.youtube.com/watch?v=AYkQki2k8wo) | 9:08 | ✅ ArraysAndStrings/src/Merge.java |
| 26 | 88 | [88.2 Merge Sorted Array (Code) / #5 / Arrays Playlist / #leetcode #array #dsa #interview](https://www.youtube.com/watch?v=weRnkpaFs2c) | 9:28 | ✅ ArraysAndStrings/src/Merge.java |
| 27 | 27 | [27.1 Remove Element (Theory) / #6 / Arrays Playlist / #dsa #leetcode #interview](https://www.youtube.com/watch?v=tSKtYe3k8Wc) | 11:05 | ✅ ArraysAndStrings/src/RemoveElement.java |
| 28 | 27 | [27.2 Remove Element (Code) / #6 / Arrays Playlist / #dsa #leetcode #interview](https://www.youtube.com/watch?v=VmhUitccX5Q) | 4:03 | ✅ ArraysAndStrings/src/RemoveElement.java |
| 29 | 26 | [26.1 Remove Duplicates from Sorted Array (Theory) / #7 / Arrays Playlist / #dsa #leetcode #interview](https://www.youtube.com/watch?v=2HD63hMo_-o) | 9:59 | ✅ ArraysAndStrings/src/RemoveDuplicates.java |
| 30 | 26 | [26.2 Remove Duplicates from Sorted Array (Code) / #7 / Arrays Playlist / #dsa #leetcode #interview](https://www.youtube.com/watch?v=Tz8hyu5ox9Q) | 3:08 | ✅ ArraysAndStrings/src/RemoveDuplicates.java |
| 31 | 1346 | [1346.1 Check If N and Its Double Exist (Theory) / #8 / Arrays Playlist / #dsa #leetcode #interview](https://www.youtube.com/watch?v=4zxhdAptEYA) | 5:17 |  |
| 32 | 1346 | [1346.2 Check If N and Its Double Exist (Code) / #8 / Arrays Playlist / #dsa #leetcode #interview](https://www.youtube.com/watch?v=7ABlUiXfRU8) | 2:11 |  |
| 33 | 941 | [941.1 Valid Mountain Array (Theory) / #9 / Arrays Playlist / Leetcode](https://www.youtube.com/watch?v=JdxqWqsZtPw) | 8:33 |  |
| 34 | 941 | [941.2 Valid Mountain Array (Code) / #9 / Arrays Playlist / Leetcode](https://www.youtube.com/watch?v=0pXZo21U0k8) | 3:36 |  |
| 35 | 1299 | [1299.1 Replace Elements with Greatest Element on Right Side(Theory)/#10 / Arrays Playlist / Leetcode](https://www.youtube.com/watch?v=4ATd6WWjoEk) | 7:07 |  |
| 36 | 1299 | [1299.2 Replace Elements with Greatest Element on Right Side(Code) / #10 / Arrays Playlist / Leetcode](https://www.youtube.com/watch?v=9LenWMiTfKo) | 3:11 |  |
| 37 | 283 | [283.1 Move Zeroes (Theory) / #11 / Arrays Playlist / Leetcode](https://www.youtube.com/watch?v=Re8iwp3czYs) | 11:32 |  |
| 38 | 283 | [283.2 Move Zeroes (Code) / #11 / Arrays Playlist / Leetcode](https://www.youtube.com/watch?v=xy8IV2FdeeU) | 6:35 |  |
| 39 | 905 | [905.1 Sort Array By Parity (Theory) / #12 / Arrays Playlist / Leetcode](https://www.youtube.com/watch?v=_DSyHqvqRFw) | 13:20 |  |
| 40 | 905 | [905.2 Sort Array By Parity (Code) / #12 / Arrays Playlist / Leetcode](https://www.youtube.com/watch?v=ZwzvV356TF4) | 10:35 |  |
| 41 | 1051 | [1051. Height Checker (Theory + Code) / #13 / Arrays Playlist / Leetcode](https://www.youtube.com/watch?v=iGFGlr-DjXA) | 7:14 |  |
| 42 | 487 | [487.1 Max Consecutive Ones II (Theory) / #14 / Arrays Playlist / Leetcode](https://www.youtube.com/watch?v=GeagslAOfYo) | 15:59 |  |
| 43 | 487 | [487.2 Max Consecutive Ones II (Code) / #14 / Arrays Playlist / Leetcode](https://www.youtube.com/watch?v=07OCTjtdV_Y) | 6:53 |  |
| 44 | 414 | [414.1 Third Maximum Number (Theory) / #15 / Arrays Playlist / Leetcode](https://www.youtube.com/watch?v=jnYHI8-ErgE) | 14:20 |  |
| 45 | 414 | [414.2 Third Maximum Number (Code) / #15 / Arrays Playlist / Leetcode](https://www.youtube.com/watch?v=-aCL4CnjMCU) | 9:58 |  |
| 46 | 448 | [448.1 Find all numbers disappeared in an array (Theory) / #16 / Arrays Playlist / Leetcode](https://www.youtube.com/watch?v=coWcRLBrWG4) | 10:16 |  |
| 47 | 448 | [448.2 Find all numbers disappeared in an array (Code) / #16 / Arrays Playlist / Leetcode](https://www.youtube.com/watch?v=RQXvurLWbAg) | 7:14 |  |
| 48 | 724 | [724.1 Find Pivot Index (Theory) / #1 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=IV3AkceP37A) | 16:58 |  |
| 49 | 724 | [724.2 Find Pivot Index (Code) / #1 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=59gnJCkDzEc) | 3:54 |  |
| 50 | 747 | [747.1 Largest Number At Least Twice Of Others (Theory) / #2 / Array And String Playlist / #leetcode](https://www.youtube.com/watch?v=QNpEthxmwxU) | 8:30 |  |
| 51 | 747 | [747.2 Largest Number At Least Twice Of Others (Code) / #2 / Array And String Playlist / #leetcode](https://www.youtube.com/watch?v=X0e0uexDXus) | 5:11 |  |
| 52 | 66 | [66.1 Plus One (Theory) / #3 / Array And String Playlist / #leetcode #dsa #array #strings](https://www.youtube.com/watch?v=jW9uyXfwURI) | 8:42 | ✅ Math/src/PlusOne.java |
| 53 | 66 | [66.2 Plus One (Code) / #3 / Array And String Playlist / #leetcode #dsa #array #strings](https://www.youtube.com/watch?v=DEEK4OsmEFA) | 5:00 | ✅ Math/src/PlusOne.java |
| 54 | 118 | [118.1 Pascal's Triangle (Theory) / #4 / Array And String Playlist / #leetcode #dsa #array #strings](https://www.youtube.com/watch?v=p6l9kw5Pv1I) | 13:33 | ✅ ArraysAndStrings/src/PascalsTriangle.java |
| 55 | 118 | [118.2 Pascal's Triangle (Code) / #4 / Array And String Playlist / #leetcode #dsa #array #strings](https://www.youtube.com/watch?v=ELo7RR-Isjk) | 8:32 | ✅ ArraysAndStrings/src/PascalsTriangle.java |
| 56 | 167 | [167.1 Two Sum II - Input Array Is Sorted (Theory) / #6 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=lpq33MWHUa4) | 9:26 | ✅ TwoPointers/src/TwoSum.java |
| 57 | 167 | [167.2 Two Sum II - Input Array Is Sorted (Code) / #6 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=fcfdJ6S3LUo) | 4:24 | ✅ TwoPointers/src/TwoSum.java |
| 58 | 189 | [189.1 Rotate Array (Theory) / #10 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=fkyCDdhlU3M) | 23:06 | ✅ ArraysAndStrings/src/RotateArray.java, ArraysAndStrings/src/RotateArrayCyclic.java |
| 59 | 189 | [189.2 Rotate Array (Code) / #10 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=C3BCuwVqZnw) | 18:02 | ✅ ArraysAndStrings/src/RotateArray.java, ArraysAndStrings/src/RotateArrayCyclic.java |
| 60 | 209 | [209.1 Minimum Size Subarray Sum (Theory) / #11 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=QkuOoadFw8U) | 17:46 | ✅ SlidingWindow/src/MinimumSizeSubArray.java |
| 61 | 209 | [209.2 Minimum Size Subarray Sum (Code) / #11 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=cFmfMYimq7U) | 12:01 | ✅ SlidingWindow/src/MinimumSizeSubArray.java |
| 62 | 561 | [561.1 Array Partition (Theory) / #12 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=sZkKQaayd8o) | 18:03 |  |
| 63 | 561 | [561.2 Array Partition (Code) / #12 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=3kEE2CQFJrI) | 13:59 |  |
| 64 | 119 | [119.1 Pascal's Triangle II (Theory) / #13 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=ct8tnQmLc7M) | 21:27 |  |
| 65 | 119 | [119.2 Pascal's Triangle II (Code) / #13 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=s47sl0cyV18) | 14:37 |  |
| 66 | 54 | [54.1 Spiral Matrix (Theory) / #14 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=5qB39bRkh8I) | 16:22 | ✅ ArraysAndStrings/src/SpiralMatrix.java |
| 67 | 54 | [54.2 Spiral Matrix (Code) / #14 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=5YDg1VGa1Us) | 7:59 | ✅ ArraysAndStrings/src/SpiralMatrix.java |
| 68 | 67 | [67.1 Add Binary (Theory) / #15 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=a_-_KUeTzZI) | 21:18 |  |
| 69 | 67 | [67.2 Add Binary (Code) / #15 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=79Pv8tFyj3c) | 19:22 |  |
| 70 | 498 | [498. Diagonal Traverse / #18 / Array And String Playlist](https://www.youtube.com/watch?v=jQ_-E1AwGrw) | 49:48 |  |
| 71 | 344 | [String-1 / 344. Reverse String / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=IzAi-hsLVKE) | 4:45 |  |
| 72 | 7 | [String-2 / 7. Reverse Integer / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=cV0yZcs8wCM) | 17:13 |  |
| 73 | 387 | [String-3 / 387. First Unique Character in a String / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=KSjBIiTlsko) | 6:28 |  |
| 74 | 242 | [String-4 / 242. Valid Anagram / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=L03Bua5Uiv8) | 13:44 | ✅ HashMap/src/ValidAnagram.java |
| 75 | 125 | [String-5 / 125. Valid Palindrome / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=o7DPQX5t21k) | 13:07 | ✅ TwoPointers/src/ValidPalindrome.java |
| 76 | 8 | [String-6 / 8. String to Integer (atoi) / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=c67bciCuQGQ) | 25:31 | ✅ ArraysAndStrings/src/StringToInteger.java |
| 77 | 28 | [String-7 / 28. Find the Index of the First Occurrence in a String /🔥Beginner,Medium & Advanced Level](https://www.youtube.com/watch?v=1_TBZ1ErdIU) | 11:48 | ✅ ArraysAndStrings/src/FirstOccurrenceOfString.java |
| 78 | 14 | [String-8 / 14. Longest Common Prefix / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=Cst7rk0gcTg) | 20:06 | ✅ ArraysAndStrings/src/LongestPrefixString.java |
| 79 | 344 | [344.1 Reverse String (Theory) / #5 / Array And String Playlist / #leetcode #dsa #array #strings](https://www.youtube.com/watch?v=SfE6EOTy3As) | 4:50 |  |
| 80 | 344 | [344.2 Reverse String (Code) / #5 / Array And String Playlist / #leetcode #dsa #array #strings](https://www.youtube.com/watch?v=0uJjOlNOrQ0) | 3:23 |  |
| 81 | 151 | [151.1 Reverse Words in a String (Theory) / #7 / Array And String Playlist / #leetcode #dsa #strings](https://www.youtube.com/watch?v=4joybmtYoag) | 16:54 | ✅ ArraysAndStrings/src/ReverseWordsInString.java |
| 82 | 151 | [151.2  Reverse Words in a String (Code) / #7 / Array And String Playlist / #leetcode #dsa #strings](https://www.youtube.com/watch?v=eqYHdbCPFL0) | 17:03 | ✅ ArraysAndStrings/src/ReverseWordsInString.java |
| 83 | 186 | [186.1  Reverse Words in a String II (Theory) / #8 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=KHQSIUOAtrA) | 6:50 |  |
| 84 | 186 | [186.2  Reverse Words in a String II (Code) / #8 / Array And String Playlist / #leetcode #dsa #string](https://www.youtube.com/watch?v=ySuyIUcWYuw) | 6:34 |  |
| 85 | 557 | [557.1  Reverse Words in a String III (Theory) / #9 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=4W6LEvcTx8s) | 6:20 |  |
| 86 | 557 | [557.2  Reverse Words in a String III (Code) / #9 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=TxJXoEwtIFo) | 4:56 |  |
| 87 | 14 | [14.1 Longest Common Prefix (Theory) / #16 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=ZEERwx80wKM) | 12:53 | ✅ ArraysAndStrings/src/LongestPrefixString.java |
| 88 | 14 | [14.2 Longest Common Prefix (Code) / #16 / Array And String Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=mIuIu1A1Ki0) | 12:55 | ✅ ArraysAndStrings/src/LongestPrefixString.java |
| 89 | 28 | [28.1 Find the Index of the First Occurrence in a String (Theory) / #17 / Array And String Playlist](https://www.youtube.com/watch?v=Y6SUYp-49Pg) | 8:49 | ✅ ArraysAndStrings/src/FirstOccurrenceOfString.java |
| 90 | 28 | [28.2 Find the Index of the First Occurrence in a String (Code) / #17 / Array And String Playlist](https://www.youtube.com/watch?v=dgpGAwhwbwU) | 7:09 | ✅ ArraysAndStrings/src/FirstOccurrenceOfString.java |
| 91 | 237 | [Linked List-1 / 237. Delete Node in a Linked List / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=FjUAwp4NgwY) | 6:07 |  |
| 92 | 19 | [Linked List-2 / 19. Remove Nth Node From End of List / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=pNA5tpTZUMo) | 17:39 | ✅ LinkedList/src/RemoveNthFromEnd.java |
| 93 | 206 | [Linked List-3 / 206. Reverse Linked List / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=CERsn72qJdM) | 7:56 | ✅ LinkedList/src/ReverseList.java |
| 94 | 21 | [Linked List-4 / 21. Merge Two Sorted Lists / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=WebKaAgoUUw) | 16:39 | ✅ LinkedList/src/MergeTwoLinkedList.java |
| 95 | 234 | [Linked List-5 / 234. Palindrome Linked List / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=nkSwIS4SFbQ) | 27:43 | ✅ LinkedList/src/PalindromeLinkedList.java |
| 96 | 141 | [Linked List-6 / 141. Linked List Cycle / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=zc4VGYgUang) | 19:18 | ✅ FAQ/src/LinkedListCycleDetector.java, LinkedList/src/LinkedListCycle.java |
| 97 |  | [Linked List-7 / Tips & Tricks / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=uwSdPzyqnzM) | 2:51 |  |
| 98 |  | [0. Linked List Introduction / #0 / Linked List Playlist / #dsa #leetcode #interview](https://www.youtube.com/watch?v=tbrVeBu0_NY) | 10:28 |  |
| 99 | 707 | [707.1 Design Linked List (Theory) / #1 / Linked List Playlist / #leetcode #linkedlist  #interview](https://www.youtube.com/watch?v=ZWtxxHEuCdU) | 20:47 |  |
| 100 | 707 | [707.2 Design Linked List (Code) / #1 / Linked List Playlist / #leetcode #linkedlist  #interview](https://www.youtube.com/watch?v=MLeTJR81O4E) | 10:34 |  |
| 101 | 141 | [141.1 Linked List Cycle (Theory) / #2 / Linked List Playlist / #leetcode #linkedlist  #interview](https://www.youtube.com/watch?v=_4v7LcNdV3c) | 13:29 | ✅ FAQ/src/LinkedListCycleDetector.java, LinkedList/src/LinkedListCycle.java |
| 102 | 141 | [141.2 Linked List Cycle (Code) / #2 / Linked List Playlist / #leetcode #linkedlist  #interview](https://www.youtube.com/watch?v=p-R9H1Rovr4) | 5:54 | ✅ FAQ/src/LinkedListCycleDetector.java, LinkedList/src/LinkedListCycle.java |
| 103 | 142 | [142.1 Linked List Cycle II (Theory) / #3 / Linked List Playlist / #leetcode #linkedlist  #interview](https://www.youtube.com/watch?v=dGKTMrVarWU) | 14:54 | ✅ FAQ/src/StartOfCycle.java |
| 104 | 142 | [142.2 Linked List Cycle II (Code) / #3 / Linked List Playlist / #leetcode #linkedlist  #interview](https://www.youtube.com/watch?v=AvR27PAVNbE) | 6:20 | ✅ FAQ/src/StartOfCycle.java |
| 105 | 160 | [160.1 Intersection of Two Linked Lists (Theory) / #4 / Linked List Playlist / #leetcode #linkedlist](https://www.youtube.com/watch?v=mbsZp5p4geQ) | 18:08 | ✅ LinkedList/src/IntersectionOfTwoLinkedLists.java |
| 106 | 160 | [160.2 Intersection of Two Linked Lists (Code) / #4 / Linked List Playlist / #leetcode #linkedlist](https://www.youtube.com/watch?v=t2_Jl0gsTJ8) | 14:36 | ✅ LinkedList/src/IntersectionOfTwoLinkedLists.java |
| 107 | 19 | [19.1 Remove Nth Node From End of List  (Theory) / #5 / Linked List Playlist / #leetcode #linkedlist](https://www.youtube.com/watch?v=NFlOHaHMDyY) | 11:32 | ✅ LinkedList/src/RemoveNthFromEnd.java |
| 108 | 19 | [19.2 Remove Nth Node From End of List  (Code) / #5 / Linked List Playlist / #leetcode #linkedlist](https://www.youtube.com/watch?v=7UjMYFmwia0) | 9:31 | ✅ LinkedList/src/RemoveNthFromEnd.java |
| 109 | 206 | [206.1 Reverse Linked List (Theory) / #6 / Linked List Playlist / #leetcode #linkedlist #dsa](https://www.youtube.com/watch?v=aYVBELkN1_E) | 7:56 | ✅ LinkedList/src/ReverseList.java |
| 110 | 206 | [206.2 Reverse Linked List (Code) / #6 / Linked List Playlist / #leetcode #linkedlist #dsa](https://www.youtube.com/watch?v=91dRxmwkrVs) | 3:05 | ✅ LinkedList/src/ReverseList.java |
| 111 | 203 | [203.1 Remove Linked List Elements (Theory) / #7 / Linked List Playlist / #leetcode #linkedlist #dsa](https://www.youtube.com/watch?v=zhXbxVlvrwY) | 11:09 |  |
| 112 | 203 | [203.2 Remove Linked List Elements (Code) / #7 / Linked List Playlist / #leetcode #linkedlist #dsa](https://www.youtube.com/watch?v=dDVGcQaDbBk) | 3:53 |  |
| 113 | 328 | [328.1 Odd Even Linked List (Theory) / #8 / Linked List Playlist / #leetcode #linkedlist #dsa](https://www.youtube.com/watch?v=xA54G94KzXk) | 10:11 |  |
| 114 | 328 | [328.2 Odd Even Linked List (Code) / #8 / Linked List Playlist / #leetcode #linkedlist #dsa](https://www.youtube.com/watch?v=dH_c2zvgCpM) | 5:24 |  |
| 115 | 234 | [234.1 Palindrome Linked List (Theory) / #9 / Linked List Playlist / #leetcode #linkedlist #dsa](https://www.youtube.com/watch?v=H67DEuigPMg) | 18:37 | ✅ LinkedList/src/PalindromeLinkedList.java |
| 116 | 234 | [234.2 Palindrome Linked List (Code) / #9 / Linked List Playlist / #leetcode #linkedlist #dsa](https://www.youtube.com/watch?v=meSGPFFCvys) | 15:53 | ✅ LinkedList/src/PalindromeLinkedList.java |
| 117 | 21 | [21.1 Merge Two Sorted Lists (Theory) / #10 / Linked List Playlist / #leetcode #linkedlist #dsa](https://www.youtube.com/watch?v=oCa17xTnB6Y) | 8:45 | ✅ LinkedList/src/MergeTwoLinkedList.java |
| 118 | 21 | [21.2 Merge Two Sorted Lists (Code) / #10 / Linked List Playlist / #leetcode #linkedlist #dsa](https://www.youtube.com/watch?v=12IMetiucE4) | 5:11 | ✅ LinkedList/src/MergeTwoLinkedList.java |
| 119 | 2 | [2.1 Add Two Numbers (Theory) / #11 / Linked List Playlist / #leetcode #linkedlist  #dsa #interview](https://www.youtube.com/watch?v=z475sv2V99k) | 14:48 | ✅ LinkedList/src/AddTwoNumbers.java |
| 120 | 2 | [2.2 Add Two Numbers (Code) / #11 / Linked List Playlist / #leetcode #linkedlist  #dsa #interview](https://www.youtube.com/watch?v=uZaq7H45mKc) | 7:41 | ✅ LinkedList/src/AddTwoNumbers.java |
| 121 | 708 | [708.1  Insert into a Sorted Circular Linked List (Theory) / #12 / Linked List Playlist / #leetcode](https://www.youtube.com/watch?v=wCb6m6xjyiw) | 14:04 |  |
| 122 | 708 | [708.2 Insert into a Sorted Circular Linked List (Code) / #12 / Linked List Playlist / #leetcode](https://www.youtube.com/watch?v=AmMebOBAO6Q) | 9:47 |  |
| 123 | 61 | [61.1 Rotate List (Theory) / #13 / Linked List Playlist / #leetcode #dsa #interview](https://www.youtube.com/watch?v=QqK6QyXkSBA) | 12:40 | ✅ LinkedList/src/RotateList.java |
| 124 | 61 | [61.2 Rotate List (Code) / #13 / Linked List Playlist / #leetcode #dsa #interview](https://www.youtube.com/watch?v=iRF0ICCxxm8) | 8:10 | ✅ LinkedList/src/RotateList.java |
| 125 | 138 | [138.1 Copy List with Random Pointer (Theory) / #14 / Linked List Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=gsjz5p1iV1s) | 27:35 | ✅ LinkedList/src/CopyListWithRandomPointer.java |
| 126 | 138 | [138.2 Copy List with Random Pointer (Code) / #14 / Linked List Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=5QCWVBKc5wM) | 22:15 | ✅ LinkedList/src/CopyListWithRandomPointer.java |
| 127 | 707 | [707.1 Design Doubly Linked List (Theory) / #15 / Linked List Playlist / #leetcode #dsa #interview](https://www.youtube.com/watch?v=KwBCZyh0jgs) | 22:03 |  |
| 128 | 707 | [707.2 Design Doubly Linked List (Code) / #15 / Linked List Playlist / #leetcode #dsa #interview](https://www.youtube.com/watch?v=2s9MyvuLs_I) | 25:24 |  |
| 129 | 430 | [430.1 Flatten a Multilevel Doubly Linked List (Theory) / #16 / Linked List Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=3yQ-aWV0wUE) | 22:19 |  |
| 130 | 430 | [430.2 Flatten a Multilevel Doubly Linked List (Code) / #16 / Linked List Playlist / #leetcode #dsa](https://www.youtube.com/watch?v=A1r16TQgYvQ) | 9:22 |  |
| 131 | 705 | [[MUST WATCH] 705. Design HashSet / #1 / Hash Table Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=NfTyAM6T7to) | 25:57 |  |
| 132 | 706 | [[MUST WATCH] 706. Design HashMap / #2 / Hash Table Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=Q2dzzbFqKmo) | 20:54 |  |
| 133 | 217 | [217. Contains Duplicate / #3 / Hash Table Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=5rs1YxOtqSA) | 9:16 | ✅ HashMap/src/ContainsDuplicate.java |
| 134 | 136 | [136. Single Number / #4 / Hash Table Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=hURay76Zie0) | 12:00 | ✅ Math/src/SingleNumber.java |
| 135 | 202 | [202. Happy Number / #7 / Hash Table Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=yx1swAFoxXY) | 22:58 | ✅ TwoPointers/src/IsHappy.java |
| 136 | 205 | [205. Isomorphic Strings / #8 / Hash Table Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=1R58ZyIH0EQ) | 25:59 | ✅ HashMap/src/IsomorphicStrings.java |
| 137 | 599 | [599. Minimum Index Sum of Two Lists / #9 / Hash Table Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=OU0voEih10g) | 15:27 |  |
| 138 | 387 | [387. First Unique Character in a String / #10 / Hash Table Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=khgf6B281tE) | 7:00 |  |
| 139 | 350 | [350. Intersection of Two Arrays II / #11 / Hash Table Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=mfHj38p4Kto) | 20:26 | ✅ HashMap/src/IntersectionOfTwoArrays2.java, HashMap/src/IntersectionOfTwoArrays2Sorting.java |
| 140 | 219 | [219. Contains Duplicate II / #12 / Hash Table Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=O2HRsifRfnc) | 9:55 | ✅ SlidingWindow/src/ContainsDuplicate.java |
| 141 | 359 | [359. Logger Rate Limiter / #13 / Hash Table Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=orr37fHDNnw) | 9:49 |  |
| 142 | 49 | [49. Group Anagrams / #14 / Hash Table Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=TrydIFZOl2Q) | 17:06 | ✅ HashMap/src/GroupAnagrams.java |
| 143 | 249 | [249. Group Shifted Strings / #15 / Hash Table Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=Xl9ysqP-yu4) | 27:14 |  |
| 144 | 36 | [36. Valid Sudoku / #16 / Hash Table Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=j1Cs6rF6wYs) | 19:21 | ✅ ArraysAndStrings/src/ValidSudoku.java |
| 145 | 771 | [771. Jewels and Stones / #17 / Hash Table Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=N3XlTweXqmc) | 8:29 |  |
| 146 | 454 | [454. 4Sum II / #18 / Hash Table Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=GWYPi8cuc5w) | 16:51 |  |
| 147 | 170 | [170. Two Sum III - Data structure design / #19 / HashTable Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=3uWGK3PtA_E) | 36:20 |  |
| 148 | 347 | [347. Top K Frequent Elements / #20 / HashTable Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=OY4_PcEvZvY) | 36:15 | ✅ Heap/src/TopKFrequentElements.java |
| 149 | 288 | [288. Unique Word Abbreviation / #21 / HashTable Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=chhSAtdwN9o) | 25:57 |  |
| 150 | 380 | [380. Insert Delete GetRandom O(1) / #22 / HashTable Playlist #hashtable #leetcode #algorithms](https://www.youtube.com/watch?v=DhWkpltokYM) | 33:46 | ✅ ArraysAndStrings/src/RandomizedSet.java |
| 151 | 3 | [3. Longest Substring Without Repeating Characters / #23 / HashTable Playlist #hashtable #leetcode](https://www.youtube.com/watch?v=Rs9jrYjVQEk) | 31:36 | ✅ SlidingWindow/src/LongestSubstring.java |
| 152 | 652 | [652. Find Duplicate Subtrees / #24 / HashTable Playlist #hashtable #leetcode](https://www.youtube.com/watch?v=JHh6-B7VwsM) | 27:44 |  |
| 153 |  | [Binary Search-1 / Theory / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=bDwl6BMTQqc) | 9:32 |  |
| 154 | 704 | [Binary Search-2 / 704. Binary Search / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=vo92sw_qN_Q) | 6:53 |  |
| 155 |  | [Binary Search-3 / Binary Search Templates / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=QSjevkLnnlM) | 9:35 |  |
| 156 | 34 | [Binary Search-4 / 34. Find First and Last Position of Element in Sorted Array / 🔥](https://www.youtube.com/watch?v=Z1UNjp7Ygcc) | 17:03 |  |
| 157 |  | [Binary Search-5 / Template - 1 / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=Ukl00P7p2xw) | 5:59 |  |
| 158 | 69 | [Binary Search-6 / 69. Sqrt(x) / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=YzFhzl7oLIU) | 13:16 | ✅ Math/src/SqrRtx.java |
| 159 | 374 | [Binary Search-7 / 374. Guess Number Higher or Lower / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=p-IJhovR5yo) | 9:08 |  |
| 160 | 33 | [Binary Search-8 / 33. Search in Rotated Sorted Array / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=AsH-GczBLI8) | 13:46 | ✅ FAQ/src/SearchInRotatedDistinctArray.java |
| 161 |  | [Binary Search-9 / Template-2 / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=RPR1g5d3rKU) | 7:15 |  |
| 162 | 278 | [Binary Search-10 / 278. First Bad Version / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=wM4760cNKCY) | 13:00 |  |
| 163 | 153 | [Binary Search-11 / 153. Find Minimum in Rotated Sorted Array / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=Eprw6nNAdTc) | 13:10 |  |
| 164 | 162 | [Binary Search-12 / 162. Find Peak Element / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=sjRwKlbZaII) | 26:14 |  |
| 165 | 658 | [Binary Search-13 / 658. Find K Closest Elements / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=IJ4d3FJC-n8) | 28:34 |  |
| 166 |  | [🚀 COMPLETE DSA in 90 Days for Placements 2025 / Course Outline-2 💻 #dsa](https://www.youtube.com/watch?v=4QICEX1cP9Q) | 5:15 |  |
| 167 |  | [🚀 Top 10 LeetCode Tips & Resources to Ace Coding Interviews! 🔥](https://www.youtube.com/watch?v=xxb4HSKwTBo) | 23:41 |  |
| 168 |  | [Sorting-1 / Theory / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=xk7O7jflrMo) | 13:40 |  |
| 169 |  | [Sorting-2 / Comparison-Based Sort / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=KRahIjlkQgI) | 6:48 |  |
| 170 |  | [Sorting-3 / Selection Sort / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=_GI9CEhFTU4) | 3:10 |  |
| 171 | 75 | [Sorting-4 / 75. Sort Colors (Selection Sort) / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=v7mhlVhqa6A) | 7:18 |  |
| 172 |  | [Sorting-5 / Bubble Sort / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=Aeemd_60Cx8) | 3:51 |  |
| 173 | 1051 | [Sorting-6 / 1051. Height Checker (Bubble Sort) / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=c23qI5VfnII) | 12:50 |  |
| 174 |  | [Sorting-7 / Insertion Sort / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=d1KFOuiXiXc) | 5:19 |  |
| 175 | 1051 | [Sorting-8 / 1051. Height Checker (Insertion Sort) / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=DzHpWW0zX9U) | 8:41 |  |
| 176 | 147 | [Sorting-9 / 147. Insertion Sort List / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=NtuCyP4FBe4) | 18:52 |  |
| 177 |  | [Sorting-10 / Count Sort (Theory) / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=X2Vp-ITVE9E) | 14:57 |  |
| 178 |  | [Sorting-11 / Count Sort (Template-1) / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=psiOZVhIDCo) | 15:47 |  |
| 179 |  | [Sorting-12 / Count Sort (Template-2) / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=EFo-cdp0gtU) | 12:54 |  |
| 180 | 75 | [Sorting-13 / 75. Sort Colors (Count Sort) / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=_K4XjuEiceI) | 12:20 |  |
| 181 | 1200 | [Sorting-14 / 1200. Minimum Absolute Difference (Sorting) / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=IsLHaZ8fHFo) | 16:24 |  |
| 182 | 1200 | [Sorting-15 / 1200. Minimum Absolute Difference (Count Sort) / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=I6JK2xU9Zw8) | 29:16 |  |
| 183 |  | [Sorting-16 / Radix Sort (Theory) / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=-3W5ziLqFLE) | 15:49 |  |
| 184 |  | [Sorting-17 / Radix Sort (Code) / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=4B3biju_ToI) | 24:16 |  |
| 185 | 164 | [Sorting-18 / 164. Maximum Gap / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=wzc6r2dU_Ps) | 11:36 |  |
| 186 | 2343 | [Sorting-19 / 2343. Query Kth Smallest Trimmed Number (Brute) / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=y_NednXRakE) | 18:39 |  |
| 187 |  | [Sorting-20 / Bucket Sort (Theory + Code) / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=tfSq31uPYTM) | 25:53 |  |
| 188 | 2343 | [[MUST WATCH] Sorting-21 / 2343. Query Kth Smallest Trimmed Number (Optimised)](https://www.youtube.com/watch?v=0DWfRnI3U7E) | 46:21 |  |
| 189 | 412 | [Math-1 / 412. Fizz Buzz / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=6HMuhKLyKg0) | 12:21 |  |
| 190 | 326 | [Math-2 / 326. Power of Three / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=rLu8Y5tDh4k) | 21:13 |  |
| 191 | 204 | [Math-3 / 204. Count Primes / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=6N64R84mkT0) | 20:48 |  |
| 192 | 13 | [Math-4 / 13. Roman to Integer / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=zlNWhCREYNI) | 11:25 | ✅ ArraysAndStrings/src/RomanToInt.java |
| 193 | 1232 | [Math-5 / 1232. Check If It Is a Straight Line / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=ThUMV3bapJw) | 13:12 |  |
| 194 | 2481 | [Math-6 / 2481. Minimum Cuts to Divide a Circle / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=fmIGyG1npJE) | 4:40 |  |
| 195 | 836 | [Math-7 / 836. Rectangle Overlap / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=2YG7r3SCEgk) | 16:08 |  |
| 196 | 191 | [Miscellaneous-1 / 191. Number of 1 Bits / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=-KTxJe99QGs) | 10:32 |  |
| 197 | 461 | [Miscellaneous-2 / 461. Hamming Distance / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=2lJ2np6uBgk) | 7:56 |  |
| 198 | 118 | [Miscellaneous-3 / 118. Pascal's Triangle / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=aIxprAqsDgw) | 12:50 | ✅ ArraysAndStrings/src/PascalsTriangle.java |
| 199 | 268 | [Miscellaneous-4 / 268. Missing Number / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=TUlZ3LrZ9uo) | 11:34 | ✅ FAQ/src/MissingNumberFinder.java |
| 200 | 190 | [Miscellaneous-5 / 190. Reverse Bits (Bit By Bit) / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=G6I_vJGqg9E) | 9:37 |  |
| 201 |  | [Miscellaneous-6 / 2's Complement / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=erZ31b72_F8) | 9:11 |  |
| 202 | 190 | [Miscellaneous-7 / 190. Reverse Bits(Mask & Shift) / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=pajTf6F7JVw) | 27:54 |  |
| 203 | 384 | [Design-1 / 384. Shuffle an Array / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=f6x7CZR72vA) | 23:51 |  |
| 204 |  | [Design-2 / Fisher Yates Algorithm / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=nIa2JbDd4Ug) | 12:18 |  |
| 205 |  | [100 FREE DSA Videos (Phase 1 Complete!) / Beginner’s DSA Roadmap for LeetCode & Interviews](https://www.youtube.com/watch?v=hTMet25xaEA) | 1:28 |  |
| 206 |  | [Stack - 1 / Introduction to Stacks](https://www.youtube.com/watch?v=-hZJcd949C4) | 14:00 |  |
| 207 | 155 | [Stack - 2 / 155. Min Stack / 3 Approaches](https://www.youtube.com/watch?v=K-_nLrXJ3AI) | 29:46 | ✅ Stack/src/StackUsingLinkedList.java, Stack/src/MinStack.java |
| 208 | 20 | [Stack - 3 / 20. Valid Parentheses](https://www.youtube.com/watch?v=l-B2npNOgYE) | 14:37 | ✅ Stack/src/ValidParentheses.java |
| 209 | 739 | [Stack - 4 / 739. Daily Temperatures / 3 Approaches](https://www.youtube.com/watch?v=CltPTmP2Jkk) | 35:58 |  |
| 210 | 150 | [Stack - 5 / 150. Evaluate Reverse Polish Notation](https://www.youtube.com/watch?v=K9Lyu_ouiu4) | 22:22 | ✅ Stack/src/ReversePolishNotation.java |
| 211 | 496 | [Stack - 6 / 496. Next Greater Element I](https://www.youtube.com/watch?v=SuPZNbomaEA) | 14:31 |  |
| 212 | 503 | [Stack - 7 / 503. Next Greater Element II](https://www.youtube.com/watch?v=7UsWIdWZZqQ) | 20:19 |  |
| 213 |  | [Stack - 8 / 1 Template to solve Many Problems](https://www.youtube.com/watch?v=3GsG3RgwYuc) | 21:08 |  |
| 214 |  | [Stack - 9 / What are Monotonic Stacks?](https://www.youtube.com/watch?v=p7oXBIChwrs) | 10:18 |  |
| 215 | 42 | [Stack - 10 / 42. Trapping Rain Water - 4 Approaches / Brute Force, DP, Stack & Two Pointers ⛈️](https://www.youtube.com/watch?v=ll1G1SQXNXo) | 1:06:42 | ✅ ArraysAndStrings/src/RainWater.java |
| 216 | 84 | [Stack - 11 / 84. Largest Rectangle in Histogram - 2 Approaches / Brute Force, Stack](https://www.youtube.com/watch?v=RrJ7wMRsBWI) | 27:00 |  |
| 217 | 85 | [Stack - 12 / 85. Maximal Rectangle](https://www.youtube.com/watch?v=G9tTFh86Z7Q) | 20:45 |  |
| 218 | 232 | [Stack - 13 / 232. Implement Queue using Stacks / Design Problem](https://www.youtube.com/watch?v=rLCDwzHXP-Q) | 18:50 |  |
| 219 | 394 | [Stack - 14 / 394. Decode String](https://www.youtube.com/watch?v=ds8tHekx7E0) | 37:39 |  |
| 220 | 907 | [Stack - 15 / 907. Sum of Subarray Minimums / 2 Approaches](https://www.youtube.com/watch?v=zYIMKyJk-HM) | 41:23 |  |
| 221 | 735 | [Stack - 16 / 735. Asteroid Collision](https://www.youtube.com/watch?v=O_yfzGXl3Y0) | 25:00 |  |
| 222 | 2104 | [Stack - 17 / 2104. Sum of Subarray Ranges](https://www.youtube.com/watch?v=tR6qP4TiFTk) | 23:53 |  |
| 223 | 402 | [Stack - 18 / 402. Remove K Digits](https://www.youtube.com/watch?v=1-qoF5nJLDw) | 28:24 |  |
| 224 | 901 | [Stack - 19 / 901. Online Stock Span](https://www.youtube.com/watch?v=U702JLORTVc) | 18:44 |  |
| 225 |  | [0. Introduction to Queues / #0 / Queue & Stack Playlist #hashtable #leetcode](https://www.youtube.com/watch?v=6Q-VOnii7FI) | 14:50 |  |
| 226 | 622 | [[MUST WATCH] 622. Design Circular Queue  / #1 / Queue & Stack Playlist #hashtable #leetcode](https://www.youtube.com/watch?v=CrZzJOyjQFE) | 36:39 |  |
| 227 |  | [Queue Methods For Interviews  / #2 / Queue & Stack Playlist #queue #stack #leetcode](https://www.youtube.com/watch?v=CClHsoo5CrQ) | 11:00 |  |
| 228 |  | [BFS Algorithm + 2 Templates / #4 / #queue #stack](https://www.youtube.com/watch?v=mgfK0uQ3_tk) | 23:32 |  |
| 229 | 346 | [346. Moving Average from Data Stream  / #3 / Queue & Stack Playlist #hashtable #leetcode](https://www.youtube.com/watch?v=ceKQOqTvqqQ) | 14:22 |  |
| 230 | 200 | [200. Number of Islands  / #5 / Queue & Stack Playlist #leetcode](https://www.youtube.com/watch?v=48pDEAwyoiQ) | 32:20 |  |
| 231 |  | [✅ Complete Stack Playlist 🚀 / 20 Popular Interview Problems to Master Stacks in DSA / Java / DSA](https://www.youtube.com/watch?v=vdMwsuEYnHA) | 2:59 |  |
| 232 |  | [Heap-1 / Introduction / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=b6Gq6V8Tvpg) | 9:05 |  |
| 233 |  | [Heap-2 / Types of Heap / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=Qy6feHiKh2Y) | 5:45 |  |
| 234 |  | [Heap-3 / Heap Intuition / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=o6S8Cg2ehN0) | 8:26 |  |
| 235 |  | [Heap-4 / Heapify, Insert, Delete (Theory) / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=OD2pv4VU9qs) | 18:41 |  |
| 236 |  | [Heap-5 / Heapify, Insert, Delete (Code) / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=QYdAXDg9M30) | 36:05 |  |
| 237 |  | [Heap-6 / Time & Space Complexity / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=fM7MjvQO0lo) | 9:29 |  |
| 238 |  | [Heap-7 / Heaps In Java / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=dPtqVlqW-lA) | 14:53 |  |
| 239 |  | [Heap-8 / Heap Sort / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=0QGoUaYMNHY) | 29:19 |  |
| 240 |  | [Heap-9 / The Top-K Problem (Theory) / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=nZXGStW0b-I) | 23:33 |  |
| 241 |  | [Heap-10 / The K-th Element (Theory) / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=ATiBFQWyPqQ) | 11:59 |  |
| 242 | 215 | [Heap-11 / 215. Kth Largest Element in an Array / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=nQQGT53HgbE) | 13:45 | ✅ Heap/src/KthLargestElement.java |
| 243 | 347 | [Heap-12 / 347. Top K Frequent Elements / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=E5aUnVMdj10) | 18:18 | ✅ Heap/src/TopKFrequentElements.java |
| 244 | 703 | [Heap-13 / 703. Kth Largest Element in a Stream / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=9jrTMj6DxMw) | 14:14 |  |
| 245 | 1046 | [Heap-14 / 1046. Last Stone Weight / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=zARkzNx9gHA) | 15:47 |  |
| 246 |  | [Heap-15 / Custom Sorting for Heaps and Lists / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=_41BeE8UNbM) | 18:13 |  |
| 247 | 1337 | [Heap-16 / 1337. The K Weakest Rows in a Matrix / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=OUI5Lcs6Y7o) | 35:39 |  |
| 248 | 378 | [Heap-17 / 378. Kth Smallest Element in a Sorted Matrix / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=InqHjb2kaNE) | 26:15 |  |
| 249 | 973 | [Heap-18 / 973. K Closest Points to Origin / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=5OyPhsOKS6c) | 15:03 |  |
| 250 | 295 | [Heap-19 / 295. Find Median from Data Stream / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=YmfWZFUbEsI) | 36:07 | ✅ Heap/src/MedianFromDataStream.java |
| 251 | 1642 | [Heap-20 / 1642. Furthest Building You Can Reach / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=yn24b0HDkDg) | 29:54 |  |
| 252 |  | [🚀 Master Heaps in DSA — 20+ Lectures from Scratch + LeetCode Practice](https://www.youtube.com/watch?v=hifJr04rbyM) | 7:22 |  |
| 253 |  | [Binary Tree-1 / Theory / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=KejwzBy5hkg) | 10:48 |  |
| 254 | 144 | [Binary Tree-2 / 144. Binary Tree Preorder Traversal / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=-K8hwyHidSg) | 27:59 |  |
| 255 | 94 | [Binary Tree-3 / 94. Binary Tree Inorder Traversal / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=zatMy62PlK0) | 26:45 |  |
| 256 | 102 | [Binary Tree-4 / 102. Binary Tree Level Order Traversal / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=nK1uE8eJnTs) | 13:17 | ✅ Trees/src/LevelOrderTraversal.java |
| 257 |  | [Binary Tree-5 / Top Down Recursion / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=POqFMgBLBJY) | 17:55 |  |
| 258 |  | [Binary Tree-6 / Bottom Up Approach / 🔥 Beginner, Medium & Advanced Level! #easy](https://www.youtube.com/watch?v=QW0rO5CH33s) | 12:00 |  |
| 259 | 144 | [Binary Tree-7 / 144. Binary Tree Preorder Traversal (Recursive) / 🔥 Beginner Medium, Advanced Level!](https://www.youtube.com/watch?v=h8cwmSQoRms) | 11:30 |  |
| 260 | 94 | [Binary Tree-8 / 94. Binary Tree Inorder Traversal (Recursive) / 🔥 Beginner, Medium, Advanced Level!](https://www.youtube.com/watch?v=w-UKr-3wEx4) | 10:19 |  |
| 261 | 145 | [Binary Tree-9 / 145. Binary Tree Postorder Traversal (Recursive) / 🔥 Beginner Medium Advanced Level!](https://www.youtube.com/watch?v=HU2xPg5lbs8) | 10:00 |  |
| 262 | 104 | [Binary Tree-10 / 104. Maximum Depth of Binary Tree (Top Down) / 🔥 Beginner, Medium & Advanced Level!](https://www.youtube.com/watch?v=YuYb__JupBE) | 14:27 | ✅ Trees/src/MaximumDepthOfBinaryTree.java |
| 263 | 104 | [Binary Tree-11 / 104. Maximum Depth of Binary Tree (Bottom Up) / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=_-gzD2gCj6M) | 12:26 | ✅ Trees/src/MaximumDepthOfBinaryTree.java |
| 264 | 112 | [Binary Tree-12 / 112. Path Sum (Recursive) / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=2PmdWpXv3Y4) | 19:45 |  |
| 265 | 112 | [Binary Tree-13 / 112. Path Sum (Iterative) / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=Mn4_IMDJGMY) | 18:04 |  |
| 266 | 101 | [Binary Tree-14 / 101. Symmetric Tree (Recursive) / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=a02KSDfLdMY) | 19:36 | ✅ Trees/src/SymmetricTree.java |
| 267 | 101 | [Binary Tree-15 / 101. Symmetric Tree (Iterative) / 🔥 Beginner, Medium & Advanced Level #dsa](https://www.youtube.com/watch?v=PcH6O5b4Cgw) | 12:58 | ✅ Trees/src/SymmetricTree.java |
| 268 |  | [Binary Search Tree - 1 / Introduction / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=1lMmEPJBvsI) | 9:12 |  |
| 269 | 700 | [Binary Search Tree - 2 / 700. Search in a Binary Search Tree / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=2udPES1G1KM) | 10:13 |  |
| 270 | 701 | [Binary Search Tree - 3 / 701. Insert into a Binary Search Tree / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=rMe1ArwYRqA) | 19:23 |  |
| 271 | 450 | [Binary Search Tree - 4 / 450. Delete Node in a BST / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=lwX4JDLSQ1M) | 38:42 |  |
| 272 | 98 | [Binary Search Tree - 5 / 98. Validate Binary Search Tree / 🔥 Beginner, Medium & Advanced Level](https://www.youtube.com/watch?v=145Teiix5Ug) | 31:57 | ✅ Trees/src/ValidateBinarySearchTree.java |
| 273 | 285 | [Binary Search Tree - 6 / 285. Inorder Successor in BST (Recursive)](https://www.youtube.com/watch?v=mpqHAtFe4dQ) | 27:06 |  |
| 274 | 285 | [Binary Search Tree - 7 / 285. Inorder Successor in BST (Iterative)](https://www.youtube.com/watch?v=Y0eXQH77Tfw) | 17:17 |  |
| 275 | 173 | [Binary Search Tree - 8 / 173. Binary Search Tree Iterator (Recursive)](https://www.youtube.com/watch?v=wcBLMpzLH0A) | 16:04 |  |
| 276 | 173 | [Binary Search Tree - 9 / 173. Binary Search Tree Iterator (Iterative)](https://www.youtube.com/watch?v=0OBo07wkCNg) | 22:46 |  |
| 277 | 703 | [Binary Search Tree - 10 / 703. Kth Largest Element in a Stream (BST Approach)](https://www.youtube.com/watch?v=94DbUctQqJY) | 33:12 |  |
| 278 | 235 | [Binary Search Tree - 11 / 235. Lowest Common Ancestor of a Binary Search Tree](https://www.youtube.com/watch?v=cfbtJ_0HN7k) | 10:27 | ✅ Trees/src/LowestCommonAncestorBST.java |
| 279 |  | [Binary Search Tree - 12 / Height-Balanced BST (Theory)](https://www.youtube.com/watch?v=5ZHHKVgBN3Q) | 13:43 |  |
| 280 | 110 | [Binary Search Tree - 13 / 110. Balanced Binary Tree](https://www.youtube.com/watch?v=rhcR-iKJ0QA) | 17:18 |  |
| 281 | 108 | [Binary Search Tree - 14 / 108. Convert Sorted Array to Binary Search Tree](https://www.youtube.com/watch?v=DS5GQQzxwJY) | 17:58 |  |
| 282 | 220 | [[MUST WATCH] Binary Search Tree - 15 / 220. Contains Duplicate III](https://www.youtube.com/watch?v=1uaCklu54NQ) | 25:44 |  |
| 283 |  | [🚀 Complete Binary Search Tree Playlist / Master All BST Problems for Interviews (Java + LeetCode)](https://www.youtube.com/watch?v=CjPGto3XAJk) | 4:56 |  |
| 284 |  | [DSA in 90 Days for Placements (Full Course)! 150 DSA Lectures Completed! 🧠🔥6000+ People Joined! #dsa](https://www.youtube.com/watch?v=DtOWtpCvu9w) | 5:18 |  |
| 285 |  | [Recursion-1 / Introduction](https://www.youtube.com/watch?v=p37ycIwH32Q) | 13:35 |  |
| 286 | 344 | [Recursion-2 / 344. Reverse String](https://www.youtube.com/watch?v=4bn14L0NCeY) | 13:37 |  |
| 287 |  | [Recursion-3 / 1 Recursion Template to solve all problems](https://www.youtube.com/watch?v=N6DRtWQUin8) | 17:25 |  |
| 288 | 24 | [Recursion-4 / 24. Swap Nodes in Pairs](https://www.youtube.com/watch?v=Nb-lNhA5mg8) | 27:50 | ✅ FAQ/src/SwapPairs.java |
| 289 |  | [Recursion-5 / Recurrence Relation (Theory)](https://www.youtube.com/watch?v=-7aG0pppkyE) | 8:58 |  |
| 290 | 206 | [Recursion-6 / 206. Reverse Linked List](https://www.youtube.com/watch?v=8UVn6LgD2po) | 19:56 | ✅ LinkedList/src/ReverseList.java |
| 291 | 700 | [Recursion-7 / 700. Search in a Binary Search Tree](https://www.youtube.com/watch?v=wEgYvLYlNJw) | 13:29 |  |
| 292 |  | [Recursion-8 / Memoization + Template](https://www.youtube.com/watch?v=IFFW_A2GhZs) | 11:42 |  |
| 293 | 509 | [Recursion-9 / 509. Fibonacci Number](https://www.youtube.com/watch?v=fRmIbPACioI) | 9:12 |  |
| 294 | 70 | [Recursion-10 / 70. Climbing Stairs - Recursion + Memoization](https://www.youtube.com/watch?v=718n6F2IuWA) | 17:35 | ✅ DynamicProgramming/src/ClimbingStairs.java |
| 295 | 119 | [Recursion-11 / 119. Pascal's Triangle II - Recursion + Memoization](https://www.youtube.com/watch?v=SmL9tYZujFQ) | 18:05 |  |
| 296 |  | [Recursion-12 / Time Complexity (Theory)](https://www.youtube.com/watch?v=SiB55-dhk_U) | 8:35 |  |
| 297 |  | [Recursion-13 / Space Complexity (Theory)](https://www.youtube.com/watch?v=wcwFFvbNBBk) | 10:45 |  |
| 298 |  | [Recursion-14 / Tail Recursion (Theory)](https://www.youtube.com/watch?v=lfWv6vO9yck) | 12:27 |  |
| 299 | 50 | [Recursion-15 / 50. Pow(x, n)](https://www.youtube.com/watch?v=yE9XPcHMUMo) | 22:11 | ✅ Math/src/Pow.java |
| 300 | 21 | [Recursion-16 / 21. Merge Two Sorted Lists - Iterative + Recursive](https://www.youtube.com/watch?v=M4H9P4Kdmak) | 17:12 | ✅ LinkedList/src/MergeTwoLinkedList.java |
| 301 | 779 | [Recursion-17 / 779. K-th Symbol in Grammar](https://www.youtube.com/watch?v=hbWFsmwTL4c) | 15:41 |  |
| 302 |  | [Recursion-18 / Tips & Tricks](https://www.youtube.com/watch?v=5LxREpu7jLg) | 7:04 |  |
| 303 |  | [✅ Complete Recursion Playlist 🔁 / Phase 1 Complete / DSA + Interview Prep](https://www.youtube.com/watch?v=ECk9hbopFdU) | 1:28 |  |
| 304 |  | [Recursion-19 / Divide & Conquer Algorithm](https://www.youtube.com/watch?v=vL8N-NX6i9g) | 6:21 |  |
| 305 |  | [Recursion-20 / Merge Sort ](https://www.youtube.com/watch?v=C6_44KX1cdo) | 28:52 |  |
| 306 |  | [Recursion-21 / Divide & Conquer (Template)](https://www.youtube.com/watch?v=4gqgzCReQ6M) | 6:38 |  |
| 307 |  | [Recursion-22 / Master Theorem (Akra–Bazzi method)](https://www.youtube.com/watch?v=elkuy6bSVFM) | 32:01 |  |
| 308 |  | [Recursion-23 / Master Theorem (Akra–Bazzi method) (Practice)](https://www.youtube.com/watch?v=VDlEI_M2XOU) | 8:38 |  |
| 309 | 98 | [Recursion-24 / 98. Validate Binary Search Tree](https://www.youtube.com/watch?v=N8H85H1J4CQ) | 21:23 | ✅ Trees/src/ValidateBinarySearchTree.java |
| 310 | 240 | [Recursion-25 / 240. Search a 2D Matrix II / 3 Approaches](https://www.youtube.com/watch?v=IglNjeKakSU) | 35:21 |  |
| 311 |  | [Recursion-26 / Quick Sort / End + Random Pivot](https://www.youtube.com/watch?v=qDEVK_yzH80) | 26:22 |  |
| 312 |  | [Recursion-27 / What is Backtracking???](https://www.youtube.com/watch?v=yUVWGoEuVlg) | 10:41 |  |
| 313 | 51 | [Recursion-28 / 51. N-Queens (HARD) / Backtracking](https://www.youtube.com/watch?v=OVPv8kqeDeY) | 43:32 |  |
| 314 | 52 | [Recursion-29 / 52. N-Queens II (HARD) / Backtracking](https://www.youtube.com/watch?v=cbzQ0XPbnFw) | 5:55 |  |
| 315 |  | [Recursion-30 / Master Backtracking: The Only Template You Need](https://www.youtube.com/watch?v=pY8QPVidu4M) | 7:21 |  |
| 316 | 77 | [Recursion-31 / 77. Combinations](https://www.youtube.com/watch?v=MgACwMqHMHU) | 25:51 | ✅ Backtracking/src/Combinations.java |
| 317 | 37 | [Recursion-32 / 37. Sudoku Solver](https://www.youtube.com/watch?v=SU-NcEECu5w) | 47:20 |  |
| 318 |  | [Recursion-33 / Recursive to Iterative Code (Template + Tips & Tricks)](https://www.youtube.com/watch?v=ogkjUU-ltcc) | 12:21 |  |
| 319 | 100 | [Recursion-34 / 100. Same Tree](https://www.youtube.com/watch?v=eanCpzNHxRk) | 15:43 |  |
| 320 | 22 | [Recursion-35 / 22. Generate Parentheses](https://www.youtube.com/watch?v=blre_iVh3VU) | 27:25 |  |
| 321 | 17 | [Recursion-36 / 17. Letter Combinations of a Phone Number](https://www.youtube.com/watch?v=r_iKOkl4fuM) | 20:21 | ✅ Backtracking/src/LetterCombinations.java |
| 322 | 46 | [Recursion-37 / 46. Permutations](https://www.youtube.com/watch?v=6UmAY-rXVBc) | 13:42 | ✅ FAQ/src/PossiblePermutations.java, Backtracking/src/Permutations.java |
| 323 |  | [Dynamic Programming Series / BIGGEST DP Playlist / Java / DSA / Placements / FAANG Interviews #dp](https://www.youtube.com/watch?v=A-sXJKi2kfg) | 4:26 |  |
| 324 |  | [What are Greedy Algorithms? / #0 / Greedy Playlist #greedy #dsa](https://www.youtube.com/watch?v=nq4TJFdn0ZQ) | 9:46 |  |
| 325 |  | [DP-0 / What is Dynamic Programming??? / #0 / DP Playlist #dp #dsa](https://www.youtube.com/watch?v=yLMZcu3eesk) | 9:11 |  |
| 326 |  | [DP-1 / 💡 Top-Down vs Bottom-Up in Dynamic Programming (DP) / #1 / DP Playlist #dp #dsa](https://www.youtube.com/watch?v=425rDIfst8E) | 9:54 |  |
| 327 |  | [DP-2 / Identify DP Problems / #2 / DP Playlist #dp #dsa](https://www.youtube.com/watch?v=7-H4ij0MWTM) | 7:50 |  |
| 328 |  | [DP-3 / DP Framework / #3 / DP Playlist #dp #dsa](https://www.youtube.com/watch?v=An3sZBKeesw) | 16:31 |  |
| 329 | 198 | [DP-4 / 198. House Robber / #4 / DP Playlist #dp #dsa](https://www.youtube.com/watch?v=X5Z8UTWhsX8) | 27:54 | ✅ DynamicProgramming/src/HouseRobber.java |
| 330 | 1137 | [DP-5 / 1137. N-th Tribonacci Number / #5 / DP Playlist #dp #dsa](https://www.youtube.com/watch?v=Z-FLkOvGOw8) | 14:31 |  |
| 331 | 746 | [DP-6 / 746. Min Cost Climbing Stairs / #6 / DP Playlist #dp #dsa](https://www.youtube.com/watch?v=sBgsdeKxy2c) | 26:45 |  |
| 332 | 740 | [DP-7 / 740. Delete and Earn / #7 / DP Playlist #dp #dsa](https://www.youtube.com/watch?v=VdtYikfV28I) | 37:32 |  |
| 333 |  | [DP-8 / Multi-Dimensional DP / #8 / DP Playlist #dp #dsa](https://www.youtube.com/watch?v=ahxV--y7tJ8) | 4:19 |  |
| 334 |  | [DP-9 / Top-Down to Bottom Up / #9 / DP Playlist #dp #dsa](https://www.youtube.com/watch?v=xvygc2jhpMw) | 11:26 |  |
| 335 | 1770 | [DP-10 / 1770. Maximum Score from Performing Multiplication Operations (Part - 1) / #10 / DP Playlist](https://www.youtube.com/watch?v=P70rKM115_M) | 37:30 |  |
| 336 |  | [Ek Nayi Shuruat: The Next Chapter of CTO Bhaiya](https://www.youtube.com/watch?v=yJMZAFg024A) | 17:10 |  |
| 337 |  | [🔥 The Show Begins NOW!!!](https://www.youtube.com/watch?v=L0f0uT8kTFI) | 2:25 |  |

</details>

## 8. Resources

Status: **used** = counted in section 3; the codes match the table in 3.1.

### Company-wise question datasets (GitHub)

- [liquidslr/interview-company-wise-problems](https://github.com/liquidslr/interview-company-wise-problems): **used (D1)**, also the only source for sections 4–6. 471 companies, CSV per time window, with frequency and topics. Updated 20 Oct 2025.
- [krishnadey30/LeetCode-Questions-CompanyWise](https://www.github.com/krishnadey30/LeetCode-Questions-CompanyWise): company-wise lists sorted by frequency and all time. **Used (D4)**; last updated 2023.
- [snehasishroy/leetcode-companywise-interview-questions](https://github.com/snehasishroy/leetcode-companywise-interview-questions): company-wise lists sorted by recency. **Used (D3)**; last updated Aug 2026.
- [rahulb99/leetcode](https://github.com/rahulb99/leetcode): one CSV per company, same format as D1 but each list capped at 100. **Used (D2)**; snapshot Jun 2025.
- [devidnyk/leetcode-wrapper](https://github.com/devidnyk/leetcode-wrapper): an app that pulls the snehasishroy data. **Not used**: no data of its own.
- [MysteryVaibhav/leetcode_company_wise_questions](https://github.com/MysteryVaibhav/leetcode_company_wise_questions): company pages saved as PDFs in April 2019. **Used (D6)**, from the PDF text.
- [crakcode-hub/dsa-leetcode-questions](https://github.com/crakcode-hub/dsa-leetcode-questions): one CSV of about 1,450 problems with the companies that asked each. **Used (D5)**.

### Curated "top N" DSA lists

- [LeetCode Discuss: Top 100 DSA Interview Questions (post 4258631)](https://leetcode.com/discuss/post/4258631/top-100-dsa-interview-questions-by-anony-jlgg/): **not used**: blocked by LeetCode's Cloudflare check. Compare it with sections 3 and 4 by hand.
- [LeetCode Discuss: Top 100 DSA interview questions (study guide 4255692)](https://leetcode.com/discuss/study-guide/4255692/top-100-dsa-interview-questions): **not used**, blocked by Cloudflare.
- [LeetCode Discuss: Wrapping up 2025 with the most asked interview questions](https://leetcode.com/discuss/post/7417074/): **not used**, blocked by Cloudflare.
- [VikashPR/MAANG-Interview-Prep-100-DSA](https://github.com/VikashPR/MAANG-Interview-Prep-100-DSA): built from LeetCode Top Interview Questions, Blind 75 and NeetCode 150. **Used (C1)**.
- [GeeksforGeeks: Top 100 DSA Interview Questions Topic-wise](https://www.geeksforgeeks.org/dsa/top-100-data-structure-and-algorithms-dsa-interview-questions-topic-wise/): **used (C4)**, titles matched by hand.
- [Preplaced: 50 Most Asked DSA Interview Questions (2025)](https://preplaced.in/blog/50-most-asked-dsa-interview-questions): each problem tagged with companies. **Used (C3)**.
- [Top 150 DSA Questions for Interview](https://github.com/Avinash-singh11/top-150-DSA-questions-): **used (C5)**, titles matched by hand. (The search result pointed to ab9106.github.io, which now returns 404; this is the source repo.)
- [DEV: Top DSA questions asked in product-based companies](https://dev.to/devcorner/top-dsa-questions-asked-in-product-based-companies-2c2a): **used (C2)**.
- [singhsidhukuldeep: Data Structures and Algorithms interview questions](https://singhsidhukuldeep.github.io/Interview-Questions/data-structures-algorithms): **not used**, page removed (404).

### Video

- [DSA In 90 Days For Placements (CTO Bhaiya)](https://www.youtube.com/playlist?list=PLVItHqpXY_DArKRcfmGWykqV3u4hDaJLo): see section 7.

## 9. Refreshing this file

1. Clone the dataset outside this repo: `git clone --depth 1 https://github.com/liquidslr/interview-company-wise-problems.git`.
2. For each target company, read `<Company>/3. Six Months.csv` (or `5. All.csv` for small lists).
3. Count, per problem link, how many target companies list it; skip rows whose Topics include Database, Shell or Concurrency.
4. Cross-check (section 3): take each dataset's top 150 the same way, add every problem from the curated lists, and count how many sources include each problem.
5. Mark ✅ by matching each problem's `https://leetcode.com/problems/<slug>/` link against the second header line of the files in this repo.

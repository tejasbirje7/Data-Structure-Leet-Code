/**
 * LeetCode 300. Longest Increasing Subsequence
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * PROBLEM
 *   - Input: an integer array nums, length >= 1.
 *   - Return: the length of the longest STRICTLY increasing subsequence.
 *   - A subsequence keeps the original order but may skip elements (not contiguous).
 *
 * EXAMPLE
 *   Input: nums = [10,9,2,5,3,7,101,18]
 *   Output: 4
 *   Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * HOW TO THINK
 *   - Clue: "subsequence" + "longest" -> trying every subset is 2^n; build answers for prefixes instead (DP).
 *   - Technique: dp[i] = length of the longest increasing subsequence that ENDS at index i.
 *     Any such subsequence is some earlier one ending at j (with nums[j] < nums[i]) plus nums[i].
 *   - Rule that stays true: after step i, dp[0..i] are final; best = max of them.
 *   - Decision: for each j < i, nums[j] < nums[i] -> dp[i] = max(dp[i], dp[j] + 1).
 *               nums[j] >= nums[i]               -> skip j (it cannot come right before nums[i]).
 *   - Return: the max over all dp[i], not dp[n-1] (the best one can end anywhere).
 *
 * STEPS
 *   1. dp[i] = 1 for every i (nums[i] alone).
 *   2. Loop i from 0 to n-1; inner loop j from 0 to i-1.
 *   3. If nums[j] < nums[i], dp[i] = max(dp[i], dp[j] + 1).
 *   4. Track best = max(best, dp[i]); return best.
 *
 * DRY RUN   input = [10,9,2,5,3,7,101,18]   (dp[i] and best are after the step)
 *
 *    i | nums[i] | smaller earlier values (their dp) | max dp[j] | dp[i] | best
 *   ---+---------+-----------------------------------+-----------+-------+------
 *    0 |   10    | none                              |     -     |   1   |  1
 *    1 |    9    | none                              |     -     |   1   |  1
 *    2 |    2    | none                              |     -     |   1   |  1
 *    3 |    5    | 2(1)                              |     1     |   2   |  2
 *    4 |    3    | 2(1)                              |     1     |   2   |  2
 *    5 |    7    | 2(1) 5(2) 3(2)                    |     2     |   3   |  3
 *    6 |   101   | 10(1) 9(1) 2(1) 5(2) 3(2) 7(3)    |     3     |   4   |  4
 *    7 |   18    | 10(1) 9(1) 2(1) 5(2) 3(2) 7(3)    |     3     |   4   |  4
 *
 *   Returns 4: for example [2,5,7,101] or [2,3,7,18].
 *
 * EDGE CASES
 *   [5]              -> 1   single element
 *   [7,7,7,7]        -> 1   strictly increasing, so equal values cannot chain
 *   [5,4,3,2,1]      -> 1   decreasing: every dp[i] stays 1
 *   [0,1,0,3,2,3]    -> 4   [0,1,2,3]
 *
 * COMMON MISTAKES
 *   - Returning dp[n-1]: the longest subsequence may not end at the last element (try [1,2,0]).
 *   - Using <= instead of <: equal values would be counted as increasing.
 *   - Starting dp[i] at 0: every element is a subsequence of length 1 by itself.
 *
 * COMPLEXITY
 *   O(n^2) time, O(n) extra space.
 *   (Follow-up: O(n log n) keeps a "tails" array and binary searches into it.)
 *
 * RELATED
 *   LeetCode 139 (WordBreak, DynamicProgramming/): same shape, dp[i] looks back at every earlier j.
 *   LeetCode 1143 (LongestCommonSubsequence, DynamicProgramming/): subsequence across two strings, 2-D dp.
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }

    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int best = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            best = Math.max(best, dp[i]);
        }
        return best;
    }

}

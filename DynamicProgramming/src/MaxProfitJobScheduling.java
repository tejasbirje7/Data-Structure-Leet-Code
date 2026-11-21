import java.util.Arrays;

/**
 * LeetCode 1235. Maximum Profit in Job Scheduling
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/
 *
 * PROBLEM
 *   - Input: n jobs given as startTime[i], endTime[i], profit[i] (n up to 5 * 10^4).
 *   - Return: the largest total profit from a set of jobs that don't overlap.
 *   - A job ending at time X and another starting at time X do NOT overlap.
 *
 * EXAMPLE
 *   Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 *   Output: 120
 *   Explanation: The subset chosen is the first and fourth job.
 *   Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 *
 * HOW TO THINK
 *   - Clue: pick non-overlapping intervals to maximise a total -> weighted interval scheduling (DP + binary search).
 *   - Technique: sort jobs by end time. dp[i] = best profit using only the first i jobs.
 *     For job i - 1: skip it (dp[i-1]), or take it and add the best profit of the jobs that end
 *     by its start. Those jobs form a prefix of the sorted list, so binary search finds its length k.
 *   - Rule that stays true: dp[0] = 0; dp only grows as i grows.
 *   - Decision: dp[i] = max(dp[i-1], profit + dp[k]),
 *     where k = number of jobs (in end-time order) with end <= this job's start.
 *   - Return: dp[n].
 *
 * STEPS
 *   1. Pack jobs as {start, end, profit} and sort by end.
 *   2. dp = int[n + 1]; dp[0] = 0.
 *   3. For i from 1 to n: k = binary search for the first job in [0, i - 1) with end > start of job i - 1.
 *      dp[i] = max(dp[i - 1], profit of job i - 1 + dp[k]).
 *   4. Return dp[n].
 *
 * DRY RUN   input = startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 *   sorted by end: (1,3,50) (2,4,10) (3,5,40) (3,6,70)   (dp[i] is after the step)
 *
 *    i | job (s,e,p) | k = jobs ending <= s | take = p + dp[k] | skip = dp[i-1] | action | dp[i]
 *   ---+-------------+----------------------+------------------+----------------+--------+-------
 *    0 |      -      |          -           |        -         |       -        | base   |   0
 *    1 |  (1,3,50)   |          0           |  50 + 0  = 50    |       0        | take   |  50
 *    2 |  (2,4,10)   |          0           |  10 + 0  = 10    |      50        | skip   |  50
 *    3 |  (3,5,40)   |   1 (end 3 <= 3)     |  40 + 50 = 90    |      50        | take   |  90
 *    4 |  (3,6,70)   |   1 (end 3 <= 3)     |  70 + 50 = 120   |      90        | take   |  120
 *
 *   Returns 120: jobs (1,3,50) and (3,6,70).
 *
 * EDGE CASES
 *   one job                                                   -> its profit
 *   [1,2,3,4,6], [3,5,10,6,9], [20,20,100,70,60]              -> 150  jobs 1, 4, 5
 *   [1,1,1], [2,3,4], [5,6,4]                                 -> 6    all overlap, take the best one
 *   [1,3], [3,5], [5,5]                                       -> 10   end 3 == start 3 is allowed
 *
 * COMMON MISTAKES
 *   - Using end < start in the search: touching jobs are allowed, so the test is end <= start.
 *   - Sorting by start time with this dp: the "jobs that finish before me" are then not a prefix.
 *   - Scanning back linearly for k: O(n^2), too slow for 5 * 10^4 jobs. Use binary search.
 *   - Greedy by highest profit or earliest end: fails on weighted intervals (example 1 above).
 *
 * COMPLEXITY
 *   O(n log n) time: sort plus one binary search per job. O(n) extra space.
 *
 * RELATED
 *   LeetCode 198 (HouseRobber, DynamicProgramming/): same take-or-skip max, but the "previous safe" item is always i - 2.
 *   LeetCode 253 (MeetingRoomsII, Intervals/): intervals sorted by time, but counts rooms instead of choosing jobs.
 *   LeetCode 56 (MergeIntervals, Intervals/): sort intervals first, then sweep.
 */
public class MaxProfitJobScheduling {

    public static void main(String[] args) {
        System.out.println(jobScheduling(new int[]{1, 2, 3, 3}, new int[]{3, 4, 5, 6}, new int[]{50, 10, 40, 70}));
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[1], b[1]));
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int start = jobs[i - 1][0];
            int lo = 0;
            int hi = i - 1;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (jobs[mid][1] <= start) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            dp[i] = Math.max(dp[i - 1], jobs[i - 1][2] + dp[lo]);
        }
        return dp[n];
    }

}

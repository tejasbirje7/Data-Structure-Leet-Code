import java.util.*;

/**
 * LeetCode 128. Longest Consecutive Sequence
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * PROBLEM
 *   - Input: an unsorted integer array nums (may contain duplicates).
 *   - Return: the length of the longest run of consecutive values (like 1,2,3,4), in any order in nums.
 *   - Constraint: must run in O(n) time, so no sorting.
 *
 * EXAMPLE
 *   Input: nums = [100,4,200,1,3,2]
 *   Output: 4
 *   Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
 *   Therefore, its length is 4.
 *
 * HOW TO THINK
 *   - Clue: "consecutive" + "O(n)" means no sorting; you need O(1) lookups of n - 1 and n + 1.
 *   - Technique: hash map, value -> length of the run (sequence) it belongs to.
 *     Only the two ends (boundaries) of each run must hold the correct length.
 *   - Rule that stays true: for every run seen so far, map[first] = map[last] = run length.
 *     Middle values may be out of date; that's fine, a new number can only touch a run at its ends.
 *   - Decision: n already in map -> skip (a duplicate would be counted twice).
 *               new n -> left = length of the run ending at n - 1 (0 if none),
 *                        right = length of the run starting at n + 1 (0 if none).
 *               sum = left + right + 1 = the length of the run n is now in (left part + right part + n itself).
 *               Write sum at n, at the left end (n - left) and at the right end (n + right).
 *               With no neighbours all three writes hit n itself.
 *   - Return: res = the largest sum seen.
 *
 * STEPS
 *   1. res = 0, map = {}.
 *   2. For each n in nums, skip it if map already has n.
 *   3. left = map.getOrDefault(n - 1, 0), right = map.getOrDefault(n + 1, 0), sum = left + right + 1.
 *   4. res = max(res, sum); put sum at n, n - left and n + right.
 *   5. Return res.
 *
 * DRY RUN   nums = [100,4,200,5,1,3,2,3]   (map shown after the step; the method also prints it)
 *
 *    n   | left | right | sum | writes sum at | res | map after
 *   -----+------+-------+-----+---------------+-----+----------------------------------------------
 *    100 |  0   |   0   |  1  | 100           |  1  | {100=1}
 *     4  |  0   |   0   |  1  | 4             |  1  | {4=1, 100=1}
 *    200 |  0   |   0   |  1  | 200           |  1  | {4=1, 100=1, 200=1}
 *     5  |  1   |   0   |  2  | 5, 4          |  2  | {4=2, 5=2, 100=1, 200=1}
 *     1  |  0   |   0   |  1  | 1             |  2  | {1=1, 4=2, 5=2, 100=1, 200=1}
 *     3  |  0   |   2   |  3  | 3, 5          |  3  | {1=1, 3=3, 4=2, 5=3, 100=1, 200=1}
 *     2  |  1   |   3   |  5  | 2, 1, 5       |  5  | {1=5, 2=5, 3=3, 4=2, 5=5, 100=1, 200=1}
 *     3  |  -   |   -   |  -  | in map, skip  |  5  | unchanged
 *
 *   Returns 5: the run 1,2,3,4,5. Its ends 1 and 5 hold 5; the middle values 3 and 4 are stale.
 *
 * EDGE CASES
 *   []                       -> 0   the loop never runs
 *   [7]                      -> 1   no neighbours
 *   [5,5,5]                  -> 1   repeats are skipped
 *   [1,2,0,1]                -> 3   the second 1 is skipped
 *   [0,3,7,2,5,8,4,6,0,1]    -> 9   the run 0..8
 *   [Integer.MAX_VALUE, Integer.MIN_VALUE] -> 2 (should be 1): n + 1 and n - 1 wrap around;
 *                               LeetCode limits values to -10^9..10^9
 *
 * COMMON MISTAKES
 *   - Dropping the "already in map" check: a repeated middle value joins runs again and over-counts.
 *     In the dry run, the second 3 would get left = map[2] = 5, right = map[4] = 2, sum = 8.
 *   - Updating only map[n]: later numbers look at n - 1 and n + 1, which are the run's ends,
 *     so the ends must get the new length.
 *   - Sorting first: O(n log n), breaks the O(n) rule.
 *
 * COMPLEXITY
 *   O(n) time, O(n) extra space.
 *
 * RELATED
 *   LeetCode 228 (SummaryRanges, Intervals/): sorted input, so consecutive runs are found in one scan.
 */
public class LongestConsecutive {

    public static void main(String[] args) {
        int[] nums = {100,4,200,5,1,3,2,3};
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] num) {
        int res =0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : num) {
            if (!map.containsKey(n)) {
                int left = map.getOrDefault(n - 1, 0);
                int right = map.getOrDefault(n + 1, 0);

                int sum = left + right + 1;

                res = Math.max(res, sum);

                map.put(n, sum);
                map.put(n - left, sum);
                map.put(n + right, sum);
                System.out.println(map);
                System.out.println(" ");
            }
        }
        return res;
    }
}

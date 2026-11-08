/**
 * LeetCode 45. Jump Game II
 * https://leetcode.com/problems/jump-game-ii/
 *
 * PROBLEM
 *   - Input: nums[i] = the maximum forward jump from index i; you start at index 0.
 *   - Return: the minimum number of jumps to reach the last index n - 1.
 *   - The last index is always reachable (guaranteed). This version still returns -1 if it isn't.
 *
 * EXAMPLE
 *   Input: nums = [2,3,1,1,4]
 *   Output: 2
 *   Explanation: The minimum number of jumps to reach the last index is 2.
 *   Jump 1 step from index 0 to index 1, then 3 steps to the last index.
 *
 * HOW TO THINK
 *   - Clue: "minimum number of jumps" = fewest steps -> think BFS, level by level.
 *   - Technique: greedy BFS without a queue. The window [left, right] holds every index you can
 *     reach with exactly `hops` jumps. The next window runs from right + 1 to the farthest index
 *     any index in this window can reach.
 *   - Rule that stays true: with hops jumps you can reach every index up to right, and nothing beyond.
 *   - Decision: while right < n - 1: farthest = max(i + nums[i]) over i in [left, right];
 *               farthest <= right -> stuck, the next window would be empty: return -1.
 *               otherwise         -> left = right + 1, right = farthest, hops++.
 *   - Return: hops as soon as right reaches n - 1.
 *
 * STEPS
 *   1. hops = 0, left = right = 0 (the window for 0 jumps is just index 0).
 *   2. While right < n - 1:
 *   3.   farthest = max of i + nums[i] for i from left to right.
 *   4.   If farthest <= right, return -1 (the end can't be reached).
 *   5.   left = right + 1, right = farthest, hops++.
 *   6. Return hops.
 *
 * DRY RUN   input = [2,3,0,1,4]   (n = 5; left, right, hops are values after the step)
 *
 *    window [left,right] | right < 4 ? | farthest from window | stuck? | left | right | hops
 *   ---------------------+-------------+----------------------+--------+------+-------+------
 *    [0,0]               |   yes       | 0+2 = 2              |   no   |  1   |   2   |  1
 *    [1,2]               |   yes       | max(1+3, 2+0) = 4    |   no   |  3   |   4   |  2
 *    [3,4]               |   no, stop  |                      |        |      |       |
 *
 *   Returns 2: 0 -> 1 -> 4.
 *
 * EDGE CASES
 *   [0]           -> 0    already at the end; the loop never runs
 *   [1,1,1,1]     -> 3    every window is one index wide
 *   [1,2]         -> 1    one jump lands on the end
 *   [5,1,1]       -> 1    the first window already reaches past the end
 *   [0,1]         -> -1   stuck at once: farthest = 0, not past right = 0
 *   [1,0,1]       -> -1   window [1,1] reaches only index 1 (1+0), so farthest = right
 *                         (LeetCode guarantees the end is reachable, so it never sees -1)
 *
 * COMMON MISTAKES
 *   - hops++ inside the for loop: that counts indexes, not levels. Count once per window.
 *   - Loop condition right < n instead of right < n - 1: adds an extra jump after the end is
 *     already covered ([2,3,0,1,4] would give 3).
 *   - Only looking at i + nums[i] for the last index of the window: any index in the window
 *     may give the farthest reach, so take the max over all of them.
 *   - No guard for unreachable input: if farthest never passes right, right stops growing
 *     and the while loop runs forever. Return -1 when farthest <= right.
 *
 * COMPLEXITY
 *   O(n) time (each index is in exactly one window), O(1) extra space.
 *
 * RELATED
 *   LeetCode 55 (JumpGame, ArraysAndStrings/): only asks whether the end is reachable;
 *     greedy goal moved backwards.
 */
public class JumpGame2 {

    public static void main(String[] args) {
        int[] input = {2,3,0,1,4};
        System.out.println(jump(input));
    }

    public static int jump(int[] nums) {
        int hops = 0;
        int left = 0;
        int right = 0;

        while (right < nums.length - 1) {
            int farthest = 0;
            for (int i = left; i < right + 1; i++) {
                farthest = Math.max(farthest,i+nums[i]);
            }
            if (farthest <= right) {
                return -1;
            }
            left = right + 1;
            right = farthest;
            hops++;
        }
        return hops;
    }
}

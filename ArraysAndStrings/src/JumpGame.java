/**
 * LeetCode 55. Jump Game
 * https://leetcode.com/problems/jump-game/
 *
 * PROBLEM
 *   - Input: nums[i] = the maximum jump length from index i; you start at index 0.
 *   - Return: true if you can reach the last index, false otherwise.
 *
 * EXAMPLE
 *   Input: nums = [2,3,1,1,4]
 *   Output: true
 *   Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * HOW TO THINK
 *   - Clue: only "can you reach", not how many jumps -> greedy, no need to try every path.
 *   - Technique: walk backwards and move the goal.
 *     If index i can reach the current goal, then reaching i is enough, so i becomes the new goal.
 *   - Rule that stays true: goal = the leftmost index seen so far from which the last index is reachable.
 *   - Decision: i + nums[i] >= goal -> goal = i.
 *               otherwise -> keep goal (an index further left may still jump over i).
 *   - Return: goal == 0 means the start can reach the end.
 *
 * STEPS
 *   1. goal = last index.
 *   2. Loop i from the last index down to 0.
 *   3. If i + nums[i] >= goal, set goal = i.
 *   4. Return goal == 0.
 *
 * DRY RUN   input = [2,3,1,0,4]   (goal is the value after the step)
 *
 *    i | nums[i] | i + nums[i] | reaches goal? | action    | goal
 *   ---+---------+-------------+---------------+-----------+------
 *    4 |    4    |      8      |  8 >= 4  yes  | goal = 4  |  4
 *    3 |    0    |      3      |  3 >= 4  no   | keep      |  4
 *    2 |    1    |      3      |  3 >= 4  no   | keep      |  4
 *    1 |    3    |      4      |  4 >= 4  yes  | goal = 1  |  1
 *    0 |    2    |      2      |  2 >= 1  yes  | goal = 0  |  0
 *
 *   Returns true (goal == 0): 0 -> 1 -> 4, jumping over the 0 at index 3.
 *
 * EDGE CASES
 *   [3,2,1,0,4]  -> false  every path lands on index 3, whose jump is 0
 *   [0]          -> true   already at the last index
 *   [0,1]        -> false  stuck at the start
 *   [2,0,0]      -> true   one jump of 2 goes straight to the end
 *   []           -> false  goal = -1, never 0; LeetCode guarantees n >= 1
 *
 * COMMON MISTAKES
 *   - Checking against the last index instead of the moving goal: an index that only reaches
 *     a good stepping stone would be missed.
 *   - Using > instead of >=: landing exactly on the goal is enough.
 *   - Trying every path with recursion: exponential time. The greedy is O(n).
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 45 (JumpGame2, ArraysAndStrings/): the end is always reachable; find the minimum number of jumps.
 *   LeetCode 134 (GasStation, ArraysAndStrings/): another one-pass greedy over an array.
 */
public class JumpGame {

    public static void main(String[] args) {
        int[] input = {2,3,1,0,4};
        System.out.println(canJump(input));
    }

    public static boolean canJump(int[] input) {
        int goal = input.length - 1;
        for (int i = input.length - 1; i >= 0; i--) {
            if (i + input[i] >= goal) {
                goal = i;
            }
        }
        return goal == 0;
    }

}

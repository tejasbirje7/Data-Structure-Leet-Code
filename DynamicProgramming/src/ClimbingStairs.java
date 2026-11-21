/**
 * LeetCode 70. Climbing Stairs
 * https://leetcode.com/problems/climbing-stairs/
 *
 * PROBLEM
 *   - Input: n, the number of steps (1 <= n <= 45).
 *   - Return: how many distinct ways there are to reach the top, climbing 1 or 2 steps at a time.
 *
 * EXAMPLE
 *   Input: n = 3
 *   Output: 3
 *   Explanation: There are three ways to climb to the top.
 *     1. 1 step + 1 step + 1 step
 *     2. 1 step + 2 steps
 *     3. 2 steps + 1 step
 *
 * HOW TO THINK
 *   - Clue: "how many distinct ways" + small fixed moves -> count by the LAST move (DP).
 *   - Technique: the last move onto step i is either a 1 (from i-1) or a 2 (from i-2),
 *     so ways(i) = ways(i-1) + ways(i-2). This is Fibonacci, and only the last two values are needed.
 *   - Rule that stays true: before computing step i, prev1 = ways(i-1) and prev2 = ways(i-2).
 *   - Decision: no branching; each step does cur = prev1 + prev2, then shifts the pair forward.
 *   - Return: prev1 after the loop = ways(n). Handle n <= 2 directly.
 *
 * STEPS
 *   1. If n <= 2, return n.
 *   2. prev2 = 1 (ways to step 1), prev1 = 2 (ways to step 2).
 *   3. Loop i from 3 to n: cur = prev1 + prev2; prev2 = prev1; prev1 = cur.
 *   4. Return prev1.
 *
 * DRY RUN   input = 5   (prev2 and prev1 are after the step)
 *
 *    i | prev1 + prev2 | cur | prev2 | prev1
 *   ---+---------------+-----+-------+-------
 *    - | init          |  -  |   1   |   2
 *    3 |     2 + 1     |  3  |   2   |   3
 *    4 |     3 + 2     |  5  |   3   |   5
 *    5 |     5 + 3     |  8  |   5   |   8
 *
 *   Returns 8.
 *
 * EDGE CASES
 *   1    -> 1            only a single 1-step
 *   2    -> 2            1+1 or 2
 *   45   -> 1836311903   largest allowed n; still fits in an int
 *
 * COMMON MISTAKES
 *   - Plain recursion ways(n-1) + ways(n-2) with no memo: exponential time, times out near n = 45.
 *   - Shifting prev2 = prev1 before computing cur: cur then adds the wrong pair.
 *   - Wrong base values (ways(2) = 1): there are two ways to reach step 2.
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 509 (FibonacciNumber, DynamicProgramming/): the same recurrence with different starting values.
 *   LeetCode 198 (HouseRobber, DynamicProgramming/): also uses only the last two dp values, but takes a max.
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(5));
    }

    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int prev2 = 1;
        int prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int cur = prev1 + prev2;
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

}

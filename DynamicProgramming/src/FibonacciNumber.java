/**
 * LeetCode 509. Fibonacci Number
 * https://leetcode.com/problems/fibonacci-number/
 *
 * PROBLEM
 *   - Input: an integer n (0 to 30).
 *   - Return: F(n), where F(0) = 0, F(1) = 1 and F(n) = F(n - 1) + F(n - 2).
 *
 * EXAMPLE
 *   Input: n = 2
 *   Output: 1
 *   Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 *
 * HOW TO THINK
 *   - Clue: each value depends only on the two before it -> the simplest DP there is.
 *   - Technique: build up from F(0) and F(1), keeping just the last two values.
 *     Plain recursion recomputes the same F(k) again and again (about 2^n calls).
 *   - Rule that stays true: at the start of loop step i, prev = F(i - 2) and curr = F(i - 1).
 *   - Decision: no branching; each step does next = prev + curr, then slides prev and curr forward.
 *   - Return: curr after the loop, which is F(n). For n < 2 return n directly.
 *
 * STEPS
 *   1. If n < 2, return n.
 *   2. prev = 0 (F(0)), curr = 1 (F(1)).
 *   3. Loop i from 2 to n: next = prev + curr; prev = curr; curr = next.
 *   4. Return curr.
 *
 * DRY RUN   input = n = 5   (prev and curr are after the step)
 *
 *    i | next = prev + curr | prev | curr
 *   ---+--------------------+------+------
 *    - | start              |  0   |  1
 *    2 | 0 + 1 = 1          |  1   |  1
 *    3 | 1 + 1 = 2          |  1   |  2
 *    4 | 1 + 2 = 3          |  2   |  3
 *    5 | 2 + 3 = 5          |  3   |  5
 *
 *   Returns 5 (curr = F(5)).
 *
 * EDGE CASES
 *   0   -> 0        early return
 *   1   -> 1        early return
 *   2   -> 1        one loop step
 *   30  -> 832040   largest allowed n; fits in int
 *
 * COMMON MISTAKES
 *   - Plain recursion fib(n - 1) + fib(n - 2) with no memo: exponential time.
 *   - Updating curr before saving it into prev: both end up holding the same value.
 *   - Off by one in the loop: start at i = 2 and run through i = n inclusive.
 *
 * COMPLEXITY
 *   O(n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 70 (ClimbingStairs, DynamicProgramming/): the same recurrence starting from 1, 1.
 *   LeetCode 91 (DecodeWays, DynamicProgramming/): the same shape, but each of the two terms is added only if valid.
 *   LeetCode 198 (HouseRobber, DynamicProgramming/): also keeps only the last two values, but takes a max.
 */
public class FibonacciNumber {

    public static void main(String[] args) {
        System.out.println(fib(5));
    }

    public static int fib(int n) {
        if (n < 2) {
            return n;
        }
        int prev = 0;
        int curr = 1;
        for (int i = 2; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }

}

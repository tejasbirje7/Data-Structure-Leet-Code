import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode 735. Asteroid Collision
 * https://leetcode.com/problems/asteroid-collision/
 *
 * PROBLEM
 *   - Input: asteroids in a row. |value| is the size, the sign is the direction
 *     (positive = right, negative = left). All move at the same speed. No zeros.
 *   - When two meet, the smaller one explodes; equal sizes both explode.
 *   - Return: the asteroids left after all collisions, in their original order.
 *
 * EXAMPLE
 *   Input: asteroids = [5,10,-5]
 *   Output: [5,10]
 *   Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 *
 * HOW TO THINK
 *   - Clue: a new asteroid only fights the most recent survivors to its left, last one first
 *     -> stack of survivors.
 *   - Technique: a collision happens only when the new asteroid moves left (a < 0) and the top
 *     of the stack moves right (top > 0). Every other pair moves apart or side by side.
 *   - Rule that stays true: the stack holds the survivors so far, in order, and no two of them
 *     will ever collide.
 *   - Decision while a < 0 and top > 0:
 *       top < |a|  -> top explodes: pop, and a keeps going (check the next top).
 *       top == |a| -> both explode: pop, and a is gone.
 *       top > |a|  -> a explodes: a is gone.
 *     If a survives the loop, push it.
 *   - Return: the stack from bottom to top.
 *
 * STEPS
 *   1. Empty stack.
 *   2. For each a: alive = true.
 *   3.   While alive and a < 0 and stack not empty and top > 0: apply the decision above.
 *   4.   If alive: push a.
 *   5. Return the stack bottom -> top as an array.
 *
 * DRY RUN   asteroids = [-2,5,10,-5,-10,3,-8]   (stack shown bottom -> top, after the step)
 *
 *    a   | top meets a      | result             | stack after
 *   -----+------------------+--------------------+-------------
 *    -2  | stack empty      | push               | -2
 *     5  | a moves right    | push               | -2 5
 *    10  | a moves right    | push               | -2 5 10
 *    -5  | 10 vs 5          | a explodes         | -2 5 10
 *   -10  | 10 vs 10         | both explode       | -2 5
 *     3  | a moves right    | push               | -2 5 3
 *    -8  | 3 vs 8           | top explodes       | -2 5
 *        | 5 vs 8           | top explodes       | -2
 *        | -2 moves left    | no collision, push | -2 -8
 *
 *   Returns [-2,-8].
 *
 * EDGE CASES
 *   [5,10,-5]        -> [5,10]          official example 1
 *   [8,-8]           -> []              official example 2: equal sizes, both gone
 *   [10,2,-5]        -> [10]            official example 3: -5 destroys 2, then loses to 10
 *   [-2,-1,1,2]      -> [-2,-1,1,2]     moving apart: left-movers first, right-movers last
 *   [1,-2,-2,-2]     -> [-2,-2,-2]      the first -2 wins, the rest just follow it left
 *
 * COMMON MISTAKES
 *   - Colliding when top < 0 and a > 0: they move apart, so they never meet.
 *   - Using if instead of while: one big left-mover can destroy many right-movers in a row.
 *   - Pushing a after "both explode": a is gone too, which is why alive is set to false.
 *
 * COMPLEXITY
 *   O(n) time (each asteroid pushed and popped at most once), O(n) extra space.
 *
 * RELATED
 *   LeetCode 20 (ValidParentheses, Stack/): the same idea of a new item cancelling the top of
 *     the stack, with '(' as a right-mover and ')' as a left-mover.
 *   LeetCode 739 (DailyTemperatures, Stack/): another "while the new item beats the top, pop" loop.
 */
public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int a : asteroids) {
            boolean alive = true;
            while (alive && a < 0 && !stack.isEmpty() && stack.peek() > 0) {
                int top = stack.peek();
                if (top < -a) {
                    stack.pop();
                } else if (top == -a) {
                    stack.pop();
                    alive = false;
                } else {
                    alive = false;
                }
            }
            if (alive) {
                stack.push(a);
            }
        }
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        AsteroidCollision sol = new AsteroidCollision();

        System.out.println(Arrays.toString(sol.asteroidCollision(new int[]{-2, 5, 10, -5, -10, 3, -8}))); // Expected: [-2, -8]
        System.out.println(Arrays.toString(sol.asteroidCollision(new int[]{5, 10, -5})));                 // Expected: [5, 10]
        System.out.println(Arrays.toString(sol.asteroidCollision(new int[]{8, -8})));                     // Expected: []
        System.out.println(Arrays.toString(sol.asteroidCollision(new int[]{10, 2, -5})));                 // Expected: [10]
        System.out.println(Arrays.toString(sol.asteroidCollision(new int[]{-2, -1, 1, 2})));              // Expected: [-2, -1, 1, 2]
        System.out.println(Arrays.toString(sol.asteroidCollision(new int[]{1, -2, -2, -2})));             // Expected: [-2, -2, -2]
    }
}

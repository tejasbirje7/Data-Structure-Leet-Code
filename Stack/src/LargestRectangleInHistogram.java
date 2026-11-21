import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 84. Largest Rectangle in Histogram
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * PROBLEM
 *   - Input: heights of bars in a histogram, each bar 1 unit wide.
 *   - Return: the area of the largest rectangle that fits inside the histogram.
 *   - Constraint: n up to 10^5, so trying every pair of ends (O(n^2)) is too slow.
 *
 * EXAMPLE
 *   Input: heights = [2,1,5,6,2,3]
 *   Output: 10
 *   Explanation: The above is a histogram where width of each bar is 1.
 *   The largest rectangle is shown in the red area, which has an area = 10 units.
 *
 * HOW TO THINK
 *   - Clue: the best rectangle is as tall as some bar and stretches left and right until a
 *     shorter bar. "First shorter bar on each side" -> monotonic stack.
 *   - Technique: keep a stack of bar indices with increasing heights. When bar i is shorter than
 *     the top, the top bar can't stretch past i, so its rectangle is now final:
 *       height = heights[top], right edge = i (exclusive),
 *       left edge = the new top after popping (exclusive), or -1 if the stack is empty.
 *       width = i - left - 1.
 *   - Rule that stays true: heights on the stack increase from bottom to top, and each bar's
 *     left limit is the bar just below it on the stack.
 *   - Decision: while h < heights[top]: pop and measure. Then push i.
 *   - A fake bar of height 0 at i = n pops (and measures) everything still on the stack.
 *   - Return: the best area seen.
 *
 * STEPS
 *   1. Empty stack of indices, best = 0.
 *   2. For i = 0 .. n: h = (i == n) ? 0 : heights[i].
 *   3.   while stack not empty and h < heights[top]: height = heights[pop];
 *          left = stack empty ? -1 : top; best = max(best, height * (i - left - 1)).
 *   4.   push i.
 *   5. Return best.
 *
 * DRY RUN   heights = [2,1,5,6,2,3]   (stack shown bottom -> top as index(height), after the step)
 *
 *    i | h | popped bar | left | width | area | best | stack after
 *   ---+---+------------+------+-------+------+------+-----------------
 *    0 | 2 | -          |  -   |   -   |  -   |  0   | 0(2)
 *    1 | 1 | 0 (h 2)    |  -1  |   1   |  2   |  2   | 1(1)
 *    2 | 5 | -          |  -   |   -   |  -   |  2   | 1(1) 2(5)
 *    3 | 6 | -          |  -   |   -   |  -   |  2   | 1(1) 2(5) 3(6)
 *    4 | 2 | 3 (h 6)    |   2  |   1   |  6   |  6   |
 *      |   | 2 (h 5)    |   1  |   2   | 10   | 10   | 1(1) 4(2)
 *    5 | 3 | -          |  -   |   -   |  -   | 10   | 1(1) 4(2) 5(3)
 *    6 | 0 | 5 (h 3)    |   4  |   1   |  3   | 10   |
 *      |   | 4 (h 2)    |   1  |   4   |  8   | 10   |
 *      |   | 1 (h 1)    |  -1  |   6   |  6   | 10   | 6(0)
 *
 *   Returns 10: height 5 across bars 2..3.
 *
 * EDGE CASES
 *   [2,4]          -> 4    official example 2: the single bar of height 4
 *   [5]            -> 5    one bar
 *   [3,3,3]        -> 9    equal bars: the fake 0 bar measures the full width
 *   [1,2,3,4,5]    -> 9    only increasing: nothing pops until the fake bar (3 x 3)
 *   [5,4,3,2,1]    -> 9    only decreasing: every bar pops the one before (3 x 3)
 *   [0,0]          -> 0    no area at all
 *
 * COMMON MISTAKES
 *   - Forgetting the final flush: bars still on the stack at the end are never measured.
 *     The fake height-0 bar at i = n fixes it.
 *   - Width i - top instead of i - left - 1: the left edge is the bar BELOW the popped one,
 *     which may be far left of it (bar 4 with height 2 stretches back to index 2).
 *   - Left = -1 missed when the stack is empty: then the bar stretches all the way to index 0.
 *
 * COMPLEXITY
 *   O(n) time (each bar pushed and popped once), O(n) extra space.
 *
 * RELATED
 *   LeetCode 739 (DailyTemperatures, Stack/): the same stack, popping on a bigger value instead.
 *   LeetCode 42 (RainWater, ArraysAndStrings/): also about bars and their limits on each side.
 *   LeetCode 85 (Maximal Rectangle, not in repo yet): run this on every row of a 0/1 matrix,
 *     treating each column's run of 1s as a bar.
 */
public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int best = 0;
        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int left = stack.isEmpty() ? -1 : stack.peek();
                best = Math.max(best, height * (i - left - 1));
            }
            stack.push(i);
        }
        return best;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram sol = new LargestRectangleInHistogram();

        System.out.println(sol.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3})); // Expected: 10
        System.out.println(sol.largestRectangleArea(new int[]{2, 4}));             // Expected: 4
        System.out.println(sol.largestRectangleArea(new int[]{5}));                // Expected: 5
        System.out.println(sol.largestRectangleArea(new int[]{3, 3, 3}));          // Expected: 9
        System.out.println(sol.largestRectangleArea(new int[]{1, 2, 3, 4, 5}));    // Expected: 9
        System.out.println(sol.largestRectangleArea(new int[]{5, 4, 3, 2, 1}));    // Expected: 9
        System.out.println(sol.largestRectangleArea(new int[]{0, 0}));             // Expected: 0
    }
}

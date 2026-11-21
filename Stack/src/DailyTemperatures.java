import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode 739. Daily Temperatures
 * https://leetcode.com/problems/daily-temperatures/
 *
 * PROBLEM
 *   - Input: an array temperatures, one value per day.
 *   - Return: answer[i] = how many days after day i until a strictly warmer day, or 0 if none.
 *   - Constraint: n up to 10^5, so checking every later day (O(n^2)) is too slow.
 *
 * EXAMPLE
 *   Input: temperatures = [73,74,75,71,69,72,76,73]
 *   Output: [1,1,4,2,1,1,0,0]
 *
 * HOW TO THINK
 *   - Clue: "next warmer day" = next greater element -> monotonic stack.
 *   - Technique: keep a stack of days still WAITING for a warmer day. Their temperatures go
 *     down (or stay equal) from bottom to top, because a warmer day would have answered them.
 *   - Rule that stays true: every day on the stack has no warmer day yet, and the stack's
 *     temperatures never increase from bottom to top.
 *   - Decision: for day i, while temps[i] > temps[top]: pop top, answer[top] = i - top.
 *               Then push i (it now waits for its own warmer day).
 *   - Return: answer; days left on the stack keep their default 0.
 *   - Store indices, not temperatures: the answer is a distance in days.
 *
 * STEPS
 *   1. answer = new int[n] (all 0), empty stack of indices.
 *   2. For each day i:
 *   3.   while stack not empty and temps[i] > temps[top]: pop j, answer[j] = i - j.
 *   4.   push i.
 *   5. Return answer.
 *
 * DRY RUN   temperatures = [73,74,75,71,69,72,76,73]   (stack shown bottom -> top as index(temp), after the step)
 *
 *    i | temp | pops (answer set)  | stack after
 *   ---+------+--------------------+-------------------
 *    0 |  73  | -                  | 0(73)
 *    1 |  74  | 0 -> ans[0] = 1    | 1(74)
 *    2 |  75  | 1 -> ans[1] = 1    | 2(75)
 *    3 |  71  | -                  | 2(75) 3(71)
 *    4 |  69  | -                  | 2(75) 3(71) 4(69)
 *    5 |  72  | 4 -> ans[4] = 1    |
 *      |      | 3 -> ans[3] = 2    | 2(75) 5(72)
 *    6 |  76  | 5 -> ans[5] = 1    |
 *      |      | 2 -> ans[2] = 4    | 6(76)
 *    7 |  73  | -                  | 6(76) 7(73)
 *
 *   Returns [1,1,4,2,1,1,0,0]. Days 6 and 7 never get a warmer day, so they stay 0.
 *
 * EDGE CASES
 *   [30,40,50,60]   -> [1,1,1,0]   always warmer: each day pops the one before
 *   [30,60,90]      -> [1,1,0]     official example 3
 *   [90,60,30]      -> [0,0,0]     always colder: nothing is ever popped
 *   [50]            -> [0]         one day
 *   [70,70,71]      -> [2,1,0]     equal is not warmer: the first 70 waits for 71
 *
 * COMMON MISTAKES
 *   - Using >= in the pop check: an equal day would wrongly count as warmer.
 *   - Pushing temperatures instead of indices: you can't compute the distance i - j.
 *   - Using if instead of while: one warm day can answer many waiting days at once (day 5 above).
 *
 * COMPLEXITY
 *   O(n) time (each day is pushed once and popped at most once), O(n) extra space.
 *
 * RELATED
 *   LeetCode 496 (NextGreaterElement, Stack/): the same stack, but stores the warmer VALUE in a map.
 *   LeetCode 503 (NextGreaterElement2, Stack/): the array wraps around, so loop over it twice.
 *   LeetCode 84 (LargestRectangleInHistogram, Stack/): the same stack the other way round
 *     (pops on a smaller value) to find how far each bar can stretch.
 *   LeetCode 239 (SlidingWindowMaximum, SlidingWindow/): a monotonic deque that also drops old indices.
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int j = stack.pop();
                answer[j] = i - j;
            }
            stack.push(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        DailyTemperatures sol = new DailyTemperatures();

        System.out.println(Arrays.toString(sol.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}))); // Expected: [1, 1, 4, 2, 1, 1, 0, 0]
        System.out.println(Arrays.toString(sol.dailyTemperatures(new int[]{30, 40, 50, 60})));                 // Expected: [1, 1, 1, 0]
        System.out.println(Arrays.toString(sol.dailyTemperatures(new int[]{30, 60, 90})));                     // Expected: [1, 1, 0]
        System.out.println(Arrays.toString(sol.dailyTemperatures(new int[]{90, 60, 30})));                     // Expected: [0, 0, 0]
        System.out.println(Arrays.toString(sol.dailyTemperatures(new int[]{50})));                             // Expected: [0]
        System.out.println(Arrays.toString(sol.dailyTemperatures(new int[]{70, 70, 71})));                     // Expected: [2, 1, 0]
    }
}

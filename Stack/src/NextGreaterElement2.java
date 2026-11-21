import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode 503. Next Greater Element II
 * https://leetcode.com/problems/next-greater-element-ii/
 *
 * PROBLEM
 *   - Input: a circular array nums (after the last element comes nums[0] again). Values may repeat.
 *   - Return: for each element, the first greater value met when walking right (wrapping
 *     around), or -1 if there is none.
 *
 * EXAMPLE
 *   Input: nums = [1,2,1]
 *   Output: [2,-1,2]
 *   Explanation: The first 1's next greater number is 2;
 *   The number 2 can't find next greater number.
 *   The second 1's next greater number needs to search circularly, which is also 2.
 *
 * HOW TO THINK
 *   - Clue: "next greater" -> monotonic stack. "Circular" -> walk the array twice.
 *   - Technique: same stack as LeetCode 496, but over i = 0 .. 2n - 1 using index i % n.
 *     The second lap lets elements near the end find a greater value near the start.
 *     Only push during the first lap: every index needs one place on the stack, not two.
 *   - Store indices (values can repeat, so a value can't be a map key).
 *   - Rule that stays true: the stack holds indices still waiting for a greater value, and their
 *     values never increase from bottom to top.
 *   - Decision: v = nums[i % n]; while v > nums[top] -> ans[pop] = v. If i < n, push i.
 *   - Return: ans, pre-filled with -1 for indices that never find a greater value.
 *
 * STEPS
 *   1. ans = new int[n] filled with -1, empty stack of indices.
 *   2. For i = 0 .. 2n - 1: v = nums[i % n].
 *   3.   while stack not empty and v > nums[top]: ans[pop] = v.
 *   4.   if i < n: push i.
 *   5. Return ans.
 *
 * DRY RUN   nums = [1,2,3,4,3]   (stack shown bottom -> top as index(value), after the step)
 *
 *    i | i%n | v | pops (answer set)  | push? | stack after
 *   ---+-----+---+--------------------+-------+--------------
 *    0 |  0  | 1 | -                  | yes   | 0(1)
 *    1 |  1  | 2 | 0 -> ans[0] = 2    | yes   | 1(2)
 *    2 |  2  | 3 | 1 -> ans[1] = 3    | yes   | 2(3)
 *    3 |  3  | 4 | 2 -> ans[2] = 4    | yes   | 3(4)
 *    4 |  4  | 3 | -                  | yes   | 3(4) 4(3)
 *    5 |  0  | 1 | -                  | no    | 3(4) 4(3)
 *    6 |  1  | 2 | -                  | no    | 3(4) 4(3)
 *    7 |  2  | 3 | - (3 is not > 3)   | no    | 3(4) 4(3)
 *    8 |  3  | 4 | 4 -> ans[4] = 4    | no    | 3(4)
 *    9 |  4  | 3 | -                  | no    | 3(4)
 *
 *   Returns [2,3,4,-1,4]. Index 3 (the max, 4) stays -1. Index 4 needed the wrap to find 4.
 *
 * EDGE CASES
 *   [1,2,1]       -> [2,-1,2]       official example 1
 *   [5]           -> [-1]           one element: nothing greater, not even itself
 *   [3,3,3]       -> [-1,-1,-1]     all equal: equal is not greater
 *   [5,4,3,2,1]   -> [-1,5,5,5,5]   only goes down: every answer comes from the second lap
 *   [1,2,3]       -> [2,3,-1]       only goes up: the max stays -1
 *
 * COMMON MISTAKES
 *   - Walking the array once: misses answers that wrap around, e.g. the last 1 in [1,2,1].
 *   - Pushing in the second lap too: indices get two places on the stack (harmless, but wasted work).
 *   - Filling ans with 0 instead of -1: values that never find a greater one must be -1.
 *   - Using >= in the pop check: an equal value is not greater.
 *
 * COMPLEXITY
 *   O(n) time (2n steps, each index pushed and popped at most once), O(n) extra space.
 *
 * RELATED
 *   LeetCode 496 (NextGreaterElement, Stack/): not circular and values distinct, so one pass and a map.
 *   LeetCode 739 (DailyTemperatures, Stack/): the same stack, but records the distance i - j.
 */
public class NextGreaterElement2 {

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 2 * n; i++) {
            int v = nums[i % n];
            while (!stack.isEmpty() && v > nums[stack.peek()]) {
                ans[stack.pop()] = v;
            }
            if (i < n) {
                stack.push(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        NextGreaterElement2 sol = new NextGreaterElement2();

        System.out.println(Arrays.toString(sol.nextGreaterElements(new int[]{1, 2, 3, 4, 3}))); // Expected: [2, 3, 4, -1, 4]
        System.out.println(Arrays.toString(sol.nextGreaterElements(new int[]{1, 2, 1})));       // Expected: [2, -1, 2]
        System.out.println(Arrays.toString(sol.nextGreaterElements(new int[]{5})));             // Expected: [-1]
        System.out.println(Arrays.toString(sol.nextGreaterElements(new int[]{3, 3, 3})));       // Expected: [-1, -1, -1]
        System.out.println(Arrays.toString(sol.nextGreaterElements(new int[]{5, 4, 3, 2, 1}))); // Expected: [-1, 5, 5, 5, 5]
        System.out.println(Arrays.toString(sol.nextGreaterElements(new int[]{1, 2, 3})));       // Expected: [2, 3, -1]
    }
}

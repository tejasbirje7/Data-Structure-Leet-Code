import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 496. Next Greater Element I
 * https://leetcode.com/problems/next-greater-element-i/
 *
 * PROBLEM
 *   - Input: two arrays of distinct values; every value of nums1 also appears in nums2.
 *   - For each x in nums1, find x in nums2 and return the first value to its right that is
 *     greater than x, or -1 if there is none.
 *   - Follow-up: O(nums1.length + nums2.length) time.
 *
 * EXAMPLE
 *   Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 *   Output: [-1,3,-1]
 *   Explanation: The next greater element for each value of nums1 is as follows:
 *   - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 *   - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 *   - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 *
 * HOW TO THINK
 *   - Clue: "next greater element to the right" -> monotonic stack.
 *   - Technique: walk nums2 once. The stack holds values still waiting for a greater one.
 *     When a bigger value v arrives, it is the answer for every smaller value on top: pop them
 *     and record next[popped] = v in a map. Then answer nums1 with map lookups.
 *   - Rule that stays true: the stack has no answer yet for any of its values, and its values
 *     go down from bottom to top.
 *   - Decision: for each v in nums2: while v > top -> map[pop] = v. Then push v.
 *   - Return: for each x in nums1, map.getOrDefault(x, -1).
 *   - Values are distinct, so the value itself can be the map key.
 *
 * STEPS
 *   1. Empty stack, empty map value -> next greater.
 *   2. For each v in nums2: while stack not empty and v > top: map.put(pop, v). Push v.
 *   3. For each i: ans[i] = map.getOrDefault(nums1[i], -1).
 *   4. Return ans.
 *
 * DRY RUN   nums1 = [4,1,2], nums2 = [1,3,4,2]   (stack shown bottom -> top, after the step)
 *
 *    v | pops (map entry added) | stack after | map after
 *   ---+------------------------+-------------+------------
 *    1 | -                      | 1           | {}
 *    3 | 1 -> next[1] = 3       | 3           | {1:3}
 *    4 | 3 -> next[3] = 4       | 4           | {1:3, 3:4}
 *    2 | -                      | 4 2         | {1:3, 3:4}
 *
 *   4 and 2 are left on the stack: no greater value. Lookups: 4 -> -1, 1 -> 3, 2 -> -1.
 *   Returns [-1,3,-1].
 *
 * EDGE CASES
 *   [2,4], [1,2,3,4]     -> [3,-1]       official example 2
 *   [5], [5]             -> [-1]         one value
 *   [1,2,3], [3,2,1]     -> [-1,-1,-1]   nums2 only goes down: nothing popped
 *   [1,2,3], [1,2,3]     -> [2,3,-1]     nums2 only goes up: each value pops the last
 *   [3], [3,1,2,4]       -> [4]          the smaller 1 and 2 are skipped, not the answer
 *
 * COMMON MISTAKES
 *   - For each x in nums1, scanning nums2 from its position: correct but O(m * n).
 *   - Taking the next value instead of the next GREATER one: [3,1,2,4] would give 1 for 3.
 *   - Forgetting the default -1 for values left on the stack.
 *
 * COMPLEXITY
 *   O(m + n) time (each nums2 value is pushed and popped at most once), O(n) extra space.
 *
 * RELATED
 *   LeetCode 503 (NextGreaterElement2, Stack/): one array that wraps around; store indices.
 *   LeetCode 739 (DailyTemperatures, Stack/): the same stack, but the answer is the distance.
 */
public class NextGreaterElement {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> next = new HashMap<>();
        for (int v : nums2) {
            while (!stack.isEmpty() && v > stack.peek()) {
                next.put(stack.pop(), v);
            }
            stack.push(v);
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = next.getOrDefault(nums1[i], -1);
        }
        return ans;
    }

    public static void main(String[] args) {
        NextGreaterElement sol = new NextGreaterElement();

        System.out.println(Arrays.toString(sol.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}))); // Expected: [-1, 3, -1]
        System.out.println(Arrays.toString(sol.nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4})));    // Expected: [3, -1]
        System.out.println(Arrays.toString(sol.nextGreaterElement(new int[]{5}, new int[]{5})));                // Expected: [-1]
        System.out.println(Arrays.toString(sol.nextGreaterElement(new int[]{1, 2, 3}, new int[]{3, 2, 1})));    // Expected: [-1, -1, -1]
        System.out.println(Arrays.toString(sol.nextGreaterElement(new int[]{1, 2, 3}, new int[]{1, 2, 3})));    // Expected: [2, 3, -1]
        System.out.println(Arrays.toString(sol.nextGreaterElement(new int[]{3}, new int[]{3, 1, 2, 4})));       // Expected: [4]
    }
}

import java.util.Arrays;

/**
 * LeetCode 189. Rotate Array (cyclic replacements approach)
 * https://leetcode.com/problems/rotate-array/
 *
 * PROBLEM
 *   - Input: an integer array nums and a non-negative number k.
 *   - Task: rotate the array to the right by k steps, in place.
 *   - Return: nothing; nums itself must end up rotated.
 *   - Follow-up: do it with O(1) extra space.
 *
 * EXAMPLE
 *   Input: nums = [1,2,3,4,5,6,7], k = 3
 *   Output: [5,6,7,1,2,3,4]
 *   Explanation:
 *   rotate 1 steps to the right: [7,1,2,3,4,5,6]
 *   rotate 2 steps to the right: [6,7,1,2,3,4,5]
 *   rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 * HOW TO THINK
 *   - Clue: rotating right by k means the element at index i must end up at (i + k) % n.
 *   - Technique: cyclic replacements, like musical chairs.
 *     Carry one value, drop it at its new spot, pick up the value that was sitting there,
 *     and carry that one to its new spot. Repeat.
 *   - Why "cycles": jumping by k keeps wrapping around and eventually lands back on the start.
 *     If n and k share a factor, one cycle skips some indexes,
 *     so start a new cycle at the next index (start + 1).
 *   - Number of cycles = gcd(n, k); each cycle moves n / gcd(n, k) elements.
 *   - Rule that stays true: count = how many elements are already in their final spot.
 *     Stop when count == n, so no element is moved twice.
 *   - Decision: inner loop: move prev into nums[next], keep the value that was there (temp).
 *               the cycle ends when current comes back to start.
 *   - First, k = k % n: rotating by n changes nothing.
 *   - Return: nothing, the array is changed in place.
 *
 * STEPS
 *   1. k = k % n, count = 0.
 *   2. For start = 0, 1, 2, ... while count < n:
 *   3.   current = start, prev = nums[start].
 *   4.   Repeat: next = (current + k) % n; save temp = nums[next]; nums[next] = prev;
 *        prev = temp; current = next; count++. Stop when current == start.
 *
 * DRY RUN   nums = [1,2,3,4,5,6], k = 2   (n = 6, gcd(6, 2) = 2, so 2 cycles of 3)
 *
 *    start | current -> next | prev placed | temp picked up | count | array after
 *   -------+-----------------+-------------+----------------+-------+---------------
 *      0   |     0 -> 2      |      1      |       3        |   1   | [1,2,1,4,5,6]
 *      0   |     2 -> 4      |      3      |       5        |   2   | [1,2,1,4,3,6]
 *      0   |     4 -> 0      |      5      |       1        |   3   | [5,2,1,4,3,6]   back at start
 *      1   |     1 -> 3      |      2      |       4        |   4   | [5,2,1,2,3,6]
 *      1   |     3 -> 5      |      4      |       6        |   5   | [5,2,1,2,3,4]
 *      1   |     5 -> 1      |      6      |       2        |   6   | [5,6,1,2,3,4]   back at start
 *
 *   count = 6 = n, done. Final array: [5,6,1,2,3,4].
 *   (With the LeetCode example, n = 7 and k = 3 share no factor, so one cycle moves all 7.)
 *
 * EDGE CASES
 *   k = 0                    -> unchanged   every cycle has length 1, each value is put back in place
 *   k = n                    -> unchanged   k % n = 0
 *   [1,2,3,4,5,6,7], k = 3   -> [5,6,7,1,2,3,4]   gcd = 1, a single cycle covers everything
 *   [1,2,3], k = 4           -> [3,1,2]     k % n = 1
 *   [5], any k               -> [5]         k % 1 = 0
 *   []                       -> crashes on k % 0; LeetCode guarantees at least one element
 *
 * COMMON MISTAKES
 *   - Running only one cycle: when gcd(n, k) > 1 it comes back to start early and misses elements.
 *     [1,2,3,4,5,6], k = 2 would stop at [5,2,1,4,3,6].
 *   - Starting a cycle at every index without the count check: elements get moved twice.
 *   - Writing nums[next] = prev before saving nums[next]: that value is lost. Save it in temp first.
 *
 * COMPLEXITY
 *   O(n) time (every element is moved exactly once), O(1) extra space.
 *
 * RELATED
 *   LeetCode 189 (RotateArray, ArraysAndStrings/): the same problem with three reversals.
 *     Same O(n) time and O(1) space, and easier to get right in an interview.
 *   LeetCode 61 (RotateList, LinkedList/): the same rotation on a linked list.
 */
public class RotateArrayCyclic {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6};
        int k = 2;
        rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = 0;
        for (int start = 0; count < n; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (current != start);
        }
    }
}

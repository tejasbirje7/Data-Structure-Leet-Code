/**
 * LeetCode 287. Find the Duplicate Number
 * https://leetcode.com/problems/find-the-duplicate-number/
 *
 * PROBLEM
 *   - Input: an array nums of n + 1 integers, each in 1..n.
 *   - Exactly one value is repeated (it may appear 2 or more times).
 *   - Return: that value.
 *   - Do not change nums, use only O(1) extra space. Follow-up: better than O(n^2).
 *
 * EXAMPLE
 *   Input: nums = [1,3,4,2,2]
 *   Output: 2
 *
 * HOW TO THINK
 *   - Clue: values are in 1..n and also valid indexes, "don't modify", O(1) space
 *     -> treat it as a linked list: index i points to index nums[i].
 *   - Two indexes pointing at the same value = two arrows into one node = a cycle,
 *     and the node where the cycle starts is the duplicate value.
 *   - Technique: Floyd's cycle detection (slow/fast pointers), as in Linked List Cycle II.
 *   - Phase 1: slow moves 1 step (nums[slow]), fast moves 2 (nums[nums[fast]]); stop when they meet
 *     (somewhere inside the cycle).
 *   - Phase 2: put slow back at the start; move both 1 step; where they meet is the cycle entry.
 *   - Rule that stays true: in phase 2, slow and fast are always the same distance from the entry.
 *   - Return: the meeting value of phase 2.
 *   - Start: index 0 is never pointed to (values are >= 1), so it is a safe start outside the cycle.
 *
 * STEPS
 *   1. slow = fast = nums[0].
 *   2. Do slow = nums[slow], fast = nums[nums[fast]] until slow == fast.
 *   3. slow = nums[0].
 *   4. While slow != fast: slow = nums[slow], fast = nums[fast].
 *   5. Return slow.
 *
 * DRY RUN   nums = [1,3,4,2,2]   (index -> value: 0->1, 1->3, 2->4, 3->2, 4->2; cycle 2 -> 4 -> 2)
 *
 *    phase | step | slow after | fast after | slow == fast?
 *   -------+------+------------+------------+--------------
 *    start |  -   |     1      |     1      |   (both = nums[0])
 *      1   |  1   |     3      |     2      |   no
 *      1   |  2   |     2      |     2      |   yes, stop phase 1
 *      2   |  -   |     1      |     2      |   slow reset to nums[0]
 *      2   |  1   |     3      |     4      |   no
 *      2   |  2   |     2      |     2      |   yes, cycle entry
 *
 *   Returns 2.
 *
 * EDGE CASES
 *   [1,1]          -> 1     smallest input (n = 1)
 *   [3,1,3,4,2]    -> 3     phase 1 meets at 3, which is already the entry; phase 2 loop doesn't run
 *   [2,2,2,2,2]    -> 2     value repeated many times
 *   [1,4,4,2,4]    -> 4     duplicate at several positions
 *
 * COMMON MISTAKES
 *   - Starting phase 1 with slow = fast but using a while (slow != fast) loop: it never runs.
 *     Use do-while, or move once before the loop.
 *   - Moving fast 2 steps in phase 2: both pointers must move 1 step, or they meet somewhere
 *     other than the entry (or never meet).
 *   - Sorting or a HashSet: correct, but sorting changes nums and a set uses O(n) space.
 *
 * COMPLEXITY
 *   O(n) time (both phases take at most a few laps of the list), O(1) extra space; nums is not changed.
 *
 * RELATED
 *   LeetCode 141 (LinkedListCycle, LinkedList/): phase 1 only, on real list nodes.
 *   LeetCode 142 (StartOfCycle, FAQ/): the same two phases on a linked list.
 *   LeetCode 202 (IsHappy, TwoPointers/): slow/fast on a "next number" function instead of an array.
 */
public class FindDuplicateNumber {

    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[]{1, 3, 4, 2, 2}));
    }

    public static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}

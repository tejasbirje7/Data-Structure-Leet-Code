/**
 * LeetCode 169. Majority Element
 * https://leetcode.com/problems/majority-element/
 *
 * PROBLEM
 *   - Input: an integer array nums of size n.
 *   - Return: the majority element = the value that appears more than n / 2 times (rounded down).
 *   - A majority element always exists. Follow-up: O(n) time and O(1) extra space.
 *
 * EXAMPLE
 *   Input: nums = [3,2,3]
 *   Output: 3
 *
 * HOW TO THINK
 *   - Clue: "more than n / 2 times" -> the majority outnumbers all the other values put together.
 *   - Technique: Moore's (Boyer-Moore) voting. Keep one candidate (el) and a vote count.
 *     Each different value cancels one vote. The majority can't be cancelled out completely,
 *     so it is the candidate left at the end.
 *   - Rule that stays true: count = votes the current candidate still has.
 *     When count hits 0, the part seen so far pairs up into different values and can be forgotten.
 *   - Decision: count == 0 -> new candidate: el = num, count++.
 *               num != el  -> vote against: count--.
 *               num == el  -> vote for: count++.
 *   - Check pass: count how often el really appears; return it only if that is more than n / 2,
 *     else -1. Only needed when a majority might not exist (LeetCode guarantees one).
 *   - Return: el, or -1.
 *
 * STEPS
 *   1. count = 0, el = 0.
 *   2. For each num: if count == 0, el = num and count++; else if num != el, count--; else count++.
 *   3. Count how many times el appears (repeatingElement).
 *   4. If repeatingElement > n / 2, return el; otherwise return -1.
 *
 * DRY RUN   nums = [2,2,1,1,1,2,2]   (el and count are values after the step)
 *
 *    num | count before | action                 | el | count
 *   -----+--------------+------------------------+----+-------
 *     2  |      0       | count 0, new candidate | 2  |   1
 *     2  |      1       | same, count++          | 2  |   2
 *     1  |      2       | different, count--     | 2  |   1
 *     1  |      1       | different, count--     | 2  |   0
 *     1  |      0       | count 0, new candidate | 1  |   1
 *     2  |      1       | different, count--     | 1  |   0
 *     2  |      0       | count 0, new candidate | 2  |   1
 *
 *   Check pass: 2 appears 4 times, 4 > 7 / 2 = 3, so it returns 2.
 *
 * EDGE CASES
 *   [1,7,2,7,8,7,7]  -> 7    a new candidate is picked 4 times (1, 2, 8, 7); 7 wins only at the end
 *   [5]              -> 5    one element
 *   [1,2,3]          -> -1   no majority: candidate 3 appears once; LeetCode guarantees a majority
 *   [1,1,2,2]        -> -1   exactly n / 2 is not "more than" n / 2
 *   []               -> -1   loops never run, 0 > 0 is false
 *
 * COMMON MISTAKES
 *   - Checking num != el before count == 0: at count 0 you'd drop to -1 instead of
 *     picking a new candidate. Test count == 0 first.
 *   - Trusting the candidate without the check pass when a majority isn't guaranteed:
 *     [1,2,3] leaves candidate 3.
 *   - Using >= n / 2 in the check: the majority must appear strictly more than half the time.
 *
 * COMPLEXITY
 *   O(n) time (two passes), O(1) extra space.
 *
 * RELATED
 *   LeetCode 451 (SortCharactersByFrequency, FAQ/): counts frequencies with a HashMap,
 *     the simple way to find common values when O(n) extra space is fine.
 *   LeetCode 229 Majority Element II (not in this repo): the same voting with two candidates,
 *     for values that appear more than n / 3 times.
 */
public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }

    public static int majorityElement(int[] nums) {
        int count = 0;
        int el = 0;

        for (int num : nums) {
            if (count == 0) {
                el = num;
                count++;
            } else if (num != el) {
                count--;
            } else {
                count++;
            }
        }

        int repeatingElement = 0;
        for (int num : nums) {
            if(el == num) {
                repeatingElement++;
            }
        }

        if(repeatingElement > (nums.length / 2)) {
            return el;
        }
        return -1;
    }
}

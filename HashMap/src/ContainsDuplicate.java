import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 217. Contains Duplicate
 * https://leetcode.com/problems/contains-duplicate/
 *
 * PROBLEM
 *   - Input: an integer array nums.
 *   - Return: true if any value appears at least twice; false if every value is distinct.
 *   - The array is not sorted, and its position doesn't matter: any two equal values count.
 *
 * EXAMPLE
 *   Input: nums = [1,2,3,1]
 *   Output: true
 *   Explanation: The element 1 occurs at the indices 0 and 3.
 *
 *   Input: nums = [1,2,3,4]
 *   Output: false
 *   Explanation: All elements are distinct.
 *
 *   Input: nums = [1,1,1,3,3,4,3,2,4,2]
 *   Output: true
 *
 * HOW TO THINK
 *   - Clue: "appears at least twice" = "have I seen this value before?".
 *   - Technique: HashSet of the values seen so far. Checking and adding are both O(1).
 *   - Rule that stays true: before index i is checked, the set holds exactly nums[0..i-1],
 *     and they are all distinct (otherwise we would have returned already).
 *   - Decision: nums[i] in the set -> seen before: return true.
 *               otherwise          -> add nums[i] and move on.
 *   - Return: true at the first repeat; false if the loop finishes.
 *
 * STEPS
 *   1. seen = empty set.
 *   2. Loop over every num in nums.
 *   3. If seen contains num, return true. Otherwise add num to seen.
 *   4. After the loop, return false.
 *
 * DRY RUN   nums = [1,2,3,1]   (seen is the set before the check)
 *
 *    i | nums[i] | seen      | in seen? | action
 *   ---+---------+-----------+----------+-------------
 *    0 |    1    | {}        |   no     | add 1
 *    1 |    2    | {1}       |   no     | add 2
 *    2 |    3    | {1,2}     |   no     | add 3
 *    3 |    1    | {1,2,3}   |   yes    | return true
 *
 *   Returns true: 1 is at index 0 and index 3.
 *
 * EDGE CASES
 *   [1,2,3,4]          -> false   every check misses, the loop finishes
 *   [7]                -> false   one value, nothing to compare with
 *   [5,5]              -> true    the repeat is right next to the first copy
 *   [-1,0,-1]          -> true    negative values work the same way
 *   []                 -> false   the loop never runs (LeetCode guarantees at least one element)
 *
 * COMMON MISTAKES
 *   - Two nested loops comparing every pair: correct, but O(n^2), too slow for n = 10^5.
 *   - Adding before checking: the value is then always "found", so every array returns true.
 *   - Sorting and comparing neighbours: works in O(n log n), but changes the input array.
 *     Fine only if you are allowed to modify nums.
 *
 * COMPLEXITY
 *   O(n) time (one pass, O(1) set operations), O(n) extra space (the set can hold every value).
 *
 * RELATED
 *   LeetCode 219 (ContainsDuplicate, SlidingWindow/): the copies must also be at most k indexes apart,
 *     so the set only holds the last k values (a sliding window).
 *   LeetCode 1 (TwoSum, HashMap/): same "have I seen it before?" lookup, but for target - num
 *     instead of num itself.
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(containsDuplicate(nums));
    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
}

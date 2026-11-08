import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 219. Contains Duplicate II
 * https://leetcode.com/problems/contains-duplicate-ii/
 *
 * PROBLEM
 *   - Input: an integer array nums and an integer k.
 *   - Return: true if two different indexes i and j have nums[i] == nums[j] and abs(i - j) <= k.
 *   - Otherwise return false.
 *
 * EXAMPLE
 *   Input: nums = [1,2,3,1], k = 3
 *   Output: true
 *
 *   Input: nums = [1,0,1,1], k = 1
 *   Output: true
 *
 *   Input: nums = [1,2,3,1,2,3], k = 2
 *   Output: false
 *
 * HOW TO THINK
 *   - Clue: "abs(i - j) <= k" means only the last k values before index r matter.
 *     That is a window of fixed size that slides to the right.
 *   - Technique: sliding window + HashSet. The set holds the values inside the window,
 *     so "have I seen nums[r] nearby?" is an O(1) lookup.
 *   - Rule that stays true: just before the check, the set holds exactly nums[l..r-1],
 *     and r - l <= k (every value in it is at most k steps from r).
 *   - Decision: r - l > k          -> window too wide: remove nums[l] from the set, l++.
 *               nums[r] in the set -> a close duplicate: return true.
 *               otherwise          -> add nums[r] and move on.
 *   - Return: true as soon as a duplicate is found in the window; false after the loop.
 *   - Why removing is safe: the set never holds two equal values (we would have returned true),
 *     so removing nums[l] removes exactly that one index.
 *
 * STEPS
 *   1. l = 0, window = empty set.
 *   2. Loop r from 0 to n - 1.
 *   3. If r - l > k: remove nums[l] from the set, l++.
 *   4. If the set contains nums[r], return true. Otherwise add nums[r].
 *   5. After the loop, return false.
 *
 * DRY RUN   nums = [1,2,3,1,3], k = 2   (l and window are after the shrink, before the check)
 *
 *    r | nums[r] | r - l > k ?   | shrink   | l | window | found? | action
 *   ---+---------+---------------+----------+---+--------+--------+-------------
 *    0 |    1    | 0 - 0 > 2 no  | -        | 0 | {}     | no     | add 1
 *    1 |    2    | 1 - 0 > 2 no  | -        | 0 | {1}    | no     | add 2
 *    2 |    3    | 2 - 0 > 2 no  | -        | 0 | {1,2}  | no     | add 3
 *    3 |    1    | 3 - 0 > 2 yes | remove 1 | 1 | {2,3}  | no     | add 1
 *    4 |    3    | 4 - 1 > 2 yes | remove 2 | 2 | {3,1}  | yes    | return true
 *
 *   Returns true: nums[2] = nums[4] = 3, and 4 - 2 = 2 <= k.
 *   The 1 at index 3 does not count: its twin at index 0 is 3 steps away and was already removed.
 *
 * EDGE CASES
 *   [1,2,3,1], k = 3         -> true    the copies are exactly k apart, which is allowed
 *   [1,2,3,1,2,3], k = 2     -> false   every pair of copies is 3 apart
 *   [1,1], k = 0             -> false   the window is empty at every check (i and j must differ)
 *   [1,2,1], k = 100         -> true    k bigger than the array: the window never shrinks
 *   [1], k = 1               -> false   nothing to compare with
 *   [], k = 1                -> false   the loop never runs
 *
 * COMMON MISTAKES
 *   - Shrinking when r - l >= k: the window keeps only k - 1 values and misses copies exactly k apart.
 *     [1,2,3,1], k = 3 would give false.
 *   - Checking before shrinking: a value k + 1 steps away is still in the set.
 *     [1,2,1], k = 1 would give true.
 *   - Using one "seen" set for the whole array (Contains Duplicate I): it ignores the distance.
 *     [1,2,3,1,2,3], k = 2 would give true.
 *
 * COMPLEXITY
 *   O(n) time, O(min(n, k)) extra space (the set holds at most k + 1 values).
 *
 * RELATED
 *   LeetCode 3 (LongestSubstring, SlidingWindow/): same HashSet window, but it shrinks
 *     when a repeat shows up instead of at a fixed size.
 *   LeetCode 209 (MinimumSizeSubArray, SlidingWindow/): a variable window over a running sum.
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = {1,2,3,1,3};
        int k = 2;
        System.out.println(containsNearbyDuplicate(nums,k));
    }
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        int l = 0;
        Set<Integer> window = new HashSet<>();
        for (int r=0; r<nums.length; r++) {
            if(r - l > k) {
                window.remove(nums[l]);
                l++;
            }
            if(window.contains(nums[r]))
                return true;
            window.add(nums[r]);
        }
        return false;
    }
}

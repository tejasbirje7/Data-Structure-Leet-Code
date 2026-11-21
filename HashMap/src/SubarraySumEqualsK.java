import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 560. Subarray Sum Equals K
 * https://leetcode.com/problems/subarray-sum-equals-k/
 *
 * PROBLEM
 *   - Input: an int array nums (1..2 * 10^4 long, values -1000..1000) and an int k.
 *   - Return: how many contiguous, non-empty subarrays add up to exactly k.
 *   - Values can be negative, so a sliding window does NOT work.
 *
 * EXAMPLE
 *   Input: nums = [1,1,1], k = 2
 *   Output: 2
 *
 * HOW TO THINK
 *   - Clue: "contiguous subarray sum" + negatives allowed = prefix sums + hash map
 *     (a window can't decide when to shrink if numbers can be negative).
 *   - Technique: prefix = sum of nums[0..i]. A subarray nums[j+1..i] sums to k exactly when
 *     prefix(i) - prefix(j) == k, i.e. an earlier prefix equals prefix(i) - k.
 *   - Rule that stays true: seen[p] = how many prefixes BEFORE index i equal p.
 *     seen starts as {0: 1}: the empty prefix, so subarrays that start at index 0 are counted.
 *   - Decision per i: prefix += nums[i]; count += seen[prefix - k]; then seen[prefix]++.
 *     (Look up first, then add, so a subarray is never empty.)
 *   - Return: count.
 *
 * STEPS
 *   1. seen = {0: 1}, prefix = 0, count = 0.
 *   2. For each x in nums:
 *   3.   prefix += x.
 *   4.   count += seen.getOrDefault(prefix - k, 0).
 *   5.   seen[prefix] += 1.
 *   6. Return count.
 *
 * DRY RUN   nums = [3,4,7,2,-3,1,4,2], k = 7   (seen is after the step)
 *
 *    i | x  | prefix | prefix - k | seen[prefix - k] | count | seen after
 *   ---+----+--------+------------+------------------+-------+-------------------------------------
 *    0 |  3 |    3   |     -4     |        0         |   0   | {0:1, 3:1}
 *    1 |  4 |    7   |      0     |        1         |   1   | {0:1, 3:1, 7:1}             [3,4]
 *    2 |  7 |   14   |      7     |        1         |   2   | + 14:1                      [7]
 *    3 |  2 |   16   |      9     |        0         |   2   | + 16:1
 *    4 | -3 |   13   |      6     |        0         |   2   | + 13:1
 *    5 |  1 |   14   |      7     |        1         |   3   | 14:2                        [7,2,-3,1]
 *    6 |  4 |   18   |     11     |        0         |   3   | + 18:1
 *    7 |  2 |   20   |     13     |        1         |   4   | + 20:1                      [1,4,2]
 *
 *   Returns 4: [3,4], [7], [7,2,-3,1], [1,4,2].
 *
 * EDGE CASES
 *   [1,1,1], k = 2      -> 2    LeetCode example
 *   [1,2,3], k = 3      -> 2    [1,2] and [3]
 *   [1], k = 0          -> 0    subarrays are non-empty, so the {0:1} seed alone doesn't count
 *   [0,0,0], k = 0      -> 6    every one of the 6 subarrays sums to 0
 *   [1,-1,0], k = 0     -> 3    [1,-1], [0], [1,-1,0]; negatives break a sliding window here
 *
 * COMMON MISTAKES
 *   - Starting with an empty map: subarrays that begin at index 0 are missed. Seed {0: 1}.
 *   - Adding prefix to the map before the lookup: with k = 0 every element counts itself as an empty subarray.
 *   - Using a sliding window: only works when all numbers are positive.
 *   - Storing a boolean "seen" instead of a count: equal prefixes can occur many times (see 14 above).
 *
 * COMPLEXITY
 *   O(n) time (one pass, O(1) map work per step), O(n) extra space for the map.
 *
 * RELATED
 *   LeetCode 1 (TwoSum, HashMap/): the same "look up target - current before storing current" idea.
 *   LeetCode 209 (MinimumSizeSubArray, SlidingWindow/): positive numbers only, so a window works there.
 *   LeetCode 238 (ProductExceptSelf, ArraysAndStrings/): prefix products instead of prefix sums.
 */
public class SubarraySumEqualsK {

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{3, 4, 7, 2, -3, 1, 4, 2}, 7));
    }

    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> seen = new HashMap<>();
        seen.put(0, 1);
        int prefix = 0;
        int count = 0;
        for (int x : nums) {
            prefix += x;
            count += seen.getOrDefault(prefix - k, 0);
            seen.merge(prefix, 1, Integer::sum);
        }
        return count;
    }
}

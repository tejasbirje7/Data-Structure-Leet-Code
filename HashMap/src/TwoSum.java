import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1. Two Sum
 * https://leetcode.com/problems/two-sum/
 *
 * PROBLEM
 *   - Input: an unsorted integer array nums and a target.
 *   - Return: the indexes of the two numbers that add up to target, in any order.
 *   - Exactly one answer exists, and the same element can't be used twice.
 *
 * EXAMPLE
 *   Input: nums = [2,7,11,15], target = 9
 *   Output: [0,1]
 *   Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * HOW TO THINK
 *   - Clue: "return indexes" of an unsorted array: sorting would lose the indexes,
 *     so remember where each value was seen instead.
 *   - Technique: hash map, value -> index, filled in one pass.
 *     For nums[i], the partner it needs is difference = target - nums[i].
 *   - Rule that stays true: trackMap holds every value before index i, with its index.
 *   - Decision: difference is in the map -> found: [map[difference], i].
 *               not in the map           -> put nums[i] -> i and move on.
 *   - Why check before putting: a number can't pair with itself ([3,2,4], target 6: 3 must not use 3).
 *   - Return: [earlier index, current index]. If no pair exists it returns [0, 0].
 *
 * STEPS
 *   1. trackMap = {}, result = new int[2].
 *   2. For each index i: difference = target - nums[i].
 *   3. If trackMap has difference, return [trackMap[difference], i].
 *   4. Otherwise put nums[i] -> i.
 *   5. After the loop, return result (still [0, 0]).
 *
 * DRY RUN   nums = [2,7,11,15], target = 9   (trackMap shown after the step)
 *
 *    i | nums[i] | difference | in map?   | action         | trackMap
 *   ---+---------+------------+-----------+----------------+----------
 *    0 |    2    |     7      | no        | put 2 -> 0     | {2=0}
 *    1 |    7    |     2      | yes, at 0 | return [0, 1]  | {2=0}
 *
 *   Returns [0, 1]: 2 + 7 = 9.
 *
 * EDGE CASES
 *   [3,2,4], 6              -> [1,2]   3 is checked before it is stored, so it doesn't pair with itself
 *   [3,3], 6                -> [0,1]   the first 3 is already in the map when the second one comes
 *   [-1,-2,-3,-4,-5], -8    -> [2,4]   negative numbers work the same way
 *   [1,2], 7                -> [0,0] (no real answer): the loop ends and the empty result is returned;
 *                              LeetCode guarantees exactly one answer
 *
 * COMMON MISTAKES
 *   - Putting nums[i] in the map before checking: [3,2,4], 6 would return [0,0] (3 pairs with itself).
 *   - Two nested loops: O(n^2) time.
 *   - Sorting and using two pointers: the original indexes are lost.
 *
 * COMPLEXITY
 *   O(n) time, O(n) extra space.
 *
 * RELATED
 *   LeetCode 167 (TwoSum, TwoPointers/): variant with sorted input; two pointers, O(1) space,
 *     1-based indexes.
 *   LeetCode 15 (ThreeSum, TwoPointers/; also in FAQ/): fix one number, then find a pair for the rest.
 *   LeetCode 219 (ContainsDuplicate, SlidingWindow/): the same "have I seen this value?" lookup,
 *     limited to a window of k.
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> trackMap = new HashMap<>();
        int[] result = new int[2];
        for (int i=0; i<nums.length; i++) {
            int difference = target - nums[i];
            if(trackMap.containsKey(difference)) {
                result[0] = trackMap.get(difference);
                result[1] = i;
                return result;
            } else {
                trackMap.put(nums[i],i);
            }
        }
        return result;

    }


}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 350. Intersection of Two Arrays II
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 *
 * PROBLEM
 *   - Input: two integer arrays nums1 and nums2.
 *   - Return: the common values, each one as many times as it appears in both
 *     (the smaller of its two counts), in any order.
 *   - Constraints: lengths 1..1000, values 0..1000.
 *   - Follow-ups: arrays already sorted; nums1 much smaller; nums2 too big for memory.
 *
 * EXAMPLE
 *   Input: nums1 = [1,2,2,1], nums2 = [2,2]
 *   Output: [2,2]
 *
 *   Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 *   Output: [4,9]
 *   Explanation: [9,4] is also accepted.
 *
 * HOW TO THINK
 *   - Clue: "as many times as it appears in both" = count matters, not just presence. So a set
 *     isn't enough (LeetCode 349); use a HashMap value -> count.
 *   - Technique: count nums1 in a map (the supply). Walk nums2; each match spends one copy.
 *   - Rule that stays true: counts[v] = copies of v in nums1 not matched yet.
 *   - Decision: counts[num] > 0 -> a copy is left: add num, counts[num]--.
 *               otherwise      -> none left (or never in nums1): skip.
 *   - Return: the answer list as an int[].
 *   - Follow-up, both arrays sorted: two pointers, no map. Equal -> add, move both;
 *     otherwise move the pointer at the smaller value. O(1) extra space (see IntersectionOfTwoArrays2Sorting).
 *   - Follow-up, nums1 much smaller: build the map from the smaller array, so it stays small.
 *   - Follow-up, nums2 too big for memory: keep the map of nums1 and read nums2 in chunks.
 *
 * STEPS
 *   1. counts = how many times each value appears in nums1.
 *   2. Loop over every num in nums2.
 *   3. If counts[num] > 0: add num to the result, counts[num]--.
 *   4. Copy the result list into an int[] and return it.
 *
 * DRY RUN   nums1 = [4,9,5,9], nums2 = [9,4,9,8,4,9]
 *           (counts = {4:1, 9:2, 5:1} at the start; count and result after the step)
 *
 *    j | nums2[j] | count before | action       | count after | result
 *   ---+----------+--------------+--------------+-------------+---------
 *    0 |    9     |      2       | add, spend 1 |   9:1       | [9]
 *    1 |    4     |      1       | add, spend 1 |   4:0       | [9,4]
 *    2 |    9     |      1       | add, spend 1 |   9:0       | [9,4,9]
 *    3 |    8     |      0       | skip         |   -         | [9,4,9]
 *    4 |    4     |      0       | skip         |   4:0       | [9,4,9]
 *    5 |    9     |      0       | skip         |   9:0       | [9,4,9]
 *
 *   Returns [9,4,9]. 9 is twice in nums1 and three times in nums2, so it appears min(2,3) = 2 times.
 *
 * EDGE CASES
 *   [1,2,3], [4,5]          -> []         nothing in common
 *   [1,2,2,1], [2]          -> [2]        nums2 has fewer copies: min(2,1) = 1
 *   [2], [2,2,2]            -> [2]        nums1 has fewer copies: min(1,3) = 1
 *   [1,1,2], [1,1,2]        -> [1,1,2]    identical arrays: everything matches
 *   [0], [0]                -> [0]        0 is a normal value
 *
 * COMMON MISTAKES
 *   - Using a set like LeetCode 349: [1,2,2,1], [2,2] would give [2] instead of [2,2].
 *   - Not decreasing the count after a match: [2], [2,2,2] would give [2,2,2].
 *   - map.get(num) > 0 for a value not in the map: get returns null and unboxing throws.
 *     Use getOrDefault(num, 0).
 *
 * COMPLEXITY
 *   O(n + m) time, O(n) extra space (the map of nums1).
 *
 * RELATED
 *   LeetCode 350 (IntersectionOfTwoArrays2Sorting, HashMap/): the same problem by sorting both arrays
 *     and walking them with two pointers. No map, but O(n log n) and it changes the input.
 *   LeetCode 349 (IntersectionOfTwoArrays, HashMap/): each common value only once; a set is enough.
 *   LeetCode 383 (RansomNote, HashMap/): the same "count the supply, then spend it" map.
 */
public class IntersectionOfTwoArrays2 {

    public static void main(String[] args) {
        int[] nums1 = {4,9,5,9};
        int[] nums2 = {9,4,9,8,4,9};
        System.out.println(Arrays.toString(intersect(nums1, nums2)));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums1) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();
        for (int num : nums2) {
            int count = counts.getOrDefault(num, 0);
            if (count > 0) {
                result.add(num);
                counts.put(num, count - 1);
            }
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}

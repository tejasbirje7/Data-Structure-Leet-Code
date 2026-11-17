import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 349. Intersection of Two Arrays
 * https://leetcode.com/problems/intersection-of-two-arrays/
 *
 * PROBLEM
 *   - Input: two integer arrays nums1 and nums2.
 *   - Return: every value that appears in both arrays, each value only once, in any order.
 *   - Constraints: lengths 1..1000, values 0..1000.
 *
 * EXAMPLE
 *   Input: nums1 = [1,2,2,1], nums2 = [2,2]
 *   Output: [2]
 *
 *   Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 *   Output: [9,4]
 *   Explanation: [4,9] is also accepted.
 *
 * HOW TO THINK
 *   - Clue: "appears in both" = for each value of nums2, ask "is it in nums1?". A HashSet answers in O(1).
 *   - Clue: "each value only once" = once a value is in the answer, it must not be added again.
 *   - Technique: put nums1 in a set. Walk nums2; when a value is in the set, add it to the answer
 *     and remove it from the set, so its later copies are skipped.
 *   - Rule that stays true: the set holds the values of nums1 that are not in the answer yet.
 *   - Decision: set.remove(num) is true  -> first time this common value is seen: add it.
 *               set.remove(num) is false -> not in nums1, or already added: skip.
 *   - Return: the answer list as an int[].
 *
 * STEPS
 *   1. set = every value of nums1 (duplicates collapse).
 *   2. Loop over every num in nums2.
 *   3. If set.remove(num) returns true, add num to the result.
 *   4. Copy the result list into an int[] and return it.
 *
 * DRY RUN   nums1 = [4,9,5], nums2 = [9,4,9,8,4]   (set = {4,9,5} at the start; set and result after the step)
 *
 *    j | nums2[j] | in set?            | action          | set     | result
 *   ---+----------+--------------------+-----------------+---------+--------
 *    0 |    9     | yes                | remove 9, add 9 | {4,5}   | [9]
 *    1 |    4     | yes                | remove 4, add 4 | {5}     | [9,4]
 *    2 |    9     | no (already added) | skip            | {5}     | [9,4]
 *    3 |    8     | no (not in nums1)  | skip            | {5}     | [9,4]
 *    4 |    4     | no (already added) | skip            | {5}     | [9,4]
 *
 *   Returns [9,4]. 5 stays in the set: it is only in nums1.
 *
 * EDGE CASES
 *   [1,2,3], [4,5]          -> []        nothing in common
 *   [1,2,2,1], [2,2]        -> [2]       duplicates in both arrays, still one 2
 *   [3,1,2], [1,2,3]        -> [1,2,3]   same values, different order
 *   [0], [0]                -> [0]       0 is a normal value
 *   [7,7,7], [7]            -> [7]       only nums1 has duplicates
 *
 * COMMON MISTAKES
 *   - Adding every match without removing it from the set: [2,2] would give [2,2], not [2].
 *   - Two nested loops: correct but O(n * m), and you still need a way to skip repeats.
 *   - Returning a List<Integer>: LeetCode wants int[], so copy the values over.
 *
 * COMPLEXITY
 *   O(n + m) time, O(n) extra space (the set of nums1; the result has at most n values).
 *
 * RELATED
 *   LeetCode 350 (IntersectionOfTwoArrays2, HashMap/): keep duplicates as many times as they appear
 *     in both; a HashMap of counts instead of a set.
 *   LeetCode 217 (ContainsDuplicate, HashMap/): the same "is it in the set?" lookup, inside one array.
 */
public class IntersectionOfTwoArrays {

    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        System.out.println(Arrays.toString(intersection(nums1, nums2)));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }

        List<Integer> result = new ArrayList<>();
        for (int num : nums2) {
            if (set.remove(num)) {
                result.add(num);
            }
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}

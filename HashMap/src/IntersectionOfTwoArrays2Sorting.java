import java.util.Arrays;

/**
 * LeetCode 350. Intersection of Two Arrays II
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 *
 * PROBLEM
 *   - Input: two integer arrays nums1 and nums2.
 *   - Return: the common values, each one as many times as it appears in both
 *     (the smaller of its two counts), in any order.
 *   - Constraints: lengths 1..1000, values 0..1000.
 *   - This file: the sorting approach (LeetCode's follow-up "what if the arrays are already sorted?").
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
 *   - Clue: once both arrays are sorted, equal values line up in the same order in both,
 *     so you can match them by walking both arrays together, with no map.
 *   - Technique: sort both, then two pointers. i walks nums1, j walks nums2.
 *   - Rule that stays true: everything before i and before j is already handled
 *     (matched, or proven to have no partner left).
 *   - Decision: nums1[i] == nums2[j] -> a match: add it, move both (each copy is used once).
 *               nums1[i] <  nums2[j] -> nums1[i] is too small to match anything left in nums2
 *                                       (the rest of nums2 is >= nums2[j]): i++.
 *               nums1[i] >  nums2[j] -> same reason the other way: j++.
 *   - Stop when either pointer reaches its end: nothing left to match with.
 *   - Return: the matches, which come out in sorted order.
 *
 * STEPS
 *   1. Sort nums1 and nums2.
 *   2. i = 0, j = 0, result = int array of size min(n, m), k = 0 (how many matches so far).
 *   3. While i < n and j < m:
 *   4.   equal -> result[k++] = nums1[i], i++, j++.  nums1[i] smaller -> i++.  nums2[j] smaller -> j++.
 *   5. Return the first k values of result.
 *
 * DRY RUN   nums1 = [4,9,5,9], nums2 = [9,4,9,8,4,9]
 *           (sorted: nums1 = [4,5,9,9], nums2 = [4,4,8,9,9,9]; i, j and result after the step)
 *
 *    step | nums1[i] | nums2[j] | compare | action       | i | j | result
 *   ------+----------+----------+---------+--------------+---+---+---------
 *     1   |    4     |    4     |  equal  | add 4, both  | 1 | 1 | [4]
 *     2   |    5     |    4     |  5 > 4  | j++          | 1 | 2 | [4]
 *     3   |    5     |    8     |  5 < 8  | i++          | 2 | 2 | [4]
 *     4   |    9     |    8     |  9 > 8  | j++          | 2 | 3 | [4]
 *     5   |    9     |    9     |  equal  | add 9, both  | 3 | 4 | [4,9]
 *     6   |    9     |    9     |  equal  | add 9, both  | 4 | 5 | [4,9,9]
 *     -   |    -     |    -     |  i = 4 = n, stop
 *
 *   Returns [4,9,9]: the same values as the HashMap approach, but sorted.
 *   The last 9 in nums2 is never used: nums1 ran out of 9s.
 *
 * EDGE CASES
 *   [1,2,3], [4,5]          -> []         every step moves i; i runs out first
 *   [1,2], [100,200,300]    -> []         stops when nums1 runs out, the rest of nums2 is never read
 *   [1,2,2,1], [2]          -> [2]        nums2 has fewer copies: min(2,1) = 1
 *   [2], [2,2,2]            -> [2]        nums1 has fewer copies: min(1,3) = 1
 *   [1,1,2], [2,1,1]        -> [1,1,2]    same values in a different order: sorting lines them up
 *   [0], [0]                -> [0]        0 is a normal value
 *
 * COMMON MISTAKES
 *   - Forgetting to sort: the "smaller value can't match" reasoning only holds on sorted arrays.
 *   - Moving only one pointer on a match: the same copy is matched again.
 *     [2,2], [2] would give [2,2] instead of [2].
 *   - Moving the pointer at the bigger value: it skips a value that could still match.
 *   - Loop condition with || instead of &&: reads past the end of the shorter array.
 *
 * COMPLEXITY
 *   O(n log n + m log m) time for the sorts; the walk itself is O(n + m).
 *   O(1) extra space apart from the result and the sort; but it changes both input arrays.
 *   If the arrays are already sorted: O(n + m) time and O(1) extra space, beating the HashMap's O(n) space.
 *
 * RELATED
 *   LeetCode 350 (IntersectionOfTwoArrays2, HashMap/): the same problem with a HashMap of counts.
 *     No sorting and keeps the input unchanged, but O(n) extra space.
 *   LeetCode 349 (IntersectionOfTwoArrays, HashMap/): each common value only once.
 *     With this approach, skip a match equal to the last one added.
 *   LeetCode 88 (Merge, ArraysAndStrings/): the same "walk two sorted arrays together" pattern.
 */
public class IntersectionOfTwoArrays2Sorting {

    public static void main(String[] args) {
        int[] nums1 = {4,9,5,9};
        int[] nums2 = {9,4,9,8,4,9};
        System.out.println(Arrays.toString(intersect(nums1, nums2)));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0;
        int j = 0;
        int k = 0;
        int[] result = new int[Math.min(nums1.length, nums2.length)];

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result[k++] = nums1[i];
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOf(result, k);
    }
}

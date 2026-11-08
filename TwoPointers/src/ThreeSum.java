import java.util.*;

/**
 * LeetCode 15. 3Sum
 * https://leetcode.com/problems/3sum/
 *
 * PROBLEM
 *   - Input: an integer array nums.
 *   - Return: every triplet [nums[i], nums[j], nums[k]] with i, j, k all different and a sum of 0.
 *   - No duplicate triplets in the answer. The order of triplets (and inside them) doesn't matter.
 *
 * EXAMPLE
 *   Input: nums = [-1,0,1,2,-1,-4]
 *   Output: [[-1,-1,2],[-1,0,1]]
 *   Explanation:
 *   nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 *   nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 *   nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 *   The distinct triplets are [-1,0,1] and [-1,-1,2].
 *   Notice that the order of the output and the order
 *   of the triplets does not matter.
 *
 * HOW TO THINK
 *   - Clue: "three numbers that sum to 0" = fix one number, then find two others that sum to -nums[i].
 *     That inner part is Two Sum.
 *   - Technique: sort, then three pointers. i is fixed for each outer loop;
 *     left starts at i + 1 and right at the end, and they move towards each other (Two Sum II).
 *   - Why sorting helps: sum too small -> left++ makes it bigger; sum too big -> right-- makes it smaller.
 *     Sorted triplets also come out in the same order, so a Set can drop duplicates.
 *   - Rule that stays true: for this i, every pair outside [left, right] has already been checked.
 *   - Decision: sum == 0 -> add the triplet to the Set, then left++ and right-- (look for more pairs).
 *               sum <  0 -> left++.
 *               sum >  0 -> right--.
 *   - Return: the Set as a list (duplicates already removed).
 *
 * STEPS
 *   1. Sort nums; make an empty Set of triplets.
 *   2. For each i: left = i + 1, right = n - 1.
 *   3. While left < right: sum = nums[i] + nums[left] + nums[right].
 *   4.   0 -> add [nums[i], nums[left], nums[right]], left++, right--.  < 0 -> left++.  > 0 -> right--.
 *   5. Return new ArrayList<>(set).
 *
 * DRY RUN   nums = [-1,0,1,2,-1,-4]  ->  sorted [-4,-1,-1,0,1,2]   (set is the value after the row)
 *
 *    i | nums[i] | left | right | nums[left] | nums[right] | sum | action               | set
 *   ---+---------+------+-------+------------+-------------+-----+----------------------+---------------------
 *    0 |   -4    |  1   |   5   |     -1     |      2      |  -3 | < 0, left++          | {}
 *    0 |   -4    |  2   |   5   |     -1     |      2      |  -3 | < 0, left++          | {}
 *    0 |   -4    |  3   |   5   |      0     |      2      |  -2 | < 0, left++          | {}
 *    0 |   -4    |  4   |   5   |      1     |      2      |  -1 | < 0, left++          | {}
 *    1 |   -1    |  2   |   5   |     -1     |      2      |   0 | add, left++, right-- | {[-1,-1,2]}
 *    1 |   -1    |  3   |   4   |      0     |      1      |   0 | add, left++, right-- | {[-1,-1,2],[-1,0,1]}
 *    2 |   -1    |  3   |   5   |      0     |      2      |   1 | > 0, right--         | {[-1,-1,2],[-1,0,1]}
 *    2 |   -1    |  3   |   4   |      0     |      1      |   0 | duplicate, Set skips | {[-1,-1,2],[-1,0,1]}
 *    3 |    0    |  4   |   5   |      1     |      2      |   3 | > 0, right--         | {[-1,-1,2],[-1,0,1]}
 *    4, 5: left >= right, the inner loop never runs.
 *
 *   Returns [[-1,-1,2],[-1,0,1]] (HashSet order; any order is accepted).
 *
 * EDGE CASES
 *   [0,1,1]        -> []          the only triplet sums to 2
 *   [0,0,0]        -> [[0,0,0]]
 *   [0,0,0,0]      -> [[0,0,0]]   found more than once; the Set keeps one
 *   [-2,0,1,1,2]   -> [[-2,0,2],[-2,1,1]]   two triplets for the same i (any order)
 *   [1,2]          -> []          fewer than 3 numbers, the inner loop never runs
 *   []             -> []
 *
 * COMMON MISTAKES
 *   - Not sorting first: moving left/right only makes sense on a sorted array.
 *   - Stopping after the first match for an i: there can be more pairs. Move both pointers and keep going.
 *   - Duplicate triplets: [-1,0,1] is found for i = 1 and i = 2. Use a Set (as here) or skip equal
 *     neighbours for i, left and right (as in FAQ/ThreeSum).
 *   - Trying all triples: O(n^3), too slow for 3000 numbers.
 *
 * COMPLEXITY
 *   O(n^2) time (sort O(n log n), then an O(n) two-pointer pass for each i).
 *   Extra space: the Set of answers, plus the sort's own space. Note: nums is sorted in place.
 *
 * RELATED
 *   LeetCode 15 (ThreeSum, FAQ/): same problem, also in FAQ/; skips duplicate values instead of using a Set.
 *   LeetCode 16 (ThreeSumClosest, FAQ/): same loops, but tracks the sum closest to a target.
 *   LeetCode 167 (TwoSum, TwoPointers/): the inner left/right loop on its own.
 *   LeetCode 1 (TwoSum, HashMap/): unsorted Two Sum, solved with a hash map.
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        int target = 0;
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();

        for (int i=0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if(sum == target) {
                    set.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return new ArrayList<>(set);

    }
}

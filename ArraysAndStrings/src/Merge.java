
import java.util.Arrays;

/**
 * LeetCode 88. Merge Sorted Array
 * https://leetcode.com/problems/merge-sorted-array/
 *
 * PROBLEM
 *   - Input: two sorted arrays nums1 and nums2, with counts m and n.
 *   - nums1 has length m + n: the first m slots are real values, the last n slots are 0 (empty space).
 *   - Task: merge nums2 into nums1 in place, so nums1 ends up sorted.
 *   - Return: nothing; nums1 itself holds the answer.
 *
 * EXAMPLE
 *   Input:
 *   nums1 = [1,2,3,0,0,0], m = 3,
 *   nums2 = [2,5,6], n = 3
 *
 *   Output: [1,2,2,3,5,6]
 *
 * HOW TO THINK
 *   - Clue: "in place" + the empty space sits at the END of nums1.
 *     Filling from the front would overwrite nums1 values you haven't read yet.
 *   - Technique: three pointers, filling from the back.
 *     m - 1 = last unread value of nums1, n - 1 = last unread value of nums2, last = slot to fill.
 *   - Rule that stays true: nums1[last+1 .. end] holds the biggest values, already sorted and final.
 *     last never passes m - 1, so no unread nums1 value is ever overwritten.
 *   - Decision: nums1[m-1] > nums2[n-1] -> put nums1[m-1] at last, m--.
 *               otherwise (smaller or equal) -> put nums2[n-1] at last, n--.
 *               Either way, last--.
 *   - After the loop: if nums2 still has values, copy them in. Leftover nums1 values are already in place.
 *   - Return: nothing, nums1 is changed in place.
 *
 * STEPS
 *   1. last = m + n - 1.
 *   2. While m > 0 and n > 0: put the bigger of nums1[m-1] and nums2[n-1] at nums1[last].
 *   3. Move back the pointer you took from (m-- or n--), then last--.
 *   4. While n > 0: nums1[last] = nums2[n-1], then n-- and last-- (copies the rest of nums2, biggest first).
 *
 * DRY RUN   nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3   (m, n, nums1 are after the step)
 *
 *    step | nums1[m-1] | nums2[n-1] | last | action                     | m | n | nums1
 *   ------+------------+------------+------+----------------------------+---+---+---------------
 *     1   |     3      |     6      |  5   | 3 < 6, nums1[5] = 6, n--   | 3 | 2 | [1,2,3,0,0,6]
 *     2   |     3      |     5      |  4   | 3 < 5, nums1[4] = 5, n--   | 3 | 1 | [1,2,3,0,5,6]
 *     3   |     3      |     2      |  3   | 3 > 2, nums1[3] = 3, m--   | 2 | 1 | [1,2,3,3,5,6]
 *     4   |     2      |     2      |  2   | equal, nums1[2] = 2, n--   | 2 | 0 | [1,2,2,3,5,6]
 *
 *   n = 0, so the tail loop doesn't run. Final nums1: [1,2,2,3,5,6].
 *
 * EDGE CASES
 *   [1], m = 1, [], n = 0                 -> [1]             nothing to merge, both loops skip
 *   [1,2,3,0,0,0], m = 3, [4,5,6], n = 3  -> [1,2,3,4,5,6]   every step takes from nums2; tail loop not needed
 *   [2,2,0,0], m = 2, [2,2], n = 2        -> [2,2,2,2]       ties take nums2 first; order of equal values doesn't matter
 *   [0], m = 0, [1], n = 1                -> [1]             no real nums1 values; main loop skips, tail loop copies 1
 *   [4,5,6,0,0,0], m = 3, [1,2,3], n = 3  -> [1,2,3,4,5,6]   nums1 runs out first; tail loop copies 3,2,1 into slots 2,1,0
 *
 * COMMON MISTAKES
 *   - Merging from the front: overwrites nums1 values before they are read. Fill from the back.
 *   - Off-by-one in the tail loop: m and n are counts, so the last unread value is nums2[n-1], not nums2[n].
 *     Reading nums2[n] crashes (ArrayIndexOutOfBounds) whenever nums1 runs out first.
 *   - Copying leftover nums1 values after the main loop: not needed, they are already in their final slots.
 *   - Sorting nums1 after copying nums2 in: works, but O((m + n) log(m + n)) instead of O(m + n).
 *
 * COMPLEXITY
 *   O(m + n) time (each value is placed once), O(1) extra space.
 *
 * RELATED
 *   LeetCode 21 (MergeTwoLinkedList, LinkedList/): merge two sorted linked lists.
 *     Nodes are relinked, not copied, so you can go front to back without overwriting anything.
 */
public class Merge {

    public static void main(String[] args) {
        int[] m = {1,2,3,0,0,0};
        int[] n = {2,5,6};
        merge(m,3,n,3);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int last = m + n - 1;

        while (m > 0 && n > 0) {
            if(nums1[m-1] > nums2[n-1]) {
                nums1[last] = nums1[m-1];
                m--;
            } else {
                nums1[last] = nums2[n-1];
                n--;
            }
            last--;
        }

        while (n > 0) {
            nums1[last] = nums2[n-1];
            n--;
            last--;
        }
        System.out.println(Arrays.toString(nums1));
    }
}
/**
 * LeetCode 4. Median of Two Sorted Arrays
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * PROBLEM
 *   - Input: two sorted arrays nums1 (size m) and nums2 (size n); m + n >= 1, either may be empty.
 *   - Return: the median of all m + n values together (a double).
 *   - Constraint: O(log(m + n)) time, so merging the two arrays (O(m + n)) is not allowed.
 *
 * EXAMPLE
 *   Input: nums1 = [1,3], nums2 = [2]
 *   Output: 2.00000
 *   Explanation: merged array = [1,2,3] and median is 2.
 *
 * HOW TO THINK
 *   - Clue: "sorted" + "log" = binary search. But search for a cut, not for a value.
 *   - Technique: the median splits the merged values into a left half and a right half.
 *     Take i values from the front of nums1 and j = half - i from the front of nums2, where
 *     half = (m + n + 1) / 2 (the left half gets the extra value when the total is odd).
 *     The cut is right when every left value <= every right value. Both arrays are sorted, so
 *     only the values next to the cuts need checking:
 *       aLeft = nums1[i - 1], aRight = nums1[i], bLeft = nums2[j - 1], bRight = nums2[j]
 *       (a cut at an end uses -infinity on the left or +infinity on the right).
 *   - Binary search i over 0..m, on the SHORTER array, so j = half - i never goes out of range.
 *   - Rule that stays true: the correct i is inside [lo, hi].
 *   - Decision: aLeft <= bRight and bLeft <= aRight -> correct cut, compute the median.
 *               aLeft > bRight -> took too many from nums1: hi = i - 1.
 *               bLeft > aRight -> took too few from nums1:  lo = i + 1.
 *   - Return: odd total -> max(aLeft, bLeft).
 *             even total -> (max(aLeft, bLeft) + min(aRight, bRight)) / 2.0.
 *
 * STEPS
 *   1. If nums1 is longer, swap so nums1 is the shorter one.
 *   2. half = (m + n + 1) / 2, lo = 0, hi = m.
 *   3. While lo <= hi: i = lo + (hi - lo) / 2, j = half - i; read the four edge values.
 *   4. Correct cut -> return the median. aLeft > bRight -> hi = i - 1. Else -> lo = i + 1.
 *
 * DRY RUN   nums1 = [2,5,6,30,40], nums2 = [1,3,4,7,8,9]   (m = 5, n = 6, total 11, half = 6)
 *           merged = [1,2,3,4,5,6,7,8,9,30,40], so the median is the 6th value, 6.
 *           (lo and hi are values before the step's action)
 *
 *    lo | hi | i | j | aLeft | aRight | bLeft | bRight | check            | action
 *   ----+----+---+---+-------+--------+-------+--------+------------------+------------
 *     0 |  5 | 2 | 4 |   5   |    6   |   7   |    8   | bLeft 7 > 6      | lo = 3
 *     3 |  5 | 4 | 2 |  30   |   40   |   3   |    4   | aLeft 30 > 4     | hi = 3
 *     3 |  3 | 3 | 3 |   6   |   30   |   4   |    7   | 6 <= 7, 4 <= 30  | found
 *
 *   Odd total, so it returns max(6, 4) = 6.0.
 *   Left half = [2,5,6] + [1,3,4], right half = [30,40] + [7,8,9].
 *
 * EDGE CASES
 *   [1,3], [2]        -> 2.0   official example 1
 *   [1,2], [3,4]      -> 2.5   official example 2: even total, average of 2 and 3
 *   [], [1]           -> 1.0   nums1 empty: i = 0, aLeft = -inf, aRight = +inf
 *   [2], []           -> 2.0   swapped so the empty array is nums1
 *   [1,1], [1,1]      -> 1.0   all equal: the first cut tried already works
 *   [10,20,30,40], [1,2,3,4,5] -> 5.0   every nums1 value is bigger: ends with i = 0
 *
 * COMMON MISTAKES
 *   - Searching on the longer array: j = half - i can be negative or past nums2's end. Swap first.
 *   - Forgetting the infinity values: a cut at the very start or end reads outside the array.
 *   - Integer division for the even case: (a + b) / 2 drops the .5; divide by 2.0.
 *   - half = (m + n) / 2 while returning max(aLeft, bLeft) for odd totals: the middle value then
 *     sits in the right half. Use (m + n + 1) / 2.
 *
 * COMPLEXITY
 *   O(log(min(m, n))) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 88 (Merge, ArraysAndStrings/): merging two sorted arrays gives the median in
 *     O(m + n), the simple but too-slow approach here.
 *   LeetCode 295 (MedianFromDataStream, Heap/): the same "left half / right half" idea, kept
 *     with two heaps as values arrive.
 *   LeetCode 704 (BinarySearch, BinarySearch/): the plain search this one builds on.
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int half = (m + n + 1) / 2;
        int lo = 0;
        int hi = m;
        while (lo <= hi) {
            int i = lo + (hi - lo) / 2;
            int j = half - i;
            int aLeft = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int aRight = i == m ? Integer.MAX_VALUE : nums1[i];
            int bLeft = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int bRight = j == n ? Integer.MAX_VALUE : nums2[j];
            if (aLeft <= bRight && bLeft <= aRight) {
                if ((m + n) % 2 == 1) {
                    return Math.max(aLeft, bLeft);
                }
                return (Math.max(aLeft, bLeft) + (long) Math.min(aRight, bRight)) / 2.0;
            } else if (aLeft > bRight) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
        throw new IllegalArgumentException("input arrays are not sorted");
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays sol = new MedianOfTwoSortedArrays();

        System.out.println(sol.findMedianSortedArrays(new int[]{2, 5, 6, 30, 40}, new int[]{1, 3, 4, 7, 8, 9})); // Expected: 6.0
        System.out.println(sol.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));                          // Expected: 2.0
        System.out.println(sol.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));                       // Expected: 2.5
        System.out.println(sol.findMedianSortedArrays(new int[]{}, new int[]{1}));                              // Expected: 1.0
        System.out.println(sol.findMedianSortedArrays(new int[]{2}, new int[]{}));                              // Expected: 2.0
        System.out.println(sol.findMedianSortedArrays(new int[]{1, 1}, new int[]{1, 1}));                       // Expected: 1.0
        System.out.println(sol.findMedianSortedArrays(new int[]{10, 20, 30, 40}, new int[]{1, 2, 3, 4, 5}));    // Expected: 5.0
    }
}

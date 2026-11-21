/**
 * LeetCode 162. Find Peak Element
 * https://leetcode.com/problems/find-peak-element/
 *
 * PROBLEM
 *   - Input: an array nums where neighbours are never equal (nums[i] != nums[i + 1]).
 *   - Return: the index of any peak, i.e. a value strictly greater than both neighbours.
 *   - Outside the array counts as -infinity, so nums[0] and nums[n - 1] only need to beat
 *     their one real neighbour.
 *   - Constraint: O(log n) time. The array is NOT sorted.
 *
 * EXAMPLE
 *   Input: nums = [1,2,3,1]
 *   Output: 2
 *   Explanation: 3 is a peak element and your function should return the index number 2.
 *
 * HOW TO THINK
 *   - Clue: "O(log n)" on an unsorted array = binary search on a property, not on values.
 *   - Technique: compare mid with its right neighbour.
 *       nums[mid] < nums[mid + 1] -> going uphill to the right. Keep climbing: since the far
 *         end drops to -infinity, the slope must turn down somewhere, so a peak is to the right.
 *       nums[mid] > nums[mid + 1] -> going downhill. mid itself may be the peak, or one is to the left.
 *   - Rule that stays true: [lo, hi] always contains a peak.
 *   - Decision: uphill -> lo = mid + 1. Downhill -> hi = mid (keep mid, it may be the peak).
 *   - Return: when lo == hi the range is one index, and it is a peak.
 *   - Why lo < hi (not <=): the loop stops at one index, and mid + 1 is always inside the array.
 *
 * STEPS
 *   1. lo = 0, hi = n - 1.
 *   2. While lo < hi: mid = lo + (hi - lo) / 2.
 *   3. nums[mid] < nums[mid + 1] -> lo = mid + 1, else hi = mid.
 *   4. Return lo.
 *
 * DRY RUN   nums = [1,2,1,3,5,6,4]   (lo and hi are values before the step's action)
 *
 *    lo | hi | mid | nums[mid] | nums[mid+1] | slope    | action
 *   ----+----+-----+-----------+-------------+----------+--------
 *     0 |  6 |  3  |     3     |      5      | uphill   | lo = 4
 *     4 |  6 |  5  |     6     |      4      | downhill | hi = 5
 *     4 |  5 |  4  |     5     |      6      | uphill   | lo = 5
 *     5 |  5 |  -  |     -     |      -      | lo == hi | stop
 *
 *   Returns 5 (value 6). Index 1 (value 2) is also a valid peak; LeetCode accepts either.
 *
 * EDGE CASES
 *   [1,2,3,1]       -> 2   official example
 *   [1]             -> 0   one element: both sides are -infinity
 *   [2,1]           -> 0   first element is the peak
 *   [1,2]           -> 1   last element is the peak
 *   [1,2,3,4,5]     -> 4   always uphill: the peak is the last index
 *   [5,4,3,2,1]     -> 0   always downhill: the peak is the first index
 *
 * COMMON MISTAKES
 *   - hi = mid - 1 on downhill: mid may be the only peak, e.g. [1,3,2] would skip index 1.
 *   - Loop condition lo <= hi: with hi = mid it never ends, and mid + 1 can run past the array.
 *   - Thinking it must return the highest peak: any peak is accepted, and the search only
 *     promises a local one (here 5, not necessarily the global max).
 *
 * COMPLEXITY
 *   O(log n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 852 (PeakIndexInMountainArray, FAQ/): exactly one peak; the same uphill/downhill test.
 *   LeetCode 153 (MinInRotatedSortedArray, BinarySearch/): the same lo < hi / hi = mid shape,
 *     but compares mid with the right end instead of the next value.
 */
public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < nums[mid + 1]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        FindPeakElement sol = new FindPeakElement();

        System.out.println(sol.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4})); // Expected: 5
        System.out.println(sol.findPeakElement(new int[]{1, 2, 3, 1}));          // Expected: 2
        System.out.println(sol.findPeakElement(new int[]{1}));                   // Expected: 0
        System.out.println(sol.findPeakElement(new int[]{2, 1}));                // Expected: 0
        System.out.println(sol.findPeakElement(new int[]{1, 2}));                // Expected: 1
        System.out.println(sol.findPeakElement(new int[]{1, 2, 3, 4, 5}));       // Expected: 4
        System.out.println(sol.findPeakElement(new int[]{5, 4, 3, 2, 1}));       // Expected: 0
    }
}

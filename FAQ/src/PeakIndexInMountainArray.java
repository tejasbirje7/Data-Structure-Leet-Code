/**
 * LeetCode 852. Peak Index in a Mountain Array
 * https://leetcode.com/problems/peak-index-in-a-mountain-array/
 *
 * PROBLEM
 *   - Input: a mountain array: values strictly go up, then strictly go down (length >= 3).
 *   - Return: the index of the peak (the largest value).
 *   - Must run in O(log n) time.
 *
 * EXAMPLE
 *   Input: arr = [0,1,0]
 *   Output: 1
 *
 * HOW TO THINK
 *   - Clue: "O(log n)" = binary search. Comparing arr[mid] with arr[mid + 1]
 *     tells you which slope mid is on, so half the array can be thrown away.
 *   - Technique: binary search on the slope direction (no target value).
 *   - Rule that stays true: the peak is always inside [start, end].
 *   - Decision: arr[mid] < arr[mid + 1] -> going up, the peak is to the right: start = mid + 1.
 *               otherwise -> going down or at the peak: end = mid (mid may be the peak, keep it).
 *   - Return: when start == end, that index is the peak.
 *   - mid + 1 is always safe: start < end means mid < end.
 *
 * STEPS
 *   1. start = 0, end = n - 1.
 *   2. While start < end: mid = start + (end - start) / 2.
 *   3.   If arr[mid] < arr[mid + 1], start = mid + 1; else end = mid.
 *   4. Return start.
 *
 * DRY RUN   arr = [0,10,15,2]   (start and end are values after the step)
 *
 *    step | mid | arr[mid] vs arr[mid + 1] | action         | start | end
 *   ------+-----+--------------------------+----------------+-------+-----
 *      0  |  -  | -                        | init           |   0   |  3
 *      1  |  1  | 10 < 15, going up        | start = mid+1  |   2   |  3
 *      2  |  2  | 15 > 2, going down       | end = mid      |   2   |  2
 *
 *   start == end = 2, loop stops. Returns 2 (value 15).
 *
 * EDGE CASES
 *   [0,1,0]         -> 1   smallest mountain
 *   [0,2,1,0]       -> 1   first step goes left (end = mid), then right
 *   [1,2,3,4,0]     -> 3   peak right before the end
 *   [0,5,4,3,2]     -> 1   peak right after the start
 *   [3,2,1]         -> 0   not a mountain (only going down): returns the first index
 *   []              -> 0   end = -1, loop never runs; LeetCode guarantees length >= 3
 *
 * COMMON MISTAKES
 *   - end = mid - 1 in the "going down" case: if mid is the peak, it is thrown away.
 *   - while (start <= end) with end = mid: when start == end the loop never ends.
 *   - Comparing with arr[mid - 1]: out of range when mid = 0. Compare with arr[mid + 1] instead.
 *   - A linear scan for the max: correct, but O(n), and the problem asks for O(log n).
 *
 * COMPLEXITY
 *   O(log n) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 162 Find Peak Element (not in this repo): same slope test, but any local peak is accepted.
 *   LeetCode 33 (SearchInRotatedDistinctArray, FAQ/): binary search that first decides which half is sorted.
 *   LeetCode 81 (SearchInRotatedDuplicateArray, FAQ/): the same with duplicates allowed.
 *   LeetCode 69 (SqrRtx, Math/): binary search on the answer instead of on an index.
 */
public class PeakIndexInMountainArray {
    public int peakIndexInMountainArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] < arr[mid + 1]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        PeakIndexInMountainArray sol = new PeakIndexInMountainArray();
        System.out.println(sol.peakIndexInMountainArray(new int[]{0, 1, 0}));
        System.out.println(sol.peakIndexInMountainArray(new int[]{0, 2, 1, 0}));
        System.out.println(sol.peakIndexInMountainArray(new int[]{0, 10, 15, 2}));
    }
}

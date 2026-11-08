/**
 * LeetCode 69. Sqrt(x)
 * https://leetcode.com/problems/sqrtx/
 *
 * PROBLEM
 *   - Input: a non-negative int x (0 to 2^31 - 1).
 *   - Return: the square root of x rounded down to a whole number.
 *   - No built-in power or square root (no pow(x, 0.5), no x ** 0.5).
 *
 * EXAMPLE
 *   Input: x = 4
 *   Output: 2
 *   Explanation: The square root of 4 is 2, so we return 2.
 *
 * HOW TO THINK
 *   - Clue: "rounded down" + the answer is somewhere in 1..x and mid * mid grows with mid
 *     = binary search on the answer.
 *   - Technique: guess mid; if mid * mid is too big, the answer is to the left; if too small, to the right.
 *   - Rule that stays true: every number left of start squares to <= x;
 *     every number right of end squares to > x.
 *   - Decision: mid * mid > x  -> too big: end = mid - 1.
 *               mid * mid == x -> exact root: return mid.
 *               mid * mid < x  -> could be the answer, look right: start = mid + 1.
 *   - Return: when start passes end, end is the largest number whose square is <= x.
 *     Math.round(end) just returns end (end is already a whole number).
 *   - Overflow care: mid = start + (end - start) / 2 (not (start + end) / 2), and the square is
 *     compared as long. The int mid * mid == x check runs only after the long check says
 *     mid * mid <= x, so it can't overflow.
 *   - x = 0 and x = 1 are returned at once.
 *
 * STEPS
 *   1. If x is 0 or 1, return x.
 *   2. start = 1, end = x.
 *   3. While start <= end: mid = start + (end - start) / 2.
 *   4.   (long) mid * mid > x -> end = mid - 1; == x -> return mid; else start = mid + 1.
 *   5. Return end.
 *
 * DRY RUN   x = 9   (start and end are values after the step; hits all three branches)
 *
 *    step | start | end | mid | mid * mid | action
 *   ------+-------+-----+-----+-----------+------------------------
 *      1  |   1   |  4  |  5  |    25     | 25 > 9, end = 4
 *      2  |   3   |  4  |  2  |     4     | 4 < 9, start = 3
 *      3  |   3   |  4  |  3  |     9     | 9 == 9, return 3
 *
 *   Returns 3. For x = 8 the loop instead ends with start = 3 > end = 2 and returns end = 2.
 *
 * EDGE CASES
 *   x = 0                 -> 0       special case
 *   x = 1                 -> 1       special case
 *   x = 2, x = 3          -> 1       loop ends, end = 1
 *   x = 8                 -> 2       the LeetCode example 2: not a perfect square, returns end
 *   x = 15                -> 3       just below a square
 *   x = 16                -> 4       perfect square, found by the == branch
 *   x = Integer.MAX_VALUE -> 46340   long compare avoids overflow (46341^2 > 2^31 - 1)
 *   x = 2147395600        -> 46340   46340^2 exactly
 *   x = -4                -> -4      loop never runs (end < start), returns end = x;
 *                                    LeetCode guarantees x >= 0
 *   main is empty, so running the class prints nothing.
 *
 * COMMON MISTAKES
 *   - mid * mid in int: overflows for mid > 46340 and gives wrong comparisons. Cast to long
 *     (or compare mid > x / mid).
 *   - Returning start instead of end: start is one past the answer when the loop ends.
 *   - (start + end) / 2: can overflow when both are near 2^31; use start + (end - start) / 2.
 *   - Loop condition start < end with end = mid - 1: can skip the answer. Keep start <= end here.
 *
 * COMPLEXITY
 *   O(log x) time, O(1) extra space.
 *
 * RELATED
 *   LeetCode 852 (PeakIndexInMountainArray, FAQ/): binary search where the check is a slope, not a square.
 *   LeetCode 33 (SearchInRotatedDistinctArray, FAQ/): binary search on a rotated sorted array.
 *   LeetCode 50 (Pow, Math/): another O(log) loop that halves n each step.
 */
public class SqrRtx {
    public static void main(String[] args) {

    }

    public static int mySqrt(int x) {
        if (x==0 || x==1) return x;

        int start = 1;
        int end = x;
        int mid = -1;

        while (start <= end) {

            mid = start + (end - start) / 2;

            if ((long) mid * mid > (long) x) {
                end = mid -1;
            } else if (mid * mid == x) {
                return mid;
            } else {
                start = mid + 1;
            }
        }

        return Math.round(end);
    }
}

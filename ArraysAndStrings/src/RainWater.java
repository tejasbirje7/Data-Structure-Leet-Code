/**
 * LeetCode 42. Trapping Rain Water
 * https://leetcode.com/problems/trapping-rain-water/
 *
 * PROBLEM
 *   - Input: height[i] = height of a bar of width 1 (an elevation map).
 *   - Return: how many units of rain water are trapped between the bars.
 *   - Water above a bar = min(tallest bar on its left, tallest bar on its right) - its own height.
 *
 * EXAMPLE
 *   Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 *   Output: 6
 *   Explanation: The above elevation map (black section) is
 *   represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this
 *   case, 6 units of rain water (blue section) are being trapped.
 *
 *   Input: height = [4,2,0,3,2,5]
 *   Output: 9
 *
 * HOW TO THINK
 *   - Clue: water at a bar depends on the tallest wall on EACH side, and the shorter of the two wins.
 *   - Technique: two pointers from both ends, each with a running max
 *     (leftCounter = tallest bar seen from the left, rightCounter = tallest from the right).
 *   - Rule that stays true: leftCounter = max(height[0..l]), rightCounter = max(height[r..n-1]).
 *   - Decision: leftCounter < rightCounter -> the left side is the limit: l++, raise leftCounter if
 *                 height[l] is taller, add leftCounter - height[l].
 *               otherwise (smaller or equal) -> same on the right side: r--, raise rightCounter, add
 *                 rightCounter - height[r].
 *   - Why it works: when you move the smaller side, the other side already has a wall at least
 *     as tall, so the smaller max alone decides the water at the new cell.
 *   - Math.max(..., 0) is only a guard: the counter was just raised to at least the bar's height.
 *   - Return: the total water once the pointers meet.
 *
 * STEPS
 *   1. l = 0, r = n - 1, leftCounter = height[0], rightCounter = height[n-1], water = 0.
 *   2. While l < r:
 *   3.   If leftCounter < rightCounter: l++, leftCounter = max(leftCounter, height[l]),
 *        water += leftCounter - height[l].
 *   4.   Else: r--, rightCounter = max(rightCounter, height[r]), water += rightCounter - height[r].
 *   5. Return water.
 *
 * DRY RUN   height = [0,1,0,2,1,0,1,3,2,1,2,1]
 *   Start: l = 0, r = 11, leftCounter = 0, rightCounter = 1, water = 0.
 *   (lc vs rc is checked before the move; everything else is after the step.)
 *
 *    step | lc vs rc | move    | l  | r  | height | leftCounter | rightCounter | added | water
 *   ------+----------+---------+----+----+--------+-------------+--------------+-------+-------
 *      1  |  0 < 1   | l++     |  1 | 11 |   1    |   1 (new)   |      1       |   0   |   0
 *      2  |  1 = 1   | r--     |  1 | 10 |   2    |      1      |   2 (new)    |   0   |   0
 *      3  |  1 < 2   | l++     |  2 | 10 |   0    |      1      |      2       |   1   |   1
 *      4  |  1 < 2   | l++     |  3 | 10 |   2    |   2 (new)   |      2       |   0   |   1
 *      5  |  2 = 2   | r--     |  3 |  9 |   1    |      2      |      2       |   1   |   2
 *      6  |  2 = 2   | r--     |  3 |  8 |   2    |      2      |      2       |   0   |   2
 *      7  |  2 = 2   | r--     |  3 |  7 |   3    |      2      |   3 (new)    |   0   |   2
 *      8  |  2 < 3   | l++     |  4 |  7 |   1    |      2      |      3       |   1   |   3
 *      9  |  2 < 3   | l++     |  5 |  7 |   0    |      2      |      3       |   2   |   5
 *     10  |  2 < 3   | l++     |  6 |  7 |   1    |      2      |      3       |   1   |   6
 *     11  |  2 < 3   | l++     |  7 |  7 |   3    |   3 (new)   |      3       |   0   |   6
 *
 *   l == r, loop ends. Returns 6.
 *
 * EDGE CASES
 *   [5]              -> 0   l == r from the start, the loop never runs
 *   [4,2,0,3,2,5]    -> 9   LeetCode example 2
 *   [1,2,3,4]        -> 0   only rising: every bar is a new max, nothing is trapped
 *   [2,2,2]          -> 0   flat: every difference is 0
 *   [3,0,3]          -> 3   one pit between two equal walls
 *   []               -> crashes on height[0] (ArrayIndexOutOfBounds); LeetCode guarantees at least one bar
 *
 * COMMON MISTAKES
 *   - Using only the left max (or only the right max): water depends on the SMALLER of the two.
 *   - Adding water before raising the counter: a taller bar would give negative water.
 *     Update the counter first (or clamp with max(..., 0)).
 *   - Moving the pointer on the taller side: you can't know the water there yet,
 *     because the limit is on the other side.
 *   - Scanning left and right for every bar: correct but O(n^2).
 *
 * COMPLEXITY
 *   O(n) time (each bar is visited once), O(1) extra space.
 *
 * RELATED
 *   LeetCode 11 (MostWaterInContainer, TwoPointers/): also two pointers that move the shorter side,
 *     but picks just two lines for the biggest area; bars in between don't matter.
 *   LeetCode 238 (ProductExceptSelf, ArraysAndStrings/): same "something from the left, something from
 *     the right" idea; the array version of this problem stores leftMax[] and rightMax[] the same way.
 */
public class RainWater {

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }

    public static int trap(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int leftCounter = height[l];
        int rightCounter = height[r];
        int water = 0;

        while (l < r) {

            if(leftCounter < rightCounter) {
                l += 1;

                if(height[l] > leftCounter) {
                    leftCounter = height[l];
                }

                water += Math.max(leftCounter - height[l], 0);
            } else {
                r -= 1;

                if (height[r] > rightCounter) {
                    rightCounter = height[r];
                }

                water += Math.max(rightCounter - height[r],0);
            }


        }
        return water;
    }

    int[] lc = { 0,1 };
    int[] rc = { 1,2 };
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * LeetCode 452. Minimum Number of Arrows to Burst Balloons
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 *
 * PROBLEM
 *   - Input: points[i] = [xstart, xend], the horizontal range of balloon i.
 *   - An arrow shot straight up at x bursts every balloon with xstart <= x <= xend.
 *   - Return: the fewest arrows needed to burst all balloons.
 *   - Values can be anywhere in the int range, so watch for overflow.
 *
 * EXAMPLE
 *   Input: points = [[10,16],[2,8],[1,6],[7,12]]
 *   Output: 2
 *   Explanation: The balloons can be burst by 2 arrows:
 *   - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
 *   - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
 *
 * HOW TO THINK
 *   - Clue: one arrow bursts a group of balloons only if they all share a common point.
 *   - Technique: greedy after sorting by start. Build groups left to right;
 *     a group's shared part ends at minEnd, the smallest end in the group.
 *   - Rule that stays true: minEnd = smallest end in the current group;
 *     arrows = number of groups already closed.
 *   - Decision: minEnd < start -> this balloon starts after the shared part: close the group
 *                                 (arrows++) and start a new one with minEnd = its end.
 *               otherwise      -> it joins the group: minEnd = min(minEnd, end).
 *   - Return: arrows + 1, because the last group is still open and needs its arrow.
 *
 * STEPS
 *   1. Sort points by start.
 *   2. arrows = 0, minEnd = Integer.MAX_VALUE (so the first balloon always joins).
 *   3. For each point: if minEnd < start, arrows++ and minEnd = end; else minEnd = min(minEnd, end).
 *   4. Return arrows + 1.
 *
 * DRY RUN   points = [[10,16],[2,8],[1,6],[7,12]]
 *   After sorting (the method prints this): [[1,6],[2,8],[7,12],[10,16]]
 *   (minEnd in "check" is the value before the step; arrows and minEnd are after it)
 *
 *    point   | check         | action                  | arrows | minEnd
 *   ---------+---------------+-------------------------+--------+--------
 *    [1,6]   | MAX < 1 no    | join, minEnd = 6        |   0    |   6
 *    [2,8]   | 6 < 2 no      | join, minEnd = min(6,8) |   0    |   6
 *    [7,12]  | 6 < 7 yes     | close group, new group  |   1    |   12
 *    [10,16] | 12 < 10 no    | join, minEnd = 12       |   1    |   12
 *
 *   Returns arrows + 1 = 2: one arrow at x = 6, one anywhere in [10,12].
 *
 * EDGE CASES
 *   [[1,2],[3,4],[5,6],[7,8]]   -> 4   no balloons overlap
 *   [[1,2],[2,3],[3,4],[4,5]]   -> 2   touching counts: x = 2 bursts [1,2] and [2,3]
 *   [[1,10],[2,3],[4,5]]        -> 2   minEnd shrinks to 3, so [4,5] needs its own arrow
 *   [[5,9]]                     -> 1   one balloon
 *   [[MIN_VALUE,MAX_VALUE],[MAX_VALUE,MAX_VALUE]] -> 1   no overflow: the sort compares, never subtracts
 *   []                          -> 1 (should be 0): the +1 is always added;
 *                                  LeetCode guarantees at least one balloon
 *
 * COMMON MISTAKES
 *   - Keeping the largest end (like LeetCode 56): [[1,10],[2,3],[4,5]] would give 1 instead of 2.
 *   - Sorting with (a, b) -> a[0] - b[0]: it overflows for values near the int limits.
 *     Use Comparator.comparing or Integer.compare (the code does).
 *   - Using <= instead of <: touching balloons would get separate arrows.
 *   - Forgetting the + 1 for the last group.
 *
 * COMPLEXITY
 *   O(n log n) time for the sort; O(1) extra space apart from the sort.
 *
 * RELATED
 *   LeetCode 56 (MergeIntervals, Intervals/): same sort and scan, but keeps the union (largest end)
 *     instead of the shared part (smallest end).
 */
public class MinimumArrowsToBurst {
    public static void main(String[] args) {
//        int[][] points = {{1,2},{3,4},{5,6},{7,8}};
        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        System.out.println(findMinArrowShots(points));
    }

    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparing(i -> i[0]));
        System.out.println(Arrays.deepToString(points));
        int arrows = 0;
        int minEnd = Integer.MAX_VALUE;

        for (int[] point : points) {
            if (minEnd < point[0]) {
                arrows++;
                minEnd = point[1];
            } else {
                minEnd = Math.min(minEnd,point[1]);
            }
        }

        return arrows + 1;
    }
}

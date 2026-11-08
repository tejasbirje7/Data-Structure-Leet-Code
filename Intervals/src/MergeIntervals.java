import java.util.*;

/**
 * LeetCode 56. Merge Intervals
 * https://leetcode.com/problems/merge-intervals/
 *
 * PROBLEM
 *   - Input: an array of intervals [start, end], in any order.
 *   - Task: merge every group of overlapping intervals into one.
 *   - Return: the non-overlapping intervals that cover the same ranges.
 *
 * EXAMPLE
 *   Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 *   Output: [[1,6],[8,10],[15,18]]
 *   Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 *
 * HOW TO THINK
 *   - Clue: "overlapping" intervals are easy to spot once they are sorted by start:
 *     an interval can then only overlap the one just before it.
 *   - Technique: sort by start, then one pass that grows the last merged interval (previousInterval).
 *   - Rule that stays true: results holds the merged answer so far; previousInterval is its last entry.
 *   - Decision: interval start <= previous end -> overlap: previous end = max(both ends).
 *               otherwise -> gap: interval becomes the new previousInterval and is added to results.
 *   - Why updating previousInterval updates results: results stores the same array object,
 *     not a copy.
 *   - The loop also visits intervals[0] itself; it "merges" with itself and nothing changes.
 *   - Return: results as int[][].
 *
 * STEPS
 *   1. If there are 0 or 1 intervals, return them as they are.
 *   2. Sort by start.
 *   3. previousInterval = intervals[0]; add it to results.
 *   4. For each interval: if start <= previous end, previous end = max(ends);
 *      else previousInterval = interval and add it to results.
 *   5. Return results as an array.
 *
 * DRY RUN   intervals = [[1,3],[8,10],[15,18],[2,6],[9,13]]
 *   After sorting (the method prints this): [[1,3],[2,6],[8,10],[9,13],[15,18]]
 *   (prev end in "check" is the value before the step; previous and results are after it)
 *
 *    interval | check          | action                  | previous | results
 *   ----------+----------------+-------------------------+----------+------------------------
 *    [1,3]    | 1 <= 3         | merge with itself       | [1,3]    | [[1,3]]
 *    [2,6]    | 2 <= 3         | merge, end = max(6,3)   | [1,6]    | [[1,6]]
 *    [8,10]   | 8 <= 6 no      | gap, start new          | [8,10]   | [[1,6],[8,10]]
 *    [9,13]   | 9 <= 10        | merge, end = max(13,10) | [8,13]   | [[1,6],[8,13]]
 *    [15,18]  | 15 <= 13 no    | gap, start new          | [15,18]  | [[1,6],[8,13],[15,18]]
 *
 *   Returns [[1,6],[8,13],[15,18]].
 *
 * EDGE CASES
 *   [[1,4],[0,1]]   -> [[0,4]]   sorting puts [0,1] first; touching ends merge
 *   [[1,4],[4,5]]   -> [[1,5]]   touching counts as overlap (<=)
 *   [[1,4],[2,3]]   -> [[1,4]]   one inside the other: max keeps 4
 *   [[1,3]]         -> [[1,3]]   returned as it is
 *   []              -> []        returned as it is
 *
 * COMMON MISTAKES
 *   - Forgetting to sort: [[1,3],[8,10],[2,6]] would not merge [2,6] into [1,3].
 *   - Setting previous end = interval end instead of the max: [[1,4],[2,3]] would give [[1,3]].
 *   - Using < instead of <=: touching intervals [1,4] and [4,5] would stay apart.
 *   - Adding a copy of previousInterval to results: later merges would not show up in the copy.
 *
 * COMPLEXITY
 *   O(n log n) time for the sort, O(n) extra space for the result.
 *   Note: the input array is sorted and its intervals are changed in place.
 *
 * RELATED
 *   LeetCode 56 (MergeIntervals, FAQ/): same problem, also in FAQ/.
 *   LeetCode 57 (InsertInterval, Intervals/): the list is already sorted; merge in only one new interval.
 *   LeetCode 452 (MinimumArrowsToBurst, Intervals/): sort by start too, but keep the smallest end
 *     (the shared part) instead of the largest.
 */
public class MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {8, 10}, {15, 18}, {2, 6}, {9, 13}};
//        int[][] intervals = {{1,4},{0,1}};
        System.out.println(Arrays.deepToString(merge(intervals)));
    }

    public static int[][] merge(int[][] intervals) {

        if (intervals.length <= 1) return intervals;

        Arrays.sort(intervals, Comparator.comparing(i -> i[0]));

        System.out.println(Arrays.deepToString(intervals));

        int[] previousInterval = intervals[0];
        List<int[]> results = new ArrayList<>();
        results.add(previousInterval);

        for (int[] interval : intervals) {
            if (interval[0] <= previousInterval[1]) {
                previousInterval[1] = Math.max(interval[1], previousInterval[1]);
            } else {
                previousInterval = interval;
                results.add(previousInterval);
            }

        }

        return results.toArray(new int[results.size()][]);

    }
}

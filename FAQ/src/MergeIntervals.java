import java.util.*;

/**
 * LeetCode 56. Merge Intervals
 * https://leetcode.com/problems/merge-intervals/
 *
 * PROBLEM
 *   - Input: an array of intervals [start, end], in any order.
 *   - Task: merge every group of overlapping intervals into one.
 *   - Return: the non-overlapping intervals that cover the same ranges.
 *   - Touching intervals ([1,4] and [4,5]) count as overlapping.
 *
 * EXAMPLE
 *   Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 *   Output: [[1,6],[8,10],[15,18]]
 *   Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 *
 * HOW TO THINK
 *   - Clue: overlaps are hard to spot in random order, but after sorting by start,
 *     an interval can only overlap the one just before it (the last merged one).
 *   - Technique: sort by start, then one pass keeping previousInterval = the interval being merged into.
 *   - Rule that stays true: results holds sorted, non-overlapping intervals;
 *     previousInterval is the last one in results and its end can still grow.
 *   - Decision: interval[0] <= previousInterval[1] -> overlap: previousInterval[1] = max of both ends.
 *               otherwise -> gap: interval becomes the new previousInterval and is added to results.
 *   - Why editing works: previousInterval is the same array object that is in results,
 *     so changing its end changes results too.
 *   - Return: results converted from List<int[]> to int[][].
 *
 * STEPS
 *   1. If there are 0 or 1 intervals, return them as they are.
 *   2. Sort by start.
 *   3. previousInterval = intervals[0]; add it to results.
 *   4. For each interval: if interval[0] <= previousInterval[1], set previousInterval[1] = max(ends);
 *      else previousInterval = interval and add it to results.
 *   5. Return results as an array.
 *
 * DRY RUN   intervals = [[1,3],[2,6],[8,10],[15,18]]   (already sorted; values after the step)
 *
 *   Start: previousInterval = [1,3], results = [[1,3]].
 *
 *    interval | start <= prev end? | action                  | previousInterval | results
 *   ----------+--------------------+-------------------------+------------------+------------------------
 *     [1,3]   | 1 <= 3 yes         | merge with itself       |      [1,3]       | [[1,3]]
 *     [2,6]   | 2 <= 3 yes         | end = max(3, 6) = 6     |      [1,6]       | [[1,6]]
 *     [8,10]  | 8 <= 6 no          | new previous, add       |      [8,10]      | [[1,6],[8,10]]
 *     [15,18] | 15 <= 10 no        | new previous, add       |      [15,18]     | [[1,6],[8,10],[15,18]]
 *
 *   Returns [[1,6],[8,10],[15,18]].
 *   (The loop starts at intervals[0] again; comparing it with itself changes nothing.)
 *
 * EDGE CASES
 *   []                  -> []                 returned as is (length <= 1)
 *   [[1,4]]             -> [[1,4]]            returned as is
 *   [[1,4],[4,5]]       -> [[1,5]]            touching counts as overlap (<=)
 *   [[1,4],[2,3]]       -> [[1,4]]            fully inside; max keeps the end at 4
 *   [[1,4],[0,1]]       -> [[0,4]]            unsorted input; the sort fixes the order
 *   [[1,2],[3,4]]       -> [[1,2],[3,4]]      no overlap at all
 *   Note: the input array is changed (sorted, and ends overwritten).
 *
 * COMMON MISTAKES
 *   - Not sorting first: overlapping intervals may not be next to each other.
 *   - previousInterval[1] = interval[1] instead of the max: [[1,4],[2,3]] would give [[1,3]].
 *   - Using < instead of <=: touching intervals [1,4],[4,5] would stay apart.
 *   - Adding a new copy on every merge: keep one reference to the last interval and grow its end.
 *
 * COMPLEXITY
 *   O(n log n) time for the sort (the merge pass is O(n)).
 *   O(n) extra space for results (plus the sort's own space).
 *
 * RELATED
 *   LeetCode 56 (MergeIntervals, Intervals/): same problem, also in Intervals/.
 *   LeetCode 57 (InsertIntervals, FAQ/; InsertInterval, Intervals/): input already sorted,
 *     merge in one new interval in O(n).
 *   LeetCode 452 (MinimumArrowsToBurst, Intervals/): sort, then count overlap groups instead of merging.
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;

        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        List<int[]> results = new ArrayList<>();

        int[] previousInterval = intervals[0];
        results.add(previousInterval);

        for (int[] interval : intervals) {
            if (interval[0] <= previousInterval[1]) {
                previousInterval[1] = Math.max(previousInterval[1], interval[1]);
            }
            else {
                previousInterval = interval;
                results.add(previousInterval);
            }
        }

        return results.toArray(new int[results.size()][]);
    }

    public static void main(String[] args) {
        MergeIntervals sol = new MergeIntervals();

        int[][] intervals1 = { {1, 3}, {2, 6}, {8, 10}, {15, 18} };
        int[][] merged1 = sol.merge(intervals1);
        System.out.println("Merged Intervals 1: " + Arrays.deepToString(merged1));

        int[][] intervals2 = { {1, 4}, {4, 5} };
        int[][] merged2 = sol.merge(intervals2);
        System.out.println("Merged Intervals 2: " + Arrays.deepToString(merged2));

        int[][] intervals3 = { {1, 4}, {2, 3} };
        int[][] merged3 = sol.merge(intervals3);
        System.out.println("Merged Intervals 3: " + Arrays.deepToString(merged3));
    }
}

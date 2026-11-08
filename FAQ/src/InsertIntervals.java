import java.util.*;

/**
 * LeetCode 57. Insert Interval
 * https://leetcode.com/problems/insert-interval/
 *
 * PROBLEM
 *   - Input: intervals = non-overlapping [start, end] pairs, sorted by start; plus one newInterval.
 *   - Task: insert newInterval, merging any intervals it overlaps.
 *   - Return: the new list, still sorted and non-overlapping.
 *   - Already sorted, so no sort is needed: one pass is enough.
 *
 * EXAMPLE
 *   Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 *   Output: [[1,5],[6,9]]
 *
 * HOW TO THINK
 *   - Clue: "sorted" + "non-overlapping" means the list splits into three runs, left to right:
 *     ends before newInterval, overlaps newInterval, starts after newInterval.
 *   - Technique: one pointer i walking three loops, one per run.
 *   - Rule that stays true: everything in result is final and sorted;
 *     newInterval always holds the merged range so far.
 *   - Decision: loop 1: intervals[i] end < newInterval start -> fully left, copy it.
 *               loop 2: intervals[i] start <= newInterval end -> overlap (touching counts),
 *                       grow newInterval to [min of starts, max of ends].
 *               after loop 2: add newInterval once.
 *               loop 3: copy the rest.
 *   - Return: result as an int[][].
 *   - Note: newInterval itself is changed in place (it becomes the merged interval).
 *
 * STEPS
 *   1. i = 0, result = empty list.
 *   2. While intervals[i][1] < newInterval[0]: add intervals[i], i++.
 *   3. While intervals[i][0] <= newInterval[1]: newInterval = [min(starts), max(ends)], i++.
 *   4. Add newInterval.
 *   5. Add every remaining interval; return result as an array.
 *
 * DRY RUN   intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 *           (newInterval and result are values after the step)
 *
 *    loop | i | intervals[i] | check                  | action            | newInterval | result
 *   ------+---+--------------+------------------------+-------------------+-------------+------------------------
 *      1  | 0 | [1,2]        | end 2 < start 4        | copy              | [4,8]       | [[1,2]]
 *      1  | 1 | [3,5]        | end 5 < start 4? no    | go to loop 2      | [4,8]       | [[1,2]]
 *      2  | 1 | [3,5]        | start 3 <= end 8       | merge             | [3,8]       | [[1,2]]
 *      2  | 2 | [6,7]        | start 6 <= end 8       | merge             | [3,8]       | [[1,2]]
 *      2  | 3 | [8,10]       | start 8 <= end 8       | merge (touching)  | [3,10]      | [[1,2]]
 *      2  | 4 | [12,16]      | start 12 <= end 10? no | add newInterval   | [3,10]      | [[1,2],[3,10]]
 *      3  | 4 | [12,16]      | -                      | copy              | [3,10]      | [[1,2],[3,10],[12,16]]
 *
 *   Returns [[1,2],[3,10],[12,16]].
 *
 * EDGE CASES
 *   [], [5,7]                       -> [[5,7]]                only loop 2's "add" runs
 *   [[3,5]], [1,2]                  -> [[1,2],[3,5]]          new interval goes first
 *   [[1,5]], [6,8]                  -> [[1,5],[6,8]]          new interval goes last
 *   [[1,5]], [2,3]                  -> [[1,5]]                fully inside an existing interval
 *   [[1,2]], [2,3]                  -> [[1,3]]                touching ends count as overlap (<=)
 *   [[1,2],[3,4],[5,6]], [0,10]     -> [[0,10]]               swallows everything
 *
 * COMMON MISTAKES
 *   - Using <= in loop 1 (end <= new start): touching intervals like [1,2] and [2,3] stay separate.
 *   - Only growing the end: the merged start must be min(new start, interval start), e.g. [3,5] + [4,8] -> [3,8].
 *   - Adding newInterval inside loop 2: it gets added many times, or never when there is no overlap.
 *     Add it exactly once, right after loop 2.
 *   - Appending and calling a full merge (sort + merge): works, but O(n log n) instead of O(n).
 *
 * COMPLEXITY
 *   O(n) time (each interval is looked at once), O(n) extra space for the result.
 *
 * RELATED
 *   LeetCode 57 (InsertInterval, Intervals/): same problem, also in Intervals/.
 *   LeetCode 56 (MergeIntervals, FAQ/ and Intervals/): unsorted input; sort first, then merge neighbours.
 *   LeetCode 452 (MinimumArrowsToBurst, Intervals/): sort intervals and count overlap groups instead of merging.
 */
public class InsertIntervals {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        InsertIntervals sol = new InsertIntervals();

        int[][] intervals1 = { {1, 3}, {6, 9} };
        int[] newInterval1 = {2, 5};
        System.out.println("Output 1: " + Arrays.deepToString(sol.insert(intervals1, newInterval1)));

        int[][] intervals2 = { {1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16} };
        int[] newInterval2 = {4, 8};
        System.out.println("Output 2: " + Arrays.deepToString(sol.insert(intervals2, newInterval2)));
    }
}

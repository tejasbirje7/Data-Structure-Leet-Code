import java.util.*;

/**
 * LeetCode 57. Insert Interval
 * https://leetcode.com/problems/insert-interval/
 *
 * PROBLEM
 *   - Input: intervals, sorted by start and not overlapping, and one newInterval.
 *   - Task: insert newInterval, merging it with every interval it overlaps.
 *   - Return: the new list, still sorted and not overlapping.
 *
 * EXAMPLE
 *   Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 *   Output: [[1,2],[3,10],[12,16]]
 *
 * HOW TO THINK
 *   - Clue: "sorted" + "non-overlapping" means one left-to-right pass, no sorting needed.
 *   - Technique: split the list into three zones: intervals fully before newInterval,
 *     intervals that overlap it, and intervals fully after it.
 *   - Rule that stays true: result holds the final answer for everything before index i;
 *     newInterval has grown to cover every overlap seen so far.
 *   - Decision: zone 1: intervals[i] ends before newInterval starts (end < new start) -> copy it.
 *               zone 2: intervals[i] starts before newInterval ends (start <= new end) -> overlap:
 *                       newInterval = [min of starts, max of ends].
 *               zone 3: everything left -> copy it.
 *   - Touching counts as overlap ([1,5] and [5,7] merge): zone 1 uses <, zone 2 uses <=.
 *   - Return: result as int[][]; newInterval is added once, between zone 2 and zone 3.
 *
 * STEPS
 *   1. i = 0, result = [].
 *   2. While intervals[i][1] < newInterval[0]: add intervals[i], i++.
 *   3. While intervals[i][0] <= newInterval[1]: widen newInterval to cover intervals[i], i++.
 *   4. Add newInterval.
 *   5. Add every remaining interval, then return result as an array.
 *
 * DRY RUN   intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 *   (newInterval and result shown after the step)
 *
 *    i | interval | zone | check               | action        | newInterval | result
 *   ---+----------+------+---------------------+---------------+-------------+--------------------------
 *    0 | [1,2]    |  1   | 2 < 4               | copy          | [4,8]       | [[1,2]]
 *    1 | [3,5]    |  1   | 5 < 4 no            | leave zone 1  | [4,8]       | [[1,2]]
 *    1 | [3,5]    |  2   | 3 <= 8              | merge         | [3,8]       | [[1,2]]
 *    2 | [6,7]    |  2   | 6 <= 8              | merge         | [3,8]       | [[1,2]]
 *    3 | [8,10]   |  2   | 8 <= 8              | merge         | [3,10]      | [[1,2]]
 *    4 | [12,16]  |  2   | 12 <= 10 no         | add new       | [3,10]      | [[1,2],[3,10]]
 *    4 | [12,16]  |  3   |                     | copy          | [3,10]      | [[1,2],[3,10],[12,16]]
 *
 *   Returns [[1,2],[3,10],[12,16]]. The method also prints zone 1 ([1, 2]) as a debug line.
 *
 * EDGE CASES
 *   [[1,3],[6,9]], [2,5]      -> [[1,5],[6,9]]   overlaps one interval
 *   [], [5,7]                 -> [[5,7]]         nothing to merge
 *   [[1,5]], [2,3]            -> [[1,5]]         new interval sits inside
 *   [[1,5]], [6,8]            -> [[1,5],[6,8]]   goes after everything
 *   [[3,5]], [1,2]            -> [[1,2],[3,5]]   goes before everything
 *   [[1,5]], [5,7]            -> [[1,7]]         touching intervals merge
 *   [[1,2],[3,4]], [0,10]     -> [[0,10]]        swallows every interval
 *
 * COMMON MISTAKES
 *   - Using <= in zone 1: [[1,5]], [5,7] would give [[1,5],[5,7]] instead of [[1,7]].
 *   - Adding newInterval inside the merge loop: it gets added once per overlap.
 *   - Forgetting that newInterval is changed in place: after the dry run the caller's array is [3,10].
 *
 * COMPLEXITY
 *   O(n) time, O(n) extra space for the result.
 *
 * RELATED
 *   LeetCode 57 (InsertIntervals, FAQ/): same problem, also in FAQ/.
 *   LeetCode 56 (MergeIntervals, Intervals/): unsorted intervals, sort first, then merge every overlap.
 */
public class InsertInterval {
    public static void main(String[] args) {
        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {4,8};
//        int[][] intervals = {{1,3},{6,9}};
//        int[] newInterval = {2,5};
        System.out.println(Arrays.deepToString(insert(intervals, newInterval)));
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {

        int i = 0;
        List<int[]> result = new ArrayList<>();

        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        result.forEach(k -> System.out.println(Arrays.toString(k)));

        while (i < intervals.length && intervals[i][0] <= newInterval[1] ) {
            newInterval[0] = Math.min(newInterval[0],intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1],intervals[i][1]);
            i++;
        }

        result.add(newInterval);

        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);

    }
}

import java.util.*;

public class MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {8, 10}, {15, 18}, {2, 6}, {9, 13}};
//        int[][] intervals = {{1,4},{0,1}};
        System.out.println(Arrays.deepToString(merge(intervals)));
    }

    /**
     * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * Output: [[1,6],[8,10],[15,18]]
     * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
     */
    public static int[][] merge(int[][] intervals) {

        if (intervals.length <= 1) return intervals;

        Arrays.sort(intervals, Comparator.comparing(i -> i[0]));

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

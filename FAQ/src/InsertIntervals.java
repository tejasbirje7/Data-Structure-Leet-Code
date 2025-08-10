import java.util.*;

public class InsertIntervals {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Step 1: Add intervals before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Step 2: Merge overlaps with newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // Step 3: Add remaining intervals
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
        System.out.println("Output 1: " + Arrays.deepToString(sol.insert(intervals1, newInterval1))); // [[1, 5], [6, 9]]

        int[][] intervals2 = { {1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16} };
        int[] newInterval2 = {4, 8};
        System.out.println("Output 2: " + Arrays.deepToString(sol.insert(intervals2, newInterval2))); // [[1, 2], [3, 10], [12, 16]]
    }
}

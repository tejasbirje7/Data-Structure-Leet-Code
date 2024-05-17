import java.util.*;

public class InsertInterval {
    public static void main(String[] args) {
        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {4,8};
        System.out.println(Arrays.deepToString(insert(intervals, newInterval)));
    }

    /**
     Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     Output: [[1,2],[3,10],[12,16]]
     int[][] intervals = {{1,3},{6,9}};
     int[] newInterval = {2,5};
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {

        int i = 0;
        List<int[]> result = new ArrayList<>();

        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

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

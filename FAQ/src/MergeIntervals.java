import java.util.*;

public class MergeIntervals {
    /**
     * Merges overlapping intervals in the given array.
     * @param intervals An array of intervals [start, end].
     * @return A merged array of non-overlapping intervals.
     *
     * Sort intervals by start time to make overlapping detection easier.
     * Keep track of a previousInterval (the one we're merging into).
     * Overlap check: If interval[0] <= previousInterval[1], update the end time to the max of both ends.
     * If no overlap, move on and add the new interval to results.
     * Convert List<int[]> to int[][] before returning.
     *
     */
    public int[][] merge(int[][] intervals) {
        // If only one or zero intervals, nothing to merge
        if (intervals.length <= 1) return intervals;

        // Step 1: Sort intervals by starting time
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        // This will hold our merged intervals
        List<int[]> results = new ArrayList<>();

        // Start with the first interval
        int[] previousInterval = intervals[0];
        results.add(previousInterval);

        // Step 2: Iterate through intervals
        for (int[] interval : intervals) {
            // If current interval overlaps with the previous, merge them
            if (interval[0] <= previousInterval[1]) {
                previousInterval[1] = Math.max(previousInterval[1], interval[1]);
            }
            // If no overlap, move to next interval
            else {
                previousInterval = interval;
                results.add(previousInterval);
            }
        }

        // Convert list to array and return
        return results.toArray(new int[results.size()][]);
    }

    public static void main(String[] args) {
        MergeIntervals sol = new MergeIntervals();

        int[][] intervals1 = { {1, 3}, {2, 6}, {8, 10}, {15, 18} };
        int[][] merged1 = sol.merge(intervals1);
        System.out.println("Merged Intervals 1: " + Arrays.deepToString(merged1)); // [[1, 6], [8, 10], [15, 18]]

        int[][] intervals2 = { {1, 4}, {4, 5} };
        int[][] merged2 = sol.merge(intervals2);
        System.out.println("Merged Intervals 2: " + Arrays.deepToString(merged2)); // [[1, 5]]

        int[][] intervals3 = { {1, 4}, {2, 3} };
        int[][] merged3 = sol.merge(intervals3);
        System.out.println("Merged Intervals 3: " + Arrays.deepToString(merged3)); // [[1, 4]]
    }
}


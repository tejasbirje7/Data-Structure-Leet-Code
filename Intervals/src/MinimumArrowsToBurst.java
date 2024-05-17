import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinimumArrowsToBurst {
    public static void main(String[] args) {
        int[][] points = {{1,2},{3,4},{5,6},{7,8}};
//        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        System.out.println(findMinArrowShots(points));
    }

    /**
     * Input: points = [[10,16],[2,8],[1,6],[7,12]]
     * Output: 2
     * Explanation: The balloons can be burst by 2 arrows:
     * - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
     * - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
     */
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

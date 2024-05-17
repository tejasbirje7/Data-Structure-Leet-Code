import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnLine {

    public static void main(String[] args) {
        int[][] points = {{1,1},{2,2},{3,3}};
        System.out.println(maxPoints(points));
    }

    public static int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;
        int ans = 2;

        for (int[] p1 : points) {
            // Maintain a map for each [x,y] point
            // This will use to identify the maximum points
            // in straight line with this [x,y] point
            Map<Double,Integer> map = new HashMap<>();

            for (int[] p2 : points) {

                if(p1 == p2) continue;

                // Calculate slope for p1,p2 points
                double slope = 0;
                if(p2[0] == p1[0]) slope = Double.POSITIVE_INFINITY;
                else slope = (p2[1] - p1[1]) / (double)(p2[0] - p1[0]);

                // Set the count with 1 if slope doesn't exist in map and add 1
                map.put(slope,map.getOrDefault(slope,1) + 1);

                // If count is greater than current straight line, update the ans
                ans = Math.max(ans,map.get(slope));
            }
        }
        return ans;
    }
}

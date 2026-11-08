import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 149. Max Points on a Line
 * https://leetcode.com/problems/max-points-on-a-line/
 *
 * PROBLEM
 *   - Input: points, where points[i] = [xi, yi] is a point on the X-Y plane.
 *   - Return: the largest number of points that lie on one straight line.
 *   - Constraints: 1 <= points.length <= 300, coordinates in -10^4..10^4, all points are unique.
 *
 * EXAMPLE
 *   Input: points = [[1,1],[2,2],[3,3]]
 *   Output: 3
 *
 * HOW TO THINK
 *   - Clue: two points always make a line; a third is on it only if the slope matches.
 *   - Technique: fix one point p1 (an anchor), and group every other point by its slope from p1
 *     in a hash map (slope -> points on that line, counting p1).
 *     Same anchor + same slope = same line, so the biggest group is the best line through p1.
 *   - Rule that stays true: map holds, for the current p1 only, slope -> count including p1;
 *     ans = the best count seen over all anchors so far.
 *   - Decision: p2 is p1 itself -> skip.
 *               same x           -> vertical line, slope = POSITIVE_INFINITY (avoids dividing by 0).
 *               else             -> slope = dy / (double) dx.
 *               map[slope] = (map[slope] or 1) + 1; the default 1 counts p1 itself.
 *               ans = max(ans, map[slope]).
 *   - Return: ans, which starts at 2 because any two points are on a line.
 *   - A new map for each p1: lines through different anchors with the same slope are parallel,
 *     not the same line.
 *
 * STEPS
 *   1. If there are 2 points or fewer, return n. Set ans = 2.
 *   2. For each point p1: make an empty map.
 *   3.   For each other point p2: work out the slope (infinity if x is the same).
 *   4.   map[slope] = map.getOrDefault(slope, 1) + 1; ans = max(ans, map[slope]).
 *   5. Return ans.
 *
 * DRY RUN   points = [[1,1],[2,2],[3,3]]   (map and ans are values after the step)
 *
 *     p1   |  p2   | slope           | map            | ans
 *   -------+-------+-----------------+----------------+-----
 *    [1,1] | [1,1] | same point      | {}             |  2
 *    [1,1] | [2,2] | 1 / 1 = 1.0     | {1.0=2}        |  2
 *    [1,1] | [3,3] | 2 / 2 = 1.0     | {1.0=3}        |  3
 *    [2,2] | [1,1] | -1 / -1 = 1.0   | {1.0=2}        |  3
 *    [2,2] | [2,2] | same point      | {1.0=2}        |  3
 *    [2,2] | [3,3] | 1 / 1 = 1.0     | {1.0=3}        |  3
 *    [3,3] | [1,1] | -2 / -2 = 1.0   | {1.0=2}        |  3
 *    [3,3] | [2,2] | -1 / -1 = 1.0   | {1.0=3}        |  3
 *    [3,3] | [3,3] | same point      | {1.0=3}        |  3
 *
 *   Returns 3: all three points are on y = x.
 *
 * EDGE CASES
 *   [[0,0]]                                   -> 1   n <= 2, returned at once
 *   [[0,0],[1,1]]                             -> 2   n <= 2
 *   []                                        -> 0   n <= 2 (LeetCode has at least one point)
 *   [[1,1],[1,2],[1,3]]                       -> 3   vertical line, slope = infinity
 *   [[2,1],[1,1],[3,1]]                       -> 3   horizontal: 0 / -1 gives -0.0, a different map key
 *                                                    from 0.0, but the leftmost point sees all the
 *                                                    others as +0.0, so the answer is still right
 *   [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]     -> 4   the second LeetCode example
 *   [[0,0],[10000,9999],[-10000,-9999],[1,0]] -> 3   slopes this close still differ as doubles
 *                                                    within the -10^4..10^4 range
 *
 * COMMON MISTAKES
 *   - Integer division for the slope: (dy) / (dx) without the (double) cast makes 1/2 and 0 the same.
 *   - Dividing when dx == 0: gives infinity or NaN by accident; handle vertical lines on purpose.
 *   - Reusing one map for all anchors: parallel lines with the same slope get merged.
 *   - Starting the default count at 0 instead of 1: forgets to count p1, answers are one too small.
 *   - Doubles as keys can fail for huge coordinates; the safe version stores the reduced
 *     fraction (dy / g, dx / g with g = gcd) as the key.
 *
 * COMPLEXITY
 *   O(n^2) time (every pair, O(1) map work), O(n) extra space for the map.
 *
 * RELATED
 *   LeetCode 1 (TwoSum, HashMap/): the same "hash map keyed by what you computed" counting idea.
 *   LeetCode 49 (GroupAnagrams, HashMap/): group items by a computed key, here the slope.
 */
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
            Map<Double,Integer> map = new HashMap<>();

            for (int[] p2 : points) {

                if(p1 == p2) continue;

                double slope = 0;
                if(p2[0] == p1[0]) slope = Double.POSITIVE_INFINITY;
                else slope = (p2[1] - p1[1]) / (double)(p2[0] - p1[0]);

                map.put(slope,map.getOrDefault(slope,1) + 1);

                ans = Math.max(ans,map.get(slope));
            }
        }
        return ans;
    }
}

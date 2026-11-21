import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 210. Course Schedule II
 * https://leetcode.com/problems/course-schedule-ii/
 *
 * PROBLEM
 *   - Input: numCourses (labelled 0 to numCourses - 1) and pairs [a, b] meaning "take b before a".
 *   - Return: any order that takes every course, or an empty array if that is impossible (a cycle).
 *
 * EXAMPLE
 *   Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 *   Output: [0,2,1,3]
 *   Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
 *     Both courses 1 and 2 should be taken after you finished course 0.
 *     So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 *
 * HOW TO THINK
 *   - Clue: "order" + "before" -> a topological order of a directed graph b -> a.
 *   - Technique: Kahn's BFS. The order courses leave the queue is a valid order.
 *   - Rule that stays true: order[0..idx-1] are taken courses; the queue holds courses whose prerequisites are all in order[].
 *   - Decision: poll a course and write it at order[idx++]; for each course that depends on it, in-degree - 1;
 *     if it becomes 0, queue it.
 *   - Return: order if idx == numCourses, else an empty array (some courses are stuck on a cycle).
 *
 * STEPS
 *   1. Build adjacency lists b -> a and count inDegree[a] for every pair [a, b].
 *   2. Queue every course with inDegree 0; order = new int[numCourses], idx = 0.
 *   3. While the queue is not empty: course = poll, order[idx++] = course.
 *   4.   For each next course: inDegree[next]--; if it is 0, queue next.
 *   5. Return idx == numCourses ? order : new int[0].
 *
 * DRY RUN   input = numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 *   edges: 0 -> [1, 2], 1 -> [3], 2 -> [3]. Start: inDegree = [0,1,1,2], queue = [0].
 *   (inDegree, queue and order are after the step)
 *
 *    step | poll | next course: inDegree change | action           | inDegree  | queue  | order
 *   ------+------+------------------------------+------------------+-----------+--------+----------
 *      1  |  0   | 1: 1 -> 0, 2: 1 -> 0         | queue 1, queue 2 | [0,0,0,2] | [1, 2] | [0]
 *      2  |  1   | 3: 2 -> 1                    | not 0, wait      | [0,0,0,1] | [2]    | [0,1]
 *      3  |  2   | 3: 1 -> 0                    | queue 3          | [0,0,0,0] | [3]    | [0,1,2]
 *      4  |  3   | none                         |                  | [0,0,0,0] | []     | [0,1,2,3]
 *
 *   idx = 4 == numCourses: returns [0,1,2,3] (LeetCode accepts any valid order, e.g. [0,2,1,3]).
 *
 * EDGE CASES
 *   numCourses = 1, []                 -> [0]       one course, nothing to wait for
 *   numCourses = 2, [[1,0]]            -> [0,1]
 *   numCourses = 2, [[1,0],[0,1]]      -> []        cycle: nobody starts at in-degree 0
 *   numCourses = 3, [[1,0]]            -> [0,2,1]   course 2 has no edges but must still be in the order
 *
 * COMMON MISTAKES
 *   - Returning the partial order when there is a cycle: check idx == numCourses first.
 *   - Reversing the edge: [a, b] means b first, so the edge is b -> a.
 *   - Using DFS finish order without reversing it: DFS post-order gives the order backwards.
 *
 * COMPLEXITY
 *   O(V + E) time, O(V + E) extra space for the graph and the order.
 *
 * RELATED
 *   LeetCode 207 (CourseSchedule, Graphs/): the same algorithm, but only answers "is there an order".
 *   LeetCode 269 (AlienDictionary, Graphs/): a topological order of letters, with edges taken from sorted words.
 */
public class CourseSchedule2 {

    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(Arrays.toString(findOrder(4, prerequisites)));
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> next = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            next.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        for (int[] p : prerequisites) {
            next.get(p[1]).add(p[0]);
            inDegree[p[0]]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        int[] order = new int[numCourses];
        int idx = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            order[idx++] = course;
            for (int n : next.get(course)) {
                inDegree[n]--;
                if (inDegree[n] == 0) {
                    queue.offer(n);
                }
            }
        }
        return idx == numCourses ? order : new int[0];
    }

}

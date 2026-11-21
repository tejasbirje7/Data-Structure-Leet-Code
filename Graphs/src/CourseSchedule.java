import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 207. Course Schedule
 * https://leetcode.com/problems/course-schedule/
 *
 * PROBLEM
 *   - Input: numCourses (labelled 0 to numCourses - 1) and pairs [a, b] meaning "take b before a".
 *   - Return: true if all courses can be finished, false otherwise.
 *   - In graph terms: is the directed graph b -> a free of cycles?
 *
 * EXAMPLE
 *   Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 *   Output: false
 *   Explanation: There are a total of 2 courses to take.
 *     To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1.
 *     So it is impossible.
 *
 * HOW TO THINK
 *   - Clue: "before" / "prerequisite" / "dependencies" -> a directed graph; "can all be done" -> no cycle.
 *   - Technique: topological sort with Kahn's BFS. A course with no prerequisites left (in-degree 0) can be taken now.
 *   - Rule that stays true: the queue holds exactly the courses whose prerequisites are all taken but which are not taken yet.
 *     A course on a cycle never reaches in-degree 0, so it is never taken.
 *   - Decision: take the queue front (taken++); for each course that depends on it, in-degree - 1;
 *     if that in-degree becomes 0, queue it.
 *   - Return: taken == numCourses. Fewer means some courses are stuck on a cycle.
 *
 * STEPS
 *   1. Build adjacency lists b -> a and count inDegree[a] for every pair [a, b].
 *   2. Queue every course with inDegree 0.
 *   3. While the queue is not empty: poll a course, taken++.
 *   4.   For each next course: inDegree[next]--; if it is 0, queue next.
 *   5. Return taken == numCourses.
 *
 * DRY RUN   input = numCourses = 4, prerequisites = [[1,0],[2,1],[3,2],[2,3]]
 *   edges: 0 -> 1, 1 -> 2, 2 -> 3, 3 -> 2 (2 and 3 form a cycle). Start: inDegree = [0,1,2,1], queue = [0].
 *   (inDegree, queue and taken are after the step)
 *
 *    step | poll | next course: inDegree change | action      | inDegree  | queue | taken
 *   ------+------+------------------------------+-------------+-----------+-------+------
 *      1  |  0   | 1: 1 -> 0                    | queue 1     | [0,0,2,1] | [1]   |  1
 *      2  |  1   | 2: 2 -> 1                    | not 0, wait | [0,0,1,1] | []    |  2
 *
 *   Queue is empty with taken = 2 < 4: returns false. Courses 2 and 3 wait on each other forever.
 *
 * EDGE CASES
 *   numCourses = 1, []                 -> true    nothing to wait for
 *   numCourses = 2, [[1,0]]            -> true    0 then 1
 *   numCourses = 2, [[0,0]]            -> false   a course that needs itself is a cycle of size 1
 *   numCourses = 3, [[1,0],[1,0]]      -> true    duplicate pair adds 2 to inDegree and removes 2, still fine
 *
 * COMMON MISTAKES
 *   - Reversing the edge: [a, b] means b comes first, so the edge is b -> a and inDegree[a]++.
 *   - Queuing a course every time its in-degree drops instead of only when it hits 0.
 *   - Only checking direct pairs like [a,b] and [b,a]: cycles can be longer (2 -> 3 -> 4 -> 2). Kahn's count catches all.
 *
 * COMPLEXITY
 *   O(V + E) time (each course is queued once, each edge is used once), O(V + E) extra space for the graph.
 *
 * RELATED
 *   LeetCode 210 (CourseSchedule2, Graphs/): the same Kahn's BFS, but return the order the courses were taken.
 *   LeetCode 269 (AlienDictionary, Graphs/): build the edges from sorted words first, then the same topological sort.
 */
public class CourseSchedule {

    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}, {2, 1}, {3, 2}, {2, 3}};
        System.out.println(canFinish(4, prerequisites));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
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
        int taken = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            taken++;
            for (int n : next.get(course)) {
                inDegree[n]--;
                if (inDegree[n] == 0) {
                    queue.offer(n);
                }
            }
        }
        return taken == numCourses;
    }

}

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode 621. Task Scheduler
 * https://leetcode.com/problems/task-scheduler/
 *
 * PROBLEM
 *   - Input: tasks, each an upper-case letter A-Z (one task takes one interval), and a cooldown n.
 *   - Two runs of the SAME letter must be at least n intervals apart; idle intervals are allowed.
 *   - Return: the minimum number of intervals to finish every task.
 *
 * EXAMPLE
 *   Input: tasks = ["A","A","A","B","B","B"], n = 2
 *   Output: 8
 *   Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
 *     After completing task A, you must wait two intervals before doing A again. The same applies to task B.
 *     In the 3rd interval, neither A nor B can be done, so you idle.
 *     By the 4th interval, you can do A again as 2 intervals have passed.
 *
 * HOW TO THINK
 *   - Clue: "minimum intervals" + cooldown -> the task with the most copies left is the one that forces idles,
 *     so always run it first (greedy with a max-heap).
 *   - Technique: max-heap of remaining counts (tasks you can run now)
 *     + a queue of tasks cooling down, each stored as {remaining count, time it is ready again}.
 *   - Rule that stays true: at each time step, the heap holds exactly the tasks that are allowed to run now.
 *     Letters don't matter, only counts.
 *   - Decision each interval: heap not empty -> run the top task, count - 1; if copies remain, queue it until time + n.
 *                             heap empty     -> idle.
 *                             then, the queue front is ready at this time -> move it back to the heap for the next interval.
 *   - Return: time when both the heap and the queue are empty.
 *
 * STEPS
 *   1. Count each letter; push every count into a max-heap.
 *   2. time = 0; loop while the heap or the queue is not empty: time++.
 *   3. If the heap is not empty: c = poll - 1; if c > 0, queue {c, time + n}.
 *   4. If the queue front's ready time == time, move its count back to the heap.
 *   5. Return time.
 *
 * DRY RUN   input = tasks = ["A","A","A","B","B","B"], n = 2
 *   (letters are only labels for reading; the heap stores counts; heap and queue are after the step)
 *
 *    time | run   | queue after run               | front ready now?   | heap  | queue
 *   ------+-------+-------------------------------+--------------------+-------+---------------------
 *     0   | init  |                               |                    | [3,3] | []
 *     1   | A 3->2| [A{2,ready 3}]                | 3 == 1 no          | [3]   | [A{2,3}]
 *     2   | B 3->2| [A{2,3}, B{2,4}]              | 3 == 2 no          | []    | [A{2,3}, B{2,4}]
 *     3   | idle  | [A{2,3}, B{2,4}]              | 3 == 3 yes, A back | [2]   | [B{2,4}]
 *     4   | A 2->1| [B{2,4}, A{1,6}]              | 4 == 4 yes, B back | [2]   | [A{1,6}]
 *     5   | B 2->1| [A{1,6}, B{1,7}]              | 6 == 5 no          | []    | [A{1,6}, B{1,7}]
 *     6   | idle  | [A{1,6}, B{1,7}]              | 6 == 6 yes, A back | [1]   | [B{1,7}]
 *     7   | A 1->0| [B{1,7}]  (A done, not queued)| 7 == 7 yes, B back | [1]   | []
 *     8   | B 1->0| []        (B done, not queued)| queue empty        | []    | []
 *
 *   Returns 8: A B idle A B idle A B.
 *
 * EDGE CASES
 *   ["A","A","A","B","B","B"], n = 0   -> 6   no cooldown: just the number of tasks
 *   ["A","A","A"], n = 2               -> 7   A idle idle A idle idle A
 *   ["A","B","C","D"], n = 2           -> 4   all different: never idle
 *   ["A","C","A","B","D","B"], n = 1   -> 6   enough other tasks to fill every gap
 *
 * COMMON MISTAKES
 *   - Running tasks in a fixed order (A, B, C...) instead of most-remaining first:
 *     the most frequent task gets left at the end with forced idles.
 *   - Off-by-one in the ready time: with "run, then release tasks ready at this time", the ready time is time + n.
 *     Releasing before running would need time + n + 1.
 *   - Stopping when the heap is empty: tasks may still be cooling down in the queue.
 *
 * COMPLEXITY
 *   O(answer) loop steps, each O(log 26) = O(1); the answer is at most tasks.length * (n + 1).
 *   O(26) = O(1) extra space.
 *   (A counting formula skips the simulation: max(tasks.length, (maxCount - 1) * (n + 1) + number of letters with maxCount).)
 *
 * RELATED
 *   LeetCode 347 (TopKFrequentElements, Heap/): count first, then use a heap ordered by those counts.
 *   LeetCode 295 (MedianFromDataStream, Heap/): another problem that keeps a heap correct while the input changes.
 */
public class TaskScheduler {

    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        System.out.println(leastInterval(tasks, 2));
    }

    public static int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char t : tasks) {
            count[t - 'A']++;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int c : count) {
            if (c > 0) {
                heap.offer(c);
            }
        }
        Queue<int[]> cooling = new ArrayDeque<>();
        int time = 0;
        while (!heap.isEmpty() || !cooling.isEmpty()) {
            time++;
            if (!heap.isEmpty()) {
                int left = heap.poll() - 1;
                if (left > 0) {
                    cooling.offer(new int[]{left, time + n});
                }
            }
            if (!cooling.isEmpty() && cooling.peek()[1] == time) {
                heap.offer(cooling.poll()[0]);
            }
        }
        return time;
    }

}

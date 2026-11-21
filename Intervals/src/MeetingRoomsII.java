import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LeetCode 253. Meeting Rooms II   (LeetCode Premium)
 * https://leetcode.com/problems/meeting-rooms-ii/
 *
 * PROBLEM
 *   - Input: meeting time intervals [start, end] (not sorted).
 *   - Return: the minimum number of conference rooms needed so that no two meetings in one room overlap.
 *   - A meeting ending at t and one starting at t can share a room.
 *
 * EXAMPLE
 *   Input: intervals = [[0,30],[5,10],[15,20]]
 *   Output: 2
 *
 * HOW TO THINK
 *   - Clue: "minimum rooms" = the most meetings running at the same moment.
 *   - Technique: sort by start time; keep a min-heap of end times, one per room in use.
 *     The heap's top is the room that frees up first.
 *   - Rule that stays true: the heap size is the number of rooms opened so far, and each entry is when that room is free.
 *   - Decision for each meeting (in start order):
 *     earliest end <= start -> that room is free: poll it and reuse it;
 *     otherwise every room is busy -> open a new room.
 *     Either way, push this meeting's end.
 *   - Return: the heap size. It never shrinks, because a poll is always followed by a push.
 *
 * STEPS
 *   1. Sort intervals by start.
 *   2. heap = min-heap of end times.
 *   3. For each [start, end]: if heap is not empty and heap.peek() <= start, poll.
 *   4.   Push end.
 *   5. Return heap.size().
 *
 * DRY RUN   input = [[0,30],[5,10],[15,20]]   (already sorted; heap is after the step)
 *
 *    meeting | earliest end | free at start? | action                 | heap (end times) | rooms
 *   ---------+--------------+----------------+------------------------+------------------+------
 *    [0,30]  |   none       |   -            | open room, push 30     | [30]             |  1
 *    [5,10]  |    30        | 30 <= 5 no     | open room, push 10     | [10, 30]         |  2
 *    [15,20] |    10        | 10 <= 15 yes   | reuse: poll 10, push 20| [20, 30]         |  2
 *
 *   Returns 2.
 *
 * EDGE CASES
 *   [[7,10],[2,4]]             -> 1   unsorted input: after sorting, [2,4] ends before [7,10] starts
 *   [[1,5]]                    -> 1   one meeting
 *   [[1,5],[5,10]]             -> 1   end == start: the room is reused (<=, not <)
 *   [[1,10],[2,9],[3,8]]       -> 3   all overlap at time 3
 *   []                         -> 0   no meetings (the constraints say at least one)
 *
 * COMMON MISTAKES
 *   - Not sorting by start first: a later meeting can grab a room before an earlier one is placed.
 *   - Using < instead of <= : a meeting that ends exactly when another starts frees its room.
 *   - Sorting by end time: that is the rule for "most meetings in one room" (greedy activity selection), not this problem.
 *   - Comparing with the last meeting's end only: the room that frees first is the heap's minimum, not the latest meeting.
 *
 * COMPLEXITY
 *   O(n log n) time (the sort and n heap operations), O(n) extra space for the heap.
 *
 * RELATED
 *   LeetCode 56 (MergeIntervals, Intervals/): also sorts by start, but merges overlaps instead of counting them.
 *   LeetCode 452 (MinimumArrowsToBurst, Intervals/): overlapping intervals again, greedy on end times.
 *   LeetCode 621 (TaskScheduler, Heap/): a heap tracking when things become free again.
 */
public class MeetingRoomsII {

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(minMeetingRooms(intervals));
    }

    public static int minMeetingRooms(int[][] intervals) {
        int[][] sorted = intervals.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> ends = new PriorityQueue<>();
        for (int[] meeting : sorted) {
            if (!ends.isEmpty() && ends.peek() <= meeting[0]) {
                ends.poll();
            }
            ends.offer(meeting[1]);
        }
        return ends.size();
    }

}

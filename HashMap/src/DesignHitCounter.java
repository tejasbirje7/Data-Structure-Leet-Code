/**
 * LeetCode 362. Design Hit Counter  (LeetCode names the class HitCounter)
 * https://leetcode.com/problems/design-hit-counter/
 *
 * PROBLEM
 *   - Design a counter of hits in the past 5 minutes (300 seconds):
 *       hit(timestamp)     -> record one hit at that second.
 *       getHits(timestamp) -> number of hits in (timestamp - 300, timestamp], i.e. the last 300 seconds.
 *   - Timestamps are in seconds (1..2 * 10^9) and never go down between calls.
 *     Several hits may share one timestamp.
 *   - Follow-up: what if there are very many hits per second?
 *
 * EXAMPLE
 *   Input: ["HitCounter","hit","hit","hit","getHits","hit","getHits","getHits"]
 *          [[],[1],[2],[3],[4],[300],[300],[301]]
 *   Output: [null,null,null,null,3,null,4,3]
 *   Explanation:
 *     HitCounter hitCounter = new HitCounter();
 *     hitCounter.hit(1);       // hit at timestamp 1.
 *     hitCounter.hit(2);       // hit at timestamp 2.
 *     hitCounter.hit(3);       // hit at timestamp 3.
 *     hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
 *     hitCounter.hit(300);     // hit at timestamp 300.
 *     hitCounter.getHits(300); // get hits at timestamp 300, return 4.
 *     hitCounter.getHits(301); // get hits at timestamp 301, return 3.
 *
 * HOW TO THINK
 *   - Clue: a fixed window of 300 seconds + the follow-up about many hits per second
 *     -> keep one bucket per second, not one entry per hit.
 *   - Technique: two arrays of size 300 used as a ring. Second t goes to slot t % 300.
 *       times[i] -> which second slot i currently describes.
 *       hits[i]  -> how many hits happened in that second.
 *   - Rule that stays true: a slot is only counted if its second is still inside the window,
 *     i.e. timestamp - times[i] < 300. Old slots are simply ignored, then reused.
 *   - Decision:
 *       hit(t): slot = t % 300. times[slot] == t -> hits[slot]++ (same second again).
 *               else the slot holds an old second -> times[slot] = t, hits[slot] = 1 (reuse it).
 *       getHits(t): add hits[i] for every slot with t - times[i] < 300.
 *   - Return: that sum.
 *
 * STEPS
 *   1. times = int[300], hits = int[300].
 *   2. hit(t): i = t % 300; if times[i] != t: times[i] = t, hits[i] = 1; else hits[i]++.
 *   3. getHits(t): total = 0; for i in 0..299: if t - times[i] < 300, total += hits[i]. Return total.
 *
 * DRY RUN   the LeetCode example   (only non-empty slots shown as slot:(time, hits))
 *
 *    op            | slot | action                         | slots after                          | returns
 *   ---------------+------+--------------------------------+--------------------------------------+--------
 *    hit(1)        |  1   | new second, set (1,1)          | 1:(1,1)                              |  -
 *    hit(2)        |  2   | new second, set (2,1)          | 1:(1,1) 2:(2,1)                      |  -
 *    hit(3)        |  3   | new second, set (3,1)          | 1:(1,1) 2:(2,1) 3:(3,1)              |  -
 *    getHits(4)    |  -   | 4-1, 4-2, 4-3 all < 300: 1+1+1 | same                                 |  3
 *    hit(300)      |  0   | new second, set (300,1)        | 0:(300,1) 1:(1,1) 2:(2,1) 3:(3,1)    |  -
 *    getHits(300)  |  -   | 0, 299, 298, 297 all < 300     | same                                 |  4
 *    getHits(301)  |  -   | 301-1 = 300 not < 300: drop slot 1; others count | same               |  3
 *
 *   Prints [null, null, null, null, 3, null, 4, 3].
 *
 * EDGE CASES
 *   hit(1) x 3, getHits(1)            -> 3   many hits in one second share a slot
 *   hit(1), getHits(300)              -> 1   300 - 1 = 299 < 300, still inside
 *   hit(1), getHits(301)              -> 0   exactly 300 seconds old: outside
 *   hit(1), hit(301), getHits(301)    -> 1   slot 1 reused: the old second 1 is overwritten
 *   getHits(5) with no hits           -> 0   empty slots have hits 0
 *
 * COMMON MISTAKES
 *   - Using <= 300 instead of < 300: a hit exactly 300 seconds old must not count.
 *   - Adding to hits[slot] without checking times[slot]: an old second's hits get mixed into the new one.
 *   - A queue of every hit timestamp: correct, but memory grows with the number of hits
 *     (the follow-up's concern); buckets keep it at 300 slots.
 *
 * COMPLEXITY
 *   hit: O(1). getHits: O(300) = O(1). O(300) = O(1) extra space, no matter how many hits.
 *
 * RELATED
 *   LeetCode 981 (TimeBasedKeyValueStore, HashMap/): another design keyed by non-decreasing timestamps.
 *   LeetCode 239 (SlidingWindowMaximum, SlidingWindow/): a sliding window where old items expire.
 */
public class DesignHitCounter {

    private static final int WINDOW = 300;
    private final int[] times = new int[WINDOW];
    private final int[] hits = new int[WINDOW];

    public void hit(int timestamp) {
        int i = timestamp % WINDOW;
        if (times[i] != timestamp) {
            times[i] = timestamp;
            hits[i] = 1;
        } else {
            hits[i]++;
        }
    }

    public int getHits(int timestamp) {
        int total = 0;
        for (int i = 0; i < WINDOW; i++) {
            if (timestamp - times[i] < WINDOW) total += hits[i];
        }
        return total;
    }

    public static void main(String[] args) {
        DesignHitCounter counter = new DesignHitCounter();
        StringBuilder out = new StringBuilder("[null");
        counter.hit(1);
        out.append(", null");
        counter.hit(2);
        out.append(", null");
        counter.hit(3);
        out.append(", null");
        out.append(", ").append(counter.getHits(4));
        counter.hit(300);
        out.append(", null");
        out.append(", ").append(counter.getHits(300));
        out.append(", ").append(counter.getHits(301));
        System.out.println(out.append("]"));
    }
}

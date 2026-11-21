import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * LeetCode 460. LFU Cache
 * https://leetcode.com/problems/lfu-cache/
 *
 * PROBLEM
 *   - Design a cache with a fixed capacity (1..10^4):
 *       get(key)        -> the value, or -1 if the key is missing.
 *       put(key, value) -> insert or update; when full and the key is new, first evict the
 *                          Least Frequently Used key. On a tie, evict the least recently used of them.
 *   - Each get or put of a key adds 1 to its use counter; a new key starts at 1.
 *   - get and put must each run in O(1) average time.
 *
 * EXAMPLE
 *   Input: ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
 *          [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
 *   Output: [null,null,null,1,null,-1,3,null,-1,3,4]
 *   Explanation: (cnt(x) = the use counter for key x; cache=[] shows the last used order)
 *     lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 *     lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 *     lfu.get(1);      // return 1; cache=[1,2], cnt(2)=1, cnt(1)=2
 *     lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
 *                      // cache=[3,1], cnt(3)=1, cnt(1)=2
 *     lfu.get(2);      // return -1 (not found)
 *     lfu.get(3);      // return 3; cache=[3,1], cnt(3)=2, cnt(1)=2
 *     lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
 *                      // cache=[4,3], cnt(4)=1, cnt(3)=2
 *     lfu.get(1);      // return -1 (not found)
 *     lfu.get(3);      // return 3; cache=[3,4], cnt(4)=1, cnt(3)=3
 *     lfu.get(4);      // return 4; cache=[4,3], cnt(4)=2, cnt(3)=3
 *
 * HOW TO THINK
 *   - Clue: evict by smallest count, ties by oldest use, all in O(1)
 *     -> group keys by count, and keep each group in use order.
 *   - Technique: three maps and one number.
 *       values[key]    -> value
 *       counts[key]    -> use count
 *       buckets[count] -> LinkedHashSet of keys with that count, oldest use first
 *       minCount       -> the smallest count that has keys (where eviction happens)
 *   - Rule that stays true: every key is in exactly one bucket, the one for its count;
 *     inside a bucket, the first key is the least recently used.
 *   - Decision:
 *       touch(key) (on get hit or update): move key from bucket c to the END of bucket c + 1;
 *         if bucket c is now empty and c == minCount, minCount++.
 *       put new key when full: evict the FIRST key of buckets[minCount].
 *       put new key: count 1, add to bucket 1, minCount = 1 (a new key is always the smallest).
 *
 * STEPS
 *   1. get(key): missing -> -1; else touch(key), return values[key].
 *   2. put(key, value): capacity 0 -> return. Key exists -> values[key] = value, touch(key), return.
 *   3.   If size == capacity: evict = first of buckets[minCount]; remove it from that bucket,
 *        values and counts.
 *   4.   values[key] = value, counts[key] = 1, add to buckets[1], minCount = 1.
 *   5. touch(key): c = counts[key]; counts[key] = c + 1; remove from buckets[c];
 *      if c == minCount and buckets[c] is empty: minCount++; add to the end of buckets[c + 1].
 *
 * DRY RUN   capacity = 2, the LeetCode example   (buckets: count -> keys, oldest use first)
 *
 *    op        | action                                        | buckets after         | min | returns
 *   -----------+-----------------------------------------------+-----------------------+-----+--------
 *    put(1,1)  | new, count 1                                  | 1:[1]                 |  1  |  -
 *    put(2,2)  | new, count 1                                  | 1:[1,2]               |  1  |  -
 *    get(1)    | touch 1: count 1 -> 2                         | 1:[2]  2:[1]          |  1  |  1
 *    put(3,3)  | full, evict first of bucket 1 = 2; add 3      | 1:[3]  2:[1]          |  1  |  -
 *    get(2)    | missing                                       | 1:[3]  2:[1]          |  1  | -1
 *    get(3)    | touch 3: 1 -> 2; bucket 1 empty, min = 2      | 2:[1,3]               |  2  |  3
 *    put(4,4)  | full, evict first of bucket 2 = 1 (LRU tie)   | 1:[4]  2:[3]          |  1  |  -
 *    get(1)    | missing                                       | 1:[4]  2:[3]          |  1  | -1
 *    get(3)    | touch 3: 2 -> 3                               | 1:[4]  3:[3]          |  1  |  3
 *    get(4)    | touch 4: 1 -> 2; bucket 1 empty, min = 2      | 2:[4]  3:[3]          |  2  |  4
 *   (empty buckets are not shown)
 *
 *   Prints [null, null, null, 1, null, -1, 3, null, -1, 3, 4].
 *
 * EDGE CASES
 *   capacity 1: put(1,1), put(2,2), get(1), get(2)  -> -1, 2    every new key evicts the old one
 *   put(1,1), put(1,5), get(1)                      -> 5        an update counts as a use, no eviction
 *   cap 2: put(1,1), put(2,2), get(1), get(2), put(3,3), get(1) -> -1   tie at count 2: 1 is older
 *   capacity 0: put(1,1), get(1)                    -> -1       nothing can be stored (older LeetCode versions)
 *
 * COMMON MISTAKES
 *   - Not resetting minCount to 1 after adding a new key: the next eviction looks in the wrong bucket.
 *   - Raising minCount whenever a bucket empties: only when the emptied bucket is the min bucket.
 *   - Evicting with a plain HashSet: no use order inside a count, so ties break randomly.
 *   - Evicting on an update of an existing key: only a new key can overflow the cache.
 *
 * COMPLEXITY
 *   O(1) average time for get and put (map and LinkedHashSet operations), O(capacity) extra space.
 *
 * RELATED
 *   LeetCode 146 (LRUCache, HashMap/): one use-ordered list; LFU is one such list per count.
 *   LeetCode 895 (FreqStack, FAQ/): also groups items into buckets by frequency.
 */
public class LFUCache {

    private final int capacity;
    private final Map<Integer, Integer> values = new HashMap<>();
    private final Map<Integer, Integer> counts = new HashMap<>();
    private final Map<Integer, LinkedHashSet<Integer>> buckets = new HashMap<>();
    private int minCount;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!values.containsKey(key)) return -1;
        touch(key);
        return values.get(key);
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        if (values.containsKey(key)) {
            values.put(key, value);
            touch(key);
            return;
        }
        if (values.size() == capacity) {
            LinkedHashSet<Integer> lowest = buckets.get(minCount);
            int evict = lowest.iterator().next();
            lowest.remove(evict);
            values.remove(evict);
            counts.remove(evict);
        }
        values.put(key, value);
        counts.put(key, 1);
        buckets.computeIfAbsent(1, c -> new LinkedHashSet<>()).add(key);
        minCount = 1;
    }

    private void touch(int key) {
        int c = counts.get(key);
        counts.put(key, c + 1);
        LinkedHashSet<Integer> bucket = buckets.get(c);
        bucket.remove(key);
        if (c == minCount && bucket.isEmpty()) minCount++;
        buckets.computeIfAbsent(c + 1, k -> new LinkedHashSet<>()).add(key);
    }

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        StringBuilder out = new StringBuilder("[null");
        lfu.put(1, 1);
        out.append(", null");
        lfu.put(2, 2);
        out.append(", null");
        out.append(", ").append(lfu.get(1));
        lfu.put(3, 3);
        out.append(", null");
        out.append(", ").append(lfu.get(2));
        out.append(", ").append(lfu.get(3));
        lfu.put(4, 4);
        out.append(", null");
        out.append(", ").append(lfu.get(1));
        out.append(", ").append(lfu.get(3));
        out.append(", ").append(lfu.get(4));
        System.out.println(out.append("]"));
    }
}

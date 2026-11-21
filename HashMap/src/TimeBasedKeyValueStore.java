import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 981. Time Based Key-Value Store  (LeetCode names the class TimeMap)
 * https://leetcode.com/problems/time-based-key-value-store/
 *
 * PROBLEM
 *   - Design a key-value store that keeps every version of a key:
 *       set(key, value, timestamp) -> store value for key at that time.
 *       get(key, timestamp)        -> the value from the latest set with time <= timestamp,
 *                                     or "" if there is none.
 *   - For one key, set is called with strictly increasing timestamps.
 *
 * EXAMPLE
 *   Input: ["TimeMap", "set", "get", "get", "set", "get", "get"]
 *          [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
 *   Output: [null, null, "bar", "bar", null, "bar2", "bar2"]
 *   Explanation:
 *     TimeMap timeMap = new TimeMap();
 *     timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
 *     timeMap.get("foo", 1);         // return "bar"
 *     timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at
 *                                    // timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
 *     timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
 *     timeMap.get("foo", 4);         // return "bar2"
 *     timeMap.get("foo", 5);         // return "bar2"
 *
 * HOW TO THINK
 *   - Clue: key lookup -> hash map. "Latest time <= t" on times that only grow -> each key's
 *     list is already sorted, so binary search it.
 *   - Technique: map key -> list of (time, value), appended in order by set.
 *   - Binary search for the LAST entry with time <= t:
 *       lo = 0, hi = size - 1, ans = -1.
 *       mid's time <= t -> it is a candidate: ans = mid, look right (lo = mid + 1).
 *       mid's time > t  -> too new, look left (hi = mid - 1).
 *   - Rule that stays true: every entry before lo has time <= t, every entry after hi has time > t,
 *     and ans is the best candidate found so far.
 *   - Return: ans == -1 -> "" (every version is newer than t, or the key is missing); else its value.
 *
 * STEPS
 *   1. set: map.computeIfAbsent(key, new list).add(entry(timestamp, value)).
 *   2. get: list = map.get(key); null -> "".
 *   3.      lo = 0, hi = size - 1, ans = -1; while lo <= hi: mid; time <= t -> ans = mid, lo = mid + 1;
 *           else hi = mid - 1.
 *   4.      Return ans == -1 ? "" : list[ans].value.
 *
 * DRY RUN   set(foo,a,1), set(foo,b,4), set(foo,c,7), set(foo,d,10), then get(foo,8) and get(foo,0)
 *           list for foo = [(1,a), (4,b), (7,c), (10,d)]
 *
 *    call         | lo | hi | mid | time[mid] | compare  | ans after | next
 *   --------------+----+----+-----+-----------+----------+-----------+----------
 *    get(foo, 8)  |  0 |  3 |  1  |     4     | 4 <= 8   |     1     | lo = 2
 *                 |  2 |  3 |  2  |     7     | 7 <= 8   |     2     | lo = 3
 *                 |  3 |  3 |  3  |    10     | 10 > 8   |     2     | hi = 2, stop
 *    get(foo, 0)  |  0 |  3 |  1  |     4     | 4 > 0    |    -1     | hi = 0
 *                 |  0 |  0 |  0  |     1     | 1 > 0    |    -1     | hi = -1, stop
 *
 *   get(foo, 8) returns "c" (time 7). get(foo, 0) returns "" (every version is newer).
 *
 * EDGE CASES
 *   get("bar", 5) with no set for "bar"      -> ""       missing key
 *   set(k,v,5), get(k,5)                     -> "v"      time equal to t counts
 *   set(k,v,5), get(k,4)                     -> ""       only newer versions
 *   set(k,v1,1), set(k,v2,2), get(k,10^7)    -> "v2"     t after every version: the last one
 *   the LeetCode example                     -> null, "bar", "bar", null, "bar2", "bar2"
 *
 * COMMON MISTAKES
 *   - Scanning the list from the end: correct, but O(n) per get.
 *   - Stopping at the first mid with time <= t: there may be a later one; keep searching right.
 *   - Using a TreeMap per key and forgetting floorEntry can be null: that's the "" case.
 *
 * COMPLEXITY
 *   set: O(1) amortised. get: O(log m), m = versions of that key. O(total sets) extra space.
 *
 * RELATED
 *   LeetCode 704 (Binary Search): the plain search this builds on; here it looks for the last time <= t.
 *   LeetCode 362 (DesignHitCounter, HashMap/): another design with non-decreasing timestamps.
 *   LeetCode 146 (LRUCache, HashMap/): a map whose values are list nodes.
 */
public class TimeBasedKeyValueStore {

    private static class Entry {
        final int time;
        final String value;

        Entry(int time, String value) {
            this.time = time;
            this.value = value;
        }
    }

    private final Map<String, List<Entry>> map = new HashMap<>();

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(new Entry(timestamp, value));
    }

    public String get(String key, int timestamp) {
        List<Entry> list = map.get(key);
        if (list == null) return "";
        int lo = 0;
        int hi = list.size() - 1;
        int ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (list.get(mid).time <= timestamp) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans == -1 ? "" : list.get(ans).value;
    }

    public static void main(String[] args) {
        TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();
        store.set("foo", "a", 1);
        store.set("foo", "b", 4);
        store.set("foo", "c", 7);
        store.set("foo", "d", 10);
        System.out.println("\"" + store.get("foo", 8) + "\" \"" + store.get("foo", 0) + "\"");
    }
}

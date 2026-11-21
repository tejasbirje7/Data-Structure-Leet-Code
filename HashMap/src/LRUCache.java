import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 146. LRU Cache
 * https://leetcode.com/problems/lru-cache/
 *
 * PROBLEM
 *   - Design a cache with a fixed capacity (1..3000):
 *       get(key)        -> the value, or -1 if the key is missing.
 *       put(key, value) -> insert or update; if this makes size > capacity,
 *                          evict the Least Recently Used key.
 *   - Both a get and a put count as "using" a key.
 *   - get and put must each run in O(1) average time.
 *
 * EXAMPLE
 *   Input: ["LRUCache","put","put","get","put","get","put","get","get","get"]
 *          [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
 *   Output: [null,null,null,1,null,-1,null,-1,3,4]
 *   Explanation:
 *     LRUCache lRUCache = new LRUCache(2);
 *     lRUCache.put(1, 1); // cache is {1=1}
 *     lRUCache.put(2, 2); // cache is {1=1, 2=2}
 *     lRUCache.get(1);    // return 1
 *     lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 *     lRUCache.get(2);    // returns -1 (not found)
 *     lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 *     lRUCache.get(1);    // return -1 (not found)
 *     lRUCache.get(3);    // return 3
 *     lRUCache.get(4);    // return 4
 *
 * HOW TO THINK
 *   - Clue: O(1) lookup -> hash map. O(1) "move to most recent" and "remove least recent"
 *     -> a doubly linked list ordered by use.
 *   - Technique: map key -> list node; the list runs from most recent (after head) to
 *     least recent (before tail). head and tail are dummy sentinels, so there are no null checks.
 *   - Rule that stays true: the map and the list hold exactly the same keys, and the list order
 *     is the order of last use.
 *   - Decision:
 *       get: missing -> -1. Found -> unlink the node, put it right after head, return its value.
 *       put: key exists -> update the value, move to front.
 *            new key -> make a node, put it at the front, add to the map;
 *            if size > capacity -> remove tail.prev (the LRU node) from the list and the map.
 *   - The node stores its key so eviction knows which map entry to delete.
 *
 * STEPS
 *   1. head <-> tail sentinels; empty map.
 *   2. get(key): node = map.get(key); null -> -1; else remove(node), addFront(node), return node.value.
 *   3. put(key, value): if node exists: set value, remove, addFront.
 *   4.   else: new node, addFront, map.put; if map.size() > capacity: lru = tail.prev,
 *        remove(lru), map.remove(lru.key).
 *   5. remove(node): node.prev.next = node.next; node.next.prev = node.prev.
 *      addFront(node): insert between head and head.next.
 *
 * DRY RUN   capacity = 2, the LeetCode example   (list shown most recent -> least recent)
 *
 *    op        | found? | action                              | list after  | returns
 *   -----------+--------+-------------------------------------+-------------+--------
 *    put(1,1)  |  no    | add 1 at front                      | [1]         |  -
 *    put(2,2)  |  no    | add 2 at front                      | [2,1]       |  -
 *    get(1)    |  yes   | move 1 to front                     | [1,2]       |  1
 *    put(3,3)  |  no    | add 3; size 3 > 2, evict tail.prev=2| [3,1]       |  -
 *    get(2)    |  no    | -                                   | [3,1]       | -1
 *    put(4,4)  |  no    | add 4; size 3 > 2, evict tail.prev=1| [4,3]       |  -
 *    get(1)    |  no    | -                                   | [4,3]       | -1
 *    get(3)    |  yes   | move 3 to front                     | [3,4]       |  3
 *    get(4)    |  yes   | move 4 to front                     | [4,3]       |  4
 *
 *   Prints [null, null, null, 1, null, -1, null, -1, 3, 4].
 *
 * EDGE CASES
 *   capacity 1: put(1,1), put(2,2), get(1)    -> -1     every new key evicts the old one
 *   put(1,1), put(1,5), get(1)                -> 5      update keeps one entry, no eviction
 *   cap 2: put(1,1), put(2,2), put(1,9), put(3,3), get(2) -> -1   updating 1 made 2 the LRU
 *   get on an empty cache                     -> -1
 *
 * COMMON MISTAKES
 *   - Not moving a key to the front on get (or on an update): the wrong key gets evicted.
 *   - Evicting when size == capacity even if the key already exists: an update must never evict.
 *   - Forgetting map.remove(lru.key) on eviction: the map grows and get returns a stale node.
 *   - A singly linked list: removing a middle node needs its prev, so it is O(n).
 *
 * COMPLEXITY
 *   O(1) average time for get and put, O(capacity) extra space.
 *
 * RELATED
 *   LeetCode 460 (LFUCache, HashMap/): evicts the least FREQUENTLY used key, LRU as the tie-break.
 *   LeetCode 706 (DesignHashMap, HashMap/): builds the map itself with bucket chains.
 *   Java's LinkedHashMap(capacity, 0.75f, true) with removeEldestEntry does the same in a few lines.
 */
public class LRUCache {

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(0, 0);
    private final Node tail = new Node(0, 0);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        remove(node);
        addFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            remove(node);
            addFront(node);
            return;
        }
        node = new Node(key, value);
        addFront(node);
        map.put(key, node);
        if (map.size() > capacity) {
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        StringBuilder out = new StringBuilder("[null");
        cache.put(1, 1);
        out.append(", null");
        cache.put(2, 2);
        out.append(", null");
        out.append(", ").append(cache.get(1));
        cache.put(3, 3);
        out.append(", null");
        out.append(", ").append(cache.get(2));
        cache.put(4, 4);
        out.append(", null");
        out.append(", ").append(cache.get(1));
        out.append(", ").append(cache.get(3));
        out.append(", ").append(cache.get(4));
        System.out.println(out.append("]"));
    }
}

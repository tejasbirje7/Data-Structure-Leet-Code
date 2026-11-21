/**
 * LeetCode 706. Design HashMap  (LeetCode names the class MyHashMap)
 * https://leetcode.com/problems/design-hashmap/
 *
 * PROBLEM
 *   - Build a hash map from int keys to int values without any built-in hash table:
 *       put(key, value) -> insert, or update the value if the key exists.
 *       get(key)        -> the value, or -1 if the key is missing.
 *       remove(key)     -> delete the key if it exists.
 *   - Keys and values are in 0..10^6; at most 10^4 calls.
 *
 * EXAMPLE
 *   Input: ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 *          [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 *   Output: [null, null, null, 1, -1, null, 1, null, -1]
 *   Explanation:
 *     MyHashMap myHashMap = new MyHashMap();
 *     myHashMap.put(1, 1); // The map is now [[1,1]]
 *     myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
 *     myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
 *     myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
 *     myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
 *     myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
 *     myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
 *     myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
 *
 * HOW TO THINK
 *   - Clue: "without built-in hash table libraries" = build buckets + collision handling yourself.
 *   - Technique: separate chaining. An array of SIZE buckets; key goes to bucket key % SIZE;
 *     each bucket is a singly linked list of (key, value) nodes.
 *   - SIZE = 1009, a prime: keys spread evenly, and 10^4 keys give chains of about 10 nodes.
 *   - Rule that stays true: each key appears at most once, and only in bucket key % SIZE.
 *   - Decision:
 *       put: walk the chain; key found -> update value; not found -> add a new node at the head.
 *       get: walk the chain; key found -> its value; else -1.
 *       remove: walk with a prev pointer; on the key, unlink it (head -> move bucket to next,
 *               middle -> prev.next = cur.next).
 *
 * STEPS
 *   1. buckets = new Node[1009].
 *   2. put: for node in chain(key % SIZE): if node.key == key: node.value = value, return.
 *      Then new node with next = bucket head; bucket head = new node.
 *   3. get: for node in chain: if node.key == key return node.value. Return -1.
 *   4. remove: prev = null, cur = head; while cur: if cur.key == key: unlink, return; prev = cur, cur = cur.next.
 *
 * DRY RUN   the LeetCode example, then put(1010,5), get(1), remove(1010), get(1010)
 *           (1010 % 1009 = 1, so 1010 shares bucket 1 with key 1; chains shown head first)
 *
 *    op            | bucket | action                                    | bucket chain after | returns
 *   ---------------+--------+-------------------------------------------+--------------------+--------
 *    put(1,1)      |   1    | not in chain, add at head                 | 1: [1=1]           |  -
 *    put(2,2)      |   2    | not in chain, add at head                 | 2: [2=2]           |  -
 *    get(1)        |   1    | found 1                                   | 1: [1=1]           |  1
 *    get(3)        |   3    | empty chain                               | 3: []              | -1
 *    put(2,1)      |   2    | found 2, update value                     | 2: [2=1]           |  -
 *    get(2)        |   2    | found 2                                   | 2: [2=1]           |  1
 *    remove(2)     |   2    | found at head, bucket = head.next         | 2: []              |  -
 *    get(2)        |   2    | empty chain                               | 2: []              | -1
 *    put(1010,5)   |   1    | walk past 1, not found, add at head       | 1: [1010=5, 1=1]   |  -
 *    get(1)        |   1    | skip 1010, found 1                        | 1: [1010=5, 1=1]   |  1
 *    remove(1010)  |   1    | found at head, bucket = head.next         | 1: [1=1]           |  -
 *    get(1010)     |   1    | walk past 1, not found                    | 1: [1=1]           | -1
 *
 *   Prints [null, null, null, 1, -1, null, 1, null, -1, null, 1, null, -1].
 *
 * EDGE CASES
 *   put(0,0), get(0)              -> 0    key 0 and value 0 are valid (bucket 0)
 *   put(1,1), put(1010,2), remove(1), get(1010) -> 2   1 is not the head: prev.next skips it, 1010 stays
 *   remove(7) on an empty map     -> no change, no error
 *   put(10^6, 10^6), get(10^6)    -> 10^6   largest key and value
 *   get(5) never put              -> -1
 *
 * COMMON MISTAKES
 *   - Adding a new node without first checking for the key: duplicates, and get may return the old value.
 *   - Removing the head node with prev.next = ...: prev is null there; update the bucket itself.
 *   - A plain int[10^6 + 1] array: works for this key range, but it is not a hash map
 *     and doesn't handle large or negative keys.
 *
 * COMPLEXITY
 *   O(n / SIZE) average per operation (the chain length), O(n) worst case if all keys collide.
 *   O(SIZE + n) extra space for the buckets and nodes.
 *
 * RELATED
 *   LeetCode 146 (LRUCache, HashMap/): uses a hash map plus a linked list; this is the map part built by hand.
 *   LeetCode 705 (Design HashSet): the same chaining, storing keys only.
 */
public class DesignHashMap {

    private static final int SIZE = 1009;

    private static class Node {
        final int key;
        int value;
        Node next;

        Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private final Node[] buckets = new Node[SIZE];

    public void put(int key, int value) {
        int i = key % SIZE;
        for (Node node = buckets[i]; node != null; node = node.next) {
            if (node.key == key) {
                node.value = value;
                return;
            }
        }
        buckets[i] = new Node(key, value, buckets[i]);
    }

    public int get(int key) {
        for (Node node = buckets[key % SIZE]; node != null; node = node.next) {
            if (node.key == key) return node.value;
        }
        return -1;
    }

    public void remove(int key) {
        int i = key % SIZE;
        Node prev = null;
        Node cur = buckets[i];
        while (cur != null) {
            if (cur.key == key) {
                if (prev == null) buckets[i] = cur.next;
                else prev.next = cur.next;
                return;
            }
            prev = cur;
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        DesignHashMap map = new DesignHashMap();
        StringBuilder out = new StringBuilder("[null");
        map.put(1, 1);
        out.append(", null");
        map.put(2, 2);
        out.append(", null");
        out.append(", ").append(map.get(1));
        out.append(", ").append(map.get(3));
        map.put(2, 1);
        out.append(", null");
        out.append(", ").append(map.get(2));
        map.remove(2);
        out.append(", null");
        out.append(", ").append(map.get(2));
        map.put(1010, 5);
        out.append(", null");
        out.append(", ").append(map.get(1));
        map.remove(1010);
        out.append(", null");
        out.append(", ").append(map.get(1010));
        System.out.println(out.append("]"));
    }
}

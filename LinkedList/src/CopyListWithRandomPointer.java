import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 138. Copy List with Random Pointer
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 *
 * PROBLEM
 *   - Input: a linked list of n nodes; each node has next and an extra random pointer
 *     (random can point to any node in the list, or be null).
 *   - Return: a deep copy: exactly n brand new nodes with the same values.
 *   - The copy's next and random must point to nodes of the copy, never to the original list.
 *     (If X.random -> Y in the original, then x.random -> y in the copy.)
 *
 * EXAMPLE
 *   Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 *   Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * HOW TO THINK
 *   - Clue: random can point forward, to a node whose copy doesn't exist yet.
 *     So you can't wire everything while you create nodes in one pass.
 *   - Technique: hash map old node -> its copy, built in two passes.
 *     Pass 1 creates every copy. Pass 2 wires next and random by looking them up in the map.
 *   - Rule that stays true: after pass 1, map.get(old) is the copy of old for every node,
 *     and map.get(null) = null (put in on purpose, so a null pointer copies to null).
 *   - Decision: pass 2, for each old node: copy.next = map.get(old.next), copy.random = map.get(old.random).
 *   - Return: map.get(head), the copy of the head (null when head is null).
 *   - Why it works: the map is keyed by node identity, not by value, so equal values don't clash.
 *
 * STEPS
 *   1. map = new HashMap, map.put(null, null).
 *   2. Pass 1: for each node curr, map.put(curr, new Node(curr.val)).
 *   3. Pass 2: for each node curr, copy = map.get(curr);
 *      copy.next = map.get(curr.next); copy.random = map.get(curr.random).
 *   4. Return map.get(head).
 *
 * DRY RUN   head = [[7,null],[13,0],[11,4],[10,2],[1,0]]   (nodes #0..#4; x' = the copy of x)
 *
 *   Pass 1 (create copies):
 *    curr     | action      | map after
 *   ----------+-------------+----------------------------------------------
 *    #0 (7)   | new node 7' | null -> null, 7 -> 7'
 *    #1 (13)  | new 13'     | ..., 13 -> 13'
 *    #2 (11)  | new 11'     | ..., 11 -> 11'
 *    #3 (10)  | new 10'     | ..., 10 -> 10'
 *    #4 (1)   | new 1'      | ..., 1 -> 1'   (6 entries)
 *
 *   Pass 2 (wire pointers):
 *    curr     | curr.next -> copy.next | curr.random -> copy.random
 *   ----------+------------------------+----------------------------
 *    #0 (7)   | #1 (13) -> 13'         | null    -> null
 *    #1 (13)  | #2 (11) -> 11'         | #0 (7)  -> 7'
 *    #2 (11)  | #3 (10) -> 10'         | #4 (1)  -> 1'
 *    #3 (10)  | #4 (1)  -> 1'          | #2 (11) -> 11'
 *    #4 (1)   | null    -> null        | #0 (7)  -> 7'
 *
 *   Returns 7': the copy reads [[7,null],[13,0],[11,4],[10,2],[1,0]], and every node in it is new.
 *
 * EDGE CASES
 *   []                         -> []                         both loops skip; map.get(null) = null
 *   [[1,0]]                    -> [[1,0]]                    random points to itself; the copy points to itself
 *   [[1,1],[2,1]]              -> [[1,1],[2,1]]              two randoms share one target
 *   [[3,null],[3,0],[3,null]]  -> [[3,null],[3,0],[3,null]]  equal values are fine: keys are nodes, not values
 *
 * COMMON MISTAKES
 *   - copy.random = curr.random: that points into the original list. Always go through the map.
 *   - Wiring random in the same pass that creates nodes: the target's copy may not exist yet.
 *   - Keying the map by value: duplicate values overwrite each other. Key by the node itself.
 *
 * COMPLEXITY
 *   O(n) time (two passes), O(n) extra space for the map.
 *   (Weaving each copy right after its original gives O(1) extra space, but it's harder to get right.)
 *
 * RELATED
 *   LeetCode 205 (IsomorphicStrings, HashMap/): also a map from each item to its partner, filled in one pass.
 *   LeetCode 21 (MergeTwoLinkedList, LinkedList/): plain next-only list; reuses nodes instead of copying them.
 */
public class CopyListWithRandomPointer {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        Map<Node,Node> map = new HashMap<>();
        map.put(null,null);
        Node curr = head;

        while (curr != null) {
            Node copyNode = new Node(curr.val);
            map.put(curr,copyNode);
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            Node copyNode = map.get(curr);
            copyNode.next = map.get(curr.next);
            copyNode.random = map.get(curr.random);
            curr = curr.next;
        }

        return map.get(head);

    }
}

import java.util.*;

/**
 * LeetCode 380. Insert Delete GetRandom O(1)
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 *
 * PROBLEM
 *   - Design a set of ints with three operations:
 *     insert(val) -> true if val was added, false if already there.
 *     remove(val) -> true if val was removed, false if not there.
 *     getRandom() -> any current value, each with the same chance.
 *   - Constraint: every operation must be O(1) on average.
 *   - getRandom is only called when the set has at least one value.
 *
 * EXAMPLE
 *   Input
 *   ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 *   [[], [1], [2], [2], [], [1], [2], []]
 *   Output
 *   [null, true, false, true, 2, true, false, 2]
 *
 *   Explanation
 *   RandomizedSet randomizedSet = new RandomizedSet();
 *   randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 *   randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
 *   randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
 *   randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
 *   randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
 *   randomizedSet.insert(2); // 2 was already in the set, so return false.
 *   randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 *
 * HOW TO THINK
 *   - Clue: "O(1) insert/remove" points to a HashMap; "O(1) random pick" needs an index, so a list.
 *     Neither alone does all three.
 *   - Technique: list + map together.
 *     store (ArrayList) holds the values; mapping (HashMap) holds value -> its index in store.
 *   - Rule that stays true: mapping.get(v) == i exactly when store.get(i) == v,
 *     and store has no gaps (values sit in slots 0 .. size-1).
 *   - Decision: insert: already in mapping -> false; else append to store, map it to the last index.
 *               remove: not in mapping -> false; else copy the LAST value into val's slot,
 *                 update that value's index in mapping, drop the last slot, then remove val from mapping.
 *               getRandom: pick a random index in 0 .. size-1.
 *   - Why swap with last: removing from the end of an ArrayList is O(1); removing from the middle shifts
 *     everything after it.
 *   - Return: the booleans for insert/remove, a value for getRandom.
 *
 * STEPS
 *   1. insert(val): if mapping has val, return false. Else store.add(val), mapping.put(val, size - 1), return true.
 *   2. remove(val): if mapping lacks val, return false.
 *   3.   index = mapping.get(val); store[index] = last value; mapping.put(last value, index).
 *   4.   Remove the last slot of store, then mapping.remove(val); return true.
 *   5. getRandom(): return store.get(random index in 0 .. size-1).
 *
 * DRY RUN   the LeetCode example: insert(1), remove(2), insert(2), getRandom(), remove(1), insert(2), getRandom()
 *   (store and mapping are after the step)
 *
 *    op               | detail                                   | returns | store  | mapping
 *   ------------------+------------------------------------------+---------+--------+--------------
 *    new              |                                          |  -      | []     | {}
 *    insert(1)        | 1 not in mapping, append at index 0      |  true   | [1]    | {1=0}
 *    remove(2)        | 2 not in mapping                         |  false  | [1]    | {1=0}
 *    insert(2)        | 2 not in mapping, append at index 1      |  true   | [1,2]  | {1=0, 2=1}
 *    getRandom()      | random index 0 or 1                      |  1 or 2 | [1,2]  | {1=0, 2=1}
 *    remove(1)        | index = 0; store[0] = 2 (last value)     |         | [2,2]  | {1=0, 2=1}
 *                     | mapping.put(2, 0)                        |         | [2,2]  | {1=0, 2=0}
 *                     | drop last slot                           |         | [2]    | {1=0, 2=0}
 *                     | mapping.remove(1)                        |  true   | [2]    | {2=0}
 *    insert(2)        | 2 already in mapping                     |  false  | [2]    | {2=0}
 *    getRandom()      | only index 0                             |  2      | [2]    | {2=0}
 *
 *   Outputs: [null, true, false, true, 1 or 2, true, false, 2]. Final state: store = [2], mapping = {2=0}.
 *
 * EDGE CASES
 *   insert(5), remove(5)                         -> true, true; set empty    val is also the last value: copied onto
 *                                                                           itself, then mapping.remove(val) runs last
 *   insert(1), insert(2), remove(2)              -> store [1], {1=0}         the last slot is removed, no real swap
 *   insert(1), insert(2), remove(1), insert(1)   -> store [2,1], {2=0, 1=1}  a removed value can be added again
 *   getRandom() on an empty set                  -> throws IllegalArgumentException (nextInt(0));
 *                                                   LeetCode guarantees at least one value when it is called
 *
 * COMMON MISTAKES
 *   - store.remove(index) in the middle: O(n) shift, and every later value's index in mapping goes stale.
 *     Copy the last value into the hole, then remove the last slot.
 *   - Calling mapping.remove(val) before mapping.put(last, index): when val IS the last value,
 *     the put adds it back. Remove val from mapping last.
 *   - Forgetting to update the moved value's index in mapping: later removes hit the wrong slot.
 *   - Using only a HashSet: there is no O(1) way to pick a random element from it.
 *
 * COMPLEXITY
 *   O(1) average time per operation (hash map + end-of-list changes), O(n) extra space.
 *
 * RELATED
 *   LeetCode 155 (MinStack, Stack/): another O(1)-per-operation design; keeps a second structure
 *     next to the main one, like mapping next to store here.
 *   LeetCode 895 (FreqStack, FAQ/): design with a HashMap plus stacks to answer a query in O(1).
 *   LeetCode 1 (TwoSum, HashMap/): the same "value -> index" HashMap trick.
 */
class RandomizedSet {
    Map<Integer,Integer> mapping;
    List<Integer> store;

    public RandomizedSet() {
        mapping = new HashMap<>();
        store = new ArrayList<>();
    }

    public boolean insert(int val) {
        if(mapping.containsKey(val))
            return false;
        else {
            store.add(val);
            mapping.put(val,store.size() - 1);
            return true;
        }
    }

    public boolean remove(int val) {
        if(!mapping.containsKey(val))
            return false;
        else {
            int index = mapping.get(val);
            store.set(index,store.get(store.size()-1));
            mapping.put(store.get(index),index);
            store.remove(store.size() - 1);
            mapping.remove(val);
            return true;
        }
    }

    public int getRandom() {
        Random rand = new Random();
        return store.get(rand.nextInt(store.size()));
    }
}
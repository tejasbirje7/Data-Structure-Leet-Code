import java.util.*;
/**
 * LeetCode 895. Maximum Frequency Stack
 * https://leetcode.com/problems/maximum-frequency-stack/
 *
 * PROBLEM
 *   - Design a stack with push(val) and pop().
 *   - pop() removes and returns the most frequent value in the stack.
 *   - Tie on frequency: pop the one pushed most recently (closest to the top).
 *   - pop() is only called when the stack is not empty.
 *
 * EXAMPLE
 *   FreqStack freqStack = new FreqStack();
 *   freqStack.push(5); // stack: [5]
 *   freqStack.push(7); // stack: [5, 7]
 *   freqStack.push(5); // stack: [5, 7, 5]
 *   freqStack.push(7); // stack: [5, 7, 5, 7]
 *   freqStack.push(4); // stack: [5, 7, 5, 7, 4]
 *   freqStack.push(5); // stack: [5, 7, 5, 7, 4, 5]
 *   System.out.println(freqStack.pop()); // 5 (most frequent)
 *   System.out.println(freqStack.pop()); // 7 (tie but closer to top)
 *   System.out.println(freqStack.pop()); // 5
 *   System.out.println(freqStack.pop()); // 4
 *
 * HOW TO THINK
 *   - Clue: "most frequent" + "tie -> most recent" = group values by frequency,
 *     and keep each group in push order (a stack).
 *   - Technique: two hash maps + a max counter.
 *     freqMap: value -> how many times it is in the stack now. e.g. {5: 3, 7: 2, 4: 1}
 *     groupStack: frequency f -> stack of values that reached count f, in push order. e.g. {1: [5,7,4], 2: [5,7], 3: [5]}
 *     maxFreq: the highest frequency in the stack right now.
 *   - Rule that stays true: a value with count c sits in stacks 1, 2, ..., c (one copy each).
 *     So when the top group empties, group maxFreq - 1 is never empty.
 *   - Decision (push): count = freqMap[val] + 1. If count > maxFreq, raise maxFreq and start a new stack.
 *     Push val onto groupStack[count].
 *   - Decision (pop): pop the top of groupStack[maxFreq] (most frequent, and most recent among ties).
 *     Lower its count. If that group is now empty, maxFreq--.
 *   - Return: pop returns the value taken from groupStack[maxFreq].
 *
 * STEPS
 *   push(val):
 *   1. count = freqMap.getOrDefault(val, 0) + 1; save it in freqMap.
 *   2. If count > maxFreq: maxFreq = count and groupStack[count] = new empty stack.
 *   3. groupStack[count].push(val).
 *   pop():
 *   1. res = groupStack[maxFreq].pop().
 *   2. freqMap[res] = freqMap[res] - 1.
 *   3. If groupStack[maxFreq] is empty, maxFreq--.
 *   4. Return res.
 *
 * DRY RUN   the EXAMPLE sequence   (all columns show state after the operation)
 *
 *    op      | freqMap        | groupStack (bottom..top)    | maxFreq | returns
 *   ---------+----------------+-----------------------------+---------+---------
 *    push(5) | {5:1}          | 1:[5]                       |    1    |
 *    push(7) | {5:1,7:1}      | 1:[5,7]                     |    1    |
 *    push(5) | {5:2,7:1}      | 1:[5,7] 2:[5]               |    2    |   (new top group)
 *    push(7) | {5:2,7:2}      | 1:[5,7] 2:[5,7]             |    2    |
 *    push(4) | {5:2,7:2,4:1}  | 1:[5,7,4] 2:[5,7]           |    2    |
 *    push(5) | {5:3,7:2,4:1}  | 1:[5,7,4] 2:[5,7] 3:[5]     |    3    |   (new top group)
 *    pop()   | {5:2,7:2,4:1}  | 1:[5,7,4] 2:[5,7] 3:[]      |    2    |    5    group 3 empty
 *    pop()   | {5:2,7:1,4:1}  | 1:[5,7,4] 2:[5]   3:[]      |    2    |    7
 *    pop()   | {5:1,7:1,4:1}  | 1:[5,7,4] 2:[]    3:[]      |    1    |    5    group 2 empty
 *    pop()   | {5:1,7:1,4:0}  | 1:[5,7]   2:[]    3:[]      |    1    |    4
 *
 *   Pops return 5, 7, 5, 4. Final: group 1 = [5,7], maxFreq = 1.
 *   Empty groups and the 4:0 entry stay in the maps; they do no harm.
 *
 * EDGE CASES
 *   push 1, push 2, pop, pop   -> 2, 1   every count is 1, so it acts like a normal stack
 *   push 1, push 1, pop, pop   -> 1, 1   the value sits in groups 1 and 2
 *   push 5 x3, pop, push 5, pop -> 5, 5  count goes 3 -> 2 -> 3; a fresh stack for group 3 is made again
 *   pop() on an empty stack    -> crashes with NullPointerException (groupStack.get(0) is null);
 *                                 LeetCode guarantees pop is only called on a non-empty stack
 *
 * COMMON MISTAKES
 *   - Keeping only one copy of each value (moving it between groups): after a pop you lose
 *     its place in the lower group. Leave the old copies; push a new copy at each new count.
 *   - Scanning for the max frequency on every pop: O(n). maxFreq only ever drops by 1, so keep a counter.
 *   - Using a priority queue on (count, push time): works, but O(log n) per operation instead of O(1).
 *   - Forgetting maxFreq-- when the top group empties: the next pop hits an empty stack.
 *
 * COMPLEXITY
 *   push and pop are O(1) time. O(n) extra space for the maps and group stacks (n = pushes).
 *
 * RELATED
 *   LeetCode 155 (MinStack, Stack/): a stack with an extra query (getMin), using a second stack.
 *   LeetCode 451 (SortCharactersByFrequency, FAQ/): also groups values by frequency (buckets).
 *   LeetCode 380 (RandomizedSet, ArraysAndStrings/): another O(1) design built from a map plus a list.
 */
public class FreqStack {

    private Map<Integer, Integer> freqMap;

    private Map<Integer, Stack<Integer>> groupStack;

    private int maxFreq;

    public FreqStack() {
        freqMap = new HashMap<>();
        groupStack = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        int valCount = freqMap.getOrDefault(val, 0) + 1;
        freqMap.put(val, valCount);

        if (valCount > maxFreq) {
            maxFreq = valCount;
            groupStack.put(valCount, new Stack<>());
        }

        groupStack.get(valCount).push(val);
    }

    public int pop() {
        int res = groupStack.get(maxFreq).pop();

        freqMap.put(res, freqMap.get(res) - 1);

        if (groupStack.get(maxFreq).isEmpty()) {
            maxFreq--;
        }

        return res;
    }

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);

        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
    }
}

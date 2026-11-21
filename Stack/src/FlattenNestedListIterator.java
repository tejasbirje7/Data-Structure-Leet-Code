import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * LeetCode 341. Flatten Nested List Iterator  (LeetCode names the class NestedIterator)
 * https://leetcode.com/problems/flatten-nested-list-iterator/
 *
 * PROBLEM
 *   - Input: a nested list; each element is either an integer or a list (which can nest again).
 *     LeetCode gives the NestedInteger interface: isInteger(), getInteger(), getList().
 *   - Build an iterator: next() returns the integers in order, flattened; hasNext() says if any
 *     integers are left.
 *   - Lists can be empty, so a nested list may contain no integers at all.
 *
 * EXAMPLE
 *   Input: nestedList = [[1,1],2,[1,1]]
 *   Output: [1,1,2,1,1]
 *   Explanation: By calling next repeatedly until hasNext returns false, the order of elements
 *   returned by next should be: [1,1,2,1,1].
 *
 * HOW TO THINK
 *   - Clue: nested lists = a tree walked left to right; an iterator must pause mid-walk ->
 *     keep the walk's state in an explicit stack instead of recursion.
 *   - Technique: the stack holds the elements still to visit, the next one on top. Push a list's
 *     elements in REVERSE so its first element ends up on top.
 *   - hasNext() does the real work: while the top is a list, pop it and push its elements.
 *     When the top is an integer, it is the next value. Empty lists just disappear.
 *   - Rule that stays true: after hasNext() returns true, the top of the stack is the next integer.
 *   - Decision in hasNext(): top is a list -> unpack it; top is an integer -> stop.
 *   - Return: next() = hasNext() check, then pop the top's integer.
 *
 * STEPS
 *   1. Constructor: push the list's elements in reverse.
 *   2. hasNext(): while stack not empty and top is a list -> pop it, push its elements in reverse.
 *      Return !stack.isEmpty().
 *   3. next(): if !hasNext() throw; return pop().getInteger().
 *
 * DRY RUN   nestedList = [1,[4,[6]]]   (stack shown top -> bottom, after the call)
 *
 *    call        | work done                         | returns | stack after
 *   -------------+-----------------------------------+---------+--------------
 *    constructor | push [4,[6]], then 1              |    -    | 1, [4,[6]]
 *    hasNext()   | top 1 is an integer               |  true   | 1, [4,[6]]
 *    next()      | pop 1                             |    1    | [4,[6]]
 *    hasNext()   | unpack [4,[6]]: push [6], then 4  |  true   | 4, [6]
 *    next()      | pop 4                             |    4    | [6]
 *    hasNext()   | unpack [6]: push 6                |  true   | 6
 *    next()      | pop 6                             |    6    | (empty)
 *    hasNext()   | stack empty                       |  false  | (empty)
 *
 *   Output [1,4,6].
 *
 * EDGE CASES
 *   [[1,1],2,[1,1]]   -> [1,1,2,1,1]   official example 1
 *   []                -> []            hasNext() is false right away
 *   [[]]              -> []            the only list is empty: hasNext() unpacks it to nothing
 *   [[],[3]]          -> [3]           an empty list before a real value is skipped
 *   [[[[5]]]]         -> [5]           deep nesting: unpacked one level per loop turn
 *   [1,2,3]           -> [1,2,3]       already flat
 *
 * COMMON MISTAKES
 *   - hasNext() returning !stack.isEmpty() without unpacking: [[]] says true, then next() fails.
 *   - Pushing a list's elements in forward order: the output comes out reversed.
 *   - Unpacking only in next(): hasNext() can't know whether the lists left hold any integer.
 *
 * COMPLEXITY
 *   Every element and list is pushed and popped once: O(N + L) over the whole iteration
 *   (N integers, L lists), so amortised O(1) per call. O(N + L) extra space in the worst case.
 *
 * RELATED
 *   LeetCode 173 (Binary Search Tree Iterator, not in repo yet): the same lazy iterator idea,
 *     with a stack of tree nodes.
 *   LeetCode 394 (DecodeString, Stack/): another stack that handles nesting one level at a time.
 *   LeetCode 297 (SerializeDeserializeTree, Trees/): another nested structure walked in order.
 */
public class FlattenNestedListIterator implements Iterator<Integer> {

    interface NestedInteger {
        boolean isInteger();

        Integer getInteger();

        List<NestedInteger> getList();
    }

    private final Deque<NestedInteger> stack = new ArrayDeque<>();

    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        pushAll(nestedList);
    }

    private void pushAll(List<NestedInteger> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            stack.push(list.get(i));
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            pushAll(stack.pop().getList());
        }
        return !stack.isEmpty();
    }

    private static NestedInteger num(int value) {
        return new NestedInteger() {
            public boolean isInteger() { return true; }
            public Integer getInteger() { return value; }
            public List<NestedInteger> getList() { return null; }
        };
    }

    private static NestedInteger list(NestedInteger... items) {
        List<NestedInteger> inner = Arrays.asList(items);
        return new NestedInteger() {
            public boolean isInteger() { return false; }
            public Integer getInteger() { return null; }
            public List<NestedInteger> getList() { return inner; }
        };
    }

    private static List<Integer> drain(NestedInteger... top) {
        FlattenNestedListIterator it = new FlattenNestedListIterator(Arrays.asList(top));
        List<Integer> out = new ArrayList<>();
        while (it.hasNext()) {
            out.add(it.next());
        }
        return out;
    }

    public static void main(String[] args) {
        System.out.println(drain(num(1), list(num(4), list(num(6)))));                       // Expected: [1, 4, 6]
        System.out.println(drain(list(num(1), num(1)), num(2), list(num(1), num(1))));       // Expected: [1, 1, 2, 1, 1]
        System.out.println(drain());                                                         // Expected: []
        System.out.println(drain(list()));                                                   // Expected: []
        System.out.println(drain(list(), list(num(3))));                                     // Expected: [3]
        System.out.println(drain(list(list(list(num(5))))));                                 // Expected: [5]
        System.out.println(drain(num(1), num(2), num(3)));                                   // Expected: [1, 2, 3]
    }
}

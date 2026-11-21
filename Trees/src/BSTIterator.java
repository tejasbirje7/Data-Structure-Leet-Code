import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 173. Binary Search Tree Iterator
 * https://leetcode.com/problems/binary-search-tree-iterator/
 *
 * PROBLEM
 *   - Build an iterator over a BST that returns values in sorted (in-order) order.
 *   - BSTIterator(root): set up. next(): return the next smallest value. hasNext(): is there one left?
 *   - next() is only called when hasNext() is true.
 *   - Follow-up: next() and hasNext() in O(1) amortised time and O(h) memory.
 *
 * EXAMPLE
 *   Input
 *   ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 *   [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 *   Output
 *   [null, 3, 7, true, 9, true, 15, true, 20, false]
 *
 * HOW TO THINK
 *   - Clue: "next smallest" in a BST, O(h) memory -> in-order traversal, but paused between calls.
 *   - Technique: keep the iterative in-order stack as a field. Each next() does one step of the traversal.
 *   - Rule that stays true: the stack holds the "left spine" still waiting to be visited;
 *     its top is always the next smallest value.
 *   - Decision: next() pops the top; if that node has a right child, push the right child and all of
 *               its left descendants (the next values in order). hasNext() = stack is not empty.
 *   - Return: next() returns the popped node's value.
 *
 * STEPS
 *   1. Constructor: pushLeft(root) - push root, root.left, root.left.left, ... until null.
 *   2. next(): node = pop; pushLeft(node.right); return node.val.
 *   3. hasNext(): return !stack.isEmpty().
 *
 * DRY RUN   input = the official example, root = [7,3,15,null,null,9,20]   (stack top is on the right)
 *
 *          7
 *         / \
 *        3   15
 *           /  \
 *          9    20
 *
 *    step | call       | popped | pushed by pushLeft | stack after | returns
 *   ------+------------+--------+--------------------+-------------+--------
 *     0   | construct  |   -    | 7, 3               | [7, 3]      | null
 *     1   | next()     |   3    | - (no right child) | [7]         | 3
 *     2   | next()     |   7    | 15, 9              | [15, 9]     | 7
 *     3   | hasNext()  |   -    | -                  | [15, 9]     | true
 *     4   | next()     |   9    | -                  | [15]        | 9
 *     5   | hasNext()  |   -    | -                  | [15]        | true
 *     6   | next()     |   15   | 20                 | [20]        | 15
 *     7   | hasNext()  |   -    | -                  | [20]        | true
 *     8   | next()     |   20   | -                  | []          | 20
 *     9   | hasNext()  |   -    | -                  | []          | false
 *
 *   Prints [null, 3, 7, true, 9, true, 15, true, 20, false].
 *
 * EDGE CASES
 *   [1]                    -> next() = 1, then hasNext() = false
 *   [3,2,null,1]           -> 1, 2, 3   left chain: constructor pushes all 3 nodes
 *   [1,null,2,null,3]      -> 1, 2, 3   right chain: stack never holds more than one node
 *
 * COMMON MISTAKES
 *   - Flattening the whole tree into a list in the constructor: correct, but O(n) memory, not O(h).
 *   - Pushing only node.right after a pop: its left descendants are smaller and must come first.
 *   - Thinking next() is O(h): a single call can be, but each node is pushed and popped once,
 *     so n calls cost O(n) total (O(1) amortised).
 *
 * COMPLEXITY
 *   O(1) amortised time per call, O(h) extra space for the stack.
 *
 * RELATED
 *   LeetCode 230 (KthSmallestInBST, Trees/): the same stack in-order, run in one go until the k-th pop.
 *   LeetCode 341 (FlattenNestedListIterator, Stack/): another iterator that keeps a stack and does work lazily.
 *   LeetCode 98 (ValidateBinarySearchTree, Trees/): in-order of a BST is sorted, which is why this works.
 */
public class BSTIterator {

    public static void main(String[] args) {
        TreeNode root = build(new Integer[]{7, 3, 15, null, null, 9, 20});
        List<Object> out = new ArrayList<>();
        BSTIterator it = new BSTIterator(root);
        out.add(null);
        out.add(it.next());
        out.add(it.next());
        out.add(it.hasNext());
        out.add(it.next());
        out.add(it.hasNext());
        out.add(it.next());
        out.add(it.hasNext());
        out.add(it.next());
        out.add(it.hasNext());
        System.out.println(out);
    }

    private final Deque<TreeNode> stack = new ArrayDeque<>();

    public BSTIterator(TreeNode root) {
        pushLeft(root);
    }

    public int next() {
        TreeNode node = stack.pop();
        pushLeft(node.right);
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void pushLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    static TreeNode build(Integer[] values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int i = 1;
        while (i < values.length) {
            TreeNode node = queue.poll();
            if (values[i] != null) {
                node.left = new TreeNode(values[i]);
                queue.offer(node.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                node.right = new TreeNode(values[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }

}

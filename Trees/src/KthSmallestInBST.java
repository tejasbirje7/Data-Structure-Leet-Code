import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * LeetCode 230. Kth Smallest Element in a BST
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 *
 * PROBLEM
 *   - Input: the root of a binary search tree and an integer k (1 <= k <= number of nodes).
 *   - Return: the k-th smallest value in the tree (1-indexed).
 *
 * EXAMPLE
 *   Input: root = [5,3,6,2,4,null,null,1], k = 3
 *   Output: 3
 *
 * HOW TO THINK
 *   - Clue: "BST" + "k-th smallest" -> in-order traversal (left, node, right) visits a BST in sorted order.
 *   - Technique: iterative in-order with a stack, so we can stop the moment the k-th node is visited.
 *   - Rule that stays true: the stack holds the path of nodes whose left side is being explored;
 *     the top of the stack is always the smallest value not yet visited.
 *   - Decision: go left as far as possible, pushing every node. Then pop one node and count it:
 *               count reaches k -> return its value.
 *               otherwise       -> move to its right child and repeat.
 *   - Return: the value of the k-th popped node. Count down k so k == 0 means "this one".
 *
 * STEPS
 *   1. node = root; stack = empty.
 *   2. Loop: while node != null, push node and go to node.left.
 *   3. Pop a node; k--. If k == 0 return its value. Else node = node.right.
 *   4. (The loop always returns because k is at most the number of nodes.)
 *
 * DRY RUN   input = root = [5,3,6,2,4,null,null,1], k = 4   (stack top is on the right)
 *
 *            5
 *           / \
 *          3   6
 *         / \
 *        2   4
 *       /
 *      1
 *
 *    step | pushed going left | stack before pop | pop | k after | action
 *   ------+-------------------+------------------+-----+---------+------------------------------
 *     1   | 5, 3, 2, 1        | [5, 3, 2, 1]     |  1  |    3    | go right: null
 *     2   | -                 | [5, 3, 2]        |  2  |    2    | go right: null
 *     3   | -                 | [5, 3]           |  3  |    1    | go right: 4
 *     4   | 4                 | [5, 4]           |  4  |    0    | k == 0, return 4
 *
 *   Returns 4. Nodes 1, 2, 3, 4 were visited in sorted order; 5 and 6 were never popped.
 *
 * EDGE CASES
 *   [1], k = 1                       -> 1   single node
 *   [3,1,4,null,2], k = 1            -> 1   smallest is the leftmost node
 *   [5,3,6,2,4,null,null,1], k = 6   -> 6   k = n, largest value, whole traversal
 *   [1,null,2,null,3], k = 2         -> 2   right-skewed tree, stack never holds more than one node
 *
 * COMMON MISTAKES
 *   - Collecting all values into a list first: correct, but O(n) space and no early stop.
 *   - Using pre-order or level order: only in-order gives sorted order in a BST.
 *   - Off by one: decrement k on each pop and return when it hits 0 (k is 1-indexed).
 *   - Forgetting to go right after popping: the right subtree's values come next in order.
 *
 * COMPLEXITY
 *   O(h + k) time: walk down to the smallest node, then k pops. O(h) extra space for the stack.
 *
 * RELATED
 *   LeetCode 173 (BSTIterator, Trees/): the same stack in-order, paused between next() calls.
 *   LeetCode 98 (ValidateBinarySearchTree, Trees/): in-order of a valid BST is strictly increasing.
 *   LeetCode 215 (KthLargestElement, Heap/): k-th value in an unsorted array, so a heap is needed instead.
 */
public class KthSmallestInBST {

    public static void main(String[] args) {
        TreeNode root = build(new Integer[]{5, 3, 6, 2, 4, null, null, 1});
        System.out.println(kthSmallest(root, 4));
    }

    public static int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (true) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            k--;
            if (k == 0) {
                return node.val;
            }
            node = node.right;
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

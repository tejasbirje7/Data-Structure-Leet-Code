import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 226. Invert Binary Tree
 * https://leetcode.com/problems/invert-binary-tree/
 *
 * PROBLEM
 *   - Input: the root of a binary tree (0 to 100 nodes).
 *   - Return: the root after mirroring the tree: every node's left and right children are swapped.
 *
 * EXAMPLE
 *   Input: root = [4,2,7,1,3,6,9]
 *   Output: [4,7,2,9,6,3,1]
 *
 * HOW TO THINK
 *   - Clue: the same small change (swap two children) at every node -> recursion over the tree.
 *   - Technique: invert both subtrees, then swap them. The mirror of a tree is
 *     "root, with the mirror of the right subtree on the left and the mirror of the left subtree on the right".
 *   - Rule that stays true: when invert(node) returns, everything under node is fully mirrored.
 *   - Decision: node is null -> return null. Otherwise invert left, invert right, then swap.
 *   - Return: the same root node; the tree is changed in place.
 *
 * STEPS
 *   1. If node is null, return null.
 *   2. left = invert(node.left); right = invert(node.right).
 *   3. node.left = right; node.right = left.
 *   4. Return node.
 *
 * DRY RUN   input = [4,2,7,1,3,6,9]   (rows in the order calls finish)
 *
 *        before:   4              after:    4
 *                /   \                    /   \
 *               2     7                  7     2
 *              / \   / \                / \   / \
 *             1   3 6   9              9   6 3   1
 *
 *    node | inverted left | inverted right | after swap (left, right)
 *   ------+---------------+----------------+-------------------------
 *     1   |     null      |      null      | (null, null)
 *     3   |     null      |      null      | (null, null)
 *     2   |       1       |       3        | (3, 1)
 *     6   |     null      |      null      | (null, null)
 *     9   |     null      |      null      | (null, null)
 *     7   |       6       |       9        | (9, 6)
 *     4   |       2       |       7        | (7, 2)
 *
 *   Returns node 4. Level order: [4, 7, 2, 9, 6, 3, 1].
 *
 * EDGE CASES
 *   []          -> []          null root
 *   [1]         -> [1]         nothing to swap
 *   [2,1,3]     -> [2,3,1]     official example 2
 *   [1,2]       -> [1,null,2]  the only child moves from left to right
 *
 * COMMON MISTAKES
 *   - node.left = invert(node.right); node.right = invert(node.left): the second line reads the NEW left
 *     (already overwritten). Save both results (or the old left) before assigning.
 *   - Swapping values instead of subtrees: the shape must be mirrored too, not just the numbers.
 *   - Forgetting to return the root.
 *
 * COMPLEXITY
 *   O(n) time: each node is visited once. O(h) extra space for the recursion stack.
 *
 * RELATED
 *   LeetCode 101 (SymmetricTree, Trees/): checks whether a tree already equals its own mirror.
 *   LeetCode 100 (SameTree, Trees/): the same "do it at the node, then both children" recursion.
 *   LeetCode 104 (MaximumDepthOfBinaryTree, Trees/): the simplest post-order recursion over a tree.
 */
public class InvertBinaryTree {

    public static void main(String[] args) {
        TreeNode root = build(new Integer[]{4, 2, 7, 1, 3, 6, 9});
        System.out.println(toLevelOrder(invertTree(root)));
    }

    public static TreeNode invertTree(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode left = invertTree(node.left);
        TreeNode right = invertTree(node.right);
        node.left = right;
        node.right = left;
        return node;
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

    static List<Integer> toLevelOrder(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                out.add(null);
                continue;
            }
            out.add(node.val);
            queue.offer(node.left);
            queue.offer(node.right);
        }
        while (!out.isEmpty() && out.get(out.size() - 1) == null) {
            out.remove(out.size() - 1);
        }
        return out;
    }

}

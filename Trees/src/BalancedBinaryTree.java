import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 110. Balanced Binary Tree
 * https://leetcode.com/problems/balanced-binary-tree/
 *
 * PROBLEM
 *   - Input: the root of a binary tree (0 to 5000 nodes).
 *   - Return: true if, at EVERY node, the heights of the left and right subtrees differ by at most 1.
 *
 * EXAMPLE
 *   Input: root = [3,9,20,null,null,15,7]
 *   Output: true
 *
 * HOW TO THINK
 *   - Clue: a check at every node that needs the heights of both children -> post-order (children first).
 *   - Technique: one recursion that returns the height, or -1 as a signal that some subtree is unbalanced.
 *     This gets heights and the check in a single pass instead of re-computing heights at every node.
 *   - Rule that stays true: height(node) is either the real height of a balanced subtree, or -1.
 *     Once -1 appears, every ancestor returns -1 too.
 *   - Decision: left or right is -1          -> return -1 (pass the failure up).
 *               |left - right| > 1            -> return -1 (this node is unbalanced).
 *               otherwise                     -> return 1 + max(left, right).
 *   - Return: height(root) != -1. An empty tree is balanced (height 0).
 *
 * STEPS
 *   1. height(null) = 0.
 *   2. left = height(node.left); if -1 return -1. right = height(node.right); if -1 return -1.
 *   3. If |left - right| > 1 return -1; else return 1 + max(left, right).
 *   4. Answer: height(root) != -1.
 *
 * DRY RUN   input = [1,2,2,3,3,null,null,4,4]   (rows in the order calls finish)
 *
 *              1
 *             / \
 *            2   2
 *           / \
 *          3   3
 *         / \
 *        4   4
 *
 *    node             | left h | right h | diff | returns
 *   ------------------+--------+---------+------+------------------
 *    4 (left)         |   0    |    0    |  0   |  1
 *    4 (right)        |   0    |    0    |  0   |  1
 *    3 (left)         |   1    |    1    |  0   |  2
 *    3 (right)        |   0    |    0    |  0   |  1
 *    2 (left)         |   2    |    1    |  1   |  3
 *    2 (right)        |   0    |    0    |  0   |  1
 *    1 (root)         |   3    |    1    |  2   | -1 (diff > 1)
 *
 *   Returns false: height(root) is -1.
 *
 * EDGE CASES
 *   []                                  -> true    empty tree, height 0
 *   [1]                                 -> true
 *   [3,9,20,null,null,15,7]             -> true    official example
 *   [1,null,2,null,3]                   -> false   a chain of 3: root sides are 0 and 2
 *   [1,2,2,3,null,null,3,4,null,null,4] -> false   root sides are equal, but each 2 is unbalanced;
 *                                                  the -1 is passed up from below
 *
 * COMMON MISTAKES
 *   - Only checking the root: both root subtrees can have equal height while a lower node is unbalanced.
 *   - Calling a separate height() at every node: correct but O(n^2) on a chain.
 *   - Forgetting to pass -1 up: a -1 child looks like a short subtree and the diff check may pass.
 *
 * COMPLEXITY
 *   O(n) time: each node is visited once. O(h) extra space for the recursion stack.
 *
 * RELATED
 *   LeetCode 104 (MaximumDepthOfBinaryTree, Trees/): the same height recursion without the balance check.
 *   LeetCode 543 (DiameterOfBinaryTree, FAQ/): the same height recursion, tracking left + right along the way.
 *   LeetCode 124 (TreeMaxPathSum, Trees/): returns one value up while checking another at every node.
 */
public class BalancedBinaryTree {

    public static void main(String[] args) {
        TreeNode root = build(new Integer[]{1, 2, 2, 3, 3, null, null, 4, 4});
        System.out.println(isBalanced(root));
    }

    public static boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    private static int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = height(node.left);
        if (left == -1) {
            return -1;
        }
        int right = height(node.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return 1 + Math.max(left, right);
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

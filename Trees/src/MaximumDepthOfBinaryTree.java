import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 104. Maximum Depth of Binary Tree
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 *
 * PROBLEM
 *   - Input: the root of a binary tree (may be empty).
 *   - Return: its maximum depth = the number of NODES on the longest root-to-leaf path.
 *
 * EXAMPLE
 *   Input: root = [3,9,20,null,null,15,7]
 *   Output: 3
 *
 * HOW TO THINK
 *   - Clue: a property of the whole tree that is built from the same property of each subtree -> recursion.
 *   - Technique: post-order DFS. A node's depth = 1 (itself) + the deeper of its two subtrees.
 *   - Rule that stays true: depth(node) returns the max depth of the subtree rooted at node; depth(null) = 0.
 *   - Decision: node == null -> return 0.
 *               otherwise    -> return 1 + max(depth(left), depth(right)).
 *   - Return: depth(root).
 *
 * STEPS
 *   1. If root is null, return 0.
 *   2. left = maxDepth(root.left).
 *   3. right = maxDepth(root.right).
 *   4. Return 1 + max(left, right).
 *
 * DRY RUN   input = [3,9,20,null,null,15,7]
 *
 *          3
 *         / \
 *        9   20
 *           /  \
 *          15   7
 *
 *   (rows in the order the calls RETURN, children before parents)
 *
 *    node | left depth | right depth | returns 1 + max
 *   ------+------------+-------------+-----------------
 *     9   |     0      |      0      |        1
 *    15   |     0      |      0      |        1
 *     7   |     0      |      0      |        1
 *    20   |     1      |      1      |        2
 *     3   |     1      |      2      |        3
 *
 *   Returns 3: path 3 -> 20 -> 15 (or 3 -> 20 -> 7).
 *
 * EDGE CASES
 *   []                    -> 0   empty tree
 *   [1]                   -> 1   only the root
 *   [1,null,2,null,3]     -> 3   skewed tree: depth = number of nodes
 *   [1,2]                 -> 2   one child is enough to go deeper
 *
 * COMMON MISTAKES
 *   - Returning 1 for null: every path then counts one extra level.
 *   - Using min instead of max, or adding both sides: that is a different question (min depth, diameter).
 *   - Counting edges instead of nodes: this problem counts nodes, so a single node has depth 1.
 *
 * COMPLEXITY
 *   O(n) time, O(h) extra space for the recursion stack (h = height; up to n for a skewed tree).
 *
 * RELATED
 *   LeetCode 543 (DiameterOfBinaryTree, FAQ/): same post-order depth, but tracks left + right at each node.
 *   LeetCode 110 (BalancedBinaryTree, Trees/): same depth recursion, plus a check that the two sides differ by <= 1.
 */
public class MaximumDepthOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = build(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println(maxDepth(root));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
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

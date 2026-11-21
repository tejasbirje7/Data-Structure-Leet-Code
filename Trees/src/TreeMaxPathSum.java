import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 124. Binary Tree Maximum Path Sum
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * PROBLEM
 *   - Input: the root of a binary tree, at least one node, values may be negative.
 *   - A path is any chain of connected nodes (each node used at most once); it need not pass through the root.
 *   - Return: the largest sum of any non-empty path.
 *
 * EXAMPLE
 *   Input: root = [-10,9,20,null,null,15,7]
 *   Output: 42
 *   Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 * HOW TO THINK
 *   - Clue: "any path", not root-to-leaf -> every path has one highest node where it bends;
 *     try every node as that bend point.
 *   - Technique: post-order DFS returning the best DOWNWARD chain ("gain") from each node,
 *     while updating a global best with the path that bends at that node.
 *   - Rule that stays true: gain(node) = node.val + the better of its two child gains (only one side,
 *     because a parent can extend a chain down one side only). A negative child gain is replaced by 0 (just don't take it).
 *   - Decision at each node: left = max(0, gain(left)), right = max(0, gain(right)).
 *       best = max(best, node.val + left + right)   (path bending here, may use both sides)
 *       return node.val + max(left, right)          (chain handed up to the parent, one side only)
 *   - Return: best, started at Integer.MIN_VALUE so all-negative trees work.
 *
 * STEPS
 *   1. best = Integer.MIN_VALUE; call gain(root).
 *   2. gain(null) = 0.
 *   3. left = max(0, gain(node.left)); right = max(0, gain(node.right)).
 *   4. best = max(best, node.val + left + right); return node.val + max(left, right).
 *   5. Return best.
 *
 * DRY RUN   input = [-10,9,20,null,null,15,-7]
 *
 *         -10
 *         /  \
 *        9    20
 *            /  \
 *          15    -7
 *
 *   (rows in the order the calls RETURN; left/right are after the max(0, ...) clamp)
 *
 *    node | left | right | path bending here | best | returns (gain)
 *   ------+------+-------+-------------------+------+----------------
 *      9  |  0   |   0   |         9         |   9  |       9
 *     15  |  0   |   0   |        15         |  15  |      15
 *     -7  |  0   |   0   |        -7         |  15  |      -7
 *     20  |  15  |   0   |  20 + 15 + 0 = 35 |  35  |  20 + 15 = 35   (-7 clamped to 0)
 *    -10  |  9   |  35   | -10 + 9 + 35 = 34 |  35  | -10 + 35 = 25
 *
 *   Returns 35: path 15 -> 20. Going up through -10 would only lower it.
 *
 * EDGE CASES
 *   [-3]                       -> -3   one negative node; best must start at MIN_VALUE, not 0
 *   [2,-1]                     -> 2    skip the negative child
 *   [1,2,3]                    -> 6    2 -> 1 -> 3 bends at the root
 *   [-10,9,20,null,null,15,7]  -> 42   the LeetCode example: 15 -> 20 -> 7
 *
 * COMMON MISTAKES
 *   - Returning node.val + left + right to the parent: a path cannot fork, so only one side goes up.
 *   - Not clamping negative gains to 0: a bad subtree drags the sum down instead of being skipped.
 *   - Starting best at 0: an all-negative tree would wrongly return 0.
 *
 * COMPLEXITY
 *   O(n) time, O(h) extra space for the recursion stack.
 *
 * RELATED
 *   LeetCode 543 (DiameterOfBinaryTree, FAQ/): the same "both sides for the answer, one side returned up" pattern, counting edges.
 *   LeetCode 104 (MaximumDepthOfBinaryTree, Trees/): the plain post-order recursion this builds on.
 */
public class TreeMaxPathSum {

    private static int best;

    public static void main(String[] args) {
        TreeNode root = build(new Integer[]{-10, 9, 20, null, null, 15, -7});
        System.out.println(maxPathSum(root));
    }

    public static int maxPathSum(TreeNode root) {
        best = Integer.MIN_VALUE;
        gain(root);
        return best;
    }

    private static int gain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(0, gain(node.left));
        int right = Math.max(0, gain(node.right));
        best = Math.max(best, node.val + left + right);
        return node.val + Math.max(left, right);
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

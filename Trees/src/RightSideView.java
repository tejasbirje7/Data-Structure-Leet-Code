import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 199. Binary Tree Right Side View
 * https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * PROBLEM
 *   - Input: the root of a binary tree (may be empty).
 *   - Return: the values you see standing on the right side, from top to bottom
 *     (the rightmost node of each level).
 *
 * EXAMPLE
 *   Input: root = [1,2,3,null,5,null,4]
 *   Output: [1,3,4]
 *
 * HOW TO THINK
 *   - Clue: "seen from the right", one value per depth -> work level by level (BFS).
 *   - Technique: the level-order loop; the LAST node polled in each level is the one visible from the right.
 *     It is not always a right child: a deep left node shows when nothing is to its right.
 *   - Rule that stays true: when a level starts, the queue holds exactly that level, left to right.
 *   - Decision: for i in 0..size-1, poll a node and offer its children;
 *               i == size - 1 -> it is the rightmost node, add its value.
 *   - Return: the collected values; [] for an empty tree.
 *
 * STEPS
 *   1. If root is null, return []. Otherwise queue = [root].
 *   2. While the queue is not empty: size = queue.size().
 *   3. Repeat size times: poll, offer non-null children; on the last one (i == size - 1) add its value.
 *   4. Return the list.
 *
 * DRY RUN   input = [1,2,3,4,null,null,null,5]
 *
 *          1
 *         / \
 *        2   3
 *       /
 *      4
 *     /
 *    5             levels 2 and 3 have nothing on the right, so 4 and 5 are visible.
 *
 *    level | queue at start | size | last polled | view so far
 *   -------+----------------+------+-------------+-------------
 *      0   | [1]            |  1   |      1      | [1]
 *      1   | [2, 3]         |  2   |      3      | [1, 3]
 *      2   | [4]            |  1   |      4      | [1, 3, 4]
 *      3   | [5]            |  1   |      5      | [1, 3, 4, 5]
 *
 *   Returns [1, 3, 4, 5].
 *
 * EDGE CASES
 *   []                       -> []         empty tree
 *   [1,null,3]               -> [1,3]      right chain
 *   [1,2]                    -> [1,2]      a left child is visible when there is no right child
 *   [1,2,3,null,5,null,4]    -> [1,3,4]    the LeetCode example
 *
 * COMMON MISTAKES
 *   - Following only right pointers from the root: misses deeper left nodes like 4 and 5 above.
 *   - Taking the FIRST node of each level: that is the left side view.
 *   - Checking queue.size() inside the loop instead of the saved size.
 *
 * COMPLEXITY
 *   O(n) time, O(w) extra space for the queue (w = widest level).
 *
 * RELATED
 *   LeetCode 102 (LevelOrderTraversal, Trees/): the same loop, keeping every value instead of the last.
 *   LeetCode 103 (ZigzagLevelOrderTraversal, Trees/): the same loop, alternating direction per level.
 */
public class RightSideView {

    public static void main(String[] args) {
        TreeNode root = build(new Integer[]{1, 2, 3, 4, null, null, null, 5});
        System.out.println(rightSideView(root));
    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> view = new ArrayList<>();
        if (root == null) {
            return view;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {
                    view.add(node.val);
                }
            }
        }
        return view;
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

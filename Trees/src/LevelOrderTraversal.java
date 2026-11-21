import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 102. Binary Tree Level Order Traversal
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 *
 * PROBLEM
 *   - Input: the root of a binary tree (may be empty).
 *   - Return: the node values level by level, left to right, one list per level.
 *
 * EXAMPLE
 *   Input: root = [3,9,20,null,null,15,7]
 *   Output: [[3],[9,20],[15,7]]
 *
 * HOW TO THINK
 *   - Clue: "level by level" -> breadth-first search with a queue.
 *   - Technique: BFS, but process the queue one whole level at a time.
 *     At the start of a level, the queue holds exactly that level's nodes, so its size is the level's width.
 *   - Rule that stays true: when a level starts, the queue contains all nodes of that level and nothing else.
 *   - Decision: for each of the `size` nodes: record its value, offer its non-null children (they form the next level).
 *   - Return: the list of level lists; [] for an empty tree.
 *
 * STEPS
 *   1. If root is null, return []. Otherwise queue = [root].
 *   2. While the queue is not empty: size = queue.size(), level = new list.
 *   3. Repeat size times: poll a node, add its value to level, offer its left and right children if not null.
 *   4. Add level to the result; return the result after the loop.
 *
 * DRY RUN   input = [3,9,20,null,null,15,7]
 *
 *          3
 *         / \
 *        9   20
 *           /  \
 *          15   7
 *
 *    level | queue at start | size | polled -> children offered | level list
 *   -------+----------------+------+----------------------------+-----------
 *      0   | [3]            |  1   | 3 -> 9, 20                 | [3]
 *      1   | [9, 20]        |  2   | 9 -> none; 20 -> 15, 7     | [9, 20]
 *      2   | [15, 7]        |  2   | 15 -> none; 7 -> none      | [15, 7]
 *
 *   Queue is empty. Returns [[3], [9, 20], [15, 7]].
 *
 * EDGE CASES
 *   []                   -> []                  empty tree
 *   [1]                  -> [[1]]               one level
 *   [1,2,null,3]         -> [[1],[2],[3]]       skewed tree: one node per level
 *   [1,2,3,4,null,null,5]-> [[1],[2,3],[4,5]]   gaps don't shift values to another level
 *
 * COMMON MISTAKES
 *   - Writing for (i = 0; i < queue.size(); i++): the size changes as children are added. Save it first.
 *   - Offering null children: later polls then crash on null.
 *   - Using a stack: that gives depth-first order, not levels.
 *
 * COMPLEXITY
 *   O(n) time, O(w) extra space for the queue (w = the widest level, up to n/2).
 *
 * RELATED
 *   LeetCode 199 (RightSideView, Trees/): same level loop, keep only the last node of each level.
 *   LeetCode 103 (ZigzagLevelOrderTraversal, Trees/): same level loop, reverse every other level.
 */
public class LevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = build(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println(levelOrder(root));
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;
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

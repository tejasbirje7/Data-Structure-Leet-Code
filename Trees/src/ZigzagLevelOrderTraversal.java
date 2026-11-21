import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 103. Binary Tree Zigzag Level Order Traversal
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 *
 * PROBLEM
 *   - Input: the root of a binary tree (0 to 2000 nodes).
 *   - Return: the values level by level, left to right on the first level, right to left on the next, and so on.
 *
 * EXAMPLE
 *   Input: root = [3,9,20,null,null,15,7]
 *   Output: [[3],[20,9],[15,7]]
 *
 * HOW TO THINK
 *   - Clue: "level by level" -> BFS with a queue, one batch per level.
 *   - Technique: normal level-order BFS; only the way each level's list is FILLED changes.
 *     Left-to-right level: add each value at the end. Right-to-left level: add each value at the front.
 *   - Rule that stays true: the queue always holds the next level in left-to-right order.
 *     The direction never changes how children are queued, only how values are written.
 *   - Decision: leftToRight -> addLast(value); otherwise -> addFirst(value). Flip the flag after each level.
 *   - Return: the list of level lists. An empty tree gives [].
 *
 * STEPS
 *   1. If root is null return []. Queue = [root]; leftToRight = true.
 *   2. While the queue is not empty: size = queue size; level = new LinkedList.
 *   3. Poll size nodes: addLast or addFirst the value, offer left then right child.
 *   4. Add level to the result, flip leftToRight. Return the result.
 *
 * DRY RUN   input = [3,9,20,null,null,15,7]
 *
 *          3
 *         / \
 *        9   20
 *           /  \
 *          15   7
 *
 *    level | queue at start | direction     | polled -> how it is added               | level list
 *   -------+----------------+---------------+-----------------------------------------+-----------
 *      0   | [3]            | left -> right | 3 addLast                               | [3]
 *      1   | [9, 20]        | right -> left | 9 addFirst -> [9]; 20 addFirst          | [20, 9]
 *      2   | [15, 7]        | left -> right | 15 addLast; 7 addLast                   | [15, 7]
 *
 *   Queue is empty. Returns [[3], [20, 9], [15, 7]].
 *
 * EDGE CASES
 *   []                       -> []
 *   [1]                      -> [[1]]
 *   [1,2,3,4,5,6,7]          -> [[1],[3,2],[4,5,6,7]]   third level is left to right again
 *   [1,2,null,3,null,4]      -> [[1],[2],[3],[4]]       one node per level, direction doesn't show
 *
 * COMMON MISTAKES
 *   - Offering children right-first on odd levels: it breaks the order of the level after. Keep the queue
 *     left to right and only change how values are written.
 *   - Using ArrayList.add(0, x): O(size) per insert. Use a LinkedList (or reverse the list once).
 *   - Reading queue.size() inside the for loop condition: it changes as children are offered. Save it first.
 *
 * COMPLEXITY
 *   O(n) time, O(w) extra space for the queue, w = widest level.
 *
 * RELATED
 *   LeetCode 102 (LevelOrderTraversal, Trees/): the same loop with every level left to right.
 *   LeetCode 199 (RightSideView, Trees/): the same loop, keeps only the last node of each level.
 */
public class ZigzagLevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = build(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println(zigzagLevelOrder(root));
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (leftToRight) {
                    level.addLast(node.val);
                } else {
                    level.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
            leftToRight = !leftToRight;
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

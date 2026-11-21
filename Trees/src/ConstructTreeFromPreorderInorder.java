import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * LeetCode 105. Construct Binary Tree from Preorder and Inorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * PROBLEM
 *   - Input: preorder and inorder traversals of the same binary tree.
 *   - Return: the root of that tree.
 *   - All values are unique (this is what makes the tree unique).
 *
 * EXAMPLE
 *   Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 *   Output: [3,9,20,null,null,15,7]
 *
 * HOW TO THINK
 *   - Clue: two traversals of one tree -> each gives what the other lacks.
 *     Preorder (root, left, right): its next value is always the root of the current subtree.
 *     Inorder (left, root, right): that root splits the range into left subtree | root | right subtree.
 *   - Technique: recursion over an inorder range [lo, hi], with a shared pointer into preorder.
 *     A HashMap value -> inorder index finds the split in O(1).
 *   - Rule that stays true: when build(lo, hi) runs, preorder[preIndex] is the root of the subtree
 *     made of inorder[lo..hi].
 *   - Decision: lo > hi -> empty range, return null.
 *               otherwise -> root = preorder[preIndex++], mid = index of root in inorder,
 *                            left = build(lo, mid - 1), THEN right = build(mid + 1, hi).
 *   - Return: build(0, n - 1).
 *
 * STEPS
 *   1. Map each inorder value to its index; preIndex = 0.
 *   2. build(lo, hi): if lo > hi return null.
 *   3. root = new node(preorder[preIndex++]); mid = map.get(root.val).
 *   4. root.left = build(lo, mid - 1); root.right = build(mid + 1, hi); return root.
 *
 * DRY RUN   input = preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 *
 *    call         | preIndex | root | mid (in inorder) | left range | right range
 *   --------------+----------+------+------------------+------------+------------
 *    build(0, 4)  |    0     |  3   |        1         |  (0, 0)    |  (2, 4)
 *    build(0, 0)  |    1     |  9   |        0         |  (0, -1)   |  (1, 0)
 *    build(0, -1) |    2     |  -   |        -         |  null      |
 *    build(1, 0)  |    2     |  -   |        -         |  null      |
 *    build(2, 4)  |    2     |  20  |        3         |  (2, 2)    |  (4, 4)
 *    build(2, 2)  |    3     |  15  |        2         |  null      |  null
 *    build(4, 4)  |    4     |  7   |        4         |  null      |  null
 *
 *          3
 *         / \
 *        9   20
 *           /  \
 *          15   7
 *
 *   Returns that tree; its level order is [3, 9, 20, null, null, 15, 7].
 *
 * EDGE CASES
 *   [-1], [-1]                -> [-1]                  single node
 *   [3,2,1], [1,2,3]          -> [3,2,null,1]          left-skewed: every split is at the right end
 *   [1,2,3], [1,2,3]          -> [1,null,2,null,3]     right-skewed: every split is at the left end
 *   duplicate values          -> not allowed; the map could point to the wrong split
 *
 * COMMON MISTAKES
 *   - Building the right subtree before the left: preorder lists the whole left subtree first,
 *     so preIndex would hand out the wrong roots.
 *   - Searching inorder with a loop in every call: O(n^2) on skewed trees. Use the map.
 *   - Copying sub-arrays (Arrays.copyOfRange) for each call: O(n^2) time and memory. Pass index ranges.
 *
 * COMPLEXITY
 *   O(n) time, O(n) extra space (map + recursion stack).
 *
 * RELATED
 *   LeetCode 297 (SerializeDeserializeTree, Trees/): with null markers stored, pre-order alone is enough.
 *   LeetCode 102 (LevelOrderTraversal, Trees/): the BFS order used to print the result.
 */
public class ConstructTreeFromPreorderInorder {

    private static int preIndex;
    private static Map<Integer, Integer> inorderIndex;

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        System.out.println(toLevelOrder(buildTree(preorder, inorder)));
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        preIndex = 0;
        inorderIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndex.put(inorder[i], i);
        }
        return build(preorder, 0, inorder.length - 1);
    }

    private static TreeNode build(int[] preorder, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preIndex++]);
        int mid = inorderIndex.get(root.val);
        root.left = build(preorder, lo, mid - 1);
        root.right = build(preorder, mid + 1, hi);
        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
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

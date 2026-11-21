import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 236. Lowest Common Ancestor of a Binary Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * PROBLEM
 *   - Input: the root of a binary tree (NOT a BST) and two different nodes p and q that are both in the tree.
 *   - Return: their lowest common ancestor: the deepest node that has both p and q below it.
 *   - A node counts as a descendant of itself, so p can be the answer when q is under p.
 *
 * EXAMPLE
 *   Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 *   Output: 3
 *   Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * HOW TO THINK
 *   - Clue: no ordering in the tree, so we can't choose a side; we must search both sides -> post-order recursion.
 *   - Technique: find(node) returns p or q if it finds one in this subtree, the LCA if it finds both, else null.
 *   - Rule that stays true: a non-null result from a subtree means "something useful is down there".
 *     The first node that gets non-null from BOTH sides is the split point, so it is the LCA.
 *   - Decision: node is null, p or q        -> return node (no need to look deeper: if the other one is below,
 *                                              this node is the LCA anyway).
 *               both sides non-null         -> return node (p on one side, q on the other).
 *               one side non-null           -> pass that result up.
 *               both null                   -> return null.
 *   - Return: the value find(root) passes all the way up.
 *
 * STEPS
 *   1. If node is null, p or q, return node.
 *   2. left = find(node.left); right = find(node.right).
 *   3. If both are non-null return node.
 *   4. Otherwise return whichever is non-null (or null).
 *
 * DRY RUN   input = root = [3,5,1,6,2,0,8,null,null,7,4], p = 6, q = 4   (rows in the order calls finish)
 *
 *              3
 *            /   \
 *           5     1
 *          / \   / \
 *         6   2 0   8
 *            / \
 *           7   4
 *
 *    node | left result | right result | rule              | returns
 *   ------+-------------+--------------+-------------------+--------
 *      6  |      -      |      -       | node is p         |   6
 *      7  |    null     |    null      | nothing found     |  null
 *      4  |      -      |      -       | node is q         |   4
 *      2  |    null     |      4       | one side          |   4
 *      5  |      6      |      4       | both sides        |   5
 *      0  |    null     |    null      | nothing found     |  null
 *      8  |    null     |    null      | nothing found     |  null
 *      1  |    null     |    null      | nothing found     |  null
 *      3  |      5      |    null      | one side          |   5
 *
 *   Returns node 5: 6 is on its left, 4 is on its right.
 *
 * EDGE CASES
 *   p = 5, q = 1 in the tree above  -> 3   p and q on opposite sides of the root
 *   p = 5, q = 4 in the tree above  -> 5   q is under p; the search stops at p
 *   [1,2], p = 1, q = 2             -> 1   root itself is the answer
 *   p = 7, q = 8 in the tree above  -> 3   deep nodes in different subtrees of the root
 *
 * COMMON MISTAKES
 *   - Comparing values like in a BST: this tree has no ordering, so p.val < node.val means nothing.
 *   - Comparing node.val == p.val when values could repeat: compare the node references.
 *   - Searching for both p and q separately and storing two root-to-node paths: works, but more code and O(n) memory.
 *
 * COMPLEXITY
 *   O(n) time: each node is visited at most once. O(h) extra space for the recursion stack.
 *
 * RELATED
 *   LeetCode 235 (LowestCommonAncestorBST, Trees/): the BST order tells which side to go, so no need to search both.
 *   LeetCode 124 (TreeMaxPathSum, Trees/): same post-order shape, combining results from both children.
 *   LeetCode 104 (MaximumDepthOfBinaryTree, Trees/): the simplest "ask both children, combine" recursion.
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode root = build(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        TreeNode p = root.left.left;
        TreeNode q = root.left.right.right;
        System.out.println(lowestCommonAncestor(root, p, q).val);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
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

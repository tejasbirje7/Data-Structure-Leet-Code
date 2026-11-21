import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 235. Lowest Common Ancestor of a Binary Search Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * PROBLEM
 *   - Input: the root of a BST and two different nodes p and q that are both in the tree.
 *   - Return: their lowest common ancestor: the deepest node that has both p and q in its subtree.
 *   - A node counts as an ancestor of itself.
 *
 * EXAMPLE
 *   Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 *   Output: 2
 *   Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 *
 * HOW TO THINK
 *   - Clue: "binary SEARCH tree" -> comparing values tells you which side p and q are on, no need to search both sides.
 *   - Technique: walk down from the root.
 *     Both smaller -> both are in the left subtree, so the LCA is there too. Both larger -> go right.
 *     Otherwise they split here (or one of them IS this node): this is the LCA.
 *   - Rule that stays true: the current node's subtree always contains both p and q.
 *   - Decision: p.val < node.val and q.val < node.val -> node = node.left.
 *               p.val > node.val and q.val > node.val -> node = node.right.
 *               otherwise                             -> return node.
 *   - Return: the first node where they stop being on the same side.
 *
 * STEPS
 *   1. node = root.
 *   2. Loop while node is not null.
 *   3. Both values smaller -> go left; both larger -> go right; otherwise return node.
 *   4. (Never reached when p and q are in the tree.) Return null.
 *
 * DRY RUN   input = root = [6,2,8,0,4,7,9,null,null,3,5], p = 3, q = 5
 *
 *            6
 *          /   \
 *         2     8
 *        / \   / \
 *       0   4 7   9
 *          / \
 *         3   5
 *
 *    node | p = 3 vs node | q = 5 vs node | action
 *   ------+---------------+---------------+----------------------
 *     6   |   3 < 6       |   5 < 6       | both smaller -> left
 *     2   |   3 > 2       |   5 > 2       | both larger -> right
 *     4   |   3 < 4       |   5 > 4       | split -> return 4
 *
 *   Returns node 4.
 *
 * EDGE CASES
 *   p = 2, q = 8   -> 6   split right at the root
 *   p = 2, q = 4   -> 2   p is an ancestor of q: equality falls into "otherwise"
 *   p = 0, q = 5   -> 2   one goes deeper than the other
 *   p = 5, q = 3   -> 4   order of p and q does not matter
 *
 * COMMON MISTAKES
 *   - Using <= / >= in the "go left / go right" checks: when node equals p, the walk steps past the answer.
 *   - Assuming p < q: write both checks for both values instead.
 *   - Searching both subtrees like a plain binary tree: correct but O(n); the BST order gives O(h).
 *
 * COMPLEXITY
 *   O(h) time (h = height; log n if balanced, n if skewed), O(1) extra space.
 *
 * RELATED
 *   LeetCode 236 (LowestCommonAncestor, Trees/): any binary tree, no ordering, so search both sides recursively.
 *   LeetCode 98 (ValidateBinarySearchTree, Trees/): the BST ordering this walk relies on.
 */
public class LowestCommonAncestorBST {

    public static void main(String[] args) {
        TreeNode root = build(new Integer[]{6, 2, 8, 0, 4, 7, 9, null, null, 3, 5});
        TreeNode p = root.left.right.left;
        TreeNode q = root.left.right.right;
        System.out.println(lowestCommonAncestor(root, p, q).val);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (node != null) {
            if (p.val < node.val && q.val < node.val) {
                node = node.left;
            } else if (p.val > node.val && q.val > node.val) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
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

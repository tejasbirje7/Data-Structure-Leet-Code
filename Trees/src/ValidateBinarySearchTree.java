import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 98. Validate Binary Search Tree
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * PROBLEM
 *   - Input: the root of a binary tree (1 to 10^4 nodes, values span the full int range).
 *   - Return: true if it is a valid BST: every node in the left subtree is STRICTLY smaller,
 *     every node in the right subtree is STRICTLY larger, and both subtrees are BSTs too.
 *
 * EXAMPLE
 *   Input: root = [5,1,4,null,null,3,6]
 *   Output: false
 *   Explanation: The root node's value is 5 but its right child's value is 4.
 *
 * HOW TO THINK
 *   - Clue: "every node in the subtree", not just the children -> each node must fit a RANGE set by all its ancestors.
 *   - Technique: DFS that passes down (low, high) bounds.
 *     Going left, the current value becomes the new upper bound; going right, it becomes the new lower bound.
 *   - Rule that stays true: check(node, low, high) is true only if every value in node's subtree is in (low, high).
 *   - Decision: node == null                       -> true (an empty subtree is valid).
 *               node.val <= low or node.val >= high -> false.
 *               otherwise -> check(left, low, val) && check(right, val, high).
 *   - Return: check(root, -infinity, +infinity). Use long bounds so Integer.MIN_VALUE / MAX_VALUE nodes still fit.
 *
 * STEPS
 *   1. Call check(root, Long.MIN_VALUE, Long.MAX_VALUE).
 *   2. If node is null, return true.
 *   3. If node.val <= low or node.val >= high, return false.
 *   4. Return check(node.left, low, node.val) && check(node.right, node.val, high).
 *
 * DRY RUN   input = [5,4,6,null,null,3,7]
 *
 *        5
 *       / \
 *      4   6
 *         / \
 *        3   7       3 is smaller than its parent 6 (fine locally) but sits in 5's right subtree.
 *
 *    call            | range        | in range?    | action
 *   -----------------+--------------+--------------+----------------------------------------
 *    check(5)        | (-inf, +inf) | yes          | go left with (-inf, 5)
 *    check(4)        | (-inf, 5)    | yes          | go left with (-inf, 4)
 *    check(null)     | (-inf, 4)    | -            | true
 *    check(null)     | (4, 5)       | -            | true -> 4's subtree is valid
 *    check(6)        | (5, +inf)    | yes          | go left with (5, 6)
 *    check(3)        | (5, 6)       | 3 <= 5  no   | false
 *                    |              |              | && stops: 7 is never checked
 *
 *   Returns false.
 *
 * EDGE CASES
 *   [2,1,3]          -> true    simple valid BST
 *   [1,1]            -> false   equal values are not allowed (strict)
 *   [2147483647]     -> true    needs long bounds; an int bound of Integer.MAX_VALUE would reject it
 *   [5,1,4,null,null,3,6] -> false  the LeetCode example: right child 4 < 5
 *
 * COMMON MISTAKES
 *   - Only comparing each node with its own children: misses [5,4,6,null,null,3,7].
 *   - Using int bounds Integer.MIN_VALUE / MAX_VALUE: a node holding exactly that value is wrongly rejected.
 *   - Using < instead of <= in the failure check: duplicates would pass.
 *
 * COMPLEXITY
 *   O(n) time, O(h) extra space for the recursion stack.
 *
 * RELATED
 *   LeetCode 230 (KthSmallestInBST, Trees/): in-order traversal of a valid BST visits values in sorted order.
 *   LeetCode 235 (LowestCommonAncestorBST, Trees/): uses the same BST ordering to pick a side at each node.
 */
public class ValidateBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = build(new Integer[]{5, 4, 6, null, null, 3, 7});
        System.out.println(isValidBST(root));
    }

    public static boolean isValidBST(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean check(TreeNode node, long low, long high) {
        if (node == null) {
            return true;
        }
        if (node.val <= low || node.val >= high) {
            return false;
        }
        return check(node.left, low, node.val) && check(node.right, node.val, high);
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

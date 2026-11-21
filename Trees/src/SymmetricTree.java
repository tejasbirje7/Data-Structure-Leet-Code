import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 101. Symmetric Tree
 * https://leetcode.com/problems/symmetric-tree/
 *
 * PROBLEM
 *   - Input: the root of a binary tree (at least one node).
 *   - Return: true if the tree is a mirror of itself around its center.
 *
 * EXAMPLE
 *   Input: root = [1,2,2,3,4,4,3]
 *   Output: true
 *
 * HOW TO THINK
 *   - Clue: "mirror of itself" -> compare the left subtree with the right subtree, flipped.
 *   - Technique: a recursive helper mirror(a, b) that walks two subtrees at once in opposite directions.
 *     Two trees are mirrors if their roots match, a's left mirrors b's right, and a's right mirrors b's left.
 *   - Rule that stays true: mirror(a, b) is true only if subtree a is the exact mirror image of subtree b.
 *   - Decision: both null      -> true.
 *               only one null  -> false.
 *               values differ  -> false.
 *               otherwise      -> mirror(a.left, b.right) && mirror(a.right, b.left).
 *   - Return: mirror(root.left, root.right).
 *
 * STEPS
 *   1. Return mirror(root.left, root.right).
 *   2. If a and b are both null, return true; if only one is null, return false.
 *   3. If a.val != b.val, return false.
 *   4. Return mirror(a.left, b.right) && mirror(a.right, b.left).
 *
 * DRY RUN   input = [1,2,2,3,4,null,3]
 *
 *          1
 *        /   \
 *       2     2
 *      / \     \
 *     3   4     3          the outer pair (3, 3) matches, the inner pair (4, nothing) does not.
 *
 *    call | (a, b)        | check                 | action
 *   ------+---------------+-----------------------+-----------------------------------------------
 *     1   | (2, 2)        | both present, 2 == 2  | check outer (3, 3), then inner (4, null)
 *     2   | (3, 3)        | both present, 3 == 3  | check (null, null) and (null, null)
 *     3   | (null, null)  | both null             | true
 *     4   | (null, null)  | both null             | true -> call 2 returns true
 *     5   | (4, null)     | only one null         | false -> call 1 returns false
 *
 *   Returns false.
 *
 * EDGE CASES
 *   [1]                       -> true    a single node mirrors itself
 *   [1,2,2]                   -> true    two equal children
 *   [1,2,3]                   -> false   values differ
 *   [1,2,2,null,3,null,3]     -> false   same values, but both 3s are right children
 *
 * COMMON MISTAKES
 *   - Comparing a.left with b.left: that checks "same tree", not "mirror".
 *   - Reading a.val before the null checks: NullPointerException.
 *   - Checking that each level's values read the same both ways while skipping nulls:
 *     [1,2,2,null,3,null,3] has levels [1], [2,2], [3,3] but is not symmetric.
 *
 * COMPLEXITY
 *   O(n) time, O(h) extra space for the recursion stack.
 *
 * RELATED
 *   LeetCode 100 (SameTree, Trees/): the same two-pointer recursion, but compares left with left.
 *   LeetCode 226 (InvertBinaryTree, Trees/): builds the mirror image instead of checking for one.
 */
public class SymmetricTree {

    public static void main(String[] args) {
        TreeNode root = build(new Integer[]{1, 2, 2, 3, 4, null, 3});
        System.out.println(isSymmetric(root));
    }

    public static boolean isSymmetric(TreeNode root) {
        return mirror(root.left, root.right);
    }

    private static boolean mirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        return mirror(a.left, b.right) && mirror(a.right, b.left);
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

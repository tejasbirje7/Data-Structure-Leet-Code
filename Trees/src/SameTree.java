import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 100. Same Tree
 * https://leetcode.com/problems/same-tree/
 *
 * PROBLEM
 *   - Input: the roots p and q of two binary trees (0 to 100 nodes each).
 *   - Return: true if they have the same shape AND the same value at every position.
 *
 * EXAMPLE
 *   Input: p = [1,2,3], q = [1,2,3]
 *   Output: true
 *
 * HOW TO THINK
 *   - Clue: compare two trees position by position -> walk both at once with one recursion.
 *   - Technique: same(a, b) = a and b match here, AND their left subtrees match, AND their right subtrees match.
 *   - Rule that stays true: a and b are always at the same position in their trees.
 *   - Decision: both null            -> true  (both trees end here).
 *               only one null        -> false (shapes differ).
 *               values differ        -> false.
 *               otherwise            -> same(a.left, b.left) && same(a.right, b.right).
 *   - Return: the result for the two roots. && stops at the first mismatch.
 *
 * STEPS
 *   1. If both are null, return true.
 *   2. If only one is null, return false.
 *   3. If the values differ, return false.
 *   4. Return same(left, left) && same(right, right).
 *
 * DRY RUN   input = p = [1,2,3], q = [1,2,4]
 *
 *        p:  1          q:  1
 *           / \            / \
 *          2   3          2   4
 *
 *    call | (a, b)        | check                 | action
 *   ------+---------------+-----------------------+-----------------------------------------
 *     1   | (1, 1)        | both present, 1 == 1  | check left (2, 2), then right (3, 4)
 *     2   | (2, 2)        | both present, 2 == 2  | check (null, null) and (null, null)
 *     3   | (null, null)  | both null             | true
 *     4   | (null, null)  | both null             | true -> call 2 returns true
 *     5   | (3, 4)        | both present, 3 != 4  | false -> call 1 returns false
 *
 *   Returns false.
 *
 * EDGE CASES
 *   [], []                 -> true    two empty trees
 *   [1], []                -> false   only one is null
 *   [1,2], [1,null,2]      -> false   same values, different shape
 *   [1,2,1], [1,1,2]       -> false   same shape, children swapped
 *
 * COMMON MISTAKES
 *   - Reading p.val before the null checks: NullPointerException on uneven shapes.
 *   - Comparing in-order (or any single) traversal lists: different shapes can give the same list
 *     unless nulls are recorded too.
 *   - Returning true as soon as the roots match: every position has to be checked.
 *
 * COMPLEXITY
 *   O(min(n, m)) time: stops at the first difference. O(h) extra space for the recursion stack.
 *
 * RELATED
 *   LeetCode 101 (SymmetricTree, Trees/): the same two-pointer recursion, but compares left with right.
 *   LeetCode 226 (InvertBinaryTree, Trees/): mirrors a tree; a tree is symmetric if it is the same as its mirror.
 *   LeetCode 297 (SerializeDeserializeTree, Trees/): a string that records nulls also identifies a tree exactly.
 */
public class SameTree {

    public static void main(String[] args) {
        TreeNode p = build(new Integer[]{1, 2, 3});
        TreeNode q = build(new Integer[]{1, 2, 4});
        System.out.println(isSameTree(p, q));
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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

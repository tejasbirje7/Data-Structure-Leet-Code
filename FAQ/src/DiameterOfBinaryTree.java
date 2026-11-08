import java.util.HashMap;
import java.util.Stack;
import java.util.Map;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * LeetCode 543. Diameter of Binary Tree
 * https://leetcode.com/problems/diameter-of-binary-tree/
 *
 * PROBLEM
 *   - Input: the root of a binary tree.
 *   - Return: the diameter = the number of edges on the longest path between any two nodes.
 *   - The path does not have to pass through the root.
 *   - This file has two versions: iterative (stack + map) and recursive (DFS helper).
 *
 * EXAMPLE
 *   Input: root = [1,2,3,4,5]
 *   Output: 3
 *   Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * HOW TO THINK
 *   - Clue: "longest path between any two nodes" = at some node, the path goes down-left and down-right.
 *     So at every node: path through it = height(left) + height(right).
 *   - Technique: post-order DFS (children first, then the node).
 *     One pass computes each node's height and checks the path through it at the same time.
 *   - Rule that stays true: height(node) = 1 + max(height(left), height(right)), height(null) = 0.
 *     dia = the longest left + right seen so far, over all finished nodes.
 *   - Decision: at each finished node: dia = max(dia, left + right); hand back 1 + max(left, right).
 *   - Return: dia, not the root's height. Heights count nodes, so left + right counts edges.
 *   - Iterative version: same post-order, done by hand.
 *     Peek the top of the stack; push an unfinished child (left first, then right).
 *     When both children are finished, pop the node and store its height in the map.
 *     A node is "finished" once it is in the map.
 *
 * STEPS
 *   Recursive (solve + diameterOfBinaryTreeRecursive):
 *   1. dia = 0, then call solve(root).
 *   2. solve(null) returns 0.
 *   3. left = solve(node.left), right = solve(node.right).
 *   4. dia = max(dia, left + right); return 1 + max(left, right).
 *   5. Return dia.
 *   Iterative (diameterOfBinaryTree):
 *   1. null root -> 0. Push root; empty map; diameter = 0.
 *   2. While the stack is not empty, peek node.
 *   3. Left child exists and not in map -> push it. Else right child exists and not in map -> push it.
 *   4. Else pop node; read left/right heights from the map (0 if null);
 *      map[node] = 1 + max; diameter = max(diameter, left + right).
 *   5. Return diameter.
 *
 * DRY RUN   root = [1,2,3,4,5]   (dia is the value after the node finishes)
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *
 *   Calls in order: solve(1) -> solve(2) -> solve(4) -> solve(5) -> back to 2 -> back to 1 -> solve(3) -> back to 1.
 *   Nodes finish in post-order:
 *
 *    finish | node | left h | right h | left + right | dia | returns height
 *   --------+------+--------+---------+--------------+-----+----------------
 *      1    |  4   |   0    |    0    |      0       |  0  |       1
 *      2    |  5   |   0    |    0    |      0       |  0  |       1
 *      3    |  2   |   1    |    1    |      2       |  2  |       2
 *      4    |  3   |   0    |    0    |      0       |  2  |       1
 *      5    |  1   |   2    |    1    |      3       |  3  |       3
 *
 *   Iterative version: stack [1] -> [1,2] -> [1,2,4] -> pop 4 -> [1,2,5] -> pop 5 -> pop 2
 *     -> [1,3] -> pop 3 -> pop 1. Same finish order, same numbers.
 *   Both return 3 (path 4 -> 2 -> 1 -> 3).
 *
 * EDGE CASES
 *   null                          -> 0   no nodes, no edges
 *   [1]                           -> 0   one node: left + right = 0
 *   1 -> 2 -> 3 -> 4 (left chain) -> 3   a straight line; the best path starts at the root
 *   [1,2,null,3,4,5,null,null,6]  -> 4   best path 5-3-2-4-6 does not pass through the root
 *                                        (the path through root 1 is only 3)
 *
 * COMMON MISTAKES
 *   - Returning the root's left + right only: the longest path may sit inside a subtree.
 *     Check left + right at every node.
 *   - Counting nodes instead of edges (left + right + 1): heights count nodes, so left + right is already edges.
 *   - Returning dia from the helper: the helper must return the height; dia is kept on the side.
 *   - Forgetting to reset dia before each call: a second call would keep the old value.
 *
 * COMPLEXITY
 *   O(n) time (each node finishes once).
 *   Recursive: O(h) extra space for the call stack (h = tree height).
 *   Iterative: O(n) extra space for the map and stack.
 *
 * RELATED
 *   LeetCode 104 Maximum Depth of Binary Tree (not in this repo): just the height part of this solution.
 *   LeetCode 124 Binary Tree Maximum Path Sum (not in this repo): same "best path through each node" idea,
 *     but with node values and negative sums.
 *   Video: https://www.youtube.com/watch?v=zM9N_x_v_24&t=1s
 */
public class DiameterOfBinaryTree {

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        Map<TreeNode, Integer> map = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        int diameter = 0;

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            if (node.left != null && !map.containsKey(node.left)) {
                stack.push(node.left);
            }
            else if (node.right != null && !map.containsKey(node.right)) {
                stack.push(node.right);
            }
            else {
                stack.pop();

                int leftDepth = map.getOrDefault(node.left, 0);
                int rightDepth = map.getOrDefault(node.right, 0);

                map.put(node, 1 + Math.max(leftDepth, rightDepth));

                diameter = Math.max(diameter, leftDepth + rightDepth);
            }
        }

        return diameter;
    }

    int dia;

    int solve(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = solve(root.left);
        int right = solve(root.right);

        dia = Math.max(dia, left + right);

        return 1 + Math.max(left, right);
    }

    public int diameterOfBinaryTreeRecursive(TreeNode root) {
        dia = 0;
        solve(root);
        return dia;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        DiameterOfBinaryTree dobt = new DiameterOfBinaryTree();

        int diaIter = dobt.diameterOfBinaryTree(root);
        System.out.println("Diameter (Iterative) = " + diaIter);

        int diaRec = dobt.diameterOfBinaryTreeRecursive(root);
        System.out.println("Diameter (Recursive) = " + diaRec);
    }
}

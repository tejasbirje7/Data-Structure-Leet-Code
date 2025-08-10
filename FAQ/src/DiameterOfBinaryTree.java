import java.util.HashMap;
import java.util.Stack;
import java.util.Map;

// Definition for a binary tree node
// https://www.youtube.com/watch?v=zM9N_x_v_24&t=1s
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

// https://www.youtube.com/watch?v=zM9N_x_v_24&t=1s
public class DiameterOfBinaryTree {

    // This class contains TWO different implementations of diameterOfBinaryTree:
    // 1) Iterative using Stack + Map (avoids recursion)
    // 2) Recursive DFS using a helper function (simpler and more common)

    // ------------------------------------------------------------
    // 1) ITERATIVE IMPLEMENTATION
    // ------------------------------------------------------------
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        Map<TreeNode, Integer> map = new HashMap<>(); // Stores depth of each visited node
        Stack<TreeNode> stack = new Stack<>();        // Used for manual post-order traversal
        int diameter = 0;                             // Stores the maximum diameter found

        stack.push(root);

        // Iterative post-order traversal
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek(); // Look at top without removing it

            // If left child exists but hasn't been processed yet, go left
            if (node.left != null && !map.containsKey(node.left)) {
                stack.push(node.left);
            }
            // Else if right child exists but hasn't been processed yet, go right
            else if (node.right != null && !map.containsKey(node.right)) {
                stack.push(node.right);
            }
            // Else, both children processed → process this node
            else {
                stack.pop(); // Remove from stack

                // Get depths of left and right children
                int leftDepth = map.getOrDefault(node.left, 0);
                int rightDepth = map.getOrDefault(node.right, 0);

                // Store depth of current node
                map.put(node, 1 + Math.max(leftDepth, rightDepth));

                // Update diameter if this path is longer
                diameter = Math.max(diameter, leftDepth + rightDepth);
            }
        }

        return diameter;
    }

    // ------------------------------------------------------------
    // 2) RECURSIVE IMPLEMENTATION (simpler and common)
    // ------------------------------------------------------------
    int dia; // Stores the maximum diameter found

    // Helper function: returns height of the current subtree
    int solve(TreeNode root) {
        if (root == null) {
            return 0; // Height of empty tree is 0
        }

        // Recursively get left and right subtree heights
        int left = solve(root.left);
        int right = solve(root.right);

        // Update the diameter with the path passing through this node
        dia = Math.max(dia, left + right);

        // Return height of current node
        return 1 + Math.max(left, right);
    }

    public int diameterOfBinaryTreeRecursive(TreeNode root) {
        dia = 0;
        solve(root); // Fill 'dia' during DFS
        return dia;
    }

    // ------------------------------------------------------------
    // MAIN METHOD for Testing
    // ------------------------------------------------------------
    public static void main(String[] args) {
        // Create a sample binary tree
        /*
                  1
                 / \
                2   3
               / \
              4   5
        Expected Diameter = 3 (path: 4 -> 2 -> 1 -> 3 or 5 -> 2 -> 1 -> 3)
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        DiameterOfBinaryTree dobt = new DiameterOfBinaryTree();

        // Test Iterative Implementation
        int diaIter = dobt.diameterOfBinaryTree(root);
        System.out.println("Diameter (Iterative) = " + diaIter);

        // Test Recursive Implementation
        int diaRec = dobt.diameterOfBinaryTreeRecursive(root);
        System.out.println("Diameter (Recursive) = " + diaRec);
    }
}

/**
 * wo different implementations of diameterOfBinaryTree:
 *
 * Iterative using Stack + Map (first method)
 *
 * You simulate a post-order traversal with a stack.
 *
 * A Map<TreeNode, Integer> stores the computed depth for each node.
 *
 * At each node, you calculate its left and right depths, update the diameter, and store its own depth.
 *
 * This avoids recursion and works in O(n) time and O(n) space.
 *
 * Recursive (DFS) (diameterOfBinaryTreee + solve)
 *
 * Standard recursive DFS solution.
 *
 * The helper solve() returns the depth of the current subtree and updates a class-level variable dia to store the max path length.
 *
 * This also runs in O(n) time and O(h) space (h = height of tree due to recursion stack).
 */

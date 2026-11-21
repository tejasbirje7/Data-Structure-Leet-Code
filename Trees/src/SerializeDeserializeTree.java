import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * LeetCode 297. Serialize and Deserialize Binary Tree
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * PROBLEM
 *   - Design Codec with serialize(root) -> String and deserialize(String) -> root.
 *   - deserialize(serialize(root)) must rebuild exactly the same tree.
 *   - Any string format is allowed; values can be negative (-1000..1000), up to 10^4 nodes.
 *
 * EXAMPLE
 *   Input: root = [1,2,3,null,null,4,5]
 *   Output: [1,2,3,null,null,4,5]
 *
 * HOW TO THINK
 *   - Clue: "rebuild exactly the same tree" from ONE string -> record the empty spots too,
 *     otherwise different trees give the same traversal.
 *   - Technique: pre-order DFS (node, left, right), writing "#" for every null child, values joined by ",".
 *     With the nulls marked, pre-order alone fixes the shape: read a value -> it is the root,
 *     then the next tokens describe its left subtree completely, then its right subtree.
 *   - Rule that stays true: while deserializing, the next unread token is always the root of the
 *     subtree being built right now.
 *   - Decision (deserialize): token == "#" -> return null.
 *                             otherwise    -> make the node, build its left, then its right.
 *   - Return: serialize -> the joined string; deserialize -> the node built from the first token.
 *
 * STEPS
 *   1. serialize(node): null -> append "#,"; else append "val," then serialize left, then right.
 *   2. deserialize: split the string on ",", put the tokens in a queue.
 *   3. build(): poll a token; "#" -> null; else node = new TreeNode(token), node.left = build(), node.right = build().
 *   4. Return build().
 *
 * DRY RUN   input = [1,2,3,null,null,4,5]
 *
 *        1
 *       / \
 *      2   3
 *         / \
 *        4   5
 *
 *   Serialize (pre-order, one row per visit):
 *
 *    visit       | appends | string so far
 *   -------------+---------+--------------------------
 *    1           | 1,      | 1,
 *    2           | 2,      | 1,2,
 *    2.left null | #,      | 1,2,#,
 *    2.right null| #,      | 1,2,#,#,
 *    3           | 3,      | 1,2,#,#,3,
 *    4           | 4,      | 1,2,#,#,3,4,
 *    4.left null | #,      | 1,2,#,#,3,4,#,
 *    4.right null| #,      | 1,2,#,#,3,4,#,#,
 *    5           | 5,      | 1,2,#,#,3,4,#,#,5,
 *    5.left null | #,      | 1,2,#,#,3,4,#,#,5,#,
 *    5.right null| #,      | 1,2,#,#,3,4,#,#,5,#,#,
 *
 *   Deserialize "1,2,#,#,3,4,#,#,5,#,#," (one row per token read):
 *
 *    token | action                      | attached as
 *   -------+-----------------------------+-------------
 *     1    | new node 1, build its left  | root
 *     2    | new node 2, build its left  | 1.left
 *     #    | null                        | 2.left
 *     #    | null, node 2 done           | 2.right
 *     3    | new node 3, build its left  | 1.right
 *     4    | new node 4, build its left  | 3.left
 *     #    | null                        | 4.left
 *     #    | null, node 4 done           | 4.right
 *     5    | new node 5, build its left  | 3.right
 *     #    | null                        | 5.left
 *     #    | null, node 5 done, tree done| 5.right
 *
 *   Returns the same tree; its level order is [1,2,3,null,null,4,5].
 *
 * EDGE CASES
 *   []          -> "#,"          -> []        empty tree is a single null marker
 *   [1]         -> "1,#,#,"      -> [1]       a leaf has two null markers
 *   [-1,-2]     -> "-1,-2,#,#,#," -> [-1,-2]  negative and multi-character values are whole tokens
 *   10^4 nodes in one chain -> deep recursion; fits the default Java stack on LeetCode,
 *                              use an explicit stack if it ever overflows
 *
 * COMMON MISTAKES
 *   - Leaving out the null markers: [1,2] and [1,null,2] both give "1,2".
 *   - Building the string with += in recursion: O(n^2). Use a StringBuilder.
 *   - Reading digits one character at a time: breaks on "-12". Split on the separator.
 *   - Tracking the read position in a local int: each recursive call gets its own copy.
 *     Use a shared queue (or a field).
 *
 * COMPLEXITY
 *   O(n) time and O(n) extra space for both serialize and deserialize.
 *
 * RELATED
 *   LeetCode 105 (ConstructTreeFromPreorderInorder, Trees/): no null markers, so it needs a second traversal to fix the shape.
 *   LeetCode 102 (LevelOrderTraversal, Trees/): BFS order, the same format LeetCode uses to print trees.
 */
public class SerializeDeserializeTree {

    public static void main(String[] args) {
        TreeNode root = build(new Integer[]{1, 2, 3, null, null, 4, 5});
        Codec codec = new Codec();
        String data = codec.serialize(root);
        System.out.println(data);
        System.out.println(codec.serialize(codec.deserialize(data)).equals(data));
    }

    static class Codec {

        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            write(root, sb);
            return sb.toString();
        }

        private void write(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append("#,");
                return;
            }
            sb.append(node.val).append(',');
            write(node.left, sb);
            write(node.right, sb);
        }

        public TreeNode deserialize(String data) {
            Queue<String> tokens = new ArrayDeque<>(Arrays.asList(data.split(",")));
            return read(tokens);
        }

        private TreeNode read(Queue<String> tokens) {
            String token = tokens.poll();
            if (token.equals("#")) {
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(token));
            node.left = read(tokens);
            node.right = read(tokens);
            return node;
        }
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

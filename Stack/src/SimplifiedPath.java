import java.util.Stack;

/**
 * LeetCode 71. Simplify Path
 * https://leetcode.com/problems/simplify-path/
 *
 * PROBLEM
 *   - Input: an absolute Unix-style path (it starts with '/').
 *   - Return: the simplified canonical path: one leading '/', a single '/' between names,
 *     no trailing '/', and no '.' or '..' parts.
 *   - '.' = current directory, '..' = parent directory (at the root it stays at the root),
 *     '//' = '/', and any other name, even "...", is a normal directory.
 *
 * EXAMPLE
 *   Input: path = "/.../a/../b/c/../d/./"
 *   Output: "/.../b/d"
 *   Explanation:
 *   "..." is a valid name for a directory in this problem.
 *
 * HOW TO THINK
 *   - Clue: '..' undoes the most recent directory -> last in, first out -> stack.
 *   - Technique: split the path on '/', then walk the parts with a stack of directory names.
 *   - Rule that stays true: the stack, read bottom -> top, is the simplified path so far.
 *   - Decision: "" or "."      -> skip ("" comes from the leading '/' and from "//").
 *               ".."           -> pop, if the stack is not empty (at the root there is nothing to undo).
 *               anything else  -> push it (that includes "...").
 *   - Return: "/" + the stack joined with "/". java.util.Stack is a Vector,
 *     so join goes bottom -> top (root first). An empty stack gives "/".
 *
 * STEPS
 *   1. Split path on "/".
 *   2. For each part: skip "" and ".".
 *   3. ".." -> pop if the stack is not empty.
 *   4. Anything else -> push.
 *   5. Return "/" + String.join("/", stack).
 *
 * DRY RUN   path = "/../.../a//b/./../c/"
 *           parts = ["", "..", "...", "a", "", "b", ".", "..", "c"]   (stack bottom -> top, after the part)
 *
 *    part  | action                 | stack
 *   -------+------------------------+-------------
 *    ""    | skip (leading '/')     | []
 *    ".."  | stack empty, no pop    | []
 *    "..." | push (a normal name)   | [...]
 *    "a"   | push                   | [..., a]
 *    ""    | skip (from "//")       | [..., a]
 *    "b"   | push                   | [..., a, b]
 *    "."   | skip                   | [..., a, b]
 *    ".."  | pop b                  | [..., a]
 *    "c"   | push                   | [..., a, c]
 *
 *   Returns "/.../a/c". The trailing '/' adds no part: split drops trailing empty strings.
 *
 * EDGE CASES
 *   "/.../a/../b/c/../d/./"   -> "/.../b/d"    the LeetCode example
 *   "/"                       -> "/"           split gives no parts, the stack stays empty
 *   "/home//foo/"             -> "/home/foo"   "//" gives an empty part, which is skipped
 *   "/../"                    -> "/"           '..' at the root does nothing
 *   "/a/./b/../../c/"         -> "/c"          two '..' undo b and then a
 *
 * COMMON MISTAKES
 *   - Treating "..." (or "..a") like '..': only the exact strings "." and ".." are special.
 *   - Popping on '..' without checking for empty: "/../" throws EmptyStackException.
 *   - Using ArrayDeque push/pop and then String.join: it iterates top -> bottom, so the path comes out reversed.
 *   - Returning "" for an empty stack: the root is "/".
 *
 * COMPLEXITY
 *   O(n) time, O(n) extra space (the parts array and the stack).
 *
 * RELATED
 *   LeetCode 20 (ValidParentheses, Stack/): the same push / pop-the-newest pattern, on bracket chars.
 *   LeetCode 151 (ReverseWordsInString, ArraysAndStrings/): also has to ignore repeated separators
 *     (extra spaces) and rebuild a clean string.
 */
public class SimplifiedPath {
    public static void main(String[] args) {
        String path = "/../.../a//b/./../c/";
        System.out.println(simplifyPath(path));
    }

    public static String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] directories = path.split("/");
        for (String dir : directories) {
            if (dir.equals(".") || dir.isEmpty()) {
                continue;
            } else if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(dir);
            }
        }
        return "/" + String.join("/", stack);
    }
}

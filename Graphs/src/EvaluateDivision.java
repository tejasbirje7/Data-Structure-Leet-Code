import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 399. Evaluate Division
 * https://leetcode.com/problems/evaluate-division/
 *
 * PROBLEM
 *   - Input: equations [Ai, Bi] with values[i] meaning Ai / Bi = values[i] (all values > 0, no contradictions),
 *     and queries [Cj, Dj].
 *   - Return: for each query, Cj / Dj, or -1.0 if it cannot be worked out.
 *   - A variable that never appears in any equation is unknown, even in x / x.
 *
 * EXAMPLE
 *   Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0],
 *          queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 *   Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 *   Explanation: Given: a / b = 2.0, b / c = 3.0
 *     queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 *     return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 *     note: x is undefined => -1.0
 *
 * HOW TO THINK
 *   - Clue: ratios that chain (a / b and b / c give a / c) -> variables are nodes, each equation is a weighted edge,
 *     and a query is a path whose weights multiply.
 *   - Technique: build a graph with both directions: a -> b weight v, b -> a weight 1 / v.
 *     For each query, DFS from C looking for D, multiplying weights on the way.
 *   - Rule that stays true: dfs(node, product) means "C / node = product".
 *     When node == D, product is the answer.
 *   - Decision: C or D not in the graph -> -1.0. In the DFS: node == D -> return product;
 *     else try each unvisited neighbour with product * weight; the first non -1 result wins; none -> -1.0.
 *   - Return: one double per query.
 *
 * STEPS
 *   1. For each equation (a, b, v): graph[a][b] = v, graph[b][a] = 1 / v.
 *   2. For each query (c, d): if c or d is not in the graph, answer -1.0.
 *   3.   Else answer dfs(c, d, 1.0, new visited set).
 *   4. dfs(node, d, product): node == d -> product. Mark node visited.
 *        For each unvisited neighbour: r = dfs(neighbour, d, product * weight); r != -1 -> return r.
 *        Return -1.0.
 *
 * DRY RUN   input = the example. Graph: a -> b (2), b -> a (0.5), b -> c (3), c -> b (1/3).
 *
 *    query | in graph? | DFS path (product after each move)          | answer
 *   -------+-----------+---------------------------------------------+--------
 *    a / c | yes, yes  | a (1) -> b (1*2 = 2) -> c (2*3 = 6), c == d | 6.0
 *    b / a | yes, yes  | b (1) -> a (1*0.5 = 0.5), a == d            | 0.5
 *    a / e | e: no     | no DFS                                      | -1.0
 *    a / a | yes, yes  | a (1), a == d right away                    | 1.0
 *    x / x | x: no     | no DFS (x is undefined)                     | -1.0
 *
 *   Returns [6.0, 0.5, -1.0, 1.0, -1.0].
 *
 * EDGE CASES
 *   a/b = 2, c/d = 3, query a / c   -> -1.0   two separate groups: no path
 *   a/b = 2, query b / b            -> 1.0    b is known, so b / b = 1
 *   a/b = 2, query x / x            -> -1.0   x is not known at all
 *   a/b = 2, b/c = 3, c/d = 4, query d / a   -> 1/24   path d -> c -> b -> a, weights 1/4 * 1/3 * 1/2
 *
 * COMMON MISTAKES
 *   - Adding only a -> b: queries like b / a then fail. Add the reverse edge with weight 1 / v.
 *   - Forgetting the visited set: a -> b -> a -> b ... loops forever.
 *   - Returning 1.0 for x / x when x is unknown: check the graph contains both variables first.
 *   - Using 0 as "not found": a real answer is never 0 here, but -1.0 is what the problem asks for.
 *
 * COMPLEXITY
 *   O(Q * (V + E)) time: each query may visit the whole graph. O(V + E) extra space for the graph.
 *   (Weighted union-find answers each query in near O(1) after building, if queries are many.)
 *
 * RELATED
 *   LeetCode 133 (CloneGraph, Graphs/): DFS over an explicit graph with a visited map.
 *   LeetCode 207 (CourseSchedule, Graphs/): build a graph from pairs first, then search it.
 */
public class EvaluateDivision {

    public static void main(String[] args) {
        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("a", "e"),
                Arrays.asList("a", "a"), Arrays.asList("x", "x"));
        System.out.println(Arrays.toString(calcEquation(equations, values, queries)));
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            graph.computeIfAbsent(a, k -> new HashMap<>()).put(b, values[i]);
            graph.computeIfAbsent(b, k -> new HashMap<>()).put(a, 1.0 / values[i]);
        }
        double[] answers = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String c = queries.get(i).get(0);
            String d = queries.get(i).get(1);
            if (!graph.containsKey(c) || !graph.containsKey(d)) {
                answers[i] = -1.0;
            } else {
                answers[i] = dfs(graph, c, d, 1.0, new HashSet<>());
            }
        }
        return answers;
    }

    private static double dfs(Map<String, Map<String, Double>> graph, String node, String target,
                              double product, Set<String> visited) {
        if (node.equals(target)) {
            return product;
        }
        visited.add(node);
        for (Map.Entry<String, Double> edge : graph.get(node).entrySet()) {
            if (!visited.contains(edge.getKey())) {
                double result = dfs(graph, edge.getKey(), target, product * edge.getValue(), visited);
                if (result != -1.0) {
                    return result;
                }
            }
        }
        return -1.0;
    }

}

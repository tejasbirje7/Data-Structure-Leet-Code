import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 133. Clone Graph
 * https://leetcode.com/problems/clone-graph/
 *
 * PROBLEM
 *   - Input: a reference to one node of a connected undirected graph (or null).
 *     Each node has an int val (1 to 100, unique) and a list of neighbours.
 *   - Return: a deep copy of the whole graph: new nodes only, with the same values and the same connections.
 *
 * EXAMPLE
 *   Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 *   Output: [[2,4],[1,3],[2,4],[1,3]]
 *   Explanation: There are 4 nodes in the graph.
 *     1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 *     2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 *     3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 *     4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 *
 * HOW TO THINK
 *   - Clue: "deep copy" of something with cycles -> you must remember which nodes are already copied,
 *     or the copy loops forever and duplicates nodes.
 *   - Technique: DFS + a map original -> copy. The map is both the "visited" set and the way to find each copy.
 *   - Rule that stays true: a node is in the map as soon as its copy is created, before its neighbours are visited.
 *     So a cycle back to it just returns the existing copy.
 *   - Decision for each node: already in the map -> return its copy;
 *     else create the copy, put it in the map, then clone every neighbour and add those copies to its list.
 *   - Return: the copy of the starting node.
 *
 * STEPS
 *   1. If node is null, return null.
 *   2. If node is in the map, return map.get(node).
 *   3. copy = new Node(node.val); map.put(node, copy).
 *   4. For each neighbour: copy.neighbors.add(clone(neighbour)).
 *   5. Return copy.
 *
 * DRY RUN   input = the example graph 1-2, 2-3, 3-4, 4-1   (map is after the call creates its copy)
 *
 *    step | depth | call     | in map? | action                         | map keys | copy's neighbours when the call ends
 *   ------+-------+----------+---------+--------------------------------+----------+-------------------------------------
 *      1  |   0   | clone(1) |   no    | create 1', visit 2 then 4      | 1        | [2', 4']
 *      2  |   1   | clone(2) |   no    | create 2', visit 1 then 3      | 1 2      | [1', 3']
 *      3  |   2   | clone(1) |   yes   | return 1' (cycle stops here)   | 1 2      |
 *      4  |   2   | clone(3) |   no    | create 3', visit 2 then 4      | 1 2 3    | [2', 4']
 *      5  |   3   | clone(2) |   yes   | return 2'                      | 1 2 3    |
 *      6  |   3   | clone(4) |   no    | create 4', visit 1 then 3      | 1 2 3 4  | [1', 3']
 *      7  |   4   | clone(1) |   yes   | return 1'                      | 1 2 3 4  |
 *      8  |   4   | clone(3) |   yes   | return 3'                      | 1 2 3 4  |
 *      9  |   1   | clone(4) |   yes   | return 4' (back in clone(1))   | 1 2 3 4  |
 *
 *   Returns 1'. Copy: 1' -> [2',4'], 2' -> [1',3'], 3' -> [2',4'], 4' -> [1',3'], all new objects.
 *
 * EDGE CASES
 *   []        -> []      null input: return null
 *   [[]]      -> [[]]    one node, no neighbours
 *   [[2],[1]] -> [[2],[1]]   two nodes pointing at each other: the map stops the 1 <-> 2 loop
 *
 * COMMON MISTAKES
 *   - Putting the copy in the map AFTER cloning the neighbours: a cycle recurses forever. Put it in first.
 *   - Adding the original neighbour to the copy's list instead of its clone: the copy points into the old graph.
 *   - Using val as the map key without checking it is unique: here values are unique, but a node-keyed map is always safe.
 *
 * COMPLEXITY
 *   O(V + E) time (each node is copied once, each edge is followed from both ends), O(V) extra space for the map and recursion.
 *
 * RELATED
 *   LeetCode 138 (CopyListWithRandomPointer, LinkedList/): the same original -> copy map, on a list with extra pointers.
 *   LeetCode 200 (NumberOfIslands, Graphs/): DFS with a "visited" mark, on a grid instead of node objects.
 */
public class CloneGraph {

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.neighbors.add(n2);
        n1.neighbors.add(n4);
        n2.neighbors.add(n1);
        n2.neighbors.add(n3);
        n3.neighbors.add(n2);
        n3.neighbors.add(n4);
        n4.neighbors.add(n1);
        n4.neighbors.add(n3);
        Node copy = cloneGraph(n1);
        System.out.println(toAdjList(copy, 4));
        System.out.println("same object as the original: " + (copy == n1));
    }

    public static Node cloneGraph(Node node) {
        return clone(node, new HashMap<>());
    }

    private static Node clone(Node node, Map<Node, Node> copies) {
        if (node == null) {
            return null;
        }
        if (copies.containsKey(node)) {
            return copies.get(node);
        }
        Node copy = new Node(node.val);
        copies.put(node, copy);
        for (Node neighbor : node.neighbors) {
            copy.neighbors.add(clone(neighbor, copies));
        }
        return copy;
    }

    private static List<List<Integer>> toAdjList(Node start, int n) {
        Node[] byVal = new Node[n + 1];
        collect(start, byVal);
        List<List<Integer>> adj = new ArrayList<>();
        for (int v = 1; v <= n; v++) {
            List<Integer> row = new ArrayList<>();
            for (Node nb : byVal[v].neighbors) {
                row.add(nb.val);
            }
            adj.add(row);
        }
        return adj;
    }

    private static void collect(Node node, Node[] byVal) {
        if (byVal[node.val] != null) {
            return;
        }
        byVal[node.val] = node;
        for (Node nb : node.neighbors) {
            collect(nb, byVal);
        }
    }

    static class Node {
        int val;
        List<Node> neighbors = new ArrayList<>();

        Node(int val) {
            this.val = val;
        }
    }

}

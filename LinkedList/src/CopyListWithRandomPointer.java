import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * A linked list of length n is given such that each node contains an additional random pointer,
     * which could point to any node in the list, or null.
     *
     * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
     * where each new node has its value set to the value of its corresponding original node.
     * Both the next and random pointer of the new nodes should point to new nodes in the copied list such
     * that the pointers in the original list and copied list represent the same list state.
     * None of the pointers in the new list should point to nodes in the original list.
     *
     * For example, if there are two nodes X and Y in the original list, where X.random --> Y,
     * then for the corresponding two nodes x and y in the copied list, x.random --> y.
     * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
     */

    public Node copyRandomList(Node head) {
        Map<Node,Node> map = new HashMap<>();
        map.put(null,null);
        Node curr = head;

        while (curr != null) {
            Node copyNode = new Node(curr.val);
            map.put(curr,copyNode);
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            Node copyNode = map.get(curr);
            copyNode.next = map.get(curr.next);
            copyNode.random = map.get(curr.random);
            curr = curr.next;
        }

        return map.get(head);

    }
}

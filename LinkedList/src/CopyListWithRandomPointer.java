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

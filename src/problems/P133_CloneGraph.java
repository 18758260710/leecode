package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P133_CloneGraph {
    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    //my solution1 1ms
    Map<Node,Node> pair = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (pair.containsKey(node)){
            return pair.get(node);
        }
        Node newNode = new Node();
        newNode.val = node.val;
        pair.put(node,newNode);

        List<Node> neighbors = new ArrayList<>();
        for (Node neighbor:node.neighbors){
            neighbors.add(cloneGraph(neighbor));
        }
        newNode.neighbors = neighbors;
        return newNode;
    }
}


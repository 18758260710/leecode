package problems;

import java.util.ArrayList;
import java.util.List;

public class PopulatingNextRightPointersinEachNode_116 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    //my solution1 0ms
    public Node connect(Node root) {
        helper(new ArrayList<>(),root,0);
        return root;
    }

    public void helper(List<Node> result, Node root, int level) {
        if (root != null) {
            if(result.size() <= level)
                result.add(root);
            else {
                result.get(level).next = root;
                result.set(level, root);
            }
            helper(result, root.left, level + 1);
            helper(result, root.right, level + 1);
        }
    }

    //0ms 要求是完全二叉树 solution2
    public Node connect2(Node root) {
        link(root,null);
        return root;
    }

    public void link(Node curr, Node next){
        if (curr == null){
            return;
        }
        curr.next = next;
        link(curr.left,curr.right);//当前节点的左节点和右节点相连
        if(next == null){//当前节点的右节点和当前节点next的左节点相连
            link(curr.right,null);
        }else{
            link(curr.right,next.left);
        }

    }
}

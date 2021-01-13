package problems;

import java.util.ArrayList;
import java.util.List;
import problems.P116_PopulatingNextRightPointersinEachNode.Node;

public class P117_PopulatingNextRightPointersinEachNodeII {
    //my solution1 same as 116
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

    //0ms same as 116 solution2 按层遍历
    public Node connect2(Node root) {
        if (root == null) return null;
        Node cur = root;
        Node pre = null;
        Node head = null;//每行第一个
        while (cur != null) {
            while (cur != null) {
                if (cur.left != null) {
                    if (pre != null) {
                        pre.next = cur.left;
                    }else {
                        head = cur.left;
                    }
                    pre = cur.left;
                }
                if (cur.right != null) {
                    if (pre != null) {
                        pre.next = cur.right;
                    }else {
                        head = cur.right;
                    }
                    pre = cur.right;
                }
                cur = cur.next;
            }
            cur = head;
            head = null;
            pre = null;
        }

        return root;
    }
}

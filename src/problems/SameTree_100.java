package problems;

import java.util.ArrayDeque;
import problems.P94_BinaryTreeInorderTraversal.TreeNode;

public class SameTree_100 {

    //my solution1 0ms same as official solution1
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    //official solution2 双端队列来遍历
    public boolean check(TreeNode p, TreeNode q) {
        // p and q are null
        if (p == null && q == null) {
            return true;
        }
        // one of p and q is null
        if (q == null || p == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return true;
    }

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (!check(p, q)) {
            return false;
        }

        // init deques
        ArrayDeque<TreeNode> deqP = new ArrayDeque<>();
        ArrayDeque<TreeNode> deqQ = new ArrayDeque<>();
        deqP.addLast(p);
        deqQ.addLast(q);

        while (!deqP.isEmpty()) {
            p = deqP.removeFirst();
            q = deqQ.removeFirst();

            if (!check(p, q)) {
                return false;
            }
            if (p != null) {
                // in Java nulls are not allowed in Deque
                if (!check(p.left, q.left)) {
                    return false;
                }
                if (p.left != null) {
                    deqP.addLast(p.left);
                    deqQ.addLast(q.left);
                }
                if (!check(p.right, q.right)) {
                    return false;
                }
                if (p.right != null) {
                    deqP.addLast(p.right);
                    deqQ.addLast(q.right);
                }
            }
        }
        return true;
    }
}

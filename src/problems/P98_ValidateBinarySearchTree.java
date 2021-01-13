package problems;

import java.util.LinkedList;
import java.util.Stack;
import problems.P94_BinaryTreeInorderTraversal.TreeNode;

public class P98_ValidateBinarySearchTree {

    //my solution1 0ms
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return explore(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean explore(TreeNode root, long minValue, long maxValue) {
        if (root.left != null) {
            if (root.left.val <= minValue || root.left.val >= root.val) {
                return false;
            }
            if (!explore(root.left, minValue, root.val)) {
                return false;
            }
        }
        if (root.right != null) {
            if (root.right.val >= maxValue || root.right.val <= root.val) {
                return false;
            }
            if (!explore(root.right, root.val, maxValue)) {
                return false;
            }
        }
        return true;
    }

    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }

        int val = node.val;
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }

        if (!helper(node.right, val, upper)) {
            return false;
        }
        if (!helper(node.left, lower, val)) {
            return false;
        }
        return true;
    }

    //official solution1   Recursion same as mine better
    public boolean isValidBST2(TreeNode root) {
        return helper(root, null, null);
    }

    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<Integer> uppers = new LinkedList<>(),
        lowers = new LinkedList<>();

    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }

    //official solution2  DFS
    public boolean isValidBST3(TreeNode root) {
        Integer lower = null, upper = null, val;
        update(root, lower, upper);

        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();

            if (root == null) {
                continue;
            }
            val = root.val;
            if (lower != null && val <= lower) {
                return false;
            }
            if (upper != null && val >= upper) {
                return false;
            }
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }

    //official solution3  Inorder traversal 后面的比前面小就不行
    public boolean isValidBST4(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        double inorder = - Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }
}

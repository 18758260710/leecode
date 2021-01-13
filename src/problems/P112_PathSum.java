package problems;

import problems.P94_BinaryTreeInorderTraversal.TreeNode;

public class P112_PathSum {
    //my solution1 0ms
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left==null&&root.right==null)return sum==root.val;
        if (root.right==null)return hasPathSum(root.left, sum - root.val);
        if (root.left==null)return hasPathSum(root.right, sum - root.val);

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}

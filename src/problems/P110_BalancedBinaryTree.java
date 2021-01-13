package problems;

import problems.P94_BinaryTreeInorderTraversal.TreeNode;

public class P110_BalancedBinaryTree {
    //my solution1 1ms
    public boolean isBalanced(TreeNode root) {
        return helper(root)>0;
    }

    private int helper(TreeNode root) {
        if (root==null){
            return 1;
        }else {
            //不需要两个都算好就能返回加速
            int leftHight = helper(root.left);
            int rightHight = helper(root.right);
            if (leftHight<0||rightHight<0)return -1;
            if (Math.abs(leftHight - rightHight) > 1)return -1;
            return Math.max(leftHight,rightHight)+1;
        }
    }

    //1ms
    public boolean isBalanced2(TreeNode root) {
        return root == null || getHeight(root) != -1;

    }
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}

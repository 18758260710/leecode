package problems;

import problems.BinaryTreeInorderTraversal_94.TreeNode;

public class BinaryTreeMaximumPathSum_124 {
    //my solution1 1ms
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root==null)return 0;
        helper(root);
        return max;
    }

    private int helper(TreeNode root) {
        int temp;
        if (root.left==null&&root.right==null){
            temp = root.val;
        }else if (root.right==null){
            int left = helper(root.left);
            temp = left>0?left+root.val:root.val;
        }else if (root.left==null){
            int right = helper(root.right);
            temp = right>0?right+root.val:root.val;
        }else {
            int left = helper(root.left);
            int right = helper(root.right);
            int y = left+right+root.val;
            max = Math.max(max,y);
            int mm = Math.max(left,right);
            temp = mm>0?mm+root.val:root.val;
        }
        max = Math.max(max,temp);
        return temp;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-2);
//        root.left = new TreeNode(-1);
        System.out.println(new BinaryTreeMaximumPathSum_124().maxPathSum(root));
    }

    //更简明 一样的思想
    int maxValue;

    public int maxPathSum2(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }
}

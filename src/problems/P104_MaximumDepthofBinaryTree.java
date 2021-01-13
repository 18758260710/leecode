package problems;

import problems.P94_BinaryTreeInorderTraversal.TreeNode;

public class P104_MaximumDepthofBinaryTree {
    //my solution1 0ms
    public int maxDepth(TreeNode root) {
        return root==null?0:(Math.max(maxDepth(root.left),maxDepth(root.right))+1);
    }
}

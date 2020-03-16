package problems;

import problems.BinaryTreeInorderTraversal_94.TreeNode;

public class MaximumDepthofBinaryTree_104 {
    //my solution1 0ms
    public int maxDepth(TreeNode root) {
        return root==null?0:(Math.max(maxDepth(root.left),maxDepth(root.right))+1);
    }
}

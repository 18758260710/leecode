package problems;


import problems.BinaryTreeInorderTraversal_94.TreeNode;

public class MinimumDepthofBinaryTree_111 {
    //0ms
    public int minDepth(TreeNode root) {
        if (root==null)return 0;
        if (root.left==null&&root.right==null)return 1;
        if (root.right==null)return minDepth(root.left)+1;
        if (root.left==null)return minDepth(root.right)+1;
        return Math.min(minDepth(root.left),minDepth(root.right))+1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(new MinimumDepthofBinaryTree_111().minDepth(root));
    }
}

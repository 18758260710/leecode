package problems;
import problems.BinaryTreeInorderTraversal_94.TreeNode;

/**
 * Created by Administrator on 2020/2/4.
 */
public class InvertBinaryTree_226 {
    public TreeNode invertTree(TreeNode root) {
        help(root);
        return root;
    }

    private void help(TreeNode root) {
        if (root==null)return;
        help(root.right);
        help(root.left);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}

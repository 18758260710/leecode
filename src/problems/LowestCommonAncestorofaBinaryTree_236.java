package problems;
import problems.BinaryTreeInorderTraversal_94.TreeNode;

/**
 * Created by Administrator on 2020/2/7.
 */
public class LowestCommonAncestorofaBinaryTree_236 {
    //递归查找
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null||root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        }
        return null;

    }
}

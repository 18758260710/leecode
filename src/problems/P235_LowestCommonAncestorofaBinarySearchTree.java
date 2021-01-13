package problems;
import problems.P94_BinaryTreeInorderTraversal.TreeNode;
/**
 * Created by Administrator on 2020/2/7.
 */
public class P235_LowestCommonAncestorofaBinarySearchTree {
    //6ms
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val<p.val&&root.val<q.val){
            return lowestCommonAncestor(root.right,p,q);
        }else if (root.val>p.val&&root.val>q.val){
            return lowestCommonAncestor(root.left,p,q);
        }
        return root;
    }
}

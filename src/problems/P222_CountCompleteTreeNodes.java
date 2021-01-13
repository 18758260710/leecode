package problems;
import problems.P94_BinaryTreeInorderTraversal.TreeNode;

/**
 * Created by Administrator on 2020/1/20.
 */
public class P222_CountCompleteTreeNodes {
    //递归
    public int countNodes2(TreeNode root) {
        if (root==null) return 0;
        int ld = getDepth(root.left);
        int rd = getDepth(root.right);
        if(ld == rd) return (1 << ld) + countNodes2(root.right); // 1(根节点) + (1 << ld)-1(左完全左子树节点数) + 右子树节点数量
        else return (1 << rd) + countNodes2(root.left);  // 1(根节点) + (1 << rd)-1(右完全右子树节点数) + 左子树节点数量

    }

    //减少重复
    public int countNodes(TreeNode root) {
        return countNodes(root,null);
    }

    public int countNodes(TreeNode root,Integer depth) {
        if (root==null) return 0;
        int ld;
        if (depth==null){
            ld= getDepth(root.left);
        }else {
            ld = depth-1;
        }
        int rd = getDepth(root.right);
        if(ld == rd) return (1 << ld) + countNodes(root.right,rd);
        else return (1 << rd) + countNodes(root.left,ld);
    }

    private int getDepth(TreeNode r) {
        int depth = 0;
        while(r != null) {
            depth++;
            r = r.left;
        }
        return depth;
    }
}

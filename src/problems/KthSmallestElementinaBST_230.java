package problems;

import problems.BinaryTreeInorderTraversal_94.TreeNode;

import java.util.LinkedList;

/**
 * Created by Administrator on 2020/2/5.
 */
public class KthSmallestElementinaBST_230 {
    class Pair{
        Integer val;
        Integer count;

        public Pair(Integer val, Integer count) {
            this.val = val;
            this.count = count;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        Pair result = help(root,k);
        return result.val;
    }

    private Pair help(TreeNode root, int k) {
        if (root==null)return new Pair(null,0);
        Pair left = help(root.left,k);
        if (left.val!=null)return left;
        if (left.count==k-1)return new Pair(root.val,0);
        Pair right = help(root.right,k-left.count-1);
        if (right.val!=null)return right;
        return new Pair(null,left.count+right.count+1);
    }

    //中序遍历
    public int kthSmallest2(TreeNode root, int k) {
        // 第二种方式：迭代实现中序遍历，找到结果提前结束
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (true){
            while (root != null){
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (--k == 0) return root.val;
            root = root.right;
        }

    }
}

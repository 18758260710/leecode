package problems;

import problems.BinaryTreeInorderTraversal_94.TreeNode;

public class FlattenBinaryTreetoLinkedList_114 {
    //my solution1 1ms
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        if (root.left != null) {
            if (root.right == null) {
                root.right = root.left;
                root.left=null;
            }else {
                TreeNode temp = root.left;
                while (temp.right!=null){
                    temp = temp.right;
                }
                temp.right=root.right;
                root.right=root.left;
                root.left=null;
            }
        }
    }

    //0ms 其实是一样的
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        flatten2(left);
        flatten2(right);
        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        cur.right = right;
    }
}

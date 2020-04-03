package problems;

import java.util.Stack;
import problems.BinaryTreeInorderTraversal_94.TreeNode;

public class ConstructBinaryTreefromInorderandPostorderTraversal_106 {

    //my solution1 从后面找 碰巧样例
    int index = 0;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        index = inorder.length - 1;
        return helper(postorder, inorder, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] post, int[] in, int i, int j) {
        if (i > j) {
            return null;
        }

        int mid = j;
        while (mid > i && post[index] != in[mid]) {
            mid--;
        }

        TreeNode root = new TreeNode(post[index]);
        index--;

        root.right = helper(post, in, mid + 1, j);
        root.left = helper(post, in, i, mid - 1);
        return root;
    }

    public static void main(String[] args) {
        TreeNode a = new ConstructBinaryTreefromInorderandPostorderTraversal_106()
            .buildTree3(new int[]{2,0,1,4,2,3,2},new int[]{2,1,0,2,4,3,2});
        System.out.println(a);
    }

    //my solution2 using stack
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0 ||
            inorder == null || inorder.length == 0) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(postorder[inorder.length - 1]);
        TreeNode cur = root;
        for (int i = inorder.length - 2, j = inorder.length - 1; i >= 0; i--) {
            if (cur.val != inorder[j]) {
                cur.right = new TreeNode(postorder[i]);
                stack.push(cur);
                cur = cur.right;
            } else {
                j--;
                while (!stack.empty() && stack.peek().val == inorder[j]) {
                    cur = stack.pop();
                    j--;
                }
                cur.left = new TreeNode(postorder[i]);
                cur = cur.left;
            }
        }
        return root;
    }


    //1ms 思想和my solution2一样 只是用递归代替栈
    int pInorder;   // index of inorder array
    int pPostorder; // index of postorder array

    private TreeNode buildTree(int[] inorder, int[] postorder, TreeNode end) {
        if (pPostorder < 0) {
            return null;
        }

        // create root node
        TreeNode n = new TreeNode(postorder[pPostorder--]);

        // if right node exist, create right subtree
        if (inorder[pInorder] != n.val) {
            n.right = buildTree(inorder, postorder, n);
        }

        pInorder--;

        // if left node exist, create left subtree
        if ((end == null) || (inorder[pInorder] != end.val)) {
            n.left = buildTree(inorder, postorder, end);
        }

        return n;
    }

    public TreeNode buildTree3(int[] inorder, int[] postorder) {
        pInorder = inorder.length - 1;
        pPostorder = postorder.length - 1;

        return buildTree(inorder, postorder, null);
    }
}

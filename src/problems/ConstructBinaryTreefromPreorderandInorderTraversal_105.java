package problems;

import java.util.Arrays;
import java.util.Stack;
import problems.BinaryTreeInorderTraversal_94.TreeNode;

public class ConstructBinaryTreefromPreorderandInorderTraversal_105 {

    //my solution1 Arrays.copyOfRange费时
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        TreeNode result = new TreeNode(preorder[0]);
        if (preorder.length == 1) {
            return result;
        }
        int i = 0;
        for (; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                break;
            }
        }
        result.left = buildTree(Arrays.copyOfRange(preorder, 1, i + 1), Arrays.copyOfRange(inorder, 0, i));
        result.right = buildTree(Arrays.copyOfRange(preorder, i + 1, preorder.length),
            Arrays.copyOfRange(inorder, i + 1, inorder.length));
        return result;
    }

    public static void main(String[] args) {
        TreeNode a = new ConstructBinaryTreefromPreorderandInorderTraversal_105()
            .buildTree3(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(a);
    }

    //my solution2 still slow
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] preorder, int preorderLeft, int preorderRight, int[] inorder, int inorderLeft,
        int inorderRight) {
        if (preorderLeft > preorderRight) {
            return null;
        }
        TreeNode result = new TreeNode(preorder[preorderLeft]);
        if (preorderLeft == preorderRight) {
            return result;
        }
        int i = inorderLeft;
        for (; i <= inorderRight; i++) {
            if (inorder[i] == preorder[preorderLeft]) {
                break;
            }
        }
        result.left = helper(preorder, preorderLeft + 1, preorderLeft + i - inorderLeft, inorder, inorderLeft, i - 1);
        result.right = helper(preorder, preorderLeft + i - inorderLeft+1, preorderRight, inorder, i + 1, inorderRight);
        return result;
    }

    //用栈 双游标遍历 2ms
    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 ||
            inorder == null || inorder.length == 0) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode cur = root;
        for (int i = 1, j = 0; i < preorder.length; i++) {
            //说明preorder[i]对应cur的左节点
            if (cur.val != inorder[j]) {
                cur.left = new TreeNode(preorder[i]);
                stack.push(cur);
                cur = cur.left;
            } else {
                //找到对应的cur
                j++;
                while (!stack.empty() && stack.peek().val == inorder[j]) {
                    cur = stack.pop();
                    j++;
                }
                //说明preorder[i]对应cur的右节点
                cur.right = new TreeNode(preorder[i]);
                cur = cur.right;
            }
        }
        return root;
    }

    //1ms 类似 my solution2 从后面找 碰巧样例
    int index = 0;
    public TreeNode buildTree4(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] pre, int[] in, int i, int j) {
        if (i > j) return null;

        int mid = j;
        while (mid > i && pre[index] != in[mid]) mid --;

        TreeNode root = new TreeNode(pre[index]);
        index ++;

        root.left = helper(pre, in, i, mid - 1);
        root.right = helper(pre, in, mid + 1, j);
        return root;
    }
}

package problems;

import problems.P94_BinaryTreeInorderTraversal.TreeNode;

public class P129_SumRoottoLeafNumbers {

    int result = 0;

    //my solution1 dfs 0ms
    public int sumNumbers(TreeNode root) {
        int temp = 0;
        if (root == null) {
            return result;
        }
        helper(temp, root);

        return result;
    }

    private void helper(int temp, TreeNode root) {
        if (root.left == null && root.right == null) {
            result += temp * 10 + root.val;
        }
        if (root.left != null) {
            helper(temp * 10 + root.val, root.left);
        }
        if (root.right != null) {
            helper(temp * 10 + root.val, root.right);
        }
    }

    //一样的
    int sum;

    public int sumNumbers2(TreeNode root) {
        sum = 0;
        helpme(root, 0);
        return sum;
    }

    private void helpme(TreeNode root, int curr) {
        if(root==null) return;
        curr = curr*10+root.val;
        if(root.left==null && root.right==null)
            sum+=curr;
        else {
            helpme(root.left,curr);
            helpme(root.right, curr);
        }

    }
}

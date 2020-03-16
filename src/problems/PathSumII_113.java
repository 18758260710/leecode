package problems;

import java.util.ArrayList;
import java.util.List;
import problems.BinaryTreeInorderTraversal_94.TreeNode;

public class PathSumII_113 {
    //mys solution1 1ms
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> temp = new ArrayList<>();
        helper(result, temp, root, sum);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> temp, TreeNode root, int sum) {
        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                temp.add(root.val);
                result.add(new ArrayList<>(temp));
                temp.remove(temp.size() - 1);
            }
            return;
        }
        temp.add(root.val);
        if (root.right != null) {
            helper(result, temp, root.right, sum - root.val);
        }
        if (root.left != null) {
            helper(result, temp, root.left, sum - root.val);
        }
        temp.remove(temp.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        new PathSumII_113().pathSum(root, 3);
    }
}

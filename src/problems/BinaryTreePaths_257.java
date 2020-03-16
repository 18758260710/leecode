package problems;

import problems.BinaryTreeInorderTraversal_94.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/2/11.
 */
public class BinaryTreePaths_257 {
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<String> left = binaryTreePaths(root.left);
        List<String> right = binaryTreePaths(root.right);
        left.addAll(right);

        if (left.isEmpty()) {
            left.add(String.valueOf(root.val));
            return left;
        } else {
            right.clear();
            for (String s : left) {
                right.add(root.val + "->" + s);
            }
            return right;
        }
    }

    //dfs
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (null == root) {
            return list;
        }

        String path = "";
        dfs(root, list, path);
        return list;
    }

    private void dfs(TreeNode node, List<String> list, String path) {
        if (path.equals("")) {
            path += node.val;
        } else {
            path += "->" + node.val;
        }
        if (node.left == null && node.right == null) {
            list.add(path);
            return; // 后边就不用判断了
        }

        if (node.left != null) {
            dfs(node.left, list, path);
        }

        if (node.right != null) {
            dfs(node.right, list, path);
        }
    }
}

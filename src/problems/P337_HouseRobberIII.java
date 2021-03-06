package problems;
import problems.P94_BinaryTreeInorderTraversal.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wengtao
 * @date 2021/2/27
 **/
public class P337_HouseRobberIII {
    Map<TreeNode, Integer> temp = new HashMap<>();
    //递归 slow 有重复计算 加temp寸 优化到3ms
    public int rob(TreeNode root) {
        if (root == null)return 0;
        if (temp.get(root)!=null)return temp.get(root);
        int sum = root.val + (root.left!=null?rob(root.left.left)+rob(root.left.right):0)+(root.right!=null?rob(root.right.left)+rob(root.right.right):0);
        int max = Math.max(sum, rob(root.left)+rob(root.right));
        temp.put(root, max);
        return max;
    }

    //用dfs 下层把自身选和不选的最大值通过数组传上来
    public int rob2(TreeNode root) {
        int[] ans = dfs(root);
        return Math.max(ans[0], ans[1]);
    }

    private int[] dfs(TreeNode node) {
        if (node == null) return new int[]{0, 0};
        int[] l = dfs(node.left);
        int[] r = dfs(node.right);
        int select = node.val + l[1] + r[1];
        int notSelect = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{select, notSelect};
    }
}

package problems;

import java.util.ArrayList;
import java.util.List;
import problems.P94_BinaryTreeInorderTraversal.TreeNode;

public class P103_BinaryTreeZigzagLevelOrderTraversal {
    //my solution1 1ms 因为要插头 所以可以用LinkedList优化
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, root, 0);
        return result;
    }

    public void helper(List<List<Integer>> result, TreeNode root, int level) {
        if (root != null) {
            if(result.size() <= level)
                result.add(new ArrayList<>());
            if (level%2==0)
                result.get(level).add(root.val);
            else result.get(level).add(0,root.val);
            helper(result, root.left, level + 1);
            helper(result, root.right, level + 1);
        }
    }
}

package problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import problems.P94_BinaryTreeInorderTraversal.TreeNode;

public class P107_BinaryTreeLevelOrderTraversalII {
    //my solution1 using stack 1ms 102题 第4种解法的思路
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return new ArrayList<>();

        LinkedList<List<Integer>> result = new LinkedList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        List<Integer> temp;
        int size = q.size();

        while(!q.isEmpty()){
            temp = new ArrayList<>();

            while(size > 0){
                TreeNode node = q.poll();
                size--;
                temp.add(node.val);

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            result.addFirst(temp);
            size = q.size();
        }
        return result;
    }

    //1ms 102题 第3种解法的思路
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, root, 0);
        return result;
    }
    public void helper(List<List<Integer>> result, TreeNode root, int level) {
        if (root != null) {
            if(result.size() <= level)
                result.add(0,new ArrayList<>());
            result.get(result.size() - 1 - level).add(root.val);
            helper(result, root.left, level + 1);
            helper(result, root.right, level + 1);
        }
    }
}

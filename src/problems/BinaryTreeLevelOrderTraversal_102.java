package problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import problems.BinaryTreeInorderTraversal_94.TreeNode;

public class BinaryTreeLevelOrderTraversal_102 {
    //my solution1 2ms
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root==null)return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        List<Integer> row = new ArrayList<>();
        while (queue.size()>1){
            TreeNode temp = queue.poll();
            if (temp!=null){
                row.add(temp.val);
                if (temp.left!=null)queue.offer(temp.left);
                if (temp.right!=null)queue.offer(temp.right);
            }else {
                result.add(new ArrayList<>(row));
                row.clear();
                queue.offer(null);
            }
        }
        result.add(new ArrayList<>(row));
        return result;
    }

    //my solution2 2ms
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root==null)return result;

        List<TreeNode> temp1 = new ArrayList<>();
        temp1.add(root);
        helper(temp1,result);

        return result;
    }

    private void helper(List<TreeNode> temp1, List<List<Integer>> result) {
        if (temp1.isEmpty())return;
        List<Integer> row = new ArrayList<>();
        List<TreeNode> temp2 = new ArrayList<>();
        for (TreeNode temp:temp1){
            row.add(temp.val);
            if (temp.left!=null)temp2.add(temp.left);
            if (temp.right!=null)temp2.add(temp.right);
        }
        result.add(row);
        helper(temp2,result);
    }

    //0ms
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, root, 0);
        return result;
    }
    public void helper(List<List<Integer>> result, TreeNode root, int level) {
        if (root != null) {
            if(result.size() <= level)
                result.add(new ArrayList<>());
            result.get(level).add(root.val);
            helper(result, root.left, level + 1);
            helper(result, root.right, level + 1);
        }
    }

    //1ms 额外计数，不用queen的size
    public List<List<Integer>> levelOrder4(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<List<Integer>> result = new ArrayList<>();

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
            result.add(temp);
            size = q.size();
        }
        return result;
    }
}

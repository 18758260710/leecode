package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import problems.P94_BinaryTreeInorderTraversal.TreeNode;

public class P144_BinaryTreePreorderTraversal {
    //my solution1 0ms
    List<Integer> result = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root!=null){
            result.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
        return result;
    }

    //my solution2 1ms
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root==null)return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode current = stack.pop();
            result.add(current.val);
            if (current.right!=null){
                stack.add(current.right);
            }
            if (current.left!=null){
                stack.add(current.left);
            }
        }
        return result;
    }
}

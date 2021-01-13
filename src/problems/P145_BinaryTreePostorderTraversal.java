package problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import problems.P94_BinaryTreeInorderTraversal.TreeNode;

public class P145_BinaryTreePostorderTraversal {
    //my solution1 0ms
    List<Integer> result = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root!=null){
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            result.add(root.val);
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
            if (current.left!=null){
                stack.add(current.left);
            }
            if (current.right!=null){
                stack.add(current.right);
            }
        }
        Collections.reverse(result);
        return result;
    }
}

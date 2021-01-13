package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import problems.P94_BinaryTreeInorderTraversal.TreeNode;

public class P199_BinaryTreeRightSideView {
    //my solution1 1ms
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root==null)return result;
        Queue<TreeNode> stack1 = new LinkedList<>();
        stack1.add(root);
        Queue<TreeNode> stack2 = new LinkedList<>();
        while (!stack1.isEmpty()||!stack2.isEmpty()){
            if (!stack1.isEmpty()){
                TreeNode right = stack1.poll();
                result.add(right.val);
                if (right.right!=null){
                    stack2.add(right.right);
                }
                if (right.left!=null){
                    stack2.add(right.left);
                }
            }else return result;
            while (!stack1.isEmpty()){
                TreeNode right = stack1.poll();
                if (right.right!=null){
                    stack2.add(right.right);
                }
                if (right.left!=null){
                    stack2.add(right.left);
                }
            }

            if (!stack2.isEmpty()){
                TreeNode right = stack2.poll();
                result.add(right.val);
                if (right.right!=null){
                    stack1.add(right.right);
                }
                if (right.left!=null){
                    stack1.add(right.left);
                }
            }else return result;
            while (!stack2.isEmpty()){
                TreeNode right = stack2.poll();
                if (right.right!=null){
                    stack1.add(right.right);
                }
                if (right.left!=null){
                    stack1.add(right.left);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(2);
        TreeNode a3 = new TreeNode(3);
        TreeNode a4 = new TreeNode(4);
        TreeNode a5 = new TreeNode(5);
        a1.left=a2;
        a1.right=a3;
        a2.right=a5;
        a3.right=a4;
        System.out.println(new P199_BinaryTreeRightSideView().rightSideView(a1));
    }

    //official solution1 用map存层数 DFS 2ms
    public List<Integer> rightSideView2(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<>();
        int max_depth = -1;

        /* These two stacks are always synchronized, providing an implicit
         * association values with the same offset on each stack. */
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();
        nodeStack.push(root);
        depthStack.push(0);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();

            if (node != null) {
                max_depth = Math.max(max_depth, depth);

                /* The first node that we encounter at a particular depth contains
                 * the correct value. */
                if (!rightmostValueAtDepth.containsKey(depth)) {
                    rightmostValueAtDepth.put(depth, node.val);
                }

                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth+1);
                depthStack.push(depth+1);
            }
        }

        /* Construct the solution based on the values that we end up with at the
         * end. */
        List<Integer> rightView = new ArrayList<>();
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }

        return rightView;
    }

    //official solution2 用map存层数 BFS 2ms
    public List<Integer> rightSideView3(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<>();
        int max_depth = -1;

        /* These two Queues are always synchronized, providing an implicit
         * association values with the same offset on each Queue. */
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> depthQueue = new LinkedList<>();
        nodeQueue.add(root);
        depthQueue.add(0);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            int depth = depthQueue.remove();

            if (node != null) {
                max_depth = Math.max(max_depth, depth);

                /* The last node that we encounter at a particular depth contains
                 * the correct value, so the correct value is never overwritten. */
                rightmostValueAtDepth.put(depth, node.val);

                nodeQueue.add(node.left);
                nodeQueue.add(node.right);
                depthQueue.add(depth+1);
                depthQueue.add(depth+1);
            }
        }

        /* Construct the solution based on the values that we end up with at the
         * end. */
        List<Integer> rightView = new ArrayList<>();
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }

        return rightView;
    }

    //递归 1ms
    public List<Integer> rightSideView4(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result, 0);
        return result;
    }

    public void helper(TreeNode root, List<Integer> result, int currDepth){
        if(root == null) return;

        if(currDepth == result.size()){
            result.add(root.val);
        }

        helper(root.right, result, currDepth+1);
        helper(root.left, result, currDepth+1);
    }
}

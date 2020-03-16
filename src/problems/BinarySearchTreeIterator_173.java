package problems;

import java.util.Stack;
import problems.BinaryTreeInorderTraversal_94.TreeNode;

public class BinarySearchTreeIterator_173 {
    //my solution1 slow same as official solution1
//    List<Integer> list = new ArrayList<>();
//    int start=0;
//    public BinarySearchTreeIterator_173(TreeNode root) {
//        getData(root);
//    }
//
//    private void getData(TreeNode root) {
//        if (root==null)return;
//        getData(root.left);
//        list.add(root.val);
//        getData(root.right);
//    }
//
//    /** @return the next smallest number */
//    public int next() {
//        return list.get(start++);
//    }
//
//    /** @return whether we have a next smallest number */
//    public boolean hasNext() {
//        return start<list.size();
//    }


    //official solution2 用栈
    Stack<TreeNode> stack;

    public BinarySearchTreeIterator_173(TreeNode root) {

        // Stack for the recursion simulation
        this.stack = new Stack<TreeNode>();

        // Remember that the algorithm starts with a call to the helper function
        // with the root node as the input
        this._leftmostInorder(root);
    }

    private void _leftmostInorder(TreeNode root) {

        // For a given node, add all the elements in the leftmost branch of the tree
        // under it to the stack.
        while (root != null) {
            this.stack.push(root);
            root = root.left;
        }
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        // Node at the top of the stack is the next smallest element
        TreeNode topmostNode = this.stack.pop();

        // Need to maintain the invariant. If the node has a right child, call the
        // helper function for the right child
        if (topmostNode.right != null) {
            this._leftmostInorder(topmostNode.right);
        }

        return topmostNode.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return this.stack.size() > 0;
    }
}

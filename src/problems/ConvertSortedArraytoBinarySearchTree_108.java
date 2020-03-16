package problems;

import problems.BinaryTreeInorderTraversal_94.TreeNode;

public class ConvertSortedArraytoBinarySearchTree_108 {
    //my solution1 0ms
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums,0,nums.length-1);
    }

    private TreeNode helper(int[] nums, int left, int right) {
        if (left>right)return null;
        int mid = (left+right)/2;
        TreeNode result = new TreeNode(nums[mid]);
        result.left=helper(nums,left,mid-1);
        result.right=helper(nums,mid+1,right);
        return result;
    }
}

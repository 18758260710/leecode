package problems;

import java.util.ArrayList;
import java.util.List;
import problems.BinaryTreeInorderTraversal_94.TreeNode;

public class UniqueBinarySearchTreesII_95 {
    //my solution1 1ms
    public List<TreeNode> generateTrees(int n) {
        if (n==0)return new ArrayList<>();
        return explore(1, n);
    }

    private List<TreeNode> explore(int min, int max) {
        List<TreeNode> result = new ArrayList<>();
        if (min == max) {
            result.add(new TreeNode(max));
            return result;
        }
        if (min>max){
            result.add(null);
            return result;
        }
        for (int i = min; i <= max; i++) {
            List<TreeNode> lefts = explore(min,i-1);
            List<TreeNode> rights = explore(i+1,max);
            for (TreeNode left:lefts){
                for (TreeNode right:rights){
                    TreeNode temp = new TreeNode(i);
                    temp.left=left;
                    temp.right=right;
                    result.add(temp);
                }
            }
        }
        return result;
    }

    //my solution2 没有空间浪费 1ms better
    public List<TreeNode> generateTrees2(int n) {
        List<TreeNode> result =new ArrayList<>();
        if (n==0)return result;
        if (n==1){
            result.add(new TreeNode(1));
            return result;
        }
        List<TreeNode> temps = generateTrees2(n-1);
        for (TreeNode temp:temps){
            TreeNode current = new TreeNode(n);
            current.left=temp;
            result.add(current);

            int count = 0;
            while (true){
                TreeNode right = cloneTree(temp);
                current = right;
                int i=count;
                while (i>0){
                    right = right.right;
                    i--;
                }
                if (right.right==null){
                    right.right = new TreeNode(n);
                    result.add(current);
                    break;
                }
                TreeNode a = right.right;
                right.right = new TreeNode(n);
                right.right.left=a;

                result.add(current);
                count++;
            }

        }
        return result;
    }

    public TreeNode cloneTree(TreeNode root){
        TreeNode node;
        if(root==null) return null;
        node=new TreeNode(root.val);
        node.left=cloneTree(root.left);
        node.right=cloneTree(root.right);

        return node;
    }

    public static void main(String[] args) {
        for (int i=1;i<10;i++) {
            List<TreeNode> result = new UniqueBinarySearchTreesII_95().generateTrees2(i);
            System.out.println(result.size());
        }
    }
}

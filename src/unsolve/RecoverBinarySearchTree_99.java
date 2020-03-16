package unsolve;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class RecoverBinarySearchTree_99 {

    public static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //此题未作出 思维被树禁锢住了
    public void recoverTree(TreeNode root) {
        helper(root, Long.MIN_VALUE, Long.MAX_VALUE, true);
    }

    public TreeNode helper(TreeNode node, Long lower, Long upper, boolean left) {
        if (node == null) {
            return null;
        }

        int val = node.val;

        TreeNode rightError = helper(node.right, (long) val, upper, false);
        TreeNode leftError = helper(node.left, lower, (long) val, true);

        if (rightError != null && leftError != null) {
            if (rightError.val < upper && rightError.val > lower && leftError.val < upper && leftError.val > lower) {
                int[] vals = new int[]{val, rightError.val, leftError.val};
                Arrays.sort(vals);
                leftError.val = vals[0];
                node.val = vals[1];
                rightError.val = vals[2];
                return null;
            } else if (rightError.val < upper && rightError.val > lower) {
                return leftError;
            } else if (leftError.val < upper && leftError.val > lower) {
                return rightError;
            } else {
                if (left) {
                    return leftError;
                }
                return rightError;
            }
        } else if (rightError != null) {
            if (rightError.val < upper && rightError.val > lower && val < upper && val > lower) {
                node.val = rightError.val;
                rightError.val = val;
                return null;
            } else if (rightError.val < upper && rightError.val > lower) {
                return node;
            } else if (val < upper && val > lower) {
                return rightError;
            } else {
                if (left) {
                    return node;
                }
                return rightError;
            }
        } else if (leftError != null) {
            if (leftError.val < upper && leftError.val > lower && val < upper && val > lower) {
                node.val = leftError.val;
                leftError.val = val;
                return null;
            } else if (leftError.val < upper && leftError.val > lower) {
                return node;
            } else if (val < upper && val > lower) {
                return leftError;
            } else {
                if (left) {
                    return leftError;
                }
                return node;
            }
        }

        if (lower != null && val <= lower || upper != null && val >= upper) {
            return node;
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(2);
        TreeNode a3 = new TreeNode(3);
        TreeNode a4 = new TreeNode(4);
//        a3.right=a2;
//        a2.right=a1;
//        new RecoverBinarySearchTree_99().recoverTree(a3);

//        a1.left=a2;
//        a2.right=a3;
//        a2.left=a4;
//        new RecoverBinarySearchTree_99().recoverTree(a1);

        a2.left = a1;
        a1.left = a3;
        a1.right = a4;
        System.out.println(a1);
    }

    //本质上是一个有序数组有一对调换了 只要遍历就可以 如果发现只有一处错误 那么就是这一对调换，如果有两处错误，那么将第一处错误前面的那个和第二处错误后面的那个对调 3ms
    public void recoverTree2(TreeNode root) {
        TreeNode pre = null, first = null, second = null;
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            if (pre != null) {
                if (pre.val > temp.val) {
                    if (first == null) {
                        first = pre;
                    }
                    second = temp;
                }
            }
            pre = temp;
            if (temp.right != null) {
                temp = temp.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
        }

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    //和上面一样的思想 只是用递归来遍历
    private TreeNode prev;
    private TreeNode firstNode;
    private TreeNode lastNode;
    public void recoverTree3(TreeNode root) {
        inOrder(root);
        int temp = firstNode.val;
        firstNode.val = lastNode.val;
        lastNode.val = temp;
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        if (prev != null && prev.val > node.val) {
            if (firstNode == null) {
                firstNode = prev;
            }
            lastNode = node;
        }
        prev = node;
        inOrder(node.right);
    }
}

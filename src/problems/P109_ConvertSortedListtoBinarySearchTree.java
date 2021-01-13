package problems;

import java.util.ArrayList;
import java.util.List;
import problems.P2_AddTwoNumbers.ListNode;
import problems.P94_BinaryTreeInorderTraversal.TreeNode;

public class P109_ConvertSortedListtoBinarySearchTree {

    //my solution1 1ms
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode slow = head;
        ListNode previous = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            previous = slow;
            slow = slow.next;
        }
        TreeNode result = new TreeNode(slow.val);
        ListNode right = slow.next;
        previous.next = null;
        if (head != slow) {
            result.left = sortedListToBST(head);
        }
        result.right = sortedListToBST(right);
        return result;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(-10);
        ListNode b = new ListNode(-3);
        ListNode c = new ListNode(0);
        ListNode d = new ListNode(5);
        ListNode e = new ListNode(9);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        TreeNode cc = new P109_ConvertSortedListtoBinarySearchTree().sortedListToBST(a);
        System.out.println(cc);
    }


    private ListNode findMiddleElement(ListNode head) {

        // The pointer used to disconnect the left half from the mid node.
        ListNode prevPtr = null;
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        // Iterate until fastPr doesn't reach the end of the linked list.
        while (fastPtr != null && fastPtr.next != null) {
            prevPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        // Handling the case when slowPtr was equal to head.
        if (prevPtr != null) {
            prevPtr.next = null;
        }

        return slowPtr;
    }

    //official solution1 same as mine
    public TreeNode sortedListToBST2(ListNode head) {

        // If the head doesn't exist, then the linked list is empty
        if (head == null) {
            return null;
        }

        // Find the middle element for the list.
        ListNode mid = this.findMiddleElement(head);

        // The mid becomes the root of the BST.
        TreeNode node = new TreeNode(mid.val);

        // Base case when there is just one element in the linked list
        if (head == mid) {
            return node;
        }

        // Recursively form balanced BSTs using the left and right halves of the original list.
        node.left = sortedListToBST2(head);
        node.right = sortedListToBST2(mid.next);
        return node;
    }


    private List<Integer> values = new ArrayList<>();

    private void mapListToValues(ListNode head) {
        while (head != null) {
            this.values.add(head.val);
            head = head.next;
        }
    }

    private TreeNode convertListToBST(int left, int right) {
        // Invalid case
        if (left > right) {
            return null;
        }

        // Middle element forms the root.
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(this.values.get(mid));

        // Base case for when there is only one element left in the array
        if (left == right) {
            return node;
        }

        // Recursively form BST on the two halves
        node.left = convertListToBST(left, mid - 1);
        node.right = convertListToBST(mid + 1, right);
        return node;
    }

    //official solution2  先转成list然后和108一样
    public TreeNode sortedListToBST3(ListNode head) {

        // Form an array out of the given linked list and then
        // use the array to form the BST.
        this.mapListToValues(head);

        // Convert the array to
        return convertListToBST(0, this.values.size() - 1);
    }


    //official solution3 先取出总长度再遍历
    private ListNode head;

    private int findSize(ListNode head) {
        ListNode ptr = head;
        int c = 0;
        while (ptr != null) {
            ptr = ptr.next;
            c += 1;
        }
        return c;
    }

    private TreeNode convertListToBST3(int l, int r) {
        // Invalid case
        if (l > r) {
            return null;
        }

        int mid = (l + r) / 2;

        // First step of simulated inorder traversal. Recursively form
        // the left half
        TreeNode left = this.convertListToBST3(l, mid - 1);

        // Once left half is traversed, process the current node
        TreeNode node = new TreeNode(this.head.val);
        node.left = left;

        // Maintain the invariance mentioned in the algorithm
        this.head = this.head.next;

        // Recurse on the right hand side and form BST out of them
        node.right = this.convertListToBST3(mid + 1, r);
        return node;
    }

    public TreeNode sortedListToBST4(ListNode head) {
        // Get the size of the linked list first
        int size = this.findSize(head);

        this.head = head;

        // Form the BST now that we know the size
        return convertListToBST3(0, size - 1);
    }
}

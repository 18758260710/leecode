package problems;

import problems.P2_AddTwoNumbers.ListNode;

import java.util.Stack;

/**
 * Created by Administrator on 2020/2/7.
 */
public class P234_PalindromeLinkedList {
    //stack
    public boolean isPalindrome(ListNode head) {
        ListNode temp = head;
        Stack<Integer> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
        }
        while (!stack.isEmpty()) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //先找到中点 然后右半部分反转 比较 这样只比较一办 方法一比较全部没必要
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode mid = middle(dummyNode);
        ListNode left = dummyNode.next;
        ListNode right = mid.next;
        mid.next = null;

        right = reverse(right);
        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;

        }
        return true;


    }

    public ListNode middle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode node) {

        ListNode pre = null;
        ListNode next = null;
        ListNode cur = node;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return pre;

    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(1);
        ListNode c = new ListNode(1);
        ListNode d = new ListNode(1);
        ListNode e = new ListNode(1);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        new P234_PalindromeLinkedList().isPalindrome2(a);
    }
}

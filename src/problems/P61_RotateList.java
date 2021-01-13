package problems;

import problems.P2_AddTwoNumbers.ListNode;

public class P61_RotateList {
    //my solution1 0ms
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        if (head.next == null || k == 0) {
            return head;
        }
        ListNode temp = head;
        int length = 1;
        while (temp.next != null) {
            temp = temp.next;
            length++;
        }
        temp.next = head;
        k = length-k%length;
        while (k > 1) {
            head = head.next;
            k--;
        }
        ListNode result = head.next;
        head.next = null;

        return result;
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        a1.next = a2;
        System.out.println(new P61_RotateList().rotateRight(a1, 3));
    }

    //less space
    public ListNode rotateRight2(ListNode head, int k) {
        //System.out.println(head.val);
        if (head == null) {
            return head;
        } else if (head.next == null) {
            return head;
        }

        int length = 1;
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
            length++;
        }

        k = k % length;

        if (k == 0) {
            return head;
        }

        ListNode leader = head;
        for (int i = 0; i < k; i++) {
            leader = leader.next;
        }

        ListNode follower = head;
        while (leader.next != null) {
            leader = leader.next;
            follower = follower.next;
        }

        leader.next = head;
        head = follower.next;
        follower.next = null;

        return head;
    }
}

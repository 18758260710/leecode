package problems;

import problems.P2_AddTwoNumbers.ListNode;

public class P19_RemoveNthNodeFromEndofList {
    //my solution
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        if (n == 0) {
            return head;
        }
        ListNode tempHead = head;
        int length = 1;
        while (tempHead.next != null) {
            length++;
            tempHead = tempHead.next;
        }
        if (length < n) {
            return head;
        }
        if (length==n)return head.next;
        if (length==1)return null;
        tempHead = head;
        int m = length - n;
        while (m > 1) {
            tempHead = tempHead.next;
            m--;
        }
        tempHead.next = tempHead.next.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode temp = head;
        head.next = new ListNode(2);
        head = head.next;
        head.next = new ListNode(3);
        head = head.next;
        head.next = new ListNode(4);
        head = head.next;
        head.next = new ListNode(5);

        System.out.println(new P19_RemoveNthNodeFromEndofList().removeNthFromEnd(temp, 2));
    }

    //official solution
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length  = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }

    //official solution2
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}

package problems;

import problems.P2_AddTwoNumbers.ListNode;

public class P24_SwapNodesinPairs {

    //my solution
    public static ListNode swapPairs(ListNode head) {
        if (head==null)return null;
        if (head.next==null)return head;
        ListNode end = head.next;
        head.next = end.next;
        end.next = head;
        head.next = swapPairs(head.next);
        return end;
    }

    //by whoji
    //same as mine
    public ListNode swapPairs2(ListNode head) {
        if ((head == null)||(head.next == null))
            return head;
        ListNode n = head.next;
        head.next = swapPairs2(head.next.next);
        n.next = head;
        return n;
    }

}

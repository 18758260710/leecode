package problems;

import problems.P2_AddTwoNumbers.ListNode;

public class P25_ReverseNodesink_Group {

    //my solution
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        for (int i=0;i<k;i++){
            if (temp==null)return head;
            temp = temp.next;
        }
        ListNode dummy = new ListNode(1);
        dummy.next = head;
        for (int i=1;i<k;i++){
            if (head.next==null)break;
            ListNode a = dummy.next;
            dummy.next = head.next;
            head.next = head.next.next;
            dummy.next.next = a;
        }
        head.next = reverseKGroup(head.next,k);
        return dummy.next;
    }

}

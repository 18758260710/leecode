package problems;

/**
 * Created by Administrator on 2020/3/12.
 */

import problems.AddTwoNumbers_2.ListNode;


public class MiddleoftheLinkedList_876 {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next!=null){
            slow=slow.next;
            if (fast.next.next!=null){
                fast=fast.next.next;
            }else break;
        }
        return slow;
    }
}

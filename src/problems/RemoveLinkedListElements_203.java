package problems;

import problems.AddTwoNumbers_2.ListNode;

/**
 * Created by Administrator on 2020/1/9.
 */
public class RemoveLinkedListElements_203 {
    //改值
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(1);
        dummy.next=head;
        ListNode slow = dummy;
        ListNode fast = head;
        while (fast!=null) {
            if (fast.val != val) {
                slow = slow.next;
                slow.val = fast.val;
            }
            fast = fast.next;
        }
        slow.next=null;
        if (slow==dummy)return null;
        return head;
    }

    //改结构
    public ListNode removeElements2(ListNode head, int val) {
        ListNode dummy = new ListNode(1);
        dummy.next=head;
        ListNode result = dummy;
        while (dummy.next!=null) {
            if (dummy.next.val==val){
                dummy.next=dummy.next.next;
            }else {
                dummy = dummy.next;
            }
        }
        return result.next;
    }
}

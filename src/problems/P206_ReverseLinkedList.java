package problems;
import problems.P2_AddTwoNumbers.ListNode;
/**
 * Created by Administrator on 2020/1/10.
 */
public class P206_ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head==null)return null;
        ListNode dummy = new ListNode(1);
        dummy.next=head;
        while (head.next!=null){
            ListNode next = head.next;
            head.next=next.next;
            next.next = dummy.next;
            dummy.next = next;
        }
        return dummy.next;
    }
}

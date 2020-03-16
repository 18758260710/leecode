package problems;
import problems.AddTwoNumbers_2.ListNode;
/**
 * Created by Administrator on 2020/1/10.
 */
public class ReverseLinkedList_206 {
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

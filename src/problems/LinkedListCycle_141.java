package problems;

import java.util.HashSet;
import java.util.Set;
import problems.AddTwoNumbers_2.ListNode;

public class LinkedListCycle_141 {
    //my solution1 4ms slow
    public boolean hasCycle(ListNode head) {
        Set<ListNode> exist = new HashSet<>();
        while (head!=null){
            if (exist.contains(head))return true;
            exist.add(head);
            head = head.next;
        }
        return false;
    }

    //my solution2 快慢两点 0ms
    public boolean hasCycle2(ListNode head) {
        if (head==null)return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next!=null&&fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow==fast)return true;
        }
        return false;
    }
}

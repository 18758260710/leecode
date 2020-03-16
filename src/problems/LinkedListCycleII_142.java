package problems;

import java.util.HashSet;
import java.util.Set;
import problems.AddTwoNumbers_2.ListNode;

public class LinkedListCycleII_142 {
    //my solution1 6ms
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> exist = new HashSet<>();
        while (head!=null){
            if (exist.contains(head))return head;
            exist.add(head);
            head = head.next;
        }
        return null;
    }

    //0ms
    public ListNode detectCycle2(ListNode head) {
        if (head==null)return null;
        ListNode slow = head;
        ListNode fast = head;
        boolean cycle = false;
        while (fast.next!=null&&fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow==fast){
                cycle = true;
                break;
            }
        }
        if (!cycle)return null;
        //计算圈长度
        int count=1;
        slow = slow.next;
        fast = fast.next.next;
        while (slow!=fast){
            slow = slow.next;
            fast = fast.next.next;
            count++;
        }

        slow = head;
        fast = head;

        while (count>0){
            fast = fast.next;
            count--;
        }

        while (slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //更简洁
    public ListNode detectCycle3(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}

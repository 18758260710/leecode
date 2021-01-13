package problems;

import problems.P2_AddTwoNumbers.ListNode;

public class RemoveDuplicatesfromSortedList_83 {
    //my solution1 0ms
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        int preVar = head.val;
        ListNode result = head;
        ListNode temp = head;
        head=head.next;
        while (head!=null){
            if (head.val!=preVar){
                temp.next=head;
                temp=head;
                preVar=head.val;
            }else {
                temp.next=null;
            }
            head=head.next;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(3);
        ListNode a5 = new ListNode(4);
        ListNode a6 = new ListNode(4);
        ListNode a7 = new ListNode(5);
        a1.next=a2;
        a2.next=a3;
        a3.next=a4;
        a4.next=a5;
        a5.next=a6;
        a6.next=a7;
        System.out.println(new RemoveDuplicatesfromSortedList_83().deleteDuplicates(a1));
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        ListNode current = head.next;
        int num = head.val;
        while (current != null) {
            if (current.val == num) {
                pre.next = null;
            }else {
                pre.next = current;
                pre = pre.next;
                num = current.val;
            }
            current = current.next;
        }
        return head;
    }
}

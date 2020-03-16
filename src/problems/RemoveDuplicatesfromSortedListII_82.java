package problems;

import problems.AddTwoNumbers_2.ListNode;

public class RemoveDuplicatesfromSortedListII_82 {
    //my solution1 0ms 原list上动
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode result = null;
        ListNode temp = null;
        int currentVar = head.val;
        int count = 1;
        while (head.next != null) {
            if (head.next.val == currentVar) {
                count++;
                if (temp!=null)temp.next = head.next;
            } else {
                if (count == 1) {
                    if (result == null) {
                        result = head;
                    }else {
                        temp.next=head;
                    }
                    temp = head;
                }else {
                    if (temp!=null)temp.next = head.next;
                }
                currentVar = head.next.val;
                count = 1;
            }
            head=head.next;
        }

        if (count==1){
            if (result == null) {
                result = head;
            }else {
                temp.next=head;
            }
        }else {
            if (temp!=null)temp.next = null;
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
//        a6.next=a7;
        System.out.println(new RemoveDuplicatesfromSortedListII_82().deleteDuplicates(a1));
    }
    //递归
    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null) {
            return null;
        }
        if(head.next==null) {
            return head;
        }
        if(head.val != head.next.val) {
            head.next = deleteDuplicates2(head.next);
            return head;
        }
        ListNode save = head;
        while(head!=null) {
            if(head.val!=save.val) {
                break;
            }
            head = head.next;
        }
        return deleteDuplicates(head);
    }
}

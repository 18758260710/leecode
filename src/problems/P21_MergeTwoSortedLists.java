package problems;

import problems.P2_AddTwoNumbers.ListNode;

public class P21_MergeTwoSortedLists {

    //my solution
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null)return l2;
        if (l2==null)return l1;
        if (l1.val > l2.val) {
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }
        ListNode temp = l1;
        while (l2 != null) {
            if (l1.next == null) {
                l1.next = l2;
                break;
            } else if (l1.next.val <= l2.val) {
                l1 = l1.next;
            } else {
                ListNode l1next = l1.next;
                ListNode l2next = l2.next;
                l1.next = l2;
                l2.next = l1next;
                l2 = l2next;
            }
        }

        return temp;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        System.out.println(new P21_MergeTwoSortedLists().mergeTwoLists(l1, l2));
    }

    //by yangliguang
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else{
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }
}

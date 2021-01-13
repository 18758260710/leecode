package problems;

import problems.P2_AddTwoNumbers.ListNode;

public class P160_IntersectionofTwoLinkedLists {
    //my solution1 0ms
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        int count1 = 0;
        while (headA!=null){
            headA=headA.next;
            count1++;
        }
        int count2 = 0;
        while (headB!=null){
            headB=headB.next;
            count2++;
        }
        while (count1>count2){
            a = a.next;
            count1--;
        }
        while (count1<count2){
            b = b.next;
            count2--;
        }
        while (a!=b){
            a=a.next;
            b=b.next;
        }
        return a;
    }
}

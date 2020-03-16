package problems;

import problems.AddTwoNumbers_2.ListNode;

public class ReorderList_143 {
    //my solution1 1ms
    public void reorderList(ListNode head) {
        if (head==null||head.next==null)return;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next!=null&&fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode preNode = null;
        //当前节点指针
        ListNode curNode = slow.next;
        slow.next=null;
        //下一个节点指针

        while (curNode!=null){
            ListNode nextNode = curNode.next;//nextNode 指向下一个节点
            curNode.next=preNode;//将当前节点next域指向前一个节点
            preNode = curNode;//preNode 指针向后移动
            curNode = nextNode;
        }

        ListNode temp  = head;
        while (preNode!=null) {
            ListNode left = temp.next;
            ListNode right = preNode.next;
            temp.next=preNode;
            preNode.next=left;
            temp=left;
            preNode = right;
        }
    }
}

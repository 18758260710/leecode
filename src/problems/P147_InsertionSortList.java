package problems;

import problems.P2_AddTwoNumbers.ListNode;

public class P147_InsertionSortList {
    //my solution1 3ms
    public ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(1);
        dummy.next = head;
        while (head.next!=null){
            if (head.next.val < head.val){
                if (dummy.next.val > head.next.val){
                    ListNode current = head.next;
                    head.next = current.next;
                    current.next = dummy.next;
                    dummy.next = current;
                }else {
                    ListNode current = dummy.next;
                    while (current.next.val < head.next.val){
                        current = current.next;
                    }
                    ListNode next = head.next;
                    head.next = next.next;
                    next.next = current.next;
                    current.next = next;
                }
            }else {
                head = head.next;
            }
        }
        return dummy.next;
    }

    //2ms
    public ListNode insertionSortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode cur = head.next, pre = head, next = null;
        head.next = null;
        while (cur != null) {
            next = cur.next;
            if(pre.val <= cur.val) {
                insert(pre, cur);//insert check from pre instead of the head. to save time.
            } else {
                insert(dummyHead, cur);
            }
            pre = cur;
            cur = next;
        }
        return dummyHead.next;
    }

    private void insert(ListNode dummyHead, ListNode toInsert) {
        ListNode cur = dummyHead;
        while (cur.next != null) {
            if (cur.next.val >= toInsert.val) {
                toInsert.next = cur.next;
                cur.next = toInsert;
                return;
            }
            cur = cur.next;
        }
        cur.next = toInsert;  // the case to append this node.
        toInsert.next = null; // to remove the rest node from this new list.
    }
}

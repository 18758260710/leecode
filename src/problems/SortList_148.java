package problems;

import problems.AddTwoNumbers_2.ListNode;

public class SortList_148 {
    //my solution1 快排的思想 但是很慢
    public ListNode sortList(ListNode head) {
        if (head==null||head.next==null)return head;
        ListNode dummy1 = new ListNode(1);
        ListNode temp1 = dummy1;
        ListNode dummy2 = new ListNode(1);
        ListNode temp2 = dummy2;
        ListNode current = head;
        while (current.next!=null){
            if (current.next.val < head.val){
                temp1.next = current.next;
                temp1 = temp1.next;
            }else {
                temp2.next = current.next;
                temp2 = temp2.next;
            }
            current = current.next;
        }
        temp1.next=null;
        temp2.next=null;
        ListNode left = sortList(dummy1.next);
        ListNode right = sortList(dummy2.next);
        head.next = right;
        if (left==null)return head;
        current = left;
        while (current.next!=null){
            current = current.next;
        }
        current.next=head;
        return left;
    }

    //归并试试 3ms fast
    public ListNode sortList2(ListNode head) {
        if (head==null||head.next==null)return head;
        ListNode dummy = new ListNode(1);
        ListNode current = dummy;
        ListNode fast= head;
        ListNode slow= head;
        while (fast.next!=null&&fast.next.next!=null){
            fast = fast.next.next;
            slow=slow.next;
        }
        ListNode right = sortList2(slow.next);
        slow.next=null;
        ListNode left = sortList2(head);
        while (right!=null&&left!=null){
            if (right.val>left.val){
                current.next=left;
                left=left.next;
            }else {
                current.next=right;
                right=right.next;
            }
            current=current.next;
        }
        if (right!=null){
            current.next=right;
        }
        if (left!=null){
            current.next=left;
        }
        return dummy.next;
    }

    //快排 1ms 形式更好 记录下左右的头尾
    public ListNode sortList3(ListNode head) {
        ListNode dummy = new ListNode(-1);
        helper(dummy,head,null);
        return dummy.next;
    }

    private void helper(ListNode pre,ListNode head,ListNode last){
        if(head == last){
            pre.next = last;
        }
        else{
            ListNode ppre = pre;
            ListNode index = head;
            ListNode dummy = new ListNode(-1);
            ListNode plast = index;
            dummy.next = index;
            //head是所有需要排序的节点
            //ppre是所有<index.val的节点链接的终点
            //dummy是所有==index.val的节点链接的起点的假头部，为了处理方便而设
            //plast是所有>index.val的节点链接的终点
            while(head != last){
                if(head.val < index.val){
                    ppre.next = head;
                    head = head.next;
                    ppre = ppre.next;
                }
                else
                if(head.val == index.val){
                    ListNode temp = head.next;
                    head.next = dummy.next;
                    dummy.next = head;
                    head = temp;
                }
                else{
                    plast.next = head;
                    plast = plast.next;
                    head = head.next;
                }
            }
            ppre.next = dummy.next;
            plast.next = last;
            helper(pre,pre.next,dummy.next);
            helper(index,index.next,last);
        }
    }
}

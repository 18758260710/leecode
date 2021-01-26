package problems;

import problems.P2_AddTwoNumbers.ListNode;

/**
 * @author wengtao
 * @date 2021/1/19
 **/
public class P238_OddEvenLinkedList {
    //my solution 0ms
    public ListNode oddEvenList(ListNode head) {
        ListNode oddDummy = new ListNode(1);
        ListNode evenDummy = new ListNode(1);
        ListNode oddHead = oddDummy;
        ListNode evenHead = evenDummy;
        if (head == null) {
            return head;
        }
        while (head!=null){
            oddHead.next=head;
            oddHead=oddHead.next;
            if (head.next!=null){
                evenHead.next = head.next;
                evenHead = evenHead.next;
            }else {
                break;
            }
            head = head.next.next;
        }
        evenHead.next = null;
        oddHead.next = evenDummy.next;
        return oddDummy.next;
    }

    //更简洁
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node6;
        ListNode head = new P238_OddEvenLinkedList().oddEvenList2(node1);
        System.out.println(node1);
    }

}

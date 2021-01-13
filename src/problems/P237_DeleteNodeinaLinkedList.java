package problems;

import problems.P2_AddTwoNumbers.ListNode;

/**
 * Created by Administrator on 2020/2/7.
 */
public class P237_DeleteNodeinaLinkedList {
    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }
}

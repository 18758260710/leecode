package problems;

import problems.AddTwoNumbers_2.ListNode;

/**
 * Created by Administrator on 2020/2/7.
 */
public class DeleteNodeinaLinkedList_237 {
    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }
}

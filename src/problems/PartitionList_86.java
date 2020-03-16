package problems;

import problems.AddTwoNumbers_2.ListNode;

public class PartitionList_86 {

    //my solution1
    public ListNode partition(ListNode head, int x) {
        ListNode left = null;
        ListNode right = null;
        ListNode leftHead = null;
        ListNode rightHead = null;

        while (head != null) {
            if (head.val < x) {
                if (leftHead == null) {
                    leftHead = head;
                    left = head;
                } else {
                    left.next = head;
                    left = head;
                }
            } else {
                if (rightHead == null) {
                    rightHead = head;
                    right = head;
                } else {
                    right.next = head;
                    right = head;
                }
            }
            head = head.next;
        }
        if (leftHead == null) {
            return rightHead;
        }
        if (rightHead == null) {
            return leftHead;
        }
        left.next = rightHead;
        right.next = null;
        return leftHead;
    }

    //official solution same as mine
    public ListNode partition2(ListNode head, int x) {

        // before and after are the two pointers used to create the two list
        // before_head and after_head are used to save the heads of the two lists.
        // All of these are initialized with the dummy nodes created.
        ListNode before_head = new ListNode(0);
        ListNode before = before_head;
        ListNode after_head = new ListNode(0);
        ListNode after = after_head;

        while (head != null) {

            // If the original list node is lesser than the given x,
            // assign it to the before list.
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                // If the original list node is greater or equal to the given x,
                // assign it to the after list.
                after.next = head;
                after = after.next;
            }

            // move ahead in the original list
            head = head.next;
        }

        // Last node of "after" list would also be ending node of the reformed list
        after.next = null;

        // Once all the nodes are correctly assigned to the two lists,
        // combine them to form a single list which would be returned.
        before.next = after_head.next;

        return before_head.next;
    }
}

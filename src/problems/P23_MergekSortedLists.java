package problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import problems.P2_AddTwoNumbers.ListNode;

public class P23_MergekSortedLists {

    //my solution slow
    //same as official solution2 Compare one by one
    public ListNode mergeKLists(ListNode[] lists) {

        ListNode start = new ListNode(0);
        ListNode result = start;
        List<ListNode> listsArray = new ArrayList<>();
        for (ListNode list : lists) {
            if (list != null) {
                listsArray.add(list);
            }
        }
        while (listsArray.size() > 0) {
            ListNode next = null;
            for (ListNode listNode : listsArray) {
                if (next == null || listNode.val < next.val) {
                    next = listNode;
                }
            }
            listsArray.remove(next);
            start.next = next;
            start = next;

            if (next.next != null) {
                listsArray.add(next.next);
            }
        }
        return result.next;
    }

    //official solution1  faster than mine
    public ListNode mergeKLists2(ListNode[] lists) {
        List<Integer> nums = new ArrayList<>();
        for (ListNode list : lists) {
            while (list != null) {
                nums.add(list.val);
                list = list.next;
            }
        }
        Collections.sort(nums);
        ListNode head = new ListNode(0);
        ListNode result = head;
        for (int a : nums) {
            head.next = new ListNode(a);
            head = head.next;
        }
        return result.next;
    }

    //official solution2
    //Merge lists one by one
    public ListNode mergeKLists3(ListNode[] lists) {
        P21_MergeTwoSortedLists p_21MergeTwoSortedLists = new P21_MergeTwoSortedLists();
        ListNode result = lists[0];
        for (int i = 1; i < lists.length; i++) {
            result = p_21MergeTwoSortedLists.mergeTwoLists(result, lists[i]);
        }
        return result;
    }

    //official solution3
    //Merge with Divide And Conquer
    public ListNode mergeKLists4(ListNode[] lists) {
        P21_MergeTwoSortedLists p_21MergeTwoSortedLists = new P21_MergeTwoSortedLists();

        int amount = lists.length;
        int interval = 1;
        while (interval < amount) {
            for (int i = 0; i < amount - interval; i += interval * 2) {
                lists[i] = p_21MergeTwoSortedLists.mergeTwoLists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        if (amount > 0) {
            return lists[0];
        }
        return null;
    }

    //official solution4
    //PriorityQueue
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists==null||lists.size()==0) return null;

        PriorityQueue<ListNode> queue= new PriorityQueue<>(lists.size(), (o1, o2) -> {
            if (o1.val<o2.val)
                return -1;
            else if (o1.val==o2.val)
                return 0;
            else
                return 1;
        });

        ListNode dummy = new ListNode(0);
        ListNode tail=dummy;

        for (ListNode node:lists)
            if (node!=null)
                queue.add(node);

        while (!queue.isEmpty()){
            tail.next=queue.poll();
            tail=tail.next;

            if (tail.next!=null)
                queue.add(tail.next);
        }
        return dummy.next;
    }
}

package problems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import problems.MergeTwoSortedLists_21.ListNode;

public class MergekSortedLists_23 {

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
        MergeTwoSortedLists_21 mergeTwoSortedLists_21 = new MergeTwoSortedLists_21();
        ListNode result = lists[0];
        for (int i = 1; i < lists.length; i++) {
            result = mergeTwoSortedLists_21.mergeTwoLists(result, lists[i]);
        }
        return result;
    }

    //official solution3
    //Merge with Divide And Conquer
    public ListNode mergeKLists4(ListNode[] lists) {
        MergeTwoSortedLists_21 mergeTwoSortedLists_21 = new MergeTwoSortedLists_21();

        int amount = lists.length;
        int interval = 1;
        while (interval < amount) {
            for (int i = 0; i < amount - interval; i += interval * 2) {
                lists[i] = mergeTwoSortedLists_21.mergeTwoLists(lists[i], lists[i + interval]);
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

        PriorityQueue<ListNode> queue= new PriorityQueue<>(lists.size(),new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1,ListNode o2){
                if (o1.val<o2.val)
                    return -1;
                else if (o1.val==o2.val)
                    return 0;
                else
                    return 1;
            }
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

    public static void main(String[] args) {

        Collection a =new ArrayList();
        a.add(16);
        a.add(15);
        a.add(14);
        a.add(3);
        a.add(2);
        a.add(1);
        PriorityQueue<Integer> b =new PriorityQueue<>(a);
        b.add(10);
        b.add(-3);
        b.add(30);
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode l3 = new ListNode(1);
        l3.next = new ListNode(3);
        l3.next.next = new ListNode(7);

        System.out.println(new MergekSortedLists_23().mergeKLists4(new ListNode[]{l1, l2, l3}));
    }

    static class PriorityQueue2<E> extends PriorityQueue<E>{

    }
}

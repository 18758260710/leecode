package problems;

import java.util.*;

/**
 * Created by Administrator on 2020/1/17.
 */
public class TheSkylineProblem_218 {
    //10ms
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        if (buildings.length==0)return result;
        Node head = new Node(0, Integer.MAX_VALUE, 0);
        Node dummy = new Node(0, Integer.MAX_VALUE, 0);
        dummy.next=head;
        head.previous=dummy;
        for (int[] building : buildings) {
            Node start = dummy.next;
            while (start != null && start.right <= building[0]) {
                start = start.next;
            }
            while (start != null && start.left < building[1]) {
                if (start.height < building[2]) {
                    if (start.right <= building[1] && start.left >= building[0]) {
                        start.height = building[2];
                    } else if (start.left < building[0] && start.right > building[1]) {
                        Node temp = new Node(building[0], building[1], building[2]);
                        Node left = new Node(start.left, building[0], start.height);
                        start.left = building[1];
                        if (start.previous!=null)start.previous.next = left;
                        left.previous = start.previous;
                        left.next = temp;
                        temp.previous = left;
                        temp.next = start;
                        start.previous = temp;
                    } else if (start.left < building[0]) {
                        Node left = new Node(start.left, building[0], start.height);
                        start.left = building[0];
                        if (start.previous!=null)start.previous.next = left;
                        left.previous = start.previous;
                        left.next = start;
                        start.previous = left;
                        start.height = building[2];
                    } else {
                        Node left = new Node(start.left, building[1], building[2]);
                        start.left = building[1];
                        if (start.previous!=null)start.previous.next = left;
                        left.previous = start.previous;
                        left.next = start;
                        start.previous = left;
                    }
                }
                start = start.next;
            }
        }
        head = dummy.next;
        while (head.next!=null){
            if (head.height==head.next.height){
                head.next.left=head.left;
                head.previous.next = head.next;
                head.next.previous = head.previous;
            }
            head=head.next;
        }
        head = dummy.next;
        while (head!=null){
            List<Integer> list = new ArrayList<>();
            list.add(head.left);
            list.add(head.height);
            result.add(list);
            head=head.next;
        }
        if (result.get(0).get(1)==0)result.remove(0);
        if (result.get(result.size()-1).get(1)!=0){
            List<Integer> list = new ArrayList<>();
            list.add(2147483647);
            list.add(0);
            result.add(list);
        }
        return result;
    }

    class Node {
        public Node(int left, int right, int height) {
            this.left = left;
            this.right = right;
            this.height = height;
        }

        int left;
        int right;
        int height;
        Node previous;
        Node next;
    }

    public static void main(String[] args) {
//        new TheSkylineProblem_218().getSkyline(new int[][]{{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}});
//        new TheSkylineProblem_218().getSkyline(new int[][]{{0,1,3}});
        new TheSkylineProblem_218().getSkyline(new int[][]{{0,2147483647,2147483647}});
    }


    //按高度降序 降低了复杂度
    static class Building {
        int loc;
        int height;
        int type;

        public Building(int loc, int height, int type) {
            this.loc = loc;
            this.height = height;
            this.type = type;
        }
    }

    public List<List<Integer>> getSkyline2(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        //默认输入已经按照起点排序
        //按照高度降序，同高度根据起点升序
        PriorityQueue<int[]> heightHeap = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[2] == b[2] ? a[0] - b[0] : b[2] - a[2];
            }
        });

        //默认起点，PRE保存前面能看见的最高建筑和他的终点起点
        int[] pre = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
        for (int[] b : buildings) {
            // 当出现断点情况，需要清空之前建筑群
            while (!heightHeap.isEmpty() && b[0] > pre[1]) {
                //获取之前最高建筑
                int[] curHighest = heightHeap.poll();
                //如果最高的终点在PRE之前，说明已经处理
                if (curHighest[1] <= pre[1]) continue;
                //如果遇到PRE之后的点，加入结果并更新PRE
                res.add(Arrays.asList(pre[1], curHighest[2]));
                pre = curHighest;
            }

            //当前建筑比之前建筑高
            if (b[2] > pre[2]) {
                if (b[0] == pre[0]) {
                    //同起点情况下，矮建筑必然被挡住，直接删除
                    res.remove(res.size() - 1);
                }
                res.add(Arrays.asList(b[0], b[2])); //未被之后遮挡前先加入结果
                if (b[1] < pre[1]) {
                    heightHeap.offer(pre); //如果终点小于前终点，将前值入堆
                }
                pre = b;//更新前值因为发现了更高的
            } else if (b[2] == pre[2]) { //同高度继续延伸END
                pre[1] = b[1];
            } else if (b[1] > pre[1]) {
                heightHeap.offer(b); //矮建筑直接入堆
            }
        }


        while (!heightHeap.isEmpty()) {
            //如果堆不为空，重复之前操作
            int[] cur = heightHeap.poll();
            if (cur[1] <= pre[1]) continue;
            res.add(Arrays.asList(pre[1], cur[2]));
            pre = cur;
        }
        //最后有剩余
        if (pre[2] > 0) {
            res.add(Arrays.asList(pre[1], 0));
        }
        return res;
    }
}

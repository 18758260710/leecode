package problems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author wengtao
 * @date 2020/11/25
 **/
public class P855_ExamRoom {
//    int size;
//    TreeSet<Integer> room;
//    public ExamRoom_855(int N) {
//        size = N-1;
//        room = new TreeSet<>();
//    }
//
//
//    //此操作O（N）比较慢  49ms
//    public int seat() {
//        int index = 0;
//        if (room.size() > 0){
//            // 第一个元素到0的距离就是其本身，默认其为最大距离
//            int dist = room.first();
//            Integer prev = null;
//            // 遍历考场中的全部座位
//            for (Integer seat: room){
//                if (prev != null){
//                    // 获取当前位置和上一个位置的距离
//                    int d = (seat - prev) >> 1;
//                    // 替换最大距离和对应的位置下标
//                    if (d > dist){
//                        dist = d;
//                        index = (seat + prev) >> 1;
//                    }
//                }
//                // 前一个位置后移到当前位置
//                prev = seat;
//            }
//            // 最后一个位置的判断
//            if (size - room.last() > dist){
//                index = size;
//            }
//        }
//        room.add(index);
//        return index;
//    }
//
//    public void leave(int p) {
//        room.remove(p);
//    }
//
//
    class Range {
        int start, end, length;
        public Range(int s, int e) {
            start = s;
            end = e;
            if (start == 0 || end == seats - 1) {
                this.length = end - start;
            } else {
                this.length = (end - start) / 2;
            }
        }
    }

    private PriorityQueue<Range> queue = new PriorityQueue<>((a, b) -> a.length != b.length ? b.length - a.length : a.start - b.start);
    private int seats = 0;

    public P855_ExamRoom(int N) {
        seats = N;
        queue.offer(new Range(0, N - 1));
    }

    public int seat() {
        Range in = queue.poll();
        int result;
        if (in.start == 0) {
            result = 0;
        } else if (in.end == seats - 1) {
            result = seats - 1;
        } else {
            result = in.start + in.length;
        }

        if (result > in.start) {
            queue.offer(new Range(in.start, result - 1));
        }
        if (in.end > result) {
            queue.offer(new Range(result + 1, in.end));
        }
        return result;
    }

    //leave操作O（n）
    public void leave(int p) {
        List<Range> list = new ArrayList(queue);
        Range prev = null;
        Range next = null;
        for (Range in: list) {
            if (in.end + 1 == p) {
                prev = in;
            }
            if (in.start - 1 == p) {
                next = in;
            }
        }
        queue.remove(prev);
        queue.remove(next);
        queue.offer(new Range(prev == null ? p : prev.start, next == null ? p : next.end));
    }
}

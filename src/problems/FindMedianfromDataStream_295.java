package problems;

import java.util.PriorityQueue;

/**
 * @author wengtao
 * @date 2020/3/24
 **/
public class FindMedianfromDataStream_295 {
    public FindMedianfromDataStream_295() {

    }

    PriorityQueue<Integer> minHeap=new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap=new PriorityQueue<>((a, b) -> b-a);;

    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }

    }

    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        FindMedianfromDataStream_295 heap = new FindMedianfromDataStream_295();
        heap.addNum(2);
        System.out.println(heap.findMedian());
        heap.addNum(27);
        System.out.println(heap.findMedian());
        heap.addNum(20);
        System.out.println(heap.findMedian());
        heap.addNum(17);
        System.out.println(heap.findMedian());
    }
}

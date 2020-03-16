package problems;

import java.util.ArrayDeque;

/**
 * Created by Administrator on 2020/2/10.
 */
public class SlidingWindowMaximum_239 {
    //双端队列
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        int [] output = new int[n - k + 1];

        ArrayDeque<Integer> deq = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            help(nums, k, deq, i);
        }
        output[0] = nums[deq.getFirst()];
        for (int i  = k; i < n; i++) {
            help(nums, k, deq, i);
            output[i - k + 1] = nums[deq.getFirst()];
        }

        return output;
    }

    private void help(int[] nums, int k, ArrayDeque<Integer> deq, int i) {
        if (!deq.isEmpty() && deq.getFirst() == i - k)
            deq.removeFirst();

        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()])//之前觉得这里有循环不是O（n）的，后面发现一共循环的次数不会超过n
            deq.removeLast();

        deq.addLast(i);
    }

    public static void main(String[] args) {
        System.out.println(new SlidingWindowMaximum_239().maxSlidingWindow(new int[]{3,1,2,5,3},3));
    }

    //dp思想 按k均分 left数组存当前组左到i最大值 right数组存当前组右到i最大值 对于任意k 都可以分为左右两段，当前组的后半段和后组的前半段 最后取左半部分最大值和右半部分最大值较大的就是结果
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        int [] left = new int[n];
        left[0] = nums[0];
        int [] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0) left[i] = nums[i];  // block_start
            else left[i] = Math.max(left[i - 1], nums[i]);

            // from right to left
            int j = n - i - 1;
            if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
            else right[j] = Math.max(right[j + 1], nums[j]);
        }

        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++)
            output[i] = Math.max(left[i + k - 1], right[i]);

        return output;
    }

}

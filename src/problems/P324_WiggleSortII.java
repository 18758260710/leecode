package problems;

import java.util.Arrays;

/**
 * @author wengtao
 * @date 2020/12/28
 **/
public class P324_WiggleSortII {
    //3ms 必须要从大到小排 从小到大会出现中间重复
    public void wiggleSort(int[] nums) {
        int[] clone = nums.clone();
        Arrays.sort(clone);
        int i = nums.length - 1;
        for (int j = 1; j < nums.length; j += 2) nums[j] = clone[i--];
        for (int j = 0; j < nums.length; j += 2) nums[j] = clone[i--];
    }

    public static void main(String[] args) {
        new P324_WiggleSortII().wiggleSort2(new int[]{5,1,5,7,5,8,3,5});
    }

    void swap(int[] nums, int p, int q) {
        int tmp = nums[p];
        nums[p] = nums[q];
        nums[q] = tmp;
    }
    //virtual mapping to new index
    //e.g. n = 8, [a, b, c, d, e, f, g, h] -> [a, e, b, f, c, g, d, h]
    //e.g. n = 9, [a, b, c, d, e, f, g, h, i] -> [a, f, b, g, c, h, d, i, e]
    int ni(int n, int i) {
        return i <= (n - 1) / 2 ? i * 2 : (i - (n + 1) / 2) * 2 + 1;
    }
    //three-way partition, O(n)-average time, O(1)-space, k starts from index 0
    int getKth(int[] nums, int k) {
        int n = nums.length, start = 0, end = n - 1;
        while (true) {
            //[start, p) < pivot, [p, q) == pivot, [q, i) > pivot
            int pivot = nums[ni(n, end)], p = start, q = p;
            for (int i = start; i < end; i++)
                if (nums[ni(n, i)] <= pivot) {
                    swap(nums, ni(n, q++), ni(n, i));
                    if (nums[ni(n, q - 1)] < pivot)
                        swap(nums, ni(n, p++), ni(n, q - 1));
                }
            swap(nums, ni(n, q++), ni(n, end));
            if (k < p - start)
                end = p - 1;
            else if (k < q - start)
                return pivot;
            else {
                k -= q - start;
                start = q;
            }
        }
    }
    //用快排思想找到中位数并将数组分为三部分
    //然后用虚拟映射避免额外空间
    public void wiggleSort2(int[] nums) {
        int n = nums.length, mid = (n - 1) / 2;
        getKth(nums, mid);
        //reverse index [0, 2, 4, 6, ...] 把前部分小的换到后面
        for (int p = 0, q = mid; p < q; p++, q--)
            swap(nums, ni(n, p), ni(n, q));
        //reverse index [1, 3, 5, 7, ...]  把后部分大的换到前面
        for (int p = mid + 1, q = n - 1; p < q; p++, q--)
            swap(nums, ni(n, p), ni(n, q));
    }
}

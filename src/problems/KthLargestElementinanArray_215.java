package problems;

import java.util.Arrays;

/**
 * Created by Administrator on 2020/1/14.
 */
public class KthLargestElementinanArray_215 {
    //系统快排 2ms
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    //堆排 1ms 后面的不用排  要是k大用小顶堆 k小用大顶堆
    public int findKthLargest2(int[] nums, int k) {

        buildHeap(nums,k);
        //如果当前元素大于小顶堆的顶元素
        for(int i=k;i<nums.length;i++){
            if(nums[i]>nums[0]){
                nums[0]=nums[i];
                downAdjust(nums,0,k);
            }
        }
        return nums[0];
    }
    public void buildHeap(int[] nums,int k){
        //从最后一个非叶子结点开始下沉元素，构建小顶堆
        for(int i=(k-1)/2;i>=0;i--){
            downAdjust(nums,i,k);
        }
    }
    public void downAdjust(int[] nums,int parent,int k){
        //拿到i位置的值，避免循环交换值
        int temp=nums[parent];
        int child=parent*2+1;
        //循环下沉到堆的最底部
        while(child<k){
            //找到最小的
            if(child+1<k && nums[child]>nums[child+1]){
                child++;
            }
            //此处需要注意：1.父节点小于子节点，则证明不用下沉 2.跳出后续父子节点索引修改的操作，避免最后赋值的一步产生出错
            if (temp < nums[child]) {
                break;
            }
            nums[parent] = nums[child];
            parent = child;
            child = parent * 2 + 1;
        }
        nums[parent] = temp;
    }

    //桶排序 太离散就不好用
    public int findKthLargest3(int[] nums, int k) {
        int max = Integer.MIN_VALUE,min = Integer.MAX_VALUE;
        for(int i : nums){
            max = Math.max(max,i);
            min = Math.min(min,i);
        }
        int n = max - min;
        int[] bucket = new int[n + 1];
        for(int i = 0;i < nums.length;i++){
            int tmp = nums[i] - min;
            bucket[tmp]++;
        }
        for(int i = n;i >= 0;i--){
            if(bucket[i] > 0)
                k -= bucket[i];
            if(k <= 0)
                return i + min;
        }
        return 0;
    }
}

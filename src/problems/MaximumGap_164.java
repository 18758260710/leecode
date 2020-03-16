package problems;

import java.util.Arrays;

public class MaximumGap_164 {
    //my solution 排序 nlog(n) 5ms
    public int maximumGap(int[] nums) {
        if (nums.length<2)return 0;
        Arrays.sort(nums);

        int result = 0;
        for (int i=0;i<nums.length-1;i++){
            result = Math.max(result,nums[i+1]-nums[i]);
        }
        return result;
    }

    //O(n) 桶排序 1ms
    public int maximumGap2(int[] nums) {
        int n = nums.length;
        if(n < 2) return 0;
        int min = nums[0];
        int max = nums[0];
        //找出最大最小值
        for(int i = 1;i < n;i++){
            if(min > nums[i]) min = nums[i];
            if(max < nums[i]) max = nums[i];
        }

        //以最大最小值之差 分成n个区间段 也就是n个桶
        int gap = (max-min)/(n-1);
        if(gap == 0) gap++;//如果最大最小之差小于总长度 区间长度为1
        int len = (max-min)/gap+1;
        int [] tmax = new int [len];//每个桶的最大值
        int [] tmin = new int [len];//每个桶的最小值

        for(int i = 0;i < n;i++){
            int index = (nums[i]-min)/gap;//找到桶
            if(nums[i] > tmax[index]) tmax[index] = nums[i];//改变桶的最大值
            if(tmin[index] == 0 || nums[i] < tmin[index]) tmin[index] = nums[i];//改变桶的最小值
        }
        //当前桶最小值与前一桶最大值之差 取最大的
        int myMax = 0;
        for(int i = 0;i < len;i++){
            if(myMax < tmin[i]-min) myMax = tmin[i]-min;
            if(tmax[i] != 0) min = tmax[i];
        }
        return myMax;
    }

    public static void main(String[] args) {
        new MaximumGap_164().maximumGap2(new int[]{3,6,9,1});
    }
}

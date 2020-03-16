package problems;

/**
 * Created by Administrator on 2020/3/4.
 */
public class LongestContinuousIncreasingSubsequence_674 {
    //1ms
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length==0)return 0;
        int max=1;
        int temp=1;
        for (int i=1;i<nums.length;i++){
            if (nums[i]<=nums[i-1]){
                max = Math.max(temp,max);
                temp=1;
            }else temp++;
        }
        max = Math.max(temp,max);
        return max;
    }
}

package problems;

import java.util.Arrays;

/**
 * Created by Administrator on 2020/3/4.
 */
public class P300_LongestIncreasingSubsequence {
    //dp
    public int lengthOfLIS(int[] nums) {
        if (nums.length==0)return 0;
        int result = 1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i=1; i<nums.length; i++) {
            dp[i]=1;
            for(int j=i-1; j>=0; j--){
                if(nums[i]>nums[j] && dp[i]<dp[j]+1){
                    dp[i]=dp[j]+1;
                }
            }
            result = Math.max(dp[i],result);
        }
        return result;
    }

    //dp + 二分
    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        new P300_LongestIncreasingSubsequence().lengthOfLIS2(new int[]{0,8,4,12,2,4});
    }
}

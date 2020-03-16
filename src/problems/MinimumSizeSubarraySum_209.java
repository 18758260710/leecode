package problems;

/**
 * Created by Administrator on 2020/1/13.
 */
public class MinimumSizeSubarraySum_209 {
    //solution1 3ms
    public int minSubArrayLen(int s, int[] nums) {
        int length = nums.length;
        if (length==0)return 0;
        int start=0;
        int end=0;
        int sum=nums[0];
        Integer result = Integer.MAX_VALUE;
        while (end<length&&start<length) {
            while (sum < s && end < length - 1) {
                end++;
                sum += nums[end];
            }
            if (sum < s && start == 0) return 0;
            if (sum>=s) {
                result = Math.min(result, end - start + 1);
                sum -= nums[start];
                start++;
            }else return result;
        }
        return result;
    }

    //1ms
    public int minSubArrayLen2(int s, int[] nums) {
        int sum = 0, minLenth=Integer.MAX_VALUE;
        int start=0;
        for (int end=0; end<nums.length;end++) {
            sum += nums[end];
            while (sum >= s) {
                minLenth = minLenth <= end-start+1 ? minLenth : end-start+1;
                sum -= nums[start++];
            }
        }
        return minLenth!=Integer.MAX_VALUE ? minLenth : 0;

    }
}

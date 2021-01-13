package problems;

/**
 * Created by Administrator on 2020/2/12.
 */
public class P268_MissingNumber {
    //求和 1ms
    public int missingNumber(int[] nums) {
        int length = nums.length;
        int sum = length*(length+1)/2;
        for (int num:nums){
            sum-=num;
        }
        return sum;
    }

    //异或 0ms
    public int missingNumber2(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; ++i){
            res ^= nums[i];
            res ^= i;
        }
        return res;
    }
}

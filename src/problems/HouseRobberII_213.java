package problems;

/**
 * Created by Administrator on 2020/1/14.
 */
public class HouseRobberII_213 {
    //和198一样 0ms
    public int rob(int[] nums) {
        if (nums.length<1)return 0;
        if (nums.length==1)return nums[0];
        if (nums.length==2)return Math.max(nums[0],nums[1]);
        if (nums.length==3)return Math.max(Math.max(nums[0],nums[1]),nums[2]);
        return Math.max(subrob1(nums),subrob2(nums));
    }
    public int subrob1(int[] nums) {
        int[] dp = new int[nums.length-1];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for (int i=2;i<nums.length-1;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[nums.length-2];
    }
    public int subrob2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[1] = nums[1];
        dp[2] = Math.max(nums[1],nums[2]);
        for (int i=3;i<nums.length;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[nums.length-1];
    }

    public static void main(String[] args) {
        System.out.println(new HouseRobberII_213().rob(new int[]{1,2,1,1}));
    }
}

package problems;

public class HouseRobber_198 {
    //my solution1 dp
    public int rob(int[] nums) {
        if (nums.length<1)return 0;
        if (nums.length==1)return nums[0];
        if (nums.length==2)return Math.max(nums[0],nums[1]);
        if (nums.length==3)return Math.max(nums[0]+nums[2],nums[1]);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        dp[2] = Math.max(nums[0]+nums[2],nums[1]);
        for (int i=3;i<nums.length;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
            dp[i] = Math.max(dp[i],dp[i-3]+nums[i]);
        }
        return dp[nums.length-1];
    }

    //3的可以省掉 由于只和前两个值有关 所以空间不用O(n)懒得写了
    public int rob2(int[] nums) {
        if (nums.length<1)return 0;
        if (nums.length==1)return nums[0];
        if (nums.length==2)return Math.max(nums[0],nums[1]);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for (int i=2;i<nums.length;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[nums.length-1];
    }



    public static void main(String[] args) {
        System.out.println(new HouseRobber_198().rob(new int[]{2,1,1,2}));
    }
}

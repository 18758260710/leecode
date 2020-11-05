package problems;

import java.util.Arrays;

/**
 * @author wengtao
 * @date 2020/4/8
 **/
public class BurstBalloons_312 {
    //dp 从上往下
    public int maxCoins(int[] nums) {
        int length = nums.length+2;
        int[] myNums = new int[length];
        myNums[0] = 1;
        myNums[length - 1] = 1;
        for (int i=1;i<length-1;i++){
            myNums[i]=nums[i-1];
        }

        int[][] dp = new int[length][length];

        return dp(dp, myNums, 0, length - 1);
    }

    private int dp(int[][] dp, int[] myNums, int left, int right) {
        if (left + 1 == right) {
            return 0;
        }

        if (dp[left][right] > 0) {
            return dp[left][right];
        }

        // left 和 right之间挑一个气球塞回去  这个气球的币为myNums[left] * myNums[i] * myNums[right]
        //塞回去一个之后  变为两个区间 left -> i   i->right 循环递归
        int ans = 0;
        for (int i = left + 1; i < right; ++i) {
            ans = Math.max(ans, myNums[left] * myNums[i] * myNums[right]
                    + dp(dp, myNums, left, i) + dp(dp, myNums, i, right));
        }

        dp[left][right] = ans;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new BurstBalloons_312().maxCoins(new int[]{3,1,5,8}));
    }

    //fast dp不递归 从下往上
    private int[][] dp;

    public void fill(int[] nums, int from, int to) {
        int max = 0, maxLeft, maxRight, result;
        //假设第i个气球是最后被戳破的
        for (int i = from; i <= to; i++) {
            maxLeft = dp[from][i - 1];
            maxRight = dp[i + 1][to];
            result = maxLeft + maxRight + nums[from - 1] * nums[i] * nums[to + 1];
            max = Math.max(result, max);
        }
        dp[from][to] = max;
    }

    public int maxCoins2(int[] nums) {
        int length = nums.length;
        dp = new int[length + 2][length + 2];
        for (int i = length + 1; i >= 0; i--) {
            Arrays.fill(dp[i], 0);
        }

        int[] expandNums = new int[length + 2];
        expandNums[0] = expandNums[length + 1] = 1;
        System.arraycopy(nums, 0, expandNums, 1, length);

        for (int span = 0; span < length; span++) {
            for (int from = length - span; from > 0; from--) {
                fill(expandNums, from, from + span);
            }
        }
        return dp[1][length];
    }
}

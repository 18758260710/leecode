package problems;

import java.util.Arrays;

public class UniqueBinarySearchTrees_96 {
    //my solution1 0ms
    int[][] dp;
    public int numTrees(int n) {
        if (n==0)return 0;
        dp = new int[n+2][n+2];
        for (int i=0;i<=n+1;i++){
            Arrays.fill(dp[i],1);
        }
        for (int i=n-1;i>0;i--){
            for (int j=i+1;j<=n;j++){
                dp[i][j]=0;
                for (int k = i; k <= j; k++) {
                    dp[i][j] += dp[i][k-1]*dp[k+1][j];
                }
            }
        }
        return dp[1][n];
    }


    public static void main(String[] args) {
        for (int i=1;i<10;i++) {
            int a = new UniqueBinarySearchTrees_96().numTrees(i);
            System.out.println(a);
        }
    }

    //better 一维数组 只和元素个数相关 所以 上面的 dp[1][3]=dp[2][4]
    public int numTrees2(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int[] dp = new int[n+1];
        dp[0] = 1; dp[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[i-j-1] * dp[j];
            }
        }
        return dp[n];
    }
}

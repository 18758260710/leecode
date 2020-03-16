package problems;

/**
 * Created by Administrator on 2020/2/13.
 */
public class PerfectSquares_279 {
    //动态规划遍历
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j*j <= i; j++){
                if(i >= j*j){
                    dp[i] = Math.min(dp[i],dp[i-j*j]+1);
                }
            }
        }
        return dp[n];
    }

    /**
     * 四平方和
     * 任何正整数都可以拆分成不超过4个数的平方和 ---> 答案只可能是1,2,3,4
     * 如果一个数最少可以拆成4个数的平方和，则这个数还满足 n = (4^a)*(8b+7) ---> 因此可以先看这个数是否满足上述公式，如果不满足，答案就是1,2,3了
     * 如果这个数本来就是某个数的平方，那么答案就是1，否则答案就只剩2,3了
     * 如果答案是2，即n=a^2+b^2，那么我们可以枚举a，来验证，如果验证通过则答案是2
     * 只能是3
     */

    public int numSquares2(int n) {
        while (n % 4 == 0) n /= 4;
        if (n % 8 == 7) return 4;
        int num = (int) Math.sqrt(n);
        if(n == num*num){
            return 1;
        }
        for (int i = 1; i * i < n; ++i) {
            int j = (int) Math.sqrt(n - i * i);
            if (i * i + j * j == n)
                return 2;
        }
        return 3;
    }
}

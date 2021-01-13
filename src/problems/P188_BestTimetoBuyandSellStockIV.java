package problems;

public class P188_BestTimetoBuyandSellStockIV {
    //my solution1 slow
    public int maxProfit(int k, int[] prices) {
        if (prices.length<2)return 0;
        k = Math.min(k,prices.length/2);
        int[] buys = new int[k+1];
        int[] sells = new int[k+1];
        for (int i=0;i<k+1;i++){
            buys[i]=Integer.MIN_VALUE;
        }
        for (int price : prices){
            for (int i=0;i<k;i++){
                if (buys[i+1] < sells[i] - price) buys[i+1] = sells[i] -price;
                if (sells[i+1] < buys[i+1] + price) sells[i+1] = buys[i+1] + price;
            }
        }
        return sells[k];
    }

    //my solution2 1ms  去掉了下解i的维度，因为i这一行只与i-1这行相关
    public int maxProfit2(int k, int[] prices) {
        if (prices.length<2)return 0;

        //如果次数比长度大，那么每个后比前大都加起来就行
        if(k >= prices.length/2){
            int maxPro = 0;
            for(int i = 1; i < prices.length; i ++){
                if(prices[i] > prices[i-1])
                    maxPro += (prices[i] - prices[i-1]);
            }
            return maxPro;
        }

        //动态规划
        int[] buys = new int[k+1];
        int[] sells = new int[k+1];
        for (int i=0;i<k+1;i++){
            buys[i]=Integer.MIN_VALUE;
        }
        for (int price : prices){
            for (int i=0;i<k;i++){
                if (buys[i+1] < sells[i] - price) buys[i+1] = sells[i] -price;
                if (sells[i+1] < buys[i+1] + price) sells[i+1] = buys[i+1] + price;
            }
        }
        return sells[k];
    }

    //未空间化简的dp  0 1 分别为上解的两个数组
    int maxProfit_k_any(int max_k, int[] prices) {
        int n = prices.length;
        if (max_k > n / 2) {
            int maxPro = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1])
                    maxPro += (prices[i] - prices[i - 1]);
            }
            return maxPro;
        }

        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++)
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                    /* 处理 base case */
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        return dp[n - 1][max_k][0];
    }


    public static void main(String[] args) {
        System.out.println(new P188_BestTimetoBuyandSellStockIV().maxProfit2(2,new int[]{2,1,2,0,1,3,5,1}));
    }
}

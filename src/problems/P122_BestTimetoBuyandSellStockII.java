package problems;

public class P122_BestTimetoBuyandSellStockII {
    public int maxProfit(int[] prices) {
        //my solution1 1ms
        if (prices==null||prices.length<=1)return 0;
        int result = 0;
        int buy = Integer.MAX_VALUE;

        for (int i=0;i<prices.length;i++){
            if (prices[i]<buy)buy=prices[i];
            else {
                result+=prices[i];
                result-=buy;
                buy=prices[i];
            }
        }
        return result;
    }

    //0ms
    public int maxProfit2(int[] prices) {
        int i=0,max=0,min=0,profit=0;
        while(i<prices.length-1){
            while(i<prices.length-1 && prices[i+1]<=prices[i]){
                i++;
            }
            min = prices[i];//先找极小值
            while(i<prices.length-1 && prices[i+1]>=prices[i]){
                i++;
            }
            max = prices[i];//再找极大值

            profit += max - min;
        }
        return profit;
    }
}

package problems;

public class P123_BestTimetoBuyandSellStockIII {
    //my solution1 1ms
    public int maxProfit(int[] prices) {
        if (prices==null||prices.length<=1)return 0;
        int[] leftProfit = new int[prices.length];
        int[] rightProfit = new int[prices.length];

        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
            leftProfit[i]=maxprofit;
        }

        int maxprice = 0;
        maxprofit = 0;
        for (int i = prices.length-1; i >=0; i--) {
            if (prices[i] > maxprice)
                maxprice = prices[i];
            else if (maxprice-prices[i] > maxprofit)
                maxprofit = maxprice-prices[i];
            rightProfit[i]=maxprofit;
        }

        maxprofit = Math.max(leftProfit[prices.length-1],rightProfit[0]);
        for (int i=1;i<prices.length-1;i++){
            maxprofit = Math.max(maxprofit,leftProfit[i]+rightProfit[i+1]);
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        System.out.println(new P123_BestTimetoBuyandSellStockIII().maxProfit2(new int[]{2,1,2,0,1}));
    }

    //0ms
    public int maxProfit2(int[] prices) {
        int buy1 = Integer.MIN_VALUE;
        int buy2 = Integer.MIN_VALUE;
        int sell1 = 0;
        int sell2 = 0;
        for (int price : prices){
            if (buy1 < -price) buy1 = -price;
            if (sell1 < buy1 + price) sell1 = buy1 + price;
            if (buy2 < sell1 - price) buy2 = sell1 - price;
            if (sell2 < buy2 + price) sell2 = buy2 +price;
        }
        return sell2;
    }
}

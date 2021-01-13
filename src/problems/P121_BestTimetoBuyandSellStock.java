package problems;

public class P121_BestTimetoBuyandSellStock {

    //my solution1 1ms
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int result = 0;
        int min = prices[0];
        int i = 1;
        while (i < prices.length) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                result = Math.max(result, prices[i] - min);
            }
            i++;
        }
        return result;
    }

    //official solution same as mine
    public int maxProfit2(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

    //同123
    public int maxProfit3(int prices[]) {
        int buy1 = Integer.MIN_VALUE;
        int sell1 = 0;
        for (int price : prices) {
            if (buy1 < -price) {
                buy1 = -price;
            }
            if (sell1 < buy1 + price) {
                sell1 = buy1 + price;
            }
        }
        return sell1;
    }
}

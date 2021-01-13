package problems;

/**
 * @author wengtao
 * @date 2020/4/3
 **/
public class P309_BestTimetoBuyandSellStockwithCooldown {
    //1ms
    public int maxProfit(int[] prices) {
        int length=prices.length;
        if (length<2) {
            return 0;
        }
        if (length==2){
            return prices[1]>prices[0]?prices[1]-prices[0]:0;
        }
        int[] sell = new int[length];
        int[] buy = new int[length];
        buy[0]=-prices[0];
        buy[1]=prices[1]>prices[0]?-prices[0]:-prices[1];
        sell[1] = prices[1]>prices[0]?prices[1]-prices[0]:0;
        for (int i=2;i<length;i++){
            sell[i] = Math.max(buy[i-1]+prices[i],sell[i-1]);
            buy[i] = Math.max(sell[i-2]-prices[i],buy[i-1]);
        }
        return sell[length-1];
    }

    //由于只和三个值相关 所以不用数组
    public int maxProfit2(int[] prices) {
        int dp_0=0;
        int dp_1=Integer.MIN_VALUE;
        int dp__0=0;
        for(int price:prices){
            int tmp=dp_0;
            dp_0=Math.max(dp_0,dp_1+price);
            dp_1=Math.max(dp_1,dp__0-price);
            dp__0=tmp;
        }
        return dp_0;
    }

    public static void main(String[] args) {
        new P309_BestTimetoBuyandSellStockwithCooldown().maxProfit(new int[]{1,2,3,0,2});
    }
}

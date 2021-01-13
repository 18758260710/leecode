package problems;

import java.util.Arrays;

/**
 * @author wengtao
 * @date 2020/12/25
 **/
public class P322_CoinChange {
    //18ms
    public int coinChange(int[] coins, int amount) {
        if (amount == 0)return 0;
        int[] array = new int[amount+1];
        for (int i=amount;i>=0;i--){
            if (i<amount && array[i]==0){
                continue;
            }
            for (int coin : coins) {
                if (i - coin >=0) {
                    int old = array[i - coin];

                    if (old == 0 || old > array[i] + 1) {
                        array[i - coin] = array[i] + 1;
                    }
                }
            }
        }
        return array[0] == 0?-1:array[0];
    }

    public static void main(String[] args) {
        System.out.println(new P322_CoinChange().coinChange(new int[]{2}, 11));
    }

    //递归 1ms
    int min=Integer.MAX_VALUE;
    public int coinChange2(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);

        dfs(coins, coins.length - 1, 0, amount);
        return min==Integer.MAX_VALUE?-1:min;
    }

    private void dfs(int[] coins, int index, int count, int amount) {

        for(int i=amount/ coins[index];i>=0;i--){
            int remain=amount-coins[index] * i;

            if(remain==0){
                min=Math.min(min,count+i);
                return;
            }
            if (index == 0) {
                return ;
            }
            if(count+i+1>=min){
                return;
            }
            dfs(coins, index - 1,count+ i, remain);

        }
    }
}

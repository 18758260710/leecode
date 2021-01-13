package problems;

/**
 * @author wengtao
 * @date 2020/11/7
 **/
public class P877_StoneGame {
    //dp 0ms
    public boolean stoneGame(int[] piles) {
        int length = piles.length;
        int[][] dp = new int[length][length];
        for (int i=0;i<length;i++){
            dp[i][i] = piles[i];
        }
        for (int i=length-2;i>=0;i--){
            for (int j=i+1;j<length;j++){
                dp[i][j] = Math.max(piles[i]-dp[i+1][j],piles[j]-dp[i][j-1]);
            }
        }
        return dp[0][length-1]>0;
    }

    //math 偶数堆则先选的人必胜，奇数堆则有可能后选的人必胜（头尾都很少的情况）
    public boolean stoneGame2(int[] piles) {
        return true;
    }
}

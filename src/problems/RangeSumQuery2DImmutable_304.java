package problems;

/**
 * @author wengtao
 * @date 2020/3/31
 **/
public class RangeSumQuery2DImmutable_304 {
    int[][] cache;
    public RangeSumQuery2DImmutable_304(int[][] matrix) {
        if (matrix.length>0) {
            cache = new int[matrix.length][matrix[0].length];
            int sum=0;
            for (int i=0;i<cache.length;i++){
                sum+=matrix[i][0];
                cache[i][0] = sum;
            }
            sum=0;
            for (int i=0;i<cache[0].length;i++){
                sum+=matrix[0][i];
                cache[0][i] = sum;
            }
            for (int i=1;i<cache.length;i++){
                for (int j=1;j<cache[0].length;j++){
                    cache[i][j] = cache[i-1][j]+cache[i][j-1]-cache[i-1][j-1]+matrix[i][j];
                }
            }
        }else {
            cache=null;
        }
    }

    public int sumRegion2(int row1, int col1, int row2, int col2) {
        if (cache==null)return 0;
        return getNum(row2,col2)-getNum(row1-1,col2)-getNum(row2,col1-1)+getNum(row1-1,col1-1);
    }

    int getNum(int row,int col){
        if (row<0||col<0)return 0;
        return cache[row][col];
    }

    //用更大一点的矩阵来避免越界
    int[][] dp;
    public RangeSumQuery2DImmutable_304(int[][] matrix,int a) {
        if(   matrix           == null
                || matrix.length    == 0
                || matrix[0].length == 0   ){
            return;
        }
        dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j- 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }
}

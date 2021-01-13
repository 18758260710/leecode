package problems;

/**
 * Created by Administrator on 2020/1/19.
 */
public class P221_MaximalSquare {
    //dp 5ms
    public int maximalSquare(char[][] matrix) {
        int length = matrix.length;
        if (length==0)return 0;
        int max=0;
        int width = matrix[0].length;
        int[][] dp = new int[length+1][width+1];
        for (int i=1;i<=length;i++){
            for (int j=1;j<=width;j++){
                if (matrix[i-1][j-1]=='1'){
                    dp[i][j] = 1+Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]);
                    if (dp[i][j]>max)max=dp[i][j];
                }
            }
        }
        return max*max;
    }

    //逐行分析  3ms
    private char[][] global_matrix;
    private int global_row, global_col;
    private int res = 0;

    public int maximalSquare2(char[][] matrix) {
        global_matrix = matrix;
        if ((global_row = matrix.length) == 0)
            return 0;
        global_col = matrix[0].length;

        for (int i = 0; i < global_row - res; ++i)
            and_operate(i);

        return res * res;
    }

    //每行进行与操作
    private void and_operate(int row) {
        char[] next, base = global_matrix[row].clone();
        if (res < 1)
            calculate(base, 1);
        for (int i = row + 1; i < global_row; ++i) {
            next = global_matrix[i];
            for (int j = 0; j < global_col; ++j)
                base[j] &= next[j];//直接与，得到'1'或'0'(都为'1'则为'1')
            //若相邻的1的个数 < limit，则无需继续进行与操作，直接return
            if (!calculate(base, i - row + 1))
                return;
        }
    }

    //计算1连续出现的次数
    private boolean calculate(char[] array, int limit) {
        int count = 0;
        for (int j = 0; j < global_col; ++j) {
            if (array[j] == '0') {
                count = 0;
                continue;
            }//array[j]为'1'
            if (++count == limit) {
                res = Math.max(res, limit);
                return true;
            }
        }
        return false;
    }
}

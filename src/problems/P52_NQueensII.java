package problems;

public class P52_NQueensII {
    int count=0;
    //solution base on 51
    public int totalNQueens(int n) {
        boolean[] col90 = new boolean[n];
        //记录左上到右下方向
        boolean[] d45 = new boolean[2*n - 1];
        //记录右上到左下方向
        boolean[] d135 = new boolean[2*n - 1];
        solve(n, 0, col90, d45, d135);
        return count;
    }

    private void solve(int n, int row, boolean[] col90, boolean[] d45, boolean[] d135) {
        if (row == n) {
            count++;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!col90[col] && !d45[row + col] && !d135[row - col + n - 1]) {
                // valid square capture
                col90[col] = true;
                d45[row + col] = true;
                d135[row - col + n - 1] = true;

                // solve for next row
                solve(n, row + 1, col90, d45, d135);

                // backtrack for failure/ to find next combination
                col90[col] = false;
                d45[row + col] = false;
                d135[row - col + n - 1] = false;
            }
        }
    }

    public static void main(String[] args) {
        for (int i=3;i<20;i++) {
            System.out.println(new P52_NQueensII().totalNQueens2(i));
        }
    }

    //fast int位运算替代数组 牛批
    public int totalNQueens2(int n) {
        if (n < 1)
            return 0;
        dfs(n, 0, 0, 0, 0);
        return count;
    }

    private void dfs(int n, int row, int col, int left, int right) {
        if (row >= n) {
            count++;
            return;
        }
        //得到当前行所有空位，0代表空位
        //需要把高于第n位的位置成0，(1 << n) -1 使得右边n位都是1
        int bits = (~(col | left | right)) & ((1 << n) -1);
        while (bits > 0) {
            int p = bits & - bits; //取最低一位1
            dfs(n, row+1, col | p, (left | p) << 1, (right | p) >> 1);
            bits = bits & (bits - 1); //去掉最低位的1
        }
    }
}

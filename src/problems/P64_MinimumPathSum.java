package problems;

public class P64_MinimumPathSum {
    //my solution1 2ms
    public int minPathSum(int[][] grid) {
        int a = grid.length;
        int b = grid[0].length;
        int[][] result = new int[a][b];

        result[0][0]=grid[0][0];
        for (int i=1;i<a;i++){
            result[i][0] = result[i-1][0]+grid[i][0];
        }
        for (int i=1;i<b;i++){
            result[0][i] = result[0][i-1]+grid[0][i];
        }
        for (int i = 1; i < a; i++) {
            for (int j = 1; j < b; j++) {
                result[i][j]=Math.min(result[i-1][j],result[i][j-1])+grid[i][j];
            }
        }
        return result[a-1][b-1];
    }

    //0ms 递归
    int[][]cache;
    public int minPathSum2(int[][] grid) {
        cache = new int[grid.length][grid[0].length];
        return minPathSum(grid, grid[0].length-1, grid.length-1);
    }

    private int minPathSum(int[][] grid, int m, int n) {
        if (m<0 || n<0) {
            return Integer.MAX_VALUE;
        }

        if (m == 0 && n==0) {
            return grid[n][m];
        }

        if (cache[n][m] != 0) {
            return cache[n][m];
        }
        cache[n][m] = grid[n][m] + Math.min(minPathSum(grid, m, n-1), minPathSum(grid, m-1, n));
        return cache[n][m];
    }
}

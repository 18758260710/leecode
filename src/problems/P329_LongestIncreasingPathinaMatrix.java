package problems;

/**
 * @author wengtao
 * @date 2021/1/20
 **/
public class P329_LongestIncreasingPathinaMatrix {
    //邪道做法 想做广搜 slow
    public int longestIncreasingPath(int[][] matrix) {
        int length = matrix.length;
        int height = matrix[0].length;

        int[][] count = new int[length][height];

        int k=length;
        while (k>0) {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < height; j++) {
                    getTemp(matrix, count, i, j, i < length - 1, j < height - 1);
                }
            }

            for (int i = length - 1; i >= 0; i--) {
                for (int j = height - 1; j >= 0; j--) {
                    getTemp(matrix, count, i, j, i < length - 1, j < height - 1);
                }
            }
            k--;
        }

        int result = 0;
        for (int i=0;i<length;i++){
            for (int j = 0; j < height ; j++) {
                result = Math.max(result, count[i][j]);
            }
        }
        return result;
    }

    private void print(int length, int height, int[][] count) {
        for (int i=0;i<length;i++){
            for (int j = 0; j < height ; j++) {
                System.out.print(count[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private int getTemp(int[][] matrix, int[][] count, int i, int j, boolean b, boolean b1) {
        int temp = count[i][j];
        if (temp == 0) {
            temp = 1;
        }
        if (i > 0 && matrix[i - 1][j] < matrix[i][j]) {
            temp = Math.max(temp, count[i - 1][j] + 1);
        }
        if (b && matrix[i + 1][j] < matrix[i][j]) {
            temp = Math.max(temp, count[i + 1][j] + 1);
        }
        if (j > 0 && matrix[i][j - 1] < matrix[i][j]) {
            temp = Math.max(temp, count[i][j - 1] + 1);
        }
        if (b1 && matrix[i][j + 1] < matrix[i][j]) {
            temp = Math.max(temp, count[i][j + 1] + 1);
        }
        count[i][j] = temp;

        if (i > 0 && matrix[i - 1][j] > matrix[i][j]) {
            count[i - 1][j] = Math.max(temp + 1, count[i - 1][j]);
        }
        if (b && matrix[i + 1][j] > matrix[i][j]) {
            count[i + 1][j] = Math.max(temp + 1, count[i + 1][j]);
        }
        if (j > 0 && matrix[i][j - 1] > matrix[i][j]) {
            count[i][j - 1] = Math.max(temp + 1, count[i][j - 1]);
        }
        if (b1 && matrix[i][j + 1] > matrix[i][j]) {
            count[i][j + 1] = Math.max(temp + 1, count[i][j + 1]);
        }
        return temp;
    }

    public static void main(String[] args) {
        new P329_LongestIncreasingPathinaMatrix().longestIncreasingPath2(new int[][]{{19,2,8,6,4,14,1,0,17},{0,1,9,10,11,4,12,14,5},{14,12,16,0,15,8,5,2,8},{5,4,1,17,9,18,8,5,2},{9,5,4,8,16,7,11,5,0},{5,7,14,18,10,0,14,14,0},{9,14,4,13,18,16,9,12,10},{18,13,9,18,11,4,12,10,10},{7,14,16,19,10,19,11,6,4},{16,2,3,7,15,9,7,1,1},{1,6,16,15,18,6,6,1,14},{9,5,2,9,8,3,2,3,10},{2,3,16,8,7,7,0,18,16},{11,0,16,8,13,13,11,3,8},{17,11,0,12,11,15,12,17,0}
        });
    }

    //dp 深搜
    int[][] dp;
    int m;
    int n;
    public int longestIncreasingPath2(int[][] matrix) {
        m=matrix.length;
        if(m==0)
            return 0;
        n=matrix[0].length;
        if(n==0)
            return 0;
        dp=new int[m][n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++)
            {
                //if not visited go and do dfs
                if(dp[i][j]==0)
                {
                    this.builddp(i,j,matrix);
                }
            }
        }
        int max=1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++)
            {
                max=Math.max(max,dp[i][j]);
            }
        }
        return max;
    }

    public void builddp(int i,int j,int[][]matrix){
        dp[i][j]=1;
        int max=0;
        //check if lesser element is on the left
        if(i>0&&matrix[i-1][j]<matrix[i][j])
        {
            //if not visited then recur
            if(dp[i-1][j]==0)
                this.builddp(i-1,j,matrix);
            max=Math.max(max,dp[i-1][j]);
        }
        //check if lesser element is on the up
        if(j>0&&matrix[i][j-1]<matrix[i][j])
        {
            if(dp[i][j-1]==0)
                this.builddp(i,j-1,matrix);
            max=Math.max(max,dp[i][j-1]);
        }
        //check if lesser element is on the right
        if(i<m-1&&matrix[i+1][j]<matrix[i][j])
        {
            if(dp[i+1][j]==0)
                this.builddp(i+1,j,matrix);
            max=Math.max(max,dp[i+1][j]);
        }
        //check if lesser element is on the down
        if(j<n-1&&matrix[i][j+1]<matrix[i][j])
        {
            if(dp[i][j+1]==0)
                this.builddp(i,j+1,matrix);
            max=Math.max(max,dp[i][j+1]);
        }
        dp[i][j]+=max;
    }
}

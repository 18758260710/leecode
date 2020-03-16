package problems;

public class SpiralMatrixII_59 {
    //my solution1 0ms
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int count = 0;
        int m=1;

        int a = 0, b = 0;
        while (m<n*n+1) {
            while (b < n - count) {
                result[a][b]=m;
                m++;
                b++;
            }
            if (m>=n*n+1)break;
            b--;
            a++;
            while (a < n - count) {
                result[a][b]=m;
                m++;
                a++;
            }
            if (m>=n*n+1)break;
            a--;
            b--;
            while (b > count - 1) {
                result[a][b]=m;
                m++;
                b--;
            }
            if (m>=n*n+1)break;
            b++;
            a--;
            count++;
            while (a > count - 1) {
                result[a][b]=m;
                m++;
                a--;
            }
            if (m>=n*n+1)break;
            a++;
            b++;
        }
        return result;
    }

    public static void main(String[] args) {
        new SpiralMatrixII_59().generateMatrix(3);
    }

    //easy to understand
    public int[][] generateMatrix2(int n) {
        int[][] res = new int[n][n];
        int iStart = 0, iEnd = n-1;
        int val = 1;
        while(iStart<iEnd){
            for(int i=iStart;i<iEnd;i++){
                res[iStart][i] = val;
                val++;
            }
            for(int i=iStart;i<iEnd;i++){
                res[i][iEnd] = val;
                val++;
            }
            for(int i=iEnd;i>iStart;i--){
                res[iEnd][i] = val;
                val++;
            }
            for(int i=iEnd;i>iStart;i--){
                res[i][iStart] = val;
                val++;
            }
            iStart++;
            iEnd--;
        }
        if(iStart==iEnd){
            res[iStart][iStart] = val;
        }
        return res;
    }
}

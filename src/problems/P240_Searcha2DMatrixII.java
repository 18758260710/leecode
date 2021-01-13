package problems;

/**
 * Created by Administrator on 2020/2/10.
 */
public class P240_Searcha2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        int height = matrix.length;
        if (height==0)return false;
        int width = matrix[0].length;
        int a=height-1;
        int b=0;

        while (a>=0&&b<width) {
            if (matrix[a][b] == target)return true;
            if (matrix[a][b] > target)a--;
            else if (matrix[a][b] < target)b++;
        }
        return false;
    }
}

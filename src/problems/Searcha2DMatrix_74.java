package problems;

public class Searcha2DMatrix_74 {
    //O(n+m)
    public boolean searchMatrix(int[][] matrix, int target) {
        int a = matrix.length;
        if (a==0)return false;
        int b = matrix[0].length;

        int i=a-1;
        int j=0;

        while (i>=0&&j<b){
            if (matrix[i][j]<target){
                j++;
            }else if (matrix[i][j]>target){
                i--;
            }else
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        new Searcha2DMatrix_74().searchMatrix2(new int[][]{{1},{3}},3);
    }

    //两次二分查找
    public boolean searchMatrix2(int[][] matrix, int target) {
        int heigth = matrix.length;
        if (heigth==0)return false;
        int width = matrix[0].length;
        if (width==0)return false;

        if(matrix[0][0] > target || matrix[heigth-1][width-1] < target)		return false;

        int head = 0;
        int tail = heigth-1;
        int mid;
        while(head != tail && matrix[tail][0] > target)
        {
            mid = (head+tail+1)/2;
            if(matrix[mid][0] < target)		head = mid;
            else if(matrix[mid][0] > target)	tail = mid-1;
            else 	return true;
        }
        int row = tail;
        head = 0;
        tail = width-1;
        while(head <= tail)
        {
            mid = (head+tail)/2;
            if(matrix[row][mid] < target)
                head = mid + 1;
            else if(matrix[row][mid] > target)
                tail = mid -1;
            else return true;
        }
        return false;
    }
}

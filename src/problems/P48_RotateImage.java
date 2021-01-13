package problems;

public class P48_RotateImage {
    //my solution 0ms
    public void rotate(int[][] matrix) {
        int n=matrix.length;
        for (int i=0;i<n/2;i++){
            for (int j=i;j<n-i-1;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = temp;
            }
        }
        print(matrix);
    }

    public void print(int[][] matrix){
        int n=matrix.length;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                System.out.print(matrix[i][j]+",");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new P48_RotateImage().rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
    }
}

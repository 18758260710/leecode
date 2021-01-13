package problems;

import java.util.Arrays;

public class P85_MaximalRectangle {
    //此题极难
    //直接循环利用84的方法
    public int maximalRectangle(char[][] matrix) {
        P84_LargestRectangleinHistogram operater = new P84_LargestRectangleinHistogram();
        if(matrix.length == 0) return 0;
        int n = matrix[0].length;
        int[] heights = new int[n]; // using a array to reduce counting step of 1
        int max = 0;
        for(char[] row: matrix){
            for(int i = 0; i < n; i++){
                if(row[i] == '1'){
                    heights[i] += 1;
                } else {
                    heights[i] = 0;
                }
            }

            max = Math.max(max, operater.largestRectangleArea4(heights)); // go a sub problem of Histogram
        }
        return max;
    }

    //动态规划
    public int maximalRectangle2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, maxArea = 0;
        int[] left = new int[n];//记录当前行当前位置往右延伸最远的1和之前行往右延伸最远的1中较近的那一个
        int[] right = new int[n];//记录当前行当前位置往左延伸最远的1和之前行往左延伸最远的1中较近的那一个
        int[] height = new int[n];//和上面方法计算方法相同
        Arrays.fill(right, n - 1);

        for (int i = 0; i < m; i++) {
            int rB = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], rB);
                } else {
                    right[j] = n - 1;
                    rB = j - 1;
                }
            }
            int lB = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], lB);
                    height[j]++;
                    maxArea = Math.max(maxArea, height[j] * (right[j] - left[j] + 1));
                } else {
                    height[j] = 0;
                    left[j] = 0;
                    lB = j + 1;
                }
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        new P85_MaximalRectangle().maximalRectangle2(new char[][]{{'0','0','1','1','1','0','0'},{'0','0','0','1','0','0','0'},{'0','1','1','1','1','1','0'}});
    }
}

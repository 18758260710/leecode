package problems;

public class UniquePathsII_63 {

    //my solution1 0ms
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int a = obstacleGrid.length + 1;
        int b = obstacleGrid[0].length + 1;
        int[][] result = new int[a][b];
        result[0][1] = 1;
        for (int i = 1; i < a; i++) {
            for (int j = 1; j < b; j++) {
                if (obstacleGrid[i - 1][j - 1] == 0) {
                    result[i][j] = result[i - 1][j] + result[i][j - 1];
                }
            }
        }
        print(result);
        return result[a - 1][b - 1];
    }

    public static void main(String[] args) {
        System.out.println(new UniquePathsII_63().uniquePathsWithObstacles(new int[][]{{1}}));
    }

    public void print(int[][] square) {
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[0].length; j++) {
                System.out.print(square[i][j] + ",");
            }
            System.out.println();
        }
    }

    //official solution1 Dynamic Programming same as mine 直接使用了原数组
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {

        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;

        // If the starting cell has an obstacle, then simply return as there would be
        // no paths to the destination.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // Number of ways of reaching the starting cell = 1.
        obstacleGrid[0][0] = 1;

        // Filling the values for the first column
        for (int i = 1; i < R; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }

        // Filling the values for the first row
        for (int i = 1; i < C; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }

        // Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }

        // Return value stored in rightmost bottommost cell. That is the destination.
        return obstacleGrid[R - 1][C - 1];
    }
}

package problems;

/**
 * Created by Administrator on 2020/3/4.
 */
public class P695_MaxAreaofIsland {
    //my solution1 4ms
    int height;
    int width;
    int[] x = new int[]{1,-1,0,0};
    int[] y = new int[]{0,0,1,-1};
    public int maxAreaOfIsland(int[][] grid) {
        height = grid.length;
        if (height==0)return 0;
        width = grid[0].length;
        if (width==0)return 0;
        int max=0;
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                    int temp = helper(grid,i,j);
                    max = Math.max(max,temp);
            }
        }
        return max;
    }

    private int helper(int[][] grid, int i, int j) {
        if (i<0||j<0||i==height||j==width||grid[i][j]==0)return 0;
        int temp=1;
        grid[i][j]=0;
        for (int k=0;k<4;k++){
            temp+=helper(grid,i+x[k],j+y[k]);
        }
        return temp;
    }
}

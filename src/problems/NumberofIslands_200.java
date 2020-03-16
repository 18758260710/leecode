package problems;

import java.util.Stack;

public class NumberofIslands_200 {
    //my solution1 7ms slow
    public int numIslands(char[][] grid) {
        int rowLength = grid.length;
        if (rowLength==0)return 0;
        int colunmLength = grid[0].length;
        if (colunmLength==0)return 0;
        int result = 0;
        Stack<Point> stack = new Stack<>();
        boolean[][] checked = new boolean[rowLength][colunmLength];
        for (int i=0;i<rowLength;i++){
            for (int j=0;j<colunmLength;j++){
                if (grid[i][j] == '1' && !checked[i][j]){
                    result++;
                    stack.push(new Point(i,j));
                    checked[i][j]=true;
                    while (!stack.isEmpty()){
                        Point current = stack.pop();
                        if (current.x>0 && grid[current.x-1][current.y] == '1' && !checked[current.x-1][current.y]){
                            stack.push(new Point(current.x-1,current.y));
                            checked[current.x-1][current.y]=true;
                        }
                        if (current.y>0 && grid[current.x][current.y-1] == '1' && !checked[current.x][current.y-1]){
                            stack.push(new Point(current.x,current.y-1));
                            checked[current.x][current.y-1]=true;
                        }
                        if (current.x<rowLength-1 && grid[current.x+1][current.y] == '1' && !checked[current.x+1][current.y]){
                            stack.push(new Point(current.x+1,current.y));
                            checked[current.x+1][current.y]=true;
                        }
                        if (current.y<colunmLength-1 && grid[current.x][current.y+1] == '1' && !checked[current.x][current.y+1]){
                            stack.push(new Point(current.x,current.y +1));
                            checked[current.x][current.y+1]=true;
                        }
                    }
                }
            }
        }
        return result;

    }

    class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    //dfs递归 1ms
    public int numIslands2(char[][] grid) {
        int islandNum = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    islandNum++;
                }
            }
        }

        return islandNum;

    }


    public void dfs (char[][] grid, int i, int j) {

        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length) {
            return;
        } else if (grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
    }

    public static void main(String[] args) {
        char[][] a =new char[][]{{'1','1','0','0','0'},
                                {'1','1','0','0','0'},
                                {'0','0','1','0','0'},
                                {'0','0','0','1','1'}};
        new NumberofIslands_200().numIslands(a);
    }
}

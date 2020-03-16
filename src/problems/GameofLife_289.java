package problems;

/**
 * Created by Administrator on 2020/3/14.
 */
public class GameofLife_289 {
    public void gameOfLife(int[][] board) {
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                int lives=0;
                lives+=check(i-1,j-1,board);
                lives+=check(i-1,j,board);
                lives+=check(i-1,j+1,board);
                lives+=check(i+1,j-1,board);
                lives+=check(i+1,j,board);
                lives+=check(i+1,j+1,board);
                lives+=check(i,j-1,board);
                lives+=check(i,j+1,board);
                if (board[i][j]==0 && lives==3){
                    board[i][j]=-1;
                }
                if (board[i][j]==1){
                    if (lives>3||lives<2)board[i][j]=2;
                }
            }
        }
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                if (board[i][j]==-1)board[i][j]=1;
                else if (board[i][j]==2)board[i][j]=0;
            }
        }
    }

    private int check(int i, int j, int[][] board) {
        if (i>=0&&j>=0&&i<board.length&&j<board[0].length&&board[i][j]>0)
        return 1;

        return 0;
    }
}

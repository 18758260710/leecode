package problems;

import java.util.LinkedList;
import java.util.Queue;

public class SurroundedRegions_130 {
    //my solution1 5ms slow
    boolean[][] temp;
    public void solve(char[][] board) {
        int row = board.length;
        if (row==0)return;
        int column = board[0].length;
        temp = new boolean[row][column];
        Queue<String> queue = new LinkedList<>();
        for (int i=0;i<column;i++){
            if (board[0][i]=='O'&&!temp[0][i]){
                temp[0][i]=true;
                queue.add("0_"+i);
            }
            if (board[row-1][i]=='O'&&!temp[row-1][i]){
                temp[row-1][i]=true;
                queue.add((row-1)+"_"+i);
            }
        }
        for (int i=1;i<row-1;i++){
            if (board[i][0]=='O'&&!temp[i][0]){
                temp[i][0]=true;
                queue.add(i+"_0");
            }
            if (board[i][column-1]=='O'&&!temp[i][column-1]){
                temp[i][column-1]=true;
                queue.add(i+"_"+(column-1));
            }
        }
        while (!queue.isEmpty()){
            String[] point = queue.poll().split("_");
            int a = Integer.parseInt(point[0]);
            int b = Integer.parseInt(point[1]);
            if (a>0&&board[a-1][b]=='O'&&!temp[a-1][b]){
                temp[a-1][b]=true;
                queue.add((a-1)+"_"+b);
            }
            if (b>0&&board[a][b-1]=='O'&&!temp[a][b-1]){
                temp[a][b-1]=true;
                queue.add(a+"_"+(b-1));
            }
            if (a<row-1&&board[a+1][b]=='O'&&!temp[a+1][b]){
                temp[a+1][b]=true;
                queue.add((a+1)+"_"+b);
            }
            if (b<column-1&&board[a][b+1]=='O'&&!temp[a][b+1]){
                temp[a][b+1]=true;
                queue.add(a+"_"+(b+1));
            }
        }
        for (int i=0;i<row;i++){
            for (int j=0;j<column;j++){
                if (!temp[i][j]){
                    board[i][j]='X';
                }
            }
        }
    }

    public static void main(String[] args) {
        new SurroundedRegions_130().solve(new char[][]{{'X','O','X','O','X','O'},{'O','X','O','X','O','X'},{'X','O','X','O','X','O'},{'O','X','O','X','O','X'}});
    }

    //一样的思想 1ms 不用额外空间 用递归代替栈
    public void solve2(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0)
            return;
        int m = board.length, n = board[0].length;
        for(int j = 0; j < n; j++){
            helper(board, 0, j);
            helper(board, m - 1, j);
        }
        for(int i = 1; i < m - 1; i++){
            helper(board, i, 0);
            helper(board, i, n - 1);
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O')  board[i][j] = 'X';
                if(board[i][j] == '*')  board[i][j] = 'O';
            }
        }
        return;
    }
    private void helper(char[][] board, int i, int j){
        if(i<0||i>=board.length||j<0||j>=board[0].length||board[i][j]!='O'){
            return;
        }
        board[i][j] = '*';
        helper(board, i + 1, j);
        helper(board, i - 1, j);
        helper(board, i, j + 1);
        helper(board, i, j - 1);
    }
}

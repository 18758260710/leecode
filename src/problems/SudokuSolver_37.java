package problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SudokuSolver_37 {
    Set<Character> remains = new HashSet<>();

    //my solution DFS 复制的时间太长
    public void solveSudoku(char[][] board) {
        for (int i=0;i<9;i++)
        remains.add((char) ('1'+i));

        Stack<char[][]> stack = new Stack<>();
        stack.push(board);

        char[][] result = DFS(stack);
        for(int i = 0;i < result.length;i++){
            board[i] = result[i].clone();
        }
    }

    private char[][] DFS(Stack<char[][]> stack) {
        while (!stack.isEmpty()){
            char[][] begin = stack.pop();
            boolean finish = manage(stack, begin);
            if (finish)return begin;
        }
        return null;
    }

    private boolean manage(Stack<char[][]> stack, char[][] begin) {
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                if (begin[i][j]=='.'){
                    Set<Character> remains = getRemains(begin,i,j);
                    for (Character remain:remains){
                        char[][] newBegin = getClone(begin);
                        newBegin[i][j] = remain;
                        stack.push(newBegin);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private char[][] getClone(char[][] begin) {
        char[][] clone = new char[begin.length][begin[0].length];
        for(int i = 0;i < begin.length;i++){
            clone[i] = begin[i].clone();
        }
        return clone;
    }

    private Set<Character> getRemains(char[][] begin, int i, int j) {
        Set<Character> result = new HashSet<>(remains);
        for (int k=0;k<9;k++){
            result.remove(begin[i][k]);
            result.remove(begin[k][j]);
        }
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                result.remove(begin[i/3*3+a][j/3*3+b]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        new SudokuSolver_37().solveSudoku(
            new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                         {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                         {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                         {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                         {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                         {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                         {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                         {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                         {'.', '.', '.', '.', '8', '.', '.', '7', '9'}});
    }

    private void print(char[][] begin){
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                System.out.print(begin[i][j]+", ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    //dfs遍历 by vinebranch
    public void solveSudoku2(char[][] board) {
        if(board == null || board.length == 0)
            return;
        solve(board);
    }

    public boolean solve(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9
                        if(isValid(board, i, j, c)){
                            board[i][j] = c; //Put c for this cell

                            if(solve(board))
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back
                        }
                    }

                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' &&
                board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }

    //by jiangbowei2010 dfs
    public void solveSudoku3(char[][] board) {
        dfs(board,0);
    }

    private boolean dfs(char[][] board, int d) {
        if (d==81) return true; //found solution
        int i=d/9, j=d%9;
        if (board[i][j]!='.') return dfs(board,d+1);//prefill number skip

        boolean[] flag=new boolean[10];
        validate(board,i,j,flag);
        for (int k=1; k<=9; k++) {
            if (flag[k]) {
                board[i][j]=(char)('0'+k);
                if (dfs(board,d+1)) return true;
            }
        }
        board[i][j]='.'; //if can not solve, in the wrong path, change back to '.' and out
        return false;
    }
    private void validate(char[][] board, int i, int j, boolean[] flag) {
        Arrays.fill(flag,true);
        for (int k=0; k<9; k++) {
            if (board[i][k]!='.') flag[board[i][k]-'0']=false;
            if (board[k][j]!='.') flag[board[k][j]-'0']=false;
            int r=i/3*3+k/3;
            int c=j/3*3+k%3;
            if (board[r][c]!='.') flag[board[r][c]-'0']=false;
        }
    }
}

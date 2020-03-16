package problems;

public class WordSearch_79 {
    //my solution 4ms
    public boolean exist(char[][] board, String word) {
        if (word.isEmpty())return true;
        char[] list  = word.toCharArray();
        char first = list[0];
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                if (board[i][j]==first){
                    if (list.length==1)return true;
                    boolean success = explore(i,j,board,list,used);
                    if (success)return true;
                }
            }
        }
        return false;
    }

    private boolean explore(int i, int j, char[][] board, char[] list,boolean[][] used) {
        used[i][j]=true;
        boolean success =  helper(i,j,board,used,1,list);
        used[i][j]=false;
        return success;
    }

    private boolean helper(int i, int j, char[][] board, boolean[][] used, int index,char[] list) {
        if (index==list.length)return true;
        char target = list[index];
        if (i>0&&!used[i-1][j]&&!used[i-1][j]&&board[i-1][j]==target){
            used[i-1][j]=true;
            if(helper(i-1,j,board,used,index+1,list))return true;
            used[i-1][j]=false;
        }
        if (i<board.length-1&&!used[i+1][j]&&board[i+1][j]==target){
            used[i+1][j]=true;
            if(helper(i+1,j,board,used,index+1,list))return true;
            used[i+1][j]=false;
        }
        if (j>0&&!used[i][j-1]&&!used[i][j-1]&&board[i][j-1]==target){
            used[i][j-1]=true;
            if(helper(i,j-1,board,used,index+1,list))return true;
            used[i][j-1]=false;
        }
        if (j<board[0].length-1&&!used[i][j+1]&&board[i][j+1]==target){
            used[i][j+1]=true;
            if(helper(i,j+1,board,used,index+1,list))return true;
            used[i][j+1]=false;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new WordSearch_79().exist3(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ABCCED"));
    }
    boolean[][] used;
    //4ms 一样的思路，结构上优化
    public boolean exist2(char[][] board, String word) {
        used = new boolean[board.length][board[0].length];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if((word.charAt(0) == board[i][j]) && search(board, word, i, j, 0)){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean search(char[][]board, String word, int i, int j, int index){
        if(index == word.length()){
            return true;
        }

        if(i >= board.length || i < 0 || j >= board[i].length || j < 0 || board[i][j] != word.charAt(index) || used[i][j]){
            return false;
        }

        used[i][j] = true;
        if(search(board, word, i-1, j, index+1) ||
            search(board, word, i+1, j, index+1) ||
            search(board, word, i, j-1, index+1) ||
            search(board, word, i, j+1, index+1)){
            return true;
        }

        used[i][j] = false;
        return false;
    }

    //思路一样 board[y][x] ^= 256标记使用情况，used
    public boolean exist3(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y=0; y<board.length; y++) {
            for (int x=0; x<board[y].length; x++) {
                if (exist(board, y, x, w, 0)) return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length) return true;
        if (y<0 || x<0 || y == board.length || x == board[y].length) return false;
        if (board[y][x] != word[i]) return false;
        board[y][x] ^= 256;
        boolean exist = exist(board, y, x+1, word, i+1)
            || exist(board, y, x-1, word, i+1)
            || exist(board, y+1, x, word, i+1)
            || exist(board, y-1, x, word, i+1);
        board[y][x] ^= 256;
        return exist;
    }
}

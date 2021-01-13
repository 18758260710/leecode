package problems;

import java.util.HashSet;
import java.util.Set;

public class P36_ValidSudoku {

    //my solution
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            int[] count1 = new int[10];
            int[] count2 = new int[10];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (count1[board[i][j] - '0']>0)return false;
                    count1[board[i][j] - '0'] = 1;
                }
                if (board[j][i] != '.') {
                    if (count2[board[j][i] - '0']>0)return false;
                    count2[board[j][i] - '0'] = 1;
                }
            }
        }
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                int[] count = new int[10];
                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        if (board[i+a][j+b]!= '.') {
                            if (count[board[i + a][j + b] - '0'] > 0)
                                return false;
                            count[board[i + a][j + b] - '0'] = 1;
                        }
                    }
                }
            }
        }

        return true;
    }

    //by StefanPochmann
    public boolean isValidSudoku2(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int i=0; i<9; ++i) {
            for (int j=0; j<9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!seen.add(number + " in row " + i) ||
                        !seen.add(number + " in column " + j) ||
                        !seen.add(number + " in block " + i/3 + "-" + j/3))
                        return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new P36_ValidSudoku().isValidSudoku(
            new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}}));
    }
}

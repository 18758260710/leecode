package problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P51_NQueens {
    //my solution1 slow
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();

        List<Integer> list = new ArrayList<>();
        Set<Integer> a = new HashSet<>();
        for (int i=0;i<n;i++){
            a.add(i);
        }

        explore(result,list,a,n);
        return result;
    }

    private void explore(List<List<String>> result, List<Integer> list, Set<Integer> a, int n) {
        if (list.size()==n){
            result.add(toSquare(list,n));
            return;
        }
        Set<Integer> allowed = new HashSet<>(a);
        for (int i=0;i<list.size();i++){
            allowed.remove(list.get(i));
            allowed.remove(list.get(i)+list.size()-i);
            allowed.remove(list.get(i)-list.size()+i);
        }

        for (Integer allow:allowed){
            list.add(allow);
            explore(result,list,a,n);
            list.remove(allow);
        }
    }

    private List<String> toSquare(List<Integer> list,int n) {


        List<String> line = new ArrayList<>();

        for (Integer item:list){
            StringBuilder temp = new StringBuilder();
            int a=0;
            while (a<n) {
                if (a==item){
                    temp.append("Q");
                }else {
                    temp.append(".");
                }
                a++;
            }
            line.add(temp.toString());
        }

        return line;
    }

    public static void main(String[] args) {
        for (int i=0;i<20;i++){
            System.out.println(i+":"+(i & - i));
        }
//        System.out.println((Integer.toBinaryString(-7)) );
//        System.out.println((Integer.toBinaryString(7)) );
//        for (int i=3;i<20;i++) {
//            System.out.println(new NQueens_51().solveNQueens2(i).size());
//        }
    }

    //best 1ms
    public List<List<String>> solveNQueens2(int n) {

        char[][] queens = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                queens[i][j] = '.';
            }
        }
        List<List<String>> sol = new ArrayList<>();
        //记录列方向
        boolean[] col90 = new boolean[n];
        //记录左上到右下方向
        boolean[] d45 = new boolean[2*n - 1];
        //记录右上到左下方向
        boolean[] d135 = new boolean[2*n - 1];
        solve(n, 0, queens, sol, col90, d45, d135);
        return sol;
    }

    private void solve(int n, int row, char[][] queens, List<List<String>> sol, boolean[] col90, boolean[] d45, boolean[] d135) {

        // base case
        if (row == n) {
            addSol(queens, sol);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!col90[col] && !d45[row + col] && !d135[row - col + n - 1]) {
                // valid square capture
                queens[row][col] = 'Q';
                col90[col] = true;
                d45[row + col] = true;
                d135[row - col + n - 1] = true;

                // solve for next row
                solve(n, row + 1, queens, sol, col90, d45, d135);

                // backtrack for failure/ to find next combination
                queens[row][col] = '.';
                col90[col] = false;
                d45[row + col] = false;
                d135[row - col + n - 1] = false;
            }
        }

    }

    private void addSol(char[][] queens, List<List<String>> sol) {
        List<String> row = new ArrayList<>();
        for (char[] queen: queens) {
            row.add(String.valueOf(queen));
        }
        sol.add(row);
    }
}

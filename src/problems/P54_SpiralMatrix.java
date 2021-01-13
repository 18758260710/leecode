package problems;

import java.util.ArrayList;
import java.util.List;

public class P54_SpiralMatrix {

    //my solution 0ms
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) {
            return result;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return result;
        }
        int count = 0;

        int a = 0, b = 0;
        while (true) {
            if (b >= n - count) {
                break;
            }
            while (b < n - count) {
                result.add(matrix[a][b]);
                b++;
            }
            b--;
            a++;
            if (a >= m - count) {
                break;
            }
            while (a < m - count) {
                result.add(matrix[a][b]);
                a++;
            }
            a--;
            b--;
            if (b <= count - 1) {
                break;
            }
            while (b > count - 1) {
                result.add(matrix[a][b]);
                b--;
            }
            b++;
            a--;
            count++;
            if (a <= count - 1) {
                break;
            }
            while (a > count - 1) {
                result.add(matrix[a][b]);
                a--;
            }
            a++;
            b++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new P54_SpiralMatrix().spiralOrder(new int[][]{{1, 2, 3}, {1, 2, 3}, {1, 2, 3}}));
    }

    //official solution1  seen来判重  空间使用较多
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0) {
            return ans;
        }
        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            int cr = r + dr[di];
            int cc = c + dc[di];
            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]) {
                r = cr;
                c = cc;
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }

    //official solution2  类似我的方法
    public List<Integer> spiralOrder3(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0) {
            return ans;
        }
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) {
                ans.add(matrix[r1][c]);
            }
            for (int r = r1 + 1; r <= r2; r++) {
                ans.add(matrix[r][c2]);
            }
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) {
                    ans.add(matrix[r2][c]);
                }
                for (int r = r2; r > r1; r--) {
                    ans.add(matrix[r][c1]);
                }
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }
}

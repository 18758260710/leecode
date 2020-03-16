package problems;

public class EditDistance_72 {

    //递归超时
    public int minDistance(String word1, String word2) {
        if (word2.isEmpty()) {
            return word1.length();
        }
        if (word1.isEmpty()) {
            return word2.length();
        }
        if (word1.equals(word2)) {
            return 0;
        }

        if (word1.charAt(0) == word2.charAt(0)) {
            return minDistance(word1.substring(1), word2.substring(1));
        } else {
            int result = minDistance(word1, word2.substring(1)) + 1;
            result = Math.min(result, minDistance(word1.substring(1), word2.substring(1)) + 1);
            result = Math.min(result, minDistance(word1.substring(1), word2) + 1);
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new EditDistance_72().minDistance3("horse", "ros"));
    }

    //dp  7ms
    public int minDistance2(String word1, String word2) {
        int a = word1.length();
        int b = word2.length();

        int[][] record = new int[a + 1][b + 1];
        for (int i = 0; i < b + 1; i++) {
            record[a][i] = b - i;
        }
        for (int i = 0; i < a + 1; i++) {
            record[i][b] = a - i;
        }
        for (int i = a - 1; i >= 0; i--) {
            for (int j = b - 1; j >= 0; j--) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    record[i][j] = record[i + 1][j + 1];
                } else {
                    record[i][j] = Math
                        .min(Math.min(record[i][j + 1] + 1, record[i + 1][j + 1] + 1), record[i + 1][j] + 1);
                }
            }
        }

        return record[0][0];
    }

    //dp2 3ms 只留存一行 和 一个 在一行上刷新数据 简化了上个方法
    public int minDistance3(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();

        int[] f = new int[l2 + 1];
        for (int j = 1; j <= l2; ++j) {
            f[j] = j;
        }

        for (int i = 1; i <= l1; ++i) {
            int prev = i;
            for (int j = 1; j <= l2; ++j) {
                int cur;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    cur = f[j - 1];
                } else {
                    cur = Math.min(Math.min(f[j - 1], prev), f[j]) + 1;
                }

                f[j - 1] = prev;
                prev = cur;
            }
            f[l2] = prev;
        }
        return f[l2];
    }
}

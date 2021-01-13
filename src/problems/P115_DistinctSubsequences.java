package problems;

import java.util.Arrays;

public class P115_DistinctSubsequences {

    //my solution1 dp 4ms
    public int numDistinct(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) {
            return 0;
        }
        int[][] dp = new int[t.length()][s.length() + 1];
        char a = t.charAt(0);
        for (int j = 1; j < s.length() + 1; j++) {
            if (s.charAt(j - 1) == a) {
                dp[0][j] = dp[0][j - 1] + 1;
            } else {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 1; i < t.length(); i++) {
            a = t.charAt(i);
            for (int j = 1; j < s.length() + 1; j++) {
                if (s.charAt(j - 1) == a) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[t.length() - 1][s.length()];
    }

    public static void main(String[] args) {
        System.out.println(new P115_DistinctSubsequences().numDistinct3("rabbbbit", "rabbit"));
    }

    //my solution2 dp 减少空间 4ms
    public int numDistinct2(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        char a = t.charAt(0);
        for (int j = 1; j < s.length() + 1; j++) {
            if (s.charAt(j - 1) == a) {
                dp[j] = dp[j - 1] + 1;
            } else {
                dp[j] = dp[j - 1];
            }
        }
        for (int i = 1; i < t.length(); i++) {
            a = t.charAt(i);
            int temp = dp[0];
            for (int j = 1; j < s.length() + 1; j++) {
                int aa = dp[j];
                if (s.charAt(j - 1) == a) {
                    dp[j] = dp[j - 1] + temp;
                } else {
                    dp[j] = dp[j - 1];
                }
                temp = aa;
            }
        }
        return dp[s.length()];
    }

    //1ms
    public int numDistinct3(String s, String t) {
        char[] ss = s.toCharArray(), ts = t.toCharArray();
        int slen = ss.length, tlen = ts.length;

        int[] startIdx = new int[128];
        int[] prevIdx = new int[tlen];
        Arrays.fill(startIdx, -1);

        for (int i = 0; i < tlen; i++) {//+1索引是因为dp的下标向后移了一位
            prevIdx[i] = startIdx[ts[i]];//记录t中某位字符之前出现的位置之后+1的索引，如之前未出现则为-1
            startIdx[ts[i]] = i + 1;//记录某字符在t中最后出现的位置之后+1的索引
        }

        int[] dp = new int[tlen + 1];
        dp[0] = 1;

        for (int i = 0; i < slen; i++) {//每次循环后dp[j]表示的是s[0,i]能够匹配t[0,j]的组合数。
            for (int j = startIdx[ss[i]]; j != -1; j = prevIdx[j - 1]) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[tlen];
    }
}

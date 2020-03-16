package problems;

import java.util.regex.Pattern;

public class RegularExpressionMatching_10 {

    //赖皮的方法
    public boolean isMatch(String s, String p) {
        return Pattern.matches(p, s);
    }

    public boolean isMatch2(String s, String p) {
        return Pattern.matches(p, s);
    }

    //递归调用
    public boolean isMatch3(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean first_match = (!s.isEmpty() &&
            (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

        if (p.length() >= 2 && p.charAt(1) == '*') {
            return (isMatch3(s, p.substring(2)) ||
                (first_match && isMatch3(s.substring(1), p)));
        } else {
            return first_match && isMatch3(s.substring(1), p.substring(1));
        }
    }

    //动态规划
    //只是把递归中得到的结果存储下来加快速度
    enum Result {
        TRUE, FALSE
    }

    Result[][] memo;

    public boolean isMatch4(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()) {
            ans = i == text.length();
        } else {
            boolean first_match = (i < text.length() &&
                (pattern.charAt(j) == text.charAt(i) ||
                    pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                ans = (dp(i, j + 2, text, pattern) ||
                    first_match && dp(i + 1, j, text, pattern));
            } else {
                ans = first_match && dp(i + 1, j + 1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }

    //动态规划第二版  最快
    public boolean isMatch5(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                    (pattern.charAt(j) == text.charAt(i) ||
                        pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(new RegularExpressionMatching_10().isMatch5("aa","*"));
    }
}

package problems;

public class P97_InterleavingString {
    //my solution 记忆递归 1ms
    Boolean[][] dp;
    String a;
    String b;
    String c;
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length()+s2.length()!=s3.length())return false;

        dp = new Boolean[s1.length()][s2.length()];
        a=s1;
        b=s2;
        c=s3;
        return explore(0,0);
    }

    private boolean explore( int index1, int index2) {
        if (a.substring(index1).isEmpty()){
            return b.substring(index2).equals(c.substring(index1+index2));
        }
        if (b.substring(index2).isEmpty()){
            return a.substring(index1).equals(c.substring(index1+index2));
        }

        if (dp[index1][index2]!=null)return dp[index1][index2];
        boolean re = false;
        if (a.charAt(index1)==c.charAt(index1+index2)){
            re = explore(index1+1,index2);
        }
        if (re){
            dp[index1][index2] = true;
            return true;
        }
        if (b.charAt(index2)==c.charAt(index1+index2)){
            re = explore(index1,index2+1);
        }
        dp[index1][index2] = re;
        return re;
    }

    public static void main(String[] args) {
        System.out.println(new P97_InterleavingString().isInterleave("aabd","abcd","aabdabcd"));
    }

    //0ms same 用int[][]快一点
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null || s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        return isInterLeave(s1, s2, s3, s1.length(), s2.length(), dp);
    }

    private boolean isInterLeave(String s1, String s2, String s3, int m, int n, int[][] dp) {
        if (m == 0 && n == 0) {
            return true;
        } else if (dp[m][n] != 0) {
            return dp[m][n] == 1;
        } else if (m == 0) {
            return s2.substring(0, n).equals(s3.substring(0, n));
        } else if (n == 0) {
            return s1.substring(0, m).equals(s3.substring(0, m));
        }
        if (s1.charAt(m - 1) == s3.charAt(m + n - 1)) {
            if (isInterLeave(s1, s2, s3, m - 1, n, dp)) {
                dp[m][n] = 1;
                return true;
            }
        }
        if (s2.charAt(n - 1) == s3.charAt(m + n - 1)) {
            if (isInterLeave(s1, s2, s3, m, n - 1, dp)) {
                dp[m][n] = 1;
                return true;
            }
        }
        dp[m][n] = -1;
        return false;
    }

    //dp 3ms 因为要全部走完才返回
    public boolean isInterleave3(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}

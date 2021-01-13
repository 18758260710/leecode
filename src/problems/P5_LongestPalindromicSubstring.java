package problems;

public class P5_LongestPalindromicSubstring {

    //my solution
    //official solution1
    public String longestPalindrome(String s) {

        if (s.isEmpty()) {
            return "";
        }
        String reverses = new StringBuffer(s).reverse().toString();
        int length = s.length();
        int[][] lengthMap = new int[length + 1][length + 1];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (s.charAt(i) == reverses.charAt(j)) {
                    lengthMap[i + 1][j + 1] = lengthMap[i][j] + 1;
                }
            }
        }
        int max = 0;
        int end = 0;
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= length; j++) {
                if (lengthMap[i][j] > max && i + j - lengthMap[i][j] == length) {
                    max = lengthMap[i][j];
                    end = i - 1;
                }
            }
        }
        String result = s.substring(end - max + 1, end + 1);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new P5_LongestPalindromicSubstring().longestPalindrome4("abbc"));
    }

    //Dynamic Programming
    //official solution2
    public String longestPalindrome2(String s) {
        String result = "";
        int max = 0;
        if (s.isEmpty()) {
            return "";
        }
        int length = s.length();
        boolean[][] lengthMap = new boolean[length + 1][length + 1];
        for (int i = 0; i < length; i++) {
            lengthMap[i][i] = true;
            if (max < 1) {
                max = 1;
                result = s.substring(i,i+1);
            }
            if (i < length - 1) {
                lengthMap[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
                if (lengthMap[i][i + 1]&&max<2){
                    max=2;
                    result = s.substring(i,i+2);
                }
            }
        }
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i + 2; j < length; j++) {
                lengthMap[i][j] = lengthMap[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                if (lengthMap[i][j] && j - i + 1 > max) {
                    max = j - i + 1;
                    result = s.substring(i, j + 1);
                }
            }
        }

        return result;
    }

    //Expand Around Center
    //official solution3
    public String longestPalindrome3(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    //Manacher’s algorithm
    //official solution4
    //best solution O(n)time complexity
    //http://www.cnblogs.com/grandyang/p/4475985.html
    public String longestPalindrome4(String s) {
        if (s.isEmpty()) {
            return "";
        }
        String t = "$#";
        for (int i = 0; i < s.length(); ++i) {
            t += s.charAt(i);
            t += "#";
        }

        int[] p = new int[t.length()];
        int mx = 0, id = 0, resLen = 0, resCenter = 0;
        for (int i = 1; i < t.length(); ++i) {
            p[i] = mx > i ? Math.min(p[2 * id - i], mx - i) : 1;
            while (i + p[i]<t.length()&&t.charAt(i + p[i]) == t.charAt(i - p[i])) ++p[i];
            if (mx < i + p[i]) {
                mx = i + p[i];
                id = i;
            }
            if (resLen < p[i]) {
                resLen = p[i];
                resCenter = i;
            }
        }

        return s.substring((resCenter - resLen) / 2, (resCenter + resLen) / 2-1);
    }
}

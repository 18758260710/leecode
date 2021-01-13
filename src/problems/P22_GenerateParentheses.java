package problems;

import java.util.ArrayList;
import java.util.List;

public class P22_GenerateParentheses {
    //my solution
    public List<String> generateParenthesis(int n) {
        if (n<=0)return new ArrayList<>();
        List<String>[][] dp = new ArrayList[n+1][n+1];
        for (int i = n; i >= 0; i--) {
            for (int j = n; j >= i; j--) {
                dp[i][j]=new ArrayList<>();
                if (i == n && j == n) {
                    dp[n][n].add("");
                } else {
                    if (i + 1 <= j) {
                        for (String a : dp[i + 1][j]) {
                            dp[i][j].add(a + "(");
                        }
                    }
                    if (j + 1 <= n) {
                        for (String a : dp[i][j + 1]) {
                            dp[i][j].add(a + ")");
                        }
                    }
                }
            }
        }
        return dp[0][0];
    }

    //official solution2
    //Backtracking
    public List<String> generateParenthesis2(int n) {
        List<String> list = new ArrayList<>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    public void backtrack(List<String> list, String str, int open, int close, int max){

        if(str.length() == max*2){
            list.add(str);
            return;
        }

        if(open < max)
            backtrack(list, str+"(", open+1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }

    //official solution1
    //Brute Force
    //generate all and validate  bad solution
    public List<String> generateParenthesis3(int n) {
        List<String> combinations = new ArrayList();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos+1, result);
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }

    //official solution3
    //Closure Number
    public List<String> generateParenthesis4(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis4(c))
                    for (String right: generateParenthesis4(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }
}

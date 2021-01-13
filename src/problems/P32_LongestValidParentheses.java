package problems;

import java.util.Stack;

public class P32_LongestValidParentheses {
    //my solution
    public int longestValidParentheses(String s) {
        int result = 0;
        Integer preview = 0;
        Stack<Pair> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (Integer i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(new Pair(i,preview));
                preview=0;
            }
            if (chars[i] == ')') {
                if (!stack.isEmpty()) {
                    Pair left = stack.pop();
                    int length = i - left.index + 1 + left.preview;
                    preview = length;
                    result = Math.max(result, length);
                }else preview=0;
            }
        }
        return result;
    }

    private class Pair {

        int index;

        int preview;
        public Pair(int index, int preview) {
            this.index = index;
            this.preview = preview;
        }

    }
    public static void main(String[] args) {
        System.out.println(new P32_LongestValidParentheses().longestValidParentheses4(")()())()()("));
    }

    //official solution1 Brute Force
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.empty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty();
    }
    public int longestValidParentheses2(String s) {
        int maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j+=2) {
                if (isValid(s.substring(i, j))) {
                    maxlen = Math.max(maxlen, j - i);
                }
            }
        }
        return maxlen;
    }

    //official solution2 Using Dynamic Programming fast
    public int longestValidParentheses3(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    //official solution3  Using Stack
    public int longestValidParentheses4(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    //official solution4  Without extra space
    public int longestValidParentheses5(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}


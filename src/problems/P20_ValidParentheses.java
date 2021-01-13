package problems;

import java.util.Stack;

public class P20_ValidParentheses {
    //my solution
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char a : chars) {
            if (a == '(' || a == '{' || a == '[') {
                stack.push(a);
            }

            if (a == ')' || a == '}' || a == ']') {
                if (stack.empty())return false;
                if ((a == ')' && stack.peek() == '(') || (a == '}' && stack.peek() == '{') || (a == ']'
                    && stack.peek() == '[')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    //by phoenix13steve better
    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
}

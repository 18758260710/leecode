package problems;

import java.util.Stack;

/**
 * Created by Administrator on 2020/2/4.
 */
public class BasicCalculator_224 {
    //递归 slow
    public int calculate(String s) {
        boolean minus = false;
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int current = 0;
        for (int i = 0; i < s.length(); ) {
            if (s.charAt(i) == ' ') {
                i++;
                continue;
            }
            if (s.charAt(i) == '-') {
                minus = true;
                i++;
                continue;
            }
            if (s.charAt(i) == '+') {
                minus = false;
                i++;
                continue;
            }
            if (s.charAt(i) == '(') {
                stack.push(i);
                i++;
                while (!stack.isEmpty()){
                    if (s.charAt(i) == ')') {
                        int left = stack.pop();
                        if (stack.isEmpty()) {
                            current = calculate(s.substring(left+1,i));
                        }
                    }
                    if (s.charAt(i) == '(') {
                        stack.push(i);
                    }
                    i++;
                }
            }else {
                current=0;
                while ( i<s.length()&& s.charAt(i)>='0' && s.charAt(i)<='9'){
                    current=current*10+s.charAt(i)-'0';
                    i++;
                }
            }
            result += (minus?-1:1)*current;
        }
        return result;
    }

    //思想类似 不用substring
    char[] cs ;

    int index = 0;
    public int calculate2(String s) {

        cs = s.toCharArray();
        return help();

    }

    int help(){
        int sign = 1;
        int num = 0;
        int res = 0;
        while (index < cs.length) {
            char ch = cs[index++];
            if (ch == ' ') continue;
            if (ch >= '0' && ch <= '9') num = num * 10 + ch - '0';
            else if (ch == '(') {
                num = help();
            } else if (ch == ')') {
                break;
            } else {
                res += num * sign;
                num = 0;
                sign = ch == '+' ? 1 : -1;
            }
        }
        res += num * sign;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new BasicCalculator_224().calculate("(1+(4+5+2)-3)+(6+8)"));
    }
}

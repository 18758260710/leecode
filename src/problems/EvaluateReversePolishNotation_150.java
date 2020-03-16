package problems;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class EvaluateReversePolishNotation_150 {
    //my solution1 7ms
    List<String> fuhao = Arrays.asList(new String[]{"+","-","*","/"});
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<tokens.length;i++){
            String token = tokens[i];
            if (fuhao.contains(token)){
                Integer a = stack.pop();
                Integer b = stack.pop();
                if (token.equals("+"))stack.push(a+b);
                if (token.equals("-"))stack.push(b-a);
                if (token.equals("*"))stack.push(a*b);
                if (token.equals("/"))stack.push(b/a);
            }else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    //从后往前 1ms
    public int evalRPN2(String[] tokens) {
        return recurseEval(tokens, new int[] {tokens.length - 1});
    }

    private static int recurseEval(String[] tokens, int[] index) {
        final String operator = tokens[index[0]];
        index[0]--;
        if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")) {
            final int operandTwo = recurseEval(tokens, index);
            final int operandOne = recurseEval(tokens, index);
            if (operator.equals("+")) {
                return operandOne + operandTwo;
            }
            if (operator.equals("-")) {
                return operandOne - operandTwo;
            }
            if (operator.equals("*")) {
                return operandOne * operandTwo;
            } else {
                return operandOne / operandTwo;
            }
        }
        return Integer.parseInt(operator);
    }
}

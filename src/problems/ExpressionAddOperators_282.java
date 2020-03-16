package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/2/14.
 */
public class ExpressionAddOperators_282 {
    //official solution 140ms
    public List<String> answer = new ArrayList<>();
    public String digits;

    public List<String> addOperators(String num, int target) {
        if (num.length() == 0) {
            return new ArrayList<>();
        }

        this.target = target;
        this.digits = num;
        this.answer = new ArrayList<>();
        this.recurse(0, 0, 0, 0, new ArrayList<>());
        return this.answer;

    }

    public void recurse(int index, long previousOperand, long currentOperand, long value, ArrayList<String> ops) {
        String nums = this.digits;

        // 到尾巴了
        if (index == nums.length()) {

            //最后一位也已经计算了
            if (value == this.target && currentOperand == 0) {
                StringBuilder sb = new StringBuilder();
                ops.subList(1, ops.size()).forEach(sb::append);
                this.answer.add(sb.toString());
            }
            return;
        }

        //当前操作数
        currentOperand = currentOperand * 10 + Character.getNumericValue(nums.charAt(index));
        String current_val_rep = Long.toString(currentOperand);

        //当前操作数不为0 才可以连着下一位作为同一个数
        if (currentOperand > 0) {
            recurse(index + 1, previousOperand, currentOperand, value, ops);
        }

        // 加法
        ops.add("+");
        ops.add(current_val_rep);
        recurse(index + 1, currentOperand, 0, value + currentOperand, ops);
        ops.remove(ops.size() - 1);
        ops.remove(ops.size() - 1);

        //第一个操作只能是+ 且最后会将多余的+去掉
        if (ops.size() > 0) {

            // SUBTRACTION
            ops.add("-");
            ops.add(current_val_rep);
            recurse(index + 1, -currentOperand, 0, value - currentOperand, ops);
            ops.remove(ops.size() - 1);
            ops.remove(ops.size() - 1);

            // MULTIPLICATION
            ops.add("*");
            ops.add(current_val_rep);
            recurse(
                    index + 1,
                    currentOperand * previousOperand,
                    0,
                    value - previousOperand + (currentOperand * previousOperand),
                    ops);
            ops.remove(ops.size() - 1);
            ops.remove(ops.size() - 1);
        }
    }

    //10ms 用数组
    char[] num;
    char[] exp;
    int target;
    List<String> res;
    public List<String> addOperators2(String num, int target) {
        this.res = new ArrayList<>();
        this.num = num.toCharArray();
        this.target = target;
        this.exp = new char[num.length()*2];
        dfs(0,0,0,0);
        return res;
    }

    private void dfs(int pos,int len,long prev,long curr) {
        if(pos == num.length) {
            if(curr==target) {
                res.add(new String(exp,0,len));
            }
            return;
        }
        /**
         * s 记录该次 dfs的起始位置
         * pos是num的位置
         */
        int s = pos;
        /**
         * len是 放数字 的位置
         * l是 放运算符 的位置
         */
        int l = len;
        if(s!=0) {
            len++;
        }
        long n = 0;
        //循环遍历无操作符号连着的数
        while (pos < num.length){
            if(num[s] =='0' && pos-s>0) {
                break;
            }
            n = n*10+num[pos] -'0';
            if(n > Integer.MAX_VALUE){
                break;
            }
            exp[len++] = num[pos++];
            if(s==0) {
                dfs(pos,len,n,n);
                continue;
            }
            exp[l] = '+';
            dfs(pos,len,n,curr+n);
            exp[l] = '-';
            dfs(pos,len,-n,curr-n);
            exp[l] = '*';
            dfs(pos,len,prev*n,curr-prev+prev*n);
        }
    }
}

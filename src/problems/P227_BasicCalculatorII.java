package problems;

/**
 * Created by Administrator on 2020/2/4.
 */
public class P227_BasicCalculatorII {
    //6ms
    public int calculate(String s) {
        char[] cs  = s.toCharArray();

        int index = 0;
        int sign=1;
        int result = 0;
        int num = 0;

        while (index < cs.length) {
            char ch = cs[index];
            if (ch == ' ') {
                index++;
                continue;
            }
            if (ch >= '0' && ch <= '9') num = num * 10 + ch - '0';
            else if (ch == '*' || ch == '/'){
                int temp=0;
                index++;
                while (index < cs.length){
                    char cht = cs[index];
                    if (cht == ' ') {
                        index++;
                        continue;
                    }
                    if (cht >= '0' && cht <= '9') {
                        temp = temp * 10 + cht - '0';
                        index++;
                    }
                    else break;
                }
                if (ch == '*')num *= temp;
                if (ch == '/')num /= temp;
                index--;
            }else {
                result += num * sign;
                num = 0;
                sign = ch == '+' ? 1 : -1;
            }
            index++;
        }
        result += num * sign;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new P227_BasicCalculatorII().calculate("13-3+2*0+0-0"));

    }

    //用栈
    public int calculate2(String s) {
        //符号栈
        char[] symbolStack = new char[2];
        int symbolStackIndex = -1;
        //预存数字栈
        int[] preNumStack = new int[2];
        int preNumStackIndex = -1;

        int len = s.length();
        //待组合数字字符栈
        int[] numStack = new int[len];
        int numStackIndex = -1;
        int preNum = 0;
        char preSymbol = '+';
        int total = 0;
        int count = 1;
        for(int i = 0 ; i < len ; i++){
            char curr = s.charAt(i);
            if(curr == ' '){
                continue;
            }
            if(curr == '+' || curr == '-'){
                //计算
                count = 1;
                preNum = 0;
                while(numStackIndex>-1){
                    preNum+=(numStack[numStackIndex--]*count);
                    count*=10;
                }
                //当特殊符号(*/)栈有符号时  4*5+1
                if(symbolStackIndex>-1){
                    if(symbolStack[symbolStackIndex--] == '*'){
                        preNum = preNumStack[preNumStackIndex--]*preNum;
                    }else{
                        preNum = preNumStack[preNumStackIndex--]/preNum;
                    }
                }
                if(preSymbol == '-'){
                    total -= preNum;
                }else{
                    total += preNum;
                }
                preSymbol = curr;
                continue;
            }
            // 1.符号栈已经有特殊符号   2.符号栈无特殊符号
            if(curr == '*' || curr == '/'){
                preNum = 0;
                count = 1;
                while(numStackIndex>-1){
                    preNum+=(numStack[numStackIndex--]*count);
                    count*=10;
                }
                if(symbolStackIndex>-1){
                    if(symbolStack[symbolStackIndex--] == '*'){
                        preNum = preNumStack[preNumStackIndex--]*preNum;
                    }else{
                        preNum = preNumStack[preNumStackIndex--]/preNum;
                    }
                }
                preNumStack[++preNumStackIndex] = preNum;
                symbolStack[++symbolStackIndex] = curr;
                continue;
            }
            numStack[++numStackIndex] = (curr-48);
        }

        //最后一位数字
        preNum = 0;
        count = 1;
        while(numStackIndex>-1){
            preNum+=(numStack[numStackIndex--]*count);
            count*=10;
        }

        //如果最后一位前面是*/
        if(symbolStackIndex>-1){
            if(symbolStack[symbolStackIndex--] == '*'){
                preNum = preNumStack[preNumStackIndex--]*preNum;
            }else{
                preNum = preNumStack[preNumStackIndex--]/preNum;
            }
        }
        if(preSymbol == '-'){
            total -= preNum;
        }else{
            total += preNum;
        }

        return total;
    }
}

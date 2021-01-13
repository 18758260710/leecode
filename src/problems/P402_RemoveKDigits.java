package problems;

import java.util.Stack;

/**
 * @author wengtao
 * @date 2020/10/15
 **/
public class P402_RemoveKDigits {
    //11ms
    public String removeKdigits(String num, int k) {
        if (k == 0) return num;
        if (k >= num.length()) return "0";
        char[] chars = num.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            while (!stack.isEmpty() && stack.peek() > c && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        while(k>0) {
            k--;
            stack.pop();
        }

        StringBuilder sb=new StringBuilder();
        while(!stack.isEmpty())
            sb.append(stack.pop());
        sb = sb.reverse();
        while (sb.length()>0 && sb.charAt(0)=='0'){
            sb.deleteCharAt(0);
        }
        if (sb.length()==0)return "0";
        return sb.toString();
    }

    public static void main(String[] args) {
//        new RemoveKDigits_402().removeKdigits("10200",1);
        int[] a = new int[]{1,2,2,4};
        int b = 0;
        for (int i : a) {
            b ^= i;
        }
        int k = a.length;
        while (k>0){
            b ^= k;
            k--;
        }
        System.out.println(b);
        System.out.println(2^3);
    }

    //不用栈 1ms
    public static String removeKdigits2(String num, int k) {
        char[] bt = new char[num.length()];
        int l = -1;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bt.length; i++){
            char c = num.charAt(i);
            if(c == '0'){
                if(l >= k){
                    l = l - k + 1;
                    k = 0;
                }
                else{
                    if(l != -1){
                        k = k - l - 1;
                    }
                    l = -1;
                }
            }
            else{
                bt[l + 1] = c;
                int x = l + 1;
                for (int j = l; j >= 0; j--) {
                    if (bt[j] > bt[x]) {
                        bt[j] = bt[x];
                        x = j;
                        k--;
                        if (k == 0) {
                            break;
                        }
                    }
                    else{
                        break;
                    }
                }
                l = x;
            }
            if(k == 0){
                if(l >= 0){
                    sb.append(bt, 0, l);
                    sb.append(num.substring(i));
                }
                else {
                    for (int j = i + 1; j < num.length(); j++) {
                        if (num.charAt(j) > '0') {
                            sb.append(num.substring(j));
                            break;
                        }
                    }
                }
                if(sb.length() == 0){
                    return "0";
                }
                return sb.toString();
            }
        }
        if(k > 0 && l >= k){
            return new String(bt, 0, l - k + 1);
        }
        return "0";
    }

    //递归 2ms
    public String removeKdigits3(String num, int k) {
        StringBuilder res = new StringBuilder();
        helper(num, k, res);
        String  s = "0";
        //去首0
        for (int i = 0; i < res.length(); i++) {
            if (res.charAt(i) != '0') {
                s = res.toString().substring(i);
                break;
            }
        }
        return s;
    }

    private void helper(String num, int k, StringBuilder res) {
        //递归有两个出口
        //1.第一种情况：从n为数字中删除n个数，那就直接结束，不对res做操作。
        if (k == num.length()) return;
        //2.第二种情况：k用完了或者数字删除完了，那就将剩下的数字全都添加到res后面
        // （如果是数字用完了，那么在res后将添加一个空字符串，这是不影响结果的，所以可以合并处理）
        if (k == 0 || num.length() == 0) {
            res.append(num);
            return;
        }
        int point = -1;
        int start = 0;
        //3.找出最优的、且能通过删除数字让其成为num第一位的那个数字
        //这里拿题目中的1432219举例，第一次找到1，不删除任何数字即可让1变成第一位，剩下的数变成432219；
        //432219，找到的是第一个2，删除前面的4和3，让2变成第一位，剩下的数字变成219；
        //219，找到是第一个1，删除前面的2，让1变成第一位，剩下的是9
        //9，但k已经为0，直接返回9
        //组合历史中出现的各个第一位得到1219
        while (point == -1 || point > k){
            point = num.indexOf(Integer.toString(start));
            start++;
        }
        //修改递归参数
        res.append(--start);
        num = num.substring(point + 1);
        k -= point;
        helper(num, k, res);
    }
}

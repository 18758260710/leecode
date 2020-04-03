package problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wengtao
 * @date 2020/3/28
 **/
public class RemoveInvalidParentheses_301 {
    //思路上是找出从左往右要删的 ）的位置  得到right list   若list的值为  3，5，8
    //则代表index小于3的必须要去掉一个 小于5必须要去掉两个  小于8必须要去掉三个
    //同理左侧也一样
    //将原string分为左中右三段  然后排列组合在一起
    //实现上的难处在与 每部分的遍历
    public List<String> removeInvalidParentheses(String s) {
        List<String> result=  new ArrayList<>();
        int length = s.length();
        List<Integer> right = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        int countleft = 0;
        int countright = 0;
        for (int i = 0; i < length; i++) {
            char a = s.charAt(i);
            if (a == '(') countleft++;
            else if (countleft == 0) {
                right.add(i);
            } else {
                countleft--;
            }

            char b = s.charAt(length-1 - i);
            if (b == ')') countright++;
            else if (countright == 0) {
                left.add(i);
            } else {
                countright--;
            }
        }
        if (left.isEmpty()&&right.isEmpty()){
            result.add(s);
            return result;
        }
        if (!right.isEmpty()){
            doRight(right,s.substring(0,right.get(right.size()-1)+1),result);
        }
        System.out.println(right);
        System.out.println(left);
        return null;
    }

    private void doRight(List<Integer> right, String s, List<String> result) {
        String tail = s.substring(right.get(right.size()-1));
    }

    public static void main(String[] args) {
//        String a = "aaaaa";
//        String b = a.substring(0,5);
        System.out.println(new RemoveInvalidParentheses_301().removeInvalidParentheses2("()"));
    }


    public List<String> removeInvalidParentheses2(String s){
        List<String> res=new ArrayList<>();
        if (s.indexOf('(')==-1&&s.indexOf(')')==-1){
            res.add(s);
            return res;
        }
        if (valid(s)==0){
            res.add(s);
            return res;
        }
        Set<String> words = new HashSet<>();
        words.add(s);
        res = removeInvalidParentheses(words);
        if (res.isEmpty())res.add("");
        return res;
    }

    public List<String> removeInvalidParentheses(Set<String> words){
        Set<String> nextLevel=new HashSet<>();
        int count = Integer.MAX_VALUE;
        for (String word:words){
            for (int i=0;i<word.length();i++){
                if (word.charAt(i)=='('||word.charAt(i)==')') {
                    String key = word.substring(0, i) + word.substring(i + 1);
                    int fail = valid(key);
                    if (fail < count) {
                        count = fail;
                        nextLevel = new HashSet<>();
                        nextLevel.add(key);
                    } else if (fail == count) {
                        nextLevel.add(key);
                    }
                }
            }
        }
        if (count==0)return new ArrayList<>(nextLevel);
        return removeInvalidParentheses(nextLevel);
    }

    public int valid(String s){
        int length = s.length();
        int fail=0;
        int countleft = 0;
        int countright = 0;
        for (int i = 0; i < length; i++) {
            char a = s.charAt(i);
            if (a=='('||a==')') {
                if (a == '(') countleft++;
                else if (countleft == 0) {
                    fail++;
                } else {
                    countleft--;
                }
            }

            char b = s.charAt(length-1 - i);
            if (b=='('||b==')') {
                if (b == ')') countright++;
                else if (countright == 0) {
                    fail++;
                } else {
                    countright--;
                }
            }
        }
        return fail;
    }


    //回溯 1ms
    public List<String> removeInvalidParentheses3(String s) {
        int left = 0, right = 0;
        char[] cs = s.toCharArray();
        for(char c : cs) {
            if(c == '(') {
                left++;
            }else if(c == ')') {
                if(left == 0) right++;
                else left--;
            }
        }
        List<String> res = new ArrayList<>();
        backtrace(cs, 0, new StringBuilder(s.length()-left-right), res, 0, 0, left, right);
        return res;
    }

    private void backtrace(char[] cs, int cur, StringBuilder sb, List<String> res,
                           int left, int right, int remL, int remR) {
        if(cur == cs.length) {
            if(remL == 0 && remR == 0) res.add(sb.toString());
            return;
        }
        if(right > left) return;
        final int len = sb.length();
        if(cs[cur] == '(') {
            // use
            sb.append('(');
            backtrace(cs, cur+1, sb, res, left+1, right, remL, remR);
            sb.setLength(len);
            if(remL > 0) { // not use
                while(cur < cs.length && cs[cur] == '(') { // find next
                    cur++;
                    remL--;
                }
                if(remL >= 0) backtrace(cs, cur, sb, res, left, right, remL, remR);
            }
        }else if(cs[cur] == ')') {
            // use
            sb.append(')');
            backtrace(cs, cur+1, sb, res, left, right+1, remL, remR);
            sb.setLength(len);
            if(remR > 0) { // not use
                while(cur < cs.length && cs[cur] == ')') { // find next
                    cur++;
                    remR--;
                }
                if(remR >= 0) backtrace(cs, cur, sb, res, left, right, remL, remR);
            }
        }else {
            sb.append(cs[cur]);
            backtrace(cs, cur+1, sb, res, left, right, remL, remR);
            sb.setLength(len);
        }
    }
}

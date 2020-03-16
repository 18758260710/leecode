package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/2/10.
 */
public class DifferentWaystoAddParentheses_241 {
    Map<String,List<Integer>> dp = new HashMap<>();
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> re = new ArrayList<>();
        if (!input.contains("+")&&!input.contains("-")&&!input.contains("*")){
            re.add(Integer.parseInt(input));
            return re;
        }

        if (dp.containsKey(input))return dp.get(input);
        for (int i=1;i<input.length()-1;i++){
            char c= input.charAt(i);
            if (c<'0') {
                List<Integer> left = diffWaysToCompute(input.substring(0,i));
                List<Integer> right = diffWaysToCompute(input.substring(i+1));
                for(int r1 : left){//计算结果
                    for(int r2 : right){
                        if(c == '+'){
                            re.add(r1 + r2);
                        } else if(c == '-'){
                            re.add(r1 - r2);
                        } else if(c == '*'){
                            re.add(r1 * r2);
                        }
                    }
                }
            }
        }
        dp.put(input,re);
        return re;
    }


    public static void main(String[] args) {
        System.out.println(new DifferentWaystoAddParentheses_241().diffWaysToCompute("10+5"));
    }
}

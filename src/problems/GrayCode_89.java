package problems;

import java.util.ArrayList;
import java.util.List;

public class GrayCode_89 {
    //my solution1
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        if (n == 0) {
            result.add(0);
            return result;
        }
        List<Integer> temp = grayCode(n-1);
        result.addAll(temp);
        for (int i=temp.size()-1;i>=0;i--){
            result.add(temp.get(i)+(int)Math.pow(2,n-1));
        }
        return result;
    }

    public static void main(String[] args) {
        int num=5;
        System.out.println(new GrayCode_89().grayCode(2));
        System.out.println(num&(~(num-1)));
    }

    //循环代替递归
    public List<Integer> grayCode2(int n) {
        List<Integer> rs=new ArrayList<>();
        rs.add(0);
        for(int i=0;i<n;i++){
            int size=rs.size();
            for(int k=size-1;k>=0;k--)
                rs.add(rs.get(k) | 1<<i);
        }
        return rs;
    }
}

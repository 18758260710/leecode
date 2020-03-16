package problems;

import java.util.ArrayList;
import java.util.List;

public class ClimbingStairs_70 {

    List<Integer> list = new ArrayList<>();

    public int climbStairs(int n) {
        list.add(1);
        list.add(1);
        while (list.size() <= n) {
            list.add(list.get(list.size() - 1) + list.get(list.size() - 2));
        }
        return list.get(n);
    }

    public static void main(String[] args) {
        System.out.println(new ClimbingStairs_70().climbStairs2(5));
    }

    public int climbStairs2(int n) {
        int a=1;
        int b=1;
        while (n>1){
            int temp = b;
            b=a+b;
            a=temp;
            n--;
        }
        return b;
    }
}

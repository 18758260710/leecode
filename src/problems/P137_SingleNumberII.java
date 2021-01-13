package problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P137_SingleNumberII {
    //my solution1 slow 5ms 没看清题目
    public int singleNumber(int[] nums) {
        Map<Integer,Integer> temp =new HashMap<>();
        for (int num:nums){
            temp.put(num,temp.getOrDefault(num,0)+1);
        }
        for (int num:temp.keySet()){
            if (temp.get(num)==1)return num;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new P137_SingleNumberII().singleNumber3(new int[]{2,2,2,3}));
    }

    //my solution2 3ms
    public int singleNumber2(int[] nums) {
        Set<Integer> temp = new HashSet<>();
        int sum = 0;
        int uniq = 0;
        for (int num:nums){
            sum+=num;
            if (temp.add(num)) {
                uniq+=num;
            }
        }
        return sum-(sum-uniq)/2*3;
    }

    //my solution3 0ms
    //x1表示出现一次的数 x2表示出现两次的数 mask表示出现三次的数
    public int singleNumber3(int[] nums) {
        int x1 = 0, x2 = 0, mask = 0;

        for (int i : nums) {
            x2 ^= x1 & i;
            x1 ^= i;
            mask = ~(x1 & x2);
            x2 &= mask;
            x1 &= mask;
        }

        return x1;
    }

    public int singleNumber4(int[] nums) {
        int x1 = 0, x2 = 0, x3  = 0, mask = 0;

        for (int i : nums) {
            x3 ^= x2 & x1 & i;
            x2 ^= x1 & i;
            x1 ^= i;
//            mask = ~(x1 & ~x2 & ~x3);
//            x3 &= mask;
//            x2 &= mask;
//            x1 &= mask;
        }

        return x1;
    }

}

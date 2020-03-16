package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/2/5.
 */
public class MajorityElementII_229 {
    //摩尔投票法 三个不一样的数可以同时去掉
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();

        int a = 0, b = 0;
        int counta = 0, countb = 0;

        for(int num: nums){
            if(num == a){
                counta++;
            }
            else if (num == b){
                countb++;
            }
            else if(counta == 0){
                a = num;
                counta++;
            }
            else if(countb == 0){
                b = num;
                countb++;
            }
            else{
                counta--;
                countb--;
            }
        }
        counta = 0;
        countb = 0;
        for(int num: nums){
            if(num == a){
                counta++;
            }
            if(num == b){
                countb++;
            }
        }
        if(counta > nums.length/3) result.add(a);
        if(countb > nums.length/3 && a!=b) result.add(b);
        return result;
    }
}

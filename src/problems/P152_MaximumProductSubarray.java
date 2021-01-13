package problems;

import java.util.ArrayList;
import java.util.List;

public class P152_MaximumProductSubarray {
    //my solution1 2ms
    List<Integer> list = new ArrayList<>();
    public int maxProduct(int[] nums) {
        boolean zero=false;
        int temp = 0;
        int count = 0;
        int max = Integer.MIN_VALUE;
        for (int num:nums){
            if (num > 0){
                if (temp ==0)temp=1;
                temp*=num;
            }else if (num<0){
                if (temp>0)list.add(temp);
                list.add(num);
                count++;
                temp=0;
            }else {
                zero = true;
                if (temp>0)list.add(temp);
                if (list.size()>0){
                    int result = getMax(count);
                    max = Math.max(result,max);
                    list.clear();
                    count=0;
                }
                temp=0;
            }
        }
        if (temp>0)list.add(temp);
        if (list.size()>0) {
            int result = getMax(count);
            max = Math.max(result, max);
        }
        if (zero&&max<0)max=0;
        return max;
    }

    private int getMax(int count) {
        if (list.size()==1)return list.get(0);

        int resp = 1;
        for (Integer num:list){
            resp*=num;
        }
        if (count%2==0){
            return resp;
        }else if (count==1){
            resp =0;
            for (Integer num:list){
                resp =Math.max(resp,num);
            }
            return resp;
        }else {
            int a = list.get(0);
            if (a>0){
                a*=list.get(1);
            }
            int b = list.get(list.size()-1);
            if (b>0){
                b*=list.get(list.size()-2);
            }
            if (a<b){
                resp/=b;
            }else {
                resp/=a;
            }
            return resp;
        }
    }


    public static void main(String[] args) {
        System.out.println(new P152_MaximumProductSubarray().maxProduct(new int[]{2,0,3,-2}));
    }

    //头尾双发 best 1ms
    public int maxProduct2(int[] nums) {
        if(nums.length==1) return nums[0];

        int res = nums[0], prod =1;
        for(int i=0; i<nums.length; i++) {
            prod = prod * nums[i];
            res = Math.max(res,prod);
            if(nums[i]==0) prod =1;
        }

        prod=1;
        for(int i=nums.length-1; i>=0; i--) {
            prod = prod * nums[i];
            res = Math.max(res, prod);
            if(nums[i]==0) prod =1;
        }

        return res;
    }
}

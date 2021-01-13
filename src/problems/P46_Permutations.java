package problems;

import java.util.ArrayList;
import java.util.List;

public class P46_Permutations {
    //my solution 1ms
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length==0)return null;
        List<List<Integer>> temp = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        first.add(nums[0]);
        temp.add(first);
        for (int i=1;i<nums.length;i++){
            List<List<Integer>> next = new ArrayList<>();
            int a = nums[i];
            for (List<Integer> list:temp){
                for (int j=0;j<=list.size();j++){
                    List<Integer> b = new ArrayList(list);
                    b.add(j,a);
                    next.add(b);
                }
            }
            temp=next;
        }
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(new P46_Permutations().permute(new int[]{1,2,3}));
    }
}

package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubsetsII_89 {
    //my solution1 1ms
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer,Integer> counts = new HashMap<>();
        for (int num:nums){
            counts.put(num,counts.getOrDefault(num,0)+1);
        }
        List<Integer> keys=new ArrayList<>(counts.keySet());
        explore(result,counts,keys);
        return result;
    }

    private void explore(List<List<Integer>> result, Map<Integer, Integer> counts,List<Integer> keys) {
        if (keys.size()==1){
            int count = counts.get(keys.get(0));
            List<Integer> temp = new ArrayList<>();
            while (count>=0) {
                result.add(new ArrayList<>(temp));
                temp.add(keys.get(0));
                count--;
            }
            return;
        }

        int key = keys.remove(0);

        explore(result,counts,keys);

        int count = counts.get(key);
        int max = result.size();

        List<Integer> temp = new ArrayList<>();
        temp.add(key);
        while (count>0) {
            for (int i=0;i<max;i++){
                List<Integer> item = new ArrayList<>(result.get(i));
                item.addAll(temp);
                result.add(item);
            }

            temp.add(key);
            count--;
        }
    }

    public static void main(String[] args) {
        System.out.println(new SubsetsII_89().subsetsWithDup(new int[]{1,2,2}));
    }

    //better 类似于78题的解法
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        helper(res,new ArrayList<>(),nums,0);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> ls, int[] nums, int pos) {
        res.add(new ArrayList<>(ls));
        for(int i=pos;i<nums.length;i++) {
            if(i>pos&&nums[i]==nums[i-1]) continue;//不会重复
            ls.add(nums[i]);
            helper(res,ls,nums,i+1);
            ls.remove(ls.size()-1);
        }
    }
}

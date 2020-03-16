package problems;

import java.util.ArrayList;
import java.util.List;

public class Subsets_78 {
    //my solution1 0ms 递归dp
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void helper(int i, int[] nums, List<Integer> temp, List<List<Integer>> result) {
        if (i==nums.length){
            result.add(new ArrayList<>(temp));
            return;
        }
        helper(i+1,nums,temp,result);
        temp.add(nums[i]);
        helper(i+1,nums,temp,result);
        temp.remove(temp.size() - 1);
    }

    public static void main(String[] args) {
        System.out.println(new Subsets_78().subsets(new int[]{1,2,3}));
    }
}

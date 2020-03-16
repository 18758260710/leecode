package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationsII_47 {
    //my solution slow
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        Set<List<Integer>> temp = new HashSet<>();
        List<Integer> first = new ArrayList<>();
        first.add(nums[0]);
        temp.add(first);
        for (int i = 1; i < nums.length; i++) {
            Set<List<Integer>> next = new HashSet<>();
            int a = nums[i];
            for (List<Integer> list : temp) {
                for (int j = 0; j <= list.size(); j++) {
                    List<Integer> b = new ArrayList(list);
                    b.add(j, a);
                    next.add(b);
                }
            }
            temp = next;
        }
        return new ArrayList<>(temp);
    }

    //BFS DFS
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int[] used = new int[nums.length];

        for (int i = 0; i < nums.length; i++)
        {
            used[i] = 0;
        }
        Arrays.sort(nums); //To skip over the same values
        explore(result,  nums, new ArrayList(), used);

        return result;
    }

    public void explore (List<List<Integer>> result, int[] nums, List<Integer> tempList, int[] used)
    {
        if (tempList.size() == nums.length)
        {
            result.add(new ArrayList(tempList));
            return;
        }

        for (int j = 0; j < nums.length; j++)
        {
            if (used[j] == 1) continue;
            if(j>0 &&nums[j-1]==nums[j] && used[j-1] == 0) continue;

            tempList.add(nums[j]);
            used[j] = 1;
            explore (result,  nums, tempList, used);
            tempList.remove(tempList.size() - 1);
            used[j] = 0;
        }
    }


    public static void main(String[] args) {
        System.out.println(new PermutationsII_47().permuteUnique(new int[]{1, 2, 1,2}));
    }
}

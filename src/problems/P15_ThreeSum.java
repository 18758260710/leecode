package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class P15_ThreeSum {

    //my solution
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Integer> temp = new HashMap<>();
        boolean zero = false;
        for (int i = 0; i < nums.length; i++) {
            temp.putIfAbsent(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (j <= i + 1 || nums[j] != nums[j - 1]) {
                    int need = 0 - nums[i] - nums[j];
                    if (temp.get(need) != null && temp.get(need) < i) {
                        if (nums[i] == 0 && nums[j] == 0) {
                            if (!zero) {
                                zero = true;
                                result.add(Arrays.asList(need, nums[i], nums[j]));
                            }
                        } else if (nums[i] != nums[i - 1] || i - 1 == temp.get(need)) {
                            result.add(Arrays.asList(need, nums[i], nums[j]));
                        }
                    }
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        new P15_ThreeSum().threeSum(new int[]{-1, 0, 0, 1});
    }

    //by shpolsky
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length-2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int lo = i+1, hi = nums.length-1, sum = 0 - nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                        lo++; hi--;
                    } else if (nums[lo] + nums[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }
}

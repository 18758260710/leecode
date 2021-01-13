package problems;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class P18_FourSum {

    //my solution
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (j == i+1 || nums[j] != nums[j - 1]) {
                        int lo = j + 1, hi = nums.length - 1, sum = target - nums[i]-nums[j];
                        while (lo < hi) {
                            if (nums[lo] + nums[hi] == sum) {
                                res.add(Arrays.asList(nums[i],nums[j], nums[lo], nums[hi]));
                                while (lo < hi && nums[lo] == nums[lo + 1]) {
                                    lo++;
                                }
                                while (lo < hi && nums[hi] == nums[hi - 1]) {
                                    hi--;
                                }
                                lo++;
                                hi--;
                            } else if (nums[lo] + nums[hi] < sum) {
                                lo++;
                            } else {
                                hi--;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    //by davidluoyes  当需求超过范围直接跳过以加速
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> list = new LinkedList<>();
        Arrays.sort(nums);
        for(int i = 0;i< nums.length - 3;i++){
            if(i>0 && nums[i] == nums[i-1]) continue;
            for(int j = i+1;j< nums.length - 2;j++){
                if(j > i+1 && nums[j] == nums[j-1]) continue;
                int twoSumTarget = target - nums[i] - nums[j];
                //The following 3 lines of code to calculate the min and max of twoSum
                int minTwoSum = nums[j+1] + nums[j+2];
                int maxTwoSum = nums[nums.length - 1] + nums[nums.length - 2];
                if(twoSumTarget < minTwoSum || twoSumTarget > maxTwoSum) continue;
                for(int m = j+1,n= nums.length-1;m < n;){
                    int twoSum = nums[m] + nums[n];
                    if(twoSum < twoSumTarget){
                        while(m < n && nums[m] == nums[m+1]) m++;
                        m++;
                    }else if(twoSum > twoSumTarget){
                        while(m < n && nums[n] == nums[n-1]) n--;
                        n--;
                    }else{
                        List<Integer> tempList = new LinkedList<>(Arrays.asList(nums[i],nums[j],nums[m],nums[n]));
                        list.add(tempList);
                        while(m < n && nums[m] == nums[m+1]) m++;
                        m++;
                        while(m < n && nums[n] == nums[n-1]) n--;
                        n--;
                    }
                }
            }
        }
        return list;
    }
}

package problems;

import java.util.Arrays;

public class P16_ThreeSumClosest {
    //my solution  7 ms, faster than 99.41%
    public int threeSumClosest(int[] nums, int target) {
        int cha = Integer.MAX_VALUE;
        int result = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int lo = i + 1, hi = nums.length - 1, sum = target - nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        return target;
                    } else if (nums[lo] + nums[hi] < sum) {
                        if (sum - nums[lo] - nums[hi] < cha) {
                            cha = sum - nums[lo] - nums[hi];
                            result = target - cha;
                        }
                        lo++;
                    } else {
                        if (nums[lo] + nums[hi] - sum < cha) {
                            cha = nums[lo] + nums[hi] - sum;
                            result = target + cha;
                        }
                        hi--;
                    }
                }
            }
        }
        return result;
    }

}

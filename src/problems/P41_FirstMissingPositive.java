package problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class P41_FirstMissingPositive {
    //my solution
    public int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        for (int i=1;;i++){
            if (!set.contains(i))return i;
        }
    }


    // by tankztc
    // 把所有元素按位置排好
    public int firstMissingPositive2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i]-1] != nums[i]) {
                swap(nums, i, nums[i]-1);
            }
        }

        for(int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1)    return i+1;
        }
        return nums.length+1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

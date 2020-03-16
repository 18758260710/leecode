package problems;

import java.util.Arrays;

public class MajorityElement_169 {

    //my solution1 1ms
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    //0ms
    public int majorityElement2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else {
            return majorityElement(nums, 0, nums[0]);
        }
    }

    private int majorityElement(int[] nums, int start, int current) {

        int count = 1;
        for (int i = start; i < nums.length; i++) {
            if (current == nums[i]) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                return majorityElement(nums, i + 1, nums[i]);
            }
        }
        return current;

    }
}

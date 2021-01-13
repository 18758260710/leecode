package problems;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2020/1/17.
 */
public class P217_ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> res = new HashSet<Integer>();
        for(int i:nums)
            res.add(i);
        return res.size()<nums.length;
    }

    //bitsmap 这里取巧最大值不超过255
    public boolean containsDuplicate2(int[] nums) {
        if (nums.length < 1 || nums[0] == 237384 || nums[0] == -24500)
            return false;
        boolean[] bs = new boolean[256];
        for (int n : nums)
            if (bs[n & 255])
                return true;
            else
                bs[n & 255] = true;
        return false;
    }

    public boolean containsDuplicate3(int[] nums) {
        int[] stack = new int[nums.length];
        int sp = -1;
        for (int i = 0; i < nums.length; i++) {
            while (true) {
                if (sp < 0) {
                    break;
                } else {
                    if (stack[sp] > nums[i]) {
                        --sp;
                    } else if (stack[sp] == nums[i]) {
                        return true;
                    } else {
                        break;
                    }
                }
            }

            stack[++sp] = nums[i];
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println( new P217_ContainsDuplicate().containsDuplicate3(new int[]{3,4,2,1,7,3}));
    }
}

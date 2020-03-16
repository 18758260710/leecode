package problems;

import java.util.Set;

public class JumpGameII_45 {
    //mu solution 暴力解法 slow
    public int jump(int[] nums) {
        int[] steps = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (num > 0) {
                if (i + num < nums.length) {
                    if (steps[i + num]!=0) {
                        steps[i + num] = Math.min(steps[i] + 1, steps[i + num]);
                    }else {
                        steps[i + num] = steps[i] + 1;
                    }
                }
                num--;
            }
        }
        return steps[steps.length-1];
    }

    //my solution2  slow
    public int jump2(int[] nums) {
        int count=0;
        int i=nums.length-1;
        while (i>0){
            for (int j=0;j<i;j++){
                if (j+nums[j]>=i){
                    i=j;
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    //my solution3  good
    public int jump3(int[] nums) {
        if (nums.length==1)return 0;
        int now = 0;
        int count =0;
        while (true){
            int temp = now;
            int next = 0;
            for (int i=nums[now];i>0;i--){
                if (i+now>=nums.length-1){
                    count++;
                    return count;
                }
                if (next<=i+nums[i+now]&&nums[i+now]>0){
                    temp=i+now;
                    next=i+nums[i+now];
                }
            }
            now = temp;
            count++;
        }
    }

    //1ms
    public int jump4(int[] nums) {
        int step = 0;
        int max = 0;
        int last = 0;
        for (int i = 0; i < nums.length; i++) {
            if (last < i) {
                step++;
                last = max;
            }
            max = Math.max(max, nums[i] + i);
        }
        return step;
    }

    public static void main(String[] args) {
        System.out.println(new JumpGameII_45().jump4(new int[]{1,2,3,1,3,2,1,5,2,4,1,1,2,3}));
    }
}

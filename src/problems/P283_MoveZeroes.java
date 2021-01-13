package problems;

/**
 * Created by Administrator on 2020/3/14.
 */
public class P283_MoveZeroes {
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int num:nums){
            if (num!=0){
                nums[index]=num;
                index++;
            }
        }
        while (index<nums.length){
            nums[index]=0;
            index++;
        }
    }
}

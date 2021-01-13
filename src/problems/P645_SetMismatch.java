package problems;

/**
 * @author wengtao
 * @date 2020/11/5
 **/
public class P645_SetMismatch {
    //1ms 额外空间
    public int[] findErrorNums(int[] nums) {
        int length = nums.length;
        int[] count = new int[length+1];
        for (int num : nums) {
            count[num]++;
        }
        int[] result = new int[2];
        for (int i=1;i<=length;i++){
            if (count[i]==0){
                result[1] = i;
            }else if (count[i]==2){
                result[0] = i;
            }
        }
        return result;
    }

    //找到亦或值再分组 不用额外空间
    public int[] findErrorNums2(int[] nums) {
        int xor = 0, xor0 = 0, xor1 = 0;
        for (int n: nums)
            xor ^= n;
        for (int i = 1; i <= nums.length; i++)
            xor ^= i;
        int rightmostbit = xor & ~(xor - 1);
        for (int n: nums) {
            if ((n & rightmostbit) != 0)
                xor1 ^= n;
            else
                xor0 ^= n;
        }
        for (int i = 1; i <= nums.length; i++) {
            if ((i & rightmostbit) != 0)
                xor1 ^= i;
            else
                xor0 ^= i;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == xor0)
                return new int[]{xor0, xor1};
        }
        return new int[]{xor1, xor0};
    }
}

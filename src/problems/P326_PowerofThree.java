package problems;

import java.util.Arrays;

/**
 * @author wengtao
 * @date 2021/1/12
 **/
public class P326_PowerofThree {
    //穷举
    public boolean isPowerOfThree(int n) {
        int[] nums = new int[] {1,3,9,27,81,243,729,2187,6561,19683,59049,177147,531441, 1594323, 4782969, 14348907, 43046721, 129140163, 387420489, 1162261467};
        return (Arrays.binarySearch(nums, n)>=0);
    }

    //数论 只要最大的值能整除就是
    public boolean isPowerOfThree2(int n) {
        return n > 0 && 1162261467%n == 0;
    }

    public boolean isPowerOfThree3(int n) {
        return Math.log10(n)/Math.log10(3) % 1 == 0;
    }
}

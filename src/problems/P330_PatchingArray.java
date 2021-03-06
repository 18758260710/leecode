package problems;

/**
 * @author wengtao
 * @date 2021/2/18
 **/
public class P330_PatchingArray {
    //贪心 mysolution
    public int minPatches(int[] nums, int n) {
        long current = 1;
        int index = 0;
        int result = 0;
        while (current <= n && index < nums.length) {
            if (current < nums[index]) {
                result++;
                current *= 2;
            } else {
                current += nums[index];
                index++;
            }
        }
        while (current <= n) {
            result++;
            current *= 2;
        }
        return result;
    }

    //贪心 更简洁
    public int minPatches2(int[] nums, int n) {
        int patches = 0;
        long x = 1;
        int length = nums.length, index = 0;
        while (x <= n) {
            if (index < length && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                patches++;
            }
        }
        return patches;
    }

    public static void main(String[] args) {
        System.out.println(new P330_PatchingArray().minPatches(new int[]{}, 8));
    }
}

package problems;

public class SingleNumber_136 {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num:nums){
            result ^= num;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(~0);
        System.out.println(new SingleNumber_136().singleNumber(new int[]{1,1,2,2,5}));
    }
}

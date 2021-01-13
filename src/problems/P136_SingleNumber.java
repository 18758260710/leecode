package problems;

public class P136_SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num:nums){
            result ^= num;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(~0);
        System.out.println(new P136_SingleNumber().singleNumber(new int[]{1,1,2,2,5}));
    }
}

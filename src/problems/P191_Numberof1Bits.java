package problems;

public class P191_Numberof1Bits {
    //my solution1 1ms
    public int hammingWeight(int n) {
        int result = 0;
        for (int i=0;i<32;i++){
            result += n&1;
            n = n >>> 1;
        }
        return result;
    }

    public int hammingWeight2(int n) {
        int sum = 0;
        for(int i=0; i< 32 && n != 0; i++, n >>=1) sum+=n&1;
        return sum;
    }

    //official solution2 0ms
    public int hammingWeight3(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
            System.out.println(Integer.toBinaryString(n));
        }
        return sum;
    }

    public static void main(String[] args) {
        new P191_Numberof1Bits().hammingWeight3(12343232);
    }
}

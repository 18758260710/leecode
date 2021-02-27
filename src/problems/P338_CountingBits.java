package problems;

/**
 * @author wengtao
 * @date 2021/2/27
 **/
public class P338_CountingBits {
    //2ms
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; ++i)
            ans[i] = popcount(i);
        return ans;
    }
    private int popcount(int x) {
        int count;
        for (count = 0; x != 0; ++count)
            x &= x - 1;
        return count;
    }

    // 最高位变化
    // P(x+b)=P(x)+1,  b=2^m>x
    //当x < 一个二次幂时  x + 这个二次幂 1的个数会比x多1
    public int[] countBits2(int num) {
        int[] ans = new int[num + 1];
        int i = 0, b = 1;

        while (b <= num) {
            while(i < b && i + b <= num){
                ans[i + b] = ans[i] + 1;
                ++i;
            }
            i = 0;   // reset i
            b <<= 1; // b = 2b
        }
        return ans;
    }

    // 最低位变化
    // P(x)=P(x/2)+(xmod2)
    //当x最低位为1 那么x/2的1的个数比x少1 否则相等
    public int[] countBits3(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i)
            ans[i] = ans[i >> 1] + (i & 1); // x / 2 is x >> 1 and x % 2 is x & 1
        return ans;
    }

    // 最低为1的位变化
    // P(x)=P(x&(x−1))+1;
    //去掉最低为1的位 差1
    public int[] countBits4(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i)
            ans[i] = ans[i & (i - 1)] + 1;
        return ans;
    }

}

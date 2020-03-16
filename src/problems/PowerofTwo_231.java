package problems;

/**
 * Created by Administrator on 2020/2/5.
 */
public class PowerofTwo_231 {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}

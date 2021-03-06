package problems;

/**
 * @author wengtao
 * @date 2021/2/27
 **/
public class P342_PowerofFour {
    public boolean isPowerOfFour(int n) {
        if (n == 0) return false;
        //只有一个1
        if ((n & n-1) > 0) return false;
        //1的位置正确
        return (n & 0xaaaaaaaa) == 0;
    }
}

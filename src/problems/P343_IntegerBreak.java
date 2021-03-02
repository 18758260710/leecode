package problems;

/**
 * @author wengtao
 * @date 2021/2/27
 **/
public class P343_IntegerBreak {
    public int integerBreak(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int num = n/3;
        int mod = n%3;
        if (mod == 1){
            num--;
            mod = 4;
        }
        if (mod == 0){
            mod = 1;
        }
        return (int) (Math.pow(3, num) * mod);
    }

    public static void main(String[] args) {
        System.out.println(new P343_IntegerBreak().integerBreak(10));
    }
}

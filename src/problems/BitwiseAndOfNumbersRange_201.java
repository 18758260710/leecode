package problems;

/**
 * Created by Administrator on 2020/1/9.
 */
public class BitwiseAndOfNumbersRange_201 {
    public int rangeBitwiseAnd(int m, int n) {
        while (n > m){
            n&=n-1;//最后一个1变为0
        }
        return n;
    }

    public static void main(String[] args) {
        int a=new BitwiseAndOfNumbersRange_201().rangeBitwiseAnd(5,7);
        System.out.println(a);
    }
}

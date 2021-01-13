package problems;

/**
 * Created by Administrator on 2020/2/6.
 */
public class P233_NumberofDigitOne {
    //按位计数
    public int countDigitOne(int n) {
        if (n<1)return 0;
        int result = 0;
        long divide = 10;
        while (true){
            long a = n/divide*divide/10;
            long b = n%divide;
            result+=a;
            b =b-divide/10+1;
            if (b<0)b=0;
            if (b>divide/10)b=divide/10;
            result +=b;
            if (a==0)break;
            divide*=10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new P233_NumberofDigitOne().countDigitOne(1633388154));
    }
}

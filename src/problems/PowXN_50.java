package problems;

public class PowXN_50 {

    //赖皮做法
    public double myPow(double x, int n) {

        return Math.pow(x,n);
    }

    //正经做法
    public double myPow2(double x, int n) {
        return pow(x,n);
    }

    public double pow(double x, long n) {
        if(n == 0)
            return 1;
        long m = n;
        if(n<0){
            m = -m;
            x = 1/x;
        }
        return (m%2 == 0) ? pow(x*x, m/2) : x*pow(x*x, m/2);
    }

    //best by SpicyDog
    public double myPow3(double x, int n) {
        if(n == 0) return 1.;
        double res = myPow3(x, n / 2);
        return n % 2 == 0 ? res * res : n < 0 ? res * res * (1 / x) : res * res * x;
    }

    public static void main(String[] args) {
        System.out.println(new PowXN_50().myPow3(2,-4));
    }
}

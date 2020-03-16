package problems;

public class DivideTwoIntegers_29 {

    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int sign = 1;
        if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) {
            sign = -1;
        }
        dividend = -Math.abs(dividend);
        divisor = -Math.abs(divisor);

        int ans = divideMag(dividend, divisor);

        return sign > 0 ? ans : -ans;
    }

    private int divideMag(int dividend, int divisor) {
        if (divisor < dividend) {
            return 0;
        }
        int tempDivisor = divisor;
        int sum = 1;
        while (dividend <= tempDivisor << 1 && tempDivisor << 1 < 0) {
            sum = sum << 1;
            tempDivisor = tempDivisor << 1;
        }
        int left = divideMag(dividend - tempDivisor, divisor);
        return sum + left;
    }

    //by siyang3
    public int divide2(int dividend, int divisor) {
        if(dividend==Integer.MIN_VALUE && divisor==-1) return Integer.MAX_VALUE;
        if(dividend > 0 && divisor > 0) return divideHelper(-dividend, -divisor);
        else if(dividend > 0) return -divideHelper(-dividend,divisor);
        else if(divisor > 0) return -divideHelper(dividend,-divisor);
        else return divideHelper(dividend, divisor);
    }

    private int divideHelper(int dividend, int divisor){
        // base case
        if(divisor < dividend) return 0;
        // get highest digit of divisor
        int cur = 0, res = 0;
        while((divisor << cur) >= dividend && divisor << cur < 0 && cur < 31) cur++;
        res = dividend - (divisor << cur-1);
        if(res > divisor) return 1 << cur-1;
        return (1 << cur-1)+divide(res, divisor);
    }
}

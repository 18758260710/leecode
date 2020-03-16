package problems;

public class ReverseInteger_7 {

    //my solution 字符串操作较慢
    public int reverse(int x) {
        boolean minus = x < 0;
        long xlong = x;
        if (minus) {
            xlong = xlong * -1;
        }
        String xStr = xlong + "";
        xStr = new StringBuilder(xStr).reverse().toString();
        long result = Long.parseLong(xStr) * (minus ? -1 : 1);
        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
            return 0;
        } else {
            return (int) result;
        }
    }

    //official solution
    public int reverse2(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    //by binhnguyenvnuhcm
    //same as official
    public int reverse3(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x = x / 10;
        }

        if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
            return 0;
        } else {
            return (int)res;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ReverseInteger_7().reverse(-2147483648));
    }
}

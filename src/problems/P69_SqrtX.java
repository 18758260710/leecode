package problems;

public class P69_SqrtX {
    //牛顿迭代逼近
    public double mySqrt(int x) {
        double x1 = x;
        double x2 = x1/2;
        while(Math.abs(x1-x2) > 0.00000001)
        {
            x1 = x2;
            x2 = (x1+x/x1)/2;
        }
        return x1;
    }

    public static void main(String[] args) {
        System.out.println(new P69_SqrtX().mySqrt(2147395599));
        System.out.println(Math.sqrt(2147395599));
    }

    //Binary Search
    public int mySqrt2(int x) {
        if (x == 0) return 0;
        int start = 1, end = x;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid <= x / mid && (mid + 1) > x / (mid + 1))// Found the result
                return mid;
            else if (mid > x / mid)// Keep checking the left part
                end = mid;
            else
                start = mid + 1;// Keep checking the right part
        }
        return start;
    }
}

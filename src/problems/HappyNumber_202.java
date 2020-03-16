package problems;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2020/1/9.
 */
public class HappyNumber_202 {
    //my solution 3ms
    Set<Integer> exist = new HashSet<>();
    public boolean isHappy(int n) {
        exist.add(n);
        while (true){
            n = dojob(n);
            if (n==1)return true;
            if (!exist.add(n))return false;
        }
    }

    private int dojob(int n) {
        int result = 0;
        int a;
        while (n>0){
            a = n%10;
            result += a*a;
            n/=10;
        }
        return result;
    }

    public static void main(String[] args) {
            System.out.println(new HappyNumber_202().isHappy(20));
    }

    //用快慢指针判重
    public boolean isHappy2(int n) {
        int fast = n;
        int slow = n;
        do {
            slow = isHappyNum(slow);
            fast = isHappyNum(fast);
            fast = isHappyNum(fast);
        } while (fast != slow);

        return slow == 1;
    }

    public int isHappyNum(int n) {
        int remainder = 0;
        int sum = 0;
        while (n > 0) {
            remainder = (n % 10);
            sum += (remainder * remainder);
            n /= 10;
        }
        return sum;
    }

}

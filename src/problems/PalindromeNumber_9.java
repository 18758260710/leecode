package problems;

import java.util.ArrayList;
import java.util.List;

public class PalindromeNumber_9 {
    //my solution1
    public boolean isPalindrome(int x) {
        if (x<0)return false;
        StringBuilder stringBuilder = new StringBuilder(x+"");
        if (stringBuilder.toString().equals(stringBuilder.reverse().toString()))return true;
        return false;

    }
    //my solution2
    public boolean isPalindrome2(int x) {
        if (x<0)return false;
        List<Integer> temp = new ArrayList<>();
        while (x>0){
            temp.add(x%10);
            x=x/10;
        }
        for (int i=0;i<=temp.size()-i-1;i++){
            if (temp.get(i)!=temp.get(temp.size()-i-1))return false;
        }
        return true;
    }

    //official solution
    public boolean isPalindrome3(int x) {
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        return x == revertedNumber || x == revertedNumber/10;

    }

    //by zeason-17  faster
    public boolean isPalindrome4(int x) {
        final int[] POWERS_OF_10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
        if (x < 0){
            return false;
        }
        if (x < 10){
            return true;
        }
        int i = 0;
        int length;
        for (length = 0; length < 10  && x / POWERS_OF_10[length] > 0; length++);
        while(x != 0){
            if (x % 10 != x / POWERS_OF_10[length - 1 - i]){
                return false;
            }
            x = (x % POWERS_OF_10[length - 1 - i]) / 10;
            i += 2;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromeNumber_9().isPalindrome(213124));
    }
}

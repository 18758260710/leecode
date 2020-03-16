package problems;

/**
 * Created by Administrator on 2020/3/12.
 */
public class LemonadeChange_860 {
    //2ms
    public boolean lemonadeChange(int[] bills) {
        int[] money = new int[2];
        for (int bill : bills) {
            if (bill == 5) money[0]++;
            else if (bill == 10) {
                if (money[0] == 0) return false;
                money[0]--;
                money[1]++;
            } else if (bill == 20) {
                if (money[1] == 0) {
                    if (money[0] < 3) return false;
                    money[0] -= 3;
                } else {
                    if (money[0] < 1) return false;
                    money[0]--;
                    money[1]--;
                }
            }
        }
        return true;
    }
}

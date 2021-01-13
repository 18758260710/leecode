package problems;

import java.util.ArrayList;
import java.util.List;

public class P60_PermutationSequence {

    //my solution 1ms
    public String getPermutation(int n, int k) {
        List<Integer> list = new ArrayList<>();
        int step = 1;
        for (int i = 1; i <= n; i++) {
            list.add(i);
            step *= i;
        }
        step /= n;
        k--;
        n--;
        StringBuilder stringBuilder = new StringBuilder();
        while (n > 0) {
            int index = k / step;
            k = k % step;
            step /= n;
            n--;

            stringBuilder.append(list.remove(index));
        }
        stringBuilder.append(list.get(0));
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new P60_PermutationSequence().getPermutation2(3, 4));
    }

    //fast 0ms 数组实现，更快
    public String getPermutation2(int n, int k) {
        k--;
        boolean[] mask = new boolean[n];
        char[] cStr = new char[n];
        int max = maxCount(n);

        for (int i = 0; i < n; i++) {
            max = max / (n - i);
            int th = k / max;
            k = k % max;
            for (int j = 0; j < n; j++) {
                if (!mask[j]) {
                    if (th == 0) {
                        mask[j] = true;
                        cStr[i] = (char) (j + (int) '1');
                        break;
                    }
                    th--;
                }
            }
        }
        return new String(cStr);
    }

    private int maxCount(int n) {
        int ret = 1;
        for (int i = 0; i < n; i++) {
            ret *= (i + 1);
        }
        return ret;
    }
}

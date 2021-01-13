package problems;

import java.util.ArrayList;
import java.util.List;

public class P77_Combinations {

    //my solution1
    public List<List<Integer>> combine(int n, int k) {
        if (n < k || k == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        if (k == 1) {
            while (n > 0) {
                List<Integer> temp = new ArrayList<>();
                temp.add(n);
                result.add(temp);
                n--;
            }
        } else {
            while (n >= k) {
                List<List<Integer>> temps = combine(n - 1, k - 1);
                for (List<Integer> temp : temps) {
                    temp.add(n);
                }
                result.addAll(temps);
                n--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new P77_Combinations().combine2(4, 2));
    }

    //my solution2 dp
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> temp = new ArrayList<>();

        int current = 1;
        while (true) {
            if (k == 0) {
                result.add(new ArrayList<>(temp));
                current = temp.remove(temp.size() - 1) + 1;
                k++;
            } else {
                if (n - current + 1 < k) {
                    if (temp.isEmpty()) {
                        break;
                    }
                    current = temp.remove(temp.size() - 1) + 1;
                    k++;
                } else {
                    temp.add(current);
                    current++;
                    k--;
                }
            }
        }

        return result;
    }

    //递归dp
    public List<List<Integer>> combine3(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        helper(1, n - k + 1, k, new ArrayList<>(), result);
        return result;
    }

    public void helper(int s, int n, int k, List<Integer> t, List<List<Integer>> r) {
        if (t.size() == k) {
            r.add(new ArrayList<>(t));
            return;
        }
        for (int i = s; i <= n; i++) {
            t.add(i);
            helper(i + 1, n + 1, k, t, r);
            t.remove(t.size() - 1);
        }
    }
}

package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/1/16.
 */
public class P216_CombinationSumIII {
    //1ms
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        dfs(1,n,path,res,k);

        return res;
    }

    void dfs(int cur, int target, List<Integer> path, List<List<Integer>> res,int count) {
        if (count == 1) {
            if (target>=cur && target<10){
                path.add(target);
                res.add(new ArrayList(path));
                path.remove(path.size() - 1);
            }
            return;
        }
        for (int i = cur; i < 10 && i<target; i++) {
            path.add(i);
            dfs(i + 1, target - i, path, res,count-1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(1 ^ 2 ^3 ^ 4 ^8);
    }
}

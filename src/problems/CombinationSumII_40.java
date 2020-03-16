package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CombinationSumII_40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> candidatesList = Arrays.stream(candidates).boxed().collect(Collectors.toList());
        List<List<Integer>> result = new ArrayList<>();
        getResult(result, new ArrayList<>(), candidatesList, target);
        return result;
    }

    private void getResult(List<List<Integer>> result, List<Integer> cur, List<Integer> candidates, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(cur));
        }
        if (candidates.size() > 0 && target > 0) {
            int count = 1;

            Integer candidate = candidates.remove(0);
            while (candidates.size() > 0) {
                if (candidates.get(0) == candidate) {
                    count++;
                    candidates.remove(0);
                } else {
                    break;
                }
            }
            int a = count;
            while (a >= 0) {
                getResult(result, cur, candidates, target);
                if (a > 0) {
                    cur.add(candidate);
                    target -= candidate;
                }
                a--;
            }
            while (count > 0) {
                candidates.add(candidate);
                cur.remove(candidate);
                count--;
            }

        }
    }

    //by lchen77 dfs
    public List<List<Integer>> combinationSum3(int[] cand, int target) {
        Arrays.sort(cand);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs_com(cand, 0, target, path, res);
        return res;
    }

    void dfs_com(int[] cand, int cur, int target, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList(path));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = cur; i < cand.length; i++) {
            if (i > cur && cand[i] == cand[i - 1]) {
                continue;
            }
            path.add(path.size(), cand[i]);
            dfs_com(cand, i + 1, target - cand[i], path, res);
            path.remove(path.size() - 1);
        }
    }
}

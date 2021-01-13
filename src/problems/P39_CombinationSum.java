package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P39_CombinationSum {

    //my solution
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> base = new ArrayList<>();
        int biggest = candidates[candidates.length - 1];
        int[] nextcandidates = new int[candidates.length - 1];
        System.arraycopy(candidates, 0, nextcandidates, 0, candidates.length - 1);
        List<List<Integer>> result = combinationSum(nextcandidates, target);

        while (target >= biggest) {
            base.add(biggest);
            target -= biggest;
            if (target == 0) {
                result.add(base);
                break;
            }
            List<List<Integer>> temps = combinationSum(nextcandidates, target);
            for (List<Integer> temp : temps) {
                temp.addAll(base);
                result.add(temp);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new P39_CombinationSum().combinationSum2(new int[]{2, 3, 5}, 8));
    }

    //by luckygxf faster bfs
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        getResult(result, new ArrayList<>(), candidates, target, 0);

        return result;
    }

    private void getResult(List<List<Integer>> result, List<Integer> cur, int candidates[], int target, int start){
        if(target > 0){
            for(int i = start; i < candidates.length && target >= candidates[i]; i++){
                cur.add(candidates[i]);
                getResult(result, cur, candidates, target - candidates[i], i);
                cur.remove(cur.size() - 1);
            }//for
        }//if
        else if(target == 0 ){
            result.add(new ArrayList<>(cur));
        }//else if
    }
}

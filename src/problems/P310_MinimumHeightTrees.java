package problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author wengtao
 * @date 2020/4/3
 **/
public class P310_MinimumHeightTrees {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();

        if (n == 1){
            ans.add(0);
            return ans;
        }
        //每个节点的度数
        int[] degree = new int[n];
        List<List<Integer>> map = new ArrayList<>();
        for (int i=0;i<n;i++) {
            map.add(new ArrayList<>());
        }
        for (int[] edge:edges){
            degree[edge[0]]++;
            degree[edge[1]]++;
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0;i < n;i++){
            if (degree[i] == 1){
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()){
            ans=new ArrayList<>();
            int size = queue.size();
            for (int i=0;i<size;i++){
                int cur = queue.poll();
                ans.add(cur);
                List<Integer> nexts = map.get(cur);
                for (Integer next:nexts){
                    degree[next]--;//删除叶子节点后，跟其相邻的节点的度数要减少
                    if (degree[next] == 1){
                        queue.offer(next);
                    }
                }
            }
        }

        return  ans;
    }

    public static void main(String[] args) {
        new P310_MinimumHeightTrees().findMinHeightTrees(4,new int[][]{{1, 0}, {1, 2}, {1, 3}});
    }
}

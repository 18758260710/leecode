package problems;

import java.util.List;

public class Triangle_120 {
    //my solution1 3ms 不使用额外空间
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i=triangle.size()-2;i>=0;i--){
            List<Integer> temp = triangle.get(i);
            List<Integer> next = triangle.get(i+1);
            for (int j=0;j<temp.size();j++){
                temp.set(j,Math.min(next.get(j),next.get(j+1))+temp.get(j));
            }
        }
        return triangle.get(0).get(0);
    }

    // 1ms same as mine 用数组代替list
    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return -1;
        int size = triangle.size();
        int[] dp =new int[size];
        for(int i = 0; i < size; i++){
            dp[i] = triangle.get(size - 1).get(i);
        }
        for (int i = size - 2; i >= 0; i--){
            for (int j = 0; j <= i; j ++){
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}

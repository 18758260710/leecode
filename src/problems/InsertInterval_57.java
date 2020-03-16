package problems;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval_57 {
    //my solution 1ms
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length==0){
            int[][] result = new int[1][];
            result[0]=newInterval;
            return result;
        }
        List<int[]> list = new ArrayList<>();
        boolean mearge = true;
        for (int[] interval:intervals){
            if (!(interval[0]>newInterval[1]||interval[1]<newInterval[0])){
                newInterval[1] = Math.max(interval[1],newInterval[1]);
                newInterval[0] = Math.min(interval[0],newInterval[0]);
            }else {
                if (mearge&&interval[0]>newInterval[1]){
                    list.add(newInterval);
                    mearge=false;
                }
                list.add(interval);
            }
        }
        if (mearge){
            list.add(newInterval);
        }

        int[][] re = new int[list.size()][];

        return list.toArray(re);
    }

    public static void main(String[] args) {
        new InsertInterval_57().insert(new int[][]{{1,5}},new int[]{2,7});
    }
}

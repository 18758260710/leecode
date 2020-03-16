package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalsTriangleII_119 {
    //my solution1 1ms
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        if (rowIndex<0)return result;
        result.add(1);
        if (rowIndex==0)return result;
        int row = 1;
        while (rowIndex>=row){
            int first = 1;
            int second;
            for (int i=1;i<result.size();i++){
                second = result.get(i);
                result.set(i,first+second);
                first = second;
            }
            result.add(1);
            row++;
        }
        return result;
    }

    //0ms 直接计算每一位
    public List<Integer> getRow2(int rowIndex) {
        Integer[] integers = new Integer[rowIndex + 1];
        integers[0] = 1;
        for (int col = 1; col <= rowIndex; col++) {
            integers[col] = (int)((long)integers[col - 1] * (rowIndex - col + 1) / col);
        }
        return Arrays.asList(integers);
    }
}

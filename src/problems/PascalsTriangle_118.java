package problems;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle_118 {
    //my solution1 0ms
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows==0)return result;
        List<Integer> first = new ArrayList<>();
        first.add(1);
        result.add(first);
        if (numRows==1)return result;
        int row = 2;
        while (numRows>=row){
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            for (int i=0;i<first.size()-1;i++){
                temp.add(first.get(i)+first.get(i+1));
            }
            temp.add(1);
            result.add(temp);
            first = temp;
            row++;
        }
        return result;
    }

    //official solution same as mine
    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();

        // First base case; if user requests zero rows, they get zero rows.
        if (numRows == 0) {
            return triangle;
        }

        // Second base case; first row is always [1].
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);

        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(rowNum-1);

            // The first row element is always 1.
            row.add(1);

            // Each triangle element (other than the first and last of each row)
            // is equal to the sum of the elements above-and-to-the-left and
            // above-and-to-the-right.
            for (int j = 1; j < rowNum; j++) {
                row.add(prevRow.get(j-1) + prevRow.get(j));
            }

            // The last row element is always 1.
            row.add(1);

            triangle.add(row);
        }

        return triangle;
    }
}

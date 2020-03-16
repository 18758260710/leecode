package problems;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion_6 {
    //my solution
    //same as official solution2
    public String convert(String s, int numRows) {
        if (numRows==1)return s;
        int length = s.length();
        StringBuilder stringBuilder = new StringBuilder();
        int i=0;
        while (i<length){
            stringBuilder.append(s.charAt(i));
            i=i+2*numRows-2;
        }
        for (int row=1;row<numRows-1;row++){
            int start=row;
            while (start<length){
                stringBuilder.append(s.charAt(start));
                start=start+(numRows-row-1)*2;
                if (start>=length)break;
                stringBuilder.append(s.charAt(start));
                start=start+row*2;
            }
        }
        i=numRows-1;
        while (i<length){
            stringBuilder.append(s.charAt(i));
            i=i+2*numRows-2;
        }
        return stringBuilder.toString();
    }

    //official solution1
    public String convert2(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }

    //official solution2
    public String convert3(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }
}

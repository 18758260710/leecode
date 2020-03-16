package problems;

/**
 * Created by Administrator on 2020/2/13.
 */
public class HIndexII_275 {
    //二分
    public int hIndex(int[] citations) {
        int n = citations.length;
        int middle, left = 0, right = n - 1;
        while (left <= right) {
            middle = left + (right - left) / 2;
            if (citations[middle] == n - middle) return n - middle;
            else if (citations[middle] < n - middle) left = middle + 1;
            else right = middle - 1;
        }
        return n - left;

    }
}

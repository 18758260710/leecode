package problems;

import java.util.Arrays;

/**
 * Created by Administrator on 2020/2/13.
 */
public class HIndex_274 {
    //O(nlogn)
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
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

    //O(n)
    public int hIndex2(int[] citations) {
        int[] counter = new int[citations.length + 1];
        //最大不超过length
        for (int x : citations) {
            x = Math.min(x, citations.length);
            counter[x]++;
        }
        //从后往前加找点
        int res = citations.length;
        int s = counter[citations.length];
        while (s < res) {
            res--;
            s += counter[res];
        }
        return res;
    }
}

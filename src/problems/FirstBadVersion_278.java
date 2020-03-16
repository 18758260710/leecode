package problems;

/**
 * Created by Administrator on 2020/2/13.
 */
public class FirstBadVersion_278 {
    public int firstBadVersion(int n) {
        int middle, left = 1, right = n;
        while (left < right) {
            middle = left + (right - left) / 2;
            if (isBadVersion(middle)){
                right=middle;
            }else {
                left=middle+1;
            }
        }
        return left;
    }

    private boolean isBadVersion(int middle) {
        return middle==0;
    }
}

package problems;

/**
 * @author wengtao
 * @date 2020/11/6
 **/
public class P1011_CapacityToShipPackagesWithinDDays {
    //不用二分75ms
    public int shipWithinDays(int[] weights, int D) {
        long sum = 0;
        int max = 0;
        for (int i = 0; i < weights.length; ++i) {
            sum += weights[i];
            max = Math.max(max,weights[i]);
        }
        int K = (int) ((sum - 1) / D + 1);
        K = Math.max(max, K);
        while (true) {
            int s = 0;
            int current = 0;
            for (int p : weights) {
                current+=p;
                if (current > K){
                    s++;
                    current = p;
                }
            }
            s++;
            if (s > D) {
                ++K;
            } else {
                return K;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new P1011_CapacityToShipPackagesWithinDDays().shipWithinDays2(new int[]{1,2,3,4,5,6,7,8,9,10},5));
    }
    //二分 8ms
    public int shipWithinDays2(int[] weights, int D) {
        long sum = 0;
        int max = 0;
        for (int i = 0; i < weights.length; ++i) {
            sum += weights[i];
            max = Math.max(max,weights[i]);
        }
        int left = (int) ((sum - 1) / D + 1);
        left = Math.max(max, left);
        int right = max*(weights.length/D+1);
        while (left<right){
            int middle = left + (right-left)/2;

            if (can(middle,weights,D)){
                right = middle;
            }else {
                left = middle+1;
            }
        }
        return left;
    }

    private boolean can(int middle, int[] weights, int d) {
        int s = 0;
        int current = 0;
        for (int p : weights) {
            current+=p;
            if (current > middle){
                s++;
                current = p;
            }
        }
        s++;
        return s<=d;
    }
}

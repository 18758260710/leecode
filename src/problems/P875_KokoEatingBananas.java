package problems;

/**
 * @author wengtao
 * @date 2020/11/5
 **/
public class P875_KokoEatingBananas {
    //my solution 11ms
    public int minEatingSpeed(int[] piles, int H) {
        long sum = 0;
        int max = 0;
        for (int pile : piles) {
            sum+=pile;
            max = Math.max(max,pile);
        }
        int min = (int) (sum/H);
        while (min<max){
            int middle = min + (max-min)/2;
            if (middle==0){
                return 1;
            }
            if (canEat(middle,piles,H)){
                max = middle;
            }else {
                min = middle+1;
            }
        }
        return min;
    }

    private boolean canEat(int middle, int[] piles, int h) {
        int time = 0;
        for (int pile : piles) {
            time += (pile - 1) / middle + 1;
        }
        return time<=h;
    }

    public static void main(String[] args) {
        System.out.println(new P875_KokoEatingBananas().minEatingSpeed(new int[]{980628391,681530205,734313996,168632541},819870953));
    }

    //数学 1ms 不用二分更快
    public int minEatingSpeed2(int[] piles, int H) {
        long sum = 0;
        for (int i = 0; i < piles.length; ++i) {
            sum += piles[i];
        }
        int K = (int) ((sum - 1) / H + 1);
        if (K == 0) {
            K = 1;
        }
        while (true) {
            int s = 0;
            for (int p : piles) {
                s += (p - 1) / K + 1;
            }
            if (s > H) {
                ++K;
            } else {
                return K;
            }
        }
    }
}

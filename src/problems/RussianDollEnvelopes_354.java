package problems;

import java.util.Arrays;

/**
 * Created by Administrator on 2020/3/4.
 */
public class RussianDollEnvelopes_354 {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length==0)return 0;
        if (envelopes.length==1)return 1;
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });
        int result = 0;
        int[] dp = new int[envelopes.length];
        dp[0] = 1;
        for (int i=1; i<envelopes.length; i++) {
            dp[i]=1;
            for(int j=i-1; j>=0; j--){
                //由于要求长度和宽度都要更大，因此一种长度的信封只能刘一个
                //因此需要增加判断条件vec[i].length!=vec[j].length
                if(envelopes[i][1]>envelopes[j][1] && dp[i]<dp[j]+1){
                    dp[i]=dp[j]+1;
                }
            }
            result = Math.max(dp[i],result);
        }
        return result;
    }

    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes.length <= 0) return 0;
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });
        int[] dp = new int[envelopes.length];
        int len = 0;
        for (int i = 0; i < envelopes.length; i++) {
            if (len == 0) {
                dp[len] = envelopes[i][1];
                len++;
                continue;
            }
            int start = 0;
            int end = len - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (dp[mid] == envelopes[i][1]) {
                    start = mid;
                    break;
                } else if (dp[mid] > envelopes[i][1]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            if (start == len) {
                len++;
            }
            dp[start] = envelopes[i][1];
        }
        return len;
    }

    public static void main(String[] args) {
        new RussianDollEnvelopes_354().maxEnvelopes2(new int[][]{{4,5},{4,6},{6,7},{2,3},{1,1}});
    }
}

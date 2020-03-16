package problems;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Created by Administrator on 2020/2/12.
 */
public class UglyNumberII_264 {
    //小顶堆 slow
    public int nthUglyNumber(int n) {
        Queue<Long> queue = new PriorityQueue<>();
        long answer=1;
        for (int i=1;i<n;++i)
        {
            queue.offer(answer*2);
            queue.offer(answer*3);
            queue.offer(answer*5);
            answer=queue.peek();
            queue.poll();
            while (!queue.isEmpty() && answer==queue.peek())
                queue.poll();
        }
        return (int) answer;
    }
    //set 判重
    public int nthUglyNumber2(int n) {
        Queue<Long> queue = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        int[] aa = new int[]{2,3,5};
        long answer=1;
        for (int i=1;i<n;++i)
        {
            for (int a:aa){
                if (!set.contains(answer*a)){
                    queue.offer(answer*a);
                    set.add(answer*a);
                }
            }
            answer=queue.poll();
        }
        return (int) answer;
    }

    /**
     * dp 三指针法
     * 每一个丑数都是由一个比他小的丑数*2 *3 或 *5得到的
     * 因此由i2 i3 i5来代表有可能的三个丑数的下标 （有可能相同）
     * 当发现 i2*2是下一个丑数 则i2++向后推移 遍历出所有丑数
     *  刚开始 0  0  0      下一个可能的丑数是 2 3 5    2最小
     *  变成   1  0  0      下一个可能的丑数是 4 3 5    3最小
     *  变成   1  1  0      下一个可能的丑数是 4 6 5    5最小
     *  。。。。。。。
     * @param n
     * @return
     */
    public int nthUglyNumber3(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < n; i++) {
            int min = Math.min(dp[i2] * 2, Math.min(dp[i3] * 3, dp[i5] * 5));
            if (min == dp[i2] * 2) i2++;
            if (min == dp[i3] * 3) i3++;
            if (min == dp[i5] * 5) i5++;
            dp[i] = min;
        }

        return dp[n - 1];
    }

}

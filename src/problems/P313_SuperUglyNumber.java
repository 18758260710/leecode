package problems;

import java.util.Arrays;

/**
 * @author wengtao
 * @date 2020/4/9
 **/
public class P313_SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        dp[0] = 1;
        int[] indexs = new int[primes.length];
        for (int i = 1; i < n; i++) {
            int min=Integer.MAX_VALUE;
            for (int k=0;k<primes.length;k++){
                min = Math.min(min,dp[indexs[k]]*primes[k]);
            }
            for (int k=0;k<primes.length;k++){
                if (dp[indexs[k]]*primes[k]==min){
                    indexs[k]++;
                }
            }
            dp[i] = min;
        }

        return dp[n-1];
    }

    public int nthSuperUglyNumber2(int n, int[] primes) {
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;
        int primesNumber = primes.length, min = 1, next = 1;
        //存两个index数组 少一次循环
        int[] primeIndexes = new int[primesNumber];
        int[] tempPrimes = new int[primesNumber];

        Arrays.fill(tempPrimes, 1);

        for (int i = 0; i < n; i++) {
            uglyNumbers[i] = min;
            min = Integer.MAX_VALUE;
            for (int j = 0; j < tempPrimes.length; j++) {
                if (tempPrimes[j] == next) {
                    tempPrimes[j] = primes[j] * uglyNumbers[primeIndexes[j]];
                    primeIndexes[j]++;
                }
                min = Math.min(tempPrimes[j], min);
            }
            next = min;
        }

        return uglyNumbers[n - 1];
    }
}

package problems;

import java.util.HashMap;
import java.util.Map;

public class PalindromePartitioningII_132 {
    //my solution1 cache在s slow
    Map<String,Integer> cache = new HashMap<>();
    public int minCut(String s) {
        if (s.isEmpty())return 0;
        return partition(s)-1;
    }
    public Integer partition(String s) {
        if (cache.containsKey(s))return cache.get(s);

        if (s.length()==1||isPalindrome(s)){
            cache.put(s,1);
            return 1;
        }

        int next = partition(s.substring(1))+1;

        for (int i=1;i<s.length()-1;i++){
            if (s.charAt(i)==s.charAt(0)&&isPalindrome(s.substring(0,i+1))){
                next = Math.min(next,partition(s.substring(i+1))+1);
            }
        }
        cache.put(s,next);
        return next;
    }
    public boolean isPalindrome(String a){
        StringBuilder stringBuilder = new StringBuilder(a);
        return stringBuilder.toString().equals(stringBuilder.reverse().toString());
    }

    //my solution2 双重缓存 4ms
    public int minCut2(String s) {
        int n = s.length();
        if (n==0)return 0;
        boolean[][] isPalindrome = new boolean[n][n];
        cache2 = new int[n];
        // expand panlindrome
        for (int center = 0; center < n; center++) {
            expand(isPalindrome, s, center, center);
            expand(isPalindrome, s, center, center+1);
        }

        return getPartitions(isPalindrome,n,0)-1;

    }
    int[] cache2;

    private int getPartitions(boolean[][] isPalindrome, int length, int i) {
        if (isPalindrome[i][length-1])return 1;
        if (cache2[i]>0)return cache2[i];
        int temp = Integer.MAX_VALUE;
        for (int j = i; j < length-1; j++) {
            if (isPalindrome[i][j]) {
                temp  = Math.min(temp,getPartitions(isPalindrome, length, j+1)+1);
            }
        }
        cache2[i]=temp;
        return temp;
    }

    private void expand(boolean[][] isPalindrome, String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            isPalindrome[l][r] = true;
            l--; r++;
        }
    }

    //边寻找边记录 1ms
    public int minCut3(String s) {
        int n = s.length();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = i;
        }
        for (int i = 0; i < n; i++) {
            //odd
            search(dp, i, i, s);
            //even
            search(dp, i, i + 1, s);
        }
        return dp[n - 1];
    }

    private void search(int[] dp, int left, int right, String s) {
        while (left >= 0 && right < s.length()
            && s.charAt(left) == s.charAt(right)) {
            if (left == 0) {
                dp[right] = 0;
            } else{
                dp[right] = Math.min(dp[right], dp[left - 1] + 1);
            }
            left--;
            right++;
        }
    }
}
